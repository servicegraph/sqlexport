package com.servicegraph.fileExporter

import com.servicegraph.data.DbResult

class ExcelExporter: FileExporter {
    override fun exportToFile(data: DbResult): Boolean {
        return true
    }
}
