/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rodol
 */
public class calcula_edad extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet calcula_edad</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Edad calculada </h1>" 
                    + "<p>"
                    + "En: " + request.getContextPath() 
                    + "</p>");
            out.println("<p>");
            if (request.getAttribute("edad") !=null){
                out.println("Edad = " + request.getAttribute("edad"));
            }
            out.println("</p>");
            out.println("<p>" + this.getServletInfo() + "</p>");
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
        String strfecha = request.getParameter("fechanacimiento");
        
        if(strfecha==null){
            processRequest(request, response);
        }
        
        Date fechaNac = null;
        try{
            fechaNac = new SimpleDateFormat("dd/MM/yyyy").parse(strfecha);            
        } catch (ParseException ex){
            Logger.getLogger(calcula_edad.class.getName()).log(Level.SEVERE , null, ex);
        }
        
        if(fechaNac!=null){
            Calendar fechaNacimiento = Calendar.getInstance();
            Calendar fechaActual = Calendar.getInstance();
            fechaNacimiento.setTime(fechaNac);
            
            int edad = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
            
            request.setAttribute("edad", edad);
        }
        
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
        return "Servlet que calcula la edad, pasando como parametro la fecha de nacimiento (fechanacimiento=dd/MM/yyyy)";
    }// </editor-fold>

}
