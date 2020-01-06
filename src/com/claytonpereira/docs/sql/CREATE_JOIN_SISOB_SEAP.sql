select  b.nome, b.numeroCPF, a.MESOBITO, a.ANOOBITO from registroETurmalina b
       inner join  registrosSisob a on  b.numeroCPF = a.cpf 
       where  b.nome = a.nomeFalecido
       and a.ANOOBITO = "2019" 
       and a.MESOBITO ="1"; 
            
select registrosSisob.NomeArquivoImportado as `Nome Do Arquivo`, 
count(*)   `Numero de Registros por arquivo` from registrosSisob
 group by registrosSisob.NomeArquivoImportado;

