/**
  建表语句
 */
-- 学生表
create table Student(
	id varchar2(50) not null,-- 学号
	name varchar2(50) not null,-- 姓名
	sex varchar2(10) not null,-- 性别
	age number(2,0) not null,-- 年龄
	sclass varchar2(50) not null,-- 班级
	department varchar2(50) not null,-- 院系
	phone varchar2(50),-- 手机号
	password varchar2(50) not null,-- 密码
	image varchar2(50),-- 头像
	primary key(id),
	foreign key(class,department) references SClass(name,dept) on delete cascade
);
-- -- 创建自增序列
-- create sequence s_id increment by 1 start with 1;
-- -- 删除自增序列
-- drop sequence s_id;
-- 教师表
create table Teacher(
	id varchar2(50) not null,-- 教师编号
	name varchar2(50) not null,-- 姓名
	sex varchar2(10) not null,-- 性别
	age number(2,0) not null,-- 年龄
	identity varchar2(50) not null,-- 身份
	password varchar2(50) not null,-- 密码
	image varchar2(50),-- 头像
	primary key(id)
);
-- create sequence s_id increment by 9512100 start with 1;
-- 课程表
create table Course(
	id varchar2(50) not null,-- 课程号
	name varchar2(50) not null,-- 课程名
	credit number(1,0) not null,-- 学分
	period number(2,0) not null,-- 总学时
-- 	theory number(2,0) not null,-- 理论学时
-- 	experiment number(2,0) not null,-- 实验学时
	primary key(id),
	constraint tb_course_name unique(name)
);

-- create sequence c_id increment by 1000 start with 1;
-- 管理员表
create table Admin(
	id varchar2(50) not null,-- 管理员id
	password varchar2(50) not null,-- 管理员密码
	name varchar2(50) not null,-- 管理员姓名
	sex varchar2(10) not null,-- 性别
	age number(2,0) not null,-- 年龄
	image varchar2(50),-- 头像
	primary key(id)
);

-- 选课表
create table SelectCourse(
	stuId varchar2(50) not null,-- 学号
	teachingTaskNum varchar2(50) not null,-- 教学任务号
	grade number(3,2) default 0 not null,-- 成绩
	primary key (stuId,teachingTaskNum),
	foreign key (teachingTaskNum) references TeachingTask (teachingTaskNum) on delete cascade,
	foreign key (stuId) references Student (id) on delete cascade
);
-- 教学任务表
create table TeachingTask(
	teachingTaskNum varchar2(50) not null,-- 教学任务号
	courseName varchar2(50) not null,-- 课程名
	teacherNum VARCHAR2(50) not null,-- 教师编号
	totalNum number(4,0) default 0,-- 选课人数
	location varchar2(50) not null,-- 上课地点
	primary key (teachingTaskNum),
	foreign key (courseName) references Course (name) on delete cascade,
	foreign key (teacherId) references Teacher (id) on delete cascade
);

-- 班级表
create table SClass(
	name varchar2(50) not null,-- 班级名
	totalNum number(4,0) default 0 not null,-- 班级总人数
	dept varchar2(50) not null,-- 院系名
	primary key(name,dept),
	foreign key(dept) references Department(name) on delete cascade,
	constraint tb_sclass_name unique(name)-- 唯一性约束
);
-- 院系表
create table Department(
	id varchar2(10) not null,-- 院系编号
	name varchar2(50) not null,-- 院系名
	primary key(id),
	constraint tb_department_u_name unique(name)-- 唯一性约束
);
/**
  基本数据
 */
-- 院系
insert into department(id,name) values('0','机械工程学院');
insert into department(id,name) values('1','电气与信息工程学院');
insert into department(id,name) values('2','材料科学与工程学院');
insert into department(id,name) values('3','汽车工程学院');
insert into department(id,name) values('4','经济管理学院');
insert into department(id,name) values('5','马克思主义学院');
insert into department(id,name) values('6','外国语学院');
insert into department(id,name) values('7','理学院');
insert into department(id,name) values('8','科技学院');
-- 班级
insert into ssclass(name,dept) values('机制161','机械工程学院');
insert into ssclass(name,dept) values('机制162','机械工程学院');
insert into ssclass(name,dept) values('机制163','机械工程学院');
insert into ssclass(name,dept) values('机制164','机械工程学院');
insert into ssclass(name,dept) values('机制165','机械工程学院');
insert into ssclass(name,dept) values('机制166','机械工程学院');
insert into ssclass(name,dept) values('机制167','机械工程学院');
insert into ssclass(name,dept) values('机制168','机械工程学院');
insert into ssclass(name,dept) values('测控161','机械工程学院');
insert into ssclass(name,dept) values('测控162','机械工程学院');
insert into ssclass(name,dept) values('测控163','机械工程学院');
insert into ssclass(name,dept) values('IE161','机械工程学院');
insert into ssclass(name,dept) values('IE162','机械工程学院');
insert into ssclass(name,dept) values('IE163','机械工程学院');
insert into ssclass(name,dept) values('工设161','机械工程学院');
insert into ssclass(name,dept) values('设计161','机械工程学院');
insert into ssclass(name,dept) values('设计162','机械工程学院');
insert into ssclass(name,dept) values('设计163','机械工程学院');
-- 电气与信息工程学院
insert into ssclass(name,dept) values('计算机161','电气与信息工程学院');
insert into ssclass(name,dept) values('计算机162','电气与信息工程学院');
insert into ssclass(name,dept) values('软件161','电气与信息工程学院');
insert into ssclass(name,dept) values('软件162','电气与信息工程学院');
insert into ssclass(name,dept) values('自动化161','电气与信息工程学院');
insert into ssclass(name,dept) values('自动化162','电气与信息工程学院');
insert into ssclass(name,dept) values('自动化163','电气与信息工程学院');
insert into ssclass(name,dept) values('电气161','电气与信息工程学院');
insert into ssclass(name,dept) values('电气162','电气与信息工程学院');
insert into ssclass(name,dept) values('电气163','电气与信息工程学院');
insert into ssclass(name,dept) values('电信大类161','电气与信息工程学院');
insert into ssclass(name,dept) values('电信大类162','电气与信息工程学院');
insert into ssclass(name,dept) values('电信大类163','电气与信息工程学院');
insert into ssclass(name,dept) values('电信大类164','电气与信息工程学院');
insert into ssclass(name,dept) values('电信大类165','电气与信息工程学院');
insert into ssclass(name,dept) values('电信大类166','电气与信息工程学院');
insert into ssclass(name,dept) values('电信大类167','电气与信息工程学院');
-- 材料科学与工程学院
insert into ssclass(name,dept) values('材控161','材料科学与工程学院');
insert into ssclass(name,dept) values('材控162','材料科学与工程学院');
insert into ssclass(name,dept) values('材控163','材料科学与工程学院');
insert into ssclass(name,dept) values('材控164','材料科学与工程学院');
insert into ssclass(name,dept) values('材控165','材料科学与工程学院');
insert into ssclass(name,dept) values('材控166','材料科学与工程学院');
insert into ssclass(name,dept) values('焊接161','材料科学与工程学院');
-- 汽车工程学院
insert into ssclass(name,dept) values('车辆161','汽车工程学院');
insert into ssclass(name,dept) values('车辆162','汽车工程学院');
insert into ssclass(name,dept) values('车辆163','汽车工程学院');
insert into ssclass(name,dept) values('车辆164','汽车工程学院');
insert into ssclass(name,dept) values('车辆165','汽车工程学院');
insert into sclass(name,dept) values('车辆166','汽车工程学院');
insert into sclass(name,dept) values('车辆167','汽车工程学院');
-- 教师
insert into teacher(id,name,sex,age,identity,password) values('200000911','许威','男',50,'教授','1');
insert into teacher(id,name,sex,age,identity,password) values('200000912','吕青秀','男',30,'教授','1');
insert into teacher(id,name,sex,age,identity,password) values('200000913','杨威','男',40,'教授','1');
insert into teacher(id,name,sex,age,identity,password) values('200000914','王佳豪','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000915','潘昊','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000916','云登','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000917','柯奇','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000918','李鹏飞','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000955','王清','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000920','陈立衡','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000921','曹建彬','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000922','张泽辰','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000923','何珊','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000924','陈苗苗','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000925','丁雄莉','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000926','赵梦雨','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200000927','刘书晴','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200001586','沈植诚','男',55,'讲师','1');
insert into teacher(id,name,sex,age,identity,password) values('200002413','石炜','男',55,'教授','1');
insert into teacher(id,name,sex,age,identity,password) values('200000930','张知朋','男',55,'副教授','1');
insert into teacher(id,name,sex,age,identity,password) values('200000931','张志广','男',55,'副教授','1');
insert into teacher(id,name,sex,age,identity,password) values('200000935','盛豆豆','男',55,'副教授','1');
insert into teacher(id,name,sex,age,identity,password) values('200000938','刘远智','男',55,'副教授','1');
insert into teacher(id,name,sex,age,identity,password) values('200000939','张远','男',55,'副教授','1');
insert into teacher(id,name,sex,age,identity,password) values('200000940','卢海宇','男',55,'助教','1');
insert into teacher(id,name,sex,age,identity,password) values('200000941','闻宏','男',55,'助教','1');
insert into teacher(id,name,sex,age,identity,password) values('200000959','葛乾','男',55,'助教','1');
insert into teacher(id,name,sex,age,identity,password) values('200000962','付康浩','男',55,'助教','1');
insert into teacher(id,name,sex,age,identity,password) values('200000966','杨福临','男',55,'助教','1');
-- 插入课程表数据
insert into course(id,name,credit,period) values('020420','C语言程序设计',4,64);
insert into course(id,name,credit,period) values('020460','计算机组成原理',4,64);
insert into course(id,name,credit,period) values('020610','离散数学',4,64);
insert into course(id,name,credit,period) values('020621','数据结构',4,64);
insert into course(id,name,credit,period) values('020630','操作系统原理',4,64);
insert into course(id,name,credit,period) values('020660','编译原理',3,48);
insert into course(id,name,credit,period) values('150014','高等数学',5,80);
insert into course(id,name,credit,period) values('020471','计算机网络A',3.5,56);
insert into course(id,name,credit,period) values('020720','JAVA程序设计',3,48);
insert into course(id,name,credit,period) values('020640','数据库系统原理',3.5,56);
insert into course(id,name,credit,period) values('020650','计算机图形学',2.5,40);
insert into course(id,name,credit,period) values('020730','算法设计与分析',2.5,40);
insert into course(id,name,credit,period) values('020700','数据库系统实现',2.5,40);
insert into course(id,name,credit,period) values('020680','软件工程',2.0,32);
-- 插入教学计划数据
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962501','C语言程序设计','200000911','6101');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962502','计算机组成原理','200000912','6102');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962503','离散数学','200000913','6103');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962504','计算机网络A','200000914','6104');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962505','数据库系统原理','200000915','6105');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962506','数据库系统实现','200000916','6106');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962507','软件工程','200000917','6107');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962508','数据库系统实现','200000918','6108');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962509','数据结构','200000919','6109');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962510','软件工程','200000920','6110');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962511','操作系统原理','200000921','6111');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962512','操作系统原理','200000922','6112');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962513','数据结构','200000923','6113');
insert into teachingTask(teachingTaskNum,courseName,teacherId,location) values('201962514','数据结构','200000924','6114');
-- 学生数据
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512101','李勇','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512102','刘晨','男',20,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512103','王敏','女',20,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9521101','张立','男',22,'材控161','材料科学与工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9521102','吴宾','女',21,'材控161','材料科学与工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9521103','张海','男',20,'材控161','材料科学与工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9531101','钱小平','女',18,'材控161','材料科学与工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9531102','王大力','男',19,'材控161','材料科学与工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512104','张健','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512105','张齐朴','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512106','何世焱','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512107','乔勇','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512108','余天璞','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512109','郑德祥','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512110','沈忱','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512111','杜达林','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512112','方科','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512113','崔昌瑞','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512114','蔡基利','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512115','陈礼锐','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512116','刘大伟','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512117','杨宝宏','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512118','陈立淦','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512119','肖克','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512120','郭清吉','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512121','代磊','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512122','刘斌龙','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512123','陈少鹏','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512124','张嘉伟','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512125','李博古','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512126','金力','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512127','张景峰','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512128','张仁涛','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512129','裴晓斌','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512130','李成','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512131','王龙','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512132','史成林','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512133','刘其辉','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512134','张锋','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512135','常铮','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512136','王娜娜','女',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512137','严涛','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512138','李江','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512139','李晓真','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512140','罗雷','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512141','李豪','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512142','高瞻','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512143','吴建兵','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512144','陈新坤','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512145','李盼','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512146','潘威','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512147','陈光银','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512148','吕建雨','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512149','尚前进','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512150','陈龙','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512151','潘和星','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512152','陈啟飞','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512153','罗烈','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512154','陈涛','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512155','许泽亭','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512156','卢秦亮','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512157','张云飞','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512158','史博文','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512159','龚进伟','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512160','周靖','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512161','孙亚鹏','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512162','陈强','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512163','罗四维','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512164','赫中翮','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512165','王辉','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512166','刁劼庭','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512167','白小康','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512168','方敏','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512169','张博文','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512170','秦正位','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512171','熊海森','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512172','曾虎','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512173','张小华','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512174','宋亚威','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512175','张龙','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512176','王德红','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512177','杨文亮','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512178','石东','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512179','董求求','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512180','石颖','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512181','刘乾乾','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512182','张涛','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512183','王本友','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512184','操恒','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512185','张娟','女',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512186','杨丽','女',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512187','陈惜','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512188','蔡永权','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512189','邓超','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512190','林挺胜','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512191','周炫','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512192','廖欢','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512193','吴丕权','男',19,'软件162','电气与信息工程学院',null,'123456','1');
insert into student(id,name,sex,age,sclass,department,image,phone,password) values('9512194','陈辉','男',19,'软件162','电气与信息工程学院',null,'123456','1');