package org.positive.daymotion.common

import android.content.Context
import androidx.appcompat.app.AlertDialog
import org.positive.daymotion.R

class LoadingHandler(context: Context) {

    private val loadingDialog: AlertDialog by lazy {
        AlertDialog.Builder(context, R.style.LoadingDialog)
            .setCancelable(false)
            .setView(R.layout.widget_progress_bar)
            .create()
    }

    fun show() {
        loadingDialog.show()
    }

    fun hide() {
        loadingDialog.hide()
    }
}