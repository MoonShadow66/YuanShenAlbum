package com.liang.yuanshenalbum.ui.main

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

open class GestureListener(context: Context) : GestureDetector.SimpleOnGestureListener(),
    View.OnTouchListener {

    /** 左右滑动的最短距离  */
    private var distance = 50

    /** 左右滑动的最大速度  */
    private val velocity = 150

    private var gestureDetector: GestureDetector? = null

    init {
        gestureDetector = GestureDetector(context, this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        gestureDetector?.onTouchEvent(event)
        return false
    }

    /**
     *
     * 向左滑的时候调用的方法，子类应该重写
     *
     * @return
     */
    open fun left(): Boolean {
        return false
    }

    /**
     *
     * 向右滑的时候调用的方法，子类应该重写
     *
     * @return
     */
    open fun right(): Boolean {
        return false
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        // e1：第1个ACTION_DOWN MotionEvent
        // e2：最后一个ACTION_MOVE MotionEvent
        // velocityX：X轴上的移动速度(像素/秒)
        // velocityY：Y轴上的移动速度(像素/秒)

        if (e1 != null && e2 != null) {
            // 向左滑
            if (e1.getX() - e2.getX() > distance
                && Math.abs(velocityX) > velocity
            ) {
                left();
            }
            // 向右滑
            if (e2.getX() - e1.getX() > distance
                && Math.abs(velocityX) > velocity
            ) {
                right();
            }
        }

        return false;
    }

    fun getDistance(): Int {
        return distance
    }

    fun setDistance(distance: Int) {
        this.distance = distance
    }

    fun getVelocity(): Int {
        return velocity
    }

    fun getGestureDetector(): GestureDetector? {
        return gestureDetector
    }

    fun setGestureDetector(gestureDetector: GestureDetector?) {
        this.gestureDetector = gestureDetector
    }
}