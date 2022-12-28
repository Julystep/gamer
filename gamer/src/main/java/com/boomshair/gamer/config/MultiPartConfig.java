package com.boomshair.gamer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @Date: 2022/5/9
 * @Description:
 */
@Configuration
public class MultiPartConfig {

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(300 * 1024 * 1024); //最大内存大小
        multipartResolver.setMaxInMemorySize(1024); //上传文件最小限制
        return multipartResolver;
    }

}
