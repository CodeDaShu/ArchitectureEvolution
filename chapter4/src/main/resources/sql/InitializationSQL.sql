--���� user ��
CREATE TABLE IF NOT EXISTS `user`(
   `userid` INT UNSIGNED AUTO_INCREMENT,
   `username` VARCHAR(100) NOT NULL,
   `gender` CHAR(1) NOT NULL,
   `age` INT NOT NULL,
   PRIMARY KEY ( `userid` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--����һ������
insert into user(username, gender, age) values('dashu','M',18);

--��� MySQL 8.0 �ͻ������ӱ���authentication plugin caching_sha2_password
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';

--�����ֻ����ֶ�
ALTER TABLE user ADD mobilephone varchar(15) ;

--���� userid = 1 ���ݵ��ֻ���
update user set mobilephone = '13800000000' where userid = '1';