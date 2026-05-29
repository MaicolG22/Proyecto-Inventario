/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.TipoProducto;
import Modelo.TipoProductoDAO;
import Vista.TiposProductos;
import javax.swing.JOptionPane;

/**
 *
 * @author TIRESIA
 */
public class ControladorTipo {
    TiposProductos vista;
    TipoProductoDAO dao;
    TipoProducto tp = new TipoProducto();

    public ControladorTipo(TiposProductos vista) {
        this.vista = vista;
        this.dao = new TipoProductoDAO();
    }
    
    public void Agregar_Eliminar_Tipo(String nombre, boolean bandera){
        tp.setNombre(nombre);
        String respuesta;
        String accion="";
        if(bandera){
            accion = "agregado";
            respuesta = dao.AgregarTipo(tp);
        }else{
            accion = "eliminado";
            respuesta = dao.EliminarTipo(tp);
        }
        if ("OK".equals(respuesta)) {
            JOptionPane.showMessageDialog(null, "Tipo "+accion+" correctamente");
            vista.Limpiar();
            vista.ActualizarLista();
        }else if ("usado".equals(respuesta)) {
                vista.Limpiar();
                vista.ActualizarLista();
                JOptionPane.showMessageDialog(null, "El tipo que intenta eliminar está siendo utilizado por producto(s) y no puede eliminarse.");
        }else{ //sino edito correctamente
            JOptionPane.showMessageDialog(null,"Error al realizar la acción");
        }
    }
}
