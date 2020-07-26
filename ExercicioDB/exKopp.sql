CREATE DATABASE exkopp;
USE exkopp;
CREATE TABLE tbprojetos(
idpro INT PRIMARY KEY AUTO_INCREMENT,
nomepro VARCHAR(20) NOT NULL
);
CREATE TABLE tbequipamentos(
idequip INT PRIMARY KEY AUTO_INCREMENT,
veloperm INT NOT NULL,
local_instalacao VARCHAR(20) NOT NULL,
idpro INT NOT NULL,
FOREIGN KEY(idpro) REFERENCES tbprojetos(idpro)
);
CREATE TABLE tbpassagemveic(
idpassagem INT PRIMARY KEY AUTO_INCREMENT,
data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
velocidade INT NOT NULL,
limite_velo BIT, -- 0= excedeu, 1= não excedeu
idequip INT NOT NULL,
FOREIGN KEY(idequip) REFERENCES tbequipamentos(idequip)
);

-- Valores inseridos para teste
INSERT INTO tbprojetos(nomepro)
VALUES ('Teste2');
SELECT idpro FROM tbprojetos;

INSERT INTO tbequipamentos(veloperm, local_instalacao,  idpro)
VALUES (110, 'Canoas', 2);

INSERT INTO tbpassagemveic(velocidade, limite_velo, idequip)
VALUES (130, 0, 1);

SELECT * FROM exkopp.tbpassagemveic;

-- Questão nº 1 -> Relatória de passagens/período
SELECT 
tbequipamentos.idequip,
tbpassagemveic.idpassagem,data_hora,velocidade,limite_velo
FROM tbprojetos, tbequipamentos, tbpassagemveic
WHERE tbprojetos.idpro = tbequipamentos.idpro 
AND tbequipamentos.idequip = tbpassagemveic.idequip
AND tbpassagemveic.data_hora BETWEEN '2020-07-23 10:00:00'
AND '2020-07-24 11:40:00' ORDER BY idequip, data_hora;

-- Questão nº 2 -> Relatório de passagens/projeto
SELECT
COUNT(idpassagem) AS total_de_passagens,
@total := COUNT(IF(limite_velo = 0, 0, NULL)) AS total_em_excesso,
SUM(IF(limite_velo = 0, velocidade, Null)) / @total AS media_velocidade
FROM tbpassagemveic, tbequipamentos, tbprojetos
WHERE tbpassagemveic.idequip = tbequipamentos.idequip
AND tbequipamentos.idpro = tbprojetos.idpro;

-- Questão nº 3 -> Criação de Trigger para velocidade acima de 80km/h
DELIMITER //
CREATE TRIGGER tr_velocidade BEFORE INSERT
ON tbpassagemveic
FOR EACH ROW
BEGIN
	IF(NEW.velocidade > 80) THEN		
        SET NEW.limite_velo = 0;
	ELSE		
        SET NEW.limite_velo = 1;
	END IF;
END//
DELIMITER ;

-- Valores inseridos para teste
INSERT INTO tbpassagemveic(velocidade, idequip)
VALUES (79, 3);

SELECT * FROM exkopp.tbpassagemveic;










 

