package com.liang.yuanshenalbum

import android.widget.Toast

fun String.showToast() {
    Toast.makeText(MyApplication.context, this, Toast.LENGTH_SHORT).show()
}
fun Int.showToast() {
    Toast.makeText(MyApplication.context, this, Toast.LENGTH_SHORT).show()
}