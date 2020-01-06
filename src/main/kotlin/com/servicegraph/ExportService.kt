package com.servicegraph

import com.servicegraph.data.DbResult
import com.servicegraph.data.FileExportType
import com.servicegraph.fileExporter.FileExporter

object ExportService {
    fun exportMulti(multiExportName: String, fileExportType: FileExportType = FileExportType.CSV){
        XmlConfigurationService.getMultiExport(multiExportName)!!.queries.forEach {
            export(it, fileExportType)
        }
    }

    fun export(dbQueryName: String, fileExportType: FileExportType = FileExportType.CSV) {
        val query = XmlConfigurationService.getDbQuery(dbQueryName)?: error("Query has not been found")
        val connection = XmlConfigurationService.getDbConnection(query.connectionName)?: error("Db-Connection has not been found")
        var pageStart: Int
        var pageEnd: Int = -1
        var fileExportResult:Boolean
        val dbResult = DbResult()

        if(query.paged){
            do {
                pageStart = pageEnd + 1
                pageEnd = pageStart + connection.pageSize - 1
                val pagedSqlQueryResult = SqlService.executeSql(connection, query, pageStart, pageEnd, dbResult)
                fileExportResult = (FileExporter.EXPORT_TYPE_MAP[fileExportType] ?: error("No valid export type found")).exportToFile(pagedSqlQueryResult)
            } while (pagedSqlQueryResult != null && fileExportResult)
        } else {
            val sqlQueryResult = SqlService.executeSql(connection, query, dbResult = dbResult)
            val fileExportResult = (FileExporter.EXPORT_TYPE_MAP[fileExportType] ?: error("No valid export type found")).exportToFile(sqlQueryResult)
        }
    }
}