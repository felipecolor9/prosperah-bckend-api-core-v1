-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema prosperah_db_rel_corev1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema prosperah_db_rel_corev1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `prosperah_db_rel_corev1` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------
USE `prosperah_db_rel_corev1` ;

-- -----------------------------------------------------
-- Table `prosperah_db_rel_corev1`.`tb001_usuario_cadastral`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prosperah_db_rel_corev1`.`tb001_usuario_cadastral` (
  `cod_usr` BINARY(16) NOT NULL,
  `usr_nome_login` VARCHAR(45) NOT NULL,
  `usr_nome_completo` VARCHAR(255) NOT NULL,
  `usr_email` VARCHAR(255) NOT NULL,
  `usr_senha` VARCHAR(32) NOT NULL,
  `usr_data_criacao` TIMESTAMP NOT NULL,
  `cod_auth_email` INT(6) NULL,
  PRIMARY KEY (`cod_usr`))
COMMENT = 'Tabela para usuários prestes a realizar cadastro no sistema. Ao validar o cadastro, o registro é removido e seus dados são transferidos via aplicação para a tabela de usuários consolidados.\n';


-- -----------------------------------------------------
-- Table `prosperah_db_rel_corev1`.`tb002_usuario_consolidado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prosperah_db_rel_corev1`.`tb002_usuario_consolidado` (
  `cod_usr` BINARY(16) NOT NULL,
  `cod_usr_cad_fk` BINARY(16) NOT NULL,
  `usr_data_nasc` TIMESTAMP NOT NULL,
  `usr_nome_login` VARCHAR(45) NOT NULL,
  `usr_nome_completo` VARCHAR(255) NOT NULL,
  `usr_email` VARCHAR(255) NOT NULL,
  `usr_senha` VARCHAR(32) NOT NULL,
  `usr_data_criacao` TIMESTAMP NOT NULL,
  `usr_data_validacao` TIMESTAMP NOT NULL,
  PRIMARY KEY (`cod_usr`),
  INDEX `idx_login_senha_usuario` USING BTREE (`usr_nome_login`, `usr_senha`) COMMENT 'Utilizado para login e verificação de disponibilidade de usuário de forma performática.' INVISIBLE,
  UNIQUE INDEX `cod_usr_UNIQUE` (`cod_usr` ASC) VISIBLE,
  INDEX `fk_tb002_usuario_consolidado_tb001_usuario_cadastral1_idx` (`cod_usr_cad_fk` ASC) VISIBLE,
  CONSTRAINT `fk_tb002_usuario_consolidado_tb001_usuario_cadastral1`
    FOREIGN KEY (`cod_usr_cad_fk`)
    REFERENCES `prosperah_db_rel_corev1`.`tb001_usuario_cadastral` (`cod_usr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Tabela para usuários devidamente cadastrados e validados no sistema.';


-- -----------------------------------------------------
-- Table `prosperah_db_rel_corev1`.`tb101_frm_topc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prosperah_db_rel_corev1`.`tb101_frm_topc` (
  `cod_top` BINARY(16) NOT NULL,
  `cod_criador_usr_fk` BINARY(16) NOT NULL,
  `top_nome_titl` VARCHAR(75) NOT NULL,
  `top_cntd` TEXT(1000) NOT NULL COMMENT 'O objetivo são tópicos curtos e diretos ao ponto.',
  `top_data_criacao` TIMESTAMP NOT NULL,
  UNIQUE INDEX `cod_top_UNIQUE` (`cod_top` ASC) VISIBLE,
  PRIMARY KEY (`cod_top`, `cod_criador_usr_fk`),
  INDEX `fk_tb101_frm_topc_tb002_usuario_consolidado1_idx` (`cod_criador_usr_fk` ASC) VISIBLE,
  CONSTRAINT `fk_tb101_frm_topc_tb002_usuario_consolidado1`
    FOREIGN KEY (`cod_criador_usr_fk`)
    REFERENCES `prosperah_db_rel_corev1`.`tb002_usuario_consolidado` (`cod_usr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Tabela para tópicos existentes em fórum.\n';


-- -----------------------------------------------------
-- Table `prosperah_db_rel_corev1`.`tb103_frm_tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prosperah_db_rel_corev1`.`tb103_frm_tags` (
  `cod_tag` INT NOT NULL,
  `tag_nome` VARCHAR(25) NOT NULL,
  `tag_data_criacao` TIMESTAMP NOT NULL,
  UNIQUE INDEX `cod_frm_UNIQUE` (`cod_tag` ASC) VISIBLE,
  PRIMARY KEY (`cod_tag`))
COMMENT = 'Tags utilizadas para fins de identificação e filtragem de tópicos no fórum';


-- -----------------------------------------------------
-- Table `prosperah_db_rel_corev1`.`tb102_topc_com`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prosperah_db_rel_corev1`.`tb102_topc_com` (
  `cod_com` BINARY(16) NOT NULL,
  `cod_top_fk` BINARY(16) NOT NULL,
  `cod_criador_usr_fk` BINARY(16) NOT NULL,
  `com_cntd` VARCHAR(255) NOT NULL COMMENT 'O objetivo são comentários mais curtos ainda e diretos ao ponto. Como em um tweet',
  `com_data_criacao` TIMESTAMP NOT NULL,
  UNIQUE INDEX `cod_com_UNIQUE` (`cod_com` ASC) INVISIBLE,
  PRIMARY KEY (`cod_com`, `cod_top_fk`, `cod_criador_usr_fk`),
  INDEX `fk_tb102_topc_com_tb101_frm_topc1_idx` (`cod_top_fk` ASC, `cod_criador_usr_fk` ASC) VISIBLE,
  CONSTRAINT `fk_tb102_topc_com_tb101_frm_topc1`
    FOREIGN KEY (`cod_top_fk` , `cod_criador_usr_fk`)
    REFERENCES `prosperah_db_rel_corev1`.`tb101_frm_topc` (`cod_top` , `cod_criador_usr_fk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Tabela para comentarios pertencentes a um tópico.';


-- -----------------------------------------------------
-- Table `prosperah_db_rel_corev1`.`tb301_usr_crtr_econ`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prosperah_db_rel_corev1`.`tb301_usr_crtr_econ` (
  `cod_crtr` BINARY(16) NOT NULL,
  `cod_dono_usr_fk` BINARY(16) NOT NULL,
  `crtr_econ_patr_fixo` FLOAT NOT NULL DEFAULT 0.00,
  `crtr_econ_patr_var` FLOAT NOT NULL DEFAULT 0.00,
  `crtr_econ_rent_fixo` FLOAT NOT NULL DEFAULT 0.00,
  `crtr_econ_rent_var` FLOAT NOT NULL DEFAULT 0.00,
  `crtr_econ_data_criacao` TIMESTAMP NOT NULL,
  PRIMARY KEY (`cod_crtr`, `cod_dono_usr_fk`),
  UNIQUE INDEX `cod_crtr_UNIQUE` (`cod_crtr` ASC) VISIBLE,
  INDEX `fk_tb301_usr_crtr_econ_tb002_usuario_consolidado1_idx` (`cod_dono_usr_fk` ASC) VISIBLE,
  CONSTRAINT `fk_tb301_usr_crtr_econ_tb002_usuario_consolidado1`
    FOREIGN KEY (`cod_dono_usr_fk`)
    REFERENCES `prosperah_db_rel_corev1`.`tb002_usuario_consolidado` (`cod_usr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Tabela para carteira de usuários';


-- -----------------------------------------------------
-- Table `prosperah_db_rel_corev1`.`tb900_frm_tags_has_tb101_frm_topc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prosperah_db_rel_corev1`.`tb900_frm_tags_has_tb101_frm_topc` (
  `tb103_frm_tags_cod_tag` INT NOT NULL,
  `tb101_frm_topc_cod_top` BINARY(16) NOT NULL,
  `tb101_frm_topc_cod_criador_usr_fk` BINARY(16) NOT NULL,
  PRIMARY KEY (`tb103_frm_tags_cod_tag`, `tb101_frm_topc_cod_top`, `tb101_frm_topc_cod_criador_usr_fk`),
  INDEX `fk_tb103_frm_tags_has_tb101_frm_topc_tb101_frm_topc1_idx` (`tb101_frm_topc_cod_top` ASC, `tb101_frm_topc_cod_criador_usr_fk` ASC) VISIBLE,
  INDEX `fk_tb103_frm_tags_has_tb101_frm_topc_tb103_frm_tags1_idx` (`tb103_frm_tags_cod_tag` ASC) VISIBLE,
  CONSTRAINT `fk_tb103_frm_tags_has_tb101_frm_topc_tb103_frm_tags1`
    FOREIGN KEY (`tb103_frm_tags_cod_tag`)
    REFERENCES `prosperah_db_rel_corev1`.`tb103_frm_tags` (`cod_tag`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb103_frm_tags_has_tb101_frm_topc_tb101_frm_topc1`
    FOREIGN KEY (`tb101_frm_topc_cod_top` , `tb101_frm_topc_cod_criador_usr_fk`)
    REFERENCES `prosperah_db_rel_corev1`.`tb101_frm_topc` (`cod_top` , `cod_criador_usr_fk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `prosperah_db_rel_corev1`.`tb201_crtr_econ_mov_fin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prosperah_db_rel_corev1`.`tb201_crtr_econ_mov_fin` (
  `cod_mov_fin` BINARY(16) NOT NULL,
  `cod_crtr_fk` BINARY(16) NOT NULL,
  `cod_dono_usr_fk` BINARY(16) NOT NULL,
  `mov_fin_val_brl` FLOAT NOT NULL,
  `cod_enum_corr_fin` INT(3) NOT NULL,
  `cod_enum_tipo_mov_fin` CHAR NOT NULL,
  `mov_fin_qntd_unit_ativ` FLOAT NOT NULL,
  `mov_fin_data` TIMESTAMP NOT NULL,
  PRIMARY KEY (`cod_mov_fin`, `cod_crtr_fk`, `cod_dono_usr_fk`),
  UNIQUE INDEX `cod_mov_fin_UNIQUE` (`cod_mov_fin` ASC) VISIBLE,
  INDEX `fk_tb201_crtr_econ_mov_fin_tb301_usr_crtr_econ1_idx` (`cod_crtr_fk` ASC, `cod_dono_usr_fk` ASC) VISIBLE,
  CONSTRAINT `fk_tb201_crtr_econ_mov_fin_tb301_usr_crtr_econ1`
    FOREIGN KEY (`cod_crtr_fk` , `cod_dono_usr_fk`)
    REFERENCES `prosperah_db_rel_corev1`.`tb301_usr_crtr_econ` (`cod_crtr` , `cod_dono_usr_fk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
