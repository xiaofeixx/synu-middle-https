create database applet;
use applet;


# 创建学上信息表

create table student (
    sno varchar(10) primary key comment '学号',
    password varchar(255) not null  comment '密码',
    info text not null comment '学生信息',
    grade text not null comment '成绩',
    subject_table text not null comment '课程表'
);

