package com.servicegraph

fun main(args: Array<String>){
    when (args[0]) {
        "--export" -> ExportService.export(args[1])
        "--exportMulti" -> ExportService.exportMulti(args[1])
        else -> print("")
    }
}
