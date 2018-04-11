/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.17-log : Database - yujian
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yujian` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yujian`;

/*Table structure for table `adverts` */

DROP TABLE IF EXISTS `adverts`;

CREATE TABLE `adverts` (
  `advertID` varchar(50) NOT NULL COMMENT '广告编号',
  `advertImgUrl` varchar(100) NOT NULL DEFAULT '' COMMENT '广告图片路径',
  `advertTitle` varchar(20) NOT NULL COMMENT '广告标题',
  `advertLink` varchar(500) NOT NULL DEFAULT '' COMMENT '广告链接',
  `advertLogoUrl` varchar(500) NOT NULL DEFAULT '' COMMENT '广告logo',
  `advertContent` varchar(50) DEFAULT '' COMMENT '广告内容',
  `advertSortIndex` int(11) DEFAULT '0' COMMENT '广告排序号',
  PRIMARY KEY (`advertID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `adverts` */

insert  into `adverts`(`advertID`,`advertImgUrl`,`advertTitle`,`advertLink`,`advertLogoUrl`,`advertContent`,`advertSortIndex`) values ('A43210410110710','/YuJianRoom/Contents/UploadFile/Adverts/A43210410110710.png','QQ音乐','https://y.qq.com/portal','/YuJianRoom/Contents/UploadFile/Adverts/A43210410110710_logo.png','QQ音乐盛典',1),('A94916282204010','/YuJianRoom/Contents/UploadFile/Adverts/A94916282204010.png','网易音乐','http://music.163.com/','/YuJianRoom/Contents/UploadFile/Adverts/A94916282204010_logo.png','网易音乐盛典',2);

/*Table structure for table `banners` */

DROP TABLE IF EXISTS `banners`;

CREATE TABLE `banners` (
  `bannerID` varchar(50) NOT NULL COMMENT '轮播图编号',
  `bannerImgUrl` varchar(200) NOT NULL DEFAULT '' COMMENT '轮播图图片连接',
  `bannerLink` varchar(200) NOT NULL DEFAULT '' COMMENT '轮播图连接',
  `bannerType` int(11) DEFAULT '0' COMMENT '0首页轮播图 1表情包轮播图',
  `address` varchar(50) DEFAULT '' COMMENT '地区',
  PRIMARY KEY (`bannerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `banners` */

insert  into `banners`(`bannerID`,`bannerImgUrl`,`bannerLink`,`bannerType`,`address`) values ('B45817513112812','/YuJianRoom/Contents/UploadFile/Banners/B45817513112812.png?a=e21a3ce0-bc90-4e0a-99fa-4fd89f904a9a','https://y.qq.com/portal/player.html',1,''),('B56319510333810','/YuJianRoom/Contents/UploadFile/Banners/B56319510333810.png?a=64b26fbf-9eb5-4818-81dd-53ad578bf477','http://588ku.com/?h=bd&sem=1',0,''),('B81919513331211','/YuJianRoom/Contents/UploadFile/Banners/B81919513331211.png','http://588ku.com/?h=bd&sem=1',0,'北京市 东城区'),('B94822111924410','/YuJianRoom/Contents/UploadFile/Banners/B94822111924410.png','http://tool.oschina.net/jscompress/',1,'');

/*Table structure for table `expressionbags` */

DROP TABLE IF EXISTS `expressionbags`;

CREATE TABLE `expressionbags` (
  `expressionBagID` varchar(50) NOT NULL COMMENT '表情包编号',
  `expressionBagTitle` varchar(50) NOT NULL COMMENT '表情包描述',
  `eBCost` float NOT NULL DEFAULT '0' COMMENT '表情包价格',
  `expressionBagUrl` varchar(200) NOT NULL DEFAULT '' COMMENT '表情包图标',
  `expressionBagIsDefault` int(11) NOT NULL DEFAULT '0' COMMENT '是否为用户默认拥有表情',
  PRIMARY KEY (`expressionBagID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `expressionbags` */

insert  into `expressionbags`(`expressionBagID`,`expressionBagTitle`,`eBCost`,`expressionBagUrl`,`expressionBagIsDefault`) values ('EB15520451143213','表情包6',1,'/YuJianRoom/Contents/UploadFile/ExpressionBags/56cc2bc3-5c96-4e3a-8b1f-e9f5336478d4.png',1),('EB18718224018511','表情包11',10,'/YuJianRoom/Contents/UploadFile/ExpressionBags/da93e0a3-3d87-4d0b-b7f3-cb9e94879bcb.png',0),('EB26619282396410','表情包1',500,'/YuJianRoom/Contents/UploadFile/ExpressionBags/5eb12f9e-1818-483b-82cb-9d57845e7ece.png',0),('EB26710150435412','表情包8',10,'/YuJianRoom/Contents/UploadFile/ExpressionBags/f4c6513c-7941-42c7-afaa-9db16c8e0746.png',0),('EB29320373283810','表情包3',1,'/YuJianRoom/Contents/UploadFile/ExpressionBags/e3c3184d-8829-4b03-9e95-c0699605c198.png',0),('EB41510155928415','表情包9',10,'/YuJianRoom/Contents/UploadFile/ExpressionBags/e0e5e56f-cf8b-4af1-918f-a36477e06bc7.png',0),('EB58016415089010','表情包7',15,'/YuJianRoom/Contents/UploadFile/ExpressionBags/76060da1-7362-4760-a3ec-9d77649b5180.png',0),('EB62620374916611','表情包4',1,'/YuJianRoom/Contents/UploadFile/ExpressionBags/eff39f16-b5f2-4f67-af3d-7a91d1c78aa4.png',0),('EB63019283569711','表情包2',1,'/YuJianRoom/Contents/UploadFile/ExpressionBags/f9603544-7d2f-4069-ad39-7ffdb261bcd0.png',0),('EB73810143002910','表情包7',10,'/YuJianRoom/Contents/UploadFile/ExpressionBags/31cc25f6-90de-4844-9655-6ef177d58841.png',0),('EB76118222117210','表情包10',10,'/YuJianRoom/Contents/UploadFile/ExpressionBags/d43689bc-a3b6-4735-b7fc-d84961c1afba.png',0);

/*Table structure for table `expressions` */

DROP TABLE IF EXISTS `expressions`;

CREATE TABLE `expressions` (
  `expressionID` varchar(50) NOT NULL COMMENT '表情编号',
  `expressionUrl` varchar(100) NOT NULL COMMENT '表情包描述',
  `expressionBagID` varchar(50) NOT NULL COMMENT '表情包编号',
  PRIMARY KEY (`expressionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `expressions` */

insert  into `expressions`(`expressionID`,`expressionUrl`,`expressionBagID`) values ('E14918225870413','/YuJianRoom/Contents/UploadFile/Expressions/E14918225870413.png','EB76118222117210'),('E15421345125612','/YuJianRoom/Contents/UploadFile/Expressions/E15421345125612.png','EB62620374916611'),('E22210161283217','/YuJianRoom/Contents/UploadFile/Expressions/E22210161283217.png','EB26710150435412'),('E26716420304412','/YuJianRoom/Contents/UploadFile/Expressions/E26716420304412.png','EB58016415089010'),('E27910144117411','/YuJianRoom/Contents/UploadFile/Expressions/E27910144117411.png','EB73810143002910'),('E34810160514816','/YuJianRoom/Contents/UploadFile/Expressions/E34810160514816.png','EB41510155928415'),('E34921283747812','/YuJianRoom/Contents/UploadFile/Expressions/E34921283747812.png','EB63019283569711'),('E43921353386314','/YuJianRoom/Contents/UploadFile/Expressions/E43921353386314.png','EB63019283569711'),('E51921353701115','/YuJianRoom/Contents/UploadFile/Expressions/E51921353701115.png','EB63019283569711'),('E53418224689712','/YuJianRoom/Contents/UploadFile/Expressions/E53418224689712.png','EB18718224018511'),('E54021354316717','/YuJianRoom/Contents/UploadFile/Expressions/E54021354316717.png','EB63019283569711'),('E65810151282713','/YuJianRoom/Contents/UploadFile/Expressions/E65810151282713.png','EB73810143002910'),('E69421282373110','/YuJianRoom/Contents/UploadFile/Expressions/E69421282373110.png','EB26619282396410'),('E82521282864511','/YuJianRoom/Contents/UploadFile/Expressions/E82521282864511.png','EB26619282396410'),('E82621354034316','/YuJianRoom/Contents/UploadFile/Expressions/E82621354034316.png','EB63019283569711'),('E84421344426111','/YuJianRoom/Contents/UploadFile/Expressions/E84421344426111.png','EB29320373283810'),('E84821343294110','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png','EB15520451143213'),('E91521284120113','/YuJianRoom/Contents/UploadFile/Expressions/E91521284120113.png','EB63019283569711'),('E94716415943311','/YuJianRoom/Contents/UploadFile/Expressions/E94716415943311.png','EB58016415089010');

/*Table structure for table `feedbacks` */

DROP TABLE IF EXISTS `feedbacks`;

CREATE TABLE `feedbacks` (
  `feedbackID` varchar(50) NOT NULL DEFAULT '' COMMENT '反馈列表',
  `feedbackContent` varchar(300) NOT NULL DEFAULT '' COMMENT '反馈内容',
  `feedbackTime` datetime NOT NULL COMMENT '反馈时间',
  `feedbackType` int(11) NOT NULL DEFAULT '0' COMMENT '0.告白 1.求婚 2.其他 3.反馈',
  `feedbackStatus` int(11) NOT NULL COMMENT '0.未处理 1.已处理',
  `userID` bigint(20) NOT NULL COMMENT '反馈用户',
  `feedbackResult` int(11) NOT NULL DEFAULT '-1' COMMENT '-1未定义 0不通过 1通过',
  PRIMARY KEY (`feedbackID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `feedbacks` */

insert  into `feedbacks`(`feedbackID`,`feedbackContent`,`feedbackTime`,`feedbackType`,`feedbackStatus`,`userID`,`feedbackResult`) values ('FB10512125090011','我想向10号求婚','2017-09-29 12:12:51',1,0,4,0),('FB17121120591810','哗啦啦啦啦','2017-10-15 21:12:06',3,1,4,0),('FB43210490165334','请求开放红人功能，成为红娘用户','2018-01-03 10:49:02',4,0,41,-1),('FB51612123067610','我想要向编号为10的用户表白。','2017-09-29 12:12:31',0,0,4,0),('FB55900061385611','请求开放红人功能，成为红娘用户','2018-01-03 00:06:14',4,1,40,0),('FB58516071267010','请求开放红人功能，成为红娘用户','2017-12-06 16:07:13',4,1,4,1),('FB68612130871813','测试反馈，系统bug','2017-09-29 12:13:09',3,0,4,0),('FB99916504947311','测试','2017-12-10 16:50:49',2,0,4,-1);

/*Table structure for table `gifts` */

DROP TABLE IF EXISTS `gifts`;

CREATE TABLE `gifts` (
  `giftID` varchar(50) NOT NULL COMMENT '礼物编号',
  `giftUrl` varchar(100) NOT NULL DEFAULT '' COMMENT '礼物路径',
  `giftCost` float NOT NULL DEFAULT '0' COMMENT '礼物价格',
  `giftTitle` varchar(20) NOT NULL DEFAULT '' COMMENT '礼物名称',
  `giftContent` varchar(30) NOT NULL DEFAULT '' COMMENT '礼物描述',
  PRIMARY KEY (`giftID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gifts` */

insert  into `gifts`(`giftID`,`giftUrl`,`giftCost`,`giftTitle`,`giftContent`) values ('G11620483270510','/YuJianRoom/Contents/UploadFile/Gifts/706ba837-8cf5-4ee7-bf4b-7e8899b52a81.png',10,'洋娃娃',''),('G18615025556110','/YuJianRoom/Contents/UploadFile/Gifts/e13ad31d-9ddf-427d-9439-f02717c41448.png',1,'小熊','女孩子最喜欢的熊熊萌物~'),('G50419104633610','/YuJianRoom/Contents/UploadFile/Gifts/12fe97ed-96fd-4485-a8fc-60ae4fe6caab.png',3,'哆啦A梦','女孩子最喜欢的猫猫萌物~'),('G73820491773711','/YuJianRoom/Contents/UploadFile/Gifts/e23cd0ef-1921-4d4a-84d5-983624e0e828.png',10,'小兔',''),('G82320493531512','/YuJianRoom/Contents/UploadFile/Gifts/29bbd12d-6b05-4657-a41f-89f11f2cb32a.png',8,'小猪','');

/*Table structure for table `leavewords` */

DROP TABLE IF EXISTS `leavewords`;

CREATE TABLE `leavewords` (
  `leaveWordID` varchar(50) NOT NULL COMMENT '留言编号',
  `leaveWordUrl` varchar(100) NOT NULL DEFAULT '' COMMENT '留言图片',
  `fromUserID` bigint(20) NOT NULL DEFAULT '0' COMMENT '来源用户编号',
  `toUserID` bigint(20) NOT NULL DEFAULT '0' COMMENT '目标用户编号',
  `leaveWordTime` datetime NOT NULL DEFAULT '2017-09-09 00:00:00' COMMENT '留言时间',
  `toLeaveWordUserID` bigint(20) NOT NULL DEFAULT '-1' COMMENT '留言目标用户',
  `parentId` varchar(50) NOT NULL DEFAULT '' COMMENT '父级编号',
  PRIMARY KEY (`leaveWordID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `leavewords` */

insert  into `leavewords`(`leaveWordID`,`leaveWordUrl`,`fromUserID`,`toUserID`,`leaveWordTime`,`toLeaveWordUserID`,`parentId`) values ('LW10416531001918','/YuJianRoom/Contents/UploadFile/Expressions/E26716420304412.png',4,10,'2017-11-12 16:53:10',-1,''),('LW11321411942710','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,10,'2017-09-28 21:41:19',-1,''),('LW11421253323110','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,10,'2018-01-02 21:25:33',-1,''),('LW11609314395030','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',41,11,'2018-01-03 09:31:44',-1,''),('LW12022142482119','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,5,'2017-09-28 22:14:25',-1,''),('LW13009314021528','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',41,11,'2018-01-03 09:31:40',-1,''),('LW14509313477824','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',41,11,'2018-01-03 09:31:35',-1,''),('LW14911493960910','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',1,10,'2018-01-14 11:49:40',-1,''),('LW15220591120724','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,10,'2017-11-12 20:59:11',-1,''),('LW17018162599523','/YuJianRoom/Contents/UploadFile/Expressions/E22210161283217.png',4,10,'2018-01-27 18:16:26',4,'LW62718061756917'),('LW19619095398611','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,6,'2017-10-08 19:09:54',-1,''),('LW20219321619010','/YuJianRoom/Contents/UploadFile/Expressions/E34921283747812.png',4,10,'2017-10-14 19:32:16',-1,''),('LW21220585680614','/YuJianRoom/Contents/UploadFile/Expressions/E54021354316717.png',4,4,'2017-12-19 20:58:57',-1,''),('LW21511521515010','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,8,'2017-10-04 11:52:15',-1,''),('LW22815301699910','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',21,4,'2017-12-13 15:30:17',-1,''),('LW23221512967314','/YuJianRoom/Contents/UploadFile/Expressions/E69421282373110.png',4,10,'2017-09-28 21:51:30',-1,''),('LW24714272054122','/YuJianRoom/Contents/UploadFile/Expressions/E82521282864511.png',4,11,'2017-12-25 14:27:21',-1,''),('LW24823355779313','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',40,23,'2018-01-03 23:35:58',-1,''),('LW25016561359710','/YuJianRoom/Contents/UploadFile/Expressions/E15421345125612.png',4,4,'2017-12-13 16:56:14',21,''),('LW26509313590325','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',41,11,'2018-01-03 09:31:36',-1,''),('LW27111414829317','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',41,4,'2018-01-04 11:41:48',4,''),('LW27122141624318','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,4,'2017-09-28 22:14:16',-1,''),('LW28514265646316','/YuJianRoom/Contents/UploadFile/Expressions/E84421344426111.png',4,11,'2017-12-25 14:26:56',-1,''),('LW28916391213610','/YuJianRoom/Contents/UploadFile/Expressions/E69421282373110.png',4,4,'2017-12-13 16:39:12',21,''),('LW28916495722215','/YuJianRoom/Contents/UploadFile/Expressions/E82521282864511.png',4,10,'2017-11-12 16:49:57',-1,''),('LW29114265929117','/YuJianRoom/Contents/UploadFile/Expressions/E94716415943311.png',4,11,'2017-12-25 14:26:59',-1,''),('LW29409314159029','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',41,11,'2018-01-03 09:31:42',-1,''),('LW30009313871527','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',41,11,'2018-01-03 09:31:39',-1,''),('LW30121413249212','/YuJianRoom/Contents/UploadFile/Expressions/E84421344426111.png',4,10,'2017-09-28 21:41:32',-1,''),('LW31618024816013','/YuJianRoom/Contents/UploadFile/Expressions/E15421345125612.png',4,10,'2018-01-27 18:02:48',-1,''),('LW32319570500410','/YuJianRoom/Contents/UploadFile/Expressions/E82521282864511.png',4,10,'2018-01-28 19:57:05',4,'LW62718061756917'),('LW32514273015126','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,11,'2017-12-25 14:27:30',-1,''),('LW33518153056721','/YuJianRoom/Contents/UploadFile/Expressions/E82521282864511.png',4,10,'2018-01-27 18:15:31',1,'LW14911493960910'),('LW34414570469310','/YuJianRoom/Contents/UploadFile/Expressions/E82521282864511.png',4,7,'2017-12-12 14:57:05',-1,''),('LW36317570017911','/YuJianRoom/Contents/UploadFile/Expressions/E69421282373110.png',4,10,'2018-01-27 17:57:00',-1,''),('LW36414271793221','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,11,'2017-12-25 14:27:18',-1,''),('LW40611470184019','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',44,45,'2018-01-04 11:47:02',-1,''),('LW42014271377520','/YuJianRoom/Contents/UploadFile/Expressions/E15421345125612.png',4,11,'2017-12-25 14:27:14',-1,''),('LW42218051944416','/YuJianRoom/Contents/UploadFile/Expressions/E34810160514816.png',4,16,'2018-01-27 18:05:19',-1,''),('LW43214270197819','/YuJianRoom/Contents/UploadFile/Expressions/E94716415943311.png',4,11,'2017-12-25 14:27:02',-1,''),('LW43616493953214','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,10,'2017-11-12 16:49:40',-1,''),('LW43913244365710','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,4,'2017-09-27 13:24:44',-1,''),('LW45818394884021','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,9,'2017-11-12 18:39:49',-1,''),('LW47117095916110','/YuJianRoom/Contents/UploadFile/Expressions/E15421345125612.png',4,8,'2017-09-25 17:09:59',-1,''),('LW47823355690312','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',40,23,'2018-01-03 23:35:57',-1,''),('LW50518163217324','/YuJianRoom/Contents/UploadFile/Expressions/E84421344426111.png',4,10,'2018-01-27 18:16:32',4,'LW62718061756917'),('LW51019490497210','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,11,'2017-12-12 19:49:05',-1,''),('LW51721324350911','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,16,'2017-12-10 21:32:44',-1,''),('LW51914570720913','/YuJianRoom/Contents/UploadFile/Expressions/E82521282864511.png',4,7,'2017-12-12 14:57:07',-1,''),('LW54423354835611','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',40,23,'2018-01-03 23:35:48',-1,''),('LW56018073553519','/YuJianRoom/Contents/UploadFile/Expressions/E22210161283217.png',4,10,'2018-01-27 18:07:36',1,'LW14911493960910'),('LW58414272824425','/YuJianRoom/Contents/UploadFile/Expressions/E94716415943311.png',4,11,'2017-12-25 14:27:28',-1,''),('LW59109313765326','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',41,11,'2018-01-03 09:31:38',-1,''),('LW62714264952513','/YuJianRoom/Contents/UploadFile/Expressions/E82521282864511.png',4,11,'2017-12-25 14:26:50',-1,''),('LW62718061756917','/YuJianRoom/Contents/UploadFile/Expressions/E69421282373110.png',4,10,'2018-01-27 18:06:18',-1,''),('LW62816533232411','/YuJianRoom/Contents/UploadFile/Expressions/E84421344426111.png',4,8,'2017-09-25 16:53:32',-1,''),('LW68014273308827','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,11,'2017-12-25 14:27:33',-1,''),('LW70810040900110','/YuJianRoom/Contents/UploadFile/Expressions/E69421282373110.png',4,10,'2017-10-30 10:04:09',-1,''),('LW71011481903412','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,10,'2017-11-12 11:48:19',-1,''),('LW72121440560210','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,9,'2017-09-26 21:44:06',-1,''),('LW72818072688418','/YuJianRoom/Contents/UploadFile/Expressions/E69421282373110.png',4,10,'2018-01-27 18:07:27',1,'LW14911493960910'),('LW76514265413415','/YuJianRoom/Contents/UploadFile/Expressions/E82521282864511.png',4,11,'2017-12-25 14:26:54',-1,''),('LW76618161796222','/YuJianRoom/Contents/UploadFile/Expressions/E69421282373110.png',4,10,'2018-01-27 18:16:18',-1,''),('LW76819532398311','/YuJianRoom/Contents/UploadFile/Expressions/E82521282864511.png',4,10,'2017-12-12 19:53:24',-1,''),('LW77918023113512','/YuJianRoom/Contents/UploadFile/Expressions/E34810160514816.png',4,10,'2018-01-27 18:02:31',-1,''),('LW80114270060318','/YuJianRoom/Contents/UploadFile/Expressions/E26716420304412.png',4,11,'2017-12-25 14:27:01',-1,''),('LW80220215871623','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,10,'2017-11-12 20:21:59',-1,''),('LW80921515791616','/YuJianRoom/Contents/UploadFile/Expressions/E15421345125612.png',4,10,'2017-09-28 21:51:58',-1,''),('LW81800070557514','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',40,4,'2018-01-03 00:07:06',-1,''),('LW81800070912115','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',40,4,'2018-01-03 00:07:09',-1,''),('LW82020582819313','/YuJianRoom/Contents/UploadFile/Expressions/E91521284120113.png',4,4,'2017-12-19 20:58:28',4,''),('LW82411413229316','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',41,10,'2018-01-04 11:41:32',4,''),('LW82418145757020','/YuJianRoom/Contents/UploadFile/Expressions/E69421282373110.png',4,10,'2018-01-27 18:14:58',1,'LW14911493960910'),('LW84314265277514','/YuJianRoom/Contents/UploadFile/Expressions/E69421282373110.png',4,11,'2017-12-25 14:26:53',-1,''),('LW87019095305510','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',4,6,'2017-10-08 19:09:53',-1,''),('LW87518031223114','/YuJianRoom/Contents/UploadFile/Expressions/E84421344426111.png',4,11,'2018-01-27 18:03:12',-1,''),('LW87809313170023','/YuJianRoom/Contents/UploadFile/Expressions/E84821343294110.png',41,11,'2018-01-03 09:31:32',-1,''),('LW91620025071711','/YuJianRoom/Contents/UploadFile/Expressions/E34810160514816.png',4,10,'2018-01-28 20:02:51',4,'LW62718061756917'),('LW92918034722215','/YuJianRoom/Contents/UploadFile/Expressions/E22210161283217.png',4,16,'2018-01-27 18:03:47',-1,''),('LW95717295559210','/YuJianRoom/Contents/UploadFile/Expressions/E54021354316717.png',4,10,'2017-09-25 17:29:56',-1,''),('LW98817265234611','/YuJianRoom/Contents/UploadFile/Expressions/E84421344426111.png',4,10,'2017-09-25 17:26:52',-1,''),('LW99414272718224','/YuJianRoom/Contents/UploadFile/Expressions/E26716420304412.png',4,11,'2017-12-25 14:27:27',-1,''),('LW99714272277523','/YuJianRoom/Contents/UploadFile/Expressions/E84421344426111.png',4,11,'2017-12-25 14:27:23',-1,'');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `orderID` varchar(50) NOT NULL COMMENT '订单编号',
  `orderCost` float NOT NULL DEFAULT '9' COMMENT '订单花费',
  `orderDate` datetime NOT NULL COMMENT '订单产生时间',
  `orderType` int(11) NOT NULL DEFAULT '1' COMMENT '0充值 1表情 2礼物 3VIp 4基金',
  `userID` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户编号',
  PRIMARY KEY (`orderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`orderID`,`orderCost`,`orderDate`,`orderType`,`userID`) values ('0001',100,'2017-09-12 11:59:29',1,0),('0002',200,'2016-11-01 15:00:00',1,0),('0003',120,'2014-01-12 15:11:27',1,0),('0004',80,'2015-11-01 15:12:51',1,0),('0005',20,'2017-11-01 15:26:11',1,0),('0006',9,'2017-11-12 16:59:46',1,0),('O10321154767710',0.9,'2017-10-18 21:15:48',2,4),('O11414521208710',1,'2018-01-06 14:52:22',0,44),('O16414582383312',1,'2018-01-06 14:58:32',0,44),('O18219350132710',1,'2018-03-16 19:35:01',1,21),('O21115160187832',3.42,'2017-12-25 15:16:02',2,4),('O21407444955110',1.5,'2017-10-31 07:44:50',1,4),('O22111460351111',0.18,'2017-11-12 11:46:04',2,4),('O27919342177311',0.09,'2017-11-14 19:34:22',2,4),('O33014561446210',5,'2018-01-06 14:56:23',0,44),('O36513144851610',1,'2018-01-20 13:14:49',1,4),('O37713211864612',1,'2018-01-20 13:21:19',1,4),('O38123103437412',45.54,'2017-10-27 23:10:34',2,4),('O42323092665810',0.09,'2017-10-27 23:09:27',2,4),('O43919472458010',0.09,'2017-11-14 19:47:25',2,4),('O45718400523222',0.09,'2017-11-12 18:40:05',2,4),('O47111182107611',5,'2017-10-15 11:18:21',2,4),('O48116131787511',1.26,'2017-12-20 16:13:18',2,4),('O51219335454610',0.09,'2017-11-14 19:33:55',2,4),('O52118423888610',0.27,'2017-10-22 18:42:39',2,4),('O52119071776010',1,'2017-12-20 19:07:18',1,4),('O52120095154310',7.2,'2017-10-18 20:09:52',2,4),('O52313162352011',1,'2018-01-20 13:16:24',1,4),('O52414595611913',1,'2018-01-06 15:00:02',0,44),('O53913232773513',0.1,'2018-01-20 13:23:28',1,4),('O57220305781511',1,'2017-12-20 20:30:58',1,4),('O60314385744717',1,'2018-01-05 14:42:15',0,44),('O60314574265511',1,'2018-01-04 14:57:50',0,44),('O62719393890610',0.27,'2017-11-14 19:39:39',2,4),('O67414463820918',1,'2018-01-06 14:47:16',0,44),('O68221182258811',4,'2017-10-15 21:18:23',2,4),('O85317043973011',0.63,'2017-12-16 17:04:40',2,4),('O87514292023030',1.62,'2017-12-25 14:29:20',2,4),('O90423095838511',0.36,'2017-10-27 23:09:58',2,4),('O90820522987711',1,'2017-12-19 20:52:30',1,4),('O91615071119510',1,'2018-01-06 15:07:23',0,44),('O94021261251212',0.72,'2018-01-02 21:26:13',2,4);

/*Table structure for table `seekintroductlogs` */

DROP TABLE IF EXISTS `seekintroductlogs`;

CREATE TABLE `seekintroductlogs` (
  `seekIntroductLogID` varchar(20) NOT NULL COMMENT '记录编号',
  `fromUserID` bigint(20) NOT NULL COMMENT '来源用户编号',
  `toUserID` bigint(20) NOT NULL COMMENT '目标用户编号',
  `seekIntroductTime` datetime NOT NULL COMMENT '撮合时间',
  `userID` bigint(20) NOT NULL COMMENT '撮合人编号',
  PRIMARY KEY (`seekIntroductLogID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `seekintroductlogs` */

insert  into `seekintroductlogs`(`seekIntroductLogID`,`fromUserID`,`toUserID`,`seekIntroductTime`,`userID`) values ('LG26723272074610',10,7,'2018-01-21 23:27:18',4),('LG39423371636810',5,16,'2018-01-21 23:37:13',6);

/*Table structure for table `seekintroducts` */

DROP TABLE IF EXISTS `seekintroducts`;

CREATE TABLE `seekintroducts` (
  `seekIntroductID` varchar(50) NOT NULL DEFAULT '' COMMENT '求推荐编号',
  `fromUserID` bigint(20) NOT NULL COMMENT '来源用户',
  `toUserID` bigint(20) NOT NULL COMMENT '目标用户',
  `seekIntroductTime` datetime NOT NULL COMMENT '请求时间',
  `userID` bigint(20) NOT NULL COMMENT '推荐人编号',
  `seekIntroductStatus` int(11) DEFAULT '0' COMMENT '0未查看 1已查看',
  `seekIntroductType` int(11) NOT NULL DEFAULT '0' COMMENT '0双向求撮合 1单向求撮合',
  PRIMARY KEY (`seekIntroductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `seekintroducts` */

insert  into `seekintroducts`(`seekIntroductID`,`fromUserID`,`toUserID`,`seekIntroductTime`,`userID`,`seekIntroductStatus`,`seekIntroductType`) values ('SI14023150039612',7,-1,'2018-01-26 23:15:00',-1,0,1),('SI16809312334022',41,11,'2018-01-03 09:31:23',41,0,0),('SI20013175201811',21,-1,'2017-12-13 13:17:52',21,0,1),('SI22223144856010',7,16,'2018-01-26 23:14:49',4,1,0),('SI37300064887212',40,-1,'2018-01-03 00:06:49',40,0,1),('SI37513105158810',21,10,'2017-12-13 13:10:52',21,0,0),('SI45408191188714',40,45,'2018-01-04 08:19:12',40,0,0),('SI53311402290315',41,10,'2018-01-04 11:40:23',41,0,0),('SI66123553470010',38,11,'2018-01-02 23:55:35',38,0,0),('SI69323353987210',40,23,'2018-01-03 23:35:40',40,0,0),('SI75010492484035',41,-1,'2018-01-03 10:49:25',41,0,1),('SI76709200863720',41,42,'2018-01-03 09:20:09',41,0,0),('SI78800065198113',40,-1,'2018-01-03 00:06:52',40,0,1),('SI83122473676020',4,11,'2018-01-25 22:47:37',4,1,0),('SI93414174195910',1,10,'2018-01-13 14:17:42',1,0,0),('SI96723204727010',7,-1,'2018-01-26 23:20:47',4,1,1),('SI96819253952710',21,40,'2018-03-16 19:25:40',10,0,0);

/*Table structure for table `sysconfigs` */

DROP TABLE IF EXISTS `sysconfigs`;

CREATE TABLE `sysconfigs` (
  `sysConfigID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统配置编号',
  `intervalNum` int(11) NOT NULL DEFAULT '0' COMMENT '间隔广告数',
  `vipDisCount` float NOT NULL DEFAULT '1' COMMENT 'Vip折扣',
  `photoCount` int(11) NOT NULL DEFAULT '0' COMMENT '照片上传张数',
  `sendGold` int(11) NOT NULL DEFAULT '0' COMMENT 'vip赠送金币',
  `shareBackUrl` varchar(100) NOT NULL DEFAULT '' COMMENT '分享返回url',
  `userBgUrl` varchar(100) NOT NULL DEFAULT '' COMMENT '用户头像背景',
  PRIMARY KEY (`sysConfigID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sysconfigs` */

insert  into `sysconfigs`(`sysConfigID`,`intervalNum`,`vipDisCount`,`photoCount`,`sendGold`,`shareBackUrl`,`userBgUrl`) values (1,6,0.9,8,10,'http://www.jeasyui.net/demo/526.html','/YuJianRoom/Contents/UploadFile/SysConfigs/bg.png?a=2fcbe169-02b8-42b1-8aae-bee7e82b06fa');

/*Table structure for table `usercollections` */

DROP TABLE IF EXISTS `usercollections`;

CREATE TABLE `usercollections` (
  `userID` bigint(20) NOT NULL COMMENT '用户编号',
  `userCollectionID` bigint(20) NOT NULL COMMENT '被收藏用户编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usercollections` */

insert  into `usercollections`(`userID`,`userCollectionID`) values (40,10),(40,11),(40,21),(40,5),(41,9),(41,11),(41,10);

/*Table structure for table `userexpressionbags` */

DROP TABLE IF EXISTS `userexpressionbags`;

CREATE TABLE `userexpressionbags` (
  `expressionBagID` varchar(50) NOT NULL COMMENT '表情包编号',
  `userID` bigint(20) NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`expressionBagID`,`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userexpressionbags` */

insert  into `userexpressionbags`(`expressionBagID`,`userID`) values ('EB15520451143213',4),('EB18718224018511',4),('EB18718224018511',21),('EB26619282396410',4),('EB26710150435412',4),('EB29320373283810',4),('EB41510155928415',4),('EB58016415089010',4),('EB62620374916611',4),('EB63019283569711',4);

/*Table structure for table `usergifts` */

DROP TABLE IF EXISTS `usergifts`;

CREATE TABLE `usergifts` (
  `fromUserID` bigint(20) NOT NULL COMMENT '来源用户编号',
  `toUserID` bigint(20) NOT NULL COMMENT '目标用户编号',
  `giftID` varchar(50) NOT NULL DEFAULT '' COMMENT '礼物编号',
  `giftCount` int(11) NOT NULL DEFAULT '1' COMMENT '礼物数量',
  `userGiftCost` float NOT NULL DEFAULT '0' COMMENT '消费总值',
  `userGiftTime` datetime NOT NULL DEFAULT '2017-01-01 00:00:00' COMMENT '礼物时间',
  `groupID` varchar(50) NOT NULL DEFAULT '' COMMENT '组号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usergifts` */

insert  into `usergifts`(`fromUserID`,`toUserID`,`giftID`,`giftCount`,`userGiftCost`,`userGiftTime`,`groupID`) values (4,10,'G18615025556110',1,1,'2017-09-26 20:34:05',''),(4,10,'G50419104633610',2,2,'2017-09-26 20:34:05',''),(4,4,'G18615025556110',1,1,'2017-09-27 13:25:00',''),(4,4,'G50419104633610',1,1,'2017-09-27 13:25:00',''),(4,10,'G18615025556110',1,1,'2017-09-28 21:45:03',''),(4,10,'G50419104633610',1,1,'2017-09-28 21:45:03',''),(4,4,'G18615025556110',1,1,'2017-10-04 11:45:50',''),(4,5,'G18615025556110',2,2,'2017-10-04 12:00:27',''),(4,5,'G50419104633610',1,1,'2017-10-04 12:00:27',''),(4,6,'G18615025556110',1,1,'2017-10-08 19:10:40',''),(4,6,'G50419104633610',1,3,'2017-10-08 19:10:40',''),(4,6,'G18615025556110',1,1,'2017-10-08 19:10:42',''),(4,6,'G50419104633610',1,3,'2017-10-08 19:10:42',''),(4,6,'G18615025556110',1,1,'2017-10-08 19:11:22',''),(4,10,'G18615025556110',1,1,'2017-10-08 19:47:13',''),(4,10,'G18615025556110',1,1,'2017-10-08 19:51:14',''),(4,10,'G50419104633610',1,3,'2017-10-08 19:51:14',''),(4,10,'G18615025556110',2,2,'2017-10-15 11:18:19',''),(4,10,'G50419104633610',1,3,'2017-10-15 11:18:19',''),(4,10,'G18615025556110',2,2,'2017-10-15 11:18:21',''),(4,10,'G50419104633610',1,3,'2017-10-15 11:18:21',''),(4,10,'G18615025556110',1,1,'2017-10-15 21:18:23',''),(4,10,'G50419104633610',1,3,'2017-10-15 21:18:23',''),(4,5,'G18615025556110',2,1.8,'2017-10-18 20:09:51',''),(4,5,'G50419104633610',2,5.4,'2017-10-18 20:09:51',''),(4,8,'G18615025556110',1,0.9,'2017-10-18 21:15:47',''),(4,4,'G50419104633610',1,2.7,'2017-10-22 18:42:39',''),(4,10,'G18615025556110',1,0.9,'2017-10-27 23:09:27',''),(4,10,'G18615025556110',4,3.6,'2017-10-27 23:09:58',''),(4,7,'G18615025556110',500,450,'2017-10-27 23:10:34',''),(4,7,'G50419104633610',2,5.4,'2017-10-27 23:10:34',''),(4,10,'G18615025556110',2,1.8,'2017-11-12 11:46:03',''),(4,9,'G18615025556110',1,0.9,'2017-11-12 18:40:05',''),(4,8,'G18615025556110',1,0.9,'2017-11-14 19:33:54',''),(4,7,'G18615025556110',1,0.9,'2017-11-14 19:34:22',''),(4,9,'G50419104633610',1,2.7,'2017-11-14 19:39:39',''),(4,9,'G18615025556110',1,0.9,'2017-11-14 19:47:24',''),(4,16,'G18615025556110',1,0.9,'2017-12-16 17:04:40',''),(4,16,'G50419104633610',2,5.4,'2017-12-16 17:04:40',''),(4,10,'G11620483270510',1,9,'2017-12-20 16:13:18',''),(4,10,'G18615025556110',1,0.9,'2017-12-20 16:13:18',''),(4,10,'G50419104633610',1,2.7,'2017-12-20 16:13:18',''),(4,4,'G50419104633610',6,16.2,'2017-12-25 14:29:20',''),(4,4,'G73820491773711',3,27,'2017-12-25 15:16:02',''),(4,4,'G82320493531512',1,7.2,'2017-12-25 15:16:02',''),(4,10,'G82320493531512',1,7.2,'2018-01-02 21:26:12','');

/*Table structure for table `userimgs` */

DROP TABLE IF EXISTS `userimgs`;

CREATE TABLE `userimgs` (
  `userImgID` varchar(50) NOT NULL COMMENT '用户图片编号',
  `userID` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户编号',
  `userImgUrl` varchar(100) NOT NULL DEFAULT '' COMMENT '用户图片路径',
  PRIMARY KEY (`userImgID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userimgs` */

insert  into `userimgs`(`userImgID`,`userID`,`userImgUrl`) values ('092fcc6c-f5a9-4f90-a0a8-146e44fc9022',8,'/YuJianRoom/Contents/UploadFile/UserPhotos/092fcc6c-f5a9-4f90-a0a8-146e44fc9022.png'),('130b9b50-09a9-4bbe-b147-7023d2c9a33a',5,'/YuJianRoom/Contents/UploadFile/UserPhotos/130b9b50-09a9-4bbe-b147-7023d2c9a33a.png'),('1785b305-2c7e-4358-8530-289d9b091e48',6,'/YuJianRoom/Contents/UploadFile/UserPhotos/1785b305-2c7e-4358-8530-289d9b091e48.png'),('1bf387df-51a2-4a2b-b712-af9a4ab4d2c1',7,'/YuJianRoom/Contents/UploadFile/UserPhotos/1bf387df-51a2-4a2b-b712-af9a4ab4d2c1.png'),('29129e9e-2cf3-4cd7-bbf6-518186df9ca2',9,'/YuJianRoom/Contents/UploadFile/UserPhotos/29129e9e-2cf3-4cd7-bbf6-518186df9ca2.png'),('35a1f7ed-319e-4904-b561-7e746e3821b5',7,'/YuJianRoom/Contents/UploadFile/UserPhotos/35a1f7ed-319e-4904-b561-7e746e3821b5.png'),('41075a46-346f-4533-921e-6e42e11b4627',5,'/YuJianRoom/Contents/UploadFile/UserPhotos/41075a46-346f-4533-921e-6e42e11b4627.png'),('46e0f085-3cb2-4482-8520-d859fb4a5a28',44,'/YuJianRoom/Contents/UploadFile/UserPhotos/46e0f085-3cb2-4482-8520-d859fb4a5a28.png'),('4ba2a03a-5121-4723-a2f9-baa3e28dc032',8,'/YuJianRoom/Contents/UploadFile/UserPhotos/4ba2a03a-5121-4723-a2f9-baa3e28dc032.png'),('5bcf5785-949e-4363-b0b2-7708a93ebc21',4,'/YuJianRoom/Contents/UploadFile/UserPhotos/5bcf5785-949e-4363-b0b2-7708a93ebc21.png'),('5e13f3d9-7775-425f-bb22-d2403c122893',7,'/YuJianRoom/Contents/UploadFile/UserPhotos/5e13f3d9-7775-425f-bb22-d2403c122893.png'),('6c21f194-4685-41fa-bee5-0243b75092d0',7,'/YuJianRoom/Contents/UploadFile/UserPhotos/6c21f194-4685-41fa-bee5-0243b75092d0.png'),('6ccc6660-f135-448c-ad9d-b402862396e1',10,'/YuJianRoom/Contents/UploadFile/UserPhotos/6ccc6660-f135-448c-ad9d-b402862396e1.png'),('85a384ee-40b9-4ce2-b7f9-af3a47c0f365',7,'/YuJianRoom/Contents/UploadFile/UserPhotos/85a384ee-40b9-4ce2-b7f9-af3a47c0f365.png'),('860549e9-b2eb-4937-9290-2f0267b87d06',6,'/YuJianRoom/Contents/UploadFile/UserPhotos/860549e9-b2eb-4937-9290-2f0267b87d06.png'),('891e786c-d550-4259-9512-1152d4d364fe',11,'/YuJianRoom/Contents/UploadFile/UserPhotos/891e786c-d550-4259-9512-1152d4d364fe.png'),('b7fc733b-2777-4ba9-9539-32c4136c2a8c',9,'/YuJianRoom/Contents/UploadFile/UserPhotos/b7fc733b-2777-4ba9-9539-32c4136c2a8c.png'),('b99ae729-4d4a-48d1-b9d2-c46fb33f450b',5,'/YuJianRoom/Contents/UploadFile/UserPhotos/b99ae729-4d4a-48d1-b9d2-c46fb33f450b.png'),('c2516618-2a58-4b5f-abf3-b8f20e3c847a',10,'/YuJianRoom/Contents/UploadFile/UserPhotos/c2516618-2a58-4b5f-abf3-b8f20e3c847a.png'),('c363f10e-39c5-48f5-82d9-1c9115572c1d',44,'/YuJianRoom/Contents/UploadFile/UserPhotos/c363f10e-39c5-48f5-82d9-1c9115572c1d.png'),('ca8a3e99-7ef5-49ca-b72b-bd9e3243bd95',4,'/YuJianRoom/Contents/UploadFile/UserPhotos/ca8a3e99-7ef5-49ca-b72b-bd9e3243bd95.png'),('cc67ee82-9f26-41b9-a7ed-40beda9137b4',8,'/YuJianRoom/Contents/UploadFile/UserPhotos/cc67ee82-9f26-41b9-a7ed-40beda9137b4.png'),('d5db7e23-abbb-421f-8a1c-aea104bc254f',19,'/YuJianRoom/Contents/UploadFile/UserPhotos/d5db7e23-abbb-421f-8a1c-aea104bc254f.png'),('d850f581-8cb4-49ce-a9d4-fc2915c977b5',9,'/YuJianRoom/Contents/UploadFile/UserPhotos/d850f581-8cb4-49ce-a9d4-fc2915c977b5.png'),('d8e5319e-3b61-43ca-91e6-ade247552d1d',6,'/YuJianRoom/Contents/UploadFile/UserPhotos/d8e5319e-3b61-43ca-91e6-ade247552d1d.png'),('db1b53d8-c47c-4073-9b4e-4971158ee6d0',10,'/YuJianRoom/Contents/UploadFile/UserPhotos/db1b53d8-c47c-4073-9b4e-4971158ee6d0.png'),('e6e20fad-4923-45f5-ba2a-47a998fb01db',4,'/YuJianRoom/Contents/UploadFile/UserPhotos/e6e20fad-4923-45f5-ba2a-47a998fb01db.png'),('ece91e1f-aad3-40aa-ba45-ec9c352d35ac',41,'/YuJianRoom/Contents/UploadFile/UserPhotos/ece91e1f-aad3-40aa-ba45-ec9c352d35ac.png'),('ef1605cb-c092-4523-9bde-0083a4692665',4,'/YuJianRoom/Contents/UploadFile/UserPhotos/ef1605cb-c092-4523-9bde-0083a4692665.png'),('ef32da63-7322-4145-bc80-ec97b47a4646',9,'/YuJianRoom/Contents/UploadFile/UserPhotos/ef32da63-7322-4145-bc80-ec97b47a4646.png'),('f10382f2-19fc-4a19-8954-2552e836f2fb',11,'/YuJianRoom/Contents/UploadFile/UserPhotos/f10382f2-19fc-4a19-8954-2552e836f2fb.png'),('fac41833-75c9-42f1-8ff8-01be38c9b076',8,'/YuJianRoom/Contents/UploadFile/UserPhotos/fac41833-75c9-42f1-8ff8-01be38c9b076.png'),('fd934789-28c2-4473-a89c-6ee686829508',10,'/YuJianRoom/Contents/UploadFile/UserPhotos/fd934789-28c2-4473-a89c-6ee686829508.png');

/*Table structure for table `userintroduces` */

DROP TABLE IF EXISTS `userintroduces`;

CREATE TABLE `userintroduces` (
  `uIUserID` bigint(20) NOT NULL COMMENT '红人编号',
  `userID` bigint(20) NOT NULL COMMENT '用户编号',
  `userIntroduceStatus` int(11) NOT NULL DEFAULT '0' COMMENT '红人状态',
  `userIntroduceType` int(11) NOT NULL DEFAULT '0' COMMENT '0用户 1红人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userintroduces` */

insert  into `userintroduces`(`uIUserID`,`userID`,`userIntroduceStatus`,`userIntroduceType`) values (19,4,0,1),(11,4,1,1),(1,4,1,0),(7,4,1,0),(5,4,1,0),(9,4,1,0),(16,4,1,0),(8,4,1,0),(23,4,1,0),(39,4,1,0),(40,4,1,0),(41,4,1,0),(42,4,1,0),(43,4,1,0),(45,4,1,0),(44,4,1,0),(47,4,1,0),(4,4,1,0),(6,4,1,0),(21,4,1,0),(10,4,1,0);

/*Table structure for table `userlovers` */

DROP TABLE IF EXISTS `userlovers`;

CREATE TABLE `userlovers` (
  `aUserID` bigint(20) NOT NULL DEFAULT '0' COMMENT '甲方编号',
  `bUserID` bigint(20) NOT NULL DEFAULT '0' COMMENT '乙方编号',
  `loveCost` float NOT NULL DEFAULT '0' COMMENT '爱情基金',
  `userLoveDate` datetime NOT NULL COMMENT '成立时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userlovers` */

insert  into `userlovers`(`aUserID`,`bUserID`,`loveCost`,`userLoveDate`) values (1,4,10000,'2017-09-08 11:33:31');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `videoUrl` varchar(500) NOT NULL DEFAULT '' COMMENT '视频路径',
  `headImgUrl` varchar(500) NOT NULL DEFAULT '' COMMENT '头像路径',
  `address` varchar(50) NOT NULL DEFAULT '' COMMENT '地址（籍贯）',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `occupation` varchar(50) NOT NULL DEFAULT '' COMMENT '职业',
  `hobby` varchar(50) NOT NULL DEFAULT '' COMMENT '爱好',
  `realName` varchar(20) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `weiXinNum` varchar(50) NOT NULL DEFAULT '' COMMENT '微信号',
  `qQNum` varchar(50) NOT NULL DEFAULT '' COMMENT 'qq号',
  `hotCount` int(11) NOT NULL DEFAULT '0' COMMENT '热度',
  `userType` int(11) NOT NULL DEFAULT '0' COMMENT '-2红娘且用户 -1红人用户 0普通用户 1VIP 2管理员 3红娘 4超级管理员',
  `goldBalance` float NOT NULL DEFAULT '0' COMMENT '金币数',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `riches` bigint(20) NOT NULL DEFAULT '0' COMMENT '财富',
  `height` varchar(50) NOT NULL DEFAULT '150-160' COMMENT '身高',
  `birthDay` date NOT NULL COMMENT '生日',
  `sex` int(11) NOT NULL DEFAULT '1' COMMENT '0女 1男',
  `record` varchar(50) NOT NULL DEFAULT '' COMMENT '学历',
  `userSortIndex` int(11) NOT NULL DEFAULT '0' COMMENT '排序号',
  `weixinUID` varchar(100) NOT NULL DEFAULT '' COMMENT '微信UID',
  `weixinOID` varchar(100) NOT NULL DEFAULT '' COMMENT '微信OpenID',
  `signContent` varchar(200) NOT NULL DEFAULT '' COMMENT '用户签名',
  `married` int(11) NOT NULL DEFAULT '0' COMMENT '0未婚 1已婚 2保密',
  `wage` varchar(20) NOT NULL DEFAULT '' COMMENT '薪资',
  `bloodtype` varchar(10) NOT NULL DEFAULT '' COMMENT '血型',
  `selectcondition` varchar(100) NOT NULL DEFAULT '' COMMENT '择偶要求',
  `matchmakerIntroduct` varchar(500) NOT NULL DEFAULT '' COMMENT '红娘介绍',
  `matchmakerGoodAt` varchar(100) NOT NULL DEFAULT '' COMMENT '擅长领域',
  `matchmakerAdvise` varchar(300) NOT NULL DEFAULT '' COMMENT '婚恋建议',
  `matchmakertype` int(11) NOT NULL DEFAULT '0' COMMENT '0无 1默认红娘',
  `otherFunction` int(11) NOT NULL DEFAULT '0' COMMENT '0无职能 1红娘',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '自定义备注',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`userID`,`videoUrl`,`headImgUrl`,`address`,`phone`,`password`,`occupation`,`hobby`,`realName`,`weiXinNum`,`qQNum`,`hotCount`,`userType`,`goldBalance`,`createTime`,`riches`,`height`,`birthDay`,`sex`,`record`,`userSortIndex`,`weixinUID`,`weixinOID`,`signContent`,`married`,`wage`,`bloodtype`,`selectcondition`,`matchmakerIntroduct`,`matchmakerGoodAt`,`matchmakerAdvise`,`matchmakertype`,`otherFunction`,`remark`) values (1,'','/YuJianRoom/Contents/UploadFile/HeadImgs/37d18a2c-cb91-4d5c-839c-7758b1f05184.png?a=0290ea03-47a3-4d7f-818f-2a71f107b151','福建省 福州市 闽侯县 ','admin','C4CA4238A0B923820DCC509A6F75849B','','','遇见','','',0,4,0,'2017-09-06 15:24:23',0,'','2018-02-03',0,'',0,'','','',0,'','','','','','',0,0,''),(4,'/YuJianRoom/Contents/UploadFile/HeadImgs/b2c1e448-6c07-44e9-a941-0536329bbd72.mp4','/YuJianRoom/Contents/UploadFile/HeadImgs/a758a12a-7e68-4386-b2a1-ec48c5a2daf6.png?a=69ee7353-166d-49f7-8c44-3f049105f562','福建省 福州市 闽侯县 ','17605036268','C4CA4238A0B923820DCC509A6F75849B','自由职业','运动 音乐 游戏 文学 动漫','孙燕姿','','',16,1,3781,'2017-09-07 21:10:10',999,'140-150','1997-12-11',0,'小学',0,'','','心情很好呢',0,'5000-8000','O','未婚,150-160,A,高中,工人,0-3000','孙红娘从2010年开始从事红娘行业，在这10年间撮合了500对情侣，400多对成功步入婚姻殿堂，帮助了100多个有求偶困难的客户，擅长沟通，值得信赖','商业人士,适龄,终年单身,亲密关系处理','精诚所至，金石为开。',1,1,''),(5,'','/YuJianRoom/Contents/UploadFile/HeadImgs/50fee2e9-1cad-4c33-8154-71e1c3e2daf7.png?a=aa6794ad-60f8-4124-83b5-28895742d8ee','福建省 福州市 闽侯县 ','1234566789','C4CA4238A0B923820DCC509A6F75849B','歌手','运动 游戏 文学 动漫','张韶涵','1234566789','1234566789',7,0,0,'2017-09-22 21:55:14',0,'150-160','1995-05-05',0,'研究生',0,'','','',0,'3000-5000','A','','','','',0,1,''),(6,'','/YuJianRoom/Contents/UploadFile/HeadImgs/0678d45e-b46f-401b-850f-12d6704d0bd2.png?a=bf5a896a-ec3f-4a7e-883d-4f839750a523','福建省 福州市 闽侯县 ','1234567891','C4CA4238A0B923820DCC509A6F75849B','歌手','运动 游戏 文学 动漫','梁静茹','1234567891','1234567891',11,1,0,'2017-09-22 21:58:39',0,'150-160','1995-05-05',0,'小学',0,'','','',0,'0-3000','AB','','','','',0,1,''),(7,'/YuJianRoom/Contents/UploadFile/HeadImgs/a74e3946-2098-4f7a-9d42-82f35b583927.mp4','/YuJianRoom/Contents/UploadFile/HeadImgs/ab0437ce-c5ab-4d26-85e3-7b1c84217daf.png?a=cd892494-2ef0-4a85-b174-9658fb7b192c','福建省 福州市 闽侯县 ','1234567892','C4CA4238A0B923820DCC509A6F75849B','演员','运动 游戏 文学 动漫','朱茵','1234567892','1234567892',457,0,0,'2017-09-23 15:45:06',0,'150-160','1995-05-05',0,'博士',0,'','','总有一天，他会驾着七彩云来娶我',0,'0-3000','B','','','','',0,1,''),(8,'','/YuJianRoom/Contents/UploadFile/HeadImgs/e51a3332-2e96-46d0-b3f1-f9ef6b9a42f1.png?a=73f1548b-9f04-416d-931e-1a17139c514c','福建省 福州市 闽侯县 ','1234567893','C4CA4238A0B923820DCC509A6F75849B','演员','运动 游戏 文学 动漫','迪丽热巴','1234567893','1234567893',0,0,0,'2017-09-23 15:54:16',0,'150-160','1995-05-05',0,'硕士',0,'','','',0,'0-3000','A','','','','',0,1,''),(9,'','/YuJianRoom/Contents/UploadFile/HeadImgs/61ac1382-2957-4bb6-8584-702a21520948.png?a=a40a7b51-6d7c-4a6a-9530-aa6b763272f6','福建省 福州市 闽侯县 ','1234567895','C4CA4238A0B923820DCC509A6F75849B','歌手','运动 游戏 文学 动漫','高圆圆','1234567895','1234567895',3,0,0,'2017-09-23 15:58:43',0,'150-160','1995-05-05',0,'本科',0,'','','今天很开心呢！',0,'0-3000','O','','','','',0,1,''),(10,'','/YuJianRoom/Contents/UploadFile/HeadImgs/25feeb3a-3feb-4a26-97b2-7b445fdcb9eb.png?a=13eb0a52-508b-43cf-9111-dfa4adb5fcce','福建省 福州市 闽侯县 ','1234567896','C4CA4238A0B923820DCC509A6F75849B','演员','运动 游戏 文学 动漫','李若彤','1234567896','1234567896',73,0,0,'2017-09-23 16:02:04',0,'150-160','1995-05-06',0,'博士',2,'','','',0,'0-3000','A','','','','',0,1,''),(11,'','/YuJianRoom/Contents/UploadFile/HeadImgs/2078898c-29bc-458b-8314-98786286a808.png?a=ab62688b-3402-4afc-a2f6-c6a2db6669e5','广东省 深圳市 南山区','','','','运动 游戏 文学 动漫','林盈盈','','',25,-1,0,'2017-09-28 15:43:48',0,'150-160','1995-05-05',0,'本科',1,'','','天生丽质',0,'20000+','AB','','','','',0,0,''),(16,'','/YuJianRoom/Contents/UploadFile/HeadImgs/a47676d1-9054-4a19-b249-be6cdbe224ad.png?a=4b0198e9-0cbe-4580-a70d-46f0cb17acf3','福建省 福州市 闽侯县 ','1235555','C4CA4238A0B923820DCC509A6F75849B','歌手 演员','运动 游戏 文学 动漫','杨幂','123555','1235555',9,0,0,'2017-10-09 16:37:13',0,'150-160','1995-05-05',0,'小学',0,'','','生活有诗和远方',0,'5000-8000','B','','','','',0,0,''),(19,'/YuJianRoom/Contents/UploadFile/HeadImgs/de443ede-2ae3-45bc-9af3-1d6caf633557.mp4','/YuJianRoom/Contents/UploadFile/HeadImgs/5f5f7390-d41c-4100-9211-21d542173b27.png','广东省 深圳市 罗湖区','1235555','','工人','音乐 游戏 户外 艺术','胡歌','','',0,-1,0,'2017-10-20 18:05:33',0,'170-180','2000-05-05',1,'小学',0,'','','今天心情很好',0,'0-3000','O','未婚,160-170,O,初中,IT,','','','',0,0,''),(21,'/YuJianRoom/Contents/UploadFile/HeadImgs/0a6d15d6-0a78-41d0-9acd-a3ef47e41d1d.MOV','/YuJianRoom/Contents/UploadFile/HeadImgs/c4d90e0c-c570-4ea9-833a-23c6c067d1f3.png?a=f2d28cb1-ef4a-4927-bba9-9581a110df17','福建省 福州市 闽侯县 ','17605036258','C4CA4238A0B923820DCC509A6F75849B','程序员','运动 游戏 文学 动漫','蔡城虎','17605036258','1126670571',0,1,890,'2017-11-11 18:56:49',0,'150-160','2000-05-05',1,'小学',0,'','oQCh21YVjh2WjA-_jEhqKhc8fJmE','生活有诗和远方',2,'5000-8000','B','未婚,160-170,,本科,IT,5000-8000','','','',0,0,''),(23,'','/YuJianRoom/Contents/UploadFile/HeadImgs/6374fe57-cb0e-4126-ba4a-d4b8eea61b49.png?a=507f9a95-9729-40aa-9a8f-a00710b3c7b0','福建省 福州市 闽侯县 ','17685858585','oQCh21as8CRnxot0hPfXT6D6VIs4','','运动 音乐 游戏 动漫','刘乐乐','1','1',3,0,0,'2017-12-03 19:00:50',0,'150-160','2000-12-03',1,'小学',0,'','oQCh21as8CRnxot0hPfXT6D6VIs4','',0,'','','','','','',0,0,''),(40,'','/YuJianRoom/Contents/UploadFile/HeadImgs/51a3a1f6-9676-4134-a907-8bdca0a6eca2.png?a=041938bc-1736-445e-b5ae-b2302ace63a7','青海省 海西自治州 海西蒙古族藏族自治州直辖 ','15005934304','ozWZnv_3f83syQoXMUPGMa6Wt334','','运动 音乐 游戏 动漫','楠','','',0,0,0,'2018-01-03 00:04:28',0,'140-150','2000-01-03',1,'小学',0,'','ozWZnv_3f83syQoXMUPGMa6Wt334','',0,'','','','','','',0,0,''),(41,'','/YuJianRoom/Contents/UploadFile/HeadImgs/57522eef-7221-41eb-929b-ddf80520db31.png?a=4b3fc963-138b-4aa0-873e-fe56f663797b','福建省 福州市 长乐市 ','18805011533','ozWZnv-kpc3UxencO6O60WiqdQ1A','','音乐 影视 户外 艺术','陈锦华','','',0,0,0,'2018-01-03 08:09:55',0,'170-180','1999-12-06',1,'小学',0,'','ozWZnv-kpc3UxencO6O60WiqdQ1A','哈哈哈哈哈哈哈',0,'','','未婚,170-180,O,博士,工人,3000-5000','','','',0,0,''),(42,'','/YuJianRoom/Contents/UploadFile/HeadImgs/f4efff24-fa2c-41e7-a972-57b19c7c2d83.png?a=4fcf8bd4-9b15-4fe1-81fb-ebf1a8ec2b8c','福建省 厦门市 集美区 ','15005934304','ozWZnvxHTqYHRbA4V4t1zJqvosLc','','运动 游戏 文学 艺术 动漫','贤','','',0,0,0,'2018-01-03 08:43:11',0,'140-150','2000-01-03',1,'小学',0,'','ozWZnvxHTqYHRbA4V4t1zJqvosLc','',0,'','','','','','',0,0,''),(43,'','','','','ozWZnv41l5gVvnU4adGqteGmFMaU','','','','','',0,0,0,'2018-01-03 22:52:52',0,'','2018-01-03',0,'',0,'','ozWZnv41l5gVvnU4adGqteGmFMaU','',0,'','','','','','',0,0,''),(44,'','/YuJianRoom/Contents/UploadFile/HeadImgs/c1c80dc7-1130-45b5-94fc-37a6554fbf41.png?a=15609197-44b4-4ec9-9fe6-2a52d4c7385c','福建省 福州市 闽侯县 ','17605036258','ozWZnvxdOkGpJtlceWhDQNXh4i8g','','运动 音乐 游戏 户外 艺术','小菜','12','12',0,1,102,'2018-01-03 22:57:09',0,'160-170','1996-03-05',1,'本科',0,'','ozWZnvxdOkGpJtlceWhDQNXh4i8g','',0,'','','','','','',0,0,''),(45,'','/YuJianRoom/Contents/UploadFile/HeadImgs/c1df36da-8265-4830-8422-c9b87d867ffc.png?a=e2e6cd28-bb64-4973-91d4-eb2ae9cdb4d6','福建省 福州市 鼓楼区  ','18344913661','ozWZnv9ZCVFtjMC_pBPNYbGCIzvA','','运动 影视 美食 艺术','周','','',1,0,0,'2018-01-03 23:11:16',0,'160-170','1995-10-25',0,'本科',0,'','ozWZnv9ZCVFtjMC_pBPNYbGCIzvA','',0,'','','','','','',0,0,''),(50,'','/YuJianRoom/Contents/UploadFile/HeadImgs/dfthead.png','山东省','1231332','C51CE410C124A10E0DB5E4B97FC2AF39','','','测试','','',0,2,0,'2018-01-13 11:16:35',0,'','2018-02-03',0,'',0,'','','',0,'','','','','','',0,0,''),(52,'','/YuJianRoom/Contents/UploadFile/HeadImgs/dfthead.png?a=a0b5e83c-ecff-4326-ba87-07b3b5198fd7','福建省','admin2','C4CA4238A0B923820DCC509A6F75849B','','','测试','','',0,2,0,'2018-02-03 15:33:26',0,'','2018-02-04',0,'',0,'','','',0,'','','','','','',0,0,''),(54,'','/YuJianRoom/Contents/UploadFile/HeadImgs/3f629cf6-5204-499d-b8bf-7b8448b3c729.png?a=6c02a32b-6897-428b-9b3f-e6182bc2a360','河南省','admin1','202CB962AC59075B964B07152D234B70','','','测试','','',0,2,0,'2018-02-04 23:21:48',0,'','2018-02-04',0,'',0,'','','',0,'','','','','','',0,0,'');

/*Table structure for table `userseekintroducts` */

DROP TABLE IF EXISTS `userseekintroducts`;

CREATE TABLE `userseekintroducts` (
  `userSeekIntroductID` varchar(32) NOT NULL COMMENT '主键',
  `fromUserID` bigint(20) NOT NULL COMMENT '来源用户编号',
  `toUserId` bigint(20) NOT NULL COMMENT '目标用户编号',
  `userSeekIntroductTime` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`userSeekIntroductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userseekintroducts` */

insert  into `userseekintroducts`(`userSeekIntroductID`,`fromUserID`,`toUserId`,`userSeekIntroductTime`) values ('SI10622473676021',4,11,'2018-01-25 22:47:37'),('SI11423200366111',4,4,'2018-01-26 23:20:04'),('SI18923105713613',7,-1,'2018-01-26 23:10:57'),('SI23923144856011',7,16,'2018-01-26 23:14:49'),('SI45323094439811',7,10,'2018-01-26 23:09:44'),('SI58023150039613',7,-1,'2018-01-26 23:15:00'),('SI72419253969611',21,40,'2018-03-16 19:25:40'),('SI89423153085515',7,-1,'2018-01-26 23:15:31'),('SI95823204727011',7,4,'2018-01-26 23:20:47');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
