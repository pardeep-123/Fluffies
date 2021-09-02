package com.puppypedia.utils.helper

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.EditText

object CommonMethods {
    @SuppressLint("ClickableViewAccessibility")
    fun scrollEditText(editText: EditText) {
        editText.setOnTouchListener { v, event ->
            if (editText.hasFocus()) {
                v.parent.requestDisallowInterceptTouchEvent(true)
            }
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_SCROLL -> {
                    v.parent.requestDisallowInterceptTouchEvent(false)
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
    }
}