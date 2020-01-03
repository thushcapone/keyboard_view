/*
 *
 *  * Copyright (c) 2018. T.C.
 *  * All Rights Reserved
 *  *
 *  * This product is protected by copyright and distributed under
 *  * licenses restricting copying, distribution and decompilation.
 *  *
 *
 */

package com.thushcapone.keyboard_view

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.widget.*
import androidx.databinding.BindingAdapter
import com.thushcapone.keyboard_view.extensions.getColorFilterFromResourceId
import com.thushcapone.keyboard_view.extensions.getColorFromResourceId
import com.thushcapone.keyboard_view.extensions.hideKeyboard
import com.thushcapone.keyboard_view.extensions.shuffle
import kotlinx.android.synthetic.main.compound_keyboard.view.*


/**
 * Created by Thiependa Seye on 1/31/19.
 * T.C.
 * thiependa.seye@gmail.com
 */
class KeyboardView : LinearLayout, View.OnClickListener {

    private val keyValues = SparseArray<String>()
    private var inputConnection: InputConnection? = null
    private var callback: OnOkListener? = null
    private var mShouldShuffle = true
    private var mKeysColor = R.color.grey
    private val numbers by lazy {
        if (mShouldShuffle){
            arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0").shuffle()
        } else {
            arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
        }
    }

    constructor(context: Context) : super(context) {
        initializeViews(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.KeyboardView, 0, 0
        )
        mShouldShuffle = a.getBoolean(R.styleable.KeyboardView_shuffle, true)
        mKeysColor = a.getResourceId(R.styleable.KeyboardView_keysColor, R.color.grey)
        a.recycle()
        initializeViews(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.KeyboardView, 0, 0
        )
        mShouldShuffle = a.getBoolean(R.styleable.KeyboardView_shuffle, true)
        mKeysColor = a.getResourceId(R.styleable.KeyboardView_keysColor, R.color.grey)
        a.recycle()
        initializeViews(context)
    }

    /**
     * Inflates the views in the layout.
     *
     * @param context the current context for the view.
     */
    private fun initializeViews(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.compound_keyboard, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        setKeysColor(button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0, button_delete)
        setListener(button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0, button_delete)
        setButtons(button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0)
    }

    private fun setKeysColor(vararg buttons: View?) {
        buttons.forEach {
            when(it){
                is ImageButton -> it.colorFilter = mKeysColor.getColorFilterFromResourceId(context)
                is Button -> it.setTextColor(mKeysColor.getColorFromResourceId(context))
            }
        }
    }

    private fun setListener(vararg buttons: View?) {
        buttons.forEach {
            it?.setOnClickListener(this)
        }
    }

    private fun setButtons(vararg buttons: TextView?) {
        for (i in numbers.indices) {
            buttons[i]?.text = numbers[i]
            buttons[i]?.id?.let { keyValues.put(it, numbers[i]) }
        }
    }

    override fun onClick(view: View) {
        inputConnection?.run {
            if (view.id == R.id.button_delete) {
                val selectedText = getSelectedText(0)

                if (TextUtils.isEmpty(selectedText)) {
                    deleteSurroundingText(1, 0)
                } else {
                    commitText("", 1)
                }
            //} else if (view.id == R.id.button_enter) {
            //    callback?.okClicked()
            } else {
                val value = keyValues.get(view.id)
                commitText(value, 1)
            }
        }
    }

    fun setInputConnection(ic: InputConnection) {
        inputConnection = ic
    }

    fun setListener(listener: OnOkListener){
        callback = listener
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        this.context.hideKeyboard(this)
    }
}

@BindingAdapter("keyboard")
fun setKeyboard(editText: EditText, keyboard: KeyboardView) {
    setKeyboard(editText, keyboard, null)
}

@SuppressLint("ClickableViewAccessibility")
@BindingAdapter("keyboard", "validateAction")
fun setKeyboard(editText: EditText, keyboard: KeyboardView, action: OnOkListener? = null) {
    Log.e("keyb", "setting keyboard 123")
    editText.setOnTouchListener { v, event ->
        v.onTouchEvent(event)
        v.context.hideKeyboard(v)
        true
    }
    //editText.showSoftInputOnFocus = false
    //DIRTY HACK 13/02/2019 : View should be focusable but when I set it to focusable, the keyboard is shown when I
    // focus on the keyboard then put the app on background and come back on the foreground of the view
    editText.isFocusable = false
    //END OF DIRTY HACK
    editText.setOnFocusChangeListener { v, _ ->
        v.context.hideKeyboard(v)
    }

    val ic = editText.onCreateInputConnection(EditorInfo())
    keyboard.setInputConnection(ic)
    action?.let { keyboard.setListener(it) }
}

interface OnOkListener {

    fun okClicked()

}