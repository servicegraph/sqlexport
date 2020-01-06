package com.servicegraph

import com.servicegraph.data.DbConnection
import com.servicegraph.data.DbQuery
import com.servicegraph.data.DbResult
import com.servicegraph.pageAdjuster.SqlPageAdjuster
import java.sql.DriverManager

object SqlService {
    fun executeSql(dbConnection: DbConnection, dbQuery: DbQuery, pageStart: Int = 0, pageEnd: Int = 1000, dbResult: DbResult): DbResult {
        var sql = ""
        var columnsCount: Int
        var rowResult: ArrayList<Any>
        var header = ArrayList<String>()

        if(dbQuery.paged) {
            sql = (SqlPageAdjuster.SQL_PAGE_ADJUSTER_MAP[dbConnection.pageAdjusterType] ?: error("")).adjustSqlToPaged(sql)
        } else {
            sql = dbQuery.sql
        }

        var c = DriverManager.getConnection(dbConnection.url, dbConnection.user, dbConnection.password)
        c.isReadOnly = true
        var rs = c.createStatement().executeQuery(sql)

        // Get Column name
        columnsCount = rs.metaData.columnCount
        for (i in 1 .. columnsCount){
            header.add(rs.metaData.getColumnName(i))
        }
        dbResult.header = header

        // Get rows
        while (rs.next()) {
            rowResult = ArrayList()
            for (i in 1 .. columnsCount){
                rowResult.add(rs.getString(i))
            }
            dbResult.addRow(rowResult)
        }

        return dbResult
    }
}
