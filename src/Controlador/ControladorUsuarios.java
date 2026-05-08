/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.UsuarioDAO;
import Modelo.Usuarios;
import Vista.FrmUsuarios;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class ControladorUsuarios {
    FrmUsuarios vista;
    UsuarioDAO dao;
    Usuarios u = new Usuarios();
    public ControladorUsuarios(FrmUsuarios vista){
        this.vista = vista;
        dao = new UsuarioDAO();
    }

    public void guardarUsuario(String usuario, String contraseña, String tipo){ //Fncion para guardar usuario 
        u.setUsuario(usuario);          // usuario
        u.setContraseña(contraseña);     // contraseña
        u.setTipo(tipo); //tipo
        boolean guardar = dao.registrarUsuario(u); //enviamos los datos y obtenemos verdaedro o falso para guardar.
        if(guardar){ //si guardo correctamente
            JOptionPane.showMessageDialog(null, "Usuario guardado correctamente");
            cargarTabla();
            vista.limpiar();
        }else{ //sino guardo correctamente
            JOptionPane.showMessageDialog(null,"Error al guardar");
        }
    }
    public void editarUsuario(String usuario, String contraseña, String tipo){
        u.setUsuario(usuario);          // usuario
        u.setContraseña(contraseña);     // contraseña
        u.setTipo(tipo); //tipo
        boolean editado = dao.editarUsuarioPorUsuario(u); //enviamos los datos y obtenemos verdaedro o falso para guardar.

        if(editado){ //si edito correctamente
            JOptionPane.showMessageDialog(null, "Usuario editado correctamente");
            cargarTabla();
            vista.limpiar();
        }else{ //sino edito correctamente
            JOptionPane.showMessageDialog(null,"Error al guardar");
        }
    }

    public void eliminarUsuario(String usuario){
        int confirm = JOptionPane.showConfirmDialog(null,"¿Seguro que desea eliminar el usuario?","Confirmar",JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        u.setUsuario(usuario);
        boolean eliminado = dao.eliminarUsuarioPorUsuario(u); //enviamos los datos y obtenemos verdaedro o falso para guardar.

        if(eliminado){ //si elimino correctamente
            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
            cargarTabla();
            vista.limpiar();
        }else{ //sino elimino correctamente
            JOptionPane.showMessageDialog(null,"Error al guardar");
        }
    }
    public void cargarTabla() {
        DefaultTableModel model = (DefaultTableModel) vista.getTblUsuarios().getModel();
        model.setRowCount(0);

        for (Usuarios u : dao.listarUsuarios()) {
            model.addRow(new Object[]{
                u.getUsuario(),
                u.getContraseña(),
                u.getTipo()
            });
        }
    }
}
