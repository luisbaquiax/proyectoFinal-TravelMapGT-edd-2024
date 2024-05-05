/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.luis.travelgt.frontend;

import com.luis.travelgt.objetos.utiles.ManejoDatos;
import java.awt.Color;
import java.awt.Toolkit;

/**
 *
 * @author Luis
 */
public class VerificarDatos extends javax.swing.JFrame {

    private ManejoDatos manejoDatos;
    private TravelGt travelGt;
    private int param;

    /**
     * Creates new form VerificarDatos
     */
    public VerificarDatos(TravelGt travelGt, String contenido, ManejoDatos manejoDatos, int param) {
        initComponents();
        setLocationRelativeTo(null);
        this.travelGt = travelGt;
        this.manejoDatos = manejoDatos;
        textArea.setText(contenido);
        this.param = param;

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/travel.jpeg")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        console = new javax.swing.JTextArea();
        buttonSeven1 = new org.edisoncor.gui.button.ButtonSeven();
        buttonSeven2 = new org.edisoncor.gui.button.ButtonSeven();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Validar datos");
        setBackground(new java.awt.Color(0, 51, 153));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jSplitPane1.setBackground(new java.awt.Color(102, 102, 102));
        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setDividerSize(3);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        jSplitPane1.setTopComponent(jScrollPane1);

        console.setColumns(20);
        console.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        console.setRows(5);
        jScrollPane2.setViewportView(console);

        jSplitPane1.setRightComponent(jScrollPane2);

        buttonSeven1.setBackground(new java.awt.Color(255, 0, 51));
        buttonSeven1.setText("Cancelar");
        buttonSeven1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeven1ActionPerformed(evt);
            }
        });

        buttonSeven2.setBackground(new java.awt.Color(102, 102, 102));
        buttonSeven2.setText("Cargar datos");
        buttonSeven2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeven2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSeven2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSeven1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSeven1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSeven2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        travelGt.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void buttonSeven1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeven1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        travelGt.setVisible(true);
    }//GEN-LAST:event_buttonSeven1ActionPerformed

    private void buttonSeven2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeven2ActionPerformed
        // TODO add your handling code here:
        if (!textArea.getText().isBlank()) {
            tipoProceso(param, textArea.getText());
            if (manejoDatos.getMensajesError().isEmpty()) {
                setVisible(false);
                travelGt.setVisible(true);
                travelGt.insertDataToGraph();
            } else {
                console.setBackground(Color.red);
                console.setText(manejoDatos.getMensaje());
                manejoDatos.reinicar();
            }
        }
    }//GEN-LAST:event_buttonSeven2ActionPerformed

    private void tipoProceso(int param, String content) {
        if (param == 1) {
            manejoDatos.processData(content);
        } else if (param == 2) {
            manejoDatos.processDataTrafico(content);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonSeven buttonSeven1;
    private org.edisoncor.gui.button.ButtonSeven buttonSeven2;
    private javax.swing.JTextArea console;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
