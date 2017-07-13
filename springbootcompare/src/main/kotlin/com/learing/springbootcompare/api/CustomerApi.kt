package com.learing.springbootcompare.api

import com.learing.springbootcompare.entities.Customer
import com.learing.springbootcompare.services.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * Created by chenweijiang on 2017/7/11.
 */
@RestController
@RequestMapping("customer")
class CustomerApi {
    @Autowired
    lateinit var customerService:CustomerService

    @RequestMapping("all")
    fun getAll():List<Customer>{
       return customerService.getAllCustomer()
    }

    @RequestMapping("add")
    fun add(@RequestBody customer:Customer):String{
        customer.createdDate = Date()
        customer.updatedDate = Date()
        customerService.addCustomer(customer)
        return "seccuss"
    }

    @RequestMapping("find")
    fun findByName(name:String):List<Customer>{
        return customerService.findByName(name)
    }
}