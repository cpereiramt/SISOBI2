/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.claytonpereira.view;

import com.claytonpereira.model.Arquivo_txt_task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.TextArea;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author claytonpereira
 */
public class TelaImportacaoSisobMensal extends javax.swing.JFrame {
public static JFileChooser selecionaarquivo; 
public static   File arquivo;
    /**
     * Creates new form TelaImportacaoSisobMensal
     */
    public TelaImportacaoSisobMensal() {
        initComponents();
        setSize(800, 600);
        salve_csv_to_db.setVisible(false);
        Converte_txt_to_csv.setVisible(false);
         setExtendedState(MAXIMIZED_BOTH);
         JL_Converter_txt.setVisible(false);
         JL_Importar_CSV.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JB_Converte_txt = new javax.swing.JButton();
        JB_Importa_csv = new javax.swing.JButton();
        JL_Converter_txt = new javax.swing.JLabel();
        JL_Importar_CSV = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Converte_txt_to_csv = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        salve_csv_to_db = new javax.swing.JTextArea();

        getContentPane().setLayout(null);

        JB_Converte_txt.setText("Converter TXT para CSV");
        JB_Converte_txt.setToolTipText("O arquivo no formato = OBI + Ano com quatro digitos + mês com dois digitos exemplo: arquivo de janeiro de 2019, ficaria assim OBI201901 . ");
        JB_Converte_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Converte_txtActionPerformed(evt);
            }
        });
        getContentPane().add(JB_Converte_txt);
        JB_Converte_txt.setBounds(10, 10, 220, 23);

        JB_Importa_csv.setText("Importar Arquivo CSV Para o Banco");
        JB_Importa_csv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Importa_csvActionPerformed(evt);
            }
        });
        getContentPane().add(JB_Importa_csv);
        JB_Importa_csv.setBounds(10, 260, 220, 23);

        JL_Converter_txt.setText("jLabel1");
        getContentPane().add(JL_Converter_txt);
        JL_Converter_txt.setBounds(240, 10, 650, 30);

        JL_Importar_CSV.setText("jLabel2");
        getContentPane().add(JL_Importar_CSV);
        JL_Importar_CSV.setBounds(240, 260, 600, 20);

        Converte_txt_to_csv.setColumns(20);
        Converte_txt_to_csv.setRows(5);
        jScrollPane1.setViewportView(Converte_txt_to_csv);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 50, 1280, 200);

        salve_csv_to_db.setColumns(20);
        salve_csv_to_db.setRows(5);
        jScrollPane2.setViewportView(salve_csv_to_db);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 290, 1280, 210);
    }// </editor-fold>//GEN-END:initComponents

     public File seleciona_arquivo( java.awt.Component tela,String desc_tipo_arquivo, String extensao,JTextArea mensagem, String title) throws FileNotFoundException{
      File arquivo = null;
    
    
    
           
        selecionaarquivo  = new JFileChooser();
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
    
    
    
    
    private void JB_Converte_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Converte_txtActionPerformed
     
               Converte_txt_to_csv.setVisible(false);
       
                //arquivo = null ;
                try {
                    arquivo = seleciona_arquivo(TelaImportacaoSisobMensal.this,"arquivo Texto", "txt",Converte_txt_to_csv,"Selecione o arquivo txt do SISOB !");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TelaImportacaoSisobMensal.class.getName()).log(Level.SEVERE, null, ex);
                }
                Arquivo_txt_task txt = new Arquivo_txt_task();
                txt.thread_format_text();
           
       
        
        
//            try {
//             
//            } catch (InterruptedException ex) {
//                Logger.getLogger(TelaImportacaoSisobMensal.class.getName()).log(Level.SEVERE, null, ex);
//            }
//           
//        } catch (IOException ex) {
//            Logger.getLogger(TelaImportacaoSisobMensal.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       
//                
   

          
      
        
    
        
        
    }//GEN-LAST:event_JB_Converte_txtActionPerformed

    private void JB_Importa_csvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Importa_csvActionPerformed
        // TODO add your handling code here:
       salve_csv_to_db.setVisible(false);
        File arquivo = null ;
      
       // try {
            
          //  arquivo = seleciona_arquivo(this,"Arquivo CSV", "csv",salve_csv_to_db,"Selecione o arquivo csv para importar !");
//           
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TelaImportacaoSisobMensal.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Arquivo_txt_task texto = new Arquivo_txt_task();
      //  try {
           
           
//            texto.export_csv_to_db(arquivo, salve_csv_to_db);
//        } catch (SQLException ex) {
//            Logger.getLogger(TelaImportacaoSisobMensal.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(TelaImportacaoSisobMensal.class.getName()).log(Level.SEVERE, null, ex);

    }//GEN-LAST:event_JB_Importa_csvActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea Converte_txt_to_csv;
    public static javax.swing.JButton JB_Converte_txt;
    public static javax.swing.JButton JB_Importa_csv;
    public static javax.swing.JLabel JL_Converter_txt;
    public static javax.swing.JLabel JL_Importar_CSV;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTextArea salve_csv_to_db;
    // End of variables declaration//GEN-END:variables
}
