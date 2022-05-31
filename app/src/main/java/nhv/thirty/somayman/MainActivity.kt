package nhv.thirty.somayman

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import nhv.thirty.somayman.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        buildLayout()
    }

    fun buildLayout() {
        val todayNumber = getTodayNumber()
        binding.txtLuckyNumber.text = todayNumber.toString()
    }

    fun getTodayNumber(): Int {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        var number = sharedPref.getInt(Smm.TODAY_LUCKY_NUMBER, -1)
        var expire = sharedPref.getLong(Smm.TODAY_LUCKY_NUMBER_EXPIRE, -1)
        if (number == -1 || expire < System.currentTimeMillis()) {
            number = (0..99).random()
            sharedPref.edit().putInt(Smm.TODAY_LUCKY_NUMBER, number).apply()
            val expire = System.currentTimeMillis() + 86400000
            sharedPref.edit().putLong(Smm.TODAY_LUCKY_NUMBER_EXPIRE, expire).apply()
        }
        return number
    }
}