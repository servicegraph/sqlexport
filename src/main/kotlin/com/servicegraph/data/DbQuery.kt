package com.servicegraph.data

class DbQuery (
    var name: String,
    var sql: String,
    var sqlFile: String,
    var connectionName: String,
    var paged: Boolean = false,
    val exportFileName: String,
    val type: DbQueryType
) {
    constructor(): this("", "", "", "", false, "", DbQueryType.QUERY)
}
