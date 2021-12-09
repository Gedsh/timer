package pan.alexander.timer.domain

class TimerStateCalculator(
    private val timestampProvider: TimestampProvider,
    private val elapsedTimeCalculator: ElapsedTimeCalculator
) {

    fun calculateRunningState(oldState: TimerState): TimerState.Running =
        when (oldState) {
            is TimerState.Running -> oldState
            is TimerState.Paused -> {
                TimerState.Running(
                    startTime = timestampProvider.getMilliseconds(),
                    elapsedTime = oldState.elapsedTime
                )
            }
        }

    fun calculatePausedState(oldState: TimerState): TimerState.Paused =
        when (oldState) {
            is TimerState.Running -> {
                val elapsedTime = elapsedTimeCalculator.calculate(oldState)
                TimerState.Paused(elapsedTime = elapsedTime)
            }
            is TimerState.Paused -> oldState
        }
}
