-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ptbd
--

CREATE DATABASE IF NOT EXISTS ptbd;
USE ptbd;

--
-- Definition of table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
CREATE TABLE `alumno` (
  `ALUM_DNI` int(11) NOT NULL,
  `ALUM_PASSWORD` varchar(255) default NULL,
  `ALUM_NOMBRE` varchar(255) NOT NULL,
  `ALUM_TELEFONO` varchar(255) default NULL,
  PRIMARY KEY  (`ALUM_DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `alumno`
--

/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` (`ALUM_DNI`,`ALUM_PASSWORD`,`ALUM_NOMBRE`,`ALUM_TELEFONO`) VALUES 
 (15003,'iso3','Patxi Stone Riser','645543211'),
 (45820650,'iso3','David Montero','625703060');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;


--
-- Definition of table `asignatura`
--

DROP TABLE IF EXISTS `asignatura`;
CREATE TABLE `asignatura` (
  `ASIG_ID` int(11) NOT NULL auto_increment,
  `ASIG_CODIGO` int(11) NOT NULL,
  `ASIG_NOMBRE` varchar(255) NOT NULL,
  `ASIG_CREDITOS` float default NULL,
  `PROF_ID` int(11) default NULL,
  PRIMARY KEY  (`ASIG_ID`),
  UNIQUE KEY `ASIG_CODIGO` (`ASIG_CODIGO`),
  KEY `FKECBB6173F489D67A` (`PROF_ID`),
  CONSTRAINT `FKECBB6173F489D67A` FOREIGN KEY (`PROF_ID`) REFERENCES `profesor` (`PROF_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `asignatura`
--

/*!40000 ALTER TABLE `asignatura` DISABLE KEYS */;
INSERT INTO `asignatura` (`ASIG_ID`,`ASIG_CODIGO`,`ASIG_NOMBRE`,`ASIG_CREDITOS`,`PROF_ID`) VALUES 
 (1,20,'ISO',5,1),
 (2,30,'Marketing',3,2),
 (3,40,'GC',2,3);
/*!40000 ALTER TABLE `asignatura` ENABLE KEYS */;


--
-- Definition of table `evaluacion`
--

DROP TABLE IF EXISTS `evaluacion`;
CREATE TABLE `evaluacion` (
  `EVAL_ID` int(11) NOT NULL auto_increment,
  `EVAL_CONCEPTO` varchar(255) NOT NULL,
  `EVAL_NOTA` float NOT NULL,
  `ALUM_DNI` int(11) NOT NULL,
  `ASIG_ID` int(11) NOT NULL,
  PRIMARY KEY  (`EVAL_ID`),
  KEY `FK500E38ED2351A56C` (`ASIG_ID`),
  KEY `FK500E38EDC476F076` (`ALUM_DNI`),
  CONSTRAINT `FK500E38ED2351A56C` FOREIGN KEY (`ASIG_ID`) REFERENCES `asignatura` (`ASIG_ID`),
  CONSTRAINT `FK500E38EDC476F076` FOREIGN KEY (`ALUM_DNI`) REFERENCES `alumno` (`ALUM_DNI`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `evaluacion`
--

/*!40000 ALTER TABLE `evaluacion` DISABLE KEYS */;
INSERT INTO `evaluacion` (`EVAL_ID`,`EVAL_CONCEPTO`,`EVAL_NOTA`,`ALUM_DNI`,`ASIG_ID`) VALUES 
 (1,'Examen Parcial 1',10,45820650,1),
 (20,'Examen Parcial 1',0,45820650,2),
 (21,'Examen Parcial 1',0,15003,3),
 (22,'Examen Parcial 2',0,45820650,1),
 (23,'Examen Parcial 2',0,45820650,2),
 (24,'Examen Parcial 2',5,15003,3),
 (25,'Examen Parcial 3',5,45820650,1),
 (26,'Examen Parcial 3',0,45820650,2),
 (27,'Examen Parcial 3',0,15003,3),
 (28,'Examen Parcial 4',0,45820650,1),
 (29,'Examen Parcial 4',0,45820650,2),
 (30,'Examen Parcial 4',0,15003,3);
/*!40000 ALTER TABLE `evaluacion` ENABLE KEYS */;


--
-- Definition of table `matriculado`
--

DROP TABLE IF EXISTS `matriculado`;
CREATE TABLE `matriculado` (
  `ALUM_DNI` int(11) NOT NULL,
  `ASIG_ID` int(11) NOT NULL,
  PRIMARY KEY  (`ASIG_ID`,`ALUM_DNI`),
  KEY `FK48D136692351A56C` (`ASIG_ID`),
  KEY `FK48D13669C476F076` (`ALUM_DNI`),
  CONSTRAINT `FK48D136692351A56C` FOREIGN KEY (`ASIG_ID`) REFERENCES `asignatura` (`ASIG_ID`),
  CONSTRAINT `FK48D13669C476F076` FOREIGN KEY (`ALUM_DNI`) REFERENCES `alumno` (`ALUM_DNI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `matriculado`
--

/*!40000 ALTER TABLE `matriculado` DISABLE KEYS */;
INSERT INTO `matriculado` (`ALUM_DNI`,`ASIG_ID`) VALUES 
 (45820650,1),
 (45820650,2),
 (15003,3);
/*!40000 ALTER TABLE `matriculado` ENABLE KEYS */;


--
-- Definition of table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
CREATE TABLE `profesor` (
  `PROF_ID` int(11) NOT NULL auto_increment,
  `PROF_NOMBRE` varchar(255) NOT NULL,
  `PROF_DNI` int(11) default NULL,
  `PROF_PASSWORD` varchar(255) default NULL,
  `PROF_TELEFONO` varchar(255) default NULL,
  `PROF_DESPACHO` varchar(255) default NULL,
  PRIMARY KEY  (`PROF_ID`),
  UNIQUE KEY `PROF_NOMBRE` (`PROF_NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `profesor`
--

/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` (`PROF_ID`,`PROF_NOMBRE`,`PROF_DNI`,`PROF_PASSWORD`,`PROF_TELEFONO`,`PROF_DESPACHO`) VALUES 
 (1,'Iker Maister',16084303,'iso3','12345678','despacho1'),
 (2,'Sustituto Jones',666,'iso3','87654321','despacho2'),
 (3,'Paco Apocope',1503,'iso3','65465413','despacho3');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;


--
-- Definition of table `unidad`
--

DROP TABLE IF EXISTS `unidad`;
CREATE TABLE `unidad` (
  `UNID_ID` int(11) NOT NULL auto_increment,
  `UNID_ACRONIMO` varchar(255) NOT NULL,
  `UNID_TITULO` varchar(255) NOT NULL,
  `UNID_CONTENIDO` varchar(255) default NULL,
  `ASIG_ID` int(11) NOT NULL,
  PRIMARY KEY  (`UNID_ID`),
  UNIQUE KEY `UNID_ACRONIMO` (`UNID_ACRONIMO`),
  KEY `FK95794C972351A56C` (`ASIG_ID`),
  CONSTRAINT `FK95794C972351A56C` FOREIGN KEY (`ASIG_ID`) REFERENCES `asignatura` (`ASIG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `unidad`
--

/*!40000 ALTER TABLE `unidad` DISABLE KEYS */;
INSERT INTO `unidad` (`UNID_ID`,`UNID_ACRONIMO`,`UNID_TITULO`,`UNID_CONTENIDO`,`ASIG_ID`) VALUES
 (1,'Tema 1','JSP mola a saco','Ipia mola ms',1),
 (2,'Tema 1.1','Mk - El timo','Como timar',2),
 (3,'Tema 1.2','La asignatura','Es de mofa, befa o sinnimo',3);
/*!40000 ALTER TABLE `unidad` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;