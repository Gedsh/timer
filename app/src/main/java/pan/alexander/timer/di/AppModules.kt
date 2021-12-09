package pan.alexander.timer.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pan.alexander.timer.data.TimestampProviderImpl
import pan.alexander.timer.domain.*
import pan.alexander.timer.ui.timer.TimerViewModel
import pan.alexander.timer.ui.timer.TimestampFormatter
import pan.alexander.timer.utils.coroutines.DispatcherProvider
import pan.alexander.timer.utils.coroutines.DispatcherProviderImpl


val appModules = module {

    viewModel {
        TimerViewModel(
            interactor = get(),
            timestampFormatter = get()
        )
    }

    factory {
        TimerInteractor(
            timerStateHolder = get(),
            elapsedTimeCalculator = get(),
            dispatcherProvider = get()
        )
    }

    single {
        TimerStateHolder(
            timerStateCalculator = get()
        )
    }

    single {
        TimerStateCalculator(
            timestampProvider = get(),
            elapsedTimeCalculator = get()
        )
    }

    single {
        ElapsedTimeCalculator(timestampProvider = get())
    }

    single<TimestampProvider> {
        TimestampProviderImpl()
    }

    single {
        TimestampFormatter()
    }

    single<DispatcherProvider> {
        DispatcherProviderImpl()
    }
}
