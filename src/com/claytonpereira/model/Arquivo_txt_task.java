/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.claytonpereira.model;

import com.claytonpereira.view.TelaImportacaoSisobMensal;
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
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author claytonpereira
 */
public class Arquivo_txt_task  extends javax.swing.SwingWorker<Void,String>{

    static Vector conteudo;
    String data;
    volatile static FileOutputStream out = null;
    JFileChooser chooser = new JFileChooser();
    String arquivo_csv;
    String arquivo_csv_ajustado;
    static JTextArea mensagem;
    JFileChooser selecionaarquivo = TelaImportacaoSisobMensal.selecionaarquivo;
    JTextArea converte_txt_to_csv = TelaImportacaoSisobMensal.Converte_txt_to_csv;
    File arquivo = TelaImportacaoSisobMensal.arquivo;
    
        @Override
    protected Void doInBackground() throws Exception {
           List<String> texto = format_txt_to_csv(arquivo);
           int i = 0;
           while(i<texto.size()){
           publish(texto.get(i));
         
           
           i++;
           }
           
           
        return null;
    }
    
    @Override
    protected void process(List<String> pairs) {
       
        for(int i = 0 ; i < pairs.size() -1 ; i++){
            
            TelaImportacaoSisobMensal.Converte_txt_to_csv.setVisible(true);
            TelaImportacaoSisobMensal.Converte_txt_to_csv.append(pairs.get(i) + "\n");
            TelaImportacaoSisobMensal.Converte_txt_to_csv.updateUI();
             System.out.print("\n" +" rodando dentro de process ........ " + pairs.get(i)+  "==" + i + "\n");
              
        }
       
        
        
        
    }
    
    public Vector format_txt_to_csv(File arquivo) throws FileNotFoundException, IOException, StringIndexOutOfBoundsException {
         Vector texto = new Vector(8, 3); 
      
try (
            FileInputStream fstream = new FileInputStream(arquivo)) {
            BufferedReader leitor = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            while ((strLine = leitor.readLine()) != null) {

                String livro_n = strLine.substring(0, 6);
                String folha_n = strLine.substring(6, 11);
                String termo_obito_n = strLine.substring(11, 21);
                String data_lavr_certidao_obito = strLine.substring(21, 29);
                String benef_inss_n = strLine.substring(29, 39);
                String nome_falecido = strLine.substring(39, 115);
                // String nome_falecido_ajustado = nome_falecido.trim();
                String nome_mae_falecido = strLine.substring(115, 147);
                // String nome_mae_falecido_ajustado = nome_mae_falecido.trim();

                String data_Nascimento = strLine.substring(147, 155);
                String data_Obito = strLine.substring(155, 163);
                String cpf = strLine.substring(163, 174);
                String nit = strLine.substring(174, 185);
                String tipo_identifica_cartorio = strLine.substring(185, 186);
                String Id_cartorio = strLine.substring(186, 200);

                String line = "";
                line = line + livro_n + ";" + folha_n + ";" + termo_obito_n + ";"
                        + data_lavr_certidao_obito + ";" + benef_inss_n + ";"
                        + nome_falecido + ";" + nome_mae_falecido + ";"
                        + data_Nascimento + ";"
                        + data_Obito + ";"
                        + cpf + ";" + nit + ";"
                        + tipo_identifica_cartorio + ";"
                        + Id_cartorio + ";";

                        texto.add(line);

            }

            return texto;
        }

    }

    public void salvar_arquivo_txt_to_csv(File arquivo, String title, JTextArea mensagem) throws IOException, InterruptedException {
                    Arquivo_txt_task texto = new Arquivo_txt_task();
                    
                    
               
                        try {
                            conteudo = format_txt_to_csv(arquivo);
                        } catch (IOException ex) {
                            Logger.getLogger(Arquivo_txt_task.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (StringIndexOutOfBoundsException ex) {
                            Logger.getLogger(Arquivo_txt_task.class.getName()).log(Level.SEVERE, null, ex);
                        }
                 
                chooser.setDialogTitle(title);

                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int CHECK = chooser.showSaveDialog(chooser);

                FileFilter filter = new FileNameExtensionFilter("Arquivo CSV", "csv");
                chooser.addChoosableFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);

                if ((CHECK == JFileChooser.CANCEL_OPTION)) {
                    System.out.println("Não selecionou nenhum arquivo");
                    JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado!");

                }

                if ((CHECK == JFileChooser.APPROVE_OPTION)) {             

                    arquivo_csv = arquivo.getName();
                    arquivo_csv_ajustado = arquivo_csv.substring(0, 9);
                    System.out.print("arquivo escolhido processado " + arquivo_csv_ajustado + "\n");

                    try {

                        out = new FileOutputStream(chooser.getCurrentDirectory() + "\\" + arquivo_csv_ajustado + ".csv");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Arquivo_txt_task.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     
                        }

                            int i = 0;
                            while (i < conteudo.size()) {
  {            
                     data = conteudo.get(i).toString() + "\n";
                     mensagem.append("Linha = " + i + "  | " + data);
                    System.out.print("arquivo convertido com sucesso !, salvo no caminho = " + chooser.getCurrentDirectory() + "\\" + arquivo_csv_ajustado + ".csv");
try {
                        out = new FileOutputStream(chooser.getCurrentDirectory() + "\\" + arquivo_csv_ajustado + ".csv");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Arquivo_txt_task.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                       out.write(data.getBytes());
                    } catch (IOException ex) {
                        Logger.getLogger(Arquivo_txt_task.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("linha = " + i);
                    
                    i++;

                   
                    try {
                        out.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Arquivo_txt_task.class.getName()).log(Level.SEVERE, null, ex);
                    }
}

 }  
                    }
              
                

                
        

    

    public void export_csv_to_db(File arquivo, JTextArea mensagem) throws FileNotFoundException, SQLException, IOException {

        mensagem.repaint();
        mensagem.setVisible(true);
        mensagem.setText("Importando o arquivo para o banco de dados ! ... " + "\n" + "\n");

        String sql = "INSERT INTO registros_sisob ("
                + "livro_num,"
                + "folha_num,"
                + "termo_num,"
                + "data_lavratura,"
                + "beneficio_num,"
                + "nome_falecido,"
                + "nome_mae,"
                + "DN_SISOBI,"
                + "DO_SISOBI,"
                + "cpf,"
                + " NIT,"
                + "  Tipo_id_cartorio,"
                + "cartorio_id,"
                + "DN_SISOBI_Ajustada,"
                + "ANO_OBITO,"
                + " MES_OBITO,"
                + "DO_SISOBI_Ajustada,"
                + " Nome_Arquivo_Importado) VALUES ("
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
            Connection conexao = con_db.database_connection();

            PreparedStatement statement = conexao.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(arquivo));
            String lineText = null;

            int count = 0;
            int registro = 1;

            // lineReader.readLine(); // skip header line
            mensagem.append("\n" + "Inserindo registros no banco !");
            while ((lineText = lineReader.readLine()) != null) {
                mensagem.append("\n" + "=============================================================================================================" + "\n");
                //String livro_n = lineText.split(";");

                String livro_n = lineText.substring(0, 6);
                String folha_n = lineText.substring(7, 12);
                String termo_obito_n = lineText.substring(13, 23);
                String data_lavr_certidao_obito = lineText.substring(24, 32);
                String benef_inss_n = lineText.substring(33, 43);
                String nome_falecido = lineText.substring(44, 115);
                //String nome_falecido_ajustado = nome_falecido.trim();               
                String nome_mae_falecido = lineText.substring(121, 153);
                //String nome_mae_falecido_ajustado = nome_mae_falecido.trim();               
                String data_Nascimento = lineText.substring(154, 162);
                String data_Obito = lineText.substring(163, 171);
                String cpf = lineText.substring(172, 183);
                String nit = lineText.substring(185, 195);
                String tipo_identifica_cartorio = lineText.substring(196, 197);
                String Id_cartorio = lineText.substring(198, 212);
                String Id_cartorio_ajustado = Id_cartorio.trim();
                String DN_SISOBI_Ajustada = lineText.substring(154, 162);
                String nome_arquivo = arquivo.getName();
                String ANO_OBITO = nome_arquivo.substring(3, 7);
                String MES_OBITO = nome_arquivo.substring(7, 9);
                String DO_SISOBI_Ajustada = lineText.substring(163, 171);
                String Nome_Arquivo_Importado = nome_arquivo;

                mensagem.append("\n" + "Registro n º" + registro + "\n");

                mensagem.append("" + "\n"
                        + livro_n + "\n"
                        + folha_n + "\n"
                        + termo_obito_n + "\n"
                        + data_lavr_certidao_obito + "\n"
                        + benef_inss_n + "\n"
                        + nome_falecido + "\n"
                        +// nome_falecido_ajustado + "\n"
                        nome_mae_falecido + "\n"
                        + //nome_mae_falecido_ajustado    + "\n"                 
                        data_Nascimento + "\n"
                        + data_Obito + "\n"
                        + cpf + "\n"
                        + nit + "\n"
                        + tipo_identifica_cartorio + "\n"
                        + Id_cartorio + "\n"
                        + DN_SISOBI_Ajustada + "\n"
                        + ANO_OBITO + "\n "
                        + MES_OBITO + "\n"
                        + DO_SISOBI_Ajustada + "\n"
                        + Nome_Arquivo_Importado);

                System.out.println(
                        " Resultado para o banco de dados !" + "\n"
                        + livro_n + "\n"
                        + folha_n + "\n"
                        + termo_obito_n + "\n"
                        + data_lavr_certidao_obito + "\n"
                        + benef_inss_n + "\n"
                        + nome_falecido + "\n"
                        +// nome_falecido_ajustado + "\n"
                        nome_mae_falecido + "\n"
                        + //nome_mae_falecido_ajustado    + "\n"                 
                        data_Nascimento + "\n"
                        + data_Obito + "\n"
                        + cpf + "\n"
                        + nit + "\n"
                        + tipo_identifica_cartorio + "\n"
                        + Id_cartorio + "\n"
                        + DN_SISOBI_Ajustada + "\n"
                        + ANO_OBITO + "\n "
                        + MES_OBITO + "\n"
                        + DO_SISOBI_Ajustada + "\n"
                        + Nome_Arquivo_Importado);

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
                int batchSize = 20;

                if (count % batchSize == 0) {
                    statement.executeBatch();
                }

                registro++;
            }

            lineReader.close();

            // execute the remaining queries
            statement.executeBatch();

            conexao.commit();

            conexao.close();

        } catch (SQLException ex) {

            mensagem.append("\n" + "\n" + "Erro sql ao importar o arquivo " + arquivo.getName()
                    + "\n" + "MYSQL error codigo : " + ex.getErrorCode() + " " + ex.getMessage());

        }

    }



}
