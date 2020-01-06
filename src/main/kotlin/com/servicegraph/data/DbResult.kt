package com.servicegraph.data

import java.util.*
import kotlin.collections.ArrayList

data class DbResult (
    var page: Int = 1,
    var exportSessionId: String = UUID.randomUUID().toString(),
    var header: ArrayList<String> = ArrayList(),
    val result: ArrayList<ArrayList<Any>> = ArrayList()
) {
    fun addRow(line: ArrayList<Any>){
         this.result.add(line)
    }
}