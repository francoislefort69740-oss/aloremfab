package com.example.domain

import java.lang.Exception

fun Int?.convertString(): String? = this?.toString()

inline fun <reified R> ResultOf<R>.doIfSuccess(callback: (value: R) -> Unit) {
    if (this is ResultOf.Success) {
        callback(data)
    }
}

inline fun <reified T> ResultOf<T>.doIfFailure(callback: (exception: Exception) -> Unit) {
    if (this is ResultOf.Error) {
        callback(exception)
    }
}

inline fun <reified T, reified R> ResultOf<T>.map(transform: (T) -> R): ResultOf<R> {
    return when (this) {
        is ResultOf.Success -> ResultOf.Success(transform(data))
        is ResultOf.Error -> this
    }
}

inline fun <T> ResultOf<T>.withDefault(value: () -> T): ResultOf.Success<T> {
    return when (this) {
        is ResultOf.Success -> this
        is ResultOf.Error -> ResultOf.Success(value())
    }
}

inline fun <T1: Any, T2: Any, R: Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2)->R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}
inline fun <T1: Any, T2: Any, T3: Any, R: Any> safeLet(p1: T1?, p2: T2?, p3: T3?, block: (T1, T2, T3)->R?): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}
inline fun <T1: Any, T2: Any, T3: Any, T4: Any, T5: Any, R: Any> safeLet(p1: T1?, p2: T2?, p3: T3?, p4: T4?, p5: T5?, block: (T1, T2, T3, T4, T5)->R?): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) block(p1, p2, p3, p4, p5) else null
}

inline fun <T1: Any, R: Any> safeNotLet(p1: T1?, block: (T1?)->R?): R? {
    return if (p1 == null) block(null) else null
}