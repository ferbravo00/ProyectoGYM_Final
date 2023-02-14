/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import dominio.Ejercicio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import negocio.GestionEjer;
import negocio.GestionUsu;
import negocio.InterfazGestionEjer;

/**
 *
 * @author Fer
 */
@WebServlet(name = "EjercicioServlet", urlPatterns = {"/ejercicios"})
public class EjercicioServlet extends HttpServlet {

    @Inject
    private InterfazGestionEjer gestionEjer; // Cremos una instancia de nuestra if local
    


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
        
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            //1. Leer los parametros de nuestro request
            String accion = request.getParameter("accion");
            if (accion != null){
                switch (accion){
                    case "editar":
                        //this.editarCliente(request, response);
                        break;
                    case "eliminar":
                        this.eliminarEjer(request, response);
                        break;
                    default:
                        this.listaEjercicios(request, response);
                }
            } else {
                this.listaEjercicios(request, response);
            }
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String accion = request.getParameter("accion");
            if (accion != null){
                switch (accion){
                    case "insertar":
                        this.insertarEjer(request, response);
                        break;
                    
                    default:
                        this.listaEjercicios(request, response);
                }
            } else {
                this.listaEjercicios(request, response);
            }
    }

    
    private void listaEjercicios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request

        List<Ejercicio> ejercicios = gestionEjer.listarEjercicios();
        System.out.println("ejercicios: " + ejercicios);
        
        // Ponemos usuarios en un alcance
        request.setAttribute("ejercicios", ejercicios);
       
        
        // Redirigimos al JSP
        request.getRequestDispatcher("/ejercicios.jsp").forward(request, response);
    }
    
    
    private void insertarEjer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request

        String nombre = request.getParameter("nombre");
        String parte = request.getParameter("parte");
        String descri = request.getParameter("descri");
        String foto = request.getParameter("foto");
       
        //2. Creamos nuestro objeto Cliente
        Ejercicio ejercicio = new Ejercicio(nombre,foto, descri, parte);
        //3. Invocamos al método de acceso a datos que inserta un cliente
        gestionEjer.registrarEjercicio(ejercicio);
        //System.out.println("registrosModificados = " + registrosModificados);
        //4. Redirigimos a la acción por defecto
        //this.accionDefault(request, response);
        this.listaEjercicios(request, response);
    }
    
    private void eliminarEjer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request

        String id =request.getParameter("idEjercicio");

        //2. Creamos nuestro objeto Cliente
        Ejercicio ejercicio = new Ejercicio(Integer.parseInt(id));
        //3. Invocamos al método de acceso a datos que inserta un cliente
        gestionEjer.eliminarEjercicio(ejercicio);
        //System.out.println("registrosModificados = " + registrosModificados);
        //4. Redirigimos a la acción por defecto
        //this.accionDefault(request, response);
        this.listaEjercicios(request, response);
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

}
