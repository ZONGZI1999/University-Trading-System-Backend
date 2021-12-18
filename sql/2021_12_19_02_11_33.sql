-- MySQL dump 10.13  Distrib 5.7.31, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: university_trading_system
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Table structure for table `delivery_info`
--

DROP TABLE IF EXISTS `delivery_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_info` (
  `delivery_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(10) DEFAULT NULL,
  `name` text,
  `phone_no` text,
  `address` text,
  PRIMARY KEY (`delivery_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_info`
--

LOCK TABLES `delivery_info` WRITE;
/*!40000 ALTER TABLE `delivery_info` DISABLE KEYS */;
INSERT INTO `delivery_info` VALUES (1,'SWE1809388','Zekizheng','1234567890123','test address'),(2,'SWE1809388','aaa','123','test address'),(3,'SWE1809388','test','1234','test add ttt'),(4,'SWE1809387','ZLF','123','TEST ADDRESS');
/*!40000 ALTER TABLE `delivery_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_details`
--

DROP TABLE IF EXISTS `item_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_details` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_id` varchar(10) DEFAULT NULL,
  `item_title` text NOT NULL,
  `item_price` int(11) DEFAULT NULL,
  `item_description` json DEFAULT NULL,
  `item_image` json DEFAULT NULL,
  `item_status` varchar(10) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_details`
--

LOCK TABLES `item_details` WRITE;
/*!40000 ALTER TABLE `item_details` DISABLE KEYS */;
INSERT INTO `item_details` VALUES (9,'SWE1809387','test item to test order',12,'[\"【brushed Microfiber Made】 4-piece Size Twin Bed Sheet Set: 2 Pillow Cases and a Top Sheet and Fitted Sheet. Top Sheet Fitted Sheet 2 Pillow Cases (20” X 30”). Spaceship Rockets Print Kids Bedding\", \"【KFZ COSMIC PLANET PRINTED BEDDING】 The Bed Sheet Sets include 1 Flat Sheet, 1 Fitted Sheet, and 2 Pillow Cases Twin Sheets Set Blue Color for Girls Kids Adults\", \"【TOP-GRADE MATERIAL】This polyester brushed microfiber fabric girls bed sheet set is super silky soft, luxuriously comfortable, and wholly breathable for all-season use. The Egyptian-quality fiber ensures that the full sheets sets are resistant to wrinkles, stains, and fading. Even more skin-friendly than 100% cotton sheets, these full bed sheets will keep you fresh and cool in summer and cozy-warm in winter. The top sheet is also suitable for use as a coverlet on summer days.\", \"【DEEP-POCKET SHEET SETS】The fitted bed sheet included is designed with a deep pocket, allowing it to comfortably fit any mattress less than 15” in thickness. With a matching pattern on the top flat sheet and European envelope-style pillowcases, you can easily upgrade your bedding with a distinctive style in no time at all. The bedding sets are 100% soft, meaning they are for kids.\", \"【CUTE DESIGN AND GENEROUS FITS】Our twin size sheets are designed with very realistic solar planets on navy blue background pattern. We included all the planets in solar system. It is suitable for use in any type of room, including in the bedroom, as kids’ room décor, especially for the little ones who are keen on astronomical science. The cool and fashionable design is a delightful and colorful addition to your home décor.\"]','[\"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\", \"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\"]','\"SOLD\"','2021-12-17 15:24:25'),(10,'SWE1809387','test product',101,'[\"【brushed Microfiber Made】 4-piece Size Twin Bed Sheet Set: 2 Pillow Cases and a Top Sheet and Fitted Sheet. Top Sheet Fitted Sheet 2 Pillow Cases (20” X 30”). Spaceship Rockets Print Kids Bedding\", \"【KFZ COSMIC PLANET PRINTED BEDDING】 The Bed Sheet Sets include 1 Flat Sheet, 1 Fitted Sheet, and 2 Pillow Cases Twin Sheets Set Blue Color for Girls Kids Adults\", \"【TOP-GRADE MATERIAL】This polyester brushed microfiber fabric girls bed sheet set is super silky soft, luxuriously comfortable, and wholly breathable for all-season use. The Egyptian-quality fiber ensures that the full sheets sets are resistant to wrinkles, stains, and fading. Even more skin-friendly than 100% cotton sheets, these full bed sheets will keep you fresh and cool in summer and cozy-warm in winter. The top sheet is also suitable for use as a coverlet on summer days.\", \"【DEEP-POCKET SHEET SETS】The fitted bed sheet included is designed with a deep pocket, allowing it to comfortably fit any mattress less than 15” in thickness. With a matching pattern on the top flat sheet and European envelope-style pillowcases, you can easily upgrade your bedding with a distinctive style in no time at all. The bedding sets are 100% soft, meaning they are for kids.\", \"【CUTE DESIGN AND GENEROUS FITS】Our twin size sheets are designed with very realistic solar planets on navy blue background pattern. We included all the planets in solar system. It is suitable for use in any type of room, including in the bedroom, as kids’ room décor, especially for the little ones who are keen on astronomical science. The cool and fashionable design is a delightful and colorful addition to your home décor.\"]','[\"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\", \"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\"]','\"SOLD\"','2021-12-17 15:24:25'),(11,'SWE1809387','test item 2',102,'[\"【brushed Microfiber Made】 4-piece Size Twin Bed Sheet Set: 2 Pillow Cases and a Top Sheet and Fitted Sheet. Top Sheet Fitted Sheet 2 Pillow Cases (20” X 30”). Spaceship Rockets Print Kids Bedding\", \"【KFZ COSMIC PLANET PRINTED BEDDING】 The Bed Sheet Sets include 1 Flat Sheet, 1 Fitted Sheet, and 2 Pillow Cases Twin Sheets Set Blue Color for Girls Kids Adults\", \"【TOP-GRADE MATERIAL】This polyester brushed microfiber fabric girls bed sheet set is super silky soft, luxuriously comfortable, and wholly breathable for all-season use. The Egyptian-quality fiber ensures that the full sheets sets are resistant to wrinkles, stains, and fading. Even more skin-friendly than 100% cotton sheets, these full bed sheets will keep you fresh and cool in summer and cozy-warm in winter. The top sheet is also suitable for use as a coverlet on summer days.\", \"【DEEP-POCKET SHEET SETS】The fitted bed sheet included is designed with a deep pocket, allowing it to comfortably fit any mattress less than 15” in thickness. With a matching pattern on the top flat sheet and European envelope-style pillowcases, you can easily upgrade your bedding with a distinctive style in no time at all. The bedding sets are 100% soft, meaning they are for kids.\", \"【CUTE DESIGN AND GENEROUS FITS】Our twin size sheets are designed with very realistic solar planets on navy blue background pattern. We included all the planets in solar system. It is suitable for use in any type of room, including in the bedroom, as kids’ room décor, especially for the little ones who are keen on astronomical science. The cool and fashionable design is a delightful and colorful addition to your home décor.\"]','[\"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\", \"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\"]','\"SOLD\"','2021-12-17 15:24:25'),(12,'SWE1809388','Test DATA',13,'[\"【brushed Microfiber Made】 4-piece Size Twin Bed Sheet Set: 2 Pillow Cases and a Top Sheet and Fitted Sheet. Top Sheet Fitted Sheet 2 Pillow Cases (20” X 30”). Spaceship Rockets Print Kids Bedding\", \"【KFZ COSMIC PLANET PRINTED BEDDING】 The Bed Sheet Sets include 1 Flat Sheet, 1 Fitted Sheet, and 2 Pillow Cases Twin Sheets Set Blue Color for Girls Kids Adults\", \"【TOP-GRADE MATERIAL】This polyester brushed microfiber fabric girls bed sheet set is super silky soft, luxuriously comfortable, and wholly breathable for all-season use. The Egyptian-quality fiber ensures that the full sheets sets are resistant to wrinkles, stains, and fading. Even more skin-friendly than 100% cotton sheets, these full bed sheets will keep you fresh and cool in summer and cozy-warm in winter. The top sheet is also suitable for use as a coverlet on summer days.\", \"【DEEP-POCKET SHEET SETS】The fitted bed sheet included is designed with a deep pocket, allowing it to comfortably fit any mattress less than 15” in thickness. With a matching pattern on the top flat sheet and European envelope-style pillowcases, you can easily upgrade your bedding with a distinctive style in no time at all. The bedding sets are 100% soft, meaning they are for kids.\", \"【CUTE DESIGN AND GENEROUS FITS】Our twin size sheets are designed with very realistic solar planets on navy blue background pattern. We included all the planets in solar system. It is suitable for use in any type of room, including in the bedroom, as kids’ room décor, especially for the little ones who are keen on astronomical science. The cool and fashionable design is a delightful and colorful addition to your home décor.\"]','[\"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\", \"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\"]','\"SOLD\"','2021-12-17 15:24:25'),(13,'SWE1809388','TEST Item',100,'[\"【KFZ COSMIC PLANET PRINTED BEDDING】 The Bed Sheet Sets include 1 Flat Sheet, 1 Fitted Sheet, and 2 Pillow Cases Twin Sheets Set Blue Color for Girls Kids Adults\", \"【CUTE DESIGN AND GENEROUS FITS】Our twin size sheets are designed with very realistic solar planets on navy blue background pattern. We included all the planets in solar system. It is suitable for use in any type of room, including in the bedroom, as kids’ room décor, especially for the little ones who are keen on astronomical science. The cool and fashionable design is a delightful and colorful addition to your home décor.\"]','[\"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\", \"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\"]','\"SOLD\"','2021-12-17 15:24:25'),(14,'SWE1809388','test last one',100,'[\"【brushed Microfiber Made】 4-piece Size Twin Bed Sheet Set: 2 Pillow Cases and a Top Sheet and Fitted Sheet. Top Sheet Fitted Sheet 2 Pillow Cases (20” X 30”). Spaceship Rockets Print Kids Bedding\", \"【KFZ COSMIC PLANET PRINTED BEDDING】 The Bed Sheet Sets include 1 Flat Sheet, 1 Fitted Sheet, and 2 Pillow Cases Twin Sheets Set Blue Color for Girls Kids Adults\", \"【TOP-GRADE MATERIAL】This polyester brushed microfiber fabric girls bed sheet set is super silky soft, luxuriously comfortable, and wholly breathable for all-season use. The Egyptian-quality fiber ensures that the full sheets sets are resistant to wrinkles, stains, and fading. Even more skin-friendly than 100% cotton sheets, these full bed sheets will keep you fresh and cool in summer and cozy-warm in winter. The top sheet is also suitable for use as a coverlet on summer days.\", \"【DEEP-POCKET SHEET SETS】The fitted bed sheet included is designed with a deep pocket, allowing it to comfortably fit any mattress less than 15” in thickness. With a matching pattern on the top flat sheet and European envelope-style pillowcases, you can easily upgrade your bedding with a distinctive style in no time at all. The bedding sets are 100% soft, meaning they are for kids.\", \"【CUTE DESIGN AND GENEROUS FITS】Our twin size sheets are designed with very realistic solar planets on navy blue background pattern. We included all the planets in solar system. It is suitable for use in any type of room, including in the bedroom, as kids’ room décor, especially for the little ones who are keen on astronomical science. The cool and fashionable design is a delightful and colorful addition to your home décor.\"]','[\"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\", \"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\"]','\"SOLD\"','2021-12-17 15:24:25'),(15,'SWE1809387','post last one',102,'[\"【brushed Microfiber Made】 4-piece Size Twin Bed Sheet Set: 2 Pillow Cases and a Top Sheet and Fitted Sheet. Top Sheet Fitted Sheet 2 Pillow Cases (20” X 30”). Spaceship Rockets Print Kids Bedding\", \"【KFZ COSMIC PLANET PRINTED BEDDING】 The Bed Sheet Sets include 1 Flat Sheet, 1 Fitted Sheet, and 2 Pillow Cases Twin Sheets Set Blue Color for Girls Kids Adults\", \"【TOP-GRADE MATERIAL】This polyester brushed microfiber fabric girls bed sheet set is super silky soft, luxuriously comfortable, and wholly breathable for all-season use. The Egyptian-quality fiber ensures that the full sheets sets are resistant to wrinkles, stains, and fading. Even more skin-friendly than 100% cotton sheets, these full bed sheets will keep you fresh and cool in summer and cozy-warm in winter. The top sheet is also suitable for use as a coverlet on summer days.\", \"【DEEP-POCKET SHEET SETS】The fitted bed sheet included is designed with a deep pocket, allowing it to comfortably fit any mattress less than 15” in thickness. With a matching pattern on the top flat sheet and European envelope-style pillowcases, you can easily upgrade your bedding with a distinctive style in no time at all. The bedding sets are 100% soft, meaning they are for kids.\", \"【CUTE DESIGN AND GENEROUS FITS】Our twin size sheets are designed with very realistic solar planets on navy blue background pattern. We included all the planets in solar system. It is suitable for use in any type of room, including in the bedroom, as kids’ room décor, especially for the little ones who are keen on astronomical science. The cool and fashionable design is a delightful and colorful addition to your home décor.\"]','[\"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\", \"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\"]','\"SOLD\"','2021-12-17 15:24:25'),(16,'SWE1809388','test upload file',100,'[\"【brushed Microfiber Made】 4-piece Size Twin Bed Sheet Set: 2 Pillow Cases and a Top Sheet and Fitted Sheet. Top Sheet Fitted Sheet 2 Pillow Cases (20” X 30”). Spaceship Rockets Print Kids Bedding\", \"【KFZ COSMIC PLANET PRINTED BEDDING】 The Bed Sheet Sets include 1 Flat Sheet, 1 Fitted Sheet, and 2 Pillow Cases Twin Sheets Set Blue Color for Girls Kids Adults\", \"【TOP-GRADE MATERIAL】This polyester brushed microfiber fabric girls bed sheet set is super silky soft, luxuriously comfortable, and wholly breathable for all-season use. The Egyptian-quality fiber ensures that the full sheets sets are resistant to wrinkles, stains, and fading. Even more skin-friendly than 100% cotton sheets, these full bed sheets will keep you fresh and cool in summer and cozy-warm in winter. The top sheet is also suitable for use as a coverlet on summer days.\", \"【DEEP-POCKET SHEET SETS】The fitted bed sheet included is designed with a deep pocket, allowing it to comfortably fit any mattress less than 15” in thickness. With a matching pattern on the top flat sheet and European envelope-style pillowcases, you can easily upgrade your bedding with a distinctive style in no time at all. The bedding sets are 100% soft, meaning they are for kids.\", \"【CUTE DESIGN AND GENEROUS FITS】Our twin size sheets are designed with very realistic solar planets on navy blue background pattern. We included all the planets in solar system. It is suitable for use in any type of room, including in the bedroom, as kids’ room décor, especially for the little ones who are keen on astronomical science. The cool and fashionable design is a delightful and colorful addition to your home décor.\"]','[\"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\", \"http://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100\", null]','\"SOLD\"','2021-12-17 15:24:25'),(17,'SWE1809388','upload image test',1,'[\"【brushed Microfiber Made】 4-piece Size Twin Bed Sheet Set: 2 Pillow Cases and a Top Sheet and Fitted Sheet. Top Sheet Fitted Sheet 2 Pillow Cases (20” X 30”). Spaceship Rockets Print Kids Bedding\", \"【KFZ COSMIC PLANET PRINTED BEDDING】 The Bed Sheet Sets include 1 Flat Sheet, 1 Fitted Sheet, and 2 Pillow Cases Twin Sheets Set Blue Color for Girls Kids Adults\", \"【TOP-GRADE MATERIAL】This polyester brushed microfiber fabric girls bed sheet set is super silky soft, luxuriously comfortable, and wholly breathable for all-season use. The Egyptian-quality fiber ensures that the full sheets sets are resistant to wrinkles, stains, and fading. Even more skin-friendly than 100% cotton sheets, these full bed sheets will keep you fresh and cool in summer and cozy-warm in winter. The top sheet is also suitable for use as a coverlet on summer days.\", \"【DEEP-POCKET SHEET SETS】The fitted bed sheet included is designed with a deep pocket, allowing it to comfortably fit any mattress less than 15” in thickness. With a matching pattern on the top flat sheet and European envelope-style pillowcases, you can easily upgrade your bedding with a distinctive style in no time at all. The bedding sets are 100% soft, meaning they are for kids.\", \"【CUTE DESIGN AND GENEROUS FITS】Our twin size sheets are designed with very realistic solar planets on navy blue background pattern. We included all the planets in solar system. It is suitable for use in any type of room, including in the bedroom, as kids’ room décor, especially for the little ones who are keen on astronomical science. The cool and fashionable design is a delightful and colorful addition to your home décor.\"]','[]','\"SOLD\"','2021-12-17 15:24:25'),(18,'SWE1809388','test upload',1,'[\"【brushed Microfiber Made】 4-piece Size Twin Bed Sheet Set: 2 Pillow Cases and a Top Sheet and Fitted Sheet. Top Sheet Fitted Sheet 2 Pillow Cases (20” X 30”). Spaceship Rockets Print Kids Bedding\", \"【KFZ COSMIC PLANET PRINTED BEDDING】 The Bed Sheet Sets include 1 Flat Sheet, 1 Fitted Sheet, and 2 Pillow Cases Twin Sheets Set Blue Color for Girls Kids Adults\", \"【TOP-GRADE MATERIAL】This polyester brushed microfiber fabric girls bed sheet set is super silky soft, luxuriously comfortable, and wholly breathable for all-season use. The Egyptian-quality fiber ensures that the full sheets sets are resistant to wrinkles, stains, and fading. Even more skin-friendly than 100% cotton sheets, these full bed sheets will keep you fresh and cool in summer and cozy-warm in winter. The top sheet is also suitable for use as a coverlet on summer days.\", \"【DEEP-POCKET SHEET SETS】The fitted bed sheet included is designed with a deep pocket, allowing it to comfortably fit any mattress less than 15” in thickness. With a matching pattern on the top flat sheet and European envelope-style pillowcases, you can easily upgrade your bedding with a distinctive style in no time at all. The bedding sets are 100% soft, meaning they are for kids.\", \"【CUTE DESIGN AND GENEROUS FITS】Our twin size sheets are designed with very realistic solar planets on navy blue background pattern. We included all the planets in solar system. It is suitable for use in any type of room, including in the bedroom, as kids’ room décor, especially for the little ones who are keen on astronomical science. The cool and fashionable design is a delightful and colorful addition to your home décor.\"]','[]','\"SOLD\"','2021-12-17 15:24:25'),(19,'SWE1809388','test data',1,'[\"【brushed Microfiber Made】 4-piece Size Twin Bed Sheet Set: 2 Pillow Cases and a Top Sheet and Fitted Sheet. Top Sheet Fitted Sheet 2 Pillow Cases (20” X 30”). Spaceship Rockets Print Kids Bedding\", \"【KFZ COSMIC PLANET PRINTED BEDDING】 The Bed Sheet Sets include 1 Flat Sheet, 1 Fitted Sheet, and 2 Pillow Cases Twin Sheets Set Blue Color for Girls Kids Adults\", \"【TOP-GRADE MATERIAL】This polyester brushed microfiber fabric girls bed sheet set is super silky soft, luxuriously comfortable, and wholly breathable for all-season use. The Egyptian-quality fiber ensures that the full sheets sets are resistant to wrinkles, stains, and fading. Even more skin-friendly than 100% cotton sheets, these full bed sheets will keep you fresh and cool in summer and cozy-warm in winter. The top sheet is also suitable for use as a coverlet on summer days.\", \"【DEEP-POCKET SHEET SETS】The fitted bed sheet included is designed with a deep pocket, allowing it to comfortably fit any mattress less than 15” in thickness. With a matching pattern on the top flat sheet and European envelope-style pillowcases, you can easily upgrade your bedding with a distinctive style in no time at all. The bedding sets are 100% soft, meaning they are for kids.\", \"【CUTE DESIGN AND GENEROUS FITS】Our twin size sheets are designed with very realistic solar planets on navy blue background pattern. We included all the planets in solar system. It is suitable for use in any type of room, including in the bedroom, as kids’ room décor, especially for the little ones who are keen on astronomical science. The cool and fashionable design is a delightful and colorful addition to your home décor.\"]','[\"http://localhost:8081/download?fileName=b050ec47-1566-4223-901a-d70156ae7d8d.png\"]','\"SOLD\"','2021-12-17 15:24:25'),(20,'SWE1809387','TEST',100,'[\"【brushed Microfiber Made】 4-piece Size Twin Bed Sheet Set: 2 Pillow Cases and a Top Sheet and Fitted Sheet. Top Sheet Fitted Sheet 2 Pillow Cases (20” X 30”). Spaceship Rockets Print Kids Bedding\", \"【KFZ COSMIC PLANET PRINTED BEDDING】 The Bed Sheet Sets include 1 Flat Sheet, 1 Fitted Sheet, and 2 Pillow Cases Twin Sheets Set Blue Color for Girls Kids Adults\", \"【TOP-GRADE MATERIAL】This polyester brushed microfiber fabric girls bed sheet set is super silky soft, luxuriously comfortable, and wholly breathable for all-season use. The Egyptian-quality fiber ensures that the full sheets sets are resistant to wrinkles, stains, and fading. Even more skin-friendly than 100% cotton sheets, these full bed sheets will keep you fresh and cool in summer and cozy-warm in winter. The top sheet is also suitable for use as a coverlet on summer days.\", \"【DEEP-POCKET SHEET SETS】The fitted bed sheet included is designed with a deep pocket, allowing it to comfortably fit any mattress less than 15” in thickness. With a matching pattern on the top flat sheet and European envelope-style pillowcases, you can easily upgrade your bedding with a distinctive style in no time at all. The bedding sets are 100% soft, meaning they are for kids.\", \"【CUTE DESIGN AND GENEROUS FITS】Our twin size sheets are designed with very realistic solar planets on navy blue background pattern. We included all the planets in solar system. It is suitable for use in any type of room, including in the bedroom, as kids’ room décor, especially for the little ones who are keen on astronomical science. The cool and fashionable design is a delightful and colorful addition to your home décor.\"]','[\"http://localhost:8081/download?fileName=337d106f-356f-43a6-9eae-e82e17797c47.png\"]','\"SOLD\"','2021-12-19 01:28:17');
/*!40000 ALTER TABLE `item_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_details` (
  `order_id` varchar(32) NOT NULL,
  `pay_no` text,
  `buyer_id` varchar(10) DEFAULT NULL,
  `seller_id` varchar(10) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `remark` text,
  `delivery_info` json DEFAULT NULL,
  `delivery_company` text,
  `tracking_no` text,
  `delivery_time` datetime DEFAULT NULL,
  `order_status` varchar(20) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `seller_evaluation` text,
  `buyer_evaluation` text,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES ('0d4ae8ccffae17c938017dadf114cb5e','202112922160124272867369054208','SWE1809387','SWE1809388',14,'test a','{\"name\": \"ZLF\", \"address\": \"TEST ADDRESS\", \"phoneNo\": \"123\"}','t','t','2021-12-09 22:16:13','FINISH','2021-12-09 22:15:29','seller a','buyer eva'),('1e9721d189b1fc727f7c3a7484b950d2','202112191507127588241091096576','SWE1809388','SWE1809387',20,'','{\"name\": \"Zekizheng\", \"address\": \"test address\", \"phoneNo\": \"1234567890123\"}',NULL,NULL,NULL,'HAS_REFUND','2021-12-19 01:50:05',NULL,NULL),('469174858db766bded7aab589c587ac5','2021129222049124274080588263424','SWE1809388','SWE1809387',15,'remark test','{\"name\": \"Zekizheng\", \"address\": \"test address\", \"phoneNo\": \"1234567890123\"}','test delivery','123','2021-12-09 22:21:12','FINISH','2021-12-09 22:20:21','test eva','test eva buyer'),('67191c7b856b7e9b9ed991038df5b17e','2021121904844127572795818274816','SWE1809387','SWE1809388',17,'','{\"name\": \"ZLF\", \"address\": \"TEST ADDRESS\", \"phoneNo\": \"123\"}',NULL,NULL,NULL,'HAS_REFUND','2021-12-19 00:48:42',NULL,NULL),('6f09f3199790beba3a5e5ce719af9f38','2021121904422127571694079471616','SWE1809387','SWE1809388',19,'','{\"name\": \"ZLF\", \"address\": \"TEST ADDRESS\", \"phoneNo\": \"123\"}',NULL,NULL,NULL,'HAS_REFUND','2021-12-19 00:44:03',NULL,NULL),('8d33c237f130019e3a8aae8ca6fbd669','2021126203743123160971064668160','SWE1809388','SWE1809387',10,'test remark','{\"name\": \"Zekizheng\", \"address\": \"Jalan Sunsuria, Bandar Sunsuria, 43900 Sepang, Selangor Darul Ehsan, Malaysia\", \"phoneNo\": \"1806000000\"}','test company aaa','sf001','2021-12-06 22:27:29','FINISH','2021-12-06 20:35:24','seller eva','buyer eva'),('8e563c39486e8748ba87778c0a4f640e','202112190397127570372999540736','SWE1809387','SWE1809388',18,'','{\"name\": \"ZLF\", \"address\": \"TEST ADDRESS\", \"phoneNo\": \"123\"}',NULL,NULL,NULL,'HAS_REFUND','2021-12-19 00:38:07',NULL,NULL),('9dac874ae90fb99fdc9fbb0293435d60',NULL,'SWE1809388','SWE1809387',11,'test close','{\"name\": \"Zekizheng\", \"address\": \"test address\", \"phoneNo\": \"1234567890123\"}',NULL,NULL,NULL,'CLOSED','2021-12-18 22:43:03',NULL,NULL),('ca15f56cc1e2a5ef351856b4a3682c38',NULL,'SWE1809387','SWE1809388',16,'','{\"name\": \"ZLF\", \"address\": \"TEST ADDRESS\", \"phoneNo\": \"123\"}',NULL,NULL,NULL,'CLOSED','2021-12-18 22:53:40',NULL,NULL),('e52797483c062641f363238b80315f75','2021129212933124261176950419456','SWE1809387','SWE1809388',12,'test remark','{\"name\": \"ZLF\", \"address\": \"TEST ADDRESS\", \"phoneNo\": \"123\"}','TEST','TEST','2021-12-09 21:30:17','FINISH','2021-12-09 21:09:25','seller eva test','test eva'),('ea77aac84b472aa1f840156259b3eec6','2021126163456123099872151760896','SWE1809388','SWE1809387',9,'test remark','{\"name\": \"Zekizheng\", \"address\": \"Jalan Sunsuria, Bandar Sunsuria, 43900 Sepang, Selangor Darul Ehsan, Malaysia\", \"phoneNo\": \"1806000000\"}',NULL,NULL,NULL,'HAS_REFUND','2021-12-06 16:33:38',NULL,NULL),('f31638aa180d85d39f795a8fa224cf30','2021129215641124268007751970816','SWE1809387','SWE1809388',13,'','{\"name\": \"ZLF\", \"address\": \"TEST ADDRESS\", \"phoneNo\": \"123\"}','company','no.','2021-12-09 21:57:01','FINISH','2021-12-09 21:54:05','seller eva','buyer eva');
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` varchar(10) NOT NULL,
  `student_name` text,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('SWE1809387','ZHENG LINFENG'),('SWE1809388','ZHENG ZONGQI');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_balance`
--

DROP TABLE IF EXISTS `user_balance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_balance` (
  `student_id` varchar(10) NOT NULL,
  `balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_balance`
--

LOCK TABLES `user_balance` WRITE;
/*!40000 ALTER TABLE `user_balance` DISABLE KEYS */;
INSERT INTO `user_balance` VALUES ('SWE1809387',889),('SWE1809388',1012);
/*!40000 ALTER TABLE `user_balance` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-19  2:11:34
