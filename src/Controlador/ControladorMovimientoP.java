/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Movimiento;
import Modelo.MovimientoDAO;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author TIRESIA
 */
public class ControladorMovimientoP {
    MovimientoDAO dao;

    public ControladorMovimientoP() {
        this.dao = new MovimientoDAO();
    }

    public void MovimientoInventario(boolean tipomov, String codigo, int cantidad, String observacion){
        if(tipomov){// ENTRADA
            boolean ok = dao.RealizarEntrada(cantidad, codigo, observacion);
            if(ok){
                JOptionPane.showMessageDialog(null, "Entrada realizada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al realizar entrada");
            }

        } else {// SALIDA
            int stock = dao.ConsultarInventario(codigo); // Reviso las cantidades disponbiles en la función consultarInv
            if(cantidad > stock){ //Validación si las cantidades que van a salir son mayores a las actuales
                JOptionPane.showMessageDialog(null, "No hay suficientes cantidades disponibles para generar la salida");
                return;
            }
            boolean ok = dao.RealizarSalida(cantidad, codigo, observacion);
            if(ok){
                JOptionPane.showMessageDialog(null, "Salida realizada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al realizar salida");
            }
        }
    }
    
    public List<Movimiento> BuscarProductoCodigo(String codigo){
        return dao.BuscarMovimientosPorCodigo(codigo);
    }
    
    
}
