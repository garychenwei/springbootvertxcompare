package com.learning.vertxcompare.verticles.factory

import io.vertx.core.Verticle
import io.vertx.core.Vertx
import io.vertx.core.spi.VerticleFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * Created by chenweijiang on 2017/7/12.
 */
@Component
class SpringVerticleFactory:VerticleFactory,ApplicationContextAware {

    private var applicationContext:ApplicationContext?=null

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    private val PREFIX:String = "java-spring"

    override fun blockingCreate(): Boolean {
        return true
    }

    override fun createVerticle(verticleName: String?, classLoader: ClassLoader?): Verticle {
        val clazz:String = VerticleFactory.removePrefix(verticleName)
        val verticle: Verticle = applicationContext!!.getBean(Class.forName(clazz)) as Verticle
        return verticle
    }


    override fun prefix(): String {
        return PREFIX
    }

}