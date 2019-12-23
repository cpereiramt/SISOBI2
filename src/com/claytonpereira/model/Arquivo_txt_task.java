/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.claytonpereira.model;

import com.claytonpereira.view.TelaImportacaoSisobMensal;
import static com.claytonpereira.view.TelaImportacaoSisobMensal.Converte_txt_to_csv;
import static com.claytonpereira.view.TelaImportacaoSisobMensal.arquivo;
import java.awt.TextArea;
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
import javafx.concurrent.Worker;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author claytonpereira
 */
public class Arquivo_txt_task {

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
    int linhas = 0;
    Boolean escolheu_arquivo = null ;
    public void thread_format_text() {

        SwingWorker<Void, String> work = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
              List<String> texto = format_txt_to_csv(arquivo);
                int i = 0;
                while (i < texto.size()) {
                   
                    publish(texto.get(i));
                    i++;
                }
                return null;
            }

            @Override
            protected void process(List<String> pairs) {
                //TelaImportacaoSisobMensal.Converte_txt_to_csv.setText(" ");
                
                TelaImportacaoSisobMensal.JL_Converter_txt.setText("Convertendo arquivo: " + arquivo.getName());
               
                 linhas = 1; 
                TelaImportacaoSisobMensal.JL_Converter_txt.setVisible(true);
                for (String texto : pairs) {         
                   
                     TelaImportacaoSisobMensal.JL_Converter_txt.setText("Linha : " + linhas + " de " +  pairs.size() + " convertido");
                       TelaImportacaoSisobMensal.Converte_txt_to_csv.append( "========================================================================================================================================================================================================================= " + "\n");
                    TelaImportacaoSisobMensal.Converte_txt_to_csv.append( "linha - " + linhas + " ||  " + texto + "\n");

                    linhas++;
                }

            }

            @Override
            protected void done() {

                thread_import_text();
            }
        };
        work.execute();

    }

    public void thread_import_text() {

        SwingWorker<Void, String> work2 = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {

              escolheu_arquivo   = salvar_arquivo_txt_to_csv(arquivo, "Escolha o diretório para salvar o csv", TelaImportacaoSisobMensal.Converte_txt_to_csv);
                return null;
            }

            @Override
            protected void done() {
                if(escolheu_arquivo){
                JOptionPane.showMessageDialog(null, "arquivo convertido com sucesso !");
               }else{
                
                JOptionPane.showMessageDialog(null,"Erro na importação ou nenhum diretório selecionado \n para "
                        + "salvar o arquivo ");
                
                }

            
            }
        };

        work2.execute();

    }
    
    
    
    public void thread_salva_csv_db() {

        SwingWorker<Void, String> work = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                TelaImportacaoSisobMensal tela = new TelaImportacaoSisobMensal();
                List<String> texto = export_csv_to_db(tela,TelaImportacaoSisobMensal.salve_csv_to_db);
                int i=0;
                while(i < texto.size()){
                publish(texto.get(i));
                
                }
                
                return null;
            }
            @Override
            protected void process(List<String> pairs){
            TelaImportacaoSisobMensal.salve_csv_to_db.setVisible(true);
            
            for(String texto : pairs){
                TelaImportacaoSisobMensal.salve_csv_to_db.setText("Teste de inserção ....");
                
                
                
            
            }
            
            
            
            }
           

        
        };
        work.execute();

    }


    public Vector format_txt_to_csv(File arquivo) throws FileNotFoundException, IOException, StringIndexOutOfBoundsException, InterruptedException {
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

    public Boolean salvar_arquivo_txt_to_csv(File arquivo, String title, JTextArea mensagem) throws IOException, InterruptedException {
        //Arquivo_txt_task texto = new Arquivo_txt_task();
        Boolean result= false;
       
        try {
            conteudo = format_txt_to_csv(arquivo);
        } catch (IOException ex) {
            Logger.getLogger(Arquivo_txt_task.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StringIndexOutOfBoundsException ex) {
            Logger.getLogger(Arquivo_txt_task.class.getName()).log(Level.SEVERE, null, ex);
        }

        chooser.setDialogTitle(title);

        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //chooser.getCurrentDirectory()

        int CHECK = chooser.showSaveDialog(chooser);

        FileFilter filter = new FileNameExtensionFilter("Arquivo CSV", "csv");
        chooser.addChoosableFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);

        if ((CHECK == JFileChooser.CANCEL_OPTION)) {
            //System.out.println("Não selecionou nenhum arquivo");
            JOptionPane.showMessageDialog(null, "Nenhum diretório selecionado!");
           result = false;
        }

        if ((CHECK == JFileChooser.APPROVE_OPTION)) {

            arquivo_csv = arquivo.getName();
            arquivo_csv_ajustado = arquivo_csv.substring(0, 9);
            System.out.print("arquivo escolhido processado " + arquivo_csv_ajustado + "\n");
                 
            try {

                out = new FileOutputStream(chooser.getSelectedFile()+ "\\" + arquivo_csv_ajustado + ".csv");
                System.out.print("retorno do arquivo" + chooser.getSelectedFile()+ "\\" + arquivo_csv_ajustado + ".csv");
                result =  true;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Arquivo_txt_task.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        int i = 0;
        //out = new FileOutputStream(chooser.getCurrentDirectory() + "\\" + arquivo_csv_ajustado + ".csv");
        while (i < conteudo.size()) {
            data = conteudo.get(i).toString() + "\n";
            //mensagem.append("Linha = " + i + "  | " + data);
            //System.out.print("arquivo convertido com sucesso !, salvo no caminho = " + chooser.getCurrentDirectory() + "\\" + arquivo_csv_ajustado + ".csv");

            out.write(data.getBytes());

            i++;

        }

        out.close();
        return result;
       
    }

  public Vector export_csv_to_db(java.awt.Component tela, JTextArea mensagem) throws FileNotFoundException, SQLException, IOException {
              Vector texto = new Vector(8, 3);
              TelaImportacaoSisobMensal telaSisob = new TelaImportacaoSisobMensal();
                             File arquivo_escolhido = telaSisob.seleciona_arquivo(tela,"arquivo CSV", "csv",TelaImportacaoSisobMensal.salve_csv_to_db,"Selecione o arquivo CSV para ser importado no banco de dados !");
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
     statement.setQueryTimeout(0);
            BufferedReader lineReader = new BufferedReader(new FileReader(arquivo_escolhido));
            String lineText = null;

            int count = 0;
            int registro = 1;
              TelaImportacaoSisobMensal.salve_csv_to_db.setVisible(true);
            // lineReader.readLine(); // skip header line
           // mensagem.append("\n" + "Inserindo registros no banco !");
            while ((lineText = lineReader.readLine()) != null) {
              
               //mensagem.append("\n" + "=============================================================================================================" + "\n");
                //String livro_n = lineText.split(";");
              System.out.print( registro + " = " + "processando linhas ! \n");
              TelaImportacaoSisobMensal.salve_csv_to_db.append(registro + " = " + "processando linhas ! \n");
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
               // String Id_cartorio_ajustado = Id_cartorio.trim();
                String DN_SISOBI_Ajustada = lineText.substring(154, 162);
                String nome_arquivo = arquivo_escolhido.getName();
               String ANO_OBITO = nome_arquivo.substring(3, 7);
                String MES_OBITO = nome_arquivo.substring(7, 9);
                  
                String DO_SISOBI_Ajustada = lineText.substring(163, 171);
                
                String Nome_Arquivo_Importado = nome_arquivo;
                 
               
             

           texto.add("" + "\n"
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

//                if (count % batchSize == 0) {
//                   statement.executeBatch();
//                }

                registro++;
            }

            lineReader.close();

            // execute the remaining queries
            statement.executeBatch();

           conexao.commit();

            conexao.close();

        } catch (SQLException ex) {
                        System.out.print("\n" + "\n" + "Erro sql ao importar o arquivo " + arquivo.getName()
                   + "\n" + "MYSQL error codigo : " + ex.getErrorCode() + " " + ex.getMessage());
//           mensagem.append("\n" + "\n" + "Erro sql ao importar o arquivo " + arquivo.getName()
//                    + "\n" + "MYSQL error codigo : " + ex.getErrorCode() + " " + ex.getMessage());
//
//        }

    }
        return texto;
}}
