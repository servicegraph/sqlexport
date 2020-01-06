package com.servicegraph.fileExporter

import com.servicegraph.data.DbResult
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.FileWriter

class CSVExporter: FileExporter {
    override fun exportToFile(data: DbResult): Boolean {
        val out = FileWriter(data.exportSessionId + ".csv")
        val printer = CSVPrinter(out, CSVFormat.DEFAULT)
        printer.printRecords(data.result)
        out.close()
        return true
    }
}