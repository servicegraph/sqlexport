package com.servicegraph.pageAdjuster

class OracleSqlPageAdjuster: SqlPageAdjuster {
    override fun adjustSqlToPaged(sql: String, pageStart: Int, pageEnd: Int) = "SELECT * FROM (SELECT ROWNUM AS ROWNUM_CAL, T1.* FROM ($sql) T1) WHERE ROWNUM_CAL BETWEEN $pageStart AND $pageEnd"
}
