CREATE DATABASE  IF NOT EXISTS `marleo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `marleo`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `accounting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounting` (
  `id_accounting` int(11) NOT NULL AUTO_INCREMENT,
  `firstSumGoods` decimal(9,2) DEFAULT NULL,
  `lastSumGoods` decimal(9,2) DEFAULT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `freeMoney` decimal(9,2) DEFAULT NULL,
  `differenceFromSumGoods` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`id_accounting`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `arrival`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arrival` (
  `id_arrival` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sumInvoice` decimal(9,2) NOT NULL,
  `sumArrival` decimal(9,2) NOT NULL,
  `number_waybill` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `note` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id_arrival`),
  UNIQUE KEY `id_arrivalList_UNIQUE` (`id_arrival`),
  KEY `fk_arrival_userSwing1_idx` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `arrivallist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `arrivallist` (
  `id_arrivalList` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(7,3) NOT NULL,
  `id_goods` bigint(20) NOT NULL,
  `id_arrival` bigint(20) NOT NULL,
  PRIMARY KEY (`id_arrivalList`),
  KEY `fk_check1_goods1_idx` (`id_goods`),
  KEY `fk_arrivalList_arrival1_idx` (`id_arrival`),
  CONSTRAINT `fk_arrivalList_arrival1` FOREIGN KEY (`id_arrival`) REFERENCES `arrival` (`id_arrival`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_check1_goods100` FOREIGN KEY (`id_goods`) REFERENCES `goods` (`id_goods`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `barcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barcode` (
  `id_barcode` int(11) NOT NULL AUTO_INCREMENT,
  `barcode` bigint(20) DEFAULT NULL,
  `id_goods` bigint(20) NOT NULL,
  PRIMARY KEY (`id_barcode`),
  KEY `fk_barcode_goods1_idx` (`id_goods`),
  CONSTRAINT `fk_barcode_goods1` FOREIGN KEY (`id_goods`) REFERENCES `goods` (`id_goods`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `case_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `case_record` (
  `id_case` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cashMustBe` decimal(9,2) DEFAULT NULL,
  `id_cashIn` int(11) DEFAULT NULL,
  `id_cashOut` int(11) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  `id_arrival` bigint(20) DEFAULT NULL,
  `id_writeoff` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_case`),
  KEY `fk_case_record_cash_in1_idx` (`id_cashIn`),
  KEY `fk_case_record_cash_out1_idx` (`id_cashOut`),
  KEY `fk_case_record_userSwing1_idx` (`id_user`),
  KEY `fk_case_record_arrival1_idx` (`id_arrival`),
  KEY `fk_case_record_writeoff1_idx` (`id_writeoff`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `cash_in`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cash_in` (
  `id_cashIn` int(11) NOT NULL AUTO_INCREMENT,
  `sum_cash` decimal(9,2) NOT NULL,
  `note` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_cashIn`),
  UNIQUE KEY `id_cashIn_UNIQUE` (`id_cashIn`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `cash_out`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cash_out` (
  `id_cashOut` int(11) NOT NULL AUTO_INCREMENT,
  `sum_cash` decimal(9,2) NOT NULL,
  `note` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_cashOut`),
  UNIQUE KEY `id_cashIn_UNIQUE` (`id_cashOut`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `categoryfavorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoryfavorite` (
  `id_categoryFavorite` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `note` varchar(100) NOT NULL,
  PRIMARY KEY (`id_categoryFavorite`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `categorygoods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorygoods` (
  `id_categoryGoods` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `note` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id_categoryGoods`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check` (
  `id_check` int(11) NOT NULL AUTO_INCREMENT,
  `sum` decimal(9,2) DEFAULT NULL,
  `money` decimal(9,2) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `note` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id_check`),
  UNIQUE KEY `id_checkList_UNIQUE` (`id_check`),
  KEY `fk_checkList_userSwing1_idx` (`id_user`),
  CONSTRAINT `fk_checkList_userSwing1` FOREIGN KEY (`id_user`) REFERENCES `userswing` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `check_discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check_discount` (
  `id_check` int(11) NOT NULL,
  `id_discount` int(11) NOT NULL,
  PRIMARY KEY (`id_check`),
  KEY `fk_check_discount_check1_idx` (`id_check`),
  KEY `fk_check_discount_discount1_idx` (`id_discount`),
  CONSTRAINT `fk_check_discount_check1` FOREIGN KEY (`id_check`) REFERENCES `check` (`id_check`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `checklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checklist` (
  `id_checkList` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(7,3) NOT NULL,
  `profit` decimal(7,3) NOT NULL,
  `id_goods` bigint(20) NOT NULL,
  `id_check` int(11) NOT NULL,
  PRIMARY KEY (`id_checkList`),
  KEY `fk_check1_goods1_idx` (`id_goods`),
  KEY `fk_check1_checkList1_idx` (`id_check`),
  CONSTRAINT `fk_check1_checkList1` FOREIGN KEY (`id_check`) REFERENCES `check` (`id_check`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_check1_goods1` FOREIGN KEY (`id_goods`) REFERENCES `goods` (`id_goods`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discount` (
  `id_discount` int(11) NOT NULL AUTO_INCREMENT,
  `numcard` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `date_register` datetime NOT NULL,
  `date_used` datetime NOT NULL,
  `percent` varchar(50) DEFAULT NULL,
  `sum_checks` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`id_discount`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite` (
  `id_favorite` int(11) NOT NULL AUTO_INCREMENT,
  `id_goods` bigint(20) NOT NULL,
  `id_categoryFavorite` int(11) NOT NULL,
  `buttonName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_favorite`),
  KEY `fk_favorite_goods1_idx` (`id_goods`),
  KEY `fk_favorite_categoryFavorite1_idx` (`id_categoryFavorite`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `id_goods` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `code` varchar(50) CHARACTER SET utf8 NOT NULL,
  `residue` decimal(7,3) DEFAULT NULL,
  `price` decimal(9,2) DEFAULT NULL,
  `id_categoryGoods` int(11) NOT NULL,
  `price_opt` decimal(9,2) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id_goods`),
  UNIQUE KEY `id_goods_UNIQUE` (`id_goods`),
  KEY `fk_goods_categoryGoods1_idx` (`id_categoryGoods`),
  CONSTRAINT `fk_goods_categoryGoods1` FOREIGN KEY (`id_categoryGoods`) REFERENCES `categorygoods` (`id_categoryGoods`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1113 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `goodsaccounting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goodsaccounting` (
  `id_goodsAccounting` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `checkin` tinyint(1) DEFAULT NULL,
  `residue_new` decimal(7,3) DEFAULT NULL,
  `residue_diff` decimal(7,3) DEFAULT NULL,
  `id_goods` bigint(20) NOT NULL,
  PRIMARY KEY (`id_goodsAccounting`),
  UNIQUE KEY `id_goods_UNIQUE` (`id_goodsAccounting`),
  KEY `fk_goodsAccounting_goods1_idx` (`id_goods`),
  CONSTRAINT `fk_goodsAccounting_goods1` FOREIGN KEY (`id_goods`) REFERENCES `goods` (`id_goods`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `goodsbackup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goodsbackup` (
  `id_goods` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `code` varchar(50) CHARACTER SET utf8 NOT NULL,
  `residue` decimal(7,3) DEFAULT NULL,
  `price` decimal(9,2) DEFAULT NULL,
  `price_opt` decimal(9,2) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `id_categoryGoods` int(11) NOT NULL,
  PRIMARY KEY (`id_goods`),
  UNIQUE KEY `id_goods_UNIQUE` (`id_goods`),
  KEY `fk_goods_categoryGoods1_idx` (`id_categoryGoods`),
  CONSTRAINT `fk_goods_categoryGoods10` FOREIGN KEY (`id_categoryGoods`) REFERENCES `categorygoods` (`id_categoryGoods`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `payment` decimal(9,2) NOT NULL,
  `date` datetime NOT NULL,
  `note` varchar(100) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id_order`),
  UNIQUE KEY `id_order_UNIQUE` (`id_order`),
  KEY `fk_orders_userSwing1_idx` (`id_user`),
  CONSTRAINT `fk_orders_userSwing1` FOREIGN KEY (`id_user`) REFERENCES `userswing` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `reportsaccounting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reportsaccounting` (
  `id_reportsAccounting` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `residue_new` decimal(7,3) DEFAULT NULL,
  `residue_diff` decimal(7,3) DEFAULT NULL,
  `id_goods` bigint(20) NOT NULL,
  PRIMARY KEY (`id_reportsAccounting`),
  UNIQUE KEY `id_goods_UNIQUE` (`id_reportsAccounting`),
  KEY `fk_reportsAccounting_goods1_idx` (`id_goods`),
  CONSTRAINT `fk_reportsAccounting_goods1` FOREIGN KEY (`id_goods`) REFERENCES `goods` (`id_goods`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `total`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `total` (
  `id_total` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `spare` decimal(9,2) DEFAULT NULL,
  `sumGoods` decimal(9,2) NOT NULL,
  `profit` decimal(9,2) DEFAULT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id_total`),
  KEY `fk_total_new_userSwing1_idx` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `trial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trial` (
  `id_trial` int(11) NOT NULL AUTO_INCREMENT,
  `firstdate` datetime NOT NULL,
  `lastdate` datetime NOT NULL,
  `created` tinyint(1) NOT NULL,
  `license` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_trial`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `idRole` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_HEAD_USER'),(3,'ROLE_USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `userswing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userswing` (
  `id_user` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `last_login` datetime NOT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `pass` varchar(50) NOT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `admin` int(11) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  KEY `fk_userSwing_user_roles1_idx` (`admin`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `writeoff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `writeoff` (
  `id_writeoff` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `sum` decimal(9,2) DEFAULT NULL,
  `note` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `id_user` bigint(20) NOT NULL,
  PRIMARY KEY (`id_writeoff`),
  UNIQUE KEY `id_writeoffList_UNIQUE` (`id_writeoff`),
  KEY `fk_writeoff_userSwing1_idx` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `writeofflist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `writeofflist` (
  `id_writeoffList` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(7,3) NOT NULL,
  `id_goods` bigint(20) NOT NULL,
  `id_writeoff` bigint(20) NOT NULL,
  PRIMARY KEY (`id_writeoffList`),
  KEY `fk_check1_goods1_idx` (`id_goods`),
  KEY `fk_writeoffList_writeoff1_idx` (`id_writeoff`),
  CONSTRAINT `fk_check1_goods10` FOREIGN KEY (`id_goods`) REFERENCES `goods` (`id_goods`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_writeoffList_writeoff1` FOREIGN KEY (`id_writeoff`) REFERENCES `writeoff` (`id_writeoff`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
