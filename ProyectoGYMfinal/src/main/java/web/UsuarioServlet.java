/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;
import dominio.Usuario;
import negocio.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

/**
 *
 * @author Fer
 */
@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet{
    
    // Ahora hacemos la inyección del componente EJB local al servlet
    @Inject
    // Ahora definimos nuestra variable
    private InterfazGestionUsu gestionUsu; // Cremos una instancia de nuestra if local
    private HttpSession session = null;
    
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
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "login":
                    this.login(request, response);
                    break;
                case "modificar":
                    //this.modificarCliente(request, response);
                    break;
                default: 
                    //this.accionDefault(request, response);
            }
        } else {
            //this.accionDefault(request, response);
        }
    }
    
   
    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("pass");
        String edad = request.getParameter("edad");
        String altura = request.getParameter("altura");
        String peso = request.getParameter("peso");
        System.out.println("hola");
        //2. Creamos nuestro objeto Cliente
        Usuario usuario = new Usuario(nombre, correo, clave, Integer.parseInt(edad), Integer.parseInt(altura), Integer.parseInt(peso));
        //3. Invocamos al método de acceso a datos que inserta un cliente
        gestionUsu.registrarUsuario(usuario);
        //System.out.println("registrosModificados = " + registrosModificados);
        //4. Redirigimos a la acción por defecto
        //this.accionDefault(request, response);
        response.sendRedirect("index.jsp");
    }
    
    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request

        System.out.println("inicia el usuario servlet");
        List<Usuario> usuarios = gestionUsu.listarUsuarios();
        System.out.println("usuarios: " + usuarios);
        
        // Ponemos usuarios en un alcance
        request.setAttribute("usuarios", usuarios);
        
        // Redirigimos al JSP
        request.getRequestDispatcher("/ejercicios.jsp").forward(request, response);
    }
    
    
    private void editarUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request

        session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usuario");

        
        request.setAttribute("usuarios", usu);
       
        
        // Redirigimos al JSP
        request.getRequestDispatcher("/perfil.jsp").forward(request, response);
    }
    
    
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //session = request.getSession();
 
        String correo = request.getParameter("correo");
        String clave = request.getParameter("pass");
  
        //2. Creamos nuestro objeto Cliente
        Usuario usuario = new Usuario(correo, clave);
        
        //session.setAttribute("usuario", gestionUsu.econtrarUsuarioPorCorreo(usuario));
        //3. Invocamos al método de acceso a datos que inserta un cliente
        if(gestionUsu.comprobar(usuario))response.sendRedirect("ejercicios");
        else response.sendRedirect("login.jsp");
        //System.out.println("registrosModificados = " + registrosModificados);
        //4. Redirigimos a la acción por defecto
        //this.accionDefault(request, response);
        
    }
    
    
}