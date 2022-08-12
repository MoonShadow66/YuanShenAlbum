package com.liang.yuanshenalbum.util

import android.app.Activity
import android.graphics.Point
import androidx.customview.widget.ViewDragHelper
import androidx.drawerlayout.widget.DrawerLayout
import java.lang.reflect.Field

object DrawerLayoutUtil {

    // 设置drawerlayout全屏滑动
    fun setDrawerLeftEdgeSize(
        activity: Activity?,
        drawerLayout: DrawerLayout?,
        displayWidthPercentage: Float
    ) {
        if (activity == null || drawerLayout == null) return
        try {
            // 找到 ViewDragHelper 并设置 Accessible 为true
            val leftDraggerField: Field =
                drawerLayout.javaClass.getDeclaredField("mLeftDragger") //Right
            leftDraggerField.setAccessible(true)
            val leftDragger = leftDraggerField.get(drawerLayout) as ViewDragHelper

            // 找到 edgeSizeField 并设置 Accessible 为true
            val edgeSizeField: Field = leftDragger.javaClass.getDeclaredField("mEdgeSize")
            edgeSizeField.setAccessible(true)
            val edgeSize: Int = edgeSizeField.getInt(leftDragger)

            // 设置新的边缘大小
            val displaySize = Point()
            activity.windowManager.defaultDisplay.getSize(displaySize)
            edgeSizeField.setInt(
                leftDragger, Math.max(edgeSize, (displaySize.x * displayWidthPercentage).toInt())
            )
        } catch (e: NoSuchFieldException) {
        } catch (e: IllegalArgumentException) {
        } catch (e: IllegalAccessException) {
        }
    }
}