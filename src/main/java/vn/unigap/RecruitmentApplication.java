package vn.unigap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(basePackages = "vn.unigap.api.repository.jpa")
@EnableMongoRepositories(basePackages = "vn.unigap.api.repository.mongodb")
public class RecruitmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentApplication.class, args);
    }

}
