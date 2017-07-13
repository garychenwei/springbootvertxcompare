package com.learing.springbootcompare.entities

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.*
import javax.persistence.*

/**
 * Created by chenweijiang on 2017/7/11.
 */
@Entity
@Table(name = "t_customer")
class Customer {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    var id:Long?=-1
    var name:String?=null
    var sex:String?=null
    var hobby:String?=null
    var createdDate:Date?=null
    var updatedDate:Date?=null
}