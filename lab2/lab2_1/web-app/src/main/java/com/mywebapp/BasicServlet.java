package com.mywebapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BasicServlet", urlPatterns = {"/BasicServlet"})
public class BasicServlet extends HttpServlet {
    private static final long serialVersionUID = -1915463532411657451L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException
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
