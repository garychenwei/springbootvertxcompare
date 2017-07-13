package com.learning.vertxcompare.verticles.services.impl

import com.learning.vertxcompare.entities.Customer
import com.learning.vertxcompare.services.CustomerService
import com.learning.vertxcompare.verticles.services.CustomerAsnycService
import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by chenweijiang on 2017/7/13.
 */
@Component
class CustomerAsnycServiceImpl:CustomerAsnycService {

    @Autowired
    lateinit var customerService:CustomerService

    override fun find(name: String, handler: Handler<AsyncResult<List<Customer>>>) {
        val result:List<Customer> = customerService.findByName(name)
        Future.succeededFuture(result).setHandler(handler)
    }
}