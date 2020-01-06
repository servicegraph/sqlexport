package com.servicegraph.data

class DbQuery (
    var name: String,
    var sql: String,
    var connectionName: String,
    var paged: Boolean = false
) {
    constructor(): this("", "", "")
}
