select registrosSisob.NomeArquivoImportado as `Nome Do Arquivo`, 
count(*)   `Numero de Registros por arquivo` from registrosSisob
 group by registrosSisob.NomeArquivoImportado;