package com.servicegraph.data

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper

data class DbConnectionDefinition (
    var dbConnections: List<DbConnection>
) {
    constructor(): this(ArrayList())
}
