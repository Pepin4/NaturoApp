-- MySQL dump 10.13  Distrib 8.4.2-2, for Linux (x86_64)
--
-- Host: localhost    Database: byamihnlks2uts0m08ed
-- ------------------------------------------------------
-- Server version	8.4.2-2

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
/*!50717 SELECT COUNT(*) INTO @rocksdb_has_p_s_session_variables FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'performance_schema' AND TABLE_NAME = 'session_variables' */;
/*!50717 SET @rocksdb_get_is_supported = IF (@rocksdb_has_p_s_session_variables, 'SELECT COUNT(*) INTO @rocksdb_is_supported FROM performance_schema.session_variables WHERE VARIABLE_NAME=\'rocksdb_bulk_load\'', 'SELECT 0') */;
/*!50717 PREPARE s FROM @rocksdb_get_is_supported */;
/*!50717 EXECUTE s */;
/*!50717 DEALLOCATE PREPARE s */;
/*!50717 SET @rocksdb_enable_bulk_load = IF (@rocksdb_is_supported, 'SET SESSION rocksdb_bulk_load = 1', 'SET @rocksdb_dummy_bulk_load = 0') */;
/*!50717 PREPARE s FROM @rocksdb_enable_bulk_load */;
/*!50717 EXECUTE s */;
/*!50717 DEALLOCATE PREPARE s */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `ad_id` int NOT NULL AUTO_INCREMENT,
  `ad_number` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ad_street` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fk_city` int NOT NULL,
  PRIMARY KEY (`ad_id`),
  KEY `fk_city` (`fk_city`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`fk_city`) REFERENCES `city` (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'10','Rue de Rivoli',1),(2,'20','Avenue des Champs',2),(3,'15','Boulevard Longchamp',3),(4,'5','Rue Neuve',4),(5,'8','Place Saint-Lambert',5),(6,'12','Rue de Fer',6),(7,'7','Grand Rue',7),(8,'3','Rue de la Gare',8),(9,'15','Rue Lafayette',1),(10,'22','Avenue Louise',2),(11,'5','Rue de la Liberté',3),(12,'10','Boulevard de la Santé',1),(13,'12','Rue des Pharmaciens',2),(14,'7','Place du Marché',3),(15,'18','Rue des Fleurs',1),(16,'27','Boulevard Saint-Michel',1),(17,'3','Avenue des Champs',2),(18,'45','Place Royale',2),(19,'8','Grand Rue',3),(20,'23','Rue des Jardins',3),(21,'30','Avenue Victor Hugo',1),(22,'14','Rue du Commerce',2),(23,'5','Place de la Gare',3),(24,'2','Boulevard des Capucines',1);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `a_id` int NOT NULL AUTO_INCREMENT,
  `a_date_time` datetime(6) NOT NULL,
  `a_subject` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `a_the_excesses` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_eating_habits` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_sleep_quality` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_physical_activity` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_blood_circulation` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_respiratory_capacity` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_transit` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_stress_reaction` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `a_note` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `fk_patient` int NOT NULL,
  `fk_user` int NOT NULL,
  PRIMARY KEY (`a_id`),
  KEY `fk_user` (`fk_user`),
  KEY `fk_patient` (`fk_patient`),
  CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`fk_user`) REFERENCES `user` (`u_id`),
  CONSTRAINT `fk_patient` FOREIGN KEY (`fk_patient`) REFERENCES `patient` (`p_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,'2025-06-01 09:00:00.000000','Consultation bilan santé','Tabac','Moyenne','Bonne','Modérée','Normale','Normale','Régulier','Faible','Rien à signaler',1,1),(2,'2025-06-02 10:30:00.000000','Suivi allergie','Alcool','Mauvaise','Moyenne','Faible','Faible','Moyenne','Irrégulier','Modérée','Allergie saisonnière active',2,2),(3,'2025-06-03 14:00:00.000000','Consultation stress','Sédentarité','Bonne','Mauvaise','Faible','Moyenne','Faible','Régulier','Élevé','Stress important',3,1),(4,'2025-06-04 16:00:00.000000','Bilan hypertension','Excès de sel','Moyenne','Bonne','Modérée','Hypertension détectée','Normale','Régulier','Faible','Suivi traitement',4,2),(5,'2025-06-05 09:30:00.000000','Suivi diabète','Sédentarité','Mauvaise','Moyenne','Faible','Normale','Normale','Irrégulier','Modérée','Glycémie élevée',5,1),(6,'2025-06-06 11:00:00.000000','Consultation migraine','Café','Bonne','Mauvaise','Modérée','Normale','Faible','Régulier','Élevé','Crises fréquentes',6,2),(7,'2025-06-07 15:00:00.000000','Suivi arthrite','Alcool','Moyenne','Mauvaise','Faible','Faible','Moyenne','Irrégulier','Modérée','Douleurs articulaires',7,1),(8,'2025-06-08 13:30:00.000000','Consultation asthme','Tabac','Bonne','Bonne','Modérée','Normale','Faible','Régulier','Faible','Crises d\'asthme',8,2),(9,'2025-06-09 10:00:00.000000','Suivi infection urinaire','Alcool','Moyenne','Bonne','Faible','Normale','Normale','Irrégulier','Modérée','Infection récidivante',9,1),(10,'2025-06-10 09:00:00.000000','Bilan eczéma','Sédentarité','Bonne','Mauvaise','Modérée','Faible','Moyenne','Régulier','Élevé','Plaques cutanées',10,2),(11,'2025-06-11 08:30:00.000000','Contrôle annuel','Tabac','Bonne','Bonne','Élevée','Normale','Normale','Régulier','Faible','Patient en forme',1,1),(12,'2025-06-12 09:45:00.000000','Réévaluation traitement','Alcool','Moyenne','Moyenne','Modérée','Normale','Normale','Irrégulier','Modérée','Nécessite ajustement médication',2,2),(13,'2025-06-13 14:15:00.000000','Consultation sommeil','Sédentarité','Mauvaise','Mauvaise','Faible','Faible','Faible','Irrégulier','Élevé','Apnée du sommeil suspectée',3,1),(14,'2025-06-14 10:00:00.000000','Bilan cardiovasculaire','Excès de sel','Moyenne','Bonne','Faible','Hypertension','Moyenne','Régulier','Modéré','Surveillance continue',4,2),(15,'2025-06-15 16:30:00.000000','Suivi glycémie','Alcool','Mauvaise','Moyenne','Faible','Normale','Normale','Irrégulier','Modérée','Contrôle serré recommandé',5,1),(16,'2025-06-16 11:15:00.000000','Consultation neurologique','Café','Bonne','Mauvaise','Modérée','Normale','Faible','Régulier','Élevé','Migraine chronique',6,2),(17,'2025-06-17 15:45:00.000000','Réévaluation douleur','Alcool','Moyenne','Mauvaise','Faible','Faible','Moyenne','Irrégulier','Modérée','Arthrite évolutive',7,1),(18,'2025-06-18 09:00:00.000000','Suivi asthme','Tabac','Bonne','Bonne','Modérée','Normale','Faible','Régulier','Faible','Traitement stabilisé',8,2),(19,'2025-06-19 10:30:00.000000','Contrôle infection','Alcool','Moyenne','Bonne','Faible','Normale','Normale','Irrégulier','Modérée','Antibiothérapie prolongée',9,1),(20,'2025-06-20 14:00:00.000000','Suivi dermatologique','Sédentarité','Bonne','Mauvaise','Modérée','Faible','Moyenne','Régulier','Élevé','Eczéma chronique',10,2),(21,'2025-06-21 13:00:00.000000','Bilan nutritionnel','Tabac','Moyenne','Bonne','Modérée','Normale','Normale','Régulier','Faible','Amélioration à poursuivre',1,1),(22,'2025-06-22 15:30:00.000000','Révision traitement allergie','Alcool','Mauvaise','Moyenne','Faible','Faible','Moyenne','Irrégulier','Modérée','Allergie atténuée',2,2),(23,'2025-06-23 09:15:00.000000','Consultation psychologique','Sédentarité','Bonne','Mauvaise','Faible','Normale','Faible','Régulier','Élevé','Stress chronique',3,1),(24,'2025-06-24 11:45:00.000000','Suivi tension artérielle','Excès de sel','Moyenne','Bonne','Modérée','Hypertension','Normale','Régulier','Modéré','Traitement efficace',4,2),(25,'2025-06-25 10:00:00.000000','Contrôle diabète','Alcool','Mauvaise','Moyenne','Faible','Normale','Normale','Irrégulier','Modérée','Stabilisation des symptômes',5,1);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_issue_solution`
--

LOCK TABLES `appointment_issue_solution` WRITE;
/*!40000 ALTER TABLE `appointment_issue_solution` DISABLE KEYS */;
INSERT INTO `appointment_issue_solution` VALUES (26,1,16),(27,2,17),(28,3,18),(29,4,19),(30,5,20),(31,6,21),(32,7,22),(33,8,23),(34,9,24),(35,10,25),(36,11,1),(37,12,2),(38,13,3),(39,14,4),(40,15,5),(41,16,6),(42,17,7),(43,18,8),(44,19,9),(45,20,10),(46,21,11),(47,22,12),(48,23,13),(49,24,14),(50,25,15);
/*!40000 ALTER TABLE `appointment_issue_solution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `c_id` int NOT NULL AUTO_INCREMENT,
  `c_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `c_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fk_country` int NOT NULL,
  PRIMARY KEY (`c_id`),
  KEY `fk_country` (`fk_country`),
  CONSTRAINT `city_ibfk_1` FOREIGN KEY (`fk_country`) REFERENCES `country` (`co_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'75000','Paris',1),(2,'69000','Lyon',1),(3,'13000','Marseille',1),(4,'1000','Bruxelles',2),(5,'4000','Liège',2),(6,'5000','Namur',2),(7,'L-1009','Luxembourg',4),(8,'L-4001','Esch-sur-Alzette',4);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `ph_id` int NOT NULL AUTO_INCREMENT,
  `ph_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ph_email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ph_id`),
  CONSTRAINT `contact_chk_1` CHECK (((`ph_number` is not null) or (`ph_email` is not null)))
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'0612345678','admin@example.com'),(2,'0698765432','jury@example.com'),(3,'0600112233','patient1@example.com'),(4,'0600223344','patient2@example.com'),(5,'0700112233','supplier1@example.com'),(6,'0700223344','supplier2@example.com'),(7,'0600334455','patient3@example.com'),(8,'0600445566','patient4@example.com'),(9,'0600556677','patient5@example.com'),(10,'0700334455','supplier3@example.com'),(11,'0700445566','supplier4@example.com'),(12,'0700556677','supplier5@example.com'),(13,'0600667788','patient6@example.com'),(14,'0600778899','patient7@example.com'),(15,'0600889900','patient8@example.com'),(16,'0600990011','patient9@example.com'),(17,'0600112233','patient10@example.com'),(18,'0710667788','supplier6@example.com'),(19,'0710778899','supplier7@example.com'),(20,'0710889900','supplier8@example.com'),(21,'0710990011','supplier9@example.com'),(22,'0710112233','supplier10@example.com');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `co_id` int NOT NULL AUTO_INCREMENT,
  `co_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `co_code_iso` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`co_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'France','FR'),(2,'Belgique','BE'),(3,'Suisse','CH'),(4,'Luxembourg','LU'),(5,'Monaco','MC'),(6,'Canada','CA'),(7,'République Démocratique du Congo','CD'),(8,'Côte d\'Ivoire','CI'),(9,'Sénégal','SN'),(10,'Madagascar','MG'),(11,'Cameroun','CM'),(12,'Rwanda','RW'),(13,'Tchad','TD'),(14,'Burkina Faso','BF'),(15,'Mali','ML'),(16,'Niger','NE'),(17,'Soudan','SD'),(18,'Bénin','BJ'),(19,'Honduras','HN'),(20,'Gabon','GA'),(21,'République du Congo','CG'),(22,'Maurice','MU'),(23,'Togo','TG'),(24,'Seychelles','SC'),(25,'Comores','KM'),(26,'Djibouti','DJ'),(27,'Vanuatu','VU'),(28,'Haïti','HT'),(29,'République Centrafricaine','CF'),(30,'Burundi','BI'),(31,'Sainte-Lucie','LC'),(32,'Saint-Kitts-et-Nevis','KN'),(33,'Grenade','GD'),(34,'Saint-Vincent-et-les-Grenadines','VC'),(35,'Cap-Vert','CV');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue` (
  `i_id` int NOT NULL AUTO_INCREMENT,
  `i_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `i_origin` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `i_constraint` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `i_description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`i_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,'Gastro-entérite','Infection alimentaire','Repos et hydratation','Inflammation de l\'estomac et des intestins'),(2,'Tension oculaire','Fatigue visuelle','Réduire usage écran','Fatigue et douleur dans les yeux'),(3,'Insuffisance rénale','Maladie chronique','Dialyse nécessaire','Fonction rénale diminuée'),(4,'Dermatite','Allergie ou irritation','Éviter allergènes','Inflammation de la peau avec rougeurs'),(5,'Hypoglycémie','Déséquilibre alimentaire','Apport sucré immédiat','Niveau trop bas de sucre dans le sang'),(6,'Ostéoporose','Carence en calcium','Suppléments calciques','Diminution de la densité osseuse'),(7,'Bronchite','Infection respiratoire','Repos, médicaments','Inflammation des bronches'),(8,'Constipation','Manque de fibres','Alimentation riche en fibres','Difficulté à évacuer les selles'),(9,'Fièvre persistante','Infection ou inflammation','Traitement symptomatique','Élévation prolongée de la température corporelle'),(10,'Céphalées de tension','Stress','Relaxation','Maux de tête liés au stress'),(11,'Syndrome du côlon irritable','Trouble digestif','Gestion du stress','Troubles intestinaux chroniques'),(12,'Névralgie','Atteinte nerveuse','Analgésiques spécifiques','Douleur nerveuse intense'),(13,'Troubles de la mémoire','Vieillissement','Exercices cognitifs','Difficultés à se souvenir'),(14,'Sinusite','Infection des sinus','Antibiotiques','Inflammation des sinus'),(15,'Arthrose','Usure articulaire','Kinésithérapie','Détérioration du cartilage articulaire'),(16,'Anémie','Carence en fer','Suppléments en fer','Diminution du taux d\'hémoglobine dans le sang'),(17,'Allergie saisonnière','Pollen','Éviter exposition','Réactions allergiques aux pollens'),(18,'Dépression','Facteurs psychologiques','Thérapie, médication','Trouble de l\'humeur prolongé'),(19,'Hypertension','Facteurs génétiques','Régime et médication','Pression artérielle élevée'),(20,'Diabète de type 2','Surpoids, génétique','Régime et médicaments','Mauvaise régulation du glucose sanguin'),(21,'Migraine','Stress, alimentation','Antalgiques spécifiques','Maux de tête sévères'),(22,'Arthrite rhumatoïde','Maladie auto-immune','Anti-inflammatoires','Inflammation des articulations'),(23,'Asthme','Allergies, environnement','Bronchodilatateurs','Difficultés respiratoires récurrentes'),(24,'Infection urinaire','Bactéries','Antibiotiques','Infection des voies urinaires'),(25,'Eczéma','Facteurs allergiques','Crèmes hydratantes','Inflammation chronique de la peau'),(26,'Fibromyalgie','Causes multiples','Traitements symptomatiques','Douleurs musculaires et fatigue'),(27,'Goutte','Accumulation d\'acide urique','Médicaments spécifiques','Douleur articulaire intense'),(28,'Hépatite','Virus','Traitement antiviral','Inflammation du foie'),(29,'Insomnie','Stress, habitudes','Thérapies, somnifères','Difficulté à dormir'),(30,'Obésité','Surconsommation alimentaire','Régime, activité physique','Excès de masse corporelle');
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_solution`
--

LOCK TABLES `issue_solution` WRITE;
/*!40000 ALTER TABLE `issue_solution` DISABLE KEYS */;
INSERT INTO `issue_solution` VALUES (1,16,16),(2,17,17),(3,18,18),(4,19,19),(5,20,20),(6,21,21),(7,22,22),(8,23,23),(9,24,24),(10,25,25),(11,26,26),(12,27,27),(13,28,28),(14,29,29),(15,30,30),(16,1,1),(17,2,2),(18,3,3),(19,4,4),(20,5,5),(21,6,6),(22,7,7),(23,8,8),(24,9,9),(25,10,10),(26,11,11),(27,12,12),(28,13,13),(29,14,14),(30,15,15);
/*!40000 ALTER TABLE `issue_solution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_info`
--

DROP TABLE IF EXISTS `medical_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_info` (
  `mi_id` int NOT NULL AUTO_INCREMENT,
  `mi_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mi_description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mi_treatment` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mi_date_added` datetime(6) NOT NULL,
  `fk_patient` int NOT NULL,
  PRIMARY KEY (`mi_id`),
  KEY `fk_patient_medical_info` (`fk_patient`),
  CONSTRAINT `fk_patient_medical_info` FOREIGN KEY (`fk_patient`) REFERENCES `patient` (`p_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_info`
--

LOCK TABLES `medical_info` WRITE;
/*!40000 ALTER TABLE `medical_info` DISABLE KEYS */;
INSERT INTO `medical_info` VALUES (26,'Allergie','Allergie au pollen','Antihistaminiques','2025-05-30 09:54:40.000000',1),(27,'Asthme','Asthme léger intermittent','Inhalateur','2025-05-30 09:54:40.000000',1),(28,'Sinusite chronique','Sinusite fréquente','Antibiotiques','2025-05-30 09:54:40.000000',1),(29,'Dermatite','Eczéma sur les mains','Crèmes hydratantes','2025-05-30 09:54:40.000000',1),(30,'Fatigue chronique','Sensation de fatigue persistante','Repos et vitamines','2025-05-30 09:54:40.000000',1),(31,'Diabète','Diabète de type 2','Insuline','2025-05-30 09:54:40.000000',2),(32,'Hypertension','Hypertension légère','Inhibiteurs ACE','2025-05-30 09:54:40.000000',2),(33,'Neuropathie','Douleurs nerveuses','Antidouleurs','2025-05-30 09:54:40.000000',2),(34,'Hypertension','Hypertension artérielle modérée','Inhibiteurs ACE','2025-05-30 09:54:40.000000',3),(35,'Cholestérol','Cholestérol élevé','Statines','2025-05-30 09:54:40.000000',3),(36,'Arthrite','Douleurs articulaires','Anti-inflammatoires','2025-05-30 09:54:40.000000',3),(37,'Anxiété','Troubles anxieux','Thérapie cognitive','2025-05-30 09:54:40.000000',3),(38,'Insomnie','Troubles du sommeil','Mélatonine','2025-05-30 09:54:40.000000',3),(39,'Asthme','Asthme léger intermittent','Inhalateur','2025-05-30 09:54:40.000000',4),(40,'Reflux gastro-œsophagien','Brûlures d\'estomac fréquentes','IPP','2025-05-30 09:54:40.000000',5),(41,'Migraines','Migraines chroniques','Triptans','2025-05-30 09:54:40.000000',5),(42,'Allergie alimentaire','Allergie aux noix','Éviction alimentaire','2025-05-30 09:54:40.000000',5),(43,'Dépression','Dépression modérée','Antidépresseurs','2025-05-30 09:54:40.000000',5),(44,'Arthrite','Arthrite rhumatoïde','Anti-inflammatoires','2025-05-30 09:54:40.000000',6),(45,'Dépression','Dépression légère','Thérapie et antidépresseurs','2025-05-30 09:54:40.000000',6),(46,'Fatigue chronique','Fatigue persistante','Vitamines','2025-05-30 09:54:40.000000',6),(47,'Hypertension','Hypertension légère','Inhibiteurs ACE','2025-05-30 09:54:40.000000',6),(48,'Problèmes digestifs','Constipation chronique','Fibres alimentaires','2025-05-30 09:54:40.000000',6),(49,'Dépression','Dépression légère','Thérapie et antidépresseurs','2025-05-30 09:54:40.000000',7),(50,'Anxiété','Troubles anxieux','Thérapie cognitive','2025-05-30 09:54:40.000000',7),(51,'Cholestérol','Cholestérol élevé','Statines','2025-05-30 09:54:40.000000',8),(52,'Hypertension','Hypertension légère','Inhibiteurs ACE','2025-05-30 09:54:40.000000',8),(53,'Migraines','Migraines occasionnelles','Triptans','2025-05-30 09:54:40.000000',8),(54,'Migraines','Migraines chroniques','Triptans','2025-05-30 09:54:40.000000',9),(55,'Insomnie','Troubles du sommeil','Mélatonine','2025-05-30 09:54:40.000000',9),(56,'Allergie','Allergie aux acariens','Antihistaminiques','2025-05-30 09:54:40.000000',9),(57,'Asthme','Asthme léger intermittent','Inhalateur','2025-05-30 09:54:40.000000',9),(58,'Insomnie','Troubles du sommeil','Mélatonine','2025-05-30 09:54:40.000000',10);
/*!40000 ALTER TABLE `medical_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `p_id` int NOT NULL AUTO_INCREMENT,
  `p_date_of_birth` date NOT NULL,
  `p_gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `p_firstname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `p_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `p_family_situation` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `p_professional_situation` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `fk_address` int NOT NULL,
  `fk_contact` int NOT NULL,
  PRIMARY KEY (`p_id`),
  KEY `fk_address` (`fk_address`),
  KEY `fk_contact` (`fk_contact`),
  CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`fk_address`) REFERENCES `address` (`ad_id`),
  CONSTRAINT `patient_ibfk_2` FOREIGN KEY (`fk_contact`) REFERENCES `contact` (`ph_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'1980-01-15','M','Jean','Dupont','Marié','Ingénieur',3,3),(2,'1990-07-22','F','Marie','Curie','Célibataire','Chercheuse',4,4),(3,'1985-04-12','F','Claire','Martin','Célibataire','Designer',9,7),(4,'1978-09-30','M','Lucas','Bernard','Marié','Professeur',10,8),(5,'1992-11-05','F','Sophie','Lemoine','Mariée','Pharmacienne',11,9),(6,'1980-01-10','M','Antoine','Dupont','Marié','Ingénieur',15,13),(7,'1975-03-22','F','Isabelle','Moreau','Divorcée','Avocate',16,14),(8,'1990-07-08','M','Julien','Faure','Célibataire','Artiste',17,15),(9,'1988-12-15','F','Marion','Leroy','Mariée','Enseignante',18,16),(10,'1995-05-05','M','Mathieu','Petit','Célibataire','Etudiant',19,17);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solution`
--

DROP TABLE IF EXISTS `solution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solution` (
  `s_id` int NOT NULL AUTO_INCREMENT,
  `s_remedy` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `s_benefits` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fk_supplier` int NOT NULL,
  PRIMARY KEY (`s_id`),
  KEY `fk_supplier` (`fk_supplier`),
  CONSTRAINT `solution_ibfk_1` FOREIGN KEY (`fk_supplier`) REFERENCES `supplier` (`su_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solution`
--

LOCK TABLES `solution` WRITE;
/*!40000 ALTER TABLE `solution` DISABLE KEYS */;
INSERT INTO `solution` VALUES (1,'Suppléments de fer','Augmente les taux d\'hémoglobine',1),(2,'Antihistaminiques','Réduit les symptômes allergiques',2),(3,'Thérapie cognitive','Améliore la santé mentale',3),(4,'Médicaments antihypertenseurs','Abaisse la pression artérielle',4),(5,'Injections d\'insuline','Régule le taux de glucose',5),(6,'Antimigraineux','Soulage les crises de migraine',6),(7,'Anti-inflammatoires','Réduit l\'inflammation articulaire',7),(8,'Inhalateurs','Améliore la respiration',8),(9,'Antibiotiques urinaires','Élimine les infections',1),(10,'Crèmes hydratantes','Apaise la peau',2),(11,'Traitements antalgiques','Soulage douleurs musculaires',3),(12,'Médicaments contre la goutte','Réduit l\'acidité urique',4),(13,'Traitement antiviral','Réduit la charge virale',5),(14,'Somnifères','Améliore la qualité du sommeil',6),(15,'Programmes de perte de poids','Favorise la réduction de poids',7),(16,'Solution hydratante','Réhydratation rapide, soulagement des symptômes',1),(17,'Gouttes oculaires','Soulagement de la fatigue oculaire',2),(18,'Dialyse','Élimination des déchets sanguins',3),(19,'Crème anti-inflammatoire','Réduit rougeurs et démangeaisons',4),(20,'Sucre oral','Augmentation rapide du taux de glucose',5),(21,'Suppléments de calcium','Renforce les os',6),(22,'Bronchodilatateurs inhalés','Amélioration de la respiration',7),(23,'Laxatifs','Facilite l\'évacuation',8),(24,'Antipyrétiques','Réduction de la fièvre',1),(25,'Relaxants musculaires','Détente des muscles',2),(26,'Probiotiques','Amélioration de la flore intestinale',3),(27,'Antalgiques nerveux','Soulagement de la douleur nerveuse',4),(28,'Exercices cognitifs','Stimulation de la mémoire',5),(29,'Antibiotiques','Traitement des infections bactériennes',6),(30,'Kinésithérapie','Amélioration de la mobilité articulaire',7);
/*!40000 ALTER TABLE `solution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `su_id` int NOT NULL AUTO_INCREMENT,
  `su_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fk_address` int NOT NULL,
  `fk_contact` int NOT NULL,
  PRIMARY KEY (`su_id`),
  KEY `fk_address` (`fk_address`),
  KEY `fk_contact` (`fk_contact`),
  CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`fk_address`) REFERENCES `address` (`ad_id`),
  CONSTRAINT `supplier_ibfk_2` FOREIGN KEY (`fk_contact`) REFERENCES `contact` (`ph_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'NaturEssence',12,10),(2,'PharmaLux',13,11),(3,'BioSanté',14,12),(4,'HerbalPlus',20,18),(5,'MediLux',21,19),(6,'BioPharm',22,20),(7,'NatureCo',23,21),(8,'EcoHealth',24,22);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `u_id` int NOT NULL AUTO_INCREMENT,
  `u_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `u_password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `fk_address` int NOT NULL,
  `fk_contact` int NOT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `unique_u_name` (`u_name`),
  UNIQUE KEY `UKkl51hjsu0irrl3dev4v48nmj4` (`u_name`),
  KEY `fk_address` (`fk_address`),
  KEY `fk_contact` (`fk_contact`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`fk_address`) REFERENCES `address` (`ad_id`),
  CONSTRAINT `user_ibfk_2` FOREIGN KEY (`fk_contact`) REFERENCES `contact` (`ph_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','Terrier6*',1,1),(2,'jury','Jury123*',2,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50112 SET @disable_bulk_load = IF (@is_rocksdb_supported, 'SET SESSION rocksdb_bulk_load = @old_rocksdb_bulk_load', 'SET @dummy_rocksdb_bulk_load = 0') */;
/*!50112 PREPARE s FROM @disable_bulk_load */;
/*!50112 EXECUTE s */;
/*!50112 DEALLOCATE PREPARE s */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-31 14:43:24
