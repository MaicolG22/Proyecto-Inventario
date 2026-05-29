/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Conexión.ConexiónBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author TIRESIA
 */
public class TipoProductoDAO {
    Conexión.ConexiónBD cn = new ConexiónBD(); //Llamamos la conexión a la BD 
    java.sql.Connection con = cn.conectar(); //Hacemos la conexión
    public ArrayList<TipoProducto> ListarTipos(){
        ArrayList<TipoProducto> lista = new ArrayList<>();
        String sql="SELECT * FROM tipoproducto ORDER BY nombre";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoProducto tp = new TipoProducto();
                tp.setId(rs.getInt("id"));
                tp.setNombre(rs.getString("nombre"));
                lista.add(tp);
            }
        }catch(Exception e){
            System.out.println("Error al listar:"+e);
        }
        return lista;
    }
    
    public String AgregarTipo(TipoProducto n){
        String sql = "INSERT INTO tipoproducto (nombre) VALUES (?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, n.getNombre());
            ps.executeUpdate();
            return "OK";
        }catch(Exception e){
            System.out.println("Error al insertar tipo:"+e);
            return "Error"+e;
        }
    }
    
    public String EliminarTipo(TipoProducto n){
        String sql = "DELETE FROM tipoproducto WHERE nombre = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, n.getNombre());
            ps.executeUpdate();
            return "OK";
        }catch (SQLIntegrityConstraintViolationException ex) {
            return "usado";
        }catch(Exception e){
            return "error";
        }
    }
}
