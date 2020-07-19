package com.xiaofei.service;

import com.xiaofei.entity.Student;
import org.apache.ibatis.annotations.Select;

/**
 * @author xiaofei
 * @Classname StudentService
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public interface StudentService {

    String insertStudentInfomation(String sno, String password);

    String login(String sno, String password);

    String reflashStudentInformation(String sno, String password);


    String findGrade(String sno, String password);

    String findSubjectTable(String sno, String password);
}
