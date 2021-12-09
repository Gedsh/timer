package pan.alexander.timer.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

class DispatcherProviderImpl : DispatcherProvider {

    override fun computation() = Dispatchers.Default

    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun ui(): MainCoroutineDispatcher = Dispatchers.Main
}
