package com.learing.springbootcompare

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ImportResource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import javax.sql.DataSource

/**
 * Created by chenweijiang on 2017/7/11.
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = arrayOf("com.learing.springbootcompare.repositories"))
@EnableTransactionManagement

open class Application {
    companion object{
        @JvmStatic fun main(args:Array<String>){
            SpringApplication.run(Application::class.java,*args)
        }
    }

}