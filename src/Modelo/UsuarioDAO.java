/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Conexión.ConexiónBD;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


/**
 *
 * @author USUARIO
 */
public class UsuarioDAO {

    Conexión.ConexiónBD cn = new ConexiónBD(); //Llamamos la conexión a la BD
    java.sql.Connection con = cn.conectar(); //Hacemos la conexión
    public boolean validarLogin(Usuarios u){ // Función para validar el acceso (Login)
        String sql = "SELECT * FROM usuarios WHERE usuario=? AND contraseña=?"; //Sentencia para la consulta.
        try {
            PreparedStatement ps = con.prepareStatement(sql); //Preparamos la consulta
            ps.setString(1, u.getUsuario()); //Agregamos a la consulta el usuario
            ps.setString(2, u.getContraseña()); //Agregamos a la consulta la contraseña
            ResultSet rs = ps.executeQuery(); //Ejecutamos la consulta
            return rs.next(); //Retornamos lo obtenido.
        } catch (Exception e) { //En caso de error
            System.out.println(e); //Imprimimos el error
            return false; //Retornamos falso
        }
    }
    
    public boolean registrarUsuario(Usuarios u){
        String sql = "INSERT INTO usuarios (usuario, contraseña, tipo) VALUES(?,?,?)"; //Sentencia sql
        try {
            PreparedStatement ps = con.prepareStatement(sql); //Preparamos la consulta
            ps.setString(1, u.getUsuario()); //Obtenemos los datos de la clase y la colocamos en la sentencia
            ps.setString(2, u.getContraseña()); //Obtenemos los datos de la clase y la colocamos en la sentencia
            ps.setString(3, u.getTipo()); //Obtenemos los datos de la clase y la colocamos en la sentencia
            ps.executeUpdate(); //Ejecutamos la sentencia
            return true; //Retornamos verdadero
        } catch (Exception e) { //En caso de error 
            System.out.println(e); //Mostramos el mensaje
            return false;//Retornamos falso
        }
    }
    public boolean editarUsuarioPorUsuario(Usuarios u){
        String sql = "UPDATE usuarios SET contraseña=?, tipo=? WHERE usuario=?";
        try (
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getContraseña());
            ps.setString(2, u.getTipo());
            ps.setString(3, u.getUsuario());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean eliminarUsuarioPorUsuario(Usuarios u){
        String sql = "DELETE FROM usuarios WHERE usuario=?";
        try (
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getUsuario());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public java.util.List<Usuarios> listarUsuarios() {
        java.util.List<Usuarios> lista = new java.util.ArrayList<>();
        String sql = "SELECT id, usuario, contraseña, tipo FROM usuarios";
        try (
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuarios u = new Usuarios();
                u.setId(rs.getInt("id"));
                u.setUsuario(rs.getString("usuario"));
                u.setContraseña(rs.getString("contraseña"));
                u.setTipo(rs.getString("tipo"));
                lista.add(u);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
}
