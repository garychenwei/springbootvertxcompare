package com.learning.vertxcompare.verticles.services

import com.learning.vertxcompare.entities.Customer
import io.vertx.codegen.annotations.ProxyGen
import io.vertx.codegen.annotations.VertxGen
import io.vertx.core.AsyncResult
import io.vertx.core.Handler

/**
 * Created by chenweijiang on 2017/7/13.
 */
@ProxyGen
@VertxGen
interface CustomerAsnycService {


    fun find(name:String,handler:Handler<AsyncResult<List<Customer>>>)

    companion object{
        val ADDRESS:String = CustomerAsnycService::class.java.name
    }
}