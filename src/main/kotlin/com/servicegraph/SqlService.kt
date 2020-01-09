package com.servicegraph

import com.servicegraph.data.DbConnection
import com.servicegraph.data.DbQuery
import com.servicegraph.data.DbResult
import com.servicegraph.pageAdjuster.SqlPageAdjuster
import java.sql.Connection
import java.sql.DriverManager

object SqlService {
    private val dbConnectionsCache = HashMap<String, Connection>()

    fun executeSql(dbConnection: DbConnection, dbQuery: DbQuery, page: Int?): DbResult {
        var sql: String
        var columnsCount: Int
        var rowResult: ArrayList<Any>
        var header = ArrayList<String>()
        var data = ArrayList<ArrayList<Any>>()
        var pageStart: Int? = null
        var pageEnd: Int? = null



       // Adjust sql to paged
        if(dbQuery.paged && page != null) {
            pageStart = page * dbConnection.pageSize
            pageEnd = (page + 1) * dbConnection.pageSize - 1

            sql = (SqlPageAdjuster.SQL_PAGE_ADJUSTER_MAP[dbConnection.pageAdjusterType] ?: error("")).adjustSqlToPaged(dbQuery.sql, pageStart, pageEnd)
        } else {
            sql = dbQuery.sql
        }

        // Build up connection
        var c: Connection? = null

        try {
            var c: Connection

            if(dbConnectionsCache[dbConnection.url] == null){
                c = DriverManager.getConnection(dbConnection.url, dbConnection.user, dbConnection.password)
                c.isReadOnly = true
                dbConnectionsCache.put(dbConnection.url, c)
            } else {
                c = dbConnectionsCache[dbConnection.url]!!
            }

            var rs = c.createStatement().executeQuery(sql)

            // Get Column name
            columnsCount = rs.metaData.columnCount
            for (i in 1 .. columnsCount){
                header.add(rs.metaData.getColumnName(i))
            }

            // Get rows
            while (rs.next()) {
                rowResult = ArrayList()
                for (i in 1 .. columnsCount){
                    rowResult.add(rs.getString(i))
                }
                data.add(rowResult)
            }
        } finally {
            c?.close()
        }

        return DbResult(dbQuery.paged, page, pageStart, pageEnd, header, data)
    }

    fun closeConnections(){
        dbConnectionsCache.keys.forEach {
            dbConnectionsCache[it]!!.close()
        }

        dbConnectionsCache.clear()
    }
}
