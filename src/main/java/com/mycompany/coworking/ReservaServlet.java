/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import utils.mascaras;

@WebServlet(name = "ReservaServlet", urlPatterns = {"/ReservaServlet"})
public class ReservaServlet extends HttpServlet {

    List<datosReseva> listReseva = new ArrayList<>();
    boolean view = false;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Guardamos los valores del jsp en variables 

        String names = request.getParameter("names");
        String date = request.getParameter("date");
        String space = request.getParameter("space");
        String duration = request.getParameter("duration");
        request.setAttribute("esVisible", view);
        Integer counter = (Integer) session.getAttribute("counter");

        if (counter == null) {
            counter = 1; // Si es la primera vez, inicializamos el contador
        } else {
            counter++; // Si ya existe, lo incrementamos
        }

        session.setAttribute("counter", counter);

        // validamos que nongun  data venga vacio 
        if (names.isEmpty() || date.isEmpty() || duration.isEmpty() || space.isBlank()) {
            // si algun dato viene vacio nosotros mostramos un mensaje que diga que ingrese todos los valores 
            request.setAttribute("error", "Ingrese todos los valores");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {

            // instacia de clase y setteo de valores 
            datosReseva datReserva = new datosReseva();
            datReserva.setId(counter);
            datReserva.setNames(names);
            datReserva.setDuration(duration);
            datReserva.setDate(date);
            datReserva.setSpace(space);

            // se añaden a lista los datos ingresados 
            listReseva.add(datReserva);
            // guardamos datos en session 

            session.setAttribute("listReseva", listReseva);

            // Al momento de tener una lista desplegable en el index.jsp le asignamos un valor pero al usuairo se le mostrara de difente manera 
            mascaras mascara = new mascaras();
            String finalmascaraSpace = mascara.mascaraSpace(space);
            String finalmascaraDuration = mascara.mascaraDuration(duration);

            // Setteamos los valor para que en el jsp se pueda ver de la manera correcta 
            request.setAttribute("viewName", names);
            request.setAttribute("viewDate", date);
            request.setAttribute("viewDuration", finalmascaraDuration);
            request.setAttribute("viewspace", finalmascaraSpace);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
