/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuarios;
import Vista.Menu;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class ControladorLogin { //Controlador LOGIN
    Vista.Login vista;
    Modelo.UsuarioDAO dao;
    public ControladorLogin(Vista.Login vista){ //Obtenemos la vista
        this.vista = vista;
        dao = new Modelo.UsuarioDAO(); 
    }
    public void iniciarSesion(){ //Función para el inicio de sesión
        Usuarios u = new Usuarios(); //Llamamos la clase
        u.setUsuario(vista.getUsuario()); //Obtenemos el usuario de la vista y lo enviamos a la clase
        u.setContraseña(vista.getContraseña()); //Obtenemos la contraseña de la vista y la enviamos a la clase
        boolean acceso = dao.validarLogin(u); //Enviamos los datos y obtenemos true/false para dar acceso o no.
        if(acceso){ //Si es verdadero abre el menu
            Menu m = new Menu(u.getTipo()); //Llamamos el formulario
            m.setVisible(true); //Lo ponemos visible
            vista.dispose(); //Cerramos el actual
        }else{ //Si es falso, mostramos el mensaje
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos"); //Mensaje de acceso denegado.
        }
    }
}
