/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexión;

import java.sql.DriverManager;

/**
 *
 * @author USUARIO
 */
public class ConexiónBD {
    java.sql.Connection con;

    public java.sql.Connection conectar() { //Metodo para conexión

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Traemos el DRIVER
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventario", //Nos conectamos a nuestra conexión con usuario root contraseña vacia a la data "inventario"
                    "root",
                    ""
            );
        } catch (Exception e) {
            System.out.println("Error conexión: " + e);
        }
        return con;
    }
}
