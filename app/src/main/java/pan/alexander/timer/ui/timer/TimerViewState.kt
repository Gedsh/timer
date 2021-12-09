package pan.alexander.timer.ui.timer

sealed class TimerViewState {

    data class Running(val displayedText: String) : TimerViewState()

    object Stopped : TimerViewState()
}
