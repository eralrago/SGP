/**
 * Author:  Gabo
 * Created: 19/01/2023
 */
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sgp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sgp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sgp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `sgp` ;

-- -----------------------------------------------------
-- Table `sgp`.`cat_empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`cat_empresa` (
  `id_empresa` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_empresa`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`cat_perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`cat_perfil` (
  `id_perfil` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `activo` TINYINT NULL DEFAULT '1',
  PRIMARY KEY (`id_perfil`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`cat_planta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`cat_planta` (
  `id_planta` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_planta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`cat_puesto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`cat_puesto` (
  `id_puesto` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_puesto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`det_empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`det_empleado` (
  `id_empleado` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `num_empleado` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `primer_ap` VARCHAR(45) NOT NULL,
  `segundo_ap` VARCHAR(45) NULL DEFAULT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `fecha_registro` DATETIME NOT NULL,
  `fecha_modificacion` DATETIME NULL DEFAULT NULL,
  `id_perfil` INT UNSIGNED NOT NULL,
  `id_puesto` INT UNSIGNED NOT NULL,
  `curp` VARCHAR(18) NULL DEFAULT NULL,
  `rfc` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NULL DEFAULT NULL,
  `fecha_ingreso` DATETIME NOT NULL,
  `nss` VARCHAR(45) NULL DEFAULT NULL,
  `id_empresa` INT UNSIGNED NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT '1',
  `id_planta` INT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id_empleado`),
  INDEX `fk_empleado_perfil_idx` (`id_perfil` ASC) VISIBLE,
  INDEX `fk_empleado_puesto_idx` (`id_puesto` ASC) VISIBLE,
  INDEX `fk_empleado_empresa_idx` (`id_empresa` ASC) VISIBLE,
  INDEX `fk_empleado_planta_idx` (`id_planta` ASC) VISIBLE,
  CONSTRAINT `fk_empleado_empresa`
    FOREIGN KEY (`id_empresa`)
    REFERENCES `sgp`.`cat_empresa` (`id_empresa`),
  CONSTRAINT `fk_empleado_perfil`
    FOREIGN KEY (`id_perfil`)
    REFERENCES `sgp`.`cat_perfil` (`id_perfil`),
  CONSTRAINT `fk_empleado_planta`
    FOREIGN KEY (`id_planta`)
    REFERENCES `sgp`.`cat_planta` (`id_planta`),
  CONSTRAINT `fk_empleado_puesto`
    FOREIGN KEY (`id_puesto`)
    REFERENCES `sgp`.`cat_puesto` (`id_puesto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`bitacora_cat_perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`bitacora_cat_perfil` (
  `id_bitacora` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fecha_captura` DATETIME NULL DEFAULT NULL,
  `modificacion` TINYINT NULL DEFAULT NULL,
  `id_perfil` INT UNSIGNED NULL DEFAULT NULL,
  `id_empleado` INT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id_bitacora`),
  INDEX `fk_bitacora_perfil_idx` (`id_perfil` ASC) VISIBLE,
  INDEX `fk_bitacora_empleado` (`id_empleado` ASC) VISIBLE,
  CONSTRAINT `fk_bitacora_empleado`
    FOREIGN KEY (`id_empleado`)
    REFERENCES `sgp`.`det_empleado` (`id_empleado`),
  CONSTRAINT `fk_bitacora_perfil`
    FOREIGN KEY (`id_perfil`)
    REFERENCES `sgp`.`cat_perfil` (`id_perfil`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`cat_articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`cat_articulo` (
  `id_articulo` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `activo` TINYINT NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_articulo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`cat_prenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`cat_prenda` (
  `id_prenda` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  `activo` TINYINT NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_prenda`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`det_inventario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`det_inventario` (
  `id_inventario` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_prenda` INT UNSIGNED NULL DEFAULT NULL,
  `id_articulo` INT UNSIGNED NULL DEFAULT NULL,
  `cantidad` INT UNSIGNED NOT NULL DEFAULT '0',
  `activo` TINYINT NOT NULL DEFAULT '1',
  `visible` TINYINT NOT NULL DEFAULT '1',
  `fecha_captura` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_inventario`),
  INDEX `fk_inventario_prenda_idx` (`id_prenda` ASC) VISIBLE,
  INDEX `fk_inventario_articulo_idx` (`id_articulo` ASC) VISIBLE,
  CONSTRAINT `fk_inventario_articulo`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `sgp`.`cat_articulo` (`id_articulo`),
  CONSTRAINT `fk_inventario_prenda`
    FOREIGN KEY (`id_prenda`)
    REFERENCES `sgp`.`cat_prenda` (`id_prenda`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`cat_tipo_bitacora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`cat_tipo_bitacora` (
  `id_tipo` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_tipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`bitacora_inventario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`bitacora_inventario` (
  `id_bitacora` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_inventario` INT UNSIGNED NOT NULL,
  `id_empleado` INT UNSIGNED NOT NULL,
  `fecha_captura` DATETIME NOT NULL,
  `id_tipo` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_bitacora`),
  INDEX `fk_bitacora_inventario_inventario_idx` (`id_inventario` ASC) VISIBLE,
  INDEX `fk_bitacora_inventario_empleado_idx` (`id_empleado` ASC) VISIBLE,
  INDEX `fk_bitacora_inventario_tipo_idx` (`id_tipo` ASC) VISIBLE,
  CONSTRAINT `fk_bitacora_inventario_empleado`
    FOREIGN KEY (`id_empleado`)
    REFERENCES `sgp`.`det_empleado` (`id_empleado`),
  CONSTRAINT `fk_bitacora_inventario_inventario`
    FOREIGN KEY (`id_inventario`)
    REFERENCES `sgp`.`det_inventario` (`id_inventario`),
  CONSTRAINT `fk_bitacora_inventario_tipo`
    FOREIGN KEY (`id_tipo`)
    REFERENCES `sgp`.`cat_tipo_bitacora` (`id_tipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`cat_estatus_incidencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`cat_estatus_incidencia` (
  `id_estatus` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_estatus`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`cat_estatus_registro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`cat_estatus_registro` (
  `id_estatus` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_estatus`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`cat_tipo_incidencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`cat_tipo_incidencia` (
  `id_tipo` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`det_biometrico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`det_biometrico` (
  `id_biometrico` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_empleado` INT UNSIGNED NOT NULL,
  `fecha_captura` DATETIME NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT '1',
  `huella` LONGBLOB NOT NULL,
  PRIMARY KEY (`id_biometrico`),
  INDEX `fk_biometrico_empleado_idx` (`id_empleado` ASC) VISIBLE,
  CONSTRAINT `fk_biometrico_empleado`
    FOREIGN KEY (`id_empleado`)
    REFERENCES `sgp`.`det_empleado` (`id_empleado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`det_solicitud_articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`det_solicitud_articulo` (
  `id_solicitud` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_articulo` INT UNSIGNED NOT NULL,
  `cantidad` INT NOT NULL DEFAULT '1',
  `aprobada` TINYINT NULL DEFAULT NULL,
  `fecha_cap` DATETIME NOT NULL,
  `fecha_mod` DATETIME NULL DEFAULT NULL,
  `id_empleado_sol` INT UNSIGNED NOT NULL,
  `id_empleado_rev` INT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id_solicitud`),
  INDEX `fk_sol_material_idx` (`id_articulo` ASC) VISIBLE,
  INDEX `fk_emp_sol_articulo_idx` (`id_empleado_sol` ASC) VISIBLE,
  INDEX `fk_emp_revl_articulo_idx` (`id_empleado_rev` ASC) VISIBLE,
  CONSTRAINT `fk_emp_rev_articulo`
    FOREIGN KEY (`id_empleado_rev`)
    REFERENCES `sgp`.`det_empleado` (`id_empleado`),
  CONSTRAINT `fk_emp_sol_articulo`
    FOREIGN KEY (`id_empleado_sol`)
    REFERENCES `sgp`.`det_empleado` (`id_empleado`),
  CONSTRAINT `fk_sol_articulo`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `sgp`.`cat_articulo` (`id_articulo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`det_solicitud_permiso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`det_solicitud_permiso` (
  `id_solicitud` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fecha_cap` DATETIME NOT NULL,
  `fecha_mod` DATETIME NULL DEFAULT NULL,
  `fecha_inicio` DATETIME NOT NULL,
  `fecha_fin` DATETIME NOT NULL,
  `aprobada` TINYINT NULL DEFAULT NULL,
  `id_empleado_sol` INT UNSIGNED NOT NULL,
  `id_empleado_rev` INT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id_solicitud`),
  INDEX `fk_emp_rev_vacaciones_idx` (`id_empleado_sol` ASC) VISIBLE,
  INDEX `fk_emp_sol_vacaciones_idx` (`id_empleado_rev` ASC) VISIBLE,
  CONSTRAINT `fk_emp_rev_vacaciones`
    FOREIGN KEY (`id_empleado_sol`)
    REFERENCES `sgp`.`det_empleado` (`id_empleado`),
  CONSTRAINT `fk_emp_sol_vacaciones`
    FOREIGN KEY (`id_empleado_rev`)
    REFERENCES `sgp`.`det_empleado` (`id_empleado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`det_solicitud_prenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`det_solicitud_prenda` (
  `id_solicitud` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_prenda` INT UNSIGNED NOT NULL,
  `cantidad` INT NOT NULL DEFAULT '1',
  `aprobada` TINYINT NULL DEFAULT NULL,
  `fecha_cap` DATETIME NOT NULL,
  `fecha_mod` DATETIME NULL DEFAULT NULL,
  `id_empleado_sol` INT UNSIGNED NOT NULL,
  `id_empleado_rev` INT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id_solicitud`),
  INDEX `fk_sol_prenda_idx` (`id_prenda` ASC) VISIBLE,
  INDEX `fk_sol_emp_idx` (`id_empleado_sol` ASC) VISIBLE,
  INDEX `fk_emp_revisor_idx` (`id_empleado_rev` ASC) VISIBLE,
  CONSTRAINT `fk_emp_rev_prenda`
    FOREIGN KEY (`id_empleado_rev`)
    REFERENCES `sgp`.`det_empleado` (`id_empleado`),
  CONSTRAINT `fk_emp_sol_prenda`
    FOREIGN KEY (`id_empleado_sol`)
    REFERENCES `sgp`.`det_empleado` (`id_empleado`),
  CONSTRAINT `fk_sol_prenda`
    FOREIGN KEY (`id_prenda`)
    REFERENCES `sgp`.`cat_prenda` (`id_prenda`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`det_incidencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`det_incidencia` (
  `id_incidencia` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_tipo` INT UNSIGNED NULL DEFAULT NULL,
  `id_empleado` INT UNSIGNED NULL DEFAULT NULL,
  `id_estatus` INT UNSIGNED NULL DEFAULT NULL,
  `visible` TINYINT NULL DEFAULT NULL,
  `id_sol_articulo` INT UNSIGNED NULL DEFAULT NULL,
  `id_sol_prenda` INT UNSIGNED NULL DEFAULT NULL,
  `id_sol_permiso` INT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id_incidencia`),
  INDEX `fk_incidencia_sol_prenda_idx` (`id_sol_prenda` ASC) VISIBLE,
  INDEX `fk_incidencia_sol_articulo_idx` (`id_sol_articulo` ASC) VISIBLE,
  INDEX `fk_incidencia_estatus_idx` (`id_estatus` ASC) VISIBLE,
  INDEX `fk_incidencia_tipo_idx` (`id_tipo` ASC) VISIBLE,
  INDEX `fk_incidencia_sol_permiso_idx` (`id_sol_permiso` ASC) VISIBLE,
  CONSTRAINT `fk_incidencia_estatus`
    FOREIGN KEY (`id_estatus`)
    REFERENCES `sgp`.`cat_estatus_incidencia` (`id_estatus`),
  CONSTRAINT `fk_incidencia_sol_articulo`
    FOREIGN KEY (`id_sol_articulo`)
    REFERENCES `sgp`.`det_solicitud_articulo` (`id_solicitud`),
  CONSTRAINT `fk_incidencia_sol_permiso`
    FOREIGN KEY (`id_sol_permiso`)
    REFERENCES `sgp`.`det_solicitud_permiso` (`id_solicitud`),
  CONSTRAINT `fk_incidencia_sol_prenda`
    FOREIGN KEY (`id_sol_prenda`)
    REFERENCES `sgp`.`det_solicitud_prenda` (`id_solicitud`),
  CONSTRAINT `fk_incidencia_tipo`
    FOREIGN KEY (`id_tipo`)
    REFERENCES `sgp`.`cat_tipo_incidencia` (`id_tipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sgp`.`det_registro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgp`.`det_registro` (
  `id_registro` INT NOT NULL AUTO_INCREMENT,
  `id_empleado` INT UNSIGNED NOT NULL,
  `fecha_entrada` DATETIME NULL DEFAULT NULL,
  `fecha_salida` DATETIME NULL DEFAULT NULL,
  `id_estatus` INT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id_registro`),
  INDEX `id_registro_empleado_idx` (`id_empleado` ASC) VISIBLE,
  INDEX `fk_registro_estatus_idx` (`id_estatus` ASC) VISIBLE,
  CONSTRAINT `fk_registro_empleado`
    FOREIGN KEY (`id_empleado`)
    REFERENCES `sgp`.`det_empleado` (`id_empleado`),
  CONSTRAINT `fk_registro_estatus`
    FOREIGN KEY (`id_estatus`)
    REFERENCES `sgp`.`cat_estatus_registro` (`id_estatus`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


--------------19-01-2023-------------------------


CREATE TABLE `cat_area` (
  `id_area` int unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `activo` tinyint NOT NULL,
  PRIMARY KEY (`id_area`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `sgp`.`det_empleado` 
ADD COLUMN `id_area` INT UNSIGNED NULL AFTER `id_planta`;


ALTER TABLE `sgp`.`det_empleado` 
ADD INDEX `fk_empleado_area_idx` (`id_area` ASC) VISIBLE;
;
ALTER TABLE `sgp`.`det_empleado` 
ADD CONSTRAINT `fk_empleado_planta`
  FOREIGN KEY (`id_planta`)
  REFERENCES `sgp`.`cat_planta` (`id_planta`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_empleado_area`
  FOREIGN KEY (`id_area`)
  REFERENCES `sgp`.`cat_area` (`id_area`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


--------------30-01-2023-------------------------

CREATE TABLE `sgp`.`cat_tipo_solicitud` (
  `id_tipo_solicitud` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  `activo` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`id_tipo_solicitud`));

INSERT INTO `sgp`.`cat_tipo_solicitud` (`descripcion`, `activo`) VALUES ('Permiso', '1');
INSERT INTO `sgp`.`cat_tipo_solicitud` (`descripcion`) VALUES ('Vacaciones');

ALTER TABLE `sgp`.`det_solicitud_permiso` 
ADD COLUMN `id_tipo_solicitud` INT UNSIGNED NOT NULL AFTER `id_empleado_rev`,
ADD INDEX `fk_sol_tipo_sol_idx` (`id_tipo_solicitud` ASC) VISIBLE;

ALTER TABLE `sgp`.`det_solicitud_permiso` 
ADD CONSTRAINT `fk_sol_tipo_sol`
  FOREIGN KEY (`id_tipo_solicitud`)
  REFERENCES `sgp`.`cat_tipo_solicitud` (`id_tipo_solicitud`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

--------------16-02-2023-------------------------
CREATE TABLE `sgp`.`cat_talla` (
  `id_talla` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_talla`));


ALTER TABLE `sgp`.`det_solicitud_prenda` 
ADD COLUMN `id_talla` INT UNSIGNED NULL AFTER `id_empleado_rev`,
ADD INDEX `fk_sol_talla_idx` (`id_talla` ASC) VISIBLE;

ALTER TABLE `sgp`.`det_solicitud_prenda` 
ADD CONSTRAINT `fk_sol_talla`
  FOREIGN KEY (`id_talla`)
  REFERENCES `sgp`.`cat_talla` (`id_talla`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


INSERT INTO `sgp`.`cat_talla` (`descripcion`) VALUES ('CH');
INSERT INTO `sgp`.`cat_talla` (`descripcion`) VALUES ('MD');
INSERT INTO `sgp`.`cat_talla` (`descripcion`) VALUES ('G/L');
INSERT INTO `sgp`.`cat_talla` (`descripcion`) VALUES ('XG');

update det_solicitud_prenda set id_talla = 1;

ALTER TABLE `sgp`.`det_solicitud_prenda` 
DROP FOREIGN KEY `fk_sol_talla`;
ALTER TABLE `sgp`.`det_solicitud_prenda` 
CHANGE COLUMN `id_talla` `id_talla` INT UNSIGNED NOT NULL ;
ALTER TABLE `sgp`.`det_solicitud_prenda` 
ADD CONSTRAINT `fk_sol_talla`
  FOREIGN KEY (`id_talla`)
  REFERENCES `sgp`.`cat_talla` (`id_talla`);

--------------17-02-2023-------------------------

ALTER TABLE sgp.det_incidencia ADD fecha_cap datetime NULL;
ALTER TABLE sgp.det_incidencia ADD fecha_mod datetime NULL;

UPDATE det_incidencia SET fecha_cap = now()
WHERE fecha_cap IS NULL;

ALTER TABLE sgp.det_incidencia MODIFY COLUMN fecha_cap datetime NOT NULL;

ALTER TABLE sgp.det_incidencia ADD CONSTRAINT fk_incidencia_empleado FOREIGN KEY (id_empleado) REFERENCES sgp.det_empleado(id_empleado);

--------------20-02-2023-------------------------
ALTER TABLE sgp.det_solicitud_permiso ADD descripcion_rechazo varchar(150) NULL;
ALTER TABLE sgp.det_incidencia ADD id_empleado_rev int unsigned NULL;

ALTER TABLE sgp.det_incidencia ADD CONSTRAINT fk_incidencia_empleado_rev FOREIGN KEY (id_empleado_rev) REFERENCES sgp.det_empleado(id_empleado);

--------------21-02-2023-------------------------
CREATE TABLE `sgp`.`cat_razon_social` (
  `id_razon_social` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `razon_socal` VARCHAR(45) NOT NULL,
  `rfc` VARCHAR(45) NOT NULL,
  `activo` TINYINT NOT NULL DEFAULT 1,
  `fecha_creacion` DATETIME NOT NULL,
  `fecha_mod` DATETIME NULL,
  PRIMARY KEY (`id_razon_social`));

INSERT INTO `sgp`.`cat_razon_social` (`razon_socal`, `rfc`, `fecha_creacion`) VALUES ('INDUSTRIA DE REFRIGERACION KELANGAN', 'XAXX010101000', '2023-02-21 06:30:00');

ALTER TABLE `sgp`.`cat_planta` 
ADD COLUMN `id_razon_social` INT UNSIGNED NULL AFTER `activo`,
ADD INDEX `fk_planta_razon_idx` (`id_razon_social` ASC) VISIBLE;

ALTER TABLE `sgp`.`cat_planta` 
ADD CONSTRAINT `fk_planta_razon`
  FOREIGN KEY (`id_razon_social`)
  REFERENCES `sgp`.`cat_razon_social` (`id_razon_social`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

UPDATE `sgp`.`cat_planta` SET `id_razon_social` = '1';


ALTER TABLE `sgp`.`cat_planta` 
DROP FOREIGN KEY `fk_planta_razon`;
ALTER TABLE `sgp`.`cat_planta` 
CHANGE COLUMN `id_razon_social` `id_razon_social` INT UNSIGNED NOT NULL ;
ALTER TABLE `sgp`.`cat_planta` 
ADD CONSTRAINT `fk_planta_razon`
  FOREIGN KEY (`id_razon_social`)
  REFERENCES `sgp`.`cat_razon_social` (`id_razon_social`);

--------------28-02-2023-------------------------
ALTER TABLE sgp.det_empleado ADD fotografia LONGTEXT NULL;

INSERT INTO sgp.cat_perfil
(id_perfil, descripcion, activo)
VALUES(1, 'Administrador', 1);
INSERT INTO sgp.cat_perfil
(id_perfil, descripcion, activo)
VALUES(2, 'Administrador de usuarios', 1);
INSERT INTO sgp.cat_perfil
(id_perfil, descripcion, activo)
VALUES(3, 'Administrador de planta', 1);
INSERT INTO sgp.cat_perfil
(id_perfil, descripcion, activo)
VALUES(4, 'Gerente de planta', 1);
INSERT INTO sgp.cat_perfil
(id_perfil, descripcion, activo)
VALUES(5, 'Auxiliar general', 1);
INSERT INTO sgp.cat_perfil
(id_perfil, descripcion, activo)
VALUES(6, 'Montacarguista', 1);