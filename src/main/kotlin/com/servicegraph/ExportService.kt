package com.servicegraph

import com.servicegraph.data.DbQueryType
import com.servicegraph.data.FileExportSession
import com.servicegraph.fileExporter.FileExporter
import org.slf4j.LoggerFactory
import java.util.*

object ExportService {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun exportMulti(
        multiExportName: String,
        environment: String,
        fileExportType: FileExporter.FileExportType = FileExporter.FileExportType.CSV,
        exportSessionId: String = UUID.randomUUID().toString()
    ){
        XmlConfigurationService.getMultiExport(multiExportName)!!.queries.forEach {
            export(it, environment, fileExportType, exportSessionId)
        }
    }

    fun export(
        dbQueryName: String,
        environment: String,
        fileExportType: FileExporter.FileExportType = FileExporter.FileExportType.CSV,
        exportSessionId: String = UUID.randomUUID().toString()
    ): Boolean {
        logger.info("Starting of execution SQL ${dbQueryName}")

        val fileExporter = (FileExporter.EXPORT_TYPE_MAP[fileExportType] ?: error("No valid exporter for Export-Type found"))
        val query = XmlConfigurationService.getDbQuery(dbQueryName)?: error("Query has not been found")
        val connection = XmlConfigurationService.getDbConnection(query.connectionName, environment)?: error("Db-Connection has not been found")

        var fileExportResult: Boolean
        val fileExportSession = FileExportSession("export\\${environment}", exportFileName = query.exportFileName, exportSessionId = exportSessionId)

        if(query.paged){
            var page = 0

            if(!fileExporter.supportsPaging){
                error("The chosen file-exporter does not support paging and therefore the configuration seems wrong")
            }

            fileExporter.startExport(fileExportSession)

            do {
                val pagedSqlQueryResult = SqlService.executeQuery(connection, query, page)
                fileExportResult = fileExporter.exportData(pagedSqlQueryResult, fileExportSession)
                page += 1
            } while (pagedSqlQueryResult.data.size != 0 && fileExportResult)

            fileExporter.endExport(fileExportSession)

            fileExportResult = true
        } else {
            if(query.type == DbQueryType.EXECUTION){
                fileExportResult = SqlService.execute(connection, query)
            } else {
                val sqlQueryResult = SqlService.executeQuery(connection, query)
                fileExportResult = fileExporter.exportNonPaged(sqlQueryResult, fileExportSession)
            }
        }

        logger.info("Finishing of execution SQL ${dbQueryName}")
        return fileExportResult
    }

    fun finalize(){
        SqlService.closeConnections()
    }
}
