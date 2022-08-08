package com.liang.yuanshenalbum.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager

class VerticalViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    init {
        //设置viewpage的切换动画,这里设置才能真正实现垂直滑动的viewpager
        setPageTransformer(true, DefaultTransformer())
    }

    /**
     * 拦截touch事件
     *
     * @param ev 获取事件类型的封装类MotionEvent
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val intercept = super.onInterceptTouchEvent(swapEvent(ev))
        swapEvent(ev)
        return intercept
    }


    /**
     * 触摸点击触发该方法
     *
     * @param ev 获取事件类型的封装类MotionEvent
     */
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return super.onTouchEvent(swapEvent(ev))
    }

    /**
     * 交换x轴和y轴的移动距离
     *
     * @param event 获取事件类型的封装类MotionEvent
     */
    private fun swapEvent(event: MotionEvent): MotionEvent {
        //获取宽高
        val width = width.toFloat()
        val height = height.toFloat()
        //将Y轴的移动距离转变成X轴的移动距离
        val swappedX = event.y / height * width
        //将X轴的移动距离转变成Y轴的移动距离
        val swappedY = event.x / width * height
        //重设event的位置
        event.setLocation(swappedX, swappedY)
        return event
    }


    /**
     * 自定义 ViewPager 切换动画
     * 如果不设置切换动画，还会是水平方向的动画
     */
    class DefaultTransformer : PageTransformer {
        override fun transformPage(page: View, position: Float) {
            if (position >= -1 && position <= 1) {
                page.translationX = page.width * -position
                page.translationY = page.height * position
            }
        }

    }

}