package example.com.madlibs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.madlibs.MainActivity
import kotlinx.android.synthetic.main.activity_principal.*


import com.example.madlibs.R
import java.util.*

class Principal : AppCompatActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        // Welcome text
        val welcomeView = findViewById<TextView>(R.id.welcome_text)
        val welcomeText = readFile()
        welcomeView.text = welcomeText
    }

    private fun readFile(): String {
        var text: String
        val scan: Scanner = Scanner(resources.openRawResource(R.raw.welcome_text))
        text = scan.nextLine()
        while (scan.hasNextLine()) {
            text += " " + scan.nextLine()
        }
        return text
    }

    fun playButton(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
    }
}