package pan.alexander.timer.ui.timer

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import pan.alexander.timer.R
import pan.alexander.timer.databinding.TimerFragmentBinding
import pan.alexander.timer.ui.base.BaseFragment

private const val DEFAULT_TIME = "00:00:000"

class TimerFragment : BaseFragment<TimerViewState>(R.layout.timer_fragment) {

    override val timerViewModel by viewModel<TimerViewModel>()

    private val binding by viewBinding(TimerFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButtonOnClickListeners()

        observeViewState()
    }

    private fun initButtonOnClickListeners() {
        binding.buttonStart.setOnClickListener {
            timerViewModel.startTimer()
        }
        binding.buttonPause.setOnClickListener {
            timerViewModel.pauseTimer()
        }
        binding.buttonStop.setOnClickListener {
            timerViewModel.stopTimer()
        }
    }

    private fun observeViewState() {
        timerViewModel.getViewStateLiveData().observe(viewLifecycleOwner) {
            setState(it)
        }
    }

    override fun setState(viewState: TimerViewState) {
        when (viewState) {
            is TimerViewState.Running -> {
                setTimerText(viewState.displayedText)
                setTimerTextColor(ContextCompat.getColor(requireContext(), R.color.running))

            }
            is TimerViewState.Stopped -> {
                setTimerText(DEFAULT_TIME)
                setTimerTextColor(ContextCompat.getColor(requireContext(), R.color.stopped))
            }
        }
    }

    private fun setTimerText(text: String) {
        binding.textTime.text = text
    }

    private fun setTimerTextColor(color: Int) {
        if (binding.textTime.currentTextColor != color) {
            binding.textTime.setTextColor(color)
        }
    }

    companion object {
        fun newInstance() = TimerFragment()
    }

}
