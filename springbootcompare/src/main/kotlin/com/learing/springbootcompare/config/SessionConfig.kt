package com.learing.springbootcompare.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration
import redis.clients.jedis.JedisPoolConfig

/**
 * Created by chenweijiang on 2017/7/11.
 */
@Configuration
@EnableRedisHttpSession
open class SessionConfig:RedisHttpSessionConfiguration() {

    @Autowired
    var redisProperties: RedisProperties?=null

    @Bean
    open fun redisConnectionFactory(): RedisConnectionFactory{
        val config :JedisPoolConfig = JedisPoolConfig()
        if(redisProperties!!.pool!=null){
            val pool:RedisProperties.Pool = redisProperties!!.pool
            config.maxIdle =pool.maxIdle
            config.minIdle = pool.minIdle
            config.maxTotal = pool.maxActive
            config.maxWaitMillis = pool.maxWait.toLong()
            config.minEvictableIdleTimeMillis = 300000
            config.numTestsPerEvictionRun = 3
            config.timeBetweenEvictionRunsMillis = 60000
        }

        val factory:JedisConnectionFactory = JedisConnectionFactory(config)
        factory.hostName = redisProperties!!.host
        factory.port = redisProperties!!.port
        return factory
    }
}