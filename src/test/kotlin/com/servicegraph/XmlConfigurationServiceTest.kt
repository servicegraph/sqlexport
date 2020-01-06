package com.servicegraph

import org.junit.Test

import org.junit.Assert.*

class XmlConfigurationServiceTest {
    val xmlConfigurationService = XmlConfigurationService()

    @Test
    fun getMultiExports() {
        val res = xmlConfigurationService.getMultiExports()
        assertEquals(1, res.size)
    }

    @Test
    fun getMultiExport() {
        val res = xmlConfigurationService.getMultiExport("")
    }

    @Test
    fun getDbConnection() {
        val res = xmlConfigurationService.getDbConnection("")
    }

    @Test
    fun getDbConnections() {
        val res = xmlConfigurationService.getDbConnections()
        assertEquals(1, res.size)
    }

    @Test
    fun getDbQueries() {
        val res = xmlConfigurationService.getDbQueries()
        assertEquals(1, res.size)
    }

    @Test
    fun getDbQuery() {
        val res = xmlConfigurationService.getDbQuery("")
    }
}
