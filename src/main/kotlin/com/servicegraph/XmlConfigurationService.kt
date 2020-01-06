package com.servicegraph

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.servicegraph.data.*
import java.io.File

object XmlConfigurationService {
    val xmlMapper = XmlMapper()

    private val FILE_CONNECTIONS = "connections.xml"
    private val FILE_MULTI_EXPORT = "multiExport.xml"
    private val FILE_QUERIES = "queries.xml"

    fun getMultiExports(): List<DbMultiExport> {
        return xmlMapper.readValue(File(FILE_MULTI_EXPORT), DbMultiExportDefinition::class.java).dbMultiExports
    }

    fun getMultiExport(name: String): DbMultiExport? {
        return getMultiExports().firstOrNull { it.name == name }
    }

    fun getDbConnections(): List<DbConnection> {
        return xmlMapper.readValue(File(FILE_CONNECTIONS), DbConnectionDefinition::class.java).dbConnections
    }

    fun getDbConnection(name: String): DbConnection? {
        return getDbConnections().firstOrNull { it.name == name }
    }

    fun getDbQueries(): List<DbQuery> {
        return xmlMapper.readValue(File(FILE_QUERIES), DbQueryDefinition::class.java).dbQueries
    }

    fun getDbQuery(name: String): DbQuery? {
        return getDbQueries().firstOrNull{ it.name == name }
    }
}
