/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80016 (8.0.16)
 Source Host           : localhost:3306
 Source Schema         : phone

 Target Server Type    : MySQL
 Target Server Version : 80016 (8.0.16)
 File Encoding         : 65001

 Date: 21/04/2024 11:58:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for audit_message
-- ----------------------------
DROP TABLE IF EXISTS `audit_message`;
CREATE TABLE `audit_message`  (
  `APPROVAL_ID` INT  NULL DEFAULT NULL COMMENT '审批id',
  `USER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `USER_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `ITEMS_SUBJECT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批事项',
  `USER_REASON` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因',
  `START_TIME` time NULL DEFAULT NULL COMMENT '起始时间',
  `END_TIME` time NULL DEFAULT NULL COMMENT '截止时间',
  `IS_OK` int(11) NULL DEFAULT NULL COMMENT '是否通过',
  `INTEMS_STATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of audit_message
-- ----------------------------

-- ----------------------------
-- Table structure for audit_personnel_permissions
-- ----------------------------
DROP TABLE IF EXISTS `audit_personnel_permissions`;
CREATE TABLE `audit_personnel_permissions`  (
  `USER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `USER_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `FACE_INFORMATION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '面部信息',
  `ADMIN_AUTORITY` int(11) NULL DEFAULT NULL COMMENT '管理权限',
  `DOOR_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '柜子编号',
  `DOOR_NUMBER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '柜门编号',
  `DOOR_UNION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属柜门编号',
  `IS_OK` int(11) NULL DEFAULT NULL COMMENT '是否通过',
  `INTEMS_STATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of audit_personnel_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for cabinet
-- ----------------------------
DROP TABLE IF EXISTS `cabinet`;
CREATE TABLE `cabinet`  (
  `id` int(255) NOT NULL COMMENT '所属柜门编号',
  `door_id` int(255) NOT NULL COMMENT '柜子编号',
  `door_number` int(255) NOT NULL COMMENT '柜门编号',
  `user_id` int(255) NULL DEFAULT NULL COMMENT '账号',
  `start_time` time NULL DEFAULT NULL COMMENT '起始时间',
  `end_time` time NULL DEFAULT NULL COMMENT '截止时间',
  `door_status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `update_user` int(11) NULL DEFAULT NULL COMMENT '更新人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cabinet
-- ----------------------------
INSERT INTO `cabinet` VALUES (1, 1, 1, 1, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cabinet` VALUES (2, 2, 1, 4, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cabinet` VALUES (3, 3, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `cabinet` VALUES (4, 4, 1, NULL, '08:22:23', '22:22:27', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for registration_table
-- ----------------------------
DROP TABLE IF EXISTS `registration_table`;
CREATE TABLE `registration_table`  (
  `USER_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `USER_PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `RE_PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重复输入密码',
  `FACE_INFORMATION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '面部信息',
  `FINGER_INFORMATION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指纹信息',
  `ADMIN_AUTORITY` int(11) NULL DEFAULT NULL COMMENT '管理权限',
  `DOOR_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '柜子编号',
  `DOOR_NUMBER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '柜门编号',
  `APPLY_STATUS` int(11) NULL DEFAULT NULL COMMENT '状态'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of registration_table
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_cabinet
-- ----------------------------
DROP TABLE IF EXISTS `schedule_cabinet`;
CREATE TABLE `schedule_cabinet`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `week` int(11) NOT NULL COMMENT '星期几',
  `start_time` time NOT NULL COMMENT '起始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  `status` int(11) UNSIGNED ZEROFILL NOT NULL COMMENT '状态：1 启用 0 禁用',
  `model` int(11) UNSIGNED ZEROFILL NOT NULL COMMENT '审批模式 1.空闲时间 2.管理时间 3.管控时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `update_user` int(11) NULL DEFAULT NULL COMMENT '更新人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2118889475 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_cabinet
-- ----------------------------
INSERT INTO `schedule_cabinet` VALUES (1, 2, '08:00:00', '18:00:00', 00000000001, 00000000001, '2024-04-03 21:49:44', '2024-04-03 21:49:44', 1, 1);
INSERT INTO `schedule_cabinet` VALUES (2, 3, '09:00:00', '18:00:00', 00000000001, 00000000001, '2024-04-03 21:41:32', '2024-04-03 21:41:32', 1, 1);
INSERT INTO `schedule_cabinet` VALUES (4, 5, '08:57:48', '22:57:53', 00000000001, 00000000001, '2024-04-06 18:58:29', '2024-04-06 18:58:34', 1, 1);
INSERT INTO `schedule_cabinet` VALUES (122298370, 2, '08:00:00', '18:00:00', 00000000001, 00000000001, '2024-04-03 21:49:49', '2024-04-03 21:49:49', 1, 1);

-- ----------------------------
-- Table structure for schedule_charge
-- ----------------------------
DROP TABLE IF EXISTS `schedule_charge`;
CREATE TABLE `schedule_charge`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `week` int(11) NOT NULL COMMENT '星期几',
  `start_time` time NOT NULL COMMENT '起始时间',
  `end_time` time NOT NULL COMMENT '结束时间',
  `status` int(11) NOT NULL COMMENT '状态：1 启用 0 禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` int(11) NOT NULL COMMENT '创建人id',
  `update_user` int(11) NOT NULL COMMENT '更新人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2118889475 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_charge
-- ----------------------------
INSERT INTO `schedule_charge` VALUES (1, 1, '08:00:00', '18:00:00', 1, '2024-04-03 13:28:55', '2024-04-03 13:28:58', 1, 1);
INSERT INTO `schedule_charge` VALUES (2, 2, '09:00:00', '11:30:00', 1, '2024-04-03 15:11:34', '2024-04-03 15:11:34', 1, 1);
INSERT INTO `schedule_charge` VALUES (3, 2, '08:00:00', '18:00:00', 1, '2024-04-03 15:13:39', '2024-04-03 15:13:39', 1, 1);
INSERT INTO `schedule_charge` VALUES (2118889474, 2, '08:00:00', '18:00:00', 1, '2024-04-03 20:58:51', '2024-04-03 20:58:51', 1, 1);

-- ----------------------------
-- Table structure for schema_configuration
-- ----------------------------
DROP TABLE IF EXISTS `schema_configuration`;
CREATE TABLE `schema_configuration`  (
  `DOOR_ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '柜子编号',
  `DOOR_NUMBER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '柜门编号',
  `DOOR_UNION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属柜门编号',
  `START_TIME` time NULL DEFAULT NULL COMMENT '起始时间',
  `END_TIME` time NULL DEFAULT NULL COMMENT '截止时间',
  `DOOR_STATUS` int(11) NULL DEFAULT NULL COMMENT '状态',
  `REMARK` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`DOOR_ID`, `DOOR_NUMBER`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schema_configuration
-- ----------------------------

-- ----------------------------
-- Table structure for user_information
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `USER_ID` INT NOT NULL COMMENT '用户id',
  `USER_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `USER_ACCOUNT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `USER_PASSWORD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `ADMIN_AUTORITY` int NULL DEFAULT NULL COMMENT '管理权限',
  `CLASS_ID` INT  NULL DEFAULT NULL COMMENT '班级编号',
  `DOOR_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '柜子编号',
  `DOOR_NUMBER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '柜门编号',
  `FACE_INFORMATION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `FINGER_INFORMATION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `DOOR_UNION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of user_information
-- ----------------------------
-- ----------------------------
-- Table structure for system_logs
-- ----------------------------
DROP TABLE IF EXISTS `system_logs`;
CREATE TABLE `system_logs`  (
  `log_message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '日志内容',
  `time` datetime NULL DEFAULT NULL COMMENT '时间',
  `USER_ID` INT NOT NULL COMMENT '用户id',
  INDEX `USER_ID`(`USER_ID` ASC) USING BTREE,
  CONSTRAINT `system_logs_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
