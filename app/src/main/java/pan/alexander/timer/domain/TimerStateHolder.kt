package pan.alexander.timer.domain

class TimerStateHolder(
    private val timerStateCalculator: TimerStateCalculator
) {

    var currentState: TimerState = TimerState.Paused(0)
        private set

    fun start() {
        currentState = timerStateCalculator.calculateRunningState(currentState)
    }

    fun pause() {
        currentState = timerStateCalculator.calculatePausedState(currentState)
    }

    fun stop() {
        currentState = TimerState.Paused(0)
    }
}
