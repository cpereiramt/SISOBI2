select  b.nome, b.cpf, a.MES_OBITO, a.ANO_OBITO from NG39_registros b, registros a where (b.nome = a.nome_falecido) 
and (a.MES_OBITO = 1) and (a.ANO_OBITO = 2019) ;

