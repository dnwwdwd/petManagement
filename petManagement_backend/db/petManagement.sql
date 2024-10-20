create database if not exists petManagement;

use petManagement;

create table petmanagement.activity
(
    id          int auto_increment comment 'id'
        primary key,
    name        varchar(512)                       null comment '活动名称',
    userId      int                                null comment '用户id',
    description varchar(256)                       null comment '活动描述',
    address     varchar(256)                       null comment '活动地点',
    imgUrl      varchar(256)                       null comment '图片地址',
    startTime   varchar(256)                       null comment '开始时间',
    endTime     varchar(256)                       null comment '结束时间',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '活动表';


create table petmanagement.comment
(
    id         int auto_increment comment 'id'
        primary key,
    userId     int                                null comment '用户id',
    petForumId int                                null comment '宠物论坛id',
    content    varchar(512)                       null comment '评论内容',
    createTIme datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '评论表';


create table petmanagement.pet
(
    id            int auto_increment comment 'id'
        primary key,
    userId        int                                null comment '添加此宠物人的id',
    petName       varchar(256)                       null comment '宠物名称',
    avatarUrl     varchar(256)                       null comment '头像',
    age           int                                null comment '年龄',
    sex           char(2)                            null comment '性别',
    state         varchar(256)                       null comment '身体状态',
    isSterilized  tinyint                            null comment '是否绝育（0 - 未绝育 1 - 已绝育）',
    isVaccination tinyint                            null comment '是否接种疫苗（0 - 未接种 1 - 已接种）',
    description   varchar(512)                       null comment '描述',
    variety       varchar(256)                       null comment '品种',
    type          int                                null comment '宠物类型',
    createTime    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '宠物表';


create table petmanagement.pet_adopt
(
    id         int auto_increment comment 'id'
        primary key,
    userId     int                                null comment '领养人id',
    petId      int                                null comment '宠物id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null comment '修改时间'
);


create table petmanagement.pet_donate
(
    id            int auto_increment comment 'id'
        primary key,
    petName       varchar(256)                       null comment '宠物名称',
    age           int                                null comment '年龄',
    sex           char(2)                            null comment '性别',
    description   varchar(256)                       null comment '描述',
    state         varchar(256)                       null comment '状态',
    avatarUrl     varchar(256)                       null comment '宠物头像',
    isSterilized  tinyint                            null comment '是否绝育',
    isVaccination tinyint                            null comment '是否接种疫苗',
    type          int                                null comment '类型',
    variety       varchar(256)                       null comment '品种',
    userId        int                                null comment '捐赠人id',
    isAdopted     tinyint  default 0                 not null comment '是否被收养',
    createTime    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '宠物捐赠';


create table petmanagement.pet_forum
(
    id          int auto_increment comment 'id'
        primary key,
    userId      int                                null comment '创建者id',
    name        varchar(256)                       null comment '论坛名称',
    imgUrl      varchar(256)                       null comment '图片',
    description varchar(512)                       null comment '描述',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '宠物论坛';



create table petmanagement.user
(
    id         int auto_increment comment 'id'
        primary key,
    username   varchar(50)                         null comment '用户名',
    password   varchar(50)                         null comment '密码',
    nickname   varchar(50)                         null comment '昵称',
    email      varchar(50)                         null comment '邮箱',
    phone      varchar(20)                         null comment '电话',
    address    varchar(255)                        null comment '地址',
    createTime timestamp default CURRENT_TIMESTAMP null comment '创建时间',
    avatarUrl  varchar(255)                        null comment '头像',
    role       varchar(50)                         null comment '角色',
    sex        varchar(255)                        null comment '性别',
    birth      varchar(255)                        null comment '出生年月'
)
    collate = utf8mb4_unicode_ci
    row_format = DYNAMIC;


INSERT INTO petmanagement.activity (id, name, userId, description, address, imgUrl, startTime, endTime, createTime, updateTime) VALUES (1, '遛狗', 1, '遛狗', '武汉江夏', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.3_okurGOQy3cqjEuRSl1RgHaE8?rs=1&pid=ImgDetMain', '2024.10.19 10:00', '2024.10.30 10:00', '2024-10-19 13:09:06', '2024-10-19 13:09:38');
INSERT INTO petmanagement.activity (id, name, userId, description, address, imgUrl, startTime, endTime, createTime, updateTime) VALUES (2, '逗猫活动', 1, '逗猫', '泰州姜堰区', 'https://img95.699pic.com/photo/60041/8540.jpg_wh860.jpg', '2024.10.19 10:00', '2024.10.30 10:00', '2024-10-19 13:08:01', '2024-10-19 13:08:01');
INSERT INTO petmanagement.activity (id, name, userId, description, address, imgUrl, startTime, endTime, createTime, updateTime) VALUES (3, '不知道', 1, '不知道', '武汉洪山', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.3_okurGOQy3cqjEuRSl1RgHaE8?rs=1&pid=ImgDetMain', '2024.11.3 10:00', '2024.11.30 10:00', '2024-10-19 13:34:35', '2024-10-19 13:34:35');


INSERT INTO petmanagement.pet (id, userId, petName, avatarUrl, age, sex, state, isSterilized, isVaccination, description, variety, type, createTime, updateTime) VALUES (2, 1, '小白', 'https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20241019102555.png', 2, '女', '健康', 0, 1, '健康', '土狗', 1, '2024-10-18 23:09:01', '2024-10-19 10:26:09');

INSERT INTO petmanagement.pet_adopt (id, userId, petId, createTime, updateTime) VALUES (1, 2, 1, '2024-10-19 10:58:48', '2024-10-19 10:58:48');
INSERT INTO petmanagement.pet_adopt (id, userId, petId, createTime, updateTime) VALUES (2, 3, 1, '2024-10-19 10:58:48', '2024-10-19 10:58:48');
INSERT INTO petmanagement.pet_adopt (id, userId, petId, createTime, updateTime) VALUES (4, 1, 3, '2024-10-19 15:23:24', '2024-10-19 15:23:24');

INSERT INTO petmanagement.pet_donate (id, petName, age, sex, description, state, avatarUrl, isSterilized, isVaccination, type, variety, userId, isAdopted, createTime, updateTime) VALUES (1, '小白', 3, '母', '可爱，健康，活泼', '健康', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.12MmFldI3GfBRH9xld_EogHaLF?rs=1&pid=ImgDetMain', 0, 0, 1, '土狗', 1, 0, '2024-10-17 18:14:22', '2024-10-19 14:23:22');
INSERT INTO petmanagement.pet_donate (id, petName, age, sex, description, state, avatarUrl, isSterilized, isVaccination, type, variety, userId, isAdopted, createTime, updateTime) VALUES (2, '小黑黑', 3, '男', '黑色的', '健康', 'https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/img/20241019102555.png', 1, 1, 1, '小土狗', 1, 0, '2024-10-19 11:14:56', '2024-10-19 11:27:31');
INSERT INTO petmanagement.pet_donate (id, petName, age, sex, description, state, avatarUrl, isSterilized, isVaccination, type, variety, userId, isAdopted, createTime, updateTime) VALUES (3, '小黄', 3, '男', '可爱', '健康', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.3_okurGOQy3cqjEuRSl1RgHaE8?rs=1&pid=ImgDetMain', 1, 1, 1, '土狗', 1, 0, '2024-10-19 14:22:30', '2024-10-19 15:24:03');

INSERT INTO petmanagement.pet_forum (id, userId, name, imgUrl, description, createTime, updateTime) VALUES (5, 1, '猫狗喜爱之争', 'https://ts1.cn.mm.bing.net/th/id/R-C.a4fb4b381e19df488540584dfd49a7b0?rik=VNzQsoMk92ViGA&riu=http%3a%2f%2fpic.616pic.com%2fphotoone%2f00%2f01%2f96%2f618cf161c3ff59689.jpg!%2ffw%2f1120&ehk=xO447Zm4pMtcJMGf1ljvHgVo951HwM9OWbkPsr5kotQ%3d&risl=&pid=ImgRaw&r=0', '你喜欢猫还是狗呢？', '2024-10-17 22:03:39', '2024-10-19 14:28:17');

INSERT INTO petmanagement.user (id, username, password, nickname, email, phone, address, createTime, avatarUrl, role, sex, birth) VALUES (1, 'admin', 'admin', '管理员', 'admin@qq.com', '13988997788', '武汉', '2022-01-22 21:10:27', 'https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/hm/56e55a4c-90d4-49c7-99e0-54a600f7afdd.jpg', 'ROLE_ADMIN', '男', '1995-06-06');
INSERT INTO petmanagement.user (id, username, password, nickname, email, phone, address, createTime, avatarUrl, role, sex, birth) VALUES (2, 'zhang', '123', '张张', 'zhang@qq.com', '13677889900', '南京', '2022-02-26 22:10:14', 'https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/hm/56e55a4c-90d4-49c7-99e0-54a600f7afdd.jpg', 'ROLE_USER', '女', '1995-06-06');
INSERT INTO petmanagement.user (id, username, password, nickname, email, phone, address, createTime, avatarUrl, role, sex, birth) VALUES (3, 'wang', '123', '我是王五', 'wang@qq.com', '13877668855', '上海', '2022-02-26 22:10:18', 'https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/hm/56e55a4c-90d4-49c7-99e0-54a600f7afdd.jpg', 'ROLE_USER', '男', '1995-06-06');
INSERT INTO petmanagement.user (id, username, password, nickname, email, phone, address, createTime, avatarUrl, role, sex, birth) VALUES (4, 'myjjj', '111', 'myjjj', null, null, null, '2023-12-12 23:52:01', 'https://hejiajun-img-bucket.oss-cn-wuhan-lr.aliyuncs.com/hm/56e55a4c-90d4-49c7-99e0-54a600f7afdd.jpg', 'ROLE_ADMIN', null, null);
