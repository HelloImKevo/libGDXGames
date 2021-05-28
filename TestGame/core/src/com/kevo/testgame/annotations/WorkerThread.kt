package com.kevo.testgame.annotations

/**
 * Indicates that the target should be invoked from a background / worker thread.
 * (When invoked, the target may block the current thread).
 */
@Target(AnnotationTarget.CLASS,
        AnnotationTarget.CONSTRUCTOR,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class WorkerThread
