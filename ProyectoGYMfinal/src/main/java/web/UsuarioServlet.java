/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;
import negocio.*;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UsuarioServelt extends HttpServlet{
    
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
}