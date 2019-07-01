/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : beerate_web

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-07-01 10:59:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_right
-- ----------------------------
DROP TABLE IF EXISTS `t_right`;
CREATE TABLE `t_right` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime(6) DEFAULT NULL,
  `action` varchar(255) NOT NULL DEFAULT '' COMMENT '权限地址',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '权限描述',
  `rightColumn` varchar(30) NOT NULL DEFAULT '' COMMENT '权限描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_right
-- ----------------------------
INSERT INTO `t_right` VALUES ('1', '2019-06-26 13:57:34', null, '/admin/main.html', '数据展示', 'MAIN');
INSERT INTO `t_right` VALUES ('2', '2019-06-26 13:58:23', null, '/admin/user/list.html', '平台会员', 'USER');
INSERT INTO `t_right` VALUES ('3', '2019-06-26 14:00:37', null, '/admin/user/detail.html', '会员资料', 'USER');
INSERT INTO `t_right` VALUES ('4', '2019-06-26 14:01:33', null, '/admin/user/updateUserBusiness', '会员名片审核', 'USER');
INSERT INTO `t_right` VALUES ('5', '2019-06-26 14:02:18', null, '/admin/item/loan/list.html', '项目融资', 'ITEM');
INSERT INTO `t_right` VALUES ('6', '2019-06-26 14:04:43', null, '/admin/item/stocktransfer/list.html', '老股转让', 'ITEM');
INSERT INTO `t_right` VALUES ('7', '2019-06-26 14:04:43', null, '/admin/item/preipo/list.html', 'PRE-IPO', 'ITEM');
INSERT INTO `t_right` VALUES ('8', '2019-06-26 14:04:59', null, '/admin/item/blocktrade/list.html', '大宗交易', 'ITEM');
INSERT INTO `t_right` VALUES ('9', '2019-06-26 14:05:03', null, '/admin/item/stockpledge/list.html', '股票质押', 'ITEM');
INSERT INTO `t_right` VALUES ('10', '2019-06-26 14:05:10', null, '/admin/item/overseaslisting/list.html', '海外上市重组', 'ITEM');
INSERT INTO `t_right` VALUES ('11', '2019-06-26 14:06:52', null, '/admin/item/loan/add', '添加项目融资', 'ITEM');
INSERT INTO `t_right` VALUES ('12', '2019-06-26 14:06:58', null, '/admin/item/stocktransfer/add', '添加老股转让', 'ITEM');
INSERT INTO `t_right` VALUES ('13', '2019-06-26 14:06:59', null, '/admin/item/preipo/add', '添加PRE-IPO', 'ITEM');
INSERT INTO `t_right` VALUES ('14', '2019-06-26 14:06:59', null, '/admin/item/stockpledge/add', '添加股票质押', 'ITEM');
INSERT INTO `t_right` VALUES ('15', '2019-06-26 14:07:01', null, '/admin/item/overseaslisting/add', '添加海外上市重组', 'ITEM');
INSERT INTO `t_right` VALUES ('16', '2019-06-26 14:07:07', null, '/admin/item/blocktrade/add', '添加大宗交易', 'ITEM');
INSERT INTO `t_right` VALUES ('17', '2019-06-26 14:07:07', null, '/admin/item/loan/detail.html', '项目融资详情', 'ITEM');
INSERT INTO `t_right` VALUES ('18', '2019-06-26 14:12:11', null, '/admin/item/stocktransfer/detail.html', '老股装让详情', 'ITEM');
INSERT INTO `t_right` VALUES ('19', '2019-06-26 14:12:12', null, '/admin/item/preipo/detail.html', 'PRE-IPO详情', 'ITEM');
INSERT INTO `t_right` VALUES ('20', '2019-06-26 14:12:39', null, '/admin/item/blocktrade/detail.html', '大宗交易详情', 'ITEM');
INSERT INTO `t_right` VALUES ('21', '2019-06-26 14:13:09', null, '/admin/item/stockpledge/detail.html', '股票质押详情', 'ITEM');
INSERT INTO `t_right` VALUES ('22', '2019-06-26 14:13:38', null, '/admin/item/common/adminInfo.html', '项目详情-管理员信息', 'ITEM');
INSERT INTO `t_right` VALUES ('23', '2019-06-26 14:15:09', null, '/admin/item/common/businessInfo.html', '项目详情-用户信息', 'ITEM');
INSERT INTO `t_right` VALUES ('24', '2019-06-26 14:16:22', null, '/admin/item/common/audit', '项目详情-项目审核', 'ITEM');
INSERT INTO `t_right` VALUES ('25', '2019-06-26 14:17:00', null, '/admin/item/common/assigner.html', '项目分配人列表', 'ITEM');
INSERT INTO `t_right` VALUES ('26', '2019-06-26 14:17:23', null, '/admin/item/common/assigner', '项目人分配', 'ITEM');
INSERT INTO `t_right` VALUES ('27', '2019-06-26 14:18:05', null, '/admin/item/common/show', '项目上下架', 'ITEM');
INSERT INTO `t_right` VALUES ('28', '2019-06-26 14:19:07', null, '/admin/list.html', '管理员列表', 'MANAGER');
INSERT INTO `t_right` VALUES ('29', '2019-06-26 14:19:43', null, '/admin/update.html', '查看管理员信息', 'MANAGER');
INSERT INTO `t_right` VALUES ('30', '2019-06-26 14:19:50', null, '/admin/update', '编辑管理员信息', 'MANAGER');
INSERT INTO `t_right` VALUES ('31', '2019-06-26 14:19:55', null, '/admin/updateLock', '管理员锁定/解锁', 'MANAGER');
INSERT INTO `t_right` VALUES ('32', '2019-06-26 14:20:32', null, '/admin/right.html', '管理员权限', 'MANAGER');
INSERT INTO `t_right` VALUES ('33', '2019-06-26 14:22:04', null, '/admin/editAdminRight', '编辑管理员权限', 'MANAGER');
INSERT INTO `t_right` VALUES ('34', '2019-06-28 15:40:53', null, '/platform/setting.html', '平台设置', 'SETTING');
INSERT INTO `t_right` VALUES ('35', '2019-06-28 15:45:18', null, '/platform/updateSmsSetting', '编辑短信接口', 'SETTING');
INSERT INTO `t_right` VALUES ('36', '2019-06-28 15:45:19', null, '/platform/updateEmailSetting', '编辑邮箱设置', 'SETTING');
INSERT INTO `t_right` VALUES ('37', '2019-06-28 15:46:44', null, '/platform/platformLog.html', '系统日志', 'SETTING');
INSERT INTO `t_right` VALUES ('38', '2019-06-28 17:10:47', null, '/admin/add.html', '添加管理员(页面)', 'MANAGER');
INSERT INTO `t_right` VALUES ('39', '2019-06-28 17:12:04', null, '/admin/add', '添加管理员', 'MANAGER');
INSERT INTO `t_right` VALUES ('40', '2019-06-28 17:16:53', null, '/admin/item/loan/add.html', '添加项目融资(页面)', 'ITEM');
INSERT INTO `t_right` VALUES ('41', '2019-06-28 17:16:53', null, '/admin/item/stocktransfer/add.html', '添加老股转让(页面)', 'ITEM');
INSERT INTO `t_right` VALUES ('42', '2019-06-28 17:17:08', null, '/admin/item/preipo/add.html', '添加PRE-IPO(页面)', 'ITEM');
INSERT INTO `t_right` VALUES ('43', '2019-06-28 17:17:24', null, '/admin/item/blocktrade/add.html', '添加大宗交易(页面)', 'ITEM');
INSERT INTO `t_right` VALUES ('44', '2019-06-28 17:17:46', null, '/admin/item/stockpledge/add.html', '添加股票质押(页面)', 'ITEM');
INSERT INTO `t_right` VALUES ('45', '2019-06-28 17:17:53', null, '/admin/item/overseaslisting/add.html', '添加海外上市(页面)', 'ITEM');
