CREATE TABLE IF NOT EXISTS `demo_inventory` (
  `id` mediumint(20) NOT NULL AUTO_INCREMENT,
  `biz_id` varchar(64) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `count` mediumint(20) NOT NULL,
  `detail` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_biz_id` (`biz_id`)
);