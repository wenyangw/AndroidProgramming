package com.lnyswz.androidprogramming

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private val TAG = "QuizActivity"
    private val KEY_INDEX = "index"
    private val KEY_CHEAT = "cheat"
    private val REQUEST_CODE_CHEAT = 0

    private var mQuestionBank = arrayOf(
        Question(R.string.question_australia, true, false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_mideast, false, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, true, false)
    )
    private var mCurrentIndex = 0
    private var mAnswers = 0
    private var mAnswerIsTrue = 0
    private var mIsCheater = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called")
        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0)
            mIsCheater = savedInstanceState.getBoolean(KEY_CHEAT, false)
        }
        setContentView(R.layout.activity_quiz)

        updateQuestion(0)

        btn_true.setOnClickListener{
            checkAnswer(true)
        }

        btn_false.setOnClickListener{
            checkAnswer(false)
        }

        btn_cheat.setOnClickListener {
            val answerIsTrue = mQuestionBank[mCurrentIndex].mAnswerTrue
            val intent = CheatActivity.newIntent(this, answerIsTrue)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
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


    private fun updateQuestion(step: Int) {
        mIsCheater = false
        mCurrentIndex += step
        var question = mQuestionBank[mCurrentIndex]

        btn_prev.isEnabled = mCurrentIndex != 0
        btn_next.isEnabled = mCurrentIndex != (mQuestionBank.size - 1)

        btn_true.isEnabled = !question.mAnswered
        btn_false.isEnabled = !question.mAnswered

        val questionId = question.mTextResId
        tv_question.setText(questionId)
    }

    private fun checkAnswer(userPressedTrue: Boolean){
        mAnswers++

        var question = mQuestionBank[mCurrentIndex]
        question.mAnswered = true

        var answerIsTrue = question.mAnswerTrue
        var messageResId: Int

        if (mIsCheater){
            messageResId = R.string.judgment_toast
        }else {
            if (userPressedTrue == answerIsTrue) {
                mAnswerIsTrue++
                messageResId = R.string.correct_toast
            } else {
                messageResId = R.string.incorrect_toast
            }
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        updateQuestion(0)

        if(mAnswers == mQuestionBank.size){
            Toast.makeText(this, "全部完成！\n共答题" + mQuestionBank.size + "道，正确" + mAnswerIsTrue + "道！", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(KEY_INDEX, mCurrentIndex)
        outState?.putBoolean(KEY_CHEAT, mIsCheater)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult() called")
        if(resultCode != Activity.RESULT_OK){
            return
        }
        if(requestCode == REQUEST_CODE_CHEAT){
            if(data == null) return
            mIsCheater = CheatActivity.wasAnswerShown(data)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
}
