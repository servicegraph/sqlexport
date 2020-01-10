package com.servicegraph.fileExporter

import com.servicegraph.data.DbResult
import com.servicegraph.data.FileExportSession
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream

class ExcelExporter: FileExporter(false) {
    override fun exportData(data: DbResult, fileExportSession: FileExportSession): Boolean {
        val wb = XSSFWorkbook()
        val sh = wb.createSheet(fileExportSession.sqlSessionId)

        // Printing header
        var header = sh.createRow(0)
        for ((i, headerValue) in data.header.iterator().withIndex()){
            var cell = header.createCell(i)
            cell.setCellValue(headerValue.toString())
        }

        // Printing data
        for ((i, rowValue) in data.data.iterator().withIndex()){
            var row = sh.createRow(i + 1)
            for((x, cellValue) in rowValue.iterator().withIndex()){
                row.createCell(x).setCellValue(cellValue.toString())
            }
        }

        // Writing value
        val fileOut = FileOutputStream(fileExportSession.exportFolder + "poi-generated-file.xlsx")
        wb.write(fileOut)
        fileOut.close()

        return true
    }
}
