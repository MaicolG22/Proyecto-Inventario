/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.ControladorMovimientoP;
import Controlador.ControladorProductos;
import Modelo.Movimiento;
import Modelo.Producto;
import Modelo.TipoProducto;
import Modelo.TipoProductoDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class GestionCantidades extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GestionCantidades.class.getName());

    /**
     * Creates new form GestionCantidades
     */
    public GestionCantidades() {
        initComponents();
        setTitle("Inventra | Movimiento de Inventario | ");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        DeshabilitoCampos();
        MostrarCombo();
    }
    
    public void DeshabilitoCampos(){
        jpSalida.setEnabled(false);
        txtCantSalida.setEnabled(false);
        txtObservacionSalida.setEnabled(false);                              
        jpEntrada.setEnabled(false);
        txtCantEntrada.setEnabled(false);
        txtObservacionEntrada.setEnabled(false);
        txtCantEntrada.setText("");
        txtObservacionEntrada.setText("");
        txtCantSalida.setText("");
        txtObservacionSalida.setText("");
        buttonGroup2.clearSelection();
    }
    public void MostrarCombo(){
        TipoProductoDAO dao=new TipoProductoDAO();
        TipoProducto todos = new TipoProducto();
        todos.setId(0);
        todos.setNombre("");
        cbTipo.addItem(todos);
        for(TipoProducto tipo : dao.ListarTipos()){ //Recorremos todos los resultados
            cbTipo.addItem(tipo);
        }
    }
    public void buscar(String codigo){
        Controlador.ControladorProductos p = new ControladorProductos();
        if(txtCodigo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe agregar el código de un producto para buscar");
            return;
        }
        Producto producto = p.BuscarProductoCodigo(codigo);
        if(producto == null){
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
            return;
        }
        cargarProducto(producto);
        ConsultarMov();
    }
    
    
    public void cargarProducto(Producto pro){
        txtCodigo.setText(pro.getCodigo());
        txtNombre.setText(pro.getNombre());
        txtCantidad.setText(String.valueOf(pro.getCantidad()));
        txtObservacion.setText(pro.getObservacion());
        if(pro.getCategoria().equals("Normal")){
            rbNormal.setSelected(true);
        }else{
            rbEspecial.setSelected(true);
        }
        for(int i = 0; i < cbTipo.getItemCount(); i++){
            TipoProducto tipo = cbTipo.getItemAt(i);
            if(tipo.toString().equals(pro.getTiponombre())){
                cbTipo.setSelectedIndex(i);
                break;
            }
        }
    }
    
    public void limpiar(){
        txtNombre.setText("");
        txtCantidad.setText("");
        txtObservacion.setText("");
        buttonGroup1.clearSelection();
        cbTipo.setSelectedIndex(0);
        DefaultTableModel model = (DefaultTableModel) tbMovimiento.getModel();
        model.setRowCount(0);
    }
    
    boolean tipomov = false;
    public void RealizarMovProducto(){
        ControladorMovimientoP c = new ControladorMovimientoP();
        String codigo = txtCodigo.getText().trim();
        String cantidadStr;
        String observacion;
        if(rbEntrada.isSelected()){
            tipomov=true;
            cantidadStr = txtCantEntrada.getText().trim();
            observacion = txtObservacionEntrada.getText().trim();
        }else{
            tipomov=false;
            cantidadStr = txtCantSalida.getText().trim();
            observacion = txtObservacionSalida.getText().trim();
        }
        if(codigo.isEmpty() || cantidadStr.isEmpty()){
            JOptionPane.showMessageDialog(null, "Complete los campos del movimiento");
            return;
        }
        try {
            int cantidad = Integer.parseInt(cantidadStr);
            if(cantidad <= 0){
                JOptionPane.showMessageDialog(null, "Cantidad inválida");
                return;
            }
            c.MovimientoInventario(tipomov, codigo, cantidad, observacion);
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Cantidad debe ser numérica");
        }
        DeshabilitoCampos();
        buscar(codigo);
    }
    
    public void ConsultarMov(){
        ControladorMovimientoP c = new ControladorMovimientoP();
        String codigo = txtCodigo.getText();
        List<Movimiento> lista = c.BuscarProductoCodigo(codigo);
        DefaultTableModel modelo = (DefaultTableModel) tbMovimiento.getModel();
        modelo.setRowCount(0);
        for(Movimiento m : lista){
            Object[] fila = {m.getCodigo(), m.getNombre(), m.getTipo(), m.getCantidad(), m.getObservacion()};
            modelo.addRow(fila);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        rbNormal = new javax.swing.JRadioButton();
        rbEspecial = new javax.swing.JRadioButton();
        btnBuscar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtObservacion = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jpEntrada = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCantEntrada = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtObservacionEntrada = new javax.swing.JTextField();
        jpSalida = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtCantSalida = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtObservacionSalida = new javax.swing.JTextField();
        rbEntrada = new javax.swing.JRadioButton();
        rbSalida = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMovimiento = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombre producto");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        txtNombre.setEditable(false);
        txtNombre.setEnabled(false);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 350, -1));

        jLabel2.setText("Codigo producto");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });
        jPanel2.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, -1));

        jLabel3.setText("Tipo");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        cbTipo.setEnabled(false);
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });
        jPanel2.add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 200, -1));

        jLabel7.setText("Categoria");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, -1));

        buttonGroup1.add(rbNormal);
        rbNormal.setText("Normal");
        rbNormal.setEnabled(false);
        jPanel2.add(rbNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 42, -1, -1));

        buttonGroup1.add(rbEspecial);
        rbEspecial.setText("Especial");
        rbEspecial.setEnabled(false);
        rbEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEspecialActionPerformed(evt);
            }
        });
        jPanel2.add(rbEspecial, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 42, -1, -1));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 140, -1));

        jLabel9.setText("Cantidad Actual");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, -1));

        txtCantidad.setEditable(false);
        txtCantidad.setEnabled(false);
        txtCantidad.setFocusable(false);
        jPanel2.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 100, -1));

        jLabel10.setText("Observación:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        txtObservacion.setEditable(false);
        txtObservacion.setEnabled(false);
        jPanel2.add(txtObservacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 410, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 140));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestion Cantidades"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpEntrada.setBorder(javax.swing.BorderFactory.createTitledBorder("Entradas"));
        jpEntrada.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Cantidad entrada");
        jpEntrada.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        jpEntrada.add(txtCantEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 102, -1));

        jLabel6.setText("Observación");
        jpEntrada.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));
        jpEntrada.add(txtObservacionEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 250, -1));

        jPanel1.add(jpEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 400, 80));

        jpSalida.setBorder(javax.swing.BorderFactory.createTitledBorder("Salidas"));
        jpSalida.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Cantidad salida");
        jpSalida.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 20, -1, -1));
        jpSalida.add(txtCantSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 102, -1));

        jLabel8.setText("Observación");
        jpSalida.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 20, -1, -1));
        jpSalida.add(txtObservacionSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 240, -1));

        jPanel1.add(jpSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 390, 80));

        buttonGroup2.add(rbEntrada);
        rbEntrada.setText("Entrada");
        rbEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEntradaActionPerformed(evt);
            }
        });
        jPanel1.add(rbEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 80, -1));

        buttonGroup2.add(rbSalida);
        rbSalida.setText("Salida");
        rbSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSalidaActionPerformed(evt);
            }
        });
        jPanel1.add(rbSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 70, -1));

        jButton1.setText("Realizar movimiento");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 160, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 146, 820, 150));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Movimientos"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbMovimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Tipo Movimiento", "Cantidad", "Observación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbMovimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbMovimiento.setEnabled(false);
        tbMovimiento.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbMovimiento.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbMovimiento);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 800, 190));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 820, 220));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel4.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 530, 100, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEspecialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbEspecialActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void rbEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEntradaActionPerformed
        jpEntrada.setEnabled(true);
        jpSalida.setEnabled(false);
        txtCantSalida.setText("");
        txtObservacionSalida.setText("");
        txtCantSalida.setEnabled(false);
        txtObservacionSalida.setEnabled(false);
        txtCantEntrada.setEnabled(true);
        txtObservacionEntrada.setEnabled(true);
    }//GEN-LAST:event_rbEntradaActionPerformed

    private void rbSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSalidaActionPerformed
        jpEntrada.setEnabled(false);
        jpSalida.setEnabled(true);
        txtCantEntrada.setText("");
        txtObservacionEntrada.setText("");
        txtCantSalida.setEnabled(true);
        txtObservacionSalida.setEnabled(true);
        txtCantEntrada.setEnabled(false);
        txtObservacionEntrada.setEnabled(false);
    }//GEN-LAST:event_rbSalidaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar(txtCodigo.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        btnBuscar.doClick();
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        limpiar();
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        RealizarMovProducto();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new GestionCantidades().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<TipoProducto> cbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpEntrada;
    private javax.swing.JPanel jpSalida;
    private javax.swing.JRadioButton rbEntrada;
    private javax.swing.JRadioButton rbEspecial;
    private javax.swing.JRadioButton rbNormal;
    private javax.swing.JRadioButton rbSalida;
    private javax.swing.JTable tbMovimiento;
    private javax.swing.JTextField txtCantEntrada;
    private javax.swing.JTextField txtCantSalida;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtObservacion;
    private javax.swing.JTextField txtObservacionEntrada;
    private javax.swing.JTextField txtObservacionSalida;
    // End of variables declaration//GEN-END:variables
}
