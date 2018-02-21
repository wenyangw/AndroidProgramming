package com.lnyswz.androidprogramming

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cheat.*

class CheatActivity : AppCompatActivity() {

    private var mAnswerIsTrue = false
    private var mAnswerIsShown = false
    private var KEY_SHOWN = "shown"

    companion object {
        private val EXTRA_ANSWER_IS_TRUE = "com.lnyswz.androidprogramming.goquiz.answer_is_true"
        private val EXTRA_ANSWER_SHOWN = "com.lnyswz.androidprogramming.geoquiz.answer_shown"

        fun newIntent (packageContent: Context, answerIsTrue: Boolean): Intent{
            var intent = Intent(packageContent, CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            return intent
        }

        fun wasAnswerShown(result: Intent): Boolean{
            return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        if(savedInstanceState != null) {
            mAnswerIsShown = savedInstanceState.getBoolean(KEY_SHOWN, false)
        }
        mAnswerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        if(mAnswerIsShown){
            setAnswerShownResult(mAnswerIsShown)
        }

        btn_show_answer.setOnClickListener {
            mAnswerIsShown = true
            if(mAnswerIsTrue){
                tv_answer.setText(R.string.true_button)
            }else{
                tv_answer.setText(R.string.false_button)
            }
            setAnswerShownResult(mAnswerIsShown)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(KEY_SHOWN, mAnswerIsShown)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean){
        var intent = Intent()
        intent.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(Activity.RESULT_OK, intent)
    }

}
