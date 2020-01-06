package com.servicegraph

import com.servicegraph.data.FileExportType
import com.servicegraph.fileExporter.FileExporter

object ExportService {
    fun exportMulti(multiExportName: String, fileExportType: FileExportType = FileExportType.CSV){
        XmlConfigurationService.getMultiExport(multiExportName)!!.queries.forEach {
            export(it, fileExportType)
        }
    }

    fun export(dbQueryName: String, fileExportType: FileExportType = FileExportType.CSV) {
        val query = XmlConfigurationService.getDbQuery(dbQueryName)!!
        val connection = XmlConfigurationService.getDbConnection(query.connectionName)!!
        var pageStart: Int
        var pageEnd: Int = -1
        var fileExportResult = false

        if(query.paged){
            do {
                pageStart = pageEnd + 1
                pageEnd = pageStart + connection.pageSize - 1
                val pagedSqlQueryResult = SqlService.executeSql(connection, query, pageStart, pageEnd)

                if(pagedSqlQueryResult != null){
                    fileExportResult = (FileExporter.EXPORT_TYPE_MAP[fileExportType] ?: error("No valid export type found")).exportToFile(pagedSqlQueryResult)
                }
            } while (pagedSqlQueryResult != null && fileExportResult)
        } else {
            val sqlQueryResult = SqlService.executeSql(connection, query)

            if(sqlQueryResult != null){
                val fileExportResult = (FileExporter.EXPORT_TYPE_MAP[fileExportType] ?: error("No valid export type found")).exportToFile(sqlQueryResult)
            }
        }
    }
}
