/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import dominio.Ejercicio;
import dominio.Rutina;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import negocio.GestionEjer;
import negocio.GestionUsu;
import negocio.InterfazGestionEjer;

/**
 *
 * @author Fer
 */
@WebServlet(name = "EjercicioServlet", urlPatterns = {"/ejercicios"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
    maxFileSize = 1024 * 1024 * 150,      // 10 MB
    maxRequestSize = 1024 * 1024 * 200    // 50 MB
)
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
                        this.editarEjer(request, response);
                        break;
                    case "eliminar":
                        this.eliminarEjer(request, response);
                        break;
                    case "listarEjerRut":
                        this.listarEjer(request, response);
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
                    case "modificar":
                        this.modificarEjer(request, response);
                        break;
                    case "buscar":
                        this.buscarEjercicios(request, response);
                        break;
                    default:
                        this.listaEjercicios(request, response);
                }
            } else {
                this.listaEjercicios(request, response);
            }
    }

    
//    private void listaEjercicios(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        //1. Recuperamos los parámetros del request
//        List<Ejercicio> ejercicios = gestionEjer.listarEjercicios();
//        
//        System.out.println("ejercicios: " + ejercicios);
//        
//        // Ponemos usuarios en un alcance
//        request.setAttribute("ejercicios", ejercicios);
//       
//        
//        // Redirigimos al JSP
//        request.getRequestDispatcher("/ejercicios.jsp").forward(request, response);
//    }
    
    
    
    private void listaEjercicios(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        
        List<Ejercicio> ejercicios = gestionEjer.listarEjercicios(); // recuperamos la lista de ejercicios
        for (Ejercicio ejer : ejercicios) { // recorremos la lista de ejercicios
            byte[] imagen = Base64.getEncoder().encode(ejer.getFoto()); // obtenemos la imagen en formato byte[]
            if (imagen != null) { // si la imagen existe
                String foto = new String(imagen,"UTF-8"); // la convertimos a base64
                ejer.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Ejercicio
                System.out.println(ejer.getFotobase64());
            }
        }
        request.setAttribute("ejercicios", ejercicios); // retornamos la lista de ejercicios con sus imágenes en base64
        request.getRequestDispatcher("/ejercicios.jsp").forward(request, response);
    }
    
    
    private void buscarEjercicios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String texto =request.getParameter("texto");

//        Ejercicio ejercicio = new Ejercicio(texto,texto);
        
        
        List<Ejercicio> ejercicios = gestionEjer.buscarEjer(texto);
          
        for (Ejercicio ejer : ejercicios) { // recorremos la lista de ejercicios
            byte[] imagen = Base64.getEncoder().encode(ejer.getFoto()); // obtenemos la imagen en formato byte[]
            if (imagen != null) { // si la imagen existe
                String foto = new String(imagen,"UTF-8"); // la convertimos a base64
                ejer.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Ejercicio
                System.out.println(ejer.getFotobase64());
            }
        }
        
        if(ejercicios.isEmpty()){
            ejercicios = gestionEjer.listarEjercicios();
        }
        System.out.println("ejercicios: " + ejercicios);
        
        // Ponemos usuarios en un alcance
        request.setAttribute("ejercicios", ejercicios);
       
        
        // Redirigimos al JSP
        request.getRequestDispatcher("/ejercicios.jsp").forward(request, response);
    }
    
    
//    private void buscarEjercicios(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        //1. Recuperamos los parámetros del request
//        String texto =request.getParameter("texto");
//
//        //2. Creamos nuestro objeto Cliente
//        Ejercicio ejercicio = new Ejercicio(texto);
//        
//        List<Ejercicio> ejercicios = gestionEjer.buscarEjercicios(ejercicio);
//        System.out.println("ejercicios: " + ejercicios);
//        
//        // Ponemos usuarios en un alcance
//        request.setAttribute("ejercicios", ejercicios);
//       
//        
//        // Redirigimos al JSP
//        request.getRequestDispatcher("/ejercicios.jsp").forward(request, response);
//    }
    
    
    private void listarEjer(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        HttpSession session = request.getSession();
        Rutina usuario = (Rutina) session.getAttribute("rutina");
        List<Ejercicio> usuarios = gestionEjer.listarEjercicios();
        List<Ejercicio> noAmigos = new ArrayList<>();
        
        for (Ejercicio otroUsuario : usuarios) {
            if (!usuario.getRutinaejercicioList().contains(otroUsuario)) {
                noAmigos.add(otroUsuario);
            }
        }
        
        for (Ejercicio noAmigo : noAmigos) { // recorremos la lista de amigos
            // obtenemos la imagen en formato byte[]
            if (noAmigo.getFoto() != null) {
                byte[] imagen = Base64.getEncoder().encode(noAmigo.getFoto());
                String foto = new String(imagen,"UTF-8"); // la convertimos a base64
                noAmigo.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Usuario
            }
        }
        
        request.setAttribute("rutina", noAmigos);
        request.getRequestDispatcher("/listarEjercicios.jsp").forward(request, response);
    }
    
    private void insertarEjer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request

        String nombre = request.getParameter("nombre");
        String parte = request.getParameter("parte");
        String descri = request.getParameter("descri");
        Part filePart = request.getPart("foto"); // Recuperar el archivo de entrada
        byte[] foto = null;
        if (filePart != null) {
            InputStream fileContent = filePart.getInputStream(); // Obtener la entrada de archivo
            foto = leerBytesDeInputStream(fileContent); // Leer el contenido del archivo en un array de bytes usando el nuevo método
        }

        //2. Creamos nuestro objeto Ejercicio
        Ejercicio ejercicio = new Ejercicio(nombre, foto, descri, parte);

        //3. Invocamos al método de acceso a datos que inserta un ejercicio
        gestionEjer.registrarEjercicio(ejercicio);

        //4. Redirigimos a la acción por defecto
        this.listaEjercicios(request, response);
    }

    private byte[] leerBytesDeInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();
    }

    
    
    private void modificarEjer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request
        //1. Recuperamos los parámetros del request
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String parte = request.getParameter("parte");
        String descri = request.getParameter("descri");
        Part filePart = request.getPart("imagen"); // Recuperar el archivo de entrada
        byte[] foto = null;
        if (filePart != null) {
            InputStream fileContent = filePart.getInputStream(); // Obtener la entrada de archivo
            foto = leerBytesDeInputStream(fileContent); // Leer el contenido del archivo en un array de bytes usando el nuevo método
        }

        //2. Creamos nuestro objeto Ejercicio
        Ejercicio ejercicio = new Ejercicio(Integer.parseInt(id), nombre, foto, descri, parte);

        //3. Invocamos al método de acceso a datos que inserta un cliente
        gestionEjer.modificarEjercicio(ejercicio);

        //4. Redirigimos a la acción por defecto
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
    
    
    private void editarEjer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request

        String id =request.getParameter("idEjercicio");

        //2. Creamos nuestro objeto Cliente
        Ejercicio ejercicio = new Ejercicio(Integer.parseInt(id));
        //3. Invocamos al método de acceso a datos que inserta un cliente
        Ejercicio ejercicios = gestionEjer.encontrarEjercicioPorID(ejercicio);
        
        byte[] imagen = Base64.getEncoder().encode(ejercicios.getFoto()); // obtenemos la imagen en formato byte[]
        if (imagen != null) { // si la imagen existe
            String foto = new String(imagen,"UTF-8"); // la convertimos a base64
            ejercicios.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Ejercicio
            System.out.println(ejercicios.getFotobase64());
        }
        
        //System.out.println("registrosModificados = " + registrosModificados);
        //4. Redirigimos a la acción por defecto
        //this.accionDefault(request, response);
        request.setAttribute("ejercicios", ejercicios);
       
        
        // Redirigimos al JSP
        request.getRequestDispatcher("/verEjer.jsp").forward(request, response);
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
