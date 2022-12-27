package app.dreamlightpal.data

import kotlin.coroutines.CoroutineContext

interface ContextProvider {

    val io: CoroutineContext
    val default: CoroutineContext
    val main: CoroutineContext
}
