package com.babosamo.myapplication

import android.util.Log
import java.util.*

object Logger {

    private val LOG_CLASS = this::class.java.name
    private val ANDROID_CLASS = "^android\\.app\\..+|^android\\.os\\..+|^com\\.android\\..+|^java\\..+|^android\\.view\\.BWebView\\\$BWebViewClient"

    fun log(msg: String?) {
        if(!BuildConfig.DEBUG) return
        val info = getStack()
        val locator = getLocator(info)
        Log.println(Log.VERBOSE, locator, msg)
    }

    fun log(tag: String, msg: String?) {
        if(!BuildConfig.DEBUG) return
        val info = getStack()
        val locator = getLocator(info)
        Log.println(Log.VERBOSE, tag, "$locator, $msg")
    }


    fun error(msg: String){
        val info = getStack()
        val locator = getLocator(info)
        Log.println(Log.ERROR, locator, msg)
    }

    private fun getLocator(info: StackTraceElement?): String {
        return if (info == null) "" else String.format(Locale.getDefault(), "(%s:%d)", info.fileName, info.lineNumber)
    }

    private fun getStack(): StackTraceElement {
        val stackTraceElements = Exception().stackTrace
        var i = 0
        val elementSize = stackTraceElements.size
        var info = stackTraceElements[i]
        while (i < elementSize) {
            info = stackTraceElements[i]
            val className = info.className
            //            final String fileName = info.getFileName();
            //            final int lineNumber = info.getLineNumber();
            //            final String methodName = info.getMethodName();
            //            android.util.Log.d("DEBUG", className + "," + fileName + "," + methodName + "," + lineNumber);
            if (className.startsWith(LOG_CLASS)) {
                i++
                continue
            }
            break
        }

        while (i < elementSize) {
            info = stackTraceElements[i]
            val className = info.className
            //            final String fileName = info.getFileName();
            //            final int lineNumber = info.getLineNumber();
            //            final String methodName = info.getMethodName();
            //            android.util.Log.d("DEBUG", className + "," + fileName + "," + methodName + "," + lineNumber);
            if (className.matches(ANDROID_CLASS.toRegex())) {
                i++
                continue
            }
            break
        }

        while (i >= elementSize) i--

        while (i >= 0) {
            info = stackTraceElements[i]
            val lineNumber = info.lineNumber
            if (lineNumber < 0) {
                i--
                continue
            }
            break
        }
        return info
    }

    private fun getTag(info: StackTraceElement?): String {
        if (info == null)
            return ""
        var tag = info.methodName
        try {
            val name = info.className
            tag = name.substring(name.lastIndexOf('.') + 1) + "." + info.methodName
        } catch (e: Exception) {
        }
        return tag.replace("\\$".toRegex(), "_")
    }
}