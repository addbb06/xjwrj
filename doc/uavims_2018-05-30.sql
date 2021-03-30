/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50617
 Source Host           : 127.0.0.1:3306
 Source Schema         : xjwrj

 Target Server Type    : MySQL
 Target Server Version : 50617
 File Encoding         : 65001

 Date: 30/05/2018 14:18:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device_category
-- ----------------------------
DROP TABLE IF EXISTS `device_category`;
CREATE TABLE `device_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类别名称',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除( 0未删除  1删除)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备类别' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of device_category
-- ----------------------------
INSERT INTO `device_category` VALUES (1, '4轴旋翼', 1, '2018-04-19 09:55:26', NULL, NULL, '0', NULL);
INSERT INTO `device_category` VALUES (2, '6轴旋翼', 1, '2018-04-19 09:55:46', NULL, NULL, '0', NULL);
INSERT INTO `device_category` VALUES (3, '固定翼', 1, '2018-04-19 09:56:52', NULL, NULL, '0', NULL);

-- ----------------------------
-- Table structure for device_info
-- ----------------------------
DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '设备名称',
  `device_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '设备编码',
  `device_type_id` int(11) NULL DEFAULT NULL COMMENT '设备类型id',
  `device_category_id` int(11) NULL DEFAULT NULL COMMENT '设备类别id',
  `gps_id` int(11) NULL DEFAULT NULL COMMENT ' gps设备id',
  `device_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '设备图片',
  `device_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '设备状态( 0 禁用 1 启用 )',
  `flyer_id` int(1) NULL DEFAULT NULL COMMENT '飞手id',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否删除( 0未删除  1删除)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '组织id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '设备表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of device_info
-- ----------------------------
INSERT INTO `device_info` VALUES (1, '大疆精灵Ⅲ', 'd1001', 1, 1, 1, NULL, '1', 1, 1, '2018-04-19 14:57:11', NULL, NULL, '0', '', 1);
INSERT INTO `device_info` VALUES (2, 'string', 'string', 1, 1, NULL, 'string', '0', 1, 1, '2018-05-03 18:14:10', NULL, NULL, '0', 'string', 1);
INSERT INTO `device_info` VALUES (3, 'string2', 'string2', 2, 3, NULL, 'string', '0', 1, 1, '2018-05-03 18:14:20', 1, '2018-05-04 16:17:44', '0', 'string2', 1);
INSERT INTO `device_info` VALUES (4, '11', '11', 1, 3, NULL, '226c7f20b0ca45b78830b891cc2c0cb8.png', '0', 1, 1, '2018-05-03 18:38:41', 1, '2018-05-08 13:52:58', '0', '111', 1);
INSERT INTO `device_info` VALUES (5, '22221', '2221', 2, 2, NULL, '2b6c0757c5604dd7a1557ce7805f353d.png', '0', 1, 1, '2018-05-04 09:36:48', 1, '2018-05-08 16:13:56', '0', '2222211', 1);
INSERT INTO `device_info` VALUES (6, '333', '333', 1, 1, NULL, NULL, '0', 1, 1, '2018-05-04 10:04:43', NULL, NULL, '1', '33333', 1);
INSERT INTO `device_info` VALUES (7, '22221', '2221', 2, 2, NULL, '2b6c0757c5604dd7a1557ce7805f353d.png', '0', 2, 1, '2018-05-04 09:36:48', 1, '2018-05-08 14:00:07', '0', '2222211', 2);
INSERT INTO `device_info` VALUES (8, 'string2', 'string2', 3, 3, 2, 'string', '0', 2, 1, '2018-05-03 18:14:20', 2, '2018-05-08 18:30:54', '0', 'string2', 2);

-- ----------------------------
-- Table structure for device_location
-- ----------------------------
DROP TABLE IF EXISTS `device_location`;
CREATE TABLE `device_location`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '定位时间',
  `position_time` datetime(0) NULL DEFAULT NULL,
  `lat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `lng` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `speed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '速度',
  `course` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方向',
  `is_stop` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否禁止',
  `device_id` int(11) NULL DEFAULT NULL COMMENT '无人机设备id',
  `gps_id` int(11) NULL DEFAULT NULL COMMENT 'gps设备id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of device_location
-- ----------------------------
INSERT INTO `device_location` VALUES (40, '2018-04-19 15:06:44', '18.42423', '109.86971', '0.00', '0', '1', 1, NULL);

-- ----------------------------
-- Table structure for device_part_info
-- ----------------------------
DROP TABLE IF EXISTS `device_part_info`;
CREATE TABLE `device_part_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NULL DEFAULT NULL COMMENT '设备id',
  `parts_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '配件名称',
  `production_date` datetime(0) NULL DEFAULT NULL COMMENT '出厂日期',
  `life_time` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '使用寿命',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否删除( 0未删除  1删除)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '组织id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '设备配件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of device_part_info
-- ----------------------------
INSERT INTO `device_part_info` VALUES (1, 1, '机架', '2018-04-19 14:58:04', '12', 1, '2018-04-19 14:58:09', NULL, NULL, '0', NULL, 1);
INSERT INTO `device_part_info` VALUES (2, 1, '电池', '2018-04-19 14:58:38', '12', 1, '2018-04-19 14:58:43', NULL, NULL, '0', NULL, 1);
INSERT INTO `device_part_info` VALUES (3, 7, '1111', '2018-05-04 13:35:34', '3', 2, '2018-05-04 18:26:19', NULL, NULL, '0', 'xxx', 2);
INSERT INTO `device_part_info` VALUES (4, 5, '1111111111', '2018-05-17 00:00:00', '111111', NULL, NULL, 1, '2018-05-08 16:10:18', '0', '1111111111111111', 1);
INSERT INTO `device_part_info` VALUES (5, 5, '222222222222', '2018-05-18 00:00:00', '2222222222', NULL, NULL, 1, '2018-05-08 16:10:18', '0', '2222222', 1);
INSERT INTO `device_part_info` VALUES (6, 5, '1111111', '2018-05-09 00:00:00', '111111111', NULL, NULL, 1, '2018-05-08 16:13:56', '0', '1', 1);
INSERT INTO `device_part_info` VALUES (7, 8, '测温hi', '2018-05-08 00:00:00', '30', 2, '2018-05-08 18:30:54', NULL, NULL, '0', NULL, 2);

-- ----------------------------
-- Table structure for device_type
-- ----------------------------
DROP TABLE IF EXISTS `device_type`;
CREATE TABLE `device_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_type_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '设备类型名称',
  `device_type_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '设备类型编码',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否删除( 0未删除  1删除)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '组织id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '设备类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of device_type
-- ----------------------------
INSERT INTO `device_type` VALUES (1, '大疆', 'dt1001', 1, '2018-04-19 14:55:44', NULL, NULL, '0', NULL, 1);
INSERT INTO `device_type` VALUES (2, '小米', 'dt1002', 1, '2018-04-19 14:56:33', NULL, NULL, '0', NULL, 1);
INSERT INTO `device_type` VALUES (3, '天蝎', 'NSY461337', 2, '2018-05-04 15:51:50', 2, '2018-05-04 16:21:05', '0', 'xxx2', 2);
INSERT INTO `device_type` VALUES (4, '天翼', 'TY1684316', 2, '2018-05-04 15:55:01', NULL, NULL, '0', '天翼', 2);
INSERT INTO `device_type` VALUES (5, 'xx', 'xxx', 2, '2018-05-04 16:18:26', NULL, NULL, '1', 'xx', 2);

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文件相对路径',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文件md5',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文件状态',
  `classified` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL COMMENT '上传人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '修改人id',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '文件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES (1, '73954b5d896c4a43bedd10f1ccd263d3.png', 'C://upload//', 'image/jpeg', '73954b5d896c4a43bedd10f1ccd263d3', 1255049, NULL, NULL, 1, '2018-04-03 11:54:02', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (2, '5631c530eca94e708aee0ecce05b6078.png', 'C://upload//', 'image/jpeg', '5631c530eca94e708aee0ecce05b6078', 1268815, NULL, NULL, 1, '2018-04-03 11:55:59', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (3, '6e29dfb791fa45888783ae8369a2cf45.png', 'C://upload//', 'image/jpeg', '6e29dfb791fa45888783ae8369a2cf45', 184269, NULL, NULL, 1, '2018-04-03 11:58:12', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (4, 'bb12c1d8db7c430c9dda5d655734f06b.png', 'C://upload//', 'image/jpeg', 'bb12c1d8db7c430c9dda5d655734f06b', 1649490, NULL, NULL, 1, '2018-04-03 11:58:51', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (5, '9101049c16d74c57a09083fcf7dd5364.png', 'C://upload//', 'image/jpeg', '9101049c16d74c57a09083fcf7dd5364', 1255049, NULL, NULL, 1, '2018-04-03 12:01:33', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (6, '149177e82ed447088e40ba60f7128c3c.png', 'C://upload//', 'image/jpeg', '149177e82ed447088e40ba60f7128c3c', 1466723, NULL, NULL, 2, '2018-05-04 18:12:40', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (7, '30966690a5d74bbe83c18dd92e642b3d.png', 'C://upload//', 'image/png', '30966690a5d74bbe83c18dd92e642b3d', 117720, NULL, NULL, 1, '2018-05-04 18:12:51', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (8, 'd03d6121a940423faf759761cf97e203.png', 'C://upload//', 'image/png', 'd03d6121a940423faf759761cf97e203', 117720, NULL, NULL, 1, '2018-05-04 18:26:46', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (9, '468a18179fe348b48da210bca576681a.png', 'C://upload//', 'image/png', '468a18179fe348b48da210bca576681a', 117720, NULL, NULL, 1, '2018-05-04 18:28:11', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (10, 'd08785fa7b71485889a905e9db366872.png', 'C://upload//', 'image/png', 'd08785fa7b71485889a905e9db366872', 117720, NULL, NULL, 1, '2018-05-07 18:07:20', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (11, '0e8b4f4f6da04ee2883b01d38142dada.png', 'C://upload//', 'image/png', '0e8b4f4f6da04ee2883b01d38142dada', 354474, NULL, NULL, 1, '2018-05-07 18:11:36', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (12, '6cc8724f71fa41a89010c632632ef58d.png', 'C://upload//', 'image/png', '6cc8724f71fa41a89010c632632ef58d', 117720, NULL, NULL, 1, '2018-05-07 18:13:39', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (13, '45d9968f03c0467e8cfdbd714c76bf3b.png', 'C://upload//', 'image/png', '45d9968f03c0467e8cfdbd714c76bf3b', 354474, NULL, NULL, 1, '2018-05-07 18:14:16', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (14, 'b88a3d5139df4f3e9090e6ba882fa08b.png', 'C://upload//', 'image/png', 'b88a3d5139df4f3e9090e6ba882fa08b', 117720, NULL, NULL, 1, '2018-05-07 18:14:49', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (15, 'dbfa50d4e9c643b084ced5da481c882f.png', 'C://upload//', 'image/png', 'dbfa50d4e9c643b084ced5da481c882f', 354474, NULL, NULL, 1, '2018-05-07 18:16:32', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (16, '6e57bb14a10541a091a2575c07649ee4.png', 'C://upload//', 'image/png', '6e57bb14a10541a091a2575c07649ee4', 117720, NULL, NULL, 1, '2018-05-07 18:16:39', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (17, 'afe31cfa014c4c078a5e0a8f7881c9c4.png', 'C://upload//', 'image/png', 'afe31cfa014c4c078a5e0a8f7881c9c4', 117720, NULL, NULL, 1, '2018-05-07 18:21:07', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (18, '3d04fa759d1c462a83fff3fd3654c1c4.png', 'C://upload//', 'image/png', '3d04fa759d1c462a83fff3fd3654c1c4', 117720, NULL, NULL, 1, '2018-05-07 18:24:28', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (19, 'a23012cc32254be38d524a85342e46ad.png', 'C://upload//', 'image/png', 'a23012cc32254be38d524a85342e46ad', 207073, NULL, NULL, 1, '2018-05-08 09:21:13', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (20, '3709c04696c94cf39fac885dd3070f8a.png', 'C://upload//', 'image/png', '3709c04696c94cf39fac885dd3070f8a', 207073, NULL, NULL, 1, '2018-05-08 10:05:31', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (21, 'fe89dd852e8b4acdb736d8355d904c6e.png', 'C://upload//', 'image/png', 'fe89dd852e8b4acdb736d8355d904c6e', 207073, NULL, NULL, 1, '2018-05-08 10:06:41', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (22, '135c75fe921a4059a6061acd94cd48cb.png', 'C://upload//', 'image/png', '135c75fe921a4059a6061acd94cd48cb', 207073, NULL, NULL, 1, '2018-05-08 10:19:20', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (23, 'f4d47cc719d54f44866755f8385d8fea.png', 'C://upload//', 'image/jpeg', 'f4d47cc719d54f44866755f8385d8fea', 1004727, NULL, NULL, 2, '2018-05-08 11:54:42', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (24, 'd99f74deede94ca9ad295db351417744.png', 'C://upload//', 'image/jpeg', 'd99f74deede94ca9ad295db351417744', 367364, NULL, NULL, 2, '2018-05-08 11:58:58', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (25, '118c971a7be648d0a364d56be8415204.png', 'C://upload//', 'image/jpeg', '118c971a7be648d0a364d56be8415204', 367364, NULL, NULL, 2, '2018-05-08 11:59:44', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (26, '9deb3f58ac224715b9f0476b93e80552.png', 'C://upload//', 'image/jpeg', '9deb3f58ac224715b9f0476b93e80552', 396131, NULL, NULL, 2, '2018-05-08 13:40:40', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (27, '2cce24a224d541df927d7523296b0468.png', 'C://upload//', 'image/jpeg', '2cce24a224d541df927d7523296b0468', 594260, NULL, NULL, 2, '2018-05-08 13:41:29', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (28, '226c7f20b0ca45b78830b891cc2c0cb8.png', 'C://upload//', 'image/png', '226c7f20b0ca45b78830b891cc2c0cb8', 207073, NULL, NULL, 1, '2018-05-08 13:52:54', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (29, 'dce6dfbb8b2f44879cc5c640cb5b8e61.png', 'C://upload//', 'image/png', 'dce6dfbb8b2f44879cc5c640cb5b8e61', 207073, NULL, NULL, 1, '2018-05-08 13:55:59', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (30, '6ae433ba318a49ae8e15537f7dfa5251.png', 'C://upload//', 'image/png', '6ae433ba318a49ae8e15537f7dfa5251', 207073, NULL, NULL, 1, '2018-05-08 13:59:45', NULL, NULL, '0');
INSERT INTO `file_info` VALUES (31, '2b6c0757c5604dd7a1557ce7805f353d.png', 'C://upload//', 'image/png', '2b6c0757c5604dd7a1557ce7805f353d', 259291, NULL, NULL, 1, '2018-05-08 14:00:04', NULL, NULL, '0');

-- ----------------------------
-- Table structure for fly_line
-- ----------------------------
DROP TABLE IF EXISTS `fly_line`;
CREATE TABLE `fly_line`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `device_id` int(11) NULL DEFAULT NULL COMMENT '实际使用设备ID',
  `task_id` int(11) NULL DEFAULT NULL COMMENT '关联任务ID',
  `flyer_id` int(11) NULL DEFAULT NULL COMMENT '关联飞手ID',
  `lng_arry` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度数组',
  `lat_arry` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度数组',
  `speed_arry` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '速度数组',
  `course_arry` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方向数组',
  `mileage` decimal(10, 2) NULL DEFAULT NULL COMMENT '里程数',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '实际开始时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '实际结束时间',
  `fly_time` decimal(10, 2) NULL DEFAULT NULL COMMENT '飞行时长',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '飞行轨迹' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for flyer
-- ----------------------------
DROP TABLE IF EXISTS `flyer`;
CREATE TABLE `flyer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话（账户）',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别(1、男 2、女)',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `certificate_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件类型',
  `certificate_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件编码',
  `certificate_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件照片',
  `issue_date` datetime(0) NULL DEFAULT NULL COMMENT '签发日期',
  `expiry_date` datetime(0) NULL DEFAULT NULL COMMENT '有效期',
  `level` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除( 0未删除  1删除)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '组织id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '飞手档案' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of flyer
-- ----------------------------
INSERT INTO `flyer` VALUES (1, '15566667777', '123456', '刘小川', '1', '1995-01-19 00:00:00', '1', 'q8785121200', 'dce6dfbb8b2f44879cc5c640cb5b8e61.png', '2008-01-01 00:00:00', '2020-01-19 00:00:00', '1', 1, '2018-04-19 15:00:32', 1, '2018-05-08 13:56:01', '0', NULL, 1);
INSERT INTO `flyer` VALUES (2, '15566668888', '123456', '张国立', '1', '2018-05-01 00:00:00', '2', 'SDFS3278S453FSDFS', NULL, NULL, NULL, NULL, 2, '2018-05-07 18:28:00', 2, '2018-05-08 11:43:48', '0', '123456', 2);
INSERT INTO `flyer` VALUES (3, '13344445555', '435435312', '李雪健', '1', '2018-05-08 00:00:00', '5', '34d645asdasa', '9deb3f58ac224715b9f0476b93e80552.png', NULL, NULL, NULL, 2, '2018-05-08 11:46:06', 2, '2018-05-08 13:40:42', '0', 'edszsd32', 2);

-- ----------------------------
-- Table structure for gps_device
-- ----------------------------
DROP TABLE IF EXISTS `gps_device`;
CREATE TABLE `gps_device`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gps_device_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'gps设备名称',
  `gps_device_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'gps设备编码',
  `gps_device_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'gps设备id',
  `gps_device_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'gps设备状态(0 未绑定  1已绑定）',
  `model` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `send_command` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time_zone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `warn_str` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备key',
  `sim_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sim卡号',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除( 0未删除  1删除)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '组织id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'gps设备' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gps_device
-- ----------------------------
INSERT INTO `gps_device` VALUES (1, 'GX803-08662', '3000008662', '50110', '1', '183', '0-0-0-0-0', 'China Standard Time', '1-1-0-1-1-1-1-1-1-1-1', '7DU2DJFDR8321', '11247845485', 1, '2018-04-19 15:03:42', NULL, NULL, '0', NULL, 1);
INSERT INTO `gps_device` VALUES (2, 'GX803-08662', '3000008662', '50110', '1', '183', '0-0-0-0-0', 'China Standard Time', '1-1-0-1-1-1-1-1-1-1-1', '7DU2DJFDR8321', '11247845485', 1, '2018-04-19 15:03:42', NULL, NULL, '0', NULL, 2);

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(11) NOT NULL DEFAULT 0 COMMENT '上级',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '企业名称',
  `license` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '营业执照注册号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `is_delete` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否删除( 0未删除  1删除)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '组织结构（角色表）' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES (1, 0, '国网天津供电公司', '468431231F6834WS', '天津市', '张贤胜', '18899990000', '0', 1, '2017-12-13 19:28:26', NULL, NULL);
INSERT INTO `organization` VALUES (2, 1, '国网天津电科院', '4351316484648446S', '天津市', '钟镇涛', '17788889999', '0', 1, '2018-03-26 17:39:48', NULL, NULL);
INSERT INTO `organization` VALUES (4, 1, '国网天津检修公司', '16355GW', '无', '张雷火', '无', '0', 4, '2018-04-02 11:56:20', 1, '2018-04-02 14:52:24');

-- ----------------------------
-- Table structure for parts_info
-- ----------------------------
DROP TABLE IF EXISTS `parts_info`;
CREATE TABLE `parts_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parts_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '配件名称',
  `parts_amount` int(100) NULL DEFAULT NULL COMMENT '配件数量',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否删除( 0未删除  1删除)',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '所属企业ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '配件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of parts_info
-- ----------------------------
INSERT INTO `parts_info` VALUES (1, '2222', 123, NULL, 1, '2018-04-20 15:07:50', 1, '2018-05-07 17:18:04', '0', 1);
INSERT INTO `parts_info` VALUES (2, '123', 12, 'lll', 2, '2018-05-07 13:53:52', NULL, NULL, '0', 2);
INSERT INTO `parts_info` VALUES (3, '111', 10, '1', 2, '2018-05-07 13:56:34', 2, '2018-05-07 16:46:02', '0', 2);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `opt_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  `opt_user_id` int(11) NULL DEFAULT NULL,
  `opt_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `msg_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '消息标题',
  `msg_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '消息内容',
  `sender_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '消息发送人id',
  `receiver_id` int(11) UNSIGNED NOT NULL COMMENT '接收人id',
  `msg_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '消息状态,未读/已读',
  `create_date` timestamp(0) NULL DEFAULT NULL COMMENT '发送时间',
  `update_date` timestamp(0) NULL DEFAULT NULL COMMENT '读取时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息内容表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_message
-- ----------------------------
INSERT INTO `sys_message` VALUES (1, '你好', '打个招呼', 2, 1, '1', '2018-03-27 15:32:16', '2018-03-27 16:04:06');
INSERT INTO `sys_message` VALUES (2, NULL, '打个招呼', 1, 2, '1', '2018-03-27 18:00:38', '2018-03-27 18:04:46');
INSERT INTO `sys_message` VALUES (3, NULL, '走开~', 2, 1, '1', '2018-03-27 18:06:56', '2018-03-27 18:09:33');
INSERT INTO `sys_message` VALUES (4, NULL, '太不友好了', 1, 2, '1', '2018-03-27 18:09:41', '2018-03-27 18:36:13');
INSERT INTO `sys_message` VALUES (5, NULL, '不好', 2, 1, '1', '2018-03-27 18:39:09', '2018-03-28 17:56:08');
INSERT INTO `sys_message` VALUES (6, NULL, '不好', 2, 1, '1', '2018-03-27 18:39:24', '2018-03-28 17:56:18');
INSERT INTO `sys_message` VALUES (7, NULL, '大大方方的地方', 2, 1, '1', '2018-03-27 18:40:02', '2018-03-28 17:56:20');
INSERT INTO `sys_message` VALUES (8, NULL, '111', 1, 2, '0', '2018-03-28 17:56:11', NULL);
INSERT INTO `sys_message` VALUES (9, 'hello', '弄啥嘞', 1, 5, '0', '2018-04-02 16:30:32', NULL);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `pid` int(10) NOT NULL DEFAULT 0 COMMENT '父节点',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '权限名称',
  `code` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '权限code',
  `order_num` smallint(2) NULL DEFAULT NULL COMMENT '权限顺序',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `menu` tinyint(1) NULL DEFAULT 0 COMMENT '是否为菜单.0否，1是',
  `icon` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `remark` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `is_delete` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '是否启用',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(10) NULL DEFAULT NULL COMMENT '创建者',
  `update_by` int(10) NULL DEFAULT NULL COMMENT '修改者',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, '无人机管理', 'device:all', 1, NULL, 1, NULL, '无人机管理', '0', '2017-09-14 11:35:16', 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (2, 1, '无人机信息', NULL, 1, NULL, 1, NULL, '无人机信息', '0', '2017-09-14 11:30:30', 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (3, 1, '无人机类型管理', NULL, 2, NULL, 1, NULL, '无人机类型管理', '0', '2017-09-14 11:33:55', 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (4, 1, '无人机配件管理', NULL, 3, NULL, 1, NULL, '无人机配件管理', '0', '2017-09-14 11:33:58', 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (5, 1, '操控手档案', NULL, 4, NULL, 1, NULL, '操控手档案', '0', '2017-09-14 11:34:01', 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (6, 0, '任务管理', 'task:all', 2, NULL, 1, NULL, '任务管理', '0', '2017-09-14 11:34:06', 0, NULL, NULL);
INSERT INTO `sys_permission` VALUES (7, 6, '飞行任务', NULL, 1, NULL, 1, NULL, '飞行任务', '0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (8, 6, '飞行记录', NULL, 2, NULL, 1, NULL, '飞行记录', '0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (9, 0, '定位管理', 'location:all', 3, NULL, 1, NULL, '定位管理', '0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (10, 9, '实时定位', NULL, 1, NULL, 1, NULL, '实时定位', '0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (11, 9, '历史轨迹', NULL, 2, NULL, 1, NULL, '历史轨迹', '0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (12, 0, 'GPS管理', NULL, 4, NULL, 1, NULL, 'GPS管理', '0', NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (13, 0, '首页', NULL, 0, NULL, 1, NULL, '首页', '0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `permission_id` int(10) NOT NULL COMMENT '权限id',
  `role_id` int(10) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '角色权限关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 2);
INSERT INTO `sys_role_permission` VALUES (2, 6, 2);
INSERT INTO `sys_role_permission` VALUES (3, 12, 2);
INSERT INTO `sys_role_permission` VALUES (4, 13, 2);
INSERT INTO `sys_role_permission` VALUES (5, 2, 2);
INSERT INTO `sys_role_permission` VALUES (6, 3, 2);
INSERT INTO `sys_role_permission` VALUES (7, 4, 2);
INSERT INTO `sys_role_permission` VALUES (8, 7, 2);
INSERT INTO `sys_role_permission` VALUES (9, 8, 2);
INSERT INTO `sys_role_permission` VALUES (11, 2, 4);
INSERT INTO `sys_role_permission` VALUES (12, 3, 4);
INSERT INTO `sys_role_permission` VALUES (13, 4, 4);
INSERT INTO `sys_role_permission` VALUES (14, 12, 4);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '账户类型，平台/公司',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '所属组织id',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `truename` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `head_img_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '头像图片名称',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '性别(1、男 2、女)',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '系统用户状态(1、正常，2锁定)',
  `is_delete` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT '0' COMMENT '是否删除( 0未删除  1删除)',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'SYSTEM_ADMIN', 1, 'admin', 'admin', '管理员', '9101049c16d74c57a09083fcf7dd5364.png', '15566667777', 'wurenjixj0683@tj.sgcc.com.cn', '1', '1', '0', 1, '2017-12-13 19:28:26', 1, '2018-04-03 12:01:46');
INSERT INTO `sys_user` VALUES (2, 'COMPANY_ADMIN', 2, 'test', '123456', '测试员', NULL, '15566667777', 'test@126.com', '1', '1', '0', 1, '2017-12-13 19:28:26', 2, '2018-05-10 16:25:35');
INSERT INTO `sys_user` VALUES (4, 'COMPANY_ADMIN', 4, 'leihuo', '123456', '张雷威', NULL, '17788889999', 'zhanglw@qq.com', '1', '1', '0', NULL, '2018-04-02 11:56:20', 1, '2018-04-02 14:24:17');
INSERT INTO `sys_user` VALUES (5, 'COMPANY_ADMIN', 2, 'zhangyide', NULL, '张翼德', NULL, '6792412112', '3423342', '1', '1', '0', 1, '2018-04-02 15:38:59', NULL, NULL);

-- ----------------------------
-- Table structure for task_info
-- ----------------------------
DROP TABLE IF EXISTS `task_info`;
CREATE TABLE `task_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `org_id` int(11) NULL DEFAULT NULL COMMENT '所属公司id',
  `task_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '任务编号',
  `publisher_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '发布者id',
  `executor_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '执行者id',
  `execute_device_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '执行设备id',
  `task_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '任务标题',
  `task_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '任务内容',
  `task_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '任务地点',
  `task_start_time` timestamp(0) NULL DEFAULT NULL COMMENT '开始时间',
  `task_end_time` timestamp(0) NULL DEFAULT NULL COMMENT '截止时间',
  `task_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '任务状态',
  `task_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '任务结果报告',
  `task_report_time` timestamp(0) NULL DEFAULT NULL COMMENT '任务报告时间',
  `is_delete` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否删除',
  `create_by` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '创建人',
  `create_date` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '修改人',
  `update_date` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '任务信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of task_info
-- ----------------------------
INSERT INTO `task_info` VALUES (1, 2, '1', 1, 3, 1, '“太空杯”无人机竞赛', '由绿色联盟俱乐部组织的无人机操作飞行大赛在武汉东湖举行', '以色列', '2018-04-08 00:00:00', '2018-04-11 00:00:00', '0', NULL, NULL, '0', 1, '2018-04-01 10:13:12', 2, '2018-05-14 18:08:31');
INSERT INTO `task_info` VALUES (3, 2, '1', 1, 2, 1, '无人机', '阿斯顿打', '阿拉伯', '2018-04-10 00:00:00', '2018-04-13 00:00:00', '0', NULL, NULL, '0', 1, '2018-04-03 18:23:27', 1, '2018-04-08 15:55:01');
INSERT INTO `task_info` VALUES (4, 2, '1', 1, 3, 11, '大风过后', '大大大大大', '江南', '2018-04-04 00:00:00', '2018-04-07 00:00:00', '0', NULL, NULL, '1', 1, '2018-04-03 19:22:10', 1, '2018-04-09 10:30:19');
INSERT INTO `task_info` VALUES (5, 1, 'task-MVAYOcbw', 1, 4, NULL, 'test', 'content', 'test', '2018-04-20 11:03:29', '2018-04-20 11:03:29', '0', NULL, NULL, '0', 1, '2018-04-20 11:03:30', 1, '2018-04-20 11:38:43');
INSERT INTO `task_info` VALUES (6, 1, 'task-RsDUbZra', 1, 5, NULL, 'string', 'string', 'string', '2018-04-20 03:26:50', '2018-04-20 03:26:50', '0', NULL, NULL, '0', 1, '2018-04-20 12:05:18', NULL, NULL);
INSERT INTO `task_info` VALUES (7, 1, 'task-aM6PKeSs', 1, 5, NULL, 'string', 'string', 'string', '2018-04-20 03:26:50', '2018-04-20 03:26:50', '0', NULL, NULL, '0', 1, '2018-04-20 12:05:40', NULL, NULL);
INSERT INTO `task_info` VALUES (8, 2, 'task-YezALkLE', 2, 2, NULL, '测试task', 'sifilwafjp', 'hongkong', '2018-05-15 00:00:00', '2018-05-25 00:00:00', '0', NULL, NULL, '0', 2, '2018-05-15 13:48:12', NULL, NULL);
INSERT INTO `task_info` VALUES (9, 2, 'task-0kkhQjQl', 2, 2, NULL, 'necessary', 'are you ok?', '伊泽布尔', '2018-05-18 00:00:00', '2018-05-20 00:00:00', '0', NULL, NULL, '0', 2, '2018-05-15 13:50:14', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
