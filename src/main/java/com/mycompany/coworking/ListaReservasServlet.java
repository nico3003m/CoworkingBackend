package com.mycompany.coworking;

import classReserva.datosReseva;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ListaReservasServlet", urlPatterns = {"/ListaReservasServlet"})
public class ListaReservasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // instacia de session 
        HttpSession session = request.getSession();
        System.out.println("Entro a cargar las listas en pantalla");

        // Obtener la lista de reservas desde la sesión
        List<datosReseva> listReseva = (List<datosReseva>) session.getAttribute("listReseva");
        if (listReseva == null) {
            listReseva = new ArrayList<>();
        }

        // Pasar la lista al JSP
        request.setAttribute("listReseva", listReseva);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
