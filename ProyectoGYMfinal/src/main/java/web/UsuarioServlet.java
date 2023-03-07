/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;
import dominio.Rutina;
import dominio.Usuario;
import java.io.ByteArrayOutputStream;
import negocio.*;
import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.Part;
import javax.transaction.Transactional;

/**
 *
 * @author Fer
 */
@WebServlet("/usuarios")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
    maxFileSize = 1024 * 1024 * 150,      // 10 MB
    maxRequestSize = 1024 * 1024 * 200    // 50 MB
)
public class UsuarioServlet extends HttpServlet{
    //private HttpSession session = null;
    // Ahora hacemos la inyección del componente EJB local al servlet
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
                    case "buscar":
                        this.listarNoAmigos(request, response);
                        break;
                    case "seguidores":
                        this.listarAmigosDe(request, response);
                        break;
                    case "seguidos":
                        this.listarAmigos(request, response);
                        break;
                    case "nuevoAmigo":
                        this.nuevoAmigo(request, response);
                        break;
                    case "listarRut":
                        this.listarRut(request, response);
                        break;
                    default:
                        this.listarNoAmigos(request, response);
                }
            } else {
                this.listarNoAmigos(request, response);
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
                case "insertarRutina":
                    this.insertarRut(request, response);
                    break;
                case "login":
                    this.login(request, response);
                    break;
                case "modificar":
                    this.modificar(request, response);
                    break;
                default: 
                    this.listarNoAmigos(request, response);
            }
        } else {
            this.listarNoAmigos(request, response);
        }
    }
    
    
    private void insertarRut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String nombre = request.getParameter("nombre");
        
        //2. Creamos nuestro objeto Ejercicio
        Rutina ejercicio = new Rutina(nombre,usuario);
//        usuario.getRutinaList().clear();
        List<Rutina> rutina = usuario.getRutinaList();
        //3. Invocamos al método de acceso a datos que inserta un ejercicio
        rutina.add(ejercicio);
        usuario.setRutinaList(rutina);
        //4. Redirigimos a la acción por defecto
        gestionUsu.modificarUsuario(usuario);
        this.listarRut(request, response);
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
    
    
//    private void nuevoAmigo(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        //1. Recuperamos los parámetros del request
//        
//        HttpSession session = request.getSession();
//        Usuario usuario = (Usuario) session.getAttribute("usuario");
//        
//        String id =request.getParameter("id");
//
//        Usuario ejercicio = new Usuario(Integer.parseInt(id));
//
//        Usuario usu = gestionUsu.encontrarUsuarioPorID(ejercicio);
//        
//        List<Usuario> uno = usu.getUsuarioList();
//        List<Usuario> dos = usuario.getUsuarioList();
//        uno.add(usuario);
//        dos.add(usu);
//        usu.setUsuarioList(uno);
//        usuario.setUsuarioList(dos);
//        
//        response.sendRedirect("usuarios");
//    }
    
    private void listarRut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Rutina> amigos = usuario.getRutinaList();
        
        request.setAttribute("rutina", amigos);
        request.getRequestDispatcher("/rutinas.jsp").forward(request, response);
    }
    
    private void nuevoAmigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    //1. Recuperamos los parámetros del request
        
    HttpSession session = request.getSession();
    Usuario usuario = (Usuario) session.getAttribute("usuario");
        
    String id =request.getParameter("id");

    Usuario amigo = new Usuario(Integer.parseInt(id));
//    UsuarioDao usuarioDao = new UsuarioDao();

    Usuario amigoEnBD = gestionUsu.encontrarUsuarioPorID(amigo);
        
    List<Usuario> amigosUsuario = usuario.getUsuarioList();
    List<Usuario> amigosAmigo = amigoEnBD.getUsuarioList1();
    amigosUsuario.add(amigoEnBD);
    amigosAmigo.add(usuario);
    usuario.setUsuarioList(amigosUsuario);
    amigoEnBD.setUsuarioList1(amigosAmigo);
        
    gestionUsu.modificarUsuario(usuario);
    gestionUsu.modificarUsuario(amigoEnBD);
        
    response.sendRedirect("usuarios");
}

    
//    
//    
//    private void listar(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        //1. Recuperamos los parámetros del request
//        HttpSession session = request.getSession();
//        Usuario usuario = (Usuario) session.getAttribute("usuario");
//        List<Usuario> usu = gestionUsu.listarUsuarios();
//        List<Usuario> usuarios = new ArrayList<>();
//        for (Usuario u : usu){
//            if(!u.getCorreo().equalsIgnoreCase(usuario.getCorreo()) && !u.getCorreo().equalsIgnoreCase("fer@gmail.com")){
//                usuarios.add(u);
//            }
//        }
//        System.out.println("HOLAAAAAAAAAAAAAAAA");
//        System.out.println(usuarios);
//        List<Usuario> noAmigos = new ArrayList<>();
//        List<Usuario> amigos = new ArrayList<>();
//
//        // Verificar si el ID de cada usuario está en la lista de amigos del usuario en cuestión
//        for (Usuario otroUsuario : usuarios) {
//            if (!usuario.getUsuarioList().contains(otroUsuario)) {
//                noAmigos.add(otroUsuario);
//            }else{
//                amigos.add(otroUsuario);
//            }
//            
//        }
//        
//        for (Usuario ejer : noAmigos) { // recorremos la lista de ejercicios
//             // obtenemos la imagen en formato byte[]
//            if (ejer.getFoto() != null) {
//                byte[] imagen = Base64.getEncoder().encode(ejer.getFoto());
//                String foto = new String(imagen,"UTF-8"); // la convertimos a base64
//                ejer.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Ejercicio
////                System.out.println(ejer.getFotobase64());
//            }
//        }
//        for (Usuario ejer : amigos) { // recorremos la lista de ejercicios
//             // obtenemos la imagen en formato byte[]
//            if (ejer.getFoto() != null) {
//                byte[] imagen = Base64.getEncoder().encode(ejer.getFoto());
//                String foto = new String(imagen,"UTF-8"); // la convertimos a base64
//                ejer.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Ejercicio
////                System.out.println(ejer.getFotobase64());
//            }
//        }
//        
//        // Ponemos usuarios en un alcance
//        request.setAttribute("noAmigos", noAmigos);
//        request.setAttribute("amigos", amigos);
//        
//        // Redirigimos al JSP
//        request.getRequestDispatcher("/amigos.jsp").forward(request, response);
//    }
    
    
    private void listarNoAmigos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Usuario> usuarios = gestionUsu.listarUsuarios();
        List<Usuario> noAmigos = new ArrayList<>();
        
        for (Usuario otroUsuario : usuarios) {
            if (!usuario.getUsuarioList().contains(otroUsuario) && !otroUsuario.equals(usuario) && !usuario.getCorreo().equalsIgnoreCase("fer@gmail.com")) {
                noAmigos.add(otroUsuario);
            }
        }
        
        for (Usuario noAmigo : noAmigos) { // recorremos la lista de amigos
            // obtenemos la imagen en formato byte[]
            if (noAmigo.getFoto() != null) {
                byte[] imagen = Base64.getEncoder().encode(noAmigo.getFoto());
                String foto = new String(imagen,"UTF-8"); // la convertimos a base64
                noAmigo.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Usuario
            }
        }
        
        request.setAttribute("noAmigos", noAmigos);
        request.getRequestDispatcher("/amigos.jsp").forward(request, response);
    }

    
    private void listarAmigos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Usuario> amigos = usuario.getUsuarioList();
        
        for (Usuario amigo : amigos) { // recorremos la lista de amigos
            // obtenemos la imagen en formato byte[]
            if (amigo.getFoto() != null) {
                byte[] imagen = Base64.getEncoder().encode(amigo.getFoto());
                String foto = new String(imagen,"UTF-8"); // la convertimos a base64
                amigo.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Usuario
            }
        }
        
        request.setAttribute("amigos", amigos);
        request.getRequestDispatcher("/seguidos.jsp").forward(request, response);
    }

    
    private void listarAmigosDe(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Usuario> usuarios = gestionUsu.listarUsuarios();
        List<Usuario> amigosDe = new ArrayList<>();
        
        for (Usuario otroUsuario : usuarios) {
            if (otroUsuario.getUsuarioList().contains(usuario)) {
                amigosDe.add(otroUsuario);
            }
        }
        
        for (Usuario amigoDe : amigosDe) { // recorremos la lista de amigos
            // obtenemos la imagen en formato byte[]
            if (amigoDe.getFoto() != null) {
                byte[] imagen = Base64.getEncoder().encode(amigoDe.getFoto());
                String foto = new String(imagen,"UTF-8"); // la convertimos a base64
                amigoDe.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Usuario
            }
        }
        
        request.setAttribute("amigosDe", amigosDe);
        request.getRequestDispatcher("/seguidores.jsp").forward(request, response);
    }

    
    
    
    private void editarUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //1. Recuperamos los parámetros del request

        HttpSession session = request.getSession();
        Usuario usuarios = (Usuario) session.getAttribute("usuario");
        if (usuarios.getFoto() != null) { // si la imagen existe
            byte[] imagen = Base64.getEncoder().encode(usuarios.getFoto());
            String foto = new String(imagen,"UTF-8"); // la convertimos a base64
            usuarios.setFotobase64(foto); // la seteamos como un string en base64 en el objeto Ejercicio
//            System.out.println(usuarios.getFotobase64());
        }
        request.setAttribute("usuarios", usuarios);
  
        // Redirigimos al JSP
        request.getRequestDispatcher("/perfil.jsp").forward(request, response);
    }
    
    
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String correo = request.getParameter("correo");
        String clave = request.getParameter("pass");

        //2. Creamos nuestro objeto Cliente
        Usuario usuario = new Usuario(correo, clave);
        int num = gestionUsu.listarUsuarios().size();     //Lo he tenido que meter en una variable para que funcione...
        for (int i = 0; i < num; i++) {
            //System.out.println(seleccionar().size());
            if(gestionUsu.listarUsuarios().get(i).getCorreo().equalsIgnoreCase(usuario.getCorreo())){
                
                session.setAttribute("usuario", gestionUsu.listarUsuarios().get(i));
            }
        }
        
        
        //session.setAttribute("usuario", gestionUsu.econtrarUsuarioPorCorreo(usuario));
        //3. Invocamos al método de acceso a datos que inserta un cliente
        if(gestionUsu.comprobarAdmin(usuario))response.sendRedirect("AdminEjer");
        else if(gestionUsu.comprobar(usuario))response.sendRedirect("ejercicios");
        else response.sendRedirect("login.jsp");
        //System.out.println("registrosModificados = " + registrosModificados);
        //4. Redirigimos a la acción por defecto
        //this.accionDefault(request, response);
        
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
        response.sendRedirect("ejercicios");

    }
    
    
    
}