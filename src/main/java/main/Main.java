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
import service.AccountServiceImpl;
import service.WebSocketServiceImpl;
import servlets.*;

import javax.servlet.Servlet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author v.chibrikov
 */
public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.append("Use port as the first argument");
            System.exit(1);
        }

        String portString = args[0];
        final int port = Integer.valueOf(portString);
        System.out.append("Starting at port: ").append(portString).append('\n');

        Server server = new Server(port);

        AccountService accountService = new AccountServiceImpl();
        WebSocketService webSocketService = new WebSocketServiceImpl();
        GameMechanics gameMechanics = new GameMechanicsImpl(webSocketService);

        final Set<PageUrlServlet> servlets = new LinkedHashSet<>();
        servlets.add(new SignUpServlet(accountService));
        servlets.add(new SignInServlet(accountService));
        servlets.add(new SignOutServlet(accountService));
        servlets.add(new AdminPageServlet(accountService));
        servlets.add(new UserPageServlet(accountService));
        servlets.add(new ScoreBoardServlet());
        servlets.add(new WebSocketGameServlet(gameMechanics, accountService, webSocketService));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        for (PageUrlServlet servlet : servlets) {
            context.addServlet(new ServletHolder((Servlet) servlet), servlet.getPageUrl());
        }

        servlets.clear();

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("static");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});
        server.setHandler(handlers);

        server.start();
        gameMechanics.run();
    }
}