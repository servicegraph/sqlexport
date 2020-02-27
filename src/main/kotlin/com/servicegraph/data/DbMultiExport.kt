package com.servicegraph.data

import com.servicegraph.fileExporter.FileExporter

data class DbMultiExport (
    var name: String,
    var charset: String,
    var exportFolder: String,
    var fileExportType: FileExporter.FileExportType,
    var queries: List<String>
) {
    constructor(): this ("", "UTF-8", "export", FileExporter.FileExportType.CSV, ArrayList())
}
