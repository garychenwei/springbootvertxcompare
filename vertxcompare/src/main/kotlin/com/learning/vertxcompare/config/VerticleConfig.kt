package com.learning.vertxcompare.config

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import io.vertx.core.Vertx
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by chenweijiang on 2017/7/12.
 */
//@Configuration
//@ComponentScan
//class VerticleConfig {
////    private var vertx:Vertx?=null
////
////    @Bean
////    fun getVertxInstance(): Vertx?{
////        if(this.vertx==null){
////            this.vertx = Vertx.vertx()
////        }
////        return vertx
////    }
//
//    @Bean
//    fun objectMapper(): ObjectMapper {
//        return ObjectMapper(JsonFactory())
//    }
//}