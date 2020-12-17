SET FOREIGN_KEY_CHECKS = 0;

truncate table pet;
truncate table store;
--fetchin data
INSERT into  store(`id`,`name`,`location`,`contact_no`)
VALUES(21,  'super store','nassarawa','90987'),
(22, 'king cabana store', 'Lagos','080987656'),
(29, 'E-centre store', 'Lagos','080987645'),
(23, 'Domino store', 'Abuja','080981234'),
(24, 'Shoprite store', 'Lagos','080987645');


INSERT into pet (`id`,`name`,`color`,`breed`,`age`,`pet_sex`,`store_id`)
VALUE(31, 'jill','blue','parot','6','MALE','21'),
(32, 'jack','black','Dog','9','MALE','21'),
(33, 'blue','green','monkey','6','FEMALE','21'),
(34, 'sally','brown','birds','8','MALE','21'),
(35, 'henry','green','goat','3','FEMALE','21'),
(36, 'salah','blue','parot','6','MALE','21'),
(37, 'ross','blue','parot','6','MALE','21');


SET FOREIGN_KEY_CHECKS = 1;

