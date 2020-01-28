package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity() {

    lateinit var bender: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bender = Bender(Bender.Status.valueOf(
                savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        ), Bender.Question.valueOf(
                savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        ))

        val (r, g, b) = bender.status.color
        iv_bender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        tv_text.text = bender.askQuestion()
        iv_send.setOnClickListener {
            val (phrase, color) = bender.listenAnswer(et_message.text.toString().toLowerCase())
            et_message.text.clear()
            val (r, g, b) = color
            iv_bender.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
            tv_text.text = phrase
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("STATUS", bender.status.name)
        outState.putString("QUESTION", bender.question.name)
    }
}
