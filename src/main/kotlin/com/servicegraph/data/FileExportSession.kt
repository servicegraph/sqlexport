package com.servicegraph.data

import java.util.*

class FileExportSession (
    val exportFolder: String,
    var sqlSessionId: String = UUID.randomUUID().toString(),
    var exportSessionId: String,
    var sessionExportPointer: Any? = null
)