package pan.alexander.timer.ui.timer

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import pan.alexander.timer.domain.TimerInteractor
import pan.alexander.timer.ui.base.BaseViewModel

class TimerViewModel(
    private val interactor: TimerInteractor,
    private val timestampFormatter: TimestampFormatter
) : BaseViewModel<TimerViewState>() {

    init {
        viewModelScope.launch {
            interactor.ticker
                .filter { it != 0L }
                .collect {
                    viewStateMutableLiveData.value =
                        TimerViewState.Running(timestampFormatter.format(it))
                }
        }

        viewStateMutableLiveData.value = TimerViewState.Stopped
    }

    fun startTimer() = viewModelScope.launch {
        interactor.startTimer()
    }

    fun pauseTimer() = interactor.pauseTimer()

    fun stopTimer() {
        viewStateMutableLiveData.value = TimerViewState.Stopped
        interactor.stopTimer()
    }
}
