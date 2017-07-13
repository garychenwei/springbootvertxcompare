package com.learning.vertxcompare.services

import com.learing.vertxcompare.repositories.CustomerRepository
import com.learning.vertxcompare.entities.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by chenweijiang on 2017/7/11.
 */
@Service
@Transactional(readOnly = true)
open class CustomerService {

    @Autowired
    lateinit var customerDao:CustomerRepository

    fun getAllCustomer():List<Customer>{
        return customerDao.findAll()
    }

    @Transactional(readOnly = false)
    fun addCustomer(customer:Customer){
        customerDao.save(customer)
    }

    fun findByName(name:String):List<Customer>{
        return customerDao.findByName(name)
    }
}