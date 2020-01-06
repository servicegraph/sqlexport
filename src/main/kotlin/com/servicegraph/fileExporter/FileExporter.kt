package com.servicegraph.fileExporter

import com.servicegraph.data.DbResult
import com.servicegraph.data.FileExportType

interface FileExporter {
    fun exportToFile(data: DbResult): Boolean

    companion object {
        val EXPORT_TYPE_MAP: Map<FileExportType, FileExporter> = mapOf(FileExportType.CSV to CSVExporter())
    }
}
