package com.learing.springbootcompare.services

import com.learing.springbootcompare.entities.Customer
import com.learing.springbootcompare.repositories.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
/**
 * Created by chenweijiang on 2017/7/11.
 */

@Service
@Transactional(readOnly = true)
class CustomerService {

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
        val result = customerDao.findByName(name)
        return result
    }
}