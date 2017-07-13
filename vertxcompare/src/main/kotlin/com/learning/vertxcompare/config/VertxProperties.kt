package com.learning.vertxcompare.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Created by chenweijiang on 2017/7/11.
 */
@Component
@ConfigurationProperties("vertx")
open class VertxProperties{
    var port:Int = 8080

}