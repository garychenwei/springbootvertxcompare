package com.learning.vertxcompare.entities

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
    var createdDate: Date?=null
    var updatedDate: Date?=null
}