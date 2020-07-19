package com.xiaofei.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaofei
 * @Classname DispatcherConfig
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.xiaofei"})
public class DispatcherConfig  implements WebMvcConfigurer {

    //配置视图解析器
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        return resolver;
    }

    //配置文件上传解析器
    //bean的名字必须为multipartResolver
    @Bean("multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        //设置上传最大尺寸5MB
        resolver.setMaxUploadSize(5242880);
        //设置默认编码
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    //配置静态资源 js css image
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
    }

    //校验的资源文件
    @Bean("messageSource")
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        //资源文件名
        // source.setBasenames("ValidationMessages");
        //设置编码格式
        source.setDefaultEncoding("UTF-8");
        //对资源文件的缓存时间
        source.setCacheSeconds(120);
        return source;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
        List<MediaType> support = new ArrayList<>();
        support.add(MediaType.APPLICATION_JSON_UTF8);
        support.add(new MediaType("text", "html", StandardCharsets.UTF_8));
        support.add(new MediaType("application","x-www-form-urlencoded"));
        messageConverter.setSupportedMediaTypes(support);
        converters.add(messageConverter);
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setSupportedMediaTypes(support);
        converters.add(jackson2HttpMessageConverter);
    }
}
