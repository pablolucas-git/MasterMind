<%-- 
    Document   : estadisticas
    Created on : 10 nov 2022, 12:17:18
    Author     : dawmi
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estadísticas</title>
    </head>
    <body>
        <h1>Estadísticas por jugador</h1>
        <table>
            <tr>
                <th>Jugador</th>
                <th>Puntuación</th>
                <th>Intentos</th>
            </tr>
            <%
                ArrayList<String[]> datos = (ArrayList<String[]>) session.getAttribute("estadisticas");
                for(int i = 0; i < datos.size(); i++){
                    out.println("<tr>");
                    for(int j = 0; j < datos.get(i).length; j++){
                        out.println("<td>" + datos.get(i)[j] + "</td>");
                }
                out.println("</tr>");
                }
                
            %>
        </table>
    </body>
</html>
