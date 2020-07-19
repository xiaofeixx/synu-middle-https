package com.xiaofei.service.iml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaofei.dao.StudentMapper;
import com.xiaofei.entity.Student;
import com.xiaofei.service.StudentService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.lang.model.element.VariableElement;
import java.util.Map;

/**
 * @author xiaofei
 * @Classname StudentServiceIml
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Service
@PropertySource({"classpath:url.properties"})
public class StudentServiceIml implements StudentService {

    @Value("${jsonUrl}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StudentMapper studentMapper;

    public String findGrade(String sno, String password) {
        return studentMapper.findGrade(sno, DigestUtils.md5Hex(password)).getGrade();
    }

    public String findSubjectTable(String sno, String password) {
        String s  = studentMapper.findSubjectTable(sno, DigestUtils.md5Hex(password)).getSubjectTable();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(s));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public String login(String sno, String password) {

        Student login = studentMapper.login(sno, DigestUtils.md5Hex(password));
        if (login == null){
            String result = insertStudentInfomation(sno, password);
            return result;
        }
        return login.getInfo();
    }

    @Transactional
    public String reflashStudentInformation(String sno, String password) {
        JsonNode jsonNode = restTemplate.getForObject(url + sno + "/" + password, JsonNode.class);
        assert jsonNode != null;
        if ("false".equals(jsonNode.get("success").toString())){
            return findGrade(sno,DigestUtils.md5Hex(password));
        } else {
            Student student = new Student();
            student.setSno(sno);
            String pwd = DigestUtils.md5Hex(password);
            student.setPassword(pwd);
            student.setGrade(jsonNode.get("grade_json").toString());
            student.setInfo(jsonNode.get("info_json").toString());
            student.setSubjectTable(jsonNode.get("schedule_json").toString());
            studentMapper.reflashStudentInformation(student);
            System.out.println(student);
            return jsonNode.get("grade_json").toString();
        }
    }

    @Transactional
    public String insertStudentInfomation(String sno, String password) {
        JsonNode map= restTemplate.getForObject(url + sno + "/" + password, JsonNode.class);
        assert map != null;
        if ("false".equals(map.get("success").toString())){
            return map.toString();
        } else {
            Student student = new Student();
            student.setSno(sno);
            String pwd = DigestUtils.md5Hex(password);
            student.setPassword(pwd);
            student.setGrade(map.get("grade_json").toString());
            student.setInfo(map.get("info_json").toString());
            student.setSubjectTable(map.get("schedule_json").toString());
             studentMapper.insertStudentInformation(student);
             return map.get("info_json").toString();
        }


    }
}
