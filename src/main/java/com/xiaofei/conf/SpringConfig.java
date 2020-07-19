package com.xiaofei.conf;

import com.xiaofei.converter.MapJackson2HttpMessageConverter;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiaofei
 * @Classname SrpingConfig
 * @Description 个人项目，仅供学习
 * @Created by xiaofei
 */
@Configuration
@ComponentScan("com.xiaofei")
@PropertySource({"classpath:jedis.properties","classpath:jdbc.properties"})
@Import({JedisConfig.class,DaoConfig.class})
public class SpringConfig {

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MapJackson2HttpMessageConverter());
        return restTemplate;
    }


}
