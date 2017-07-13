package com.learning.vertxcompare.api

import com.learning.vertxcompare.api.Handler.RestHandler
import com.learning.vertxcompare.verticles.CustomerWorker
import io.vertx.core.*
import io.vertx.core.eventbus.DeliveryOptions
import io.vertx.core.eventbus.Message
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * Created by chenweijiang on 2017/7/11.
 */
//@Component
//class CustomerVerticles{
//    @Autowired
//    lateinit var vertx:Vertx
//
//    @Autowired
//    lateinit var restHandler:RestHandler
//
//    @Autowired
//    lateinit var customerWorker:CustomerWorker
//
//    @PostConstruct
//    fun start(){
//        val deployOption:DeploymentOptions = DeploymentOptions()
//                .setWorker(true).setInstances(4)
//        vertx.deployVerticle(customerWorker,deployOption)
//
//        vertx.deployVerticle(customerWorker,
//                deployOption,
//                object: Handler<AsyncResult<String>>{
//                    override fun handle(event: AsyncResult<String>?) {
//                        if(event!!.succeeded()){
//                            val router:Router = Router.router(vertx)
//                            router.route().handler(BodyHandler.create())
//                            var devOption:DeliveryOptions = DeliveryOptions().setSendTimeout(2000)
//
//                            router.get("/customer/find").handler({event ->
//                                var name:String = event.request().getParam("name")
//                                devOption.addHeader("method","find")
//                                devOption.addHeader("name",name)
//                                vertx.eventBus().send("com.learning.vertxcompare.customer",null,devOption,
//                                        object :Handler<AsyncResult<Message<Any>>>{
//                                            override fun handle(reply: AsyncResult<Message<Any>>) {
//                                                handleReply(reply,event)
//                                            }
//                                        })
//                            })
//
//                            vertx.createHttpServer().requestHandler(router::accept).listen(9001)
//                            println("create service success")
//                        } else {
//                            println("error")
//                        }
//                    }
//                }
//        )
//        println("go to start")
//    }
//
//    fun handleReply(reply:AsyncResult<Message<Any>>?, context:RoutingContext){
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
//
//}