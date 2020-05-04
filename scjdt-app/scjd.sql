CREATE DATABASE  IF NOT EXISTS `SCJDT` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `SCJDT`;
-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: SCJDT
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

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

--
-- Table structure for table `categoriaFuncao`
--

DROP TABLE IF EXISTS `categoriaFuncao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoriaFuncao` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCategoria` varchar(100) NOT NULL,
  `salarioCategoria` decimal(10,2) NOT NULL,
  `descricaoCategoria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriaFuncao`
--

LOCK TABLES `categoriaFuncao` WRITE;
/*!40000 ALTER TABLE `categoriaFuncao` DISABLE KEYS */;
INSERT INTO `categoriaFuncao` VALUES (1,'Tesdtde',500.00,NULL),(2,'Tesdtde',250.00,NULL),(3,'Tesdtde',12.00,NULL),(4,'Tesdtde',12.00,NULL),(5,'Motorista Junior',251.00,'Motorista de mircro ônibus');
/*!40000 ALTER TABLE `categoriaFuncao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localTrabalho`
--

DROP TABLE IF EXISTS `localTrabalho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localTrabalho` (
  `idLocalTrabalho` int(11) NOT NULL AUTO_INCREMENT,
  `nomeLocalTrabalho` varchar(100) NOT NULL,
  PRIMARY KEY (`idLocalTrabalho`),
  UNIQUE KEY `nomeLocalTrabalho` (`nomeLocalTrabalho`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localTrabalho`
--

LOCK TABLES `localTrabalho` WRITE;
/*!40000 ALTER TABLE `localTrabalho` DISABLE KEYS */;
INSERT INTO `localTrabalho` VALUES (4,'Aparecidinha'),(1,'Bairro da Ponte'),(5,'Encosta do Sol');
/*!40000 ALTER TABLE `localTrabalho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nomeUsuario` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `nomeUsuario` (`nomeUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (10,'gabriel','123456'),(15,'cassia','764139'),(20,'cascudo','teste');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'SCJDT'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-04 18:55:37
