package org.asqatasun.scenarioloader

import jp.vmi.selenium.selenese.inject.*

class AsqaBindModule(private val interceptor: AbstractDoCommandInterceptor): BindModule() {

    /**
     * Get DoCommand interceptors.
     *
     * @return DoCommand interceptors.
     */
    override fun getDoCommandInterceptors() =
        arrayOf(MaxTimeInterruptInterceptor(), CommandLogInterceptor(), interceptor, ScreenshotInterceptor())

}