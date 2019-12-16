LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/SISOBI - 2019 09 Set_CE.csv' 
INTO TABLE registros 
FIELDS TERMINATED BY ';' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

SET GLOBAL local_infile = 1;
SHOW VARIABLES LIKE "secure_file_priv";

