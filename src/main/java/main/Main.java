package main;

import base.AccountService;
import base.GameMechanics;
import base.PageUrlServlet;
import base.WebSocketService;
import mechanics.GameMechanicsImpl;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resource.ResourceFactory;
import resource.ServerResource;
import service.AccountServiceImpl;
import service.WebSocketServiceImpl;
import servlets.*;

import javax.servlet.Servlet;

/**
 * @author v.chibrikov
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ServerResource serverResource = (ServerResource) ResourceFactory.instance().get("./data/server.xml");

        System.out.append("Starting at port: ").append(String.valueOf(serverResource.getPort())).append('\n');

        Server server = new Server(serverResource.getPort());

        AccountService accountService = new AccountServiceImpl();
        WebSocketService webSocketService = new WebSocketServiceImpl();
        GameMechanics gameMechanics = new GameMechanicsImpl(webSocketService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        addServlet(context, new SignUpServlet(accountService));
        addServlet(context, new LogInServlet(accountService));
        addServlet(context, new LogOutServlet(accountService));
        addServlet(context, new AdminPageServlet(accountService));
        addServlet(context, new UserPageServlet(accountService));
        addServlet(context, new ScoreBoardServlet());
        addServlet(context, new WebSocketGameServlet(gameMechanics, accountService, webSocketService));

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("static");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});
        server.setHandler(handlers);

        server.start();
        gameMechanics.run();
    }

    private static void addServlet(ServletContextHandler contextHandler, PageUrlServlet servlet) {
        contextHandler.addServlet(new ServletHolder((Servlet) servlet), servlet.getPageUrl());
    }
}