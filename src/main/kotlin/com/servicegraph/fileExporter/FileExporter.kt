package com.servicegraph.fileExporter

import com.servicegraph.data.DbResult
import com.servicegraph.data.FileExportSession
import java.io.File
import kotlin.math.exp

abstract class FileExporter {
    fun exportNonPaged(data: DbResult, fileExportSession: FileExportSession): Boolean {
        createExportFolder(File(fileExportSession.exportFolder))
        var start = startExport(fileExportSession)
        var export = exportData(data, fileExportSession)
        var end = endExport(fileExportSession)

        return start && export && end
    }

    private fun createExportFolder(file: File){
        if(file.exists()){
            file.mkdirs()
        }
    }

    open fun startExport(fileExportSession: FileExportSession): Boolean = true
    abstract fun exportData(data: DbResult, fileExportSession: FileExportSession): Boolean
    open fun endExport(fileExportSession: FileExportSession): Boolean = true

    fun supportsPaging() = true

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
