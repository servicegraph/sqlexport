package com.servicegraph.pageAdjuster

class PostgrePageAdjuster: SqlPageAdjuster {
    override fun adjustSqlToPaged(sql: String, pageSortColumn: String, pageStart: Int, pageEnd: Int) = "$sql LIMIT $pageStart TO $pageEnd"
    override fun pagingColumn() = ""
}
