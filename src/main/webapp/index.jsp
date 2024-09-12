
<%@page import="utils.mascaras"%>
<%@page import="classReserva.datosReseva"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Coworking</title>

    </head>
    <body>
        <!-- formulario para manejar las reservas -->
        <div class="contenedor">
            <h1> Gestión de Reservas para un Coworking</h1>
            <form action="ReservaServlet" method="POST">
                <p><label>Nombres Y Apellidos: </label><br><input type="text" name="names"></p>
                <p><label>Fecha de reserva: </label><br><input type="date" name="date"></p>
                <p><label>Escoga su espacio edecuado:</label><br><select name="space" > 
                        <option value="escritorio">Escritorio</option>
                        <option value="saladereuniones">Sala de reuniones</option>
                        <option value="oficinaprivada">Oficina privada</option>
                    </select></p>
                <p><label>Escoga una duración: </label><br><select name="duration" > 
                        <option value="0.5">30 minutos</option>
                        <option value="1.0">1 hora</option>
                        <option value="1.5">1:30 minutos</option>
                        <option value="2.0">2 horas</option>

                    </select></p>
                <p><button class="buttonverde" type="submit">Enviar</button></p>
                <h3>
                    <%= request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
                </h3>
            </form>
        </div>

        <!-- campo donde se muestran los valores ingresados  -->
        <div class="contenedor">
            <h1 > Usted acaba de ingresar los siguientes datos</h1>
            <!-- Tomamos los atributos modificados en el servelt para ponerlos en el jsp   -->
            <p><label>El nombre ingresado es : </label> <%= request.getAttribute("viewName") != null ? request.getAttribute("viewName") : ""%></p>
            <p><label>El fecha  ingresada es : </label><%= request.getAttribute("viewDate") != null ? request.getAttribute("viewDate") : ""%></p>
            <p><label>El duracion es : </label><%= request.getAttribute("viewDuration") != null ? request.getAttribute("viewDuration") : ""%></p>
            <p><label>El espacio es  ingresado es : </label><%= request.getAttribute("viewspace") != null ? request.getAttribute("viewspace") : ""%></p>
        </div>
        <!-- Contenedor para mostrar los datos de las reservas agendadas en el sistema   -->
        <div class="contenedor">
            <h1>Mostrar lista de reservas</h1>
            <%
                List<datosReseva> listReseva = (List<datosReseva>) request.getAttribute("listReseva");
                if (listReseva != null && !listReseva.isEmpty()) {
            %>
            <h2>Reservas disponibles</h2>
            <!-- instaciamos a la clase mascasra , es una clase donde nos ayuda a poner los valores de spacio y duracion de forma presentable para el usuario  -->
            <%
                mascaras mascara = new mascaras();
                for (datosReseva reserva : listReseva) {
                    String finalmascaraSpace = mascara.mascaraSpace(reserva.getSpace());
                    String finalmascaraDuration = mascara.mascaraDuration(reserva.getDuration());
            %>

            <!-- Formulario independiente para eliminar la reserva -->
            <form action="EliminarReservaServlet" method="POST">
                <div class="contenedorDelete">
                    <p>Nombre: <%= reserva.getNames()%></p>
                    <p>Fecha: <%= reserva.getDate()%></p>
                    <p>Duración: <%= finalmascaraDuration%></p>
                    <p>Espacio: <%= finalmascaraSpace%></p>

                </div>
                <div >
                    <p><button type="submit" class="buttonDelete">Eliminar</button></p>
                    <input type="hidden" name="id_eliminar" value="<%= reserva.getId()%>">
                    <hr>
                </div>
            </form>
            <%
                }
            } else {
            %>
            <h3>No hay reservas disponibles.</h3>
            <%
                }
            %>
            <!-- Formulario para mostrar la lista de reservas -->
            <form action="ListaReservasServlet" method="POST">
                <p><button type="submit" name="action" value="showList">Mostrar Lista</button></p>
            </form>
        </div>
    </body>
</html>
