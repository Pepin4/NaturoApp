-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: naturoapp
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `ad_id` int NOT NULL AUTO_INCREMENT,
  `ad_number` varchar(5) COLLATE utf8mb4_general_ci NOT NULL,
  `ad_street` varchar(128) COLLATE utf8mb4_general_ci NOT NULL,
  `fk_city` int NOT NULL,
  PRIMARY KEY (`ad_id`),
  KEY `fk_city` (`fk_city`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`fk_city`) REFERENCES `city` (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `a_id` int NOT NULL AUTO_INCREMENT,
  `a_date_time` datetime NOT NULL,
  `a_subject` varchar(1024) COLLATE utf8mb4_general_ci NOT NULL,
  `a_the_excesses` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_eating_habits` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_sleep_quality` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_physical_activity` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_blood_circulation` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_respiratory_capacity` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_transit` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_stress_reaction` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_note` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `fk_patient` int NOT NULL,
  `fk_user` int NOT NULL,
  PRIMARY KEY (`a_id`),
  KEY `fk_user` (`fk_user`),
  KEY `fk_patient` (`fk_patient`),
  CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`fk_user`) REFERENCES `user` (`u_id`),
  CONSTRAINT `fk_patient` FOREIGN KEY (`fk_patient`) REFERENCES `patient` (`p_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `appointment_issue_solution`
--

DROP TABLE IF EXISTS `appointment_issue_solution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment_issue_solution` (
  `ais_id` int NOT NULL AUTO_INCREMENT,
  `fk_appointment` int NOT NULL,
  `fk_issue_solution` int NOT NULL,
  PRIMARY KEY (`ais_id`),
  KEY `fk_issue_solution` (`fk_issue_solution`),
  KEY `fk_appointment_issue_solution` (`fk_appointment`),
  CONSTRAINT `appointment_issue_solution_ibfk_2` FOREIGN KEY (`fk_issue_solution`) REFERENCES `issue_solution` (`is_id`),
  CONSTRAINT `fk_appointment_issue_solution` FOREIGN KEY (`fk_appointment`) REFERENCES `appointment` (`a_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `c_id` int NOT NULL AUTO_INCREMENT,
  `c_code` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `c_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `fk_country` int NOT NULL,
  PRIMARY KEY (`c_id`),
  KEY `fk_country` (`fk_country`),
  CONSTRAINT `city_ibfk_1` FOREIGN KEY (`fk_country`) REFERENCES `country` (`co_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `ph_id` int NOT NULL AUTO_INCREMENT,
  `ph_number` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ph_email` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ph_id`),
  CONSTRAINT `contact_chk_1` CHECK (((`ph_number` is not null) or (`ph_email` is not null)))
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `co_id` int NOT NULL AUTO_INCREMENT,
  `co_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `co_code_iso` varchar(2) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`co_id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue` (
  `i_id` int NOT NULL AUTO_INCREMENT,
  `i_name` varchar(256) COLLATE utf8mb4_general_ci NOT NULL,
  `i_origin` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `i_constraint` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `i_description` varchar(1024) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`i_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `issue_solution`
--

DROP TABLE IF EXISTS `issue_solution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue_solution` (
  `is_id` int NOT NULL AUTO_INCREMENT,
  `fk_issue` int NOT NULL,
  `fk_solution` int DEFAULT NULL,
  PRIMARY KEY (`is_id`),
  KEY `fk_issue` (`fk_issue`),
  KEY `fk_solution` (`fk_solution`),
  CONSTRAINT `issue_solution_ibfk_1` FOREIGN KEY (`fk_issue`) REFERENCES `issue` (`i_id`),
  CONSTRAINT `issue_solution_ibfk_2` FOREIGN KEY (`fk_solution`) REFERENCES `solution` (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medical_info`
--

DROP TABLE IF EXISTS `medical_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_info` (
  `mi_id` int NOT NULL AUTO_INCREMENT,
  `mi_type` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `mi_description` varchar(256) COLLATE utf8mb4_general_ci NOT NULL,
  `mi_treatment` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mi_date_added` datetime NOT NULL,
  `fk_patient` int NOT NULL,
  PRIMARY KEY (`mi_id`),
  KEY `fk_patient_medical_info` (`fk_patient`),
  CONSTRAINT `fk_patient_medical_info` FOREIGN KEY (`fk_patient`) REFERENCES `patient` (`p_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `p_id` int NOT NULL AUTO_INCREMENT,
  `p_date_of_birth` date NOT NULL,
  `p_gender` varchar(2) COLLATE utf8mb4_general_ci NOT NULL,
  `p_firstname` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `p_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `p_family_situation` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `p_professional_situation` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `fk_address` int NOT NULL,
  `fk_contact` int NOT NULL,
  PRIMARY KEY (`p_id`),
  KEY `fk_address` (`fk_address`),
  KEY `fk_contact` (`fk_contact`),
  CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`fk_address`) REFERENCES `address` (`ad_id`),
  CONSTRAINT `patient_ibfk_2` FOREIGN KEY (`fk_contact`) REFERENCES `contact` (`ph_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `solution`
--

DROP TABLE IF EXISTS `solution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solution` (
  `s_id` int NOT NULL AUTO_INCREMENT,
  `s_remedy` varchar(256) COLLATE utf8mb4_general_ci NOT NULL,
  `s_benefits` varchar(1024) COLLATE utf8mb4_general_ci NOT NULL,
  `fk_supplier` int NOT NULL,
  PRIMARY KEY (`s_id`),
  KEY `fk_supplier` (`fk_supplier`),
  CONSTRAINT `solution_ibfk_1` FOREIGN KEY (`fk_supplier`) REFERENCES `supplier` (`su_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `su_id` int NOT NULL AUTO_INCREMENT,
  `su_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `fk_address` int NOT NULL,
  `fk_contact` int NOT NULL,
  PRIMARY KEY (`su_id`),
  KEY `fk_address` (`fk_address`),
  KEY `fk_contact` (`fk_contact`),
  CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`fk_address`) REFERENCES `address` (`ad_id`),
  CONSTRAINT `supplier_ibfk_2` FOREIGN KEY (`fk_contact`) REFERENCES `contact` (`ph_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `u_id` int NOT NULL AUTO_INCREMENT,
  `u_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `u_password` varchar(64) COLLATE utf8mb4_general_ci NOT NULL,
  `fk_address` int NOT NULL,
  `fk_contact` int NOT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `unique_u_name` (`u_name`),
  UNIQUE KEY `UKkl51hjsu0irrl3dev4v48nmj4` (`u_name`),
  KEY `fk_address` (`fk_address`),
  KEY `fk_contact` (`fk_contact`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`fk_address`) REFERENCES `address` (`ad_id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`fk_contact`) REFERENCES `contact` (`ph_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'naturoapp'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-20  2:13:20
