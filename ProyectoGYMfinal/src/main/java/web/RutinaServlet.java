/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import dominio.Ejercicio;
import dominio.Rutina;
import dominio.Rutinaejercicio;
import dominio.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import negocio.InterfazGestionEjer;
import negocio.InterfazGestionRutina;

/**
 *
 * @author Fer
 */
@WebServlet(name = "RutinaServlet", urlPatterns = {"/rutinas"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
    maxFileSize = 1024 * 1024 * 150,      // 10 MB
    maxRequestSize = 1024 * 1024 * 200    // 50 MB
)
public class RutinaServlet extends HttpServlet {

    @Inject
    private InterfazGestionRutina gestionRutina;
    @Inject
    private InterfazGestionEjer gestionEjer;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            //1. Leer los parametros de nuestro request
            String accion = request.getParameter("accion");
            if (accion != null){
                switch (accion){
                    case "editar":
                        //this.editarEjer(request, response);
                        break;
                    case "eliminar":
                        this.eliminar(request, response);
                        break;
                    case "listar":
                        this.listar(request, response);
                        break;
//                    case "listarEjer":
//                        this.listarEjer(request, response);
//                        break;
                    case "insertar":
                        this.insertar(request, response);
                        break;
                    default:
                        this.listar(request, response);
                }
            } else {
                this.listar(request, response);
            }
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
            if (accion != null){
                switch (accion){
                    
                    case "modificar":
//                        this.modificarEjer(request, response);
                        break;
                    case "buscar":
//                        this.buscarEjercicios(request, response);
                        break;
                    default:
                        this.listar(request, response);
                }
            } else {
                this.listar(request, response);
            }
    }
    
    
    private void listar(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id =request.getParameter("idRutina");
        Rutina ejercicio = new Rutina(Integer.parseInt(id));
        Rutina rutina = gestionRutina.encontrarRutinaPorID(ejercicio);
        session.setAttribute("rutina", rutina);
        List<Rutinaejercicio> amigos = rutina.getRutinaejercicioList();
        List<Ejercicio> ejercicios = new ArrayList<>();
        for (int i = 0; i < amigos.size(); i++) {
            ejercicios.add(amigos.get(i).getEjercicioidEjercicio());
        }
 
        for (Ejercicio ejer : ejercicios) { // recorremos la lista de amigos
            // obtenemos la imagen en formato byte[]
            if (ejer.getFoto() != null) {
                byte[] imagen = Base64.getEncoder().encode(ejer.getFoto());
                String foto = new String(imagen,"UTF-8"); // la convertimos a base64
                ejer.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Usuario
            }
        }
        
        request.setAttribute("rutina", ejercicios);
        request.getRequestDispatcher("/ejerciciosRutinas.jsp").forward(request, response);
    }
//    
//    private void listarEjer(HttpServletRequest request, HttpServletResponse response)
//           throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        Rutina usuario = (Rutina) session.getAttribute("rutina");
//        List<Ejercicio> usuarios = gestionEjer.listarEjercicios();
//        List<Ejercicio> noAmigos = new ArrayList<>();
//        
//        for (Ejercicio otroUsuario : usuarios) {
//            if (!usuario.getRutinaejercicioList().contains(otroUsuario)) {
//                noAmigos.add(otroUsuario);
//            }
//        }
//        
//        for (Ejercicio noAmigo : noAmigos) { // recorremos la lista de amigos
//            // obtenemos la imagen en formato byte[]
//            if (noAmigo.getFoto() != null) {
//                byte[] imagen = Base64.getEncoder().encode(noAmigo.getFoto());
//                String foto = new String(imagen,"UTF-8"); // la convertimos a base64
//                noAmigo.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Usuario
//            }
//        }
//        
//        request.setAttribute("rutina", noAmigos);
//        request.getRequestDispatcher("/listarEjercicios.jsp").forward(request, response);
//    }
    
    private void insertar(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        HttpSession session = request.getSession();
        Rutina rutina = (Rutina) session.getAttribute("rutina");
        
        String id =request.getParameter("idEjercicio");
        Ejercicio ejercicio = new Ejercicio(Integer.parseInt(id));
        Ejercicio ejer = gestionEjer.encontrarEjercicioPorID(ejercicio);
        
        Rutinaejercicio rutEjer = new Rutinaejercicio(ejer,rutina);
        rutina.getRutinaejercicioList().add(rutEjer);
        List<Rutinaejercicio> amigos = rutina.getRutinaejercicioList();
        rutina.setRutinaejercicioList(amigos);
        gestionRutina.modificarRutina(rutina);
        
        List<Ejercicio> ejercicios = new ArrayList<>();
        for (int i = 0; i < amigos.size(); i++) {
            ejercicios.add(amigos.get(i).getEjercicioidEjercicio());
        }
 
        for (Ejercicio eje : ejercicios) { // recorremos la lista de amigos
            // obtenemos la imagen en formato byte[]
            if (eje.getFoto() != null) {
                byte[] imagen = Base64.getEncoder().encode(eje.getFoto());
                String foto = new String(imagen,"UTF-8"); // la convertimos a base64
                eje.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Usuario
            }
        }
        
        request.setAttribute("rutina", ejercicios);
        request.getRequestDispatcher("/ejerciciosRutinas.jsp").forward(request, response);
    }
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request

        String id =request.getParameter("idRutina");

        //2. Creamos nuestro objeto Cliente
        Rutina ejercicio = new Rutina(Integer.parseInt(id));
        
        Rutina rutina = gestionRutina.encontrarRutinaPorID(ejercicio);
        
        gestionRutina.eliminarRutina(rutina);
        //System.out.println("registrosModificados = " + registrosModificados);
        //4. Redirigimos a la acción por defecto
        //this.accionDefault(request, response);
        this.listar(request, response);
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
