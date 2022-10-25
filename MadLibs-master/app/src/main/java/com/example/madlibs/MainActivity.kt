package com.example.madlibs

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.util.*
import androidx.appcompat.app.AppCompatActivity
import example.com.madlibs.ShowLib

class MainActivity : AppCompatActivity() {
    private var libText: String? = null
    private var typeOfWords: ArrayList<String>? = null
    private var inputText: EditText? = null
    private var wordCount: TextView? = null
    private var currentIndex = 0

    @SuppressLint("SetTextI18n")
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Generate random filename
        val fileNames = arrayOf("madlibs1", "madlibs2", "madlibs3", "madlibs4")
        val fileName = fileNames[Random().nextInt(fileNames.size)]

        // Get text from file
        libText = readFile(fileName)
        typeOfWords = getWordTypes(libText)
        inputText = findViewById(R.id.input_text) as EditText?
        inputText!!.hint = typeOfWords!![currentIndex]
        wordCount = findViewById(R.id.word_count) as TextView?
        wordCount!!.text = typeOfWords!!.size.toString() + " word(s) left"
    }

    protected override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("libText", libText)
        outState.putStringArrayList("typeOfWords", typeOfWords)
        outState.putInt("currentIndex", currentIndex)
    }

    protected override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey("libText") &&
            savedInstanceState.containsKey("typeOfWords") &&
            savedInstanceState.containsKey("currentIndex")
        ) {
            libText = savedInstanceState.getString("libText")
            typeOfWords = savedInstanceState.getStringArrayList("typeOfWords")
            currentIndex = savedInstanceState.getInt("currentIndex")
            val count = typeOfWords!!.size - currentIndex
            update(count)
        }
    }

    private fun readFile(fileName: String): String {
        var text: String
        if (fileName === "madlibs1") {
            val scan: Scanner = Scanner(resources.openRawResource(R.raw.madlib1_tarzan))
            text = scan.nextLine()
            while (scan.hasNextLine()) {
                text += " " + scan.nextLine()
            }
        } else {
            val scan: Scanner = Scanner(resources.openRawResource(R.raw.madlib2_university))
            text = scan.nextLine()
            while (scan.hasNextLine()) {
                text += " " + scan.nextLine()
            }
        }
        return text
    }

    private fun getWordTypes(text: String?): ArrayList<String> {
        var text = text
        val arraySub = ArrayList<String>()
        var count = 0
        while (text!!.contains("<") && text.contains(">")) {
            count++
            val subString = text.substring(text.indexOf("<") + 1, text.indexOf(">"))
            arraySub.add(subString)
            text = text.replaceFirst("<" + subString + ">".toRegex(), "")
            if (count > text.length) {
                break
            }
        }
        return arraySub
    }

    fun OkButton(view: View?) {
        val inputWord = inputText!!.text.toString()
        Log.d("myDebug", "Before: $libText")
        libText = libText!!.replaceFirst(
            "<" + typeOfWords!![currentIndex] + ">".toRegex(),
            inputWord
        )
        Log.d("myDebug", "Input word: $inputWord")
        Log.d("myDebug", "After: $libText")
        if (currentIndex >= typeOfWords!!.size - 1) {
            // ShowLib Activity
            val intent = Intent(this, ShowLib::class.java)
            intent.putExtra("completeText", libText)
            intent.putExtra("inputWords", typeOfWords)
            startActivity(intent)
        } else {
            currentIndex += 1
            Log.d("myDebug", "Current Index = $currentIndex")
        }

        // Set word count to text view
        val count = typeOfWords!!.size - currentIndex
        update(count)
    }

    private fun update(count: Int) {
        inputText!!.setText("")
        inputText!!.hint = typeOfWords!![currentIndex].toLowerCase()
        wordCount!!.text = "$count word(s) left"
    }
}