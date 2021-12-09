package pan.alexander.timer.utils

fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, '0')
