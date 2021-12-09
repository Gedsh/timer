package pan.alexander.timer.data

import pan.alexander.timer.domain.TimestampProvider

class TimestampProviderImpl : TimestampProvider {
    override fun getMilliseconds(): Long = System.currentTimeMillis()
}
