package com.example.watchvideo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.example.watchvideo.Model")
@EnableJpaRepositories("com.example.watchvideo.DAO")
@DependsOn("embeddedMysqlServer")
public class WatchVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatchVideoApplication.class, args);
    }

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        // 修改内置的 tomcat 容器配置
        TomcatServletWebServerFactory tomcatServlet = new TomcatServletWebServerFactory();
        tomcatServlet.addConnectorCustomizers(
                (TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "[]{}")
        );
        return tomcatServlet;
    }

}