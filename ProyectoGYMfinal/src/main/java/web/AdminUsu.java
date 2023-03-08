/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import dominio.Usuario;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import javax.servlet.http.Part;
import negocio.InterfazGestionUsu;

/**
 *
 * @author Fer
 */
@WebServlet(name = "AdminUsu", urlPatterns = {"/AdminUsu"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
    maxFileSize = 1024 * 1024 * 150,      // 10 MB
    maxRequestSize = 1024 * 1024 * 200    // 50 MB
)
public class AdminUsu extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Inject
    private InterfazGestionUsu gestionUsu; // Cremos una instancia de nuestra if local
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            //1. Leer los parametros de nuestro request
            String accion = request.getParameter("accion");
            if (accion != null){
                switch (accion){
                    case "editar":
                        this.editarUser(request, response);
                        break;
                    case "eliminar":
                        this.eliminar(request, response);
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
            throws IOException, ServletException {
        
        // 1. Leemos los parametros de nuestro request
        String accion = request.getParameter("accion");
        if (accion != null){
            switch (accion){ 
                case "modificar":
                    this.modificar(request, response);
                    break;
                default: 
                    this.listar(request, response);
            }
        } else {
            //this.accionDefault(request, response);
        }
    }
    
    
    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request

        System.out.println("inicia el usuario servlet");
        List<Usuario> usuarios = gestionUsu.listarUsuarios();
        System.out.println("usuarios: " + usuarios);
//        
//        for(Usuario usu: usuarios){
//            if(usu.getCorreo().equalsIgnoreCase("fer@gmail.com"))usuarios.remove(usu);
//        }
        
        for (Usuario ejer : usuarios) { // recorremos la lista de ejercicios
             // obtenemos la imagen en formato byte[]
            if (ejer.getFoto() != null) {
                byte[] imagen = Base64.getEncoder().encode(ejer.getFoto());
                String foto = new String(imagen,"UTF-8"); // la convertimos a base64
                ejer.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Ejercicio
                System.out.println(ejer.getFotobase64());
            }
        }
        
        // Ponemos usuarios en un alcance
        request.setAttribute("usuarios", usuarios);
        
        // Redirigimos al JSP
        request.getRequestDispatcher("/listadoUsuarios.jsp").forward(request, response);
    }
    
    
    private void editarUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request

        String id =request.getParameter("idUsuario");

        Usuario ejercicio = new Usuario(Integer.parseInt(id));
        Usuario usu = gestionUsu.encontrarUsuarioPorID(ejercicio);
        
        request.setAttribute("usuarios", usu);
  
        // Redirigimos al JSP
        request.getRequestDispatcher("/editarUsu.jsp").forward(request, response);
    }
    
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id =request.getParameter("idUsuario");

        Usuario ejercicio = new Usuario(Integer.parseInt(id));
        
        gestionUsu.eliminarUsuario(ejercicio);
        
        this.listar(request, response);
    }
    
    private void modificar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request
        //1. Recuperamos los parámetros del request
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        String edad = request.getParameter("edad");
        String altura = request.getParameter("altura");
        String peso = request.getParameter("peso");
        System.out.println("hola");
        Part filePart = request.getPart("imagen"); // Recuperar el archivo de entrada
        byte[] foto = null;
        if (filePart != null) {
            InputStream fileContent = filePart.getInputStream(); // Obtener la entrada de archivo
            foto = leerBytesDeInputStream(fileContent); // Leer el contenido del archivo en un array de bytes usando el nuevo método
        }

        //2. Creamos nuestro objeto Ejercicio
        Usuario usuario = new Usuario(Integer.parseInt(id), nombre, correo, clave, Integer.parseInt(edad), Integer.parseInt(altura), Integer.parseInt(peso),foto);

        //3. Invocamos al método de acceso a datos que inserta un cliente
        gestionUsu.modificarUsuario(usuario);
        HttpSession session = request.getSession();
        session.setAttribute("usuario",usuario);

        //4. Redirigimos a la acción por defecto
        response.sendRedirect("AdminUsu");

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

    
    
}