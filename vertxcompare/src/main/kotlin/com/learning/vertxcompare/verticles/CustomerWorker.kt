package com.learning.vertxcompare.verticles

import com.fasterxml.jackson.databind.ObjectMapper
import com.learning.vertxcompare.services.CustomerService
import com.learning.vertxcompare.verticles.services.CustomerAsnycService
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.eventbus.DeliveryOptions
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.eventbus.Message
import io.vertx.serviceproxy.ProxyHandler
import io.vertx.serviceproxy.ProxyHelper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * Created by chenweijiang on 2017/7/11.
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class CustomerWorker :AbstractVerticle() {

//    @Autowired
//    lateinit var objectMapper:ObjectMapper
//    @Autowired
//    lateinit var customerService:CustomerService

    @Autowired
    lateinit var customerAsyncService:CustomerAsnycService

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)
        ProxyHelper.registerService(CustomerAsnycService::class.java,vertx,customerAsyncService,
                CustomerAsnycService.ADDRESS).completionHandler({completionHandler->
            if(completionHandler.succeeded()){
                startFuture!!.complete()
            } else {
                startFuture!!.fail(completionHandler.cause())
            }
        })
    }

//    override fun start() {
//        super.start()
//
////        val consumer:MessageConsumer<Any> = vertx.eventBus().consumer("com.learning.vertxcompare.customer")
////        consumer.handler(this::handleDatabaseRequest)
//    }

//    fun handleDatabaseRequest(msg:Message<Any>){
//        val method = msg.headers().get("method")
//        val deOpt:DeliveryOptions = DeliveryOptions()
//        if("find".equals(method)){
//            var name:String = msg.headers().get("name")
//            var result = customerService.findByName(name)
//            val reply = objectMapper.writeValueAsString(result)
//            msg.reply(reply,deOpt)
//        }
//    }
}