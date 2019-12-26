DROP TABLE registros_sisob;
CREATE TABLE registros_sisob (

    livroNum VARCHAR(6),
	folhaNum VARCHAR(5),
	termoNum VARCHAR(10),
     dataLavratura DATE,
     beneficioNum BIGINT(10),
     nomeFalecido VARCHAR(76),
     nomeMae VARCHAR(32),
      DNSISOBI DATE,
      DOSISOBI DATE,
      cpf VARCHAR(13),
      NIT BIGINT,
	  TipoIdCartorio INT(1),
	  cartorioId BIGINT(14),      
      DNSISOBIAjustada VARCHAR(10),    
      ANOOBITO INT(4),
      MESOBITO INT(2),
      DOSISOBIAjustada VARCHAR(10),
      NomeArquivoImportado VARCHAR(255),
      PRIMARY KEY (livroNum,folhaNum,nomeFalecido,nomeMae,MESOBITO)
);


DROP TABLE registros;
CREATE TABLE NG39_registros (
    id int auto_increment,
    chave VARCHAR(15),
    tipovinc_ajustado VARCHAR(25),
    ref_folha TIMESTAMP,
    num_folha INT(2),
    tipovinc VARCHAR(20),
    nomeorgao VARCHAR(100),
    municipio VARCHAR(70),
    unidade INT(7),
    desc_unid VARCHAR(60),
    numfunc INT(7),
    numvinc INT(2),
    numpens INT,
    nome VARCHAR(255),
	cpf  VARCHAR(13) ,
    categoria VARCHAR(70),
    desc_cargo VARCHAR(255),
    desc_funcao VARCHAR(255),
    especialidade VARCHAR(80),
    referencia VARCHAR(8),
    jornada VARCHAR(3),
    DTEXERC DATE,
    DTVAC DATE,
    DTAPOSENT DATE,
    DTNASC DATE,
    DTNASC_AJUSTADA DATE,
    idade INT(3),
    faixa_idade VARCHAR(250),
    vl_bruto DECIMAL(13,4),
	faixa_remuneracao_bruta VARCHAR(255),
    vldesc DECIMAL(13,4),
    subsidio DECIMAL(13,4),
    tipoevento VARCHAR(255),
    formaprov VARCHAR(30),
    VALOR_AUX1 DECIMAL(13,4),
    VALOR_AUX2 DECIMAL(13,4),
    FLEX_CAMPO_01_sexo VARCHAR(2),   
    Nome_Arquivo_importado VARCHAR(255),
    PRIMARY KEY (id)
);