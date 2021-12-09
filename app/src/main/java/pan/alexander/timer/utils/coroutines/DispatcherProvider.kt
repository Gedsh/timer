package pan.alexander.timer.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

interface DispatcherProvider {
    fun computation(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun ui(): MainCoroutineDispatcher
}
