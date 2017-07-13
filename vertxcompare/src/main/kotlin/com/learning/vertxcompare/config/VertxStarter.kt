package com.learning.vertxcompare.config

import com.learning.vertxcompare.verticles.CustomerApi
import com.learning.vertxcompare.verticles.factory.SpringVerticleFactory
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import org.springframework.context.ApplicationContext


/**
 * Created by chenweijiang on 2017/7/12.
 */
//@Component
//class VertxStarter {
//    @Autowired
//    lateinit var vertx: Vertx
//
//    @Autowired
//    private val context: ApplicationContext? = null
//
////    @Autowired
////    lateinit var customerWorker: CustomerWorker
//    @Autowired
//    lateinit var customerMVCVerticles: CustomerApi
//
//    @PostConstruct
//    fun start(){
//        SpringVerticleFactory.setApplicationContext(context!!)
//        //deploy worker verticle
//        val deployOption: DeploymentOptions = DeploymentOptions()
//                .setWorker(true)
//                .setInstances(4)
//        vertx.deployVerticle("java-spring:com.learning.vertxcompare.verticles.CustomerWorker",deployOption)
//        vertx.deployVerticle(customerMVCVerticles)
//    }
//}