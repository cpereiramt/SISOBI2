select  b.nome, b.numeroCPF, a.MESOBITO, a.ANOOBITO from registrosSisob a 
       inner join  registroETurmalina b on  b.numeroCPF = a.cpf 
       where  b.nome = a.nomeFalecido
       and a.ANOOBITO = "2019" 
       and a.MESOBITO ="1"; 
            



select  b.nome, b.numeroCPF, a.MESOBITO, a.ANOOBITO from registrosSisob a 
       inner join  registroETurmalina b on  b.numeroCPF = a.cpf 
       where  b.nome = a.nomeFalecido
       and a.ANOOBITO = " + 2019" 
       and a.MESOBITO = " + 1"; 
