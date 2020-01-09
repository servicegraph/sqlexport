package com.servicegraph.data

import kotlin.collections.ArrayList

data class DbResult (
    var paged: Boolean,
    var page: Int?,
    var pageStart: Int?,
    var pageEnd: Int?,
    var header: ArrayList<String>,
    val data: ArrayList<ArrayList<Any>>
) {
    fun addRow(line: ArrayList<Any>){
         this.data.add(line)
    }
}
