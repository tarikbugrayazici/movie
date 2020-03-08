package com.example.movies.ui.detailtabs.view

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialogFragment

class Dialog : AppCompatDialogFragment() {


    private val list = arrayOf("Öncelikli", "İsim", "Karakter")

    override fun onCreateDialog(savedInstanceState: Bundle?): android.app.Dialog {
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("Sırala")
                .setItems(list) { dialog, which ->
                    val sharedPreferences = context!!.getSharedPreferences("sorting", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putInt("key", which)
                    editor.apply()

                    when (which) {
                        0 -> {
                            sendBackResult(which)
                            sendBackResult(which)
                            sendBackResult(which)
                        }
                        1 -> {
                            sendBackResult(which)
                            sendBackResult(which)
                        }
                        2 -> sendBackResult(which)
                    }
                }
        return builder.create()
    }


    fun sendBackResult(alf: Int) {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        val listener = targetFragment as DialogListener?
        listener!!.onFinishDialog(alf)
        dismiss()
    }

    interface DialogListener {
        fun onFinishDialog(inputText: Int)
    }
}
