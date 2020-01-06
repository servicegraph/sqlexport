package com.servicegraph.fileExporter

import com.servicegraph.data.FileExportType

interface FileExporter {
    fun exportToFile(data: List<HashMap<String, String>>): Boolean

    companion object {
        val EXPORT_TYPE_MAP: Map<FileExportType, FileExporter> = mapOf(FileExportType.CSV to CSVExporter())
    }
}
