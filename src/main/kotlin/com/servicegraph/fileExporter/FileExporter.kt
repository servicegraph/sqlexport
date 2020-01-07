package com.servicegraph.fileExporter

import com.servicegraph.data.DbResult

interface FileExporter {
    fun exportToFile(data: DbResult): Boolean

    enum class FileExportType {
        CSV, EXCEL
    }

    companion object {
        val EXPORT_TYPE_MAP: Map<FileExportType, FileExporter> = mapOf(
            FileExportType.CSV to CSVExporter(),
            FileExportType.EXCEL to ExcelExporter()
        )
    }
}
