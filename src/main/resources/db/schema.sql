-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(6) NOT NULL,
  `student_id` int(6) NOT NULL,
  `name` varchar(45) NOT NULL,
  `age` int(6) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `isOk` datetime DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `modifiry_tm` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

