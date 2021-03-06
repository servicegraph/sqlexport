package com.servicegraph

import org.junit.Test

import org.junit.Assert.*

class XmlConfigurationServiceTest {

    @Test
    fun getMultiExports() {
        val res = XmlConfigurationService.getMultiExports()
        assertEquals(1, res.size)
    }

    @Test
    fun getMultiExport() {
        XmlConfigurationService.getMultiExport("")
    }

    @Test
    fun getDbConnection() {
        XmlConfigurationService.getDbConnection("", "")
    }

    @Test
    fun getDbConnections() {
        val res = XmlConfigurationService.getDbConnections()
        assertEquals(1, res.size)
    }

    @Test
    fun getDbQueries() {
        val res = XmlConfigurationService.getDbQueries()
        assertEquals(1, res.size)
    }

    @Test
    fun getDbQuery() {
        XmlConfigurationService.getDbQuery("")
    }
}
