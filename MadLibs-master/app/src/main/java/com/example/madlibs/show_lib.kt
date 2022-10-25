package example.com.madlibs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.madlibs.MainActivity
import com.example.madlibs.R

class ShowLib : AppCompatActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_lib)

        val intent: Intent = getIntent()
        val completeStory = intent.getStringExtra("completeText")

        // Lib text
        val text = findViewById(R.id.complete_text) as TextView
        text.text = completeStory
    }

    fun newStoryButton(view: View?) {
        // Main Activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}