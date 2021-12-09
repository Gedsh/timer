package pan.alexander.timer.domain

interface TimestampProvider {
    fun getMilliseconds(): Long
}
