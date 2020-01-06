/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.claytonpereira.model;
/**
 *
 * @author claytonpereira
 */
import java.sql.*;  
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class DatabaseConnectionMysql{  
    
    Connection con;
    ResultSet rs;
public Connection database_connection(){
try{  
  Class.forName("com.mysql.jdbc.Driver");  
  con =DriverManager.getConnection(  
  "jdbc:mysql://localhost:3306/sisobi","root","root");  
 //here sisobi is database name, root is username and password  
   //con.setAutoCommit(false);
 }catch(Exception e){ System.out.println(e);}  
  
return  con;
}


public java.sql.ResultSet count_registros_por_arquivo(){
try{
    
 database_connection(); 
 Statement stmt = con.createStatement();  
 rs = stmt.executeQuery("select registrosSisob.NomeArquivoImportado as `Nome Do Arquivo`, count(*)   `Numero de Registros por arquivo` from registrosSisob\n" +
" group by registrosSisob.NomeArquivoImportado");  
//while(rs.next())  {
////System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
//// con.close(); } 
//
}catch(Exception e){ System.out.println(e);}  
        return rs;
}  


public java.sql.ResultSet join_registro_sisob_eturmalina(int MES, int ANO) throws SQLException{
    
    System.out.println("select  b.nome, b.numeroCPF, a.MESOBITO, a.ANOOBITO from registrosSisob a \n" +
"       inner join  registroETurmalina b on  b.numeroCPF = a.cpf \n" +
"       where  b.nome = a.nomeFalecido\n" +
"       and a.ANOOBITO = " +  "\"" + ANO + "\" \n" +
"       and a.MESOBITO = " +  "\"" + MES + "\"; ");
    
    database_connection();
    Statement stmt = con.createStatement();
    rs = stmt.executeQuery("select  b.nome, b.numeroCPF, a.MESOBITO, a.ANOOBITO from registrosSisob a \n" +
"       inner join  registroETurmalina b on  b.numeroCPF = a.cpf \n" +
"       where  b.nome = a.nomeFalecido\n" +
"       and a.ANOOBITO = " +  "\"" + ANO + "\" \n" +
"       and a.MESOBITO = " +  "\"" + MES + "\"; ");

    
    
     return rs;




}

}  


 