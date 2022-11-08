<%-- 
    Document   : juego
    Created on : 3 nov 2022, 12:03:18
    Author     : dawmi
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="mastermindpackage.Juego"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            *{

                border-collapse: collapse;
            }
            #tablero{
                background-color: rgb(239, 216, 142);
                margin: auto;
                border: 1px solid black;

                width: 300px;
            }
            .circulo{
                border-radius: 100%;
                background: radial-gradient(  rgb(111, 74, 29) 30%, rgb(0, 0, 0));
            }
            .respuesta .circulo{
                height: 30px;
                width: 30px;
            }
            .resultado .circulo{
                height: 20px;
                width: 20px;
            }
            .row, .respuesta,.resultado{
                display: flex;
            }
            .row{
                border-bottom: 1px solid black;
            }
            .row:last-child{
                border-bottom: none;
            }
            .row{
                justify-content: space-between;
                align-items: center;
            }
            .respuesta, .resultado{
                justify-content: space-around;
                gap: 10px;
                padding: 10px 10px;
            }
            .respuesta{
                border-right: 1px solid black;
                width: 900px;
            }
            .resultado{
                width: 100%;
                height: 100%;
            }
            input[type="radio"]{
                display: none;
            }
            input[type="radio"] + label{
                width: 20px;
                height: 20px;
                display: block;
                border-radius: 100%;
                box-sizing: border-box;
            }
            input[type="radio"]:checked + label{
                border: 2px solid rgb(68, 40, 12);
            }
            .color1, .color2, .color3, .color4{
                display: flex;
                gap: 5px;
                align-items: center;
                margin-top: 5px;
                padding: 5px 0;
            }
            form{
                display: block;
                width: fit-content;
                background-color: rgb(234, 215, 155);
                font-family: sans-serif;
                position: absolute;
                top: 20px;
                padding: 10px;

            }
            form div:nth-child(-n +3){
                border-bottom: 1px solid black;
            }
            form span{
                margin-right: 20px;
            }
            .rojo{
                background: radial-gradient( red 1%, rgba(125,15,15,1));
            }
            .verde{
                background: radial-gradient( green 1%, rgb(10, 82, 11));
            }
            .azul{
                background: radial-gradient( blue 1%, rgb(1, 1, 60));
            }
            .rosa{
                background: radial-gradient( pink 1%, rgb(181, 104, 117));
            }
            .amarillo{
                background: radial-gradient( yellow 1%, rgb(194, 194, 8));
            }
            .naranja{
                background: radial-gradient( orange 1%, rgb(182, 120, 5));
            }
            .negro{
                background: radial-gradient( rgb(46, 46, 46) 1%, rgb(0, 0, 0));
            }
            .blanco{
                background: radial-gradient( rgb(255, 255, 255) 1%, rgb(189, 188, 187));
            }
            
        </style>
    </head>
    <body>
        
        <div id="tablero">
            <%
                Juego juego = (Juego) session.getAttribute("juego");
                String[] solucion = juego.solucion.combinacion;
                int ronda = juego.ronda;
                ArrayList<ArrayList<String[]>> rondas = juego.rondas;
                int i = 0;
                for (; i < ronda; i++) {
                    out.println("<div class=\"row\">");
                    String[] respuesta = rondas.get(i).get(0);
                    String[] resultado = rondas.get(i).get(1);

                    out.println("<div class=\"respuesta\">");
                    for (int j = 0; j < 4; j++) {
                        out.println("<div class=\"circulo " + respuesta[j] + "\"></div>");
                    }
                    out.println("</div>");
                    out.println("<div class=\"resultado\">");
                    for (int j = 0; j < 4; j++) {
                        out.println("<div class=\"circulo " + resultado[j] + "\"></div>");
                    }
                    out.println("</div>");
                     out.println("</div>");
                }
                for (; i < 15; i++) {
                    out.println("<div class=\"row\">");
                    if(i == ronda - 1){
                    out.println("<div class=\"respuesta actual\">");
                }
                    out.println("<div class=\"respuesta\">");
                    for (int j = 0; j < 4; j++) {
                        out.println("<div class=\"circulo\"></div>");
                    }
                    out.println("</div>");
                    out.println("<div class=\"resultado\">");
                    for (int j = 0; j < 4; j++) {
                        out.println("<div class=\"circulo\"></div>");
                    }
                    out.println("</div>");
                     out.println("</div>");
                }
            %>
        </div>
            <% out.println("<p>");
            
            for(i = 0; i < solucion.length; i++){
            out.println(solucion[i] + " ");
                }
                out.println("</p>");
            
            
            %>
        <form action="MastermindServlet?opcion=3" method="post">
            <div class="color1">
                <span>Color 1:</span>
                <input type="radio" name="color1" value="rojo" id="color1rojo" > 
                <label for="color1rojo" class="rojo"></label>
                <input type="radio" name="color1" value="verde" id="color1verde" > 
                <label for="color1verde" class="verde"></label>
                <input type="radio" name="color1" value="azul" id="color1azul" > 
                <label for="color1azul" class="azul"></label>
                <input type="radio" name="color1" value="rosa" id="color1rosa" > 
                <label for="color1rosa" class="rosa"></label>
                <input type="radio" name="color1" value="amarillo" id="color1amarillo" > 
                <label for="color1amarillo" class="amarillo"></label>
                <input type="radio" name="color1" value="naranja" id="color1naranja" > 
                <label for="color1naranja" class="naranja"></label>
            </div>

            <div class="color2">
                <span>Color 2:</span>
                <input type="radio" name="color2" value="rojo" id="color2rojo" > 
                <label for="color2rojo" class="rojo"></label>
                <input type="radio" name="color2" value="verde" id="color2verde" > 
                <label for="color2verde" class="verde"></label>
                <input type="radio" name="color2" value="azul" id="color2azul" > 
                <label for="color2azul" class="azul"></label>
                <input type="radio" name="color2" value="rosa" id="color2rosa" > 
                <label for="color2rosa" class="rosa"></label>
                <input type="radio" name="color2" value="amarillo" id="color2amarillo" > 
                <label for="color2amarillo" class="amarillo"></label>
                <input type="radio" name="color2" value="naranja" id="color2naranja" > 
                <label for="color2naranja" class="naranja"></label>
            </div>

            <div class="color3">
                <span>Color 3:</span>
                <input type="radio" name="color3" value="rojo" id="color3rojo" > 
                <label for="color3rojo" class="rojo"></label>
                <input type="radio" name="color3" value="verde" id="color3verde" > 
                <label for="color3verde" class="verde"></label>
                <input type="radio" name="color3" value="azul" id="color3azul" > 
                <label for="color3azul" class="azul"></label>
                <input type="radio" name="color3" value="rosa" id="color3rosa" > 
                <label for="color3rosa" class="rosa"></label>
                <input type="radio" name="color3" value="amarillo" id="color3amarillo" > 
                <label for="color3amarillo" class="amarillo"></label>
                <input type="radio" name="color3" value="naranja" id="color3naranja" > 
                <label for="color3naranja" class="naranja"></label>
            </div>

            <div class="color4">
                <span>Color 4:</span>
                <input type="radio" name="color4" value="rojo" id="color4rojo" > 
                <label for="color4rojo" class="rojo"></label>
                <input type="radio" name="color4" value="verde" id="color4verde" > 
                <label for="color4verde" class="verde"></label>
                <input type="radio" name="color4" value="azul" id="color4azul" > 
                <label for="color4azul" class="azul"></label>
                <input type="radio" name="color4" value="rosa" id="color4rosa" > 
                <label for="color4rosa" class="rosa"></label>
                <input type="radio" name="color4" value="amarillo" id="color4amarillo" > 
                <label for="color4amarillo" class="amarillo"></label>
                <input type="radio" name="color4" value="naranja" id="color4naranja" > 
                <label for="color4naranja" class="naranja"></label>
            </div>
            <input type="submit" value="Jugar" style="display: block;  margin: auto;margin-top: 10px;">

        </form>
    </body>
</html>
