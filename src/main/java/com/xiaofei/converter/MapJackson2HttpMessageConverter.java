package com.xiaofei.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaofei
 * @Classname MapJackson2HttpMessageConverter
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
public class MapJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public MapJackson2HttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_HTML);  //加入text/html类型的支持
        setSupportedMediaTypes(mediaTypes);// tag6
    }
}
