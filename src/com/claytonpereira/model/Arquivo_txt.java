/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.claytonpereira.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import jdk.nashorn.internal.ir.CatchNode;

/**
 *
 * @author claytonpereira
 */
public class Arquivo_txt {
   
    
    public Vector abrir_ler_arquivo_txt(File arquivo, JTextArea mensagem) throws FileNotFoundException, IOException,StringIndexOutOfBoundsException{
          Vector texto = new Vector(8,3); 
          
          mensagem.setText("Arquivo TXT selecionado : " + arquivo.getName() +  "..." + "\n" );
          mensagem.setEditable(false);
          mensagem.setVisible(true);
         
        try ( 
             FileInputStream fstream = new FileInputStream(arquivo)) {
            BufferedReader leitor = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
              
            while ((strLine = leitor.readLine()) != null) {
                 
               String livro_n =  strLine.substring(0, 6);
               String folha_n = strLine.substring(6, 11);
               String termo_obito_n = strLine.substring(11, 21);
               String data_lavr_certidao_obito = strLine.substring(21, 29);
               String benef_inss_n = strLine.substring(29, 39);
               String nome_falecido = strLine.substring(39, 115);
              // String nome_falecido_ajustado = nome_falecido.trim();
               String nome_mae_falecido = strLine.substring(115,147);
              // String nome_mae_falecido_ajustado = nome_mae_falecido.trim();
                       
               String data_Nascimento = strLine.substring(147, 155);
               String data_Obito = strLine.substring(155, 163);
               String cpf = strLine.substring(163, 174);
               String nit = strLine.substring(174, 185);
               String tipo_identifica_cartorio = strLine.substring(185, 186);
               String Id_cartorio = strLine.substring(186, 200);
              
        
                   String line = "";
                 line =  line +  livro_n + ";" + folha_n + ";" + termo_obito_n + ";"
                + data_lavr_certidao_obito + ";" + benef_inss_n + ";"
                + nome_falecido + ";" + nome_mae_falecido + ";"
                + data_Nascimento + ";"
                + data_Obito + ";" 
                + cpf + ";" + nit + ";" 
                + tipo_identifica_cartorio + ";"
                + Id_cartorio+ ";";
             
                 System.out.println(line);
              texto.add(line);  
              

                
            } 
            
   return texto;
        }
     
}
    public void conteudo_arquivo_csv_sisob(File arquivo,JTextArea mensagem) throws IOException{
       Vector conteudo = abrir_ler_arquivo_txt(arquivo,mensagem);
       int i = 0 ;
       while(i < conteudo.size()){
         System.out.print(conteudo.get(i) + "\n");
         i++;
    }}
    
    public void salvar_arquivo_txt_to_csv(File arquivo, String title, JTextArea mensagem) throws IOException{

        
     String data = "Test data";
     Vector conteudo = abrir_ler_arquivo_txt(arquivo,mensagem);
       int i = 0 ;
       
          JFileChooser chooser = new JFileChooser();
       //chooser.setCurrentDirectory(new java.io.File("."));
       chooser.setDialogTitle(title);
       
       chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       
      
       int CHECK = chooser.showSaveDialog(chooser); 
       
      
       
       FileFilter filter = new FileNameExtensionFilter("Arquivo CSV", "csv");
        chooser.addChoosableFileFilter(filter);  
        chooser.setAcceptAllFileFilterUsed(false);
           
            if((CHECK == JFileChooser.CANCEL_OPTION))
            {
                System.out.println("Não selecionou nenhum arquivo");
            JOptionPane.showMessageDialog(null,"Nenhum arquivo selecionado!");

            
            }  if((CHECK == JFileChooser.APPROVE_OPTION))
            {  // chooser.showSaveDialog(chooser);
                
                
                String arquivo_csv = arquivo.getName();
             String  arquivo_csv_ajustado =  arquivo_csv.substring(0,9);
                System.out.print("arquivo escolhido processado " +  arquivo_csv_ajustado + "\n" );
                FileOutputStream out =   new FileOutputStream( chooser.getCurrentDirectory() + "\\" + arquivo_csv_ajustado + ".csv");
                System.out.print("arquivo convertido com sucesso !, salvo no caminho = " + chooser.getCurrentDirectory() + "\\" + arquivo_csv_ajustado + ".csv" );     

       while(i < conteudo.size()){ 

        data = conteudo.get(i).toString() + "\n";
       out.write(data.getBytes());
           System.out.println("linha = " + i);
        i++;
        
       } 
     
     
       mensagem.append("Arquivo TXT convertido para csv "
               + "\n" + "e salvo em : " + chooser.getCurrentDirectory() + "\\" + arquivo_csv_ajustado + ".csv" + "..." + "\n");
          mensagem.setVisible(true);
      out.close();
               
        }
                
         
 
 
}
 public void export_csv_to_db(File arquivo, JTextArea mensagem) throws FileNotFoundException, SQLException, IOException{
     mensagem.repaint();
     mensagem.setVisible(true);         
     mensagem.setText("Importando o arquivo para o banco de dados ! ... " + "\n");
    
  
 String sql = "INSERT INTO registros_sisob (" +
     "livro_num," +
	"folha_num," +
	"termo_num," +
     "data_lavratura," +
     "beneficio_num," + 
     "nome_falecido," + 
     "nome_mae," +
      "DN_SISOBI," +
      "DO_SISOBI," +
          "cpf," +
     " NIT," + 
	"  Tipo_id_cartorio," +
	  "cartorio_id," +
           "DN_SISOBI_Ajustada,"+    
      "ANO_OBITO," +
     " MES_OBITO,"  +
      "DO_SISOBI_Ajustada," +
     " Nome_Arquivo_Importado) VALUES ("
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?,"
        + "?)";
 
 try {
 DatabaseConnectionMysql con_db = new DatabaseConnectionMysql();
 Connection  conexao =  con_db.database_connection();
  
  
   PreparedStatement statement = conexao.prepareStatement(sql);
   
 
            BufferedReader lineReader = new BufferedReader(new FileReader(arquivo));
            String lineText = null;
 
            int count = 0;
 
           // lineReader.readLine(); // skip header line
 
            while ((lineText = lineReader.readLine()) != null) {
                
                //String livro_n = lineText.split(";");
               
             
                String livro_n =  lineText.substring(0, 6);
               String folha_n = lineText.substring(7, 12);
               String termo_obito_n = lineText.substring(13, 23);
               
               String data_lavr_certidao_obito = lineText.substring(24, 32);
               
               String benef_inss_n = lineText.substring(33, 43);
               
               String nome_falecido = lineText.substring(44, 115);
               //String nome_falecido_ajustado = nome_falecido.trim();
               
               String nome_mae_falecido = lineText.substring(121,153);
               //String nome_mae_falecido_ajustado = nome_mae_falecido.trim();
               
               String data_Nascimento = lineText.substring(154, 162);
               
               String data_Obito = lineText.substring(163, 171);
               
               String cpf = lineText.substring(172, 183);
               String nit = lineText.substring(185, 195);
               
               String tipo_identifica_cartorio = lineText.substring(196, 197);
               String Id_cartorio = lineText.substring(198, 212);
               String Id_cartorio_ajustado = Id_cartorio.trim();
               
               
                System.out.println(
               " Resultado para o banco de dados !" + "\n" +      
                 livro_n + "\n"    
               + folha_n + "\n"
               + termo_obito_n + "\n"
               + data_lavr_certidao_obito + "\n"
               +  benef_inss_n + "\n"
              +  nome_falecido + "\n"
              +// nome_falecido_ajustado + "\n"
                 nome_mae_falecido + "\n"
              + //nome_mae_falecido_ajustado    + "\n"                 
                data_Nascimento + "\n"
               + data_Obito + "\n"
               + cpf + "\n"
               + nit + "\n"
               + tipo_identifica_cartorio + "\n"
               + Id_cartorio + "\n"
                        + Id_cartorio_ajustado);
               
               String DN_SISOBI_Ajustada = lineText.substring(154, 162);
               String nome_arquivo = arquivo.getName();
               String ANO_OBITO = nome_arquivo.substring(3,7 );             
               String MES_OBITO = nome_arquivo.substring(7,9 ) ;
               String DO_SISOBI_Ajustada = lineText.substring(163, 171);
               String Nome_Arquivo_Importado = nome_arquivo;
 
                statement.setString(1, livro_n);
                statement.setString(2, folha_n);
                statement.setString(3, termo_obito_n);
                statement.setString(4, data_lavr_certidao_obito);
                 statement.setString(5, benef_inss_n);
                statement.setString(6, nome_falecido);
                statement.setString(7, nome_mae_falecido);
                statement.setString(8, data_Nascimento);
                 statement.setString(9, data_Obito);
                statement.setString(10, cpf);
                statement.setString(11, nit);
                statement.setString(12, tipo_identifica_cartorio);
                 statement.setString(13, Id_cartorio);
                 
              
                 statement.setString(14, DN_SISOBI_Ajustada);
                statement.setString(15, ANO_OBITO);
                statement.setString(16, MES_OBITO);
                 statement.setString(17, DO_SISOBI_Ajustada);
                statement.setString(18, Nome_Arquivo_Importado);
 
                statement.addBatch();
     int batchSize=20;
 
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
 
            lineReader.close();
 
            // execute the remaining queries
            statement.executeBatch();
            
            conexao.commit();
              

            conexao.close();
           
 }catch(SQLException ex){
     
  mensagem.append("Erro sql ao salvar o arquivo" + arquivo.getName() + " MYSQL error codigo : " + ex.getErrorCode() );
 
 
 
 }
      
 
 
 
 }


}