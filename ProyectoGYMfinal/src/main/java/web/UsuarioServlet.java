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

/**
 *
 * @author Fer
 */
@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet{
    
    // Ahora hacemos la inyección del componente EJB local al servlet
    @Inject
    // Ahora definimos nuestra variable
    GestionUsu gestionUsu; // Cremos una instancia de nuestra if local
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse respose)
    throws ServletException, IOException {
        
        /** Ahora este método va acceder al listado de usuarios por medio
         * de la instancia que estamos recibiendo el EJB         
         */
        System.out.println("inicia el usuario servlet");
        List<Usuario> usuarios = gestionUsu.listarUsuarios();
        System.out.println("usuarios: " + usuarios);
        
        // Ponemos usuarios en un alcance
        request.setAttribute("usuarios", usuarios);
        
        // Redirigimos al JSP
        request.getRequestDispatcher("/listadoUsuarios.jsp").forward(request, 
                respose);
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
        String correo = request.getParameter("apellido");
        String clave = request.getParameter("email");
        int edad = Integer.parseInt(request.getParameter("edad"));
        int altura = Integer.parseInt(request.getParameter("altura"));
        int peso = Integer.parseInt(request.getParameter("peso"));
        
        //2. Creamos nuestro objeto Cliente
        Usuario usuario = new Usuario(nombre, correo, clave, edad, altura, peso);
        //3. Invocamos al método de acceso a datos que inserta un cliente
        gestionUsu.registrarUsuario(usuario);
        //System.out.println("registrosModificados = " + registrosModificados);
        //4. Redirigimos a la acción por defecto
        //this.accionDefault(request, response);
    }
    
    
}