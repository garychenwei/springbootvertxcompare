package com.learning.vertxcompare.api.Handler

import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import org.springframework.stereotype.Component

/**
 * Created by chenweijiang on 2017/7/11.
 */
@Component
class RestHandler:Handler<AsyncResult<String>> {



    override fun handle(event: AsyncResult<String>?) {

    }
}