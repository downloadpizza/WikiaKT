package net.downloadpizza.wikiakt

sealed class Result<out T, out E>
class Ok<out T>(val value: T) : Result<T, Nothing>()
class Err<out E>(val error: E) : Result<Nothing, E>()

inline fun <U, T, E> Result<T, E>.map(transform: (T) -> U): Result<U, E> =
    when (this) {
        is Ok -> Ok(transform(value))
        is Err -> this
    }

inline fun <U, T, E> Result<T, E>.mapError(transform: (E) -> U): Result<T, U> =
    when (this) {
        is Ok -> this
        is Err -> Err(transform(error))
    }

inline fun <U, T, E> Result<T, E>.andThen(transform: (T) -> Result<U, E>): Result<U, E> =
    when (this) {
        is Ok -> transform(value)
        is Err -> this
    }

inline fun <U, T, E> Result<T, E>.fold(transformResult: (T) -> U, transformError: (E) -> U): U =
    when (this) {
        is Ok -> transformResult(value)
        is Err -> transformError(error)
    }

fun <T> Result<T, *>.value() =
    when(this) {
        is Ok -> this.value
        is Err -> error("Result was error")
    }

fun <T> Result<*, T>.error() =
    when(this) {
        is Ok -> error("Result was not an error")
        is Err -> error
    }

typealias RequestResult<T> = Result<T, String?>