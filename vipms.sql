/*
Navicat MySQL Data Transfer

Source Server         : graduate
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : vipms

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2019-07-08 22:25:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `vipuser`
-- ----------------------------
DROP TABLE IF EXISTS `vipuser`;
CREATE TABLE `vipuser` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vipuser
-- ----------------------------
INSERT INTO `vipuser` VALUES ('0116191229', 'test', 'test', 'test');
INSERT INTO `vipuser` VALUES ('0716153051', 'sdf', 'a', 'a');
INSERT INTO `vipuser` VALUES ('root', 'root', 'root', 'root');
