package com.investment.modeling

import java.io.InputStreamReader
import java.util.*

open class ResourceProvider {
    protected var property = Properties()

    constructor(fileName: String) {
        init(fileName)
    }

    fun reload(fileName: String) {
        init(fileName)
    }

    private fun init(fileName: String) {
        this.property = Properties()
        val stream = this.javaClass.classLoader.getResourceAsStream(fileName)
        val reader = InputStreamReader(stream, "UTF-8")
        this.property.load(reader)
    }

    fun getText(name: String): String {
        return property.getProperty(name) ?: throw NullPointerException("Value for property $name is not found")
    }
}