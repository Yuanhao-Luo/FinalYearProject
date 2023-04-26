package com.example.watchvideo;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;
import com.wix.mysql.distribution.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.wix.mysql.ScriptResolver.classPathScript;

@Configuration
public class EmbeddedMysqlConfig {

    @Bean(destroyMethod = "stop")
    public EmbeddedMysql embeddedMysqlServer() throws IOException {
        MysqldConfig config = MysqldConfig.aMysqldConfig(Version.v5_7_latest)
                .withCharset(Charset.UTF8)
                .withPort(3305)
                .withUser("user", "uiop435AFH")
                .build();

        EmbeddedMysql mysqld = EmbeddedMysql.anEmbeddedMysql(config)
                .addSchema("fyp")
                .start();

//        // Read SQL script file contents
//        String sqlScript = Files.lines(Paths.get("F:\\program\\java\\WatchVideo\\database.sql"), java.nio.charset.Charset.forName("UTF-8")).collect(Collectors.joining("\n"));
//
//        // Execute SQL script
//        mysqld.executeScripts(sqlScript);


        return mysqld;
    }
}