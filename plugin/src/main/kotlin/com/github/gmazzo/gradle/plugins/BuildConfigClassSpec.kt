package com.github.gmazzo.gradle.plugins

import com.github.gmazzo.gradle.plugins.generators.BuildConfigGenerator
import com.github.gmazzo.gradle.plugins.generators.BuildConfigOutputType.Companion.asOutputType
import org.gradle.api.Named

interface BuildConfigClassSpec : Named {

    var className: String?

    var packageName: String?

    var outputType: BuildConfigGenerator?

    fun className(className: String) {
        this.className = className
    }

    fun packageName(packageName: String) {
        this.packageName = packageName
    }

    fun outputType(outputType: BuildConfigGenerator) {
        this.outputType = outputType
    }

    fun outputType(outputType: String) =
        outputType(outputType.asOutputType())

    @Deprecated("Use outputType instead", ReplaceWith("outputType(language)"))
    fun language(language: String) = outputType(language)

    @Deprecated("Use outputType instead", ReplaceWith("outputType(language)"))
    fun language(language: BuildConfigGenerator) = outputType(language)

    fun buildConfigField(field: BuildConfigField): BuildConfigField

    fun buildConfigField(type: String, name: String, value: String) =
        buildConfigField(BuildConfigField(type, name, value))

    fun buildConfigField(type: String, name: String, value: () -> String) =
        buildConfigField(BuildConfigField(type, name, value()))

    val generateTask: BuildConfigTask

}
