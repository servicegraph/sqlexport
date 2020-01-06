package com.servicegraph.data

data class DbMultiExport (
    var name: String,
    var queries: List<String>
) {
    constructor(): this ("", ArrayList())
}
