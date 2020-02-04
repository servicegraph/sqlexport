package com.servicegraph.pageAdjuster

class MSSqlPageAdjuster: SqlPageAdjuster {
    override fun adjustSqlToPaged(sql: String, pageSortColumn: String, pageStart: Int, pageEnd: Int) = "SELECT * FROM (SELECT ROW_NUMBER() OVER ( ORDER BY OrderDate ) AS RowNum, * FROM ($sql) ORDER BY $pageSortColumn) AS T1 WHERE RowNum >= $pageStart AND RowNum < $pageEnd ORDER BY RowNum"
    override fun pagingColumn() = "RowNum"
}
