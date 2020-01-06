package com.servicegraph.data

data class DbConnectionDefinition (
    var dbConnections: List<DbConnection>
) {
    constructor(): this(ArrayList())
}
