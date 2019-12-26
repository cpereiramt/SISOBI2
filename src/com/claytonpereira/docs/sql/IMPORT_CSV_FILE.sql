LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/ArquivoSiprev_Servidor_Inclusao.xml' 
INTO TABLE registroETurmalina
ROWS IDENTIFIED BY "<servidores>";

SET GLOBAL local_infile = 1;
SHOW VARIABLES LIKE "secure_file_priv";

