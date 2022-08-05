package com.liang.yuanshenalbum.ui.home

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


class VerticalViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return super.onTouchEvent(swapTouchEvent(MotionEvent.obtain(ev)))
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return super.onInterceptTouchEvent(swapTouchEvent(MotionEvent.obtain(ev)))
    }

    private fun swapTouchEvent(event: MotionEvent): MotionEvent {
        event.setLocation((event.y / height) * width, ((event.x / width) * height))
        return event
    }

}