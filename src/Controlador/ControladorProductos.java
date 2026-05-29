/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
import Modelo.ProductoDAO;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author TIRESIA
 */
public class ControladorProductos {
    ProductoDAO dao;
    Producto p = new Producto();

    public ControladorProductos() {
        this.dao = new ProductoDAO();
    }
    
    public void Agregar_Editar_Producto(String codigo, String nombre, float precio, int cantidad, int tipo, String categoria, String observacion, boolean editar){
        boolean respuesta = false;
        String accion = "agregado";
        p.setCodigo(codigo);
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        p.setTipo(tipo);
        p.setCategoria(categoria);
        p.setObservacion(observacion);
        if(editar){
            respuesta = dao.EditarProducto(p);
            accion="editado";
        }else{
            respuesta = dao.AgregarProducto(p);
        }
        
        if(respuesta){
            JOptionPane.showMessageDialog(null, "Producto "+accion+"  correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Error al agregar/editar producto. \n");
        }
    }
    
    public void EliminarProducto(String codigo){
        boolean respuesta = false;
        p.setCodigo(codigo);
        respuesta = dao.EliminarProducto(p);
        if(respuesta){
            JOptionPane.showMessageDialog(null, "Producto INACTIVADO correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Error al INACTIVAR producto");
        }
    }
    
    public Producto BuscarProductoCodigo(String codigo){
        return dao.BuscarProductoCodigo(codigo);
    }
    
    public List<Producto> BuscarProducto(String codigo, String nombre, int tipo, String categoria, String estado){
        return dao.BuscarProductos(codigo, nombre, tipo, categoria, estado);
    }
    
    public List<Producto> BuscadorProductoStock(){
        return dao.BuscarProductosStock();
    }
}
