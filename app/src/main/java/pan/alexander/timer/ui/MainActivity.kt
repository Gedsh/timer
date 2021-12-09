package pan.alexander.timer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pan.alexander.timer.R
import pan.alexander.timer.ui.timer.TimerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TimerFragment.newInstance())
                .commitNow()
        }
    }
}
