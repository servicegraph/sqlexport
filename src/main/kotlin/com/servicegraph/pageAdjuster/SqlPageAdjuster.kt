package com.servicegraph.pageAdjuster

interface SqlPageAdjuster {
    fun adjustSqlToPaged(sql: String, pageStart: Int, pageEnd: Int): String

    enum class PageAdjusterType {
        MYSQL, ORACLE
    }

    companion object {
        val SQL_PAGE_ADJUSTER_MAP: Map<PageAdjusterType, SqlPageAdjuster> = mapOf(
            PageAdjusterType.MYSQL to MySqlPageAdjuster(),
            PageAdjusterType.ORACLE to OracleSqlPageAdjuster()
        )
    }
}
