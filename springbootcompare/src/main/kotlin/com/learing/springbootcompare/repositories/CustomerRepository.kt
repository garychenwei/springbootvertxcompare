package com.learing.springbootcompare.repositories

import com.learing.springbootcompare.entities.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by chenweijiang on 2017/7/11.
 */
@Repository
interface CustomerRepository:JpaRepository<Customer,Long> {
    fun findByName(name:String):List<Customer>
}