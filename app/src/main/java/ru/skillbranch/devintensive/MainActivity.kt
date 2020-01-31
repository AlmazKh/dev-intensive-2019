package ru.skillbranch.devintensive

import android.app.Activity
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity() {

    lateinit var bender: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bender = Bender(
                Bender.Status.valueOf(
                        savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
                ), Bender.Question.valueOf(
                savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        )
        )

        val (r, g, b) = bender.status.color
        iv_bender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        tv_text.text = bender.askQuestion()

        et_message.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                sendDataToBender()
                Activity().hideKeyboard(this)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        iv_send.setOnClickListener {
//            sendDataToBender()
            et_message.onEditorAction(EditorInfo.IME_ACTION_DONE)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("STATUS", bender.status.name)
        outState.putString("QUESTION", bender.question.name)
    }

    private fun sendDataToBender() {
        val (phrase, color) = bender.listenAnswer(et_message.text.toString())
        et_message.text.clear()
        val (r, g, b) = color
        iv_bender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        tv_text.text = phrase
    }
}
