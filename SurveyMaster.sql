-- MariaDB dump 10.19-11.2.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: Encuestas
-- ------------------------------------------------------
-- Server version	11.2.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `encuesta`
--

DROP TABLE IF EXISTS `encuesta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encuesta` (
  `idencuesta` int(6) NOT NULL,
  `contraseña` varchar(8) DEFAULT NULL,
  `titulo` varchar(100) DEFAULT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `tiempoCreacion` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idencuesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encuesta`
--

LOCK TABLES `encuesta` WRITE;
/*!40000 ALTER TABLE `encuesta` DISABLE KEYS */;
INSERT INTO `encuesta` VALUES
(-455373989,'a','Formulario sin título','Descripción del formulario',NULL),
(435,'leonardo',NULL,NULL,NULL),
(238927,'12345678','Formulario sin título','Descripción del formulario',NULL),
(250124,'1234567l','Formulario sin título','Descripción del formulario',NULL),
(252692,'1234567l','Formulario sin título','Descripción del formulario',NULL),
(262623,'12345678','Formulario sin título','Descripción del formulario',NULL),
(285022,'1234567l','Formulario sin título','Descripción del formulario',NULL),
(319639,'a','Formulario sin título','Descripción del formulario',NULL),
(322273,'12345678','Formulario sin título','Descripción del formulario',NULL),
(335932,'1234567a','preguntasadasD','descripcion de prueba numero 10000','2023-12-03 21:27:52'),
(455908,'12345678','Formulario sin título','Descripción del formulario',NULL),
(465752,'1234567l','prueba 2 preguntas texto','Descripción del formulario',NULL),
(514871,'leona534','Formulario sin título','Descripción del formulario',NULL),
(555361,'1234567l','formulario de prueba 2','Descripción del formulario',NULL),
(570517,'1234567e','Formulario sin título2','Descripción del formulario',NULL),
(712954,'1234567l','Formulario sin título','Descripción del formulario',NULL),
(761869,'1234567a','Formulario sin título','Descripción del formulario','2023-12-03 21:17:03'),
(787327,'12345678','Formulario sin título','Descripción del formulario',NULL),
(789823,'1234567l','Formulario de prueba completo','Esta es la descripci\'on mas larga que se me pudo ocurrir para un formulario',NULL),
(800139,'1234567a','prueba contraria','Descripción del formulario','2023-12-03 21:20:50'),
(872260,'1234567l','Formulario sin título 344','asdfadsf',NULL),
(967670,'1234567l','Formulario sin título','Descripción del formulario',NULL),
(973615,'1234567l','prueba solo 1 panel texto','Descripción del formulario',NULL),
(999053,'12345678','Formulario sin título','Descripción del formulario',NULL);
/*!40000 ALTER TABLE `encuesta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pregunta`
--

DROP TABLE IF EXISTS `pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pregunta` (
  `idpregunta` int(6) NOT NULL AUTO_INCREMENT,
  `numeroPregunta` int(3) DEFAULT NULL,
  `idencuesta` int(6) DEFAULT NULL,
  `texto` mediumtext DEFAULT NULL,
  `tipo_respuesta` enum('Texto','OpcionMultiple') DEFAULT NULL,
  `opciones` text DEFAULT NULL,
  PRIMARY KEY (`idpregunta`),
  KEY `idencuesta_idx` (`idencuesta`),
  CONSTRAINT `idencuesta` FOREIGN KEY (`idencuesta`) REFERENCES `encuesta` (`idencuesta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta`
--

LOCK TABLES `pregunta` WRITE;
/*!40000 ALTER TABLE `pregunta` DISABLE KEYS */;
INSERT INTO `pregunta` VALUES
(1,1,967670,'Pregunta de texto','Texto',NULL),
(2,2,967670,'Pregunta de opciones multiples','OpcionMultiple','Opción1,Opción2,Opción3'),
(3,1,570517,'Pregunta de texto 2','Texto',NULL),
(4,2,570517,'Pregunta de opciones2','OpcionMultiple','Opción1,Opción2,Opción3,Opción4'),
(5,1,789823,'¿Es ta es la primera pregunta?','OpcionMultiple','sí,no'),
(6,2,789823,'Descríbete con un adjetivo','Texto',NULL),
(7,3,789823,'Esta es una pregunta con 4 opciones','OpcionMultiple','Opción1,Opción2,Opción3,Opción4'),
(8,4,789823,'Ahora una pregunta con 5','OpcionMultiple','Opción21,Opción22,Opción23,Opción24,Opción25'),
(9,1,555361,'Pregunta sin título','Texto',NULL),
(10,2,555361,'Pregunta sin título','Texto',NULL),
(11,3,555361,'Pregunta sin título','Texto',NULL),
(12,4,555361,'Pregunta sin título','OpcionMultiple','Opción,Opción,Opción,Opción'),
(13,5,555361,'Pregunta sin título','OpcionMultiple','Opción,Opción,Opción,Opción,Opción'),
(14,6,555361,'Pregunta sin título','OpcionMultiple','Opción,Opción,Opción,Opción,Opción'),
(15,7,555361,'Pregunta sin título','Texto',NULL),
(16,8,555361,'Pregunta sin título','Texto',NULL),
(17,1,973615,'Pregunta sin título','Texto',NULL),
(18,1,465752,'Pregunta sin título','Texto',NULL),
(19,2,465752,'Pregunta sin título','Texto',NULL),
(20,1,250124,'Pregunta sin título','Texto',NULL),
(21,2,250124,'Pregunta sin título','Texto',NULL),
(22,3,250124,'Pregunta sin título','Texto',NULL),
(23,4,250124,'Pregunta sin título','Texto',NULL),
(24,5,250124,'Pregunta sin título','Texto',NULL),
(25,6,250124,'Pregunta sin título','Texto',NULL),
(26,7,250124,'Pregunta sin título','Texto',NULL),
(27,8,250124,'Pregunta sin título','Texto',NULL),
(28,9,250124,'Pregunta sin título','Texto',NULL),
(29,10,250124,'Pregunta sin título','Texto',NULL),
(30,11,250124,'Pregunta sin título','Texto',NULL),
(31,12,250124,'Pregunta sin título','Texto',NULL),
(32,13,250124,'Pregunta sin título','Texto',NULL),
(33,1,872260,'test','OpcionMultiple','si,no'),
(34,2,872260,'Pregunta sin título','OpcionMultiple','no'),
(35,3,872260,'Pregunta sin título','Texto',NULL),
(36,4,872260,'Pregunta sin título','Texto',NULL),
(37,5,872260,'Pregunta sin título','Texto',NULL),
(38,1,761869,'Pregunta sin título','Texto',NULL),
(39,1,800139,'Pregunta sin título','Texto',NULL),
(40,1,335932,'Pregunta sin título','Texto',NULL),
(41,2,335932,'Pregunta sin título','OpcionMultiple','Opción,Opción,Opción');
/*!40000 ALTER TABLE `pregunta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuestas`
--

DROP TABLE IF EXISTS `respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `respuestas` (
  `idrespuesta` int(11) NOT NULL AUTO_INCREMENT,
  `encuestaID` int(6) DEFAULT NULL,
  `numeroPregunta` int(3) DEFAULT NULL,
  `texto_respuesta` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idrespuesta`),
  KEY `idencuesta_idx` (`encuestaID`),
  CONSTRAINT `encuestaID` FOREIGN KEY (`encuestaID`) REFERENCES `encuesta` (`idencuesta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuestas`
--

LOCK TABLES `respuestas` WRITE;
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
INSERT INTO `respuestas` VALUES
(1,570517,1,'Leoanrdo romero'),
(2,570517,1,'Leonardo romero'),
(3,570517,2,'Opción4'),
(4,570517,1,'Respuesta'),
(5,570517,2,'Opción1'),
(6,570517,1,'Respuesta'),
(7,570517,2,'Opción1'),
(8,570517,1,'Respuesta'),
(9,570517,2,'Opción1'),
(10,555361,1,'Respuesta'),
(11,555361,2,'Respuesta'),
(12,555361,3,'Respuesta'),
(13,555361,4,'Opción'),
(14,555361,5,'Opción'),
(15,555361,6,'Opción'),
(16,555361,7,'Respuesta'),
(17,555361,8,'Respuesta'),
(18,555361,1,'Respuesta'),
(19,555361,2,'Respuesta'),
(20,555361,3,'Respuesta'),
(21,555361,4,'Opción'),
(22,555361,5,'Opción'),
(23,555361,6,'Opción'),
(24,555361,7,'Respuesta'),
(25,555361,8,'Respuesta'),
(26,555361,1,'Respuesta'),
(27,555361,2,'Respuesta'),
(28,555361,3,'Respuesta'),
(29,555361,4,'Opción'),
(30,555361,5,'Opción'),
(31,555361,6,'Opción'),
(32,555361,7,'Respuesta'),
(33,555361,8,'Respuesta'),
(34,555361,1,'Respuesta'),
(35,555361,2,'Respuesta'),
(36,555361,3,'Respuesta'),
(37,555361,4,'Opción'),
(38,555361,5,'Opción'),
(39,555361,6,'Opción'),
(40,555361,7,'Respuesta'),
(41,555361,8,'Respuesta'),
(42,555361,1,'Respuesta'),
(43,555361,2,'Respuesta'),
(44,555361,3,'Respuesta'),
(45,555361,4,'Opción'),
(46,555361,5,'Opción'),
(47,555361,6,'Opción'),
(48,555361,7,'Respuesta'),
(49,555361,8,'Respuesta'),
(50,555361,1,'Respuesta'),
(51,555361,2,'Respuesta'),
(52,555361,3,'Respuesta'),
(53,555361,4,'Opción'),
(54,555361,5,'Opción'),
(55,555361,6,'Opción'),
(56,555361,7,'Respuesta'),
(57,555361,8,'Respuesta'),
(58,555361,1,'Respuesta'),
(59,555361,2,'Respuesta'),
(60,555361,3,'Respuesta'),
(61,555361,4,'Opción'),
(62,555361,5,'Opción'),
(63,555361,6,'Opción'),
(64,555361,7,'Respuesta'),
(65,555361,8,'Respuesta'),
(66,872260,1,'si'),
(67,872260,2,'no'),
(68,872260,3,'Respuesta'),
(69,872260,4,'Respuesta'),
(70,872260,5,'Respuesta'),
(71,872260,1,'si'),
(72,872260,2,'no'),
(73,872260,3,'Respuesta'),
(74,872260,4,'Respuesta'),
(75,872260,5,'Respuesta'),
(76,872260,1,'si'),
(77,872260,2,'no'),
(78,872260,3,'Respuesta'),
(79,872260,4,'Respuesta'),
(80,872260,5,'Respuesta'),
(81,335932,1,'Respuesta'),
(82,335932,2,'Opción'),
(83,335932,1,'RespuestaASDFASDF'),
(84,335932,2,'Opción');
/*!40000 ALTER TABLE `respuestas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-17  1:22:59
