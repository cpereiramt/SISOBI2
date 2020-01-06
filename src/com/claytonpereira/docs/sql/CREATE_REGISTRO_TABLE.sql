
CREATE DATABASE SISOB;
DROP TABLE registrosSisob;
CREATE TABLE registrosSisob (

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
CREATE TABLE registrosSeap (
    id int auto_increment,
    chave VARCHAR(15),
    tipovincajustado VARCHAR(25),
    reffolha TIMESTAMP,
    numfolha INT(2),
    tipovinc VARCHAR(20),
    nomeorgao VARCHAR(100),
    municipio VARCHAR(70),
    unidade INT(7),
    descunid VARCHAR(60),
    numfunc INT(7),
    numvinc INT(2),
    numpens INT,
    nome VARCHAR(255),
	cpf  VARCHAR(13) ,
    categoria VARCHAR(70),
    desccargo VARCHAR(255),
    descfuncao VARCHAR(255),
    especialidade VARCHAR(80),
    referencia VARCHAR(8),
    jornada VARCHAR(3),
    DTEXERC DATE,
    DTVAC DATE,
    DTAPOSENT DATE,
    DTNASC DATE,
    DTNASC_AJUSTADA DATE,
    idade INT(3),
    faixaidade VARCHAR(250),
    vlbruto DECIMAL(13,4),
	faixaremuneracao_bruta VARCHAR(255),
    vldesc DECIMAL(13,4),
    subsidio DECIMAL(13,4),
    tipoevento VARCHAR(255),
    formaprov VARCHAR(30),
    VALORAUX1 DECIMAL(13,4),
    VALORAUX2 DECIMAL(13,4),
    FLEXCAMPO01sexo VARCHAR(2),   
    NomeArquivoimportado VARCHAR(255),
    PRIMARY KEY (id)
);

DROP TABLE registroETurmalina;
CREATE TABLE registroETurmalina (
    id int auto_increment,
    operacao VARCHAR(5),
   dataFalecimento DATE,
  dataIngressoServicoPublico DATE,
  dataNascimento DATE,
  estadoCivil INT,
  nome VARCHAR(255),
  nomeMae VARCHAR(255),
  nomePai VARCHAR(255),
  sexo VARCHAR(255) ,
  numeroCPF VARCHAR(13), 
  PRIMARY KEY (id,nome,numeroCPF)
);


