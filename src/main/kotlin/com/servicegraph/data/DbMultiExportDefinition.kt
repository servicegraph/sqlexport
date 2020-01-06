package com.servicegraph.data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper

data class DbMultiExportDefinition (
    var dbMultiExports: List<DbMultiExport>
) {
    constructor(): this(ArrayList())
}
