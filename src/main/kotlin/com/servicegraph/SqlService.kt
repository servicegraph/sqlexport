package com.servicegraph

import com.servicegraph.data.DbConnection
import com.servicegraph.data.DbQuery
import com.servicegraph.data.DbResult
import com.servicegraph.pageAdjuster.SqlPageAdjuster
import java.sql.Connection
import java.sql.DriverManager

object SqlService {
    private val dbConnectionsCache = HashMap<String, Connection>()

    fun execute(dbConnection: DbConnection, dbQuery: DbQuery): Boolean {
        return this.getConnection(dbConnection).createStatement().execute(dbQuery.sql)
    }

    fun executeQuery(dbConnection: DbConnection, dbQuery: DbQuery, page: Int? = null): DbResult {
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

        try {
            var rs = getConnection(dbConnection).createStatement().executeQuery(sql)

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

        }

        return DbResult(dbQuery.paged, page, pageStart, pageEnd, header, data)
    }

    private fun getConnection(dbConnection: DbConnection): Connection {
        return this.getConnection(dbConnection.url, dbConnection.user, dbConnection.password)
    }

    private fun getConnection(url: String, user: String, password: String): Connection {
        var c: Connection

        if(dbConnectionsCache[url] == null){
            c = DriverManager.getConnection(url, user, password)
            dbConnectionsCache.put(url, c)
        } else {
            c = dbConnectionsCache[url]!!
        }

        return c
    }

    fun closeConnections(){
        dbConnectionsCache.keys.forEach {
            dbConnectionsCache[it]!!.close()
        }

        dbConnectionsCache.clear()
    }
}
