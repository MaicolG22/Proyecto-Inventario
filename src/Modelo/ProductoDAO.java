/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Conexión.ConexiónBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author TIRESIA
 */
public class ProductoDAO {
    Conexión.ConexiónBD cn = new ConexiónBD();//Llamamos la conexión a la BD 
    java.sql.Connection con = cn.conectar(); //Hacemos la conexión
    
    public List<Producto> BuscarProductos(String codigo, String nombre, int tipo, String categoria, String estado){
        List<Producto> lista = new ArrayList<>();
        String filtros = "";
        if(!codigo.isEmpty()){
           if(filtros.isEmpty()){
               filtros += " codigo LIKE ?";
           }else{
               filtros += " AND codigo LIKE ?";
           }
        }
        if(!nombre.isEmpty()){
            if(filtros.isEmpty()){
                filtros += " p.nombre LIKE ?";
            }else{
                filtros += " AND p.nombre LIKE ?";
            }
        }

        if(tipo != 0){
            if(filtros.isEmpty()){
                filtros += " tipo_fk = ?";
            }else{
                filtros += " AND tipo_fk = ?";
            }
        }

        if(!categoria.isEmpty()){
            if(filtros.isEmpty()){
                filtros += " categoria = ?";
            }else{
                filtros += " AND categoria = ?";
            }
        }
        
        if(!estado.isEmpty()){
           if(filtros.isEmpty()){
               filtros += " estado = ?";
           }else{
               filtros += " AND estado = ?";
           }
        }
        String sql = "SELECT p.*, t.nombre AS tipo FROM producto p INNER JOIN tipoproducto t ON t.id = p.tipo_fk";
        if(!filtros.isEmpty()){
            sql += " WHERE " + filtros;
        }
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            int posicion = 1;
            if(!codigo.isEmpty()){
                ps.setString(posicion, "%" + codigo + "%");
                posicion++;
            }
            if(!nombre.isEmpty()){
                ps.setString(posicion, "%" + nombre + "%");
                posicion++;
            }
            if(tipo != 0){
                ps.setInt(posicion, tipo);
                posicion++;
            }
            if(!categoria.isEmpty()){
                ps.setString(posicion, categoria);
                posicion++;
            }
            if(!estado.isEmpty()){
                ps.setString(posicion, estado);
                posicion++;
            }
            ResultSet rs = ps.executeQuery(); //Ejecutamos la consulta
            while(rs.next()){
                Producto p = new Producto();
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setTipo(rs.getInt("tipo_fk"));
                p.setTiponombre(rs.getString("tipo"));
                p.setCategoria(rs.getString("categoria"));
                p.setObservacion(rs.getString("observacion"));
                p.setEstado(rs.getString("estado"));
                
                lista.add(p);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al buscar productos");
        }
        return lista;
    }
    
    public List<Producto> BuscarProductosStock(){
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT p.*, t.nombre AS tipo FROM producto p INNER JOIN tipoproducto t ON t.id = p.tipo_fk WHERE cantidad <= 5 ORDER BY cantidad DESC";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); //Ejecutamos la consulta
            while(rs.next()){
                Producto p = new Producto();
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setTiponombre(rs.getString("tipo"));
                p.setCategoria(rs.getString("categoria"));
                p.setObservacion(rs.getString("observacion"));
                
                lista.add(p);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al buscar productos");
        }
        return lista;
    }
    
    public Producto BuscarProductoCodigo(String codigo){
        Producto p = null;
        String sql = "SELECT p.*, t.nombre AS tipo FROM producto p INNER JOIN tipoproducto t ON t.id = p.tipo_fk WHERE p.codigo = ?";

        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){

                p = new Producto();

                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setTipo(rs.getInt("tipo_fk"));
                p.setTiponombre(rs.getString("tipo"));
                p.setCategoria(rs.getString("categoria"));
                p.setObservacion(rs.getString("observacion"));
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al buscar producto");
        }
        return p;
    }
              
    public boolean AgregarProducto(Producto p){
        String sql = "INSERT INTO producto (codigo,nombre,precio,cantidad,tipo_fk,categoria,observacion) VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getCantidad());
            ps.setInt(5, p.getTipo());
            ps.setString(6, p.getCategoria());
            ps.setString(7, p.getObservacion());
            ps.executeUpdate();
            System.out.println("Hace el insert");
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean EditarProducto(Producto p){
        String sql = "UPDATE producto SET nombre=?, precio=?, cantidad=?, tipo_fk=?, categoria=?, observacion=? WHERE codigo=?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getTipo());
            ps.setString(5, p.getCategoria());
            ps.setString(6, p.getObservacion());
            ps.setString(7, p.getCodigo());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("Error:"+e);
            return false;
        } 
    }
    
        public boolean EliminarProducto(Producto p){
        String sql = "UPDATE producto SET estado='INACTIVO' WHERE codigo = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodigo());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
}
