package com.proxbook.finder;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    MariaDBContainer<?> mariaDBContainer(){
        return new MariaDBContainer<>(DockerImageName.parse("mariadb:latest"))
                .withDatabaseName("test")
                .withUsername("test")
                .withPassword("test");
    }
}
