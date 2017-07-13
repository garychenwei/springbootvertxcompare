package com.learning.vertxcompare

import io.vertx.core.Vertx
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.learning.vertxcompare.config.VertxProperties
import com.learning.vertxcompare.verticles.CustomerApi
import com.learning.vertxcompare.verticles.CustomerWorker
import com.learning.vertxcompare.verticles.factory.SpringVerticleFactory
import io.vertx.core.AsyncResult
import io.vertx.core.DeploymentOptions
import io.vertx.core.VertxOptions
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Scope
import org.springframework.context.event.EventListener
import sun.java2d.SurfaceDataProxy
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean


/**
 * Created by chenweijiang on 2017/7/11.
 */

@SpringBootApplication
@EnableJpaRepositories(basePackages = arrayOf("com.learing.vertxcompare.repositories"))
//@EnableConfigurationProperties(VertxProperties::class)
@EnableTransactionManagement
//@ComponentScan(basePackages = arrayOf("com.learing.vertxcompare"))
class Application{

    val logger:Logger = LoggerFactory.getLogger(Application::class.java)
    companion object{
        @JvmStatic fun main(args:Array<String>){
            SpringApplication.run(Application::class.java,*args)
        }
    }

    @Autowired
    private lateinit var springVerticleFactory:SpringVerticleFactory

//    private var vertx:Vertx?=null
//
//    @Bean
//    @Scope(BeanDefinition.SCOPE_SINGLETON)
//    fun getVertxInstance(): Vertx?{
//        if(this.vertx==null){
//            this.vertx = Vertx.vertx()
//        }
//        return vertx
//    }

//    @Bean
//    fun objectMapper(): ObjectMapper {
//        return ObjectMapper(JsonFactory())
//    }


    @EventListener
    fun deployVerticles(event:ApplicationReadyEvent){
        val vertx:Vertx = Vertx.vertx(VertxOptions().setWorkerPoolSize(6))
        vertx.registerVerticleFactory(springVerticleFactory)
        val deployLatch:CountDownLatch = CountDownLatch(2)
        val failed:AtomicBoolean = AtomicBoolean(false)
        val apiVerticleName = springVerticleFactory.prefix()+":"+CustomerApi::class.java.name
        vertx.deployVerticle(apiVerticleName,{ ar: AsyncResult<String>->
            if(ar.failed()){
                println(ar.cause())
                logger.error(ar.cause().toString())
                failed.compareAndSet(false,true)
            } else {
                logger.debug("start up $apiVerticleName success")
            }
            deployLatch.countDown()
        })
        val deployOptoin:DeploymentOptions = DeploymentOptions().setInstances(4).setWorker(true)
        val workerVerticleName = springVerticleFactory.prefix()+":"+CustomerWorker::class.java.name
        vertx.deployVerticle(workerVerticleName,deployOptoin,{ ar: AsyncResult<String>->
            if(ar.failed()){
                println(ar.cause())
                logger.error(ar.cause().toString())
                failed.compareAndSet(false,true)
            }else {
                logger.debug("start up $workerVerticleName success")
            }
            deployLatch.countDown()
        })
//        if(!deployLatch.await(30,TimeUnit.SECONDS)){
//            throw RuntimeException("start up time out")
//        } else if(failed.get()){
//            throw RuntimeException("start up fail")
//        }
    }
}