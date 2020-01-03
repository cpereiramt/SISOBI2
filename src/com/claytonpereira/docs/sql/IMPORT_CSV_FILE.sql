LOAD xml INFILE 'C:/ProgramData/MySQL/MySQL Server 5.7/Uploads/arquivoSiprev_convertido.xml' 
INTO TABLE registroETurmalina
ROWS IDENTIFIED BY '<servidores>';


SET GLOBAL local_infile = 1;
SHOW VARIABLES LIKE "secure_file_priv";
SET GLOBAL secure_file_priv = "C:\ProgramData\MySQL\MySQL Server 5.7\Uploads";
select * from registroETurmalina;