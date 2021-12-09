package pan.alexander.timer.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<T>(
    @LayoutRes layout: Int
) : Fragment(layout) {

    abstract val timerViewModel: BaseViewModel<T>

    abstract fun setState(viewState: T)

}
