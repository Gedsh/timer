package pan.alexander.timer.domain

sealed class TimerState {

    data class Paused(
        val elapsedTime: Long
    ) : TimerState()

    data class Running(
        val startTime: Long,
        val elapsedTime: Long
    ) : TimerState()
}

