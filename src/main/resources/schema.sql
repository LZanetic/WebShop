-- -----------------------------------------------------
-- Table `mydb`.`Korisnik`
-- -----------------------------------------------------
create schema if not exists `nppjj`;
CREATE TABLE IF NOT EXISTS `nppjj`.`Korisnik` (
                                                  `Id` INT NOT NULL AUTO_INCREMENT,
                                                  `Ime` VARCHAR(45) NOT NULL,
    `Prezime` VARCHAR(45) NOT NULL,
    `Username` VARCHAR(45) NOT NULL,
    `Password` VARCHAR(250) NOT NULL,
    `Kontakt` VARCHAR(45) NOT NULL,
    `Lokacija` VARCHAR(45) NOT NULL,
    `Email` VARCHAR(45) NOT NULL,
    `BrojTelefona` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`Id`),
    UNIQUE INDEX `idKorisnik_UNIQUE` (`Id` ASC) VISIBLE)
    ENGINE = InnoDB;
SET FOREIGN_KEY_CHECKS = 0;








-- -----------------------------------------------------
-- Table `mydb`.`Proizvod`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nppjj`.`Proizvod` (
                                                  `Id` INT NOT NULL AUTO_INCREMENT,
                                                  `Korisnik_Id` INT NOT NULL,
                                                  `Naslov` VARCHAR(45) NOT NULL,
    `Opis` MEDIUMTEXT NOT NULL,
    `Stanje` VARCHAR(45) NOT NULL,
    `Cijena` INT NOT NULL,
    `Kategorija` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`Id`, `Korisnik_Id`),
    INDEX `fk_Proizvod_Korisnik_idx` (`Korisnik_Id` ASC) VISIBLE,
    UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE,
    CONSTRAINT `fk_Proizvod_Korisnik`
    FOREIGN KEY (`Korisnik_Id`)
    REFERENCES `nppjj`.`Korisnik` (`Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
    ENGINE = InnoDB;
SET FOREIGN_KEY_CHECKS = 0;








-- -----------------------------------------------------
-- Table `mydb`.`Slike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nppjj`.`Slike` (
                                               `Id` INT NOT NULL AUTO_INCREMENT,
                                               `Proizvod_Id` INT NOT NULL,
                                               `Proizvod_Korisnik_Id` INT NOT NULL,
                                               `Slika` VARCHAR(64) NOT NULL ,
    PRIMARY KEY (`Id`, `Proizvod_Id`, `Proizvod_Korisnik_Id`),
    INDEX `fk_Slike_Proizvod1_idx` (`Proizvod_Id` ASC, `Proizvod_Korisnik_Id` ASC) VISIBLE,
    UNIQUE INDEX `Id_UNIQUE` (`Id` ASC) VISIBLE,
    CONSTRAINT `fk_Slike_Proizvod1`
    FOREIGN KEY (`Proizvod_Id` , `Proizvod_Korisnik_Id`)
    REFERENCES `nppjj`.`Proizvod` (`Id` , `Korisnik_Id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE )
    ENGINE = InnoDB;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE IF NOT EXISTS `nppjj`.`authority` (
                                                   Id INT NOT NULL AUTO_INCREMENT,
                                                   Name VARCHAR(100) NOT NULL,
    PRIMARY KEY (Id),
    UNIQUE INDEX `idAuthority_UNIQUE` (Id ASC) VISIBLE)
    ENGINE = InnoDB;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE IF NOT EXISTS `nppjj`.`user_authority` (
                                                        user_id INT NOT NULL,
                                                        authority_id INT NOT NULL,
                                                        PRIMARY KEY (user_id, authority_id),
    INDEX  fk_User_Authority_idx (user_id ASC, authority_id ASC) VISIBLE ,
    FOREIGN KEY (user_id)
    REFERENCES `nppjj`.`Korisnik` (Id),
    FOREIGN KEY (authority_id)
    REFERENCES `nppjj`.`Authority`(Id)
    ON DELETE CASCADE
    ON UPDATE CASCADE )

    ENGINE = InnoDB;
SET FOREIGN_KEY_CHECKS = 0;




