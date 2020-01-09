package com.servicegraph.pageAdjuster

class MySqlPageAdjuster: SqlPageAdjuster {
    override fun adjustSqlToPaged(sql: String, pageStart: Int, pageEnd: Int) = "$sql LIMIT $pageStart TO $pageEnd"
}
