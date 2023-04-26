-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: fyp
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `album` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(40) DEFAULT NULL,
  `image_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `album_cover` (`image_id`),
  CONSTRAINT `album_cover` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composer`
--

DROP TABLE IF EXISTS `composer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composer` (
  `music_id` int NOT NULL,
  `creator_id` int NOT NULL,
  PRIMARY KEY (`music_id`,`creator_id`),
  KEY `composer_creator` (`creator_id`),
  CONSTRAINT `composer_creator` FOREIGN KEY (`creator_id`) REFERENCES `creator` (`id`),
  CONSTRAINT `composer_music` FOREIGN KEY (`music_id`) REFERENCES `music` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composer`
--

LOCK TABLES `composer` WRITE;
/*!40000 ALTER TABLE `composer` DISABLE KEYS */;
INSERT INTO `composer` VALUES (4,8);
/*!40000 ALTER TABLE `composer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creator`
--

DROP TABLE IF EXISTS `creator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creator` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `image_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `creator_image` (`image_id`),
  CONSTRAINT `creator_image` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creator`
--

LOCK TABLES `creator` WRITE;
/*!40000 ALTER TABLE `creator` DISABLE KEYS */;
INSERT INTO `creator` VALUES (1,'Tokoyami Towa',NULL),(2,'Hoshimachi Suisei',NULL),(3,'Minato Aqua',NULL),(4,'Amane Kanata',NULL),(5,'Yuziki Choko',NULL),(6,'Ozora Subaru',NULL),(7,'Himemori Runa',NULL),(8,'Rerulili',NULL),(9,'AZKi',NULL),(10,'Kazama Iroha',NULL),(11,'Nakiri Ayame',NULL),(12,'Hakos Baelz',NULL);
/*!40000 ALTER TABLE `creator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `file_name` varchar(120) DEFAULT NULL,
  `file_path` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (0,'default-video-image.png','/image'),(1,'1.jpg','/image'),(2,'2.jpg','/image'),(3,'3.jpg','/image'),(4,'umamusume.jpg','\\image'),(5,'5.jpg','\\image'),(6,'6.jpg','\\image'),(7,'7.jpg','\\image');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lyricist`
--

DROP TABLE IF EXISTS `lyricist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lyricist` (
  `music_id` int NOT NULL,
  `creator_id` int NOT NULL,
  PRIMARY KEY (`music_id`,`creator_id`),
  KEY `lyricist_creator` (`creator_id`),
  CONSTRAINT `lyricist_creator` FOREIGN KEY (`creator_id`) REFERENCES `creator` (`id`),
  CONSTRAINT `lyricist_music` FOREIGN KEY (`music_id`) REFERENCES `music` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lyricist`
--

LOCK TABLES `lyricist` WRITE;
/*!40000 ALTER TABLE `lyricist` DISABLE KEYS */;
INSERT INTO `lyricist` VALUES (4,8);
/*!40000 ALTER TABLE `lyricist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music`
--

DROP TABLE IF EXISTS `music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `music` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(40) DEFAULT NULL,
  `file_name` varchar(120) DEFAULT NULL,
  `file_path` varchar(120) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `album_id` int DEFAULT NULL,
  `image_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `music_id_uindex` (`id`),
  KEY `music_album` (`album_id`),
  KEY `music_image` (`image_id`),
  CONSTRAINT `music_album` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`),
  CONSTRAINT `music_image` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music`
--

LOCK TABLES `music` WRITE;
/*!40000 ALTER TABLE `music` DISABLE KEYS */;
INSERT INTO `music` VALUES (1,'Unlasting','銆愭槦琛椼仚銇勩仜銇勩€慤nlasting _ LiSA (銈姐兗銉夈偄銉笺儓銉汇偑銉炽儵銈ゃ兂 銈儶銈枫偧銉笺偡銉с兂)銆愭瓕鏋犲垏銈婃姕銇嶃€?2021_11_15) Hoshimachi Suisei.aac','\\music','2022-11-25',NULL,NULL),(2,'Lemon','銆愭槦琛椼仚銇勩仜銇勩€慙emon _ 绫虫触鐜勫斧銆愭瓕鏋犲垏銈婃姕銇嶃€?2021_05_31) Hoshimachi Suisei.aac','\\music','2023-03-04',NULL,NULL),(3,'璐栫姜','銆愬泚銇嶅０銇с€戣礀缃紡澶╅煶銇嬨仾銇?cover).opus','\\music','2023-03-14',NULL,NULL),(4,'绁炪伄銇俱伀銇俱伀','銆愭瓕銇ｃ仸銇裤仧銆戠銇伨銇伨銇玙Covered by鐧掓湀銇°倗銇擄紗澶х┖銈广儛銉紗濮．銉兗銉娿€愩儧銉儵銈ゃ儢_銈儶銈搞儕銉辑锛躲€?opus','\\music','2023-03-11',NULL,NULL),(5,'妗溿儹銉冦偗','妗溿儹銉冦偗 - AZKi 脳 棰ㄧ湡銇勩倣銇?銆?棰ㄧ湡銇勩倣銇?鍛ㄥ勾LIVE銆?aac','\\music',NULL,NULL,NULL),(6,'澶㈣姳鐏?,'澶㈣姳鐏? 鐧鹃銇傘倓銈?original.aac','\\music',NULL,NULL,NULL),(7,'Blooming in the Mud','銆怣V銆態looming in the Mud 銉绘偿涓伀鍜层亸 - 銈︺偐銉償銈广偒銉笺偪銉? HAKOS BAELZ X TOKOYAMI TOWA COVER.aac','\\music',NULL,NULL,NULL),(8,'銈姐儻銉?,'銈姐儻銉?aac','\\music',NULL,NULL,NULL);
/*!40000 ALTER TABLE `music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musiclist`
--

DROP TABLE IF EXISTS `musiclist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `musiclist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(40) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `musiclist_user` (`user_id`),
  CONSTRAINT `musiclist_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musiclist`
--

LOCK TABLES `musiclist` WRITE;
/*!40000 ALTER TABLE `musiclist` DISABLE KEYS */;
INSERT INTO `musiclist` VALUES (1,'Favourite',1),(2,'Most listened',1),(6,'asdfafda',1);
/*!40000 ALTER TABLE `musiclist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musiclist_music`
--

DROP TABLE IF EXISTS `musiclist_music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `musiclist_music` (
  `musiclist_id` int NOT NULL,
  `music_id` int NOT NULL,
  PRIMARY KEY (`music_id`,`musiclist_id`),
  KEY `musiclsit` (`musiclist_id`),
  CONSTRAINT `music` FOREIGN KEY (`music_id`) REFERENCES `music` (`id`),
  CONSTRAINT `musiclsit` FOREIGN KEY (`musiclist_id`) REFERENCES `musiclist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musiclist_music`
--

LOCK TABLES `musiclist_music` WRITE;
/*!40000 ALTER TABLE `musiclist_music` DISABLE KEYS */;
INSERT INTO `musiclist_music` VALUES (1,5),(1,6),(1,7),(1,8);
/*!40000 ALTER TABLE `musiclist_music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `series`
--

DROP TABLE IF EXISTS `series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `series` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(120) DEFAULT NULL,
  `image_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `series`
--

LOCK TABLES `series` WRITE;
/*!40000 ALTER TABLE `series` DISABLE KEYS */;
INSERT INTO `series` VALUES (1,'Arcane',NULL),(2,'Lycoris Recoil',NULL),(3,'Kill La Kill',NULL),(4,'music video',NULL),(5,'Uma Musume S2',NULL),(6,'uma',NULL),(7,'Oniichan ha Oshimai',NULL);
/*!40000 ALTER TABLE `series` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_user_name_uindex` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test123','asdf123',NULL),(2,'test321','test321','1354321@354.com'),(6,'testasd','asdfasdf',''),(7,'asdf','SfsdSD354',''),(8,'test456','asdASD123','123@asd.com'),(9,'test789','ASdasd789',''),(10,'test741','asdASD741',''),(11,'testtest','asdfASD123','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(40) NOT NULL,
  `file_name` varchar(120) DEFAULT NULL,
  `file_path` varchar(120) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `series_id` int DEFAULT NULL,
  `image_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `video_id_uindex` (`id`),
  KEY `image` (`image_id`),
  KEY `series` (`series_id`),
  CONSTRAINT `image` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `series` FOREIGN KEY (`series_id`) REFERENCES `series` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (1,'Lycoris Recoil [01]','[Lilith-Raws] &Lycoris Recoil - 01 [Baha][WEB-DL][1080p][AVC AAC][CHT][MP4].mp4','\\Lycoris & Recoil',NULL,2,1),(2,'Arcane [01]','Arcane 01.mkv','\\Arcane',NULL,1,2),(3,'Arcane [02]','Arcane 02.mkv','\\Arcane',NULL,1,2),(4,'Dr. House [08]','[www.48486.com]璞柉鍖荤敓绗竴瀛?8.rmvb','\\绗?瀛?,NULL,NULL,NULL),(5,'Inception','鐩楁ⅵ绌洪棿.Inception.2010.1080p.BluRay.CHS&ENG-HAN@CHAOSPACE.mp4','F:\\bangumi',NULL,NULL,3),(6,'Thor: Ragnarok','[鍏紬鍙凤細闊﹀崜鐢靛奖].mkv','F:\\bangumi\\闆风3锛氳绁為粍鏄?Thor Ragnarok (2017)',NULL,NULL,NULL),(7,'Arcane [03]','鑻遍泟鑱旂洘.鍙屽煄涔嬫垬.Arcane.S01E03.The.Base.Violence.Necessary.for.Change.1080p.Mandarin.NF.WEB-DL.X264.AAC-IcanSub.mkv','F:\\bangumi\\Arcane',NULL,1,2),(8,'Arcane [04]','鑻遍泟鑱旂洘.鍙屽煄涔嬫垬.Arcane.S01E04.Happy.Progress.Day.1080p.Mandarin.NF.WEB-DL.X264.AAC-IcanSub.mkv','F:\\bangumi\\Arcane',NULL,1,2),(9,'Arcane [05]','鑻遍泟鑱旂洘.鍙屽煄涔嬫垬.Arcane.S01E05.Everybody.Wants.to.Be.My.Enemy.1080p.Mandarin.NF.WEB-DL.X264.AAC-IcanSub.mkv','F:\\bangumi\\Arcane',NULL,1,2),(10,'Arcane [06]','鑻遍泟鑱旂洘.鍙屽煄涔嬫垬.Arcane.S01E06.When.These.Walls.Come.Tumbling.Down.1080p.Mandarin.NF.WEB-DL.X264.AAC-IcanSub.mkv','F:\\bangumi\\Arcane',NULL,1,2),(11,'Arcane [07]','鑻遍泟鑱旂洘.鍙屽煄涔嬫垬.Arcane.S01E07.The.Boy.Savior.1080p.Mandarin.NF.WEB-DL.X264.AAC-IcanSub.mkv','F:\\bangumi\\Arcane',NULL,1,2),(12,'Arcane [08]','鑻遍泟鑱旂洘.鍙屽煄涔嬫垬.Arcane.S01E08.Oil.and.Water.1080p.Mandarin.NF.WEB-DL.X264.AAC-IcanSub.mkv','F:\\bangumi\\Arcane',NULL,1,2),(13,'Arcane [09]','鑻遍泟鑱旂洘.鍙屽煄涔嬫垬.Arcane.S01E09.The.Monster.You.Created.1080p.Mandarin.NF.WEB-DL.X264.AAC-IcanSub.mkv','F:\\bangumi\\Arcane',NULL,1,2),(14,'Lycoris Recoil [02]','[Lilith-Raws] Lycoris Recoil - 02 [Baha][WEB-DL][1080p][AVC AAC][CHT][MP4].mp4','\\Lycoris & Recoil',NULL,2,1),(15,'Lycoris Recoil [03]','[Lilith-Raws] Lycoris Recoil - 03 [Baha][WEB-DL][1080p][AVC AAC][CHT][MP4].mp4','\\Lycoris & Recoil',NULL,2,1),(16,'Lycoris Recoil [04]','[Lilith-Raws] Lycoris Recoil - 04 [Baha][WEB-DL][1080p][AVC AAC][CHT][MP4].mp4','\\Lycoris & Recoil',NULL,2,1),(25,'Lemon','銆愭槦琛椼仚銇勩仜銇勩€慙emon _ 绫虫触鐜勫斧銆愭瓕鏋犲垏銈婃姕銇嶃€?2021_05_31) Hoshimachi Suisei.mp4','\\video','2023-03-17',NULL,NULL),(26,' 鍐嶄細','銆怌OVER銆慡AIKAI _ 鍐嶄細銆怣oona x Suisei Hoshimachi _ 銉犮兗銉婏紡鏄熻銇欍亜銇涖亜銆?mp4','\\video','2023-03-09',NULL,NULL),(28,'Uma Musume S2 [01]','[DHR][Uma Musume S2][01][BIG5][1080P][HEVC_AAC].mp4','\\video',NULL,5,4),(29,'Uma Musume S2 [03]','29.mp4','\\video',NULL,5,5),(30,'Uma Musume S2 [04]','30.mp4','\\video',NULL,5,6),(31,'Uma Musume S2 [06]','31.mp4','\\video','2023-03-25',6,7),(32,'Oniichan ha Oshimai! [05]','32.mp4','\\video',NULL,7,NULL),(33,'Oniichan ha Oshimai [07]','33.mp4','\\video',NULL,7,NULL),(34,'Oniichan ha Oshimai! [09]','34.mp4','\\video',NULL,7,NULL),(35,'Stellar Stellar','35.mp4','\\video',NULL,4,NULL),(36,'澶滄挮銇с倠銉°儙銈?,'36.mp4','\\video',NULL,4,NULL),(37,'鏄庢棩銇搞伄澧冪晫绶?,'37.mp4','\\video',NULL,4,NULL);
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocal`
--

DROP TABLE IF EXISTS `vocal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vocal` (
  `music_id` int NOT NULL,
  `creator_id` int NOT NULL,
  PRIMARY KEY (`music_id`,`creator_id`),
  KEY `vocal_creator` (`creator_id`),
  CONSTRAINT `vocal_creator` FOREIGN KEY (`creator_id`) REFERENCES `creator` (`id`),
  CONSTRAINT `vocal_music` FOREIGN KEY (`music_id`) REFERENCES `music` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocal`
--

LOCK TABLES `vocal` WRITE;
/*!40000 ALTER TABLE `vocal` DISABLE KEYS */;
INSERT INTO `vocal` VALUES (3,1),(7,1),(8,2),(3,4),(4,5),(4,6),(4,7),(5,9),(5,10),(6,11),(7,12);
/*!40000 ALTER TABLE `vocal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-19  1:04:51
