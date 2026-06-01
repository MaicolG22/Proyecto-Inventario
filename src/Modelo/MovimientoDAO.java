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
public class MovimientoDAO {
    Conexión.ConexiónBD cn = new ConexiónBD();//Llamamos la conexión a la BD 
    java.sql.Connection con = cn.conectar(); //Hacemos la conexión
    
    public List<Movimiento> BuscarMovimientosPorCodigo(String codigo){
        List<Movimiento> lista = new ArrayList<>();
        String sql = "SELECT p.codigo, p.nombre, m.tipo, m.cantidad, m.observacion FROM movimientos m " +
                     "INNER JOIN producto p ON p.id = m.producto_fk WHERE p.codigo = ? ORDER BY m.id DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Movimiento m = new Movimiento();
                m.setCodigo(rs.getString("codigo"));
                m.setNombre(rs.getString("nombre"));
                m.setTipo(rs.getString("tipo"));
                m.setCantidad(rs.getInt("cantidad"));
                m.setObservacion(rs.getString("observacion"));
                lista.add(m);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al buscar movimientos");
        }
        return lista;
    }
    
    public int ConsultarInventario(String codigo){
        int cantidad = 0;
        try {
            String sql = "SELECT cantidad FROM producto where codigo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            java.sql.ResultSet rs = ps.executeQuery();
            if(rs.next()){
                cantidad = rs.getInt("cantidad");
            }
        }catch(Exception e){
            System.out.println("Error al consultar inventario: "+ e.getMessage());
        }
        return cantidad;
    }
    public boolean RealizarEntrada(int cantidad, String codigo, String observacion) {
        try {
            String sql = "UPDATE producto SET cantidad = cantidad + ? WHERE codigo = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setString(2, codigo);
            ps.executeUpdate();
            
            String sql2 = "INSERT INTO movimientos (producto_fk, cantidad, tipo, observacion) " +
            "VALUES ((SELECT id FROM producto WHERE codigo = ?), ?, 'entrada', ?)";
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setString(1, codigo);
            ps2.setInt(2, cantidad);
            ps2.setString(3, observacion);
            ps2.executeUpdate();
            
            return true;
        } catch (Exception e) {
            System.out.println("Error entrada: " + e.getMessage());
            return false;
        }
    }

    public boolean RealizarSalida(int cantidad, String codigo, String observacion) {
        try {
            String sql = "UPDATE producto SET cantidad = cantidad - ? WHERE codigo = ? AND cantidad >= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setString(2, codigo);
            ps.setInt(3, cantidad);
            
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                con.rollback(); // no había stock suficiente o código no existe
                return false;
            }

            String sql2 = "INSERT INTO movimientos (producto_fk, cantidad, tipo, observacion) " +
            "VALUES ((SELECT id FROM producto WHERE codigo = ?), ?, 'salida', ?)";
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setString(1, codigo);
            ps2.setInt(2, cantidad);
            ps2.setString(3, observacion);
            ps2.executeUpdate();
            
            return true;
        } catch (Exception e) {
            System.out.println("Error salida: " + e.getMessage());
            return false;
        }
    }
}
