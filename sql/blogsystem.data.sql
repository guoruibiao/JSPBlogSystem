-- MySQL dump 10.13  Distrib 5.7.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: blogsystem
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `admin_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) NOT NULL DEFAULT '',
  `admin_pass` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`admin_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','d033e22ae348aeb5660fc2140aec35850c4da997');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `commenttime` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_user1_idx` (`user_id`),
  KEY `fk_comment_post1_idx` (`post_id`),
  CONSTRAINT `fk_comment_post1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (9,'How standardful explaination of `root`! Cool!','2017-06-27 19:32:31',2,14),(10,'Thanks for your watching :)','2017-06-27 20:04:28',1,2),(17,'The Comment Test.','2017-06-28 09:54:21',1,4),(19,'I always say something like <code>Hello World!</code> when learning a new programming language.','2017-06-29 15:56:22',3,3),(20,'Nice to meet this cool page.\r\n<code>\r\nLife is short, I use Python.\r\n</code>','2017-06-29 16:00:14',3,16),(21,'666！','2017-06-29 16:07:29',2,14),(23,'è¯è¯ä¸­æè¯è®ºãTest for Chinese.','2017-06-29 16:09:53',1,4),(24,'ææ¯å°èï¼æ¬¢è¿ä½ ä»¬æ¥çæçæç« ï¼ä¹ä¹åã','2017-06-29 16:12:17',1,2),(25,'äº²ç±çrootåçï¼æ¨è¿ç¯æç« æ¯ä»ç¾åº¦ç¾ç§ä¸ç´æ¥æè¿æ¥çå§ ;-)','2017-06-29 16:13:27',1,14),(26,'å¤§å®¶å¥½ï¼ææ¯å¼ ä¸ãè¿ç¯æç« å¥½åææåï¼å¿å¿ã','2017-06-29 18:45:59',3,17),(27,'ä½ å¥½ï¼ææ¯<code>root</code>,çå°ä½ è¿ç¯æç« ï¼åçå¾å¥½ï¼å æ²¹ã','2017-06-30 09:18:08',2,17);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL,
  `digest` varchar(255) DEFAULT NULL,
  `content` mediumtext NOT NULL,
  `postdate` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_post_user_idx` (`user_id`),
  CONSTRAINT `fk_post_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (2,'第一篇文章','workbench的特长是创建表结构的，然后在结构图中，','workbench的特长是创建表结构的，然后在结构图中，圈圈点点，很容易就利用可视化方式把数据库建好，然后再导入到数据库服务器中，这种办法很效率。但是有时我们有一个需求，事先没有建表结构模型，而是利用别的数据库软件建的数据库，并且我们还想利用workbench的表结构模型在上面修改，此时，如何所已有的数据库导出为模型表结构就是焦点了！','2017-06-26 18:40:01',1),(3,'更新后的标题部分','JSTL全名为JavaServer Pages Stan','# JSTL全名为JavaServer Pages Standard Tag Library. ##JSTL是由JCP(Java Community Process)所制定的标准规范，它主要提供给Java Web开发人员一个标准通用的标签函数库。## 剩下的不想抄了，有点长......','2017-06-27 16:09:02',1),(4,'The Third Post of The King.','Welcome to Hexo! This is you','Welcome to Hexo! This is your very first post. Check documentation for more info. If you get any problems when using Hexo, you can find the answer in troubleshooting or you can ask me on GitHub.','2017-06-26 20:06:02',1),(11,'JSTL概念','JSTL全名为JavaServer Pages Stan','JSTL全名为JavaServer Pages Standard Tag Library，JSTL是由JCP(Java Community Process)所制定的标准规范，它主要提供给Java Web开发人员一个标准通用的标签函数库。','2017-06-27 16:23:10',2),(12,'CommonBusiness文章发表测试','CommonBusiness 是针对于普遍的业务需求而设','CommonBusiness 是针对于普遍的业务需求而设计的一个业务类，里面包含了常见的关于一个博客系统的最基本功能的相关方法，当然了，仅仅有这个是远远不够的，还需要很多其他的业务类处理，用得到的时候再进行增加吧。','2017-06-27 16:23:43',3),(13,'The End of Java','è¿æ¯ä¸ç¯ä»¤äººæ·±æçæ','è¿æ¯ä¸ç¯ä»¤äººæ·±æçæç« ï¼ä¸ºä»ä¹æ é¢å¦æ­¤çæ«ï¼\r\nè¿æå°±æ¯åççHTMLé¡µé¢ççæ¯ä¸å¤å¥½çï¼ä¸ä¸æ­¥åå¤å ä¸å»Markdownè¯­æ³çç¼è¾æ§ä»¶ãåå ä¸ä¸äºä»£ç é«äº®å¤çï¼å¾çå¤çä»ä¹çã\r\nå¦æè½çå°ä¸é¢çå¾çï¼è¯´æè¿æ»¤å¤çè¿æ²¡æåï¼éè¦è¿ä¸æ­¥çå¤çäºã\r\n<img src=\"https://avatars0.githubusercontent.com/u/12973402?v=3&s=460\">','2017-06-27 16:27:12',3),(14,'root用户发表的第二篇文章','ROOT存在于Linux系统、UNIX系统（如AIX、B','ROOT存在于Linux系统、UNIX系统（如AIX、BSD等）和类UNIX系统（如稳定到服务器都在用的Debian、适合长期作业成熟老道的Redhat、比较流行的Ubuntu和archlinux等版本的Linux系统以及Android系统）中，是系统中唯一的超级用户，相当于Windows系统中的SYSTEM(XP及以下)/TrustedInstaller(Vista及以上)用户。其具有系统中所有的权限，如启动或停止一个进程，删除或增加用户，增加或者禁用硬件等等。','2017-06-27 16:30:23',2),(16,'English Title','不知道This Blog 能不能 显示 Normally','不知道This Blog 能不能 显示 Normally. What a 尴尬啊！\r\n\r\n比如代码块：\r\n<code>\r\nSystem.out.println(\"Hello World!\");\r\n</code>\r\n\r\n还有引用：\r\n<quote>\r\n人固有一死，或重于泰山，或轻于鸿毛。\r\n</quote>\r\n\r\n图片：\r\n<img src=\"https://avatars0.githubusercontent.com/u/12973402?v=3&s=460\">\r\n\r\n跑马灯效果：\r\n<marquee><font color=\'green\'>原谅绿</font></marquee>\r\n\r\n','2017-06-28 09:20:38',2),(17,'张三，你快乐吗？','亲爱的读者，\r\n    好久不写文章了，这几天一直在写这','亲爱的读者，\r\n    好久不写文章了，这几天一直在写这个小学期的大作业。虽然差不多已经9个多月没有接触Java代码了。但其实编程思想这种东西是相通的。还记得大一大二的时候对于各种网络数据的请求，处理，理解的都不是很到位。所以写出来的东西质量也不是很高。但是这段时间以来，倒是真的学了一些“歪门邪道”，编程上的小技巧。虽然这对于大型的企业及项目而言，可谓是微不足道，但是慢慢的积累下来，也多多少少让我有了一些收获。\r\n    对于模板引擎而言，最早接触的应该还是Java了，慢慢的接触了Django，jinja2等Python中的模板引擎，PHP的smarty，或者说Nodejs中的jade，ejs这些，凡此种种，都是相似的，一通百通。所以重要的不是语法级别，而是从根本上的思维模式的转变。\r\n    ......\r\n    差不多测试文章就是这些了，就写到这里吧。','2017-06-29 18:44:15',3),(18,'一只充电宝的神奇之旅！','大家好，我是一只充电宝，我来自地球。我出生在一个“战火纷','大家好，我是一只充电宝，我来自地球。我出生在一个“战火纷争”的年代，石油，天然气，煤炭都在枯竭中，太阳能正逐步覆盖着地球上的把部分能源事业。作为大洋能的副产品，我--一只充电宝，就诞生了。当然了，我可不是一只普通的充电宝，而是一只具有高度人工智能的充电宝，我本身存储的电量足够一个宇宙飞船遨游太空几千万亿个光年。所以一旦我发生了爆炸，嘿嘿整个地球都会被轮番轰成百上千不知道多少次了。不过我很安全，因为我是一只具备高度人工智能的充电宝嘛。\r\n然而，自从我遇见了我的master，我就悲剧了。巴拉巴拉小魔仙','2017-06-30 15:46:13',2);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_has_tag`
--

DROP TABLE IF EXISTS `post_has_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_has_tag` (
  `post_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`,`tag_id`),
  KEY `fk_post_has_tag_tag1_idx` (`tag_id`),
  KEY `fk_post_has_tag_post1_idx` (`post_id`),
  CONSTRAINT `fk_post_has_tag_post1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_post_has_tag_tag1` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_has_tag`
--

LOCK TABLES `post_has_tag` WRITE;
/*!40000 ALTER TABLE `post_has_tag` DISABLE KEYS */;
INSERT INTO `post_has_tag` VALUES (2,3),(17,3),(2,4),(3,4),(17,4),(14,5),(17,8),(17,9),(17,13),(18,13);
/*!40000 ALTER TABLE `post_has_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (3,'Java'),(4,'Python'),(5,'Computer'),(6,'C'),(7,'C++'),(8,'PHP'),(9,'Nodejs'),(10,'HTML'),(11,'JavaScript'),(12,'SQL'),(13,'Emotion');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'xiaowei','63a9f0ea7bb98050796b649e85481845','marksinoberg@gmail.com',0),(2,'root','63a9f0ea7bb98050796b649e85481845','1064319632@qq.com',1),(3,'zhangsan','e10adc3949ba59abbe56e057f20f883e','1064319632@qq.com',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL DEFAULT '',
  `user_pass` varchar(255) NOT NULL DEFAULT '',
  `user_avatar` varchar(255) NOT NULL DEFAULT '',
  `user_bio` varchar(255) NOT NULL DEFAULT '',
  `join_date` date NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watches`
--

DROP TABLE IF EXISTS `watches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `watches` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `watches` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_watches` (`post_id`),
  CONSTRAINT `fk_watches` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `watches`
--

LOCK TABLES `watches` WRITE;
/*!40000 ALTER TABLE `watches` DISABLE KEYS */;
INSERT INTO `watches` VALUES (1,2,706),(2,3,299),(3,4,1241),(4,11,423),(5,12,321),(6,13,913),(7,14,333),(8,16,750),(9,17,659),(10,18,337);
/*!40000 ALTER TABLE `watches` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-30 21:38:16
