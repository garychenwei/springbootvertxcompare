package com.learning.vertxcompare.verticles

import com.learning.vertxcompare.entities.Customer
import com.learning.vertxcompare.verticles.services.CustomerAsnycService
import io.vertx.core.AbstractVerticle
import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.eventbus.DeliveryOptions
import io.vertx.core.eventbus.Message
import io.vertx.core.http.HttpServer
import io.vertx.core.json.JsonArray
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.serviceproxy.ProxyHelper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * Created by chenweijiang on 2017/7/12.
 * use communicate to work with evenbus
 * could not use service proxy it support from java to other language
 */
@Component
class CustomerApi : AbstractVerticle() {

    private val log:Logger = LoggerFactory.getLogger(CustomerApi::class.java)

    private var customerAsyncService:CustomerAsnycService?=null

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)
        vertx.createHttpServer().requestHandler(genRouter()::accept).listen(9001,{event: AsyncResult<HttpServer>? ->
            if(event!!.succeeded()){
                log.error("start up 9001 success")
            } else {
                log.error("start up 9001 fail")
            }
        })
    }

    fun genRouter():Router{
        val router:Router = Router.router(vertx)
        router.route().handler(BodyHandler.create())
        router.get("/customer/find").handler(this::findCustomer)
        return router
    }

    fun findCustomer(routingContext:RoutingContext){
        var name:String = routingContext.request().getParam("name")
        //send message to eventbus
        var devOption: DeliveryOptions = DeliveryOptions().setSendTimeout(2000)
        devOption.addHeader("method","find")
        vertx.eventBus().send("com.learning.vertxcompare.customer",name,devOption,
                {rl:AsyncResult<Message<String>>->
            if(rl.succeeded()){
                val result:String = rl.result().body() as String
                routingContext.response().end(result)
            } else {
                routingContext.fail(rl!!.cause())
            }
        })

    }

}