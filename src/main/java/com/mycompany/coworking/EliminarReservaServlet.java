package com.mycompany.coworking;

import classReserva.datosReseva;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EliminarReservaServlet", urlPatterns = {"/EliminarReservaServlet"})
public class EliminarReservaServlet extends HttpServlet {

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
        
        System.out.println("doPost en EliminarReservaServlet fue llamado.");
        // instacia de la session 
        HttpSession session = request.getSession();
        // declaracion de variable int oara tomar el id de la reserva 
        int id_eliminar = Integer.parseInt(request.getParameter("id_eliminar"));
        System.out.println("id_eliminar: " + id_eliminar);

        // Obtener la lista de reservas desde la sesión
        List<datosReseva> listaReserva = (List<datosReseva>) session.getAttribute("listReseva");

        if (listaReserva != null) {
            datosReseva reservaEliminar = null;

            // Buscar la reserva con el ID especificado
            for (datosReseva reserva : listaReserva) {
                System.out.println("Recorriendo reservas...");
                if (reserva.getId() == id_eliminar) {
                    reservaEliminar = reserva;
                    break;
                }
            }

            // Eliminar la reserva si se encuentra
            if (reservaEliminar != null) {
                listaReserva.remove(reservaEliminar);
                session.setAttribute("listReseva", listaReserva);
                System.out.println("Reserva eliminada.");
            } else {
                System.out.println("Reserva no encontrada.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            System.out.println("La lista de reservas es nula.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        // Redirigir al  jsp 
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
