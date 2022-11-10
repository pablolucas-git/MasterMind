/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mastermindpackage;

import java.io.IOException;
import java.io.PrintWriter;
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
            throws ServletException, IOException {

        int opcion = Integer.parseInt(request.getParameter("opcion"));

        switch (opcion) {
            case 1: //Empezar juego
                String nombre = request.getParameter("nombre");
                if (nombre == null) {
                    nombre = "Anónimo";
                }
                empezarJuego(nombre, request, response);
                break;
            case 2:
                //mostrarEstadisticas();
                break;
            case 3:
                continuarJuego(request, response);
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
        processRequest(request, response);
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

    private void empezarJuego(String nombre, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        session = request.getSession(true);
        Juego juego;
        juego = new Juego(nombre);
        session.setAttribute("juego", juego);
        RequestDispatcher rd = request.getRequestDispatcher("/juego.jsp");
        rd.forward(request, response);
    }

    private void continuarJuego(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Juego juego = (Juego) session.getAttribute("juego");

        String color1 = request.getParameter("color1");
        String color2 = request.getParameter("color2");
        String color3 = request.getParameter("color3");
        String color4 = request.getParameter("color4");

        Combinacion respuesta = new Combinacion(color1, color2, color3, color4);
        juego.checkRespuesta(respuesta);
        session.setAttribute("juego", juego);
        RequestDispatcher rd = request.getRequestDispatcher("/juego.jsp");
        rd.forward(request, response);
    }

}
