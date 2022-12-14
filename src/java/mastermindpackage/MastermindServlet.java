/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mastermindpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dawmi
 */
public class MastermindServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int opcion = Integer.parseInt(request.getParameter("opcion"));

        switch (opcion) {
            case 1: //Empezar juego
                String nombre = request.getParameter("nombre");
                if (nombre == null) {
                    nombre = "Anónimo";
                }
                empezarJuego(nombre, request, response);
                break;
            case 2: //mostrar estadisticas
                DatabaseConnection con = new DatabaseConnection();
                ArrayList<String[]> jugadores = (ArrayList<String[]>) con.obtenerJugador();
                HttpSession session = request.getSession();
                session.setAttribute("estadisticas", jugadores);
                RequestDispatcher rd = request.getRequestDispatcher("/estadisticas.jsp");
                rd.forward(request, response);
                break;
            case 3:
                continuarJuego(request, response); //continuar el juego
        }

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MastermindServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MastermindServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MastermindServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MastermindServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void empezarJuego(String nombre, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); //se obtiene la sesión
        session.invalidate(); //se borra si existia una
        session = request.getSession(true); //se crea una nueva sesion
        Juego juego;
        juego = new Juego(nombre); //creo el objeto juego
        session.setAttribute("juego", juego); //lo guardo en la sesión
        RequestDispatcher rd = request.getRequestDispatcher("/juego.jsp"); 
        rd.forward(request, response);
    }

    private void continuarJuego(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();

        Juego juego = (Juego) session.getAttribute("juego"); // se obtiene el objeto de la sesión

        String color1 = request.getParameter("color1");
        String color2 = request.getParameter("color2");
        String color3 = request.getParameter("color3");
        String color4 = request.getParameter("color4");

        Combinacion respuesta = new Combinacion(color1, color2, color3, color4); //se crea un objeto combinacion con la respuesta dada por el usuario
        juego.checkRespuesta(respuesta); //se comprueba la respuesta
        session.setAttribute("juego", juego); //se guarda el objeto en la sesión
        if(juego.acertado || juego.ronda > juego.numrondas){ //cuando se acaba el juego, se guarda automaticamente en la base de datos
            DatabaseConnection con = new DatabaseConnection();
            con.guardarJugador(juego.jugador, juego.ronda, juego.puntos);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/juego.jsp");
        rd.forward(request, response);
    }

  

}
