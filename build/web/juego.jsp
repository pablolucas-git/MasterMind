<%-- 
    Document   : juego
    Created on : 3 nov 2022, 12:03:18
    Author     : dawmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .tablero{
                margin: auto;
                background: pink;
                width: fit-content;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                padding: 0 10px;
            }
            .fila{
                display: flex;
                margin: auto;
                margin: 10px 0px;
                gap: 10px;
            }
            .respuesta{
                display: flex;
                width: 300px;
                justify-content: space-between;
                align-items: center;
            }
            .solucion{
                width: 200px;
                display:flex;
                justify-content: space-between;
                align-items: center;
            }
            .circulo, .cuadrado{
                width: 30px;
                height: 30px;
            }
            .circulo{
                border-radius: 100%;
                background: red;
                width: 40px;
                height: 40px;
            }
            .cuadrado{
                background: blue;
            }
        </style>
    </head>
    <body>
        <div class="tablero">
            <%
                for (int i = 0; i < 15; i++) {

                    out.println("<div class=\"fila\">");
                    out.println("<div class=\"respuesta\">");
                    for (int j = 0; j < 4; j++) {
                        out.println("<div class=\"circulo\"></div>");
                    }

                    out.println("</div>");

                    out.println("<div class=\"solucion\">");
                    for (int j = 0; j < 4; j++) {
                        out.println("<div class=\"cuadrado\"></div>");
                    }
                    out.println("</div>");

                    out.println("</div>");
                    
                }
            %>
        </div>
    </body>
</html>
