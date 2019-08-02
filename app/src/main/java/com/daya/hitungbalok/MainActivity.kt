package com.daya.hitungbalok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val STATE_RESULT = "state_result"

    }

    private val edtWidth by lazy {
        findViewById<EditText>(R.id.edt_width)
    }
    private val edtHeight by lazy {
        findViewById<EditText>(R.id.edt_height)
    }

    private val edtLength by lazy {
        findViewById<EditText>(R.id.edt_length)
    }
    private val btnCalculate by lazy {
        findViewById<Button>(R.id.btn_calculate)
    }
    private val rvResult by lazy {
        findViewById<TextView>(R.id.tv_result)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalculate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calculate) {
            val inputLength = edtLength.text.toString()
            val inputWidth = edtWidth.text.toString().trim { it <= ' ' }
            val inputHeight = edtHeight.text.toString().trim { it <= ' ' }

            var isEmptyFields = false
            var isInvalidDouble = false

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true
                edtLength.error = "field iini tidak boleh kososng"

            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true
                edtWidth.error = "field iini tidak boleh kososng"
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true
                edtHeight.error = "field iini tidak boleh kososng"
            }

            val length = toDouble(inputLength)
            val width = toDouble(inputWidth)
            val height = toDouble(inputHeight)

            if (length == null) {
                isInvalidDouble = true
                edtLength.error = "Field ini harus berupa nomer yang valid"

            }

            if (width == null) {
                isInvalidDouble = true
                edtWidth.error = "Field ini harus berupa nomer yang valid"

            }

            if (height == null) {
                isInvalidDouble = true
                edtLength.error = "Field ini harus berupa nomer yang valid"

            }

            if (!isEmptyFields && !isInvalidDouble) {
                val volume = length as Double * width as Double * height as Double
                tv_result.text = volume.toString()

            }


        }
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putString(STATE_RESULT, tv_result.text.toString())
    }


}
