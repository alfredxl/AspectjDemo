package com.aspectj.demo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import com.aspectj.combizlib.base.BaseActivity
import com.aspectj.demo.bean.Person
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class StartPage : BaseActivity() {

    override fun onCreateLife(savedInstanceState: Bundle?) {
        super.onCreateLife(savedInstanceState)
        verticalLayout {
            gravity = Gravity.CENTER_HORIZONTAL
            button("ButtonOne") {
                gravity = Gravity.CENTER
                textSize = 14f
                textColor = Color.BLACK
            }.lparams(width = -2, height = -2) {
                topMargin = 30
            }.onClick {
                setButtonOneData(it as Button, "ButtonOne_" + it.toString())
            }

            button("创建新对象") {
                gravity = Gravity.CENTER
                textSize = 14f
                textColor = Color.BLACK
            }.lparams(width = -2, height = -2) {
                topMargin = 30
            }.onClick {
                (it as Button).text = Person().toString()
            }

            button("显示toast") {
                gravity = Gravity.CENTER
                textSize = 14f
                textColor = Color.BLACK
            }.lparams(width = -2, height = -2) {
                topMargin = 30
            }.onClick {
                showToast("显示toast")
            }
        }
    }

    private fun setButtonOneData(view: Button, message: String) {
        view.text = message
    }

    private fun showToast(message: String) {
        if (message.isNotEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
        try {
            throw IllegalArgumentException()
        } catch (e: IllegalArgumentException) {

        }
    }
}
