package com.lnyswz.androidprogramming

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private var mQuestionBank = arrayOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var mCurrentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        updateQuestion(0)

        btn_true.setOnClickListener{
            checkAnswer(true)
        }

        btn_false.setOnClickListener{
            checkAnswer(false)
        }

        btn_prev.setOnClickListener{
            updateQuestion(-1)
        }

        btn_next.setOnClickListener{
            updateQuestion(1)
        }

        tv_question.setOnClickListener {
            updateQuestion(1)
        }
    }

    fun updateQuestion(step: Int){
        mCurrentIndex += step
        var question = mQuestionBank[(mCurrentIndex + mQuestionBank.size) % mQuestionBank.size].mTextResId
        tv_question.setText(question)
    }

    fun checkAnswer(userPressedTrue: Boolean){
        var answerIsTrue = mQuestionBank[mCurrentIndex].mAnswerTrue
        var messageResId: Int
        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast
        }else{
            messageResId = R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}
