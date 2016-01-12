
/* registration table*/

CREATE TABLE `registration` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created` DATE NOT NULL,
  `userName` TEXT NULL,
  `password` TEXT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

/* customer table */
  
CREATE TABLE `customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created` DATE NOT NULL,
  `firstName` TEXT NULL,
  `lastName` TEXT NULL,
  `mobileNumber` VARCHAR(55) NULL,
  `registrationId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_customer_1_idx` (`registrationId` ASC),
  CONSTRAINT `fk_customer_1`
    FOREIGN KEY (`registrationId`)
    REFERENCES `registration` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
  
 /* contact table */
  
CREATE TABLE `contact` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created` DATE NOT NULL,
  `firstName` TEXT NULL,
  `lastName` TEXT NULL,
  `mobileNumber` VARCHAR(55) NULL,
  `customerId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contact_1_idx` (`customerId` ASC),
  CONSTRAINT `fk_contact_1`
    FOREIGN KEY (`customerId`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

