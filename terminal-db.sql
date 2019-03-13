SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS tb_config;
DROP TABLE IF EXISTS tb_dailytransfer_order;
DROP TABLE IF EXISTS tb_dailytransfer;
DROP TABLE IF EXISTS tb_product_type;
DROP TABLE IF EXISTS tb_site;
DROP TABLE IF EXISTS tb_user;




/* Create Tables */

-- 系统配置表
CREATE TABLE tb_config
(
	-- 编号
	id varchar(32) NOT NULL COMMENT '编号 : 编号',
	-- 配置key
	config_key varchar(100) COMMENT '配置key : 配置key',
	-- 配置值
	config_value varchar(200) COMMENT '配置值 : 配置值',
	-- 创建时间
	create_time datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_time datetime COMMENT '更新时间 : 更新时间',
	PRIMARY KEY (id)
) COMMENT = '系统配置表';


-- 传输表
CREATE TABLE tb_dailytransfer
(
	-- 编号
	id varchar(32) NOT NULL COMMENT '编号 : 编号',
	-- 创建人编号
	create_id varchar(32) COMMENT '创建人编号 : 创建人编号',
	-- 更新人编号
	update_id varchar(32) COMMENT '更新人编号 : 更新人编号',
	-- 创建时间
	create_time datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_time datetime COMMENT '更新时间 : 更新时间',
	-- 报表日期
	report_time datetime COMMENT '报表日期 : 报表日期',
	-- 传输状态,0:待传输，1:传输成功, 2:传输失败
	status int DEFAULT 0 COMMENT '传输状态 : 传输状态,0:待传输，1:传输成功, 2:传输失败',
	-- xml数据
	xml_data text COMMENT 'xml数据 : xml数据',
	-- 填报单位
	company_name varchar(100) COMMENT '填报单位 : 填报单位',
	-- 单位编号
	company_id varchar(100) COMMENT '单位编号 : 单位编号',
	-- 预留字段1
	res1 varchar(50) COMMENT '预留字段1 : 预留字段1',
	-- 预留字段2
	res2 varchar(50) COMMENT '预留字段2 : 预留字段2',
	-- 预留字段3
	res3 varchar(50) COMMENT '预留字段3 : 预留字段3',
	-- 删除标识
	is_del int DEFAULT 0 COMMENT '删除标识 : 删除标识',
	PRIMARY KEY (id)
) COMMENT = '传输表';


-- 上传订单信息表
CREATE TABLE tb_dailytransfer_order
(
	-- 编号
	id varchar(32) NOT NULL COMMENT '编号 : 编号',
	-- 站点编号
	site_id varchar(32) COMMENT '站点编号 : 站点编号',
	-- 商品类别id
	product_type_id varchar(32) COMMENT '商品类别id : 商品类别id',
	-- 传输编号
	dailytransfer_id varchar(32) COMMENT '传输编号 : 传输编号',
	-- 代买代卖数据类型
	buy_sell_type varchar(50) COMMENT '代买代卖数据类型 : 代买代卖数据类型',
	-- 代买/代卖总金额
	money varchar(100) COMMENT '代买/代卖总金额 : 代买/代卖总金额',
	-- 代买总订单数
	buy_count int DEFAULT 0 COMMENT '代买总订单数 : 代买总订单数',
	-- 代卖总订单数
	sell_count int COMMENT '代卖总订单数 : 代卖总订单数',
	-- 预留字段1
	res1 varchar(50) COMMENT '预留字段1 : 预留字段1',
	-- 预留字段2
	res2 varchar(50) COMMENT '预留字段2 : 预留字段2',
	-- 预留字段3
	res3 varchar(50) COMMENT '预留字段3 : 预留字段3',
	-- 预留字段4
	res4 varchar(50) COMMENT '预留字段4 : 预留字段4',
	-- 删除标识
	is_del int DEFAULT 0 COMMENT '删除标识 : 删除标识',
	PRIMARY KEY (id)
) COMMENT = '上传订单信息表';


-- 商品类别表
CREATE TABLE tb_product_type
(
	-- 编号
	id varchar(32) NOT NULL COMMENT '编号 : 编号',
	-- 创建人编号
	create_id varchar(32) COMMENT '创建人编号 : 创建人编号',
	-- 更新人编号
	update_id varchar(32) COMMENT '更新人编号 : 更新人编号',
	-- 商品大类
	broad_type varchar(100) COMMENT '商品大类 : 商品大类',
	-- 商品类别
	product_type varchar(200) COMMENT '商品类别 : 商品类别',
	-- 代买编码
	generate_buy_no varchar(50) COMMENT '代买编码 : 代买编码',
	-- 代卖编码
	generate_sell_no varchar(200) COMMENT '代卖编码 : 代卖编码',
	-- 创建时间
	create_time datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_time datetime COMMENT '更新时间 : 更新时间',
	-- 备注
	remark varchar(200) COMMENT '备注 : 备注',
	-- 删除标识
	isdel int DEFAULT 0 COMMENT '删除标识 : 删除标识',
	PRIMARY KEY (id)
) COMMENT = '商品类别表';


-- 站点表
CREATE TABLE tb_site
(
	-- 编号
	id varchar(32) NOT NULL COMMENT '编号 : 编号',
	-- 创建人编号
	create_id varchar(32) COMMENT '创建人编号 : 创建人编号',
	-- 更新人
	update_id varchar(32) COMMENT '更新人 : 更新人',
	-- 创建时间
	create_time datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_time datetime COMMENT '更新时间 : 更新时间',
	-- 站点名称
	site_name varchar(100) COMMENT '站点名称 : 站点名称',
	-- 站点编号
	site_code varchar(50) COMMENT '站点编号 : 站点编号',
	-- 站点类型
	site_type varchar(20) COMMENT '站点类型 : 站点类型',
	-- 预留字段1
	res1 varchar(50) COMMENT '预留字段1 : 预留字段1',
	-- 预留字段2
	res2 varchar(50) COMMENT '预留字段2 : 预留字段2',
	-- 预留字段3
	res3 varchar(50) COMMENT '预留字段3 : 预留字段3',
	-- 删除标识
	is_del int DEFAULT 0 COMMENT '删除标识 : 删除标识',
	PRIMARY KEY (id)
) COMMENT = '站点表';


-- 用户表
CREATE TABLE tb_user
(
	-- 编号
	id varchar(32) NOT NULL COMMENT '编号 : 编号',
	-- 用户名称
	user_name varchar(50) COMMENT '用户名称 : 用户名称',
	-- 手机号码
	mobile varchar(20) COMMENT '手机号码 : 手机号码',
	-- 密码
	password varchar(50) COMMENT '密码 : 密码',
	-- 加密盐值
	salt varchar(50) COMMENT '加密盐值 : 加密盐值',
	-- 头像地址
	face_url varchar(200) COMMENT '头像地址 : 头像地址',
	-- 创建时间
	create_time datetime COMMENT '创建时间 : 创建时间',
	-- 更新时间
	update_time datetime COMMENT '更新时间 : 更新时间',
	-- 邮箱地址
	email varchar(50) COMMENT '邮箱地址 : 邮箱地址',
	-- 删除标识
	is_del int DEFAULT 0 COMMENT '删除标识 : 删除标识',
	PRIMARY KEY (id)
) COMMENT = '用户表';



/* Create Foreign Keys */

ALTER TABLE tb_dailytransfer_order
	ADD FOREIGN KEY (dailytransfer_id)
	REFERENCES tb_dailytransfer (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tb_dailytransfer_order
	ADD FOREIGN KEY (product_type_id)
	REFERENCES tb_product_type (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tb_dailytransfer_order
	ADD FOREIGN KEY (site_id)
	REFERENCES tb_site (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tb_dailytransfer
	ADD FOREIGN KEY (create_id)
	REFERENCES tb_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tb_dailytransfer
	ADD FOREIGN KEY (update_id)
	REFERENCES tb_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tb_product_type
	ADD FOREIGN KEY (create_id)
	REFERENCES tb_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tb_product_type
	ADD FOREIGN KEY (update_id)
	REFERENCES tb_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tb_site
	ADD FOREIGN KEY (update_id)
	REFERENCES tb_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tb_site
	ADD FOREIGN KEY (create_id)
	REFERENCES tb_user (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



