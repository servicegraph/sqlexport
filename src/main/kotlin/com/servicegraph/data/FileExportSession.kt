package com.servicegraph.data

import java.nio.charset.Charset
import java.util.*

class FileExportSession (
    val exportFolder: String,
    val exportFileName: String?,
    val charset: Charset,
    var sqlSessionId: String = UUID.randomUUID().toString(),
    var exportSessionId: String,
    var sessionExportPointer: Any? = null
)
