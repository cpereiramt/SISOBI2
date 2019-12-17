/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.claytonpereira.view;

import com.claytonpereira.model.Arquivo_txt;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author claytonpereira
 */
public class TelaImportacaoSisobMensal extends javax.swing.JFrame {

    /**
     * Creates new form TelaImportacaoSisobMensal
     */
    public TelaImportacaoSisobMensal() {
        initComponents();
        setSize(800, 600);
        salve_csv_to_db.setVisible(false);
        Converte_txt_to_csv.setVisible(false);
         setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Converte_txt_to_csv = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        salve_csv_to_db = new javax.swing.JTextArea();

        getContentPane().setLayout(null);

        jButton1.setText("Converter TXT para CSV");
        jButton1.setToolTipText("O arquivo no formato = OBI + Ano com quatro digitos + mês com dois digitos exemplo: arquivo de janeiro de 2019, ficaria assim OBI201901 . ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(10, 10, 220, 23);

        jButton2.setText("Importar Arquivo CSV Para o Banco");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(10, 260, 220, 23);

        Converte_txt_to_csv.setColumns(20);
        Converte_txt_to_csv.setRows(5);
        jScrollPane1.setViewportView(Converte_txt_to_csv);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 40, 1280, 200);

        salve_csv_to_db.setEditable(false);
        salve_csv_to_db.setColumns(20);
        salve_csv_to_db.setRows(5);
        jScrollPane2.setViewportView(salve_csv_to_db);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 290, 1280, 180);
    }// </editor-fold>//GEN-END:initComponents

     public File seleciona_arquivo( java.awt.Component tela,String desc_tipo_arquivo, String extensao,JTextArea mensagem, String title) throws FileNotFoundException{
    File arquivo = null;
    int i;
    
    
           
        JFileChooser selecionaarquivo = new JFileChooser();
        selecionaarquivo.setDialogTitle(title);
        FileFilter filter = new FileNameExtensionFilter(desc_tipo_arquivo, extensao);
        selecionaarquivo.addChoosableFileFilter(filter);  
        selecionaarquivo.setAcceptAllFileFilterUsed(false);
        
        
        
        int returnVal = selecionaarquivo.showOpenDialog(tela);
        if (returnVal == JFileChooser.APPROVE_OPTION){
           
          arquivo = selecionaarquivo.getSelectedFile();
          System.out.println("abrindo: " + arquivo.getName());
        
          
        }
        
        
        if(returnVal == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(tela, "Nenhum arquivo selecionado !");
        
        }
        
        
        
        return arquivo;
    }
    
    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        Converte_txt_to_csv.setVisible(false);
        
        Thread abre_arquivo = new Thread(new Runnable() {
            @Override
            public void run() {
                File arquivo = null ;
        try {
            
            arquivo = seleciona_arquivo(TelaImportacaoSisobMensal.this,"arquivo Texto", "txt",Converte_txt_to_csv,"Selecione o arquivo txt do SISOB !");
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaImportacaoSisobMensal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           Arquivo_txt txt = new Arquivo_txt();
        try {
          
             
            try {
                txt.salvar_arquivo_txt_to_csv(arquivo,"Escolha o arquivo Csv",Converte_txt_to_csv);
            } catch (InterruptedException ex) {
                Logger.getLogger(TelaImportacaoSisobMensal.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        } catch (IOException ex) {
            Logger.getLogger(TelaImportacaoSisobMensal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            }
        });
        
        
        SwingUtilities.invokeLater(abre_arquivo);
           
      
        
     
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       salve_csv_to_db.setVisible(false);
        File arquivo = null ;
      
        try {
            
            arquivo = seleciona_arquivo(this,"Arquivo CSV", "csv",salve_csv_to_db,"Selecione o arquivo csv para importar !");
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaImportacaoSisobMensal.class.getName()).log(Level.SEVERE, null, ex);
        }
        Arquivo_txt texto = new Arquivo_txt();
        try {
           
           
            texto.export_csv_to_db(arquivo, salve_csv_to_db);
        } catch (SQLException ex) {
            Logger.getLogger(TelaImportacaoSisobMensal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TelaImportacaoSisobMensal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Converte_txt_to_csv;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea salve_csv_to_db;
    // End of variables declaration//GEN-END:variables
}
