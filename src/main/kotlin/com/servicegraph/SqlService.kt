package com.servicegraph

import com.servicegraph.data.DbConnection
import com.servicegraph.data.DbQuery
import com.servicegraph.pageAdjuster.SqlPageAdjuster
import java.sql.DriverManager

object SqlService {
    fun executeSql(dbConnection: DbConnection, dbQuery: DbQuery, pageStart: Int = 0, pageEnd: Int = 1000): List<HashMap<String, String>>? {
        var sql = ""
        val result = ArrayList<HashMap<String, String>>()
        var rowResult: HashMap<String, String>
        var columns = ArrayList<String>()

        if(dbQuery.paged) {
            sql = (SqlPageAdjuster.SQL_PAGE_ADJUSTER_MAP[dbConnection.pageAdjusterType] ?: error("")).adjustSqlToPaged(sql)
        }

        var c = DriverManager.getConnection(dbConnection.url, dbConnection.user, dbConnection.password)
        c.isReadOnly = true
        var rs = c.createStatement().executeQuery(sql)

        // Get Column name
        for (i in 0 .. rs.metaData.columnCount){
            columns.add(rs.metaData.getColumnName(i));
        }

        if(rs.first()){
            do {
                rowResult = HashMap()
                columns.forEach {
                    rowResult[it] = rs.getString(it)
                }
            } while (rs.next())
        }

        return result
    }
}
