package com.xiaofei.controller;

import com.xiaofei.entity.User;
import com.xiaofei.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaofei
 * @Classname StudentController
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;


  /**
   * * @param user
   *@return {@link String}
   *@throws
   *@description 登录并返回学生信息
   */
    @PostMapping("/login")
    public String insertStudentInformation(@RequestBody User user){
        return studentService.login(user.getSno(), user.getPassword());

    }

    /**
     * * @param
     *@return {@link String}
     *@throws
     *@description 查询成绩
     */
    @PostMapping("/grade")
    public String findGrade(@RequestBody User user){
        return studentService.findGrade(user.getSno(), user.getPassword());
    }

    /**
     * * @param
     *@return {@link String}
     *@throws
     *@description 查询课程表
     */
    @PostMapping("/subject")
    public String findSubjectTable(@RequestBody User user){

        return studentService.findSubjectTable(user.getSno(), user.getPassword());
    }


    @PostMapping("/refresh")
    public String refreshStudentInformation(@RequestBody User user) {

        return studentService.reflashStudentInformation(user.getSno(), user.getPassword());
    }

}
