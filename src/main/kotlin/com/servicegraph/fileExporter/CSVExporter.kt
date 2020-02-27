package com.servicegraph.fileExporter

import com.servicegraph.data.DbResult
import com.servicegraph.data.FileExportSession
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets


class CSVExporter: FileExporter(true) {
    override fun startExport(fileExportSession: FileExportSession): Boolean {
        val out = OutputStreamWriter(FileOutputStream(fileExportSession.exportFolder + "/" + fileExportSession.exportFileName), fileExportSession.charset)
        fileExportSession.sessionExportPointer = CSVPrinter(out, CSVFormat.DEFAULT.withDelimiter(';'))

        return true
    }

    override fun exportData(data: DbResult, fileExportSession: FileExportSession): Boolean {
        val csvPrinter = (fileExportSession.sessionExportPointer as CSVPrinter)
        csvPrinter.printRecord(data.header)
        csvPrinter.printRecords(data.data)

        return true
    }

    override fun endExport(fileExportSession: FileExportSession): Boolean {
        (fileExportSession.sessionExportPointer as CSVPrinter).close()

        return true
    }
}
