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
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estadísticas</title>
        <style>
            
            body {
                background-color:#cdbcdf;
            }
            
            * {
               
                font-family: 'Press Start 2P', cursive;
            }
            
            div{
                width: fit-content;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
            }
            h1 {
                
                padding-bottom: 20px;
                text-align: center;
                font-size: 20px;
            }
            table {
                background-color:#faf5ff;
                border-collapse: collapse;
            }
            
             th, td {
                text-align: center;
                border: 2px solid black;
                border-style:dashed;
                padding: 30px;
                
            }
            
            th {
                
                border-radius: 3px;
                background-color:#652299;
                color:white;
            }
            
            tr:nth-child(2n){
                background:#cc99ff;
            }
        </style>
    </head>
    <body>
        <div>
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
        </div>
    </body>
</html>
