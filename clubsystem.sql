/*
Navicat MySQL Data Transfer

Source Server         : superGG
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : clubsystem

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-11-28 10:59:32
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '相册编号',
  `club_id` int(11) DEFAULT NULL COMMENT '社团编号',
  `name` varchar(255) DEFAULT NULL COMMENT '相册名称',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `path` varchar(255) DEFAULT NULL COMMENT '封面路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '申请表编号',
  `detail_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `name` varchar(255) DEFAULT NULL COMMENT '申请人名称',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `sex` bit(1) DEFAULT NULL COMMENT '1男 0 女',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `major_class` varchar(255) DEFAULT NULL COMMENT '专业班级',
  `introduce` text COMMENT '个人简介',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `club_id` int(11) DEFAULT NULL COMMENT '社团编号',
  `reason` text COMMENT '申请理由',
  `statue` int(11) DEFAULT NULL COMMENT '0同意 1拒绝 2未审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for club_type
-- ----------------------------
DROP TABLE IF EXISTS `club_type`;
CREATE TABLE `club_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型编号',
  `name` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言编号',
  `detail_id` int(11) DEFAULT NULL COMMENT '用户详情编号',
  `content` text COMMENT '留言内容',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `father_id` int(11) DEFAULT NULL COMMENT '父级留言编号',
  `good` int(11) DEFAULT NULL COMMENT '点赞次数',
  `bad` int(11) DEFAULT NULL COMMENT '点踩次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片编号',
  `album_id` int(11) DEFAULT NULL COMMENT '相册编号',
  `content` varchar(255) DEFAULT NULL COMMENT '图片说明',
  `path` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学校编号',
  `name` varchar(255) DEFAULT NULL COMMENT '学校名称',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userclub
-- ----------------------------
DROP TABLE IF EXISTS `userclub`;
CREATE TABLE `userclub` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '成员编号',
  `club_id` int(11) DEFAULT NULL COMMENT '社团编号',
  `apply_id` int(11) DEFAULT NULL COMMENT '申请表编号',
  `positon_id` int(11) DEFAULT NULL COMMENT '职位编号',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
