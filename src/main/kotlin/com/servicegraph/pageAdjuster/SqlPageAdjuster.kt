package com.servicegraph.pageAdjuster

import com.servicegraph.data.PageAdjusterType

interface SqlPageAdjuster {
    fun adjustSqlToPaged(sql: String, pageStart: Int, pageEnd: Int): String

    companion object {
        val SQL_PAGE_ADJUSTER_MAP: Map<PageAdjusterType, SqlPageAdjuster> = mapOf(PageAdjusterType.MYSQL to MySqlPageAdjuster())
    }
}
