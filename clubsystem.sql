/*
Navicat MySQL Data Transfer

Source Server         : superGG
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : clubsystem

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-11 17:19:14
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', '1', '测试活动', '测试活动内容', '2016-12-06 15:19:15');
INSERT INTO `activity` VALUES ('2', '1', '测试活动2', '测试活动内同', '2016-12-06 16:25:48');
INSERT INTO `activity` VALUES ('3', '1', '测试活动', '测试活动内容3', '2016-12-06 16:26:06');
INSERT INTO `activity` VALUES ('4', '1', '测试活动内容', '测试活动内容4', '2016-12-06 16:26:28');
INSERT INTO `activity` VALUES ('5', '1', '测试活动内容', '测试活动内容5', '2016-12-06 16:26:46');
INSERT INTO `activity` VALUES ('6', '1', '测试活动', '测试活动内容6', '2016-12-06 16:27:01');
INSERT INTO `activity` VALUES ('7', '1', '测试活动', '测试活动内', '2016-12-06 16:27:28');
INSERT INTO `activity` VALUES ('8', '1', '顶顶顶', '顶顶顶', '2016-12-06 16:51:12');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album
-- ----------------------------

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '申请表编号',
  `detail_id` int(11) DEFAULT NULL COMMENT '用户编号',
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
  `club_id` int(11) NOT NULL COMMENT '社团编号',
  `reason` text COMMENT '申请理由',
  `statue` int(11) DEFAULT '2' COMMENT '0同意 1拒绝 2未审核',
  `position_id` int(11) DEFAULT NULL COMMENT '社团职位id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES ('1', '7', '宋文光', '410654146@qq.com', '', '16', '汉', '网球', '18320489492', '软件1133', '很帅', '2016-12-06 17:00:24', '1', '我爱网球', '0', '1');

-- ----------------------------
-- Table structure for club
-- ----------------------------
DROP TABLE IF EXISTS `club`;
CREATE TABLE `club` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '社团编号',
  `school_id` int(11) DEFAULT NULL COMMENT '学校编号',
  `name` varchar(255) DEFAULT NULL COMMENT '社团名称',
  `leader` varchar(255) DEFAULT NULL COMMENT '社长',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `introduce` text COMMENT '社团简介',
  `phone` varchar(255) DEFAULT NULL COMMENT '社团电话',
  `email` varchar(255) DEFAULT NULL COMMENT '社团邮箱',
  `type_id` int(11) DEFAULT NULL COMMENT '社团类型',
  `badge` varchar(255) DEFAULT NULL COMMENT '社徽',
  `detail_id` int(11) DEFAULT NULL COMMENT '创建社团用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of club
-- ----------------------------
INSERT INTO `club` VALUES ('1', '1', '网球协会', '宋文光', '2016-12-06 15:18:32', '水粉笔少部分is部分is的那份神农大丰三大你', '18320489492', '410654146@qq.com', '1', '/images/003.jpg', '6');

-- ----------------------------
-- Table structure for clubcreate
-- ----------------------------
DROP TABLE IF EXISTS `clubcreate`;
CREATE TABLE `clubcreate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '申请表编号',
  `detail_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `name` varchar(255) DEFAULT NULL COMMENT '申请人名称',
  `reason` text COMMENT '申请理由',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `statue` int(11) DEFAULT NULL COMMENT '0同意 1拒绝 2未审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clubcreate
-- ----------------------------

-- ----------------------------
-- Table structure for club_type
-- ----------------------------
DROP TABLE IF EXISTS `club_type`;
CREATE TABLE `club_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型编号',
  `name` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of club_type
-- ----------------------------
INSERT INTO `club_type` VALUES ('1', '体育类', '2016-12-06 10:37:23');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言编号',
  `detail_id` int(11) DEFAULT NULL COMMENT '用户详情编号',
  `content` text COMMENT '留言内容',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `father_id` int(11) DEFAULT '0' COMMENT '父级留言编号',
  `good` int(11) NOT NULL DEFAULT '0' COMMENT '点赞次数',
  `bad` int(11) NOT NULL DEFAULT '0' COMMENT '点踩次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '8', '测试留言', '2016-12-01 11:02:05', '0', '0', '0');
INSERT INTO `message` VALUES ('2', '8', '测试留言1', '2016-12-01 11:02:25', '0', '0', '0');
INSERT INTO `message` VALUES ('3', '8', '测试留言2', '2016-12-01 11:02:29', '0', '0', '0');
INSERT INTO `message` VALUES ('4', '7', '测试子留言', '2016-12-01 11:03:17', '3', '0', '0');
INSERT INTO `message` VALUES ('5', '6', '测试子留言3', '2016-12-01 11:03:37', '3', '0', '0');
INSERT INTO `message` VALUES ('6', '5', '测试子留言2', '2016-12-01 11:03:57', '2', '0', '0');
INSERT INTO `message` VALUES ('7', '5', '测试子留言2', '2016-12-01 11:03:59', '2', '0', '0');
INSERT INTO `message` VALUES ('8', '4', '测试子留言1', '2016-12-01 11:04:13', '1', '0', '0');
INSERT INTO `message` VALUES ('9', '4', '测试子留言1', '2016-12-01 11:04:15', '1', '0', '0');
INSERT INTO `message` VALUES ('10', '4', '测试子留言1', '2016-12-01 11:36:12', '1', '0', '0');
INSERT INTO `message` VALUES ('11', '4', '测试子留言1', '2016-12-01 11:37:54', '1', '0', '0');
INSERT INTO `message` VALUES ('12', '4', '测试子留言1', '2016-12-01 11:46:30', '1', '0', '0');
INSERT INTO `message` VALUES ('13', '4', '测试父留言4', '2016-12-01 15:40:01', '0', '0', '0');
INSERT INTO `message` VALUES ('14', '4', '测试父留言5', '2016-12-01 15:40:04', '0', '0', '0');
INSERT INTO `message` VALUES ('15', '4', '测试父留言6', '2016-12-01 15:40:07', '0', '0', '0');
INSERT INTO `message` VALUES ('16', '4', '测试父留言7', '2016-12-01 15:40:10', '0', '0', '0');
INSERT INTO `message` VALUES ('17', '4', '测试父留言8', '2016-12-01 15:40:13', '0', '0', '0');
INSERT INTO `message` VALUES ('18', '4', '测试父留言9', '2016-12-01 15:40:16', '0', '0', '0');
INSERT INTO `message` VALUES ('19', '4', '测试父留言10', '2016-12-01 15:40:20', '0', '0', '0');
INSERT INTO `message` VALUES ('20', '4', '测试父留言11', '2016-12-01 15:40:23', '0', '0', '0');
INSERT INTO `message` VALUES ('21', '4', '测试父留言12', '2016-12-01 15:40:25', '0', '0', '0');
INSERT INTO `message` VALUES ('22', '4', '测试父留言13', '2016-12-01 15:40:28', '0', '0', '0');
INSERT INTO `message` VALUES ('23', '4', '测试父留言14', '2016-12-01 15:40:30', '0', '0', '0');
INSERT INTO `message` VALUES ('24', '4', '测试父留言15', '2016-12-01 15:40:33', '0', '0', '0');
INSERT INTO `message` VALUES ('25', '4', '测试父留言16', '2016-12-01 15:40:35', '0', '0', '0');
INSERT INTO `message` VALUES ('26', '4', '测试子留言1', '2016-12-01 22:33:25', '1', '2', '0');
INSERT INTO `message` VALUES ('27', '6', '测试子留言1', '2016-12-02 15:52:17', '1', '0', '0');
INSERT INTO `message` VALUES ('28', '6', '测试子留言1', '2016-12-02 15:52:50', '1', '0', '0');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', '1', '2016-12-06 16:56:40', '社长');

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学校编号',
  `name` varchar(255) DEFAULT NULL COMMENT '学校名称',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES ('1', '1海洋大学', '2016-11-28 16:26:11');
INSERT INTO `school` VALUES ('2', '广东海洋大学', '2016-11-28 22:29:14');
INSERT INTO `school` VALUES ('3', '广东海洋大学', '2016-11-28 22:47:18');
INSERT INTO `school` VALUES ('4', '广东海洋大学', '2016-11-28 23:08:58');
INSERT INTO `school` VALUES ('10', '广东海洋大学', '2016-11-29 09:15:53');
INSERT INTO `school` VALUES ('11', '广东海洋大学', '2016-11-29 09:21:03');
INSERT INTO `school` VALUES ('12', '广东海洋大学', '2016-11-29 10:37:09');

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
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('46', 'kellan123', 'e10adc3949ba59abbe56e057f20f883e', '2016-11-30 11:06:12');
INSERT INTO `user` VALUES ('47', 'Kellan', 'e10adc3949ba59abbe56e057f20f883e', '2016-11-30 16:56:56');
INSERT INTO `user` VALUES ('48', 'Kellan2', 'e10adc3949ba59abbe56e057f20f883e', '2016-11-30 16:59:59');
INSERT INTO `user` VALUES ('49', 'kellan124', 'e10adc3949ba59abbe56e057f20f883e', '2016-11-30 17:04:11');
INSERT INTO `user` VALUES ('50', 'Kellan23', 'e10adc3949ba59abbe56e057f20f883e', '2016-11-30 17:09:54');
INSERT INTO `user` VALUES ('51', 'kellan123456', '25f9e794323b453885f5181f1b624d0b', '2016-12-02 16:25:41');

-- ----------------------------
-- Table structure for userclub
-- ----------------------------
DROP TABLE IF EXISTS `userclub`;
CREATE TABLE `userclub` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '成员编号',
  `club_id` int(11) DEFAULT NULL COMMENT '社团编号',
  `apply_id` int(11) DEFAULT NULL COMMENT '申请表编号',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `position_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userclub
-- ----------------------------
INSERT INTO `userclub` VALUES ('1', '1', '1', '2016-12-06 16:56:16', '1');

-- ----------------------------
-- Table structure for user_details
-- ----------------------------
DROP TABLE IF EXISTS `user_details`;
CREATE TABLE `user_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户详情编号',
  `user_id` int(11) unsigned DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `sex` bit(1) DEFAULT b'1' COMMENT '1 男  0 女',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `singnation` varchar(255) DEFAULT NULL COMMENT '个性签名',
  `head_path` varchar(255) DEFAULT NULL COMMENT '头像路径',
  `role_id` int(11) DEFAULT '0' COMMENT '角色',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_details
-- ----------------------------
INSERT INTO `user_details` VALUES ('4', '46', '用户46', '', null, null, null, null, '/headpath/aaa.jpg', '1', '2016-11-30 11:06:13');
INSERT INTO `user_details` VALUES ('5', '47', '用户47', '', null, null, null, null, '/headpath/aaa.jpg', '1', '2016-11-30 16:56:58');
INSERT INTO `user_details` VALUES ('6', '48', '用户48', '', null, null, null, null, '/headpath/aaa.jpg', '1', '2016-11-30 16:59:59');
INSERT INTO `user_details` VALUES ('7', '49', '用户49', '', null, null, null, null, '/headpath/aaa.jpg', '1', '2016-11-30 17:04:11');
INSERT INTO `user_details` VALUES ('8', '50', '用户50', '', null, null, null, null, '/headpath/aaa.jpg', '1', '2016-11-30 17:09:54');
INSERT INTO `user_details` VALUES ('9', '51', '用户51', '', null, null, null, null, '/headpath/aaa.jpg', '1', '2016-12-02 16:25:41');
