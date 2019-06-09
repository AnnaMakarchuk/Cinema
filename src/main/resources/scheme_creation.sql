CREATE DATABASE  IF NOT EXISTS `cinemaproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `cinemaproject`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cinemaproject
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `max_row` int(11) NOT NULL,
  `places_in_row` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (1,'Blue',6,9),(2,'Red',10,11),(3,'Yellow',5,6);
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `genre_id` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `ageLimit` int(11) DEFAULT NULL,
  `description` mediumtext,
  `isActive` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `movie_genre_id` (`genre_id`),
  CONSTRAINT `movie_genre_id` FOREIGN KEY (`genre_id`) REFERENCES `movie_genre` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'Avengers: EndGame',3,182,12,'After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to undo Thanos actions and restore order to the universe.',1),(2,'Alladin',2,128,12,'	In the heart of an enchanted city, a commoner named Aladdin and his mischievous monkey, Abu, battle to save the free-spirited Princess Jasmine. Aladdins life changes with one rub of a magic lamp as a fun-loving, shape-shifting Genie appears and grants him three wishes.',1),(3,'KuToppen',6,66,0,'Klara, a young city-calf, dreams of becoming a big star. One day she receives a letter from her father, Mosk, who wants her to visit his farm. Her mom says Mosk is a rock star, but when Clara arrives, she discovers her father is only a regular apple pie farmer.',1),(4,'Dark Phoenix',3,120,16,'Jean Grey begins to develop incredible powers that corrupt and turn her into a Dark Phoenix. Now the X-Men will have to decide if the life of a team member is worth more than all the people living in the world.',1),(5,'A Star Is Born',5,135,16,'A musician helps a young singer find fame as age and alcoholism send his own career into a downward spiral.',0),(6,'Made In Italy',5,100,16,'Riko works around a pigment than people. To shake things up, he decides to drive to Rome with his friends. Together with Sarah they take part in a protest during which Riko receives a blow to the head.',1),(7,'Poison Rose',4,120,16,'Inspired by classic film noir, Carson Phillips, an ex-football star turned PI, has a soft spot for a lady in distress.',1),(9,'Shazam!',1,132,12,'We all have a superhero inside us, it just takes a bit of magic to bring it out. In Billy Batsons case, by shouting out one word - SHAZAM. - this streetwise fourteen-year-old foster kid can turn into the grown-up superhero Shazam.',1),(10,'The Secret Life of Pets 2',6,86,0,'Continuing the story of Max and his pet friends, following their secret lives after their owners leave them for work or school each day.',1);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_genre`
--

DROP TABLE IF EXISTS `movie_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_genre`
--

LOCK TABLES `movie_genre` WRITE;
/*!40000 ALTER TABLE `movie_genre` DISABLE KEYS */;
INSERT INTO `movie_genre` VALUES (1,'comedy'),(2,'fantasy'),(3,'action'),(4,'thriller'),(5,'melodrama'),(6,'cartoon');
/*!40000 ALTER TABLE `movie_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price_type`
--

DROP TABLE IF EXISTS `price_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `price_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `row_number` int(11) NOT NULL,
  `price` double NOT NULL,
  `hall_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `price_hall_idx` (`hall_id`),
  CONSTRAINT `price_hallId` FOREIGN KEY (`hall_id`) REFERENCES `hall` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_type`
--

LOCK TABLES `price_type` WRITE;
/*!40000 ALTER TABLE `price_type` DISABLE KEYS */;
INSERT INTO `price_type` VALUES (1,1,50,1),(2,2,50,1),(3,3,50,1),(4,4,75,1),(5,5,75,1),(6,6,150,1);
/*!40000 ALTER TABLE `price_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_schedule`
--

DROP TABLE IF EXISTS `session_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day_of_week` varchar(45) NOT NULL,
  `time` time NOT NULL,
  `hall_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `isActive` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `session_movie_idx` (`movie_id`),
  KEY `session_hall_idx` (`hall_id`),
  CONSTRAINT `session_hall` FOREIGN KEY (`hall_id`) REFERENCES `hall` (`id`),
  CONSTRAINT `session_movie` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_schedule`
--

LOCK TABLES `session_schedule` WRITE;
/*!40000 ALTER TABLE `session_schedule` DISABLE KEYS */;
INSERT INTO `session_schedule` VALUES (1,'MONDAY','09:00:00',1,3,1),(2,'MONDAY','10:30:00',1,6,1),(3,'MONDAY','12:30:00',1,9,1),(4,'MONDAY','15:00:00',1,10,1),(5,'MONDAY','18:00:00',1,7,1),(6,'MONDAY','22:00:00',1,1,1),(7,'TUESDAY','09:00:00',1,3,1),(8,'TUESDAY','10:30:00',1,6,1),(9,'TUESDAY','12:00:00',1,9,1),(10,'TUESDAY','15:00:00',1,5,0),(11,'TUESDAY','18:00:00',1,4,1),(12,'TUESDAY','22:00:00',1,1,1),(13,'WEDNESDAY','09:00:00',1,3,1),(14,'WEDNESDAY','10:30:00',1,6,1),(15,'WEDNESDAY','12:00:00',1,9,1),(16,'WEDNESDAY','15:00:00',1,5,0),(17,'WEDNESDAY','18:00:00',1,7,1),(18,'WEDNESDAY','22:00:00',1,1,1),(19,'THURSDAY','09:00:00',1,3,1),(20,'THURSDAY','10:30:00',1,4,1),(21,'THURSDAY','13:00:00',1,7,1),(22,'THURSDAY','15:30:00',1,1,1),(23,'THURSDAY','19:30:00',1,2,1),(24,'THURSDAY','22:00:00',1,4,1),(25,'FRIDAY','09:00:00',1,3,1),(26,'FRIDAY','10:30:00',1,4,1),(27,'FRIDAY','13:00:00',1,7,1),(28,'FRIDAY','15:30:00',1,1,1),(29,'FRIDAY','19:30:00',1,2,1),(30,'FRIDAY','22:00:00',1,4,1),(31,'SATURDAY','09:00:00',1,3,1),(32,'SATURDAY','10:30:00',1,4,1),(33,'SATURDAY','13:00:00',1,7,1),(34,'SATURDAY','15:30:00',1,1,1),(35,'SATURDAY','19:30:00',1,2,1),(36,'SATURDAY','22:00:00',1,4,1),(37,'SUNDAY','09:00:00',1,3,1),(38,'SUNDAY','10:30:00',1,4,1),(39,'SUNDAY','13:00:00',1,7,1),(40,'SUNDAY','15:30:00',1,1,1),(41,'SUNDAY','19:30:00',1,2,1),(42,'SUNDAY','22:00:00',1,4,1);
/*!40000 ALTER TABLE `session_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `session_schedule_id` int(11) NOT NULL,
  `row` int(11) NOT NULL,
  `place_number` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ticket_session_idx` (`session_schedule_id`),
  KEY `tickei_user_idx` (`user_id`),
  CONSTRAINT `ticket_session` FOREIGN KEY (`session_schedule_id`) REFERENCES `session_schedule` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ticket_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,3,6,5,4,75),(4,4,1,2,3,50),(5,4,1,2,4,50),(6,4,1,2,5,50),(7,9,30,6,5,150),(8,9,30,6,6,150),(9,12,34,6,5,150),(10,12,34,6,6,150),(11,8,37,2,3,50),(12,8,37,2,4,50),(13,8,37,2,5,50),(14,11,12,5,4,75),(15,11,12,5,3,75),(17,12,34,6,4,150),(18,12,34,6,3,150),(19,3,1,5,4,75),(20,3,1,5,5,75),(21,4,2,3,4,50),(22,4,2,3,5,50),(23,4,2,3,6,50),(24,8,2,4,5,75),(25,8,2,4,8,75),(26,8,2,4,7,75),(27,3,2,4,8,75),(28,3,2,5,9,75),(29,3,2,4,9,75),(30,3,2,4,9,75),(31,3,2,3,9,50),(32,3,2,3,1,50),(33,3,2,6,9,150);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `email_address` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role_id` int(10) NOT NULL,
  `salary` double DEFAULT NULL,
  `working_hours_week` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_idx` (`role_id`),
  CONSTRAINT `user_role` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Anna','Makarchuk','female','anna','aniki.das@gmail.com','698d51a19d8a121ce581499d7b701668',1,1000,40),(3,'Petro','Petrov','male','Petrov','petrov@i.ua','698d51a19d8a121ce581499d7b701668',2,NULL,NULL),(4,'Ivan','Ivanov','male','IIva','ivanov@gmail.com','698d51a19d8a121ce581499d7b701668',2,NULL,NULL),(6,'Olga','Ospanova','female','ospOlga','o.osp@i.ua','698d51a19d8a121ce581499d7b701668',1,1000,20),(7,'Volodymyr','Konoval','male','v.ko@gmail.com','v.kon','698d51a19d8a121ce581499d7b701668',2,NULL,NULL),(8,'Inna','Veselka','female','veselka@ukr.net','veselka','698d51a19d8a121ce581499d7b701668',2,NULL,NULL),(9,'Katya','Kot','female','kot@ukr.net','kot.k','698d51a19d8a121ce581499d7b701668',2,NULL,NULL),(10,'Maksym','Marsymov','male','mak.mak@ukr.net','maker','698d51a19d8a121ce581499d7b701668',2,NULL,NULL),(11,'Igor','Zhuk','male','zhyk.i@gmail.com','zhyk.i','698d51a19d8a121ce581499d7b701668',2,NULL,NULL),(12,'Alisa','Osipova','female','Ali@ukr.net','alisa','698d51a19d8a121ce581499d7b701668',2,NULL,NULL),(13,'Andrey','Titov','male','titovk@ukr.net','a.tit','698d51a19d8a121ce581499d7b701668',2,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'administrator'),(2,'client');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-09 21:58:09
