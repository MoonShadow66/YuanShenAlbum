package com.liang.yuanshenalbum.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.liang.yuanshenalbum.R
import com.liang.yuanshenalbum.logic.model.Role
import com.liang.yuanshenalbum.showToast
import com.liang.yuanshenalbum.util.LogUtil
import kotlinx.android.synthetic.main.activity_main.*


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
        viewPager.isLongClickable = true
        // 监听滑动事件，右滑时打开drawerLayout
        viewPager.setOnTouchListener(MyGestureListener(this, drawerLayout))
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
    override fun onClick(role: Role) {
        viewModel.getImage(role.type)
        // true 表示平滑的滚动到位置0，false表示立即过渡到位置0
        viewPager.setCurrentItem(0, false)
        drawerLayout.closeDrawers()
    }

    override fun onDestroy() {
        super.onDestroy()
        // 清除内存缓存
//        Glide.get(this).clearMemory();
        // 清除硬盘缓存
//        Glide.get(this).clearDiskCache();
        LogUtil.d("MainActivity", "onDestroy")
    }

}