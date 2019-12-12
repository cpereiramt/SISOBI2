/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.claytonpereira.controller;
import com.claytonpereira.model.Arquivo_txt;
import java.io.IOException;
/**
 *
 * @author claytonpereira
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Arquivo_txt arquivo = new Arquivo_txt();
        arquivo.conteudo_arquivo_csv_sisob();
    }
    
}
