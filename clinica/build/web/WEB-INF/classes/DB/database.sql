
CREATE TABLE paciente (
  `idpaciente` INT NOT NULL AUTO_INCREMENT,
  `primerNombre` VARCHAR(45) NULL,
  `segundoNombre` VARCHAR(45) NULL,
  `primerApellido` VARCHAR(45) NULL,
  `segundoApellido` VARCHAR(45) NULL,
  `edad` INT NULL,
  PRIMARY KEY (`idpaciente`));

CREATE TABLE cita (
  `idcita` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL,
  `hora` TIME NULL,
  `estado` VARCHAR(45) NULL,
  `observaciones` VARCHAR(100) NULL,
  `paciente_idpaciente` INT NOT NULL,
  PRIMARY KEY (`idcita`),
  FOREIGN KEY (`paciente_idpaciente`) REFERENCES `paciente` (`idpaciente`));