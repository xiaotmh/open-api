/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : localhost:3306
 Source Schema         : openapi-db

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 29/09/2020 18:36:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '管理员账号',
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '管理员密码',
  `nickname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '管理员电子邮箱',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近登录时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '你大爷的', 'admin@admin.com', '2020-09-02 19:47:38', '2020-09-01 19:47:43');

-- ----------------------------
-- Table structure for tb_application
-- ----------------------------
DROP TABLE IF EXISTS `tb_application`;
CREATE TABLE `tb_application`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `corp_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联企业名称,冗余减少查询',
  `app_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用名称',
  `app_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用唯一标示 KEY',
  `app_secret` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用签名秘钥',
  `redirect_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用回调用 URL',
  `limit_count` int(11) NULL DEFAULT NULL COMMENT '免费接口日调用限制次数',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用介绍',
  `cus_id` int(11) NULL DEFAULT NULL COMMENT '关联客户 id',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_application
-- ----------------------------
INSERT INTO `tb_application` VALUES (1, '华为', '鸿蒙', '1', '啊啊', '百度', 22, '你大爷的', 1, 1);
INSERT INTO `tb_application` VALUES (3, '你大爷', '你大爷', '123', '123', '123', 123, '你大爷', 2, 0);
INSERT INTO `tb_application` VALUES (4, '阿里巴巴', '淘宝', '1233213124123', 'afdssdsfsd324', 'fsdffsddfs', 22, 'dsfafsdfsafds', 13, 1);
INSERT INTO `tb_application` VALUES (5, '你大爷', '你大爷', '你大爷', '你大爷', '你大爷', 12, '你大爷', 40, 1);
INSERT INTO `tb_application` VALUES (6, '阿达达', '阿达达', '阿达达', '阿达达', '阿达达', 213, '阿达达', 38, 1);
INSERT INTO `tb_application` VALUES (8, '喵喵喵', '喵喵喵喵喵喵', '喵喵喵喵喵喵', '喵喵喵喵喵喵喵喵喵', '喵喵喵喵喵喵喵喵喵喵喵喵', 22, '喵喵喵喵喵喵喵喵喵喵喵喵', 2, 0);
INSERT INTO `tb_application` VALUES (9, '大叔大婶', '大撒说的都是', '水电费', '十多个', '文人趣味大神撒旦阿斯顿', 321, '爱上打法的书法大赛', 14, 1);
INSERT INTO `tb_application` VALUES (10, '的复合物', '活人', '吃v不行', '574法国货', '风格化的涂鸦', 3, 'oui哟女重新', 14, 1);
INSERT INTO `tb_application` VALUES (12, '从bv线跑', '说的佛牌', '许昌', 'V型出门', 'v不能进', 3, '发多少', 14, 1);
INSERT INTO `tb_application` VALUES (15, 'wet', 'afsd', 'gsad', 'dsf', 'dfs', 3, 'fsda', 1, 1);
INSERT INTO `tb_application` VALUES (16, 'apple', 'fdsfsd', 'fsdfds', 'fsd', 'fdsfsd', 4, 'dsffds', 2, 1);
INSERT INTO `tb_application` VALUES (17, 'apple', 'rgfd', 'dfg', 'dfg', 'gfd', 4, 'dfg', 11, 1);
INSERT INTO `tb_application` VALUES (18, 'apple', '鸿蒙', 'sad', 'asd', 'ads', 2, 'asd', 2, 1);

-- ----------------------------
-- Table structure for tb_customer
-- ----------------------------
DROP TABLE IF EXISTS `tb_customer`;
CREATE TABLE `tb_customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司名称',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `money` int(11) NULL DEFAULT NULL COMMENT '余额(毫)',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态 1 正常,0 停用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_customer
-- ----------------------------
INSERT INTO `tb_customer` VALUES (1, '大爷', '123', '阿斯顿1', '发生的', 22, 1);
INSERT INTO `tb_customer` VALUES (2, 'jack', '123456', 'apple', '你大爷的', 1234, 1);
INSERT INTO `tb_customer` VALUES (10, 'bde7d', 'bde7f4', 'apple', 'asd', 1000, 0);
INSERT INTO `tb_customer` VALUES (11, 'd46c7', 'd46c9c', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (12, 'e9779', 'e977be', 'apple', 'asd', 1000, 0);
INSERT INTO `tb_customer` VALUES (13, 'ffc4b', 'ffc4da', 'apple', 'asd', 1000, 0);
INSERT INTO `tb_customer` VALUES (14, '16d6f', '16d718', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (15, '2d27b', '2d27dc', 'apple', 'asd', 1000, 0);
INSERT INTO `tb_customer` VALUES (16, '43db2', '43db51', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (17, '5af54', '5af56e', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (18, '73aaa', '73aacb', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (19, '8aaec', '8aaee9', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (20, 'a492f', 'a49365', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (21, 'bc779', 'bc77ba', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (22, 'd559c', 'd55a18', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (23, 'eb8a9', 'eb8ac2', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (24, '02c74', '02c76b', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (25, '1a8a8', '1a8aae', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (26, '34812', '348150', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (27, '49c8a', '49c8d0', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (28, '60db4', '60db6e', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (29, '78301', '78303a', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (30, '911ed', '911eff', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (31, 'a9843', 'a98458', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (32, 'c23f5', 'c23f8a', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (33, 'd6d9f', 'd6da1f', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (34, 'eeaa9', 'eeaac2', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (35, '07e15', '07e181', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (36, '1fab9', '1fabc2', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (37, '37747', '3774a1', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (39, '6573e', '65740a', 'apple', 'asd', 1000, 1);
INSERT INTO `tb_customer` VALUES (40, '阿斯达斯', '123', '492684787@qq.com', '的撒啊的事', 123, 1);
INSERT INTO `tb_customer` VALUES (49, 'das', 'asd', 'ads', 'ads', 32, 0);
INSERT INTO `tb_customer` VALUES (50, 'das', 'dsa', 'cxz', 'fsd', 3, 0);

-- ----------------------------
-- Table structure for tb_parameter
-- ----------------------------
DROP TABLE IF EXISTS `tb_parameter`;
CREATE TABLE `tb_parameter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数名',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数介绍',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态 1 启用,0 禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_parameter
-- ----------------------------
INSERT INTO `tb_parameter` VALUES (1, 'test', '你大爷的大妈的', 1);
INSERT INTO `tb_parameter` VALUES (3, '喵喵喵', '喵喵喵喵喵喵喵喵喵', 0);
INSERT INTO `tb_parameter` VALUES (4, '秒杀', '秒杀秒杀秒杀秒杀秒杀', 1);
INSERT INTO `tb_parameter` VALUES (6, 'dfs', 'sdf', 0);

-- ----------------------------
-- Table structure for tb_recharge
-- ----------------------------
DROP TABLE IF EXISTS `tb_recharge`;
CREATE TABLE `tb_recharge`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cus_id` int(11) NULL DEFAULT NULL COMMENT '客户 id',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建订单时间',
  `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '充值金额',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态,0未支付,1 已支付,2 已取消',
  `payment_type` int(11) NULL DEFAULT NULL COMMENT '支付类型 0 银联,1 微信,2支付宝',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_recharge
-- ----------------------------
INSERT INTO `tb_recharge` VALUES (7, 1, '2020-09-28 04:00:00', '2020-09-29 14:08:34', 44.00, 1, 2);
INSERT INTO `tb_recharge` VALUES (8, 1, '2020-09-19 04:00:00', '2020-09-28 20:24:24', 3531.00, 1, 1);
INSERT INTO `tb_recharge` VALUES (9, 1, '2020-09-28 20:21:45', '2020-09-28 20:21:45', 41.00, 1, 0);
INSERT INTO `tb_recharge` VALUES (10, 1, '2020-09-28 20:22:14', '2020-09-28 20:22:14', 234.00, 1, 0);
INSERT INTO `tb_recharge` VALUES (11, 1, '2020-09-28 20:23:08', '2020-09-28 20:23:08', 24.00, 0, 2);
INSERT INTO `tb_recharge` VALUES (12, 10, '2020-09-28 20:23:31', '2020-09-28 20:23:31', 88.00, 0, 1);
INSERT INTO `tb_recharge` VALUES (13, 24, '2020-09-28 20:23:37', '2020-09-29 14:07:13', 9.00, 0, 2);
INSERT INTO `tb_recharge` VALUES (14, 11, '2020-09-29 14:06:55', '2020-09-29 14:07:02', 12.00, 1, 2);
INSERT INTO `tb_recharge` VALUES (15, 1, '2020-09-29 14:08:43', '2020-09-29 14:08:43', 123.00, 1, 0);
INSERT INTO `tb_recharge` VALUES (16, 1, '2020-09-29 15:07:28', '2020-09-29 15:07:28', 534.00, 1, 0);

-- ----------------------------
-- Table structure for tb_route
-- ----------------------------
DROP TABLE IF EXISTS `tb_route`;
CREATE TABLE `tb_route`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gateway_api_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由名称标识',
  `inside_api_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务接口地址',
  `service_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍信息',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态 1 有效,0 无效',
  `idempotents` int(11) NULL DEFAULT NULL COMMENT '幂等性 1 幂等 0 非幂等',
  `needfee` int(11) NULL DEFAULT NULL COMMENT '是否收费 1 收费 0 免费',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_route
-- ----------------------------
INSERT INTO `tb_route` VALUES (1, 'test', 'sadsadasd', '1', 'dsfdsffsd', 0, 0, 1);
INSERT INTO `tb_route` VALUES (2, 'asd', 'werew', 'sdffsdfdsfsdsfd', 'cxvcxvcxvvcx', 1, 1, 0);
INSERT INTO `tb_route` VALUES (7, 'fds', 'fds', 'dfs', 'sdf', 1, 1, 1);

-- ----------------------------
-- Table structure for tb_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_token`;
CREATE TABLE `tb_token`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '客户 id',
  `access_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token 内容',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_token
-- ----------------------------
INSERT INTO `tb_token` VALUES (9, 2, '42d6081650974c68ba3366412a3c2297', '2020-09-09 20:08:08', '2020-09-30 20:08:08');
INSERT INTO `tb_token` VALUES (10, 1, '6a15df53399145078d2f2c923b4ffbbd', '2013-09-03 20:08:08', '2027-09-29 20:08:08');
INSERT INTO `tb_token` VALUES (11, 1, '8f98679b9bd84aca83b26d298f7705c3', '2018-08-20 20:08:08', '2018-08-20 20:08:08');
INSERT INTO `tb_token` VALUES (12, 11, 'ff6e32d6fb40423cbccafc9d93990279', '2020-09-17 20:06:08', '2020-09-02 13:07:08');

SET FOREIGN_KEY_CHECKS = 1;
