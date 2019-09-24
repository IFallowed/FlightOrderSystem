-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: flightOrder
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.12.04.1

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
-- Table structure for table `t_admin`
--

DROP TABLE IF EXISTS `t_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_admin` (
  `a_id` varchar(18) NOT NULL,
  `a_account` varchar(20) NOT NULL,
  `a_password` varchar(50) NOT NULL,
  `a_adminname` varchar(10) NOT NULL,
  `a_phone` varchar(11) NOT NULL,
  `a_lock` int(11) NOT NULL DEFAULT '3',
  `a_locktime` datetime DEFAULT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_admin`
--

LOCK TABLES `t_admin` WRITE;
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
INSERT INTO `t_admin` VALUES ('111222333444555667','123456','mmm','admin','12312312312',0,'2019-09-19 10:27:09'),('321183199201010020','admin','123123','管理员甲','15905294547',3,NULL);
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_flight`
--

DROP TABLE IF EXISTS `t_flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_flight` (
  `f_flightid` varchar(10) NOT NULL,
  `f_takeofftime` datetime NOT NULL,
  `f_flyingtime` varchar(10) NOT NULL,
  `f_startplace` varchar(20) NOT NULL,
  `f_endplace` varchar(20) NOT NULL,
  `f_tickets` varchar(255) NOT NULL,
  `f_price` float NOT NULL,
  PRIMARY KEY (`f_flightid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_flight`
--

LOCK TABLES `t_flight` WRITE;
/*!40000 ALTER TABLE `t_flight` DISABLE KEYS */;
INSERT INTO `t_flight` VALUES ('q1','2019-09-19 11:00:00','2','bj','nj','10-1-0-3-4-5-6-7-8-9-10',100),('q2','2019-09-19 14:00:00','2','bj','nj','20-1-0-3-4-0-0-7-8-9-0-11-12-13-14-15-16-17-0-19-20',100),('q3','2019-09-19 16:00:00','5','bj','nj','50-1-2-0-4-5-0-7-8-9-10-11-12-13-14-15-16-17-18-19-20-21-22-23-24-25-26-27-28-29-30-31-32-33-34-35-36-37-38-39-40-41-42-43-44-45-46-47-48-49-50',300),('q4','2019-09-19 15:00:00','2','bj','nj','20-1-2-3-4-5-6-7-0-9-10-11-12-13-14-15-16-17-18-0-20',30),('q5','2019-09-19 15:39:00','5','乌鲁木齐','厦门','30-1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-0-19-20-21-22-23-24-25-26-27-28-29-30',497),('q6','2019-09-20 12:00:00','4','拉萨','青岛','30-1-2-3-4-5-6-7-8-9-10-0-12-13-14-15-16-17-18-0-20-0-0-23-0-25-26-27-28-29-30',560);
/*!40000 ALTER TABLE `t_flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `o_orderid` varchar(20) NOT NULL,
  `o_flightid` varchar(10) NOT NULL,
  `o_userid` varchar(18) NOT NULL,
  `o_seat` varchar(5) NOT NULL,
  `o_state` varchar(20) NOT NULL,
  PRIMARY KEY (`o_orderid`),
  KEY `fk_order_user` (`o_userid`),
  KEY `fk_order_flight` (`o_flightid`),
  CONSTRAINT `fk_order_flight` FOREIGN KEY (`o_flightid`) REFERENCES `t_flight` (`f_flightid`),
  CONSTRAINT `fk_order_user` FOREIGN KEY (`o_userid`) REFERENCES `t_user` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES ('20190917094816','q1','12341234123412','1','已过期'),('20190917104339','q4','12341234123412','8','已过期'),('20190917124335','q3','12341234123412','6','已退订'),('20190918155349','q5','321123199605259628','18','已过期'),('20190918171602','q6','321123199605259628','22','已预订'),('20190918190936','q4','321123199605259628','19','已预订'),('20190918191010','q6','321123199605259628','19','已预订'),('20190919102011','q1','321123199605259628','2','已过期'),('20190919113057','q6','321123199605259628','21','已退订');
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `u_id` varchar(18) NOT NULL,
  `u_username` varchar(10) NOT NULL,
  `u_account` varchar(10) NOT NULL,
  `u_password` varchar(50) NOT NULL,
  `u_sex` varchar(2) NOT NULL,
  `u_phone` varchar(11) NOT NULL,
  `u_mail` varchar(25) NOT NULL,
  `u_address` varchar(50) NOT NULL,
  `u_lock` int(11) NOT NULL DEFAULT '3',
  `u_locktime` datetime DEFAULT NULL,
  `u_createtime` datetime NOT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES ('12341234123412','test01','test2','test02','女','11221122111','1222@qq.com','qwqwqw',0,'2019-09-18 10:48:06','2019-09-16 14:50:38'),('321123199010100023','kc','kongcong','112233','女','15952961289','aaaa@qq.com','aaddww',3,NULL,'2019-09-19 11:18:41'),('321123199605259628','kc','kfc','123456','女','13852978307','awsl@google.com','china',3,NULL,'2019-09-18 14:50:11'),('321123199910104432','aaa','qwe','123456','男','13345678900','qqq@google.com','zx',3,NULL,'2019-09-12 14:31:56');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-19 17:33:32
