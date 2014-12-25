package main;

import accounting.AccountService;
import accounting.AccountServiceImpl;
import accounting.database.DatabaseBuilder;
import frontend.servlets.*;
import frontend.websocket.WebSocketService;
import frontend.websocket.WebSocketServiceImpl;
import mechanics.GameMechanics;
import mechanics.GameMechanicsImpl;
import messageSystem.MessageSystem;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resource.ResourceFactory;
import resource.ServerResource;

import javax.servlet.Servlet;

/**
 * @author v.chibrikov
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ServerResource serverResource = (ServerResource) ResourceFactory.instance().get("./data/server.xml");

        System.out.append("Starting at port: ").append(String.valueOf(serverResource.getPort())).append('\n');
        final Server server = new Server(serverResource.getPort());

        final MessageSystem messageSystem = new MessageSystem();
        final DatabaseBuilder databaseBuilder = new DatabaseBuilder("hibernate.cfg.xml");

        final AccountService accountService = new AccountServiceImpl(messageSystem, databaseBuilder.getUsersDAO());
        final WebSocketService webSocketService = new WebSocketServiceImpl(messageSystem);
        final GameMechanics gameMechanics = new GameMechanicsImpl(messageSystem);

        final ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        addServlet(context, new SignupServlet(accountService));
        addServlet(context, new LoginServlet(accountService));
        addServlet(context, new LogoutServlet(accountService));
        addServlet(context, new AdminPageServlet(accountService));
        addServlet(context, new UserPageServlet(accountService));
        addServlet(context, new ScoreBoardServlet(accountService));
        addServlet(context, new WebSocketGameServlet(accountService, webSocketService));
        addServlet(context, new WebSocketMobileServlet());

        final ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("static");

        final HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});
        server.setHandler(handlers);

        server.start();
        new Thread(gameMechanics).start();
        new Thread(webSocketService).start();
        new Thread(accountService).start();
    }

    private static void addServlet(ServletContextHandler contextHandler, PageUrlServlet servlet) {
        contextHandler.addServlet(new ServletHolder((Servlet) servlet), servlet.getPageUrl());
    }
}