/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.claytonpereira.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Vector;

/**
 *
 * @author claytonpereira
 */
public class Arquivo_txt {
   
    
    public Vector abrir_ler_arquivo() throws FileNotFoundException, IOException{
          Vector texto = new Vector(8,3); 
        try ( 
             FileInputStream fstream = new FileInputStream("C:\\Users\\claytonpereira\\Documents\\GitHub\\SISOBI2\\src\\com\\claytonpereira\\model\\OBI201901.txt")) {
            BufferedReader leitor = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
              
            while ((strLine = leitor.readLine()) != null) {
                  
               String livro_n =  strLine.substring(0, 6);
               String folha_n = strLine.substring(6, 11);
               String termo_obito_n = strLine.substring(11, 21);
               String data_lavr_certidao_obito = strLine.substring(21, 29);
               String benef_inss_n = strLine.substring(29, 39);
               String nome_falecido = strLine.substring(39, 115);
               String nome_falecido_ajustado = nome_falecido.trim();
               String nome_mae_falecido = strLine.substring(115,147);
               String nome_mae_falecido_ajustado = nome_mae_falecido.trim();
                       
               String data_Nascimento = strLine.substring(147, 155);
               String data_Obito = strLine.substring(155, 163);
               String cpf = strLine.substring(163, 174);
               String nit = strLine.substring(174, 185);
               String tipo_identifica_cartorio = strLine.substring(185, 186);
               String Id_cartorio = strLine.substring(186, 210);
              String Id_cartorio_ajustado = Id_cartorio.trim() ;
        
                   String line = "";
                 line =  line +  livro_n + ";" + folha_n + ";" + termo_obito_n + ";"
                + data_lavr_certidao_obito + ";" + benef_inss_n + ";"
                + nome_falecido_ajustado + ";" + nome_mae_falecido_ajustado + ";"
                + data_Nascimento + ";"
                + data_Obito + ";" 
                + cpf + ";" + nit + ";" 
                + tipo_identifica_cartorio + ";"
                + Id_cartorio_ajustado + ";";
             
                 
              texto.add(line);  
              

                
            } 
            
   return texto;
        }
     
}
    public void conteudo_arquivo_csv_sisob() throws IOException{
       Vector conteudo = abrir_ler_arquivo();
       int i = 0 ;
       while(i < conteudo.size()){
         System.out.print(conteudo.get(i) + "\n");
         i++;
    }}
 
}