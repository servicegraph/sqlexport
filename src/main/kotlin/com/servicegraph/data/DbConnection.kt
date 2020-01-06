package com.servicegraph.data

data class DbConnection (
    var name: String,
    var url: String,
    var user: String,
    var password: String,
    var pageSize: Int,
    var pageAdjusterType: PageAdjusterType
) {
    constructor(): this("", "", "", "", 0, PageAdjusterType.MYSQL)
}
