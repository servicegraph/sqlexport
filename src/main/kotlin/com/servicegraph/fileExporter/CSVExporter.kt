package com.servicegraph.fileExporter

import com.servicegraph.data.DbResult
import com.servicegraph.data.FileExportSession
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.FileWriter

class CSVExporter: FileExporter(true) {
    override fun startExport(fileExportSession: FileExportSession): Boolean {
        val out = FileWriter(fileExportSession.exportFolder + "/" + fileExportSession.exportSessionId + ".csv")
        fileExportSession.sessionExportPointer = CSVPrinter(out, CSVFormat.DEFAULT)

        return true
    }

    override fun exportData(data: DbResult, fileExportSession: FileExportSession): Boolean {
        var csvPrinter = (fileExportSession.sessionExportPointer as CSVPrinter)
        csvPrinter.printRecords(data.data)

        return true
    }

    override fun endExport(fileExportSession: FileExportSession): Boolean {
        (fileExportSession.sessionExportPointer as CSVPrinter).close()

        return true
    }
}