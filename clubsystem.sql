/*
Navicat MySQL Data Transfer

Source Server         : superGG
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : clubsystem

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-11-13 20:50:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `name` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('test', '1', 'test1133');
INSERT INTO `user` VALUES ('Kellan', '2', '软件1133');
INSERT INTO `user` VALUES ('Kellan', '3', '软件1133');
