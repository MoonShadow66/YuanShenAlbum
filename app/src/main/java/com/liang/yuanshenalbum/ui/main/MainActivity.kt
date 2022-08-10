package com.liang.yuanshenalbum.ui.main

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.customview.widget.ViewDragHelper
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.liang.yuanshenalbum.R
import com.liang.yuanshenalbum.logic.model.Role
import com.liang.yuanshenalbum.showToast
import com.liang.yuanshenalbum.util.LogUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Field


class MainActivity : AppCompatActivity(), OnRcyItemClickListener {

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private lateinit var adapter: MyAdapter
    private lateinit var rcyAdapter: RcyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBar()
        initViewPager()
        initRcy()
        initObserver()
        initEvent()
    }

    // 观察者，监听数据变化动态改变view
    private fun initObserver() {
        viewModel.imageLiveData.observe(this, { result ->
            val list = result.getOrNull()
            if (list != null) {
                viewModel.strList.clear()
                viewModel.strList.addAll(list)
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.roleLiveData.observe(this, { result ->
            val list = result.getOrNull()
            if (list != null) {
                viewModel.roleList.clear()
                viewModel.roleList.addAll(list)
                rcyAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun initEvent() {
//        viewPager.isLongClickable = true
        // 监听滑动事件，右滑时打开drawerLayout，这种方式是自己写手势判断是否滑动，体验非常不好
//        viewPager.setOnTouchListener(MyGestureListener(this, drawerLayout))
        setDrawerLeftEdgeSize(this, drawerLayout, 1F)
    }

    // 分类列表
    private fun initRcy() {
        viewModel.getRole() // 通知viewModel去调用livedata
        val layoutManager = GridLayoutManager(this, 3)
        rcy_main.layoutManager = layoutManager
        rcyAdapter = RcyAdapter(this, viewModel.roleList)
        rcyAdapter.setOnRcyItemClickListener(this)
        rcy_main.adapter = rcyAdapter
    }

    private fun initViewPager() {
        viewModel.getImage("all") // 通知viewModel去调用livedata
        adapter = MyAdapter(viewModel.strList)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (position == viewModel.strList.size - 1) {
                    "已经是当时分类的最后一张图片了哦，没有下一张了。".showToast()
                }
                LogUtil.d("MainActivity", "viewModel.strList.size : ${viewModel.strList.size - 1}")
                LogUtil.d("MainActivity", "onPageSelected : ${position}")
            }
            override fun onPageScrollStateChanged(state: Int) {

            }

        })

    }

    private fun setStatusBar() {
        // 沉浸式状态栏
        val decorView = window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        // 将状态栏设置成透明色
        window.statusBarColor = Color.TRANSPARENT
    }

    // 分类列表的点击事件
    override fun onClick(role: Role, position: Int) {
        // 根据点击的分类请求新的图片信息
        viewModel.getImage(role.type)
        // 更改选择分类的状态
        resetItemCheckStatus()
        viewModel.roleList[position].isChecked = true
        rcyAdapter.notifyDataSetChanged()
        // true 表示平滑的滚动到位置0，false表示立即过渡到位置0
        viewPager.setCurrentItem(0, false)
        drawerLayout.closeDrawers()
    }

    fun resetItemCheckStatus() {
        val length = viewModel.roleList.size - 1
        for (i in 0..length) {
            viewModel.roleList[i].isChecked = false
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        // 清除内存缓存
//        Glide.get(this).clearMemory();
        // 清除硬盘缓存
//        Glide.get(this).clearDiskCache();
        LogUtil.d("MainActivity", "onDestroy")
    }


    // 重写返回键监听
    override fun onBackPressed() {
        //  super.onBackPressed(); 这句话一定要注释掉，不然又去调用默认的back处理方式了
        // 监听返回键，让返回键实现HOME键的功能
        // 实现HOME键功能，简而言之就是回到桌面，让Activity不销毁，程序后台运行
        val intent = Intent(Intent.ACTION_MAIN)
        /*
         * Intent.FLAG_ACTIVITY_NEW_TASK  使用一个新的 Task 来启动一个 Activity ，
         * 但是启动的每个 Activity 都将在一个新的 Task 中 ( 不然 NEW_TASK 这个称号不是白费了吗 )。
         * 这种方式通常使用在 Service 中启动 Activity 的情况，由于在 Service 中不存在 Activity 栈。
         * 所以使用该 Flag 来创建一个新的 Activity 栈。并创建新的 Activity 实例 。
         */intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)

    }


    // 设置drawerlayout全屏滑动
    private fun setDrawerLeftEdgeSize(
        activity: Activity?,
        drawerLayout: DrawerLayout?,
        displayWidthPercentage: Float
    ) {
        if (activity == null || drawerLayout == null) return
        try {
            // 找到 ViewDragHelper 并设置 Accessible 为true
            val leftDraggerField: Field =
                drawerLayout.javaClass.getDeclaredField("mRightDragger") //Left
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