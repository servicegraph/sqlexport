package com.servicegraph

import com.servicegraph.fileExporter.FileExporter

fun main(args: Array<String>){
    if(args.isEmpty()){
        print("No param available")
    } else {
        when (args[0]) {
            "--export" -> {
                ExportService.export(args[1], FileExporter.FileExportType.CSV)
                ExportService.finalize()
            }
            "--exportMulti" -> {
                ExportService.exportMulti(args[1])
                ExportService.finalize()
            }
            else -> print("Param not valid")
        }
    }
}