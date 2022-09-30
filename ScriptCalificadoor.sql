-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_calificaDoor
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_calificaDoor
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_calificaDoor` ;
USE `db_calificaDoor` ;

-- -----------------------------------------------------
-- Table `db_calificaDoor`.`Profesor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_calificaDoor`.`Profesor` (
  `idProfesor` INT NOT NULL AUTO_INCREMENT,
  `tipoDocumento` VARCHAR(45) NULL,
  `numDocumento` VARCHAR(60) NULL,
  `nombres` VARCHAR(120) NULL,
  `apellidos` VARCHAR(120) NULL,
  `email` VARCHAR(120) NULL,
  PRIMARY KEY (`idProfesor`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_calificaDoor`.`Cursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_calificaDoor`.`Cursos` (
  `idCursos` INT NOT NULL AUTO_INCREMENT,
  `nombreCurso` VARCHAR(45) NULL,
  `ciclo` INT NULL,
  `Profesor_idProfesor` INT NOT NULL,
  PRIMARY KEY (`idCursos`),
  INDEX `fk_Cursos_Profesor1_idx` (`Profesor_idProfesor` ASC) VISIBLE,
  CONSTRAINT `fk_Cursos_Profesor1`
    FOREIGN KEY (`Profesor_idProfesor`)
    REFERENCES `db_calificaDoor`.`Profesor` (`idProfesor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_calificaDoor`.`Estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_calificaDoor`.`Estudiante` (
  `idEstudiante` INT NOT NULL AUTO_INCREMENT,
  `tipoDocumento` VARCHAR(45) NULL,
  `numDocumento` VARCHAR(60) NULL,
  `nombres` VARCHAR(120) NULL,
  `apellidos` VARCHAR(120) NULL,
  `email` VARCHAR(120) NULL,
  `Cursos_idCursos` INT NOT NULL,
  PRIMARY KEY (`idEstudiante`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_Estudiante_Cursos1_idx` (`Cursos_idCursos` ASC) VISIBLE,
  CONSTRAINT `fk_Estudiante_Cursos1`
    FOREIGN KEY (`Cursos_idCursos`)
    REFERENCES `db_calificaDoor`.`Cursos` (`idCursos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_calificaDoor`.`Notas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_calificaDoor`.`Notas` (
  `idNotas` INT NOT NULL AUTO_INCREMENT,
  `calificacion` VARCHAR(45) NULL,
  `Estudiante_idEstudiante` INT NOT NULL,
  `Cursos_idCursos` INT NOT NULL,
  `Profesor_idProfesor` INT NOT NULL,
  PRIMARY KEY (`idNotas`),
  INDEX `fk_Notas_Estudiante_idx` (`Estudiante_idEstudiante` ASC) VISIBLE,
  INDEX `fk_Notas_Cursos1_idx` (`Cursos_idCursos` ASC) VISIBLE,
  INDEX `fk_Notas_Profesor1_idx` (`Profesor_idProfesor` ASC) VISIBLE,
  CONSTRAINT `fk_Notas_Estudiante`
    FOREIGN KEY (`Estudiante_idEstudiante`)
    REFERENCES `db_calificaDoor`.`Estudiante` (`idEstudiante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Notas_Cursos1`
    FOREIGN KEY (`Cursos_idCursos`)
    REFERENCES `db_calificaDoor`.`Cursos` (`idCursos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Notas_Profesor1`
    FOREIGN KEY (`Profesor_idProfesor`)
    REFERENCES `db_calificaDoor`.`Profesor` (`idProfesor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
