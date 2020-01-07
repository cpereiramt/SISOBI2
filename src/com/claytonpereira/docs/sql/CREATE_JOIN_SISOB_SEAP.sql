select  b.nome, b.numeroCPF, a.MESOBITO, a.ANOOBITO from registrosSisob a 
       inner join  registroETurmalina b on  b.numeroCPF = a.cpf 
       where  b.nome = a.nomeFalecido
       and a.ANOOBITO = "2018" 
       and a.MESOBITO ="2"; 
            
select registrosSisob.NomeArquivoImportado as `Nome Do Arquivo`, 
count(*)   `Numero de Registros por arquivo` from registrosSisob
 group by registrosSisob.NomeArquivoImportado;


select  b.nome, b.numeroCPF, a.MESOBITO, a.ANOOBITO from registrosSisob a 
       inner join  registroETurmalina b on  b.numeroCPF = a.cpf 
       where  b.nome = a.nomeFalecido
       and a.ANOOBITO = " + 2019" 
       and a.MESOBITO = " + 1"; 
