package com.servicegraph.pageAdjuster

class MySqlPageAdjuster: SqlPageAdjuster {
    override fun adjustSqlToPaged(sql: String, pageSortColumn: String, pageStart: Int, pageEnd: Int) = "$sql LIMIT $pageStart TO $pageEnd ORDER BY $pageSortColumn"
    override fun pagingColumn() = ""
}
