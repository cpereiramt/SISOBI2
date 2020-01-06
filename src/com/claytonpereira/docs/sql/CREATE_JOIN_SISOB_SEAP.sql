select  b.nome, b.numeroCPF, a.MESOBITO, a.ANOOBITO from registroETurmalina b,
        registrosSisob a where (b.nome = a.nome_falecido) 
and (a.MES_OBITO = 1) and (a.ANO_OBITO = 2019) ;

