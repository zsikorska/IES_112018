package com.embeddedjetty;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class EmbeddedJetty {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8680);

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(HelloServlet.class, "/");

        server.start();
        server.join();

    }

    public static class HelloServlet extends HttpServlet
    {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            String username = request.getParameter("username");

            if (username == null)
                throw new NullPointerException("Parameter is missing - there is no username");
            else {

                try {
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>WelcomeServlet</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h2>Hello " + username + "!" + "</h2>");
                    out.println("<img src='https://img.freepik.com/premium-vector/black-cat-says-hello-cat-waves-his-paw-animal-greets-black-halloween-kitten-raised-his-hand_502320-1176.jpg'>");
                    out.println("</body>");
                    out.println("</html>");
                } finally {
                    out.close();
                }
            }
        }
    }
}
