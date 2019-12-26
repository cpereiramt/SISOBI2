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



public class DatabaseConnectionMysql{  
    
    Connection con;
    ResultSet rs;
public Connection database_connection(){
try{  
  Class.forName("com.mysql.jdbc.Driver");  
  con =DriverManager.getConnection(  
  "jdbc:mysql://localhost:3306/sisobi","root","root");  
 //here sisobi is database name, root is username and password  
   con.setAutoCommit(false);
 }catch(Exception e){ System.out.println(e);}  
  
return  con;
}


public java.sql.ResultSet consulta_db(){
try{
    
 database_connection(); 
 Statement stmt = con.createStatement();  
 rs = stmt.executeQuery("select registros_sisob.NomeArquivoImportado, "
         + "count(*) numero_registros from registros_sisob\n" +
" group by registros_sisob.NomeArquivoImportado");  
//while(rs.next())  {
////System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
//// con.close(); } 
//
}catch(Exception e){ System.out.println(e);}  
        return rs;
}  


}  


 