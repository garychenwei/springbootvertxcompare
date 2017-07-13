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
import org.springframework.stereotype.Component

/**
 * Created by chenweijiang on 2017/7/12.
 */
@Component
class CustomerApi : AbstractVerticle() {

    private var customerAsyncService:CustomerAsnycService?=null

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)
        customerAsyncService = ProxyHelper.createProxy(CustomerAsnycService::class.java,vertx,CustomerAsnycService.ADDRESS)
        vertx.createHttpServer().requestHandler(genRouter()::accept).listen(9001,{event: AsyncResult<HttpServer>? ->
            if(event!!.succeeded()){
                startFuture!!.complete()
            } else {
                startFuture!!.fail(event!!.cause())
            }
        })
    }

    fun genRouter():Router{
        val router:Router = Router.router(vertx)
        router.route().handler(BodyHandler.create())
        router.get("/customer/get").handler(this::findCustomer)
        return router
    }

    fun findCustomer(routingContext:RoutingContext){
        var name:String = routingContext.request().getParam("name")
        customerAsyncService!!.find(name,object :Handler<AsyncResult<List<Customer>>>{
            override fun handle(event: AsyncResult<List<Customer>>?) {
                if(event!!.succeeded()){
                    val result:List<Customer> = event.result()
                    val json:JsonArray = JsonArray(result)
                    routingContext.response().end(json.encodePrettily())
                } else {
                    routingContext.fail(event!!.cause())
                }
            }
        })
    }

    //    override fun start() {
//        super.start()
//        vertx.createHttpServer().requestHandler(router()::accept).listen(9001)
//    }
//
//    fun router():Router{
//        val router:Router = Router.router(vertx)
//        router.route().handler(BodyHandler.create())
//        router.route("/customer/find").handler({event ->
//            //send to event bus
//            var devOption: DeliveryOptions = DeliveryOptions().setSendTimeout(2000)
//            var name:String = event.request().getParam("name")
//            devOption.addHeader("method","find")
//            devOption.addHeader("name",name)
//            vertx.eventBus().send("com.learning.vertxcompare.customer",null,devOption,
//                    object :Handler<AsyncResult<Message<Any>>>{
//                        override fun handle(reply: AsyncResult<Message<Any>>?) {
//                            handleReply(reply,event)
//                        }
//                    })
//        })
//        return router
//    }
//
//
//
//    fun handleReply(reply:AsyncResult<Message<Any>>?, context: RoutingContext){
//        if(reply!!.succeeded()){
//            var replyMsg = reply.result()
//            context.response()
//                    .putHeader("Content-Type","application/json")
//                    .end(replyMsg.body().toString())
//        } else {
//            context.response()
//                    .setStatusCode(500)
//                    .setStatusMessage("error")
//                    .end("error")
//        }
//    }
}