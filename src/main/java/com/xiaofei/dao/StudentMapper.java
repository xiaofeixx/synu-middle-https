package com.xiaofei.dao;

import com.xiaofei.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author xiaofei
 * @Classname StudentMapper
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Repository
public interface StudentMapper {

    @Insert("insert into student(sno,password,info,grade,subject_table) values(#{sno},#{password},#{info},#{grade},#{subjectTable})")
    int insertStudentInformation(Student student);

    @Select("select sno,password,info from student where sno=#{sno} and password=#{password}")
    Student login(@Param("sno") String sno,@Param("password") String password);

    @Update("update student set info=#{info}, grade=#{grade},subject_table=#{subjectTable} where sno=#{sno} and password=#{password}")
    int reflashStudentInformation(Student student);

    @Select("select sno,grade from student where sno=#{sno} and password=#{password} ")
    Student findGrade(@Param("sno") String sno,@Param("password") String password);

    @Select("select sno,subject_table from student where sno=#{sno} and password=#{password}")
    Student findSubjectTable(@Param("sno") String sno,@Param("password") String password);
}
