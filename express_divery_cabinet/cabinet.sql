/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.0.1
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : cabinet

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 18/04/2024 11:35:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for approval
-- ----------------------------
DROP TABLE IF EXISTS `approval`;
CREATE TABLE `approval`  (
  `approval_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `class_id` int(11) NULL DEFAULT NULL,
  `items_subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `items_status` int(11) NULL DEFAULT NULL COMMENT '消息审核是否通过，0表示审批未通过，1表示审批通过，2表示未审批',
  PRIMARY KEY (`approval_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of approval
-- ----------------------------
INSERT INTO `approval` VALUES ('5d2c5cb6fd11225799c002b60058bdb7', '小王', '17abb532e5a6ec903803679515a28874', 1, '水果捞', '通过一下呗-。-', '2024-01-12 00:00:00', '2024-01-18 00:00:00', 2);
INSERT INTO `approval` VALUES ('6c324faac58596a6a3b2411832e01251', '小王', '17abb532e5a6ec903803679515a28874', 1, '水果捞', '通过一下呗-。-', NULL, '2024-01-18 00:00:00', 1);
INSERT INTO `approval` VALUES ('759bb4921fb18087781bcff1ddff93f8', '小王', '17abb532e5a6ec903803679515a28874', 1, '水果捞', '通过一下呗-。-', '2024-01-12 00:00:00', '2024-01-18 00:00:00', 0);
INSERT INTO `approval` VALUES ('aaf6cdadb9ea2c3b273e9a37e55aef09', '小王', '17abb532e5a6ec903803679515a28874', 1, '水果捞', '通过一下呗-。-', '2024-01-12 00:00:00', '2024-01-18 00:00:00', 1);

-- ----------------------------
-- Table structure for approval_record
-- ----------------------------
DROP TABLE IF EXISTS `approval_record`;
CREATE TABLE `approval_record`  (
  `approval_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息id',
  `approval_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批人',
  `approval_advice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批意见',
  `approval_time` timestamp(0) NULL DEFAULT NULL COMMENT '审批时间',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of approval_record
-- ----------------------------
INSERT INTO `approval_record` VALUES ('aaf6cdadb9ea2c3b273e9a37e55aef09', '17abb532e5a6ec903803679515a28874', '同意', '2024-04-18 01:29:30', 1);
INSERT INTO `approval_record` VALUES ('aaf6cdadb9ea2c3b273e9a37e55aef09', '17abb532e5a6ec903803679515a28874', '同意', '2024-04-18 01:37:58', 2);
INSERT INTO `approval_record` VALUES ('759bb4921fb18087781bcff1ddff93f8', '17abb532e5a6ec903803679515a28874', '同意', '2024-04-18 11:31:00', 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键,uuid',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_account` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `face_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `finger_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `admin_authority` int(1) NULL DEFAULT NULL COMMENT '用数字来代表权限的不同，0表示管理员，1表示班长，2表示队员',
  `class_id` int(11) NULL DEFAULT NULL,
  `door_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `door_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `door_union` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '柜子编号和柜门编号一起决定的编号',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('17abb532e5a6ec903803679515a28874', '小王', '13123456789', '12345678', NULL, NULL, 0, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
