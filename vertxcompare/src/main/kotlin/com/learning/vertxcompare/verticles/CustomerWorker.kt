package com.learning.vertxcompare.verticles

import com.fasterxml.jackson.databind.ObjectMapper
import com.learning.vertxcompare.services.CustomerService
import com.learning.vertxcompare.verticles.services.CustomerAsnycService
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.eventbus.DeliveryOptions
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.eventbus.Message
import io.vertx.core.json.JsonArray
import io.vertx.serviceproxy.ProxyHandler
import io.vertx.serviceproxy.ProxyHelper
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * Created by chenweijiang on 2017/7/11.
 * worker consume the event bus.
 * receive message from event bus and call the db and reply
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class CustomerWorker :AbstractVerticle() {
    private val LOG:Logger = LoggerFactory.getLogger(CustomerWorker::class.java)

    @Autowired
    lateinit var customerService: CustomerService

    override fun start() {
        val consumer: MessageConsumer<Any> = vertx.eventBus().consumer("com.learning.vertxcompare.customer")
        consumer.handler(this::handleMesssage)
        LOG.error("worker is start up sucess")
    }

    fun handleMesssage(msg:Message<Any>){
        val method = msg.headers().get("method")
        val deOpt:DeliveryOptions = DeliveryOptions()
        if("find".equals(method)){

            val name:String = msg.body() as String
            val result = customerService.findByName(name)
            val json:JsonArray = JsonArray(result)
            msg.reply(json.encodePrettily(),deOpt)
        }
    }


}