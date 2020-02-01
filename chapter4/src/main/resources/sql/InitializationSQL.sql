--创建 user 表
CREATE TABLE IF NOT EXISTS `user`(
   `userid` INT UNSIGNED AUTO_INCREMENT,
   `username` VARCHAR(100) NOT NULL,
   `gender` CHAR(1) NOT NULL,
   `age` INT NOT NULL,
   PRIMARY KEY ( `userid` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--插入一条数据
insert into user(username, gender, age) values('dashu','M',18);

--如果 MySQL 8.0 客户端连接报错：authentication plugin caching_sha2_password
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';

--增加手机号字段
ALTER TABLE user ADD mobilephone varchar(15) ;

--更新 userid = 1 数据的手机号
update user set mobilephone = '13800000000' where userid = '1';