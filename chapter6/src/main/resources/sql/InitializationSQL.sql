--用户表
CREATE TABLE IF NOT EXISTS `user`(
   `id` INT UNSIGNED AUTO_INCREMENT,    
   `userid` VARCHAR(100) NOT NULL,
   `username` VARCHAR(100) NOT NULL,
   `gender` CHAR(1) NOT NULL,
   `age` INT NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--用户表插入数据
insert into user(userid ,username, gender, age) values('dashu','大叔','M',18);

--当遇到 authentication plugin caching_sha2_password 错误
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';

--用户表增加手机号字段
ALTER TABLE user ADD mobilephone varchar(15) ;

--修改手机号
update user set mobilephone = '13800000000' where userid = '1';

--产品表
CREATE TABLE IF NOT EXISTS `product`(
   `id` INT UNSIGNED AUTO_INCREMENT,     
   `productid` VARCHAR(100) NOT NULL,
   `productname` VARCHAR(100) NOT NULL,
   `type` CHAR(4) NOT NULL,
   `price` DECIMAL(10,2) NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO product(productid, productname, type, price) values ('0001MB0001','Huawei Mate30 Pro', '1', 6899.00);
INSERT INTO product(productid, productname, type, price) values ('0001MB0002','Xiaomi CC9 Pro', '1', 2599.00);

--字典表
CREATE TABLE IF NOT EXISTS `dict`(
   `id` INT UNSIGNED AUTO_INCREMENT comment '主键id',
   `type` VARCHAR(100) NOT NULL comment '字典数据的类型，比如 PRODUCT_TYPE 表示商品类型',
   `code` CHAR(4) NOT NULL comment '编码',
   `desc` VARCHAR(100) NOT NULL comment '中文描述',
   `short_desc` VARCHAR(100) NOT NULL comment '中文缩写',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO dict(`type`, `code`, `desc`, `short_desc`) values ('PRODUCT_TYPE','1', '手机/运营商/数码', '手机');
INSERT INTO dict(`type`, `code`, `desc`, `short_desc`) values ('PRODUCT_TYPE','2', '图书/文娱/教育/电子书', '图书');
INSERT INTO dict(`type`, `code`, `desc`, `short_desc`) values ('PRODUCT_TYPE','3', '电脑/办公', '电脑');
INSERT INTO dict(`type`, `code`, `desc`, `short_desc`) values ('PRODUCT_TYPE','4', '家用电器', '电器');