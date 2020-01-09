package com.servicegraph.data

import com.servicegraph.pageAdjuster.SqlPageAdjuster

data class DbConnection (
    var name: String,
    var url: String,
    var user: String,
    var password: String,
    var pageSize: Int,
    var pageAdjusterType: SqlPageAdjuster.PageAdjusterType
) {
    constructor(): this("", "", "", "", 0, SqlPageAdjuster.PageAdjusterType.MYSQL)
}
