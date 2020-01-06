package com.servicegraph.data

data class DbMultiExportDefinition (
    var dbMultiExports: List<DbMultiExport>
) {
    constructor(): this(ArrayList())
}
