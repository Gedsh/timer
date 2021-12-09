package pan.alexander.timer.domain

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pan.alexander.timer.utils.coroutines.DispatcherProvider

class TimerInteractor(
    private val timerStateHolder: TimerStateHolder,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val dispatcherProvider: DispatcherProvider
) {

    private var job: Job? = null
    private val mutableTicker = MutableStateFlow(0L)
    val ticker: StateFlow<Long> = mutableTicker

    suspend fun startTimer() {
        timerStateHolder.start()
        if (job == null) startJob()
    }

    private suspend fun startJob() {
        coroutineScope {
            job = launch(dispatcherProvider.computation()) {
                while (isActive) {
                    mutableTicker.value = calculateTime(timerStateHolder.currentState)
                    delay(20)
                }
            }
        }
    }

    fun pauseTimer() {
        timerStateHolder.pause()
        stopJob()
    }

    fun stopTimer() {
        timerStateHolder.stop()
        stopJob()
        clearValue()
    }

    private fun stopJob() {
        job?.cancel()
        job = null
    }

    private fun clearValue() {
        mutableTicker.value = 0L
    }

    private fun calculateTime(state: TimerState): Long =
        when (state) {
            is TimerState.Paused -> state.elapsedTime
            is TimerState.Running -> elapsedTimeCalculator.calculate(state)
        }

}
