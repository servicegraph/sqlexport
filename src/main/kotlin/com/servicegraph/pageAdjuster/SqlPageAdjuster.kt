package com.servicegraph.pageAdjuster

interface SqlPageAdjuster {
    fun adjustSqlToPaged(sql: String, pageSortColumn: String, pageStart: Int, pageEnd: Int): String
    fun pagingColumn(): String

    enum class PageAdjusterType {
        MYSQL, ORACLE, MSSQL, POSTGRE
    }

    companion object {
        val SQL_PAGE_ADJUSTER_MAP: Map<PageAdjusterType, SqlPageAdjuster> = mapOf(
            PageAdjusterType.MYSQL to MySqlPageAdjuster(),
            PageAdjusterType.ORACLE to OracleSqlPageAdjuster(),
            PageAdjusterType.MSSQL to MSSqlPageAdjuster(),
            PageAdjusterType.POSTGRE to PostgrePageAdjuster()
        )
    }
}
