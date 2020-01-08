/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.claytonpereira.model;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * Classe responsável pelas conexões do banco de dados bem como com <br> o gerenciamento das consultas e inserts no banco . 
 * @author claytonpereira
 */
public class DatabaseConnectionMysql {

    Connection con;
    ResultSet rs;
   /** Ajusta o banco de dados (mysql, oracle) e cria a conexão <br> com o db passando o endereço, login e a senha . 
    * @return retorna um objeto do tipo Connection com as configurações ajustada para conexão no banco de dados .
    * 
    */
    public Connection database_connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sisobi", "root", "root");
        } catch (Exception e) {
            System.out.println(e);
        }

        return con;
    }

    
    /** consulta que retorna os registros do Sisob agrupados por arquivo <br> de onde foram importados .
     * @return retorna um objeto ResultSet com os resultados da consulta executada .
     */
    public java.sql.ResultSet count_registros_por_arquivo() {
        try {

            database_connection();
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("select registrosSisob.NomeArquivoImportado as `Nome Do Arquivo`, count(*)   `Numero de Registros por arquivo` from registrosSisob\n"
                    + " group by registrosSisob.NomeArquivoImportado");
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

     /** consulta que retorna os registros do Sisob comparados com a base de dados do sistema E-TURMALINA.
      * @param MES, parametro mes no formato de um digito 
      * @param ANO, parametro ano no formato de quatro digitos 
      * @throws SQLException
      * @return retorna um objeto ResultSet com os resultados da consulta executada .
     */
    public java.sql.ResultSet join_registro_sisob_eturmalina(int MES, int ANO) throws SQLException {

        System.out.println("select  b.nome, b.numeroCPF, a.MESOBITO, a.ANOOBITO from registrosSisob a \n"
                + "       inner join  registroETurmalina b on  b.numeroCPF = a.cpf \n"
                + "       where  b.nome = a.nomeFalecido\n"
                + "       and a.ANOOBITO = " + "\"" + ANO + "\" \n"
                + "       and a.MESOBITO = " + "\"" + MES + "\"; ");

        database_connection();
        Statement stmt = con.createStatement();
        rs = stmt.executeQuery("select  b.nome, b.numeroCPF, a.MESOBITO, a.ANOOBITO from registrosSisob a \n"
                + "       inner join  registroETurmalina b on  b.numeroCPF = a.cpf \n"
                + "       where  b.nome = a.nomeFalecido\n"
                + "       and a.ANOOBITO = " + "\"" + ANO + "\" \n"
                + "       and a.MESOBITO = " + "\"" + MES + "\"; ");
        return rs;

    }

}
