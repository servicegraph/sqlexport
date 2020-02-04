package com.servicegraph

import com.servicegraph.fileExporter.FileExporter
import org.slf4j.LoggerFactory
import java.sql.DriverManager

fun main(args: Array<String>){
    val logger = LoggerFactory.getLogger("Batch-Script")

    if(args.isEmpty()){
        print("No param available")
    } else {
        when (args[0]) {
            "--export" -> {
                logger.info("Starting of SQL-Export ${args[1]} for Environemt ${args[3]}")

                ExportService.export(args[1], args[3], FileExporter.FileExportType.CSV)
                ExportService.finalize()

                logger.info("Finishing of SQL-Export ${args[1]} for Environemt ${args[3]}")
            }
            "--exportMulti" -> {
                logger.info("Starting of Multi-SQL-Export ${args[1]} for Environemt ${args[3]}")

                ExportService.exportMulti(args[1], args[3])
                ExportService.finalize()

                logger.info("Finishing of Multi-SQL-Export ${args[1]} for Environemt ${args[3]}")
            }
            else -> logger.error("Parameter not valid")
        }
    }
}
