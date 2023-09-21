/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.semana04lpsw;

import com.mycompany.semana04lpsw.model.DaoUsuario;
import com.mycompany.semana04lpsw.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author thiago
 */
public class menu extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public String senha;
    DaoUsuario newDaoUsuario;

   /* @Override
    public void init() {
        senha = getServletConfig().getInitParameter("senha");
    }*/
 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        newDaoUsuario = new DaoUsuario(0, "th", "1234");
        String nomeInp = request.getParameter("usuario");
        String senhaInp = request.getParameter("senha");
        
        Usuario newUsusario = newDaoUsuario.buscar(nomeInp);

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            //String id = "thiago";

            if (newUsusario == null || !senhaInp.equals(newUsusario.getSenha())) {
                HttpSession session = request.getSession(true);
                session.setAttribute("msg", "Login ou senha inválidos.");
                response.sendRedirect("index.jsp");
            } else {

                request.getSession(true).setAttribute("logged",nomeInp);

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet menu</title>");

                out.println("<style>");
                out.println("body {"
                        + "display: flex;"
                        + "justify-content: center;"
                        + "align-items: center;"
                        + "}");
                out.println("div.container {"
                        + "text-align: center;"
                        + "}");
                out.println("a {"
                        + "text-decoration: none;"
                        + "padding: 5px;"
                        + "}");
                out.println("form {"
                        + "margin-top: 15px;"
                        + "}");

                out.println("</style>");

                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<h1>Menu</h1>");

                out.println("<a href=\"xxx.html\">ERRO HTML</a>");
                out.println("<a href=\"errojava\">ERRO JAVA</a>");

                out.print(
                        "<form action='welcome' method='get'>"
                        + "<input type='submit' value='Welcome'>"
                        + "</form>");

                out.println("</br>");

                out.print(
                        "<form action='sair' method='get'>"
                        + "<input type='submit' value='Sair'>"
                        + "</form>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("logged") == null) {
            // O usuário não está logado
            session = request.getSession();
            session.setAttribute("msg", "Sua sessão expirou ou você não está autenticado.");
            response.sendRedirect("index.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
