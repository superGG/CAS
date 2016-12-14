/*
Navicat MySQL Data Transfer

Source Server         : superGG
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : clubsystem

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-14 18:37:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动编号',
  `club_id` int(11) DEFAULT NULL COMMENT '社团编号',
  `title` varchar(255) DEFAULT NULL COMMENT '社团标题',
  `content` text COMMENT '活动内容',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('9', '3', '【活动】交际舞社隆重诞生！欢饮广大师生加入！！！！', '&lt;p&gt;&lt;span style=\"font-size:48px\"&gt;&lt;span style=\"background-color:#ffd700\"&gt;招新啦！！！！！&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;\n\n&lt;p&gt;&lt;span style=\"font-size:48px\"&gt;&lt;span style=\"background-color:#ffd700\"&gt;&lt;img alt=\"\" src=\"/ClubSystem//activity/cc3dd628-2ad5-4dde-b84a-ad5c941c0f92.jpg\" style=\"height:400px; width:480px\" /&gt;&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;\n', '2016-12-13 16:37:45');
INSERT INTO `activity` VALUES ('10', '2', '大淫会', '&lt;p style=\"text-align:center\"&gt;&lt;span style=\"color:#ffff00\"&gt;&lt;span style=\"font-size:48px\"&gt;大淫会&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;\n\n&lt;p style=\"text-align:center\"&gt;[a];nbsp; [a];nbsp;[a];nbsp;&lt;span style=\"font-size:20px\"&gt;本协会将于12月15日举行绝世大淫会，欢迎大家前来淫乱！！&lt;/span&gt;&lt;/p&gt;\n\n&lt;p style=\"text-align:center\"&gt;&lt;span style=\"font-size:20px\"&gt;[a];nbsp; [a];nbsp;&lt;span style=\"color:#ff0000\"&gt;等着你哟~~&lt;img alt=\"kiss\" src=\"http://192.168.137.1:8080/ClubSystem/js/ckeditor/plugins/smiley/images/kiss.png\" style=\"height:23px; width:23px\" title=\"kiss\" /&gt;&lt;img alt=\"kiss\" src=\"http://192.168.137.1:8080/ClubSystem/js/ckeditor/plugins/smiley/images/kiss.png\" style=\"height:23px; width:23px\" title=\"kiss\" /&gt;&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;\n\n&lt;p style=\"text-align:center\"&gt;&lt;img alt=\"\" src=\"/ClubSystem//activity/66066a43-d879-42a2-a9c7-bc1d6f88bf44.jpg\" style=\"height:400px; width:300px\" /&gt;&lt;/p&gt;\n', '2016-12-13 16:42:35');
INSERT INTO `activity` VALUES ('11', '3', '【公告】不如跳舞', '&lt;p&gt;&lt;img alt=\"\" src=\"/ClubSystem//activity/fd9c2243-0a90-4fee-b1e9-b5b81b41ac68.jpg\" style=\"height:400px; width:480px\" /&gt;&lt;/p&gt;\n', '2016-12-13 16:44:31');
INSERT INTO `activity` VALUES ('13', '3', '【活动】社团烧烤，速速来人！！！', '&lt;p&gt;&lt;span style=\"font-size:48px\"&gt;&lt;span style=\"color:#800080\"&gt;社团创建伊始，要举行一次盛大的烧烤活动，大家在本周星期5前速速报名，联系电话18320363387。&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;\n\n&lt;p&gt;&lt;span style=\"font-size:48px\"&gt;&lt;span style=\"color:#800080\"&gt;&lt;img alt=\"\" src=\"/ClubSystem//activity/fad19545-2609-4739-9d05-9d3fb27e8253.jpg\" style=\"height:640px; width:640px\" /&gt;&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;\n', '2016-12-13 16:51:25');
INSERT INTO `activity` VALUES ('14', '4', '【招新】计算机协会招新啦！！！', '&lt;h1 style=\"text-align:center\"&gt;&lt;strong&gt;计算机协会招新活动&lt;/strong&gt;&lt;/h1&gt;\n\n&lt;p&gt;&lt;span style=\"font-size:20px\"&gt;大家好：&lt;/span&gt;&lt;/p&gt;\n\n&lt;p&gt;[a];nbsp; &lt;span style=\"font-size:20px\"&gt;&lt;span style=\"background-color:#00ffff\"&gt;计算机邪乎是一个有&lt;/span&gt;&lt;/span&gt;&lt;span style=\"font-size:12px\"&gt;&lt;span style=\"background-color:#00ffff\"&gt;组织&lt;/span&gt;&lt;/span&gt;&lt;span style=\"font-size:20px\"&gt;&lt;span style=\"background-color:#00ffff\"&gt;有&lt;/span&gt;&lt;/span&gt;&lt;span style=\"background-color:#00ffff\"&gt;纪律&lt;/span&gt;&lt;span style=\"font-size:20px\"&gt;&lt;span style=\"background-color:#00ffff\"&gt;有&lt;/span&gt;&lt;/span&gt;&lt;span style=\"background-color:#00ffff\"&gt;目标&lt;/span&gt;&lt;span style=\"font-size:20px\"&gt;&lt;span style=\"background-color:#00ffff\"&gt;的&lt;/span&gt;&lt;/span&gt;&lt;span style=\"background-color:#00ffff\"&gt;成长型&lt;/span&gt;&lt;span style=\"font-size:20px\"&gt;&lt;span style=\"background-color:#00ffff\"&gt;社团&lt;/span&gt;，社团集百家之长，充实自己。社团成员&lt;/span&gt;热爱&lt;span style=\"font-size:20px\"&gt;学习，&lt;/span&gt;热爱&lt;span style=\"font-size:20px\"&gt;社员。如果你喜欢&lt;/span&gt;&lt;span style=\"color:#a52a2a\"&gt;&lt;span style=\"font-size:48px\"&gt;逃课&lt;/span&gt;&lt;/span&gt;&lt;span style=\"font-size:20px\"&gt;，上课&lt;/span&gt;&lt;span style=\"font-size:48px\"&gt;&lt;span style=\"color:#00ffff\"&gt;玩手&lt;/span&gt;机&lt;/span&gt;&lt;span style=\"font-size:20px\"&gt;，&lt;/span&gt;&lt;span style=\"color:#006400\"&gt;&lt;span style=\"font-size:48px\"&gt;睡觉&lt;/span&gt;&lt;/span&gt;&lt;span style=\"font-size:20px\"&gt;，那么你就来着吧，这是你的&lt;/span&gt;&lt;span style=\"color:#ffd700\"&gt;&lt;span style=\"font-size:36px\"&gt;天堂&lt;/span&gt;&lt;/span&gt;&lt;span style=\"font-size:20px\"&gt;。如果你喜欢&lt;/span&gt;&lt;span style=\"color:#ffd700\"&gt;&lt;span style=\"font-size:36px\"&gt;玩&lt;/span&gt;&lt;/span&gt;&lt;span style=\"font-size:20px\"&gt;，喜欢&lt;/span&gt;&lt;span style=\"font-size:36px\"&gt;疯狂&lt;/span&gt;&lt;span style=\"font-size:20px\"&gt;，那也来这吧，这是你的&lt;/span&gt;&lt;span style=\"font-size:28px\"&gt;乐园&lt;/span&gt;&lt;span style=\"font-size:20px\"&gt;。&lt;/span&gt;&lt;/p&gt;\n\n&lt;p style=\"text-align:center\"&gt;&lt;span style=\"font-size:20px\"&gt;&lt;img alt=\"\" src=\"/ClubSystem//activity/b95de832-9394-496d-8b57-30d323759c6d.jpeg\" style=\"height:533px; width:600px\" /&gt;&lt;/span&gt;&lt;/p&gt;\n\n&lt;p style=\"text-align:center\"&gt;&lt;span style=\"font-size:36px\"&gt;&lt;span style=\"background-color:#ee82ee\"&gt;如果你想要加入此社团，那就点击此页面上方[a];ldquo;申请加入[a];rdquo;吧！！&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;\n\n&lt;p&gt;&lt;span style=\"font-size:36px\"&gt;社团出征寸草不生！&lt;img alt=\"\" src=\"/ClubSystem//activity/9d670cd2-2c16-4651-9342-a3c0036dba53.jpg\" style=\"height:283px; width:200px\" /&gt;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~&lt;/span&gt;&lt;/p&gt;\n\n&lt;p&gt;[a];nbsp;&lt;/p&gt;\n', '2016-12-13 16:51:54');
INSERT INTO `activity` VALUES ('15', '5', '都是石头里出生的，为什么就不是猴子呢', '&lt;p&gt;猜书协会招新了&lt;/p&gt;\n\n&lt;p&gt;[a];nbsp;&lt;/p&gt;\n', '2016-12-13 16:57:34');

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '相册编号',
  `club_id` int(11) NOT NULL COMMENT '社团编号',
  `name` varchar(255) DEFAULT NULL COMMENT '相册名称',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `path` varchar(255) DEFAULT NULL COMMENT '封面路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES ('2', '2', '社庆', '2016-12-13 16:30:24', '/photo/网球协会/社庆/IMG_2565.JPG');
INSERT INTO `album` VALUES ('3', '3', '葫芦娃', '2016-12-13 16:31:40', '/photo/交际舞社/葫芦娃/img-3f98cf9770b97c3fd9901b6a1be4b5ed.jpg');
INSERT INTO `album` VALUES ('4', '2', '春宫图', '2016-12-13 16:33:00', '/photo/网球协会/春宫图/0c1d97b8f73b74ee7f741426410048f352833eda8d0a-MC2dVN_fw236.jpg');
INSERT INTO `album` VALUES ('5', '4', '美好时光', '2016-12-13 16:35:42', '/photo/计算机协会/美好时光/20140517_191821.jpg');
INSERT INTO `album` VALUES ('6', '4', '当年那日', '2016-12-13 16:37:48', '/album/001.jpg');
INSERT INTO `album` VALUES ('7', '5', '非礼勿点', '2016-12-13 16:49:20', '/photo/大保健/非礼勿点/yezi.jpg');
INSERT INTO `album` VALUES ('8', '2', 'xin', '2016-12-13 17:17:53', '/photo/淫乱协会/xin/DSC_0030_副本.jpg');

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '申请表编号',
  `club_id` int(11) NOT NULL COMMENT '社团编号',
  `detail_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `position_id` int(11) DEFAULT NULL COMMENT '社团职位id',
  `name` varchar(255) DEFAULT NULL COMMENT '申请人名称',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `sex` bit(1) DEFAULT b'1' COMMENT '1男 0 女',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `major_class` varchar(255) DEFAULT NULL COMMENT '专业班级',
  `introduce` text COMMENT '个人简介',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `reason` text COMMENT '申请理由',
  `statue` int(11) DEFAULT '2' COMMENT '0同意 1拒绝 2未审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES ('2', '2', '10', '6', '寂寞女孩', '410654146@qq.com', '', null, null, '网球、足球、篮球、羽毛球', '18320489492', null, null, '2016-12-13 16:28:11', null, '0');
INSERT INTO `apply` VALUES ('3', '3', '11', '2', 'Dikuni', null, '', null, null, '画画，吟诗作对，看漫画，看小说，看电影。', '18320363387', null, null, '2016-12-13 16:30:24', null, '0');
INSERT INTO `apply` VALUES ('4', '4', '13', '8', 'Chuancy', null, '', null, null, '无', '18344094195', null, null, '2016-12-13 16:32:30', null, '0');
INSERT INTO `apply` VALUES ('5', '2', '12', '7', '底裤泥', 'yuema@163.com', '\0', '9999', '汉', 'lalalala', '1643246165', '软件', 'hahahaha', '2016-12-13 16:35:28', '要理由吗', '0');
INSERT INTO `apply` VALUES ('8', '3', '12', null, '空', '测试', '', '22', '汉', '阿拉法发掘', '11651654', '哈哈啊哈', '法定发掘', '2016-12-13 16:40:07', '不如跳舞', '1');
INSERT INTO `apply` VALUES ('9', '2', '11', null, '梁伟栋', '534798430@qq.com', '', '22', '汉族', '打网球。', '18318858328', '软件1133班', '我爱打网球。', '2016-12-13 17:16:25', '大四了，还能加入吗？', '0');
INSERT INTO `apply` VALUES ('12', '4', '12', null, '底裤泥', '1654684@qq.com', '', '22', '', '撸码', '1324560', '软件', '下辈子不撸码', '2016-12-13 16:53:41', '不想下辈子撸码', '0');
INSERT INTO `apply` VALUES ('13', '5', '14', null, '', '', '', null, '', '', '', '', '', '2016-12-13 16:53:40', '', '0');
INSERT INTO `apply` VALUES ('14', '4', '11', null, '', '', '', null, '', '', '', '', '', '2016-12-13 16:56:09', '', '0');
INSERT INTO `apply` VALUES ('15', '5', '16', null, '', '', '', null, '', '', '', '', '', '2016-12-13 17:08:22', '', '2');
INSERT INTO `apply` VALUES ('16', '3', '12', null, '底裤泥', '5464', '', '1223', '汉', '发发', '1643246165', '啦啦啦', '发发啊', '2016-12-13 17:10:58', '发呆发', '0');
INSERT INTO `apply` VALUES ('17', '3', '15', null, '宋文光', '451131651@qq.com', '', '18', '汉', '网球', '18320489492', '软件1133', '帅哥一枚', '2016-12-13 17:14:49', '不需要理由！！！', '0');
INSERT INTO `apply` VALUES ('18', '6', '16', null, '疯狂的石头', null, '', null, null, null, null, null, null, '2016-12-13 17:17:00', null, '0');

-- ----------------------------
-- Table structure for club
-- ----------------------------
DROP TABLE IF EXISTS `club`;
CREATE TABLE `club` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '社团编号',
  `school_id` int(11) DEFAULT NULL COMMENT '学校编号',
  `type_id` int(11) DEFAULT NULL COMMENT '社团类型',
  `detail_id` int(11) DEFAULT NULL COMMENT '创建社团用户',
  `name` varchar(255) DEFAULT NULL COMMENT '社团名称',
  `leader` varchar(255) DEFAULT NULL COMMENT '社长',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `introduce` text COMMENT '社团简介',
  `phone` varchar(255) DEFAULT NULL COMMENT '社团电话',
  `email` varchar(255) DEFAULT NULL COMMENT '社团邮箱',
  `badge` varchar(255) DEFAULT NULL COMMENT '社徽',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of club
-- ----------------------------
INSERT INTO `club` VALUES ('2', '13', '5', '10', '淫乱协会', '寂寞女孩', '2016-12-13 16:28:11', '中国网球，由我们来拯救！！！', '18320489492', null, '/shehuipath/1451bf9d-e95c-46e0-823d-7055361226e9.png');
INSERT INTO `club` VALUES ('3', '13', '5', '11', '交际舞', 'Dikuni', '2016-12-13 16:30:24', '交际舞能够很好的促进人与人之间的交流。', '18320363387', 'widung@163.com', '/shehuipath/c015c4c6-e914-4354-842f-2ea8979d0833.jpg');
INSERT INTO `club` VALUES ('4', '19', '7', '13', '计算机协会', 'Chuancy', '2016-12-13 16:32:30', '这是一个IT型的社团！', '18344094195', '2430324843@qq.com', '/shehuipath/959c465f-537b-466f-b751-b3717ce967e0.jpg');
INSERT INTO `club` VALUES ('5', '16', '3', '14', '猜书协会', '狂砍一条街', '2016-12-13 16:46:21', '', '', null, '/shehuipath/693cad61-9c95-44dc-903f-5b6f3a71f1fb.jpg');
INSERT INTO `club` VALUES ('6', '13', '2', '16', '手球社', '疯狂的石头', '2016-12-13 17:17:00', '我们11111111111111111111111111111111fasklfjlkjgblksvm.s,m1l;k1klslajf;lkas12', '13241111234', null, '/shehuipath/eb6050d1-2143-4faa-9e54-ffeb49403f3d.png');

-- ----------------------------
-- Table structure for club_type
-- ----------------------------
DROP TABLE IF EXISTS `club_type`;
CREATE TABLE `club_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型编号',
  `name` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of club_type
-- ----------------------------
INSERT INTO `club_type` VALUES ('2', '体育类', '2016-12-13 16:21:33');
INSERT INTO `club_type` VALUES ('3', '文学类', '2016-12-13 16:21:37');
INSERT INTO `club_type` VALUES ('4', '电竞类', '2016-12-13 16:21:46');
INSERT INTO `club_type` VALUES ('5', '艺术类', '2016-12-13 16:21:55');
INSERT INTO `club_type` VALUES ('6', '经济类', '2016-12-13 16:22:03');
INSERT INTO `club_type` VALUES ('7', 'IT类', '2016-12-13 16:22:23');

-- ----------------------------
-- Table structure for complain
-- ----------------------------
DROP TABLE IF EXISTS `complain`;
CREATE TABLE `complain` (
  `id` int(11) NOT NULL,
  `detail_id` int(11) NOT NULL COMMENT '用户id',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '1 色情  2暴力  3不够色情  4 不够暴力 5看得心情不爽 6无聊无聊就想举报别人  7其他',
  `content` varchar(255) NOT NULL COMMENT '投诉内容',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of complain
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言编号',
  `detail_id` int(11) DEFAULT NULL COMMENT '用户详情编号',
  `father_id` int(11) DEFAULT '0' COMMENT '父级留言编号',
  `content` text COMMENT '留言内容',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `good` int(11) NOT NULL DEFAULT '0' COMMENT '点赞次数',
  `bad` int(11) NOT NULL DEFAULT '0' COMMENT '点踩次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('29', '10', '0', '今晚寂寞，约么？？&lt;img src=\"../../images/expression/6.gif\"&gt;&lt;img src=\"../../images/expression/6.gif\"&gt;', '2016-12-13 16:26:29', '14', '0');
INSERT INTO `message` VALUES ('30', '11', '29', '人妖要吗？', '2016-12-13 16:27:25', '1', '0');
INSERT INTO `message` VALUES ('31', '12', '29', 'A113等你哟&lt;img src=\"../../images/expression/3.gif\"&gt;', '2016-12-13 16:28:04', '0', '0');
INSERT INTO `message` VALUES ('32', '12', '0', '多点数据吧', '2016-12-13 16:40:57', '1', '0');
INSERT INTO `message` VALUES ('33', '14', '0', 'qqqqqqqqqqqqqqqqqqqqqq', '2016-12-13 16:42:41', '12', '0');
INSERT INTO `message` VALUES ('34', '11', '0', '情歌丸子郑烁彬&lt;img src=\"../../images/expression/5.gif\"&gt;', '2016-12-13 16:45:38', '49', '0');
INSERT INTO `message` VALUES ('35', '16', '0', 'qqqqqqqqqqqqqqqqq&lt;div&gt;&lt;br&gt;&lt;/div&gt;', '2016-12-13 17:13:08', '0', '0');

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片编号',
  `album_id` int(11) NOT NULL COMMENT '相册编号',
  `content` varchar(255) DEFAULT NULL COMMENT '图片说明',
  `path` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo
-- ----------------------------
INSERT INTO `photo` VALUES ('3', '2', 'IMG_2564', '/photo/网球协会/社庆/IMG_2564.JPG', '2016-12-13 16:31:40');
INSERT INTO `photo` VALUES ('4', '2', 'IMG_2565', '/photo/网球协会/社庆/IMG_2565.JPG', '2016-12-13 16:32:02');
INSERT INTO `photo` VALUES ('5', '3', '蓝娃', '/photo/交际舞社/葫芦娃/img-1f127aebda00a6f9a09b349e489b931e.jpg', '2016-12-13 16:32:47');
INSERT INTO `photo` VALUES ('6', '3', '红娃', '/photo/交际舞社/葫芦娃/img-3e75b1d7ddd89f34ad8123a94b24a63a.jpg', '2016-12-13 16:32:53');
INSERT INTO `photo` VALUES ('7', '3', '黄娃', '/photo/交际舞社/葫芦娃/img-3f98cf9770b97c3fd9901b6a1be4b5ed.jpg', '2016-12-13 16:32:59');
INSERT INTO `photo` VALUES ('8', '3', '绿娃', '/photo/交际舞社/葫芦娃/img-6eeb603e47a86dcdd2f45eac6b9d965b.jpg', '2016-12-13 16:33:04');
INSERT INTO `photo` VALUES ('9', '4', '0c1d97b8f73b74ee7f741426410048f352833eda8d0a-MC2dVN_fw236', '/photo/网球协会/春宫图/0c1d97b8f73b74ee7f741426410048f352833eda8d0a-MC2dVN_fw236.jpg', '2016-12-13 16:33:11');
INSERT INTO `photo` VALUES ('10', '3', '紫娃', '/photo/交际舞社/葫芦娃/img-58421ff353d7b4ed472362ea09c3b214.jpg', '2016-12-13 16:33:13');
INSERT INTO `photo` VALUES ('11', '4', '8a0be9efgw1eh040693r9j20g40lhdih', '/photo/网球协会/春宫图/8a0be9efgw1eh040693r9j20g40lhdih.jpg', '2016-12-13 16:33:25');
INSERT INTO `photo` VALUES ('12', '4', '11177964_13876936167180', '/photo/网球协会/春宫图/11177964_13876936167180.jpg', '2016-12-13 16:33:28');
INSERT INTO `photo` VALUES ('13', '4', '150850xk01z7v0g87jh11m', '/photo/网球协会/春宫图/150850xk01z7v0g87jh11m.jpg', '2016-12-13 16:33:31');
INSERT INTO `photo` VALUES ('14', '4', '1402023674943', '/photo/网球协会/春宫图/1402023674943.jpg', '2016-12-13 16:33:34');
INSERT INTO `photo` VALUES ('16', '4', '1402023689331', '/photo/网球协会/春宫图/1402023689331.jpg', '2016-12-13 16:33:45');
INSERT INTO `photo` VALUES ('17', '4', '920131104b (1)', '/photo/网球协会/春宫图/920131104b (1).jpg', '2016-12-13 16:33:48');
INSERT INTO `photo` VALUES ('18', '4', '90b75bbagw1ea9691ihm8j20dw0ijt9d', '/photo/网球协会/春宫图/90b75bbagw1ea9691ihm8j20dw0ijt9d.jpg', '2016-12-13 16:33:53');
INSERT INTO `photo` VALUES ('19', '5', 'sample_photo_08', '/photo/计算机协会/美好时光/sample_photo_08.jpg', '2016-12-13 16:36:25');
INSERT INTO `photo` VALUES ('20', '5', 'sample_photo_01', '/photo/计算机协会/美好时光/sample_photo_01.jpg', '2016-12-13 16:36:39');
INSERT INTO `photo` VALUES ('21', '5', '20140502_151015', '/photo/计算机协会/美好时光/20140502_151015.jpg', '2016-12-13 16:37:06');
INSERT INTO `photo` VALUES ('22', '5', '20140517_191821', '/photo/计算机协会/美好时光/20140517_191821.jpg', '2016-12-13 16:37:27');
INSERT INTO `photo` VALUES ('23', '6', 'QQ图片20160313093740', '/photo/计算机协会/社会活动日/QQ图片20160313093740.jpg', '2016-12-13 16:38:10');
INSERT INTO `photo` VALUES ('24', '6', 'WP_000491', '/photo/计算机协会/社会活动日/WP_000491.jpg', '2016-12-13 16:38:58');
INSERT INTO `photo` VALUES ('25', '6', 'WP_000515', '/photo/计算机协会/社会活动日/WP_000515.jpg', '2016-12-13 16:39:13');
INSERT INTO `photo` VALUES ('26', '6', 'WP_000565', '/photo/计算机协会/社会活动日/WP_000565.jpg', '2016-12-13 16:39:23');
INSERT INTO `photo` VALUES ('27', '7', 'yezi', '/photo/大保健/非礼勿点/yezi.jpg', '2016-12-13 16:49:30');
INSERT INTO `photo` VALUES ('28', '7', '6f88b0e903d5e7bdc84c1dae52226f7b', '/photo/大保健/非礼勿点/6f88b0e903d5e7bdc84c1dae52226f7b.jpg', '2016-12-13 16:49:35');
INSERT INTO `photo` VALUES ('29', '8', 'DSC_0030_副本', '/photo/淫乱协会/xin/DSC_0030_副本.jpg', '2016-12-13 17:18:00');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '职位编号',
  `club_id` int(11) DEFAULT NULL COMMENT '社团编号',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `name` varchar(255) DEFAULT NULL COMMENT '职位名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('2', '3', '2016-12-13 16:35:28', '社长');
INSERT INTO `position` VALUES ('3', '3', '2016-12-13 16:35:34', '副社长');
INSERT INTO `position` VALUES ('4', '3', '2016-12-13 16:35:42', '部长');
INSERT INTO `position` VALUES ('5', '3', '2016-12-13 16:35:51', '成员');
INSERT INTO `position` VALUES ('6', '2', '2016-12-13 16:36:36', '大妈');
INSERT INTO `position` VALUES ('7', '2', '2016-12-13 16:37:13', '淫妇');
INSERT INTO `position` VALUES ('8', '4', '2016-12-13 16:40:44', '社长');
INSERT INTO `position` VALUES ('9', '4', '2016-12-13 16:40:54', '成员');
INSERT INTO `position` VALUES ('10', '5', '2016-12-13 16:47:31', '执健长');
INSERT INTO `position` VALUES ('12', '5', '2016-12-13 17:00:25', '流光使者');

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学校编号',
  `name` varchar(255) DEFAULT NULL COMMENT '学校名称',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('13', '广东海洋大学', '2016-12-13 16:20:18');
INSERT INTO `school` VALUES ('14', '清华大学', '2016-12-13 16:20:26');
INSERT INTO `school` VALUES ('15', '北京大学', '2016-12-13 16:20:35');
INSERT INTO `school` VALUES ('16', '岭南师范大学', '2016-12-13 16:20:42');
INSERT INTO `school` VALUES ('17', '广东医学院', '2016-12-13 16:21:08');
INSERT INTO `school` VALUES ('18', '广州大学', '2016-12-13 16:21:14');
INSERT INTO `school` VALUES ('19', '深圳大学', '2016-12-13 16:21:19');
INSERT INTO `school` VALUES ('20', '武汉大学', '2016-12-13 16:21:24');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `account` varchar(255) DEFAULT NULL COMMENT '用户帐号',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('52', 'Kellan', 'e10adc3949ba59abbe56e057f20f883e', '2016-12-13 16:23:59');
INSERT INTO `user` VALUES ('53', 'Dikuni', '3069c379516c76859d26fc1f60338d9f', '2016-12-13 16:24:28');
INSERT INTO `user` VALUES ('54', 'chenxinglong', 'e10adc3949ba59abbe56e057f20f883e', '2016-12-13 16:26:54');
INSERT INTO `user` VALUES ('55', 'mcy123', 'e10adc3949ba59abbe56e057f20f883e', '2016-12-13 16:28:20');
INSERT INTO `user` VALUES ('56', '风花雪月12', '96e79218965eb72c92a549dd5a330112', '2016-12-13 16:42:16');
INSERT INTO `user` VALUES ('57', 'Kellan2', 'e10adc3949ba59abbe56e057f20f883e', '2016-12-13 17:03:42');
INSERT INTO `user` VALUES ('58', '123456', 'e10adc3949ba59abbe56e057f20f883e', '2016-12-13 17:06:40');

-- ----------------------------
-- Table structure for user_details
-- ----------------------------
DROP TABLE IF EXISTS `user_details`;
CREATE TABLE `user_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户详情编号',
  `user_id` int(11) unsigned DEFAULT NULL,
  `role_id` int(11) DEFAULT '0' COMMENT '角色',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `sex` bit(1) DEFAULT b'1' COMMENT '1 男  0 女',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `singnation` varchar(255) DEFAULT NULL COMMENT '个性签名',
  `head_path` varchar(255) DEFAULT NULL COMMENT '头像路径',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_details
-- ----------------------------
INSERT INTO `user_details` VALUES ('10', '52', '1', '寂寞女孩', '', '18320489492', '410654146@qq.com', '网球、足球、篮球、羽毛球', '运动，永无止境！！！', '/headpath/0ffef7f1-8338-4952-b718-a551bb207bf1.png', '2016-12-13 16:23:59');
INSERT INTO `user_details` VALUES ('11', '53', '1', 'Dikuni', '', '18320363387', 'widung@163.com', '画画，吟诗作对，看漫画，看小说，看电影。', '兴趣广泛，喜欢交友。', '/headpath/5fc99d1e-3e4e-4db3-a662-c58750c214f1.jpg', '2016-12-13 16:24:28');
INSERT INTO `user_details` VALUES ('12', '54', '1', '底裤泥', '\0', '16413541', '16431654@163.com', '通宵打游戏', '通宵打游戏', '/headpath/beb4f02e-856f-4de7-8099-70b9ad7ee93c.jpg', '2016-12-13 16:26:54');
INSERT INTO `user_details` VALUES ('13', '55', '1', 'Chuancy', '', '18344094195', '2430324843@qq.com', '无', '好个性的签名', '/headpath/da7f7a25-5cfa-4113-a087-0f5036c31dd6.jpg', '2016-12-13 16:28:20');
INSERT INTO `user_details` VALUES ('14', '56', '1', '石头', '', null, null, null, null, '/headpath/5799135c-07d8-486c-9858-9b56ee15d95b.jpg', '2016-12-13 16:42:16');
INSERT INTO `user_details` VALUES ('15', '57', '1', 'Kellan', '', '18320489492', '410654146@qq.com', '网球，运动，打代码....', '生命不熄，运动不止！！！', '/headpath/8cfebe6e-7eeb-4ee8-b9bf-bc6ea0c3d255.jpg', '2016-12-13 17:03:42');
INSERT INTO `user_details` VALUES ('16', '58', '1', '疯狂的石头', '', null, null, null, null, '/headpath/850c8d5b-dd1e-48c4-844d-57c0f5eaf8f6.jpg', '2016-12-13 17:06:40');
