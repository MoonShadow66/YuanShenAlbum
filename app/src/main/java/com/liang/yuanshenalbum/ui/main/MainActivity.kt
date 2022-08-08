package com.liang.yuanshenalbum.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.liang.yuanshenalbum.R
import com.liang.yuanshenalbum.util.ImageResource
import com.liang.yuanshenalbum.util.LogUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private lateinit var adapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBar()
        initViewPager()
        initNav()
    }

    private fun initViewPager() {
        // 根据图片网络地址的数量动态创建 imageView
        viewModel.strList.addAll(ImageResource.getAllImageUrl())
        for (url in viewModel.strList) {
            val imageView = ImageView(this)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            viewModel.viewList.add(imageView)
            LogUtil.d("MainActivity", url)
        }
        adapter = MyAdapter(viewModel.strList, viewModel.viewList)
        viewPager.adapter = adapter
    }

    private fun initNav() {
        navView.setCheckedItem(R.id.nav_group)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_all -> {

                }
                R.id.nav_group -> {
                    changeData("group")
                }
                R.id.nav_ganyu -> {
                    changeData("ganyu")
                }
                R.id.nav_linghua -> {
                    changeData("linghua")
                }
                R.id.nav_feixieer -> {
                    changeData("feixieer")
                }
            }

            drawerLayout.closeDrawers()
            true
        }
    }

    private fun changeData(name: String) {
        // 清空之前的数据
        viewModel.strList.clear()
        viewModel.viewList.clear()

        viewModel.strList.addAll(ImageResource.getImageUrlByName(name))
        for (url in viewModel.strList) {
            val imageView = ImageView(this)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            viewModel.viewList.add(imageView)
        }

        adapter.notifyDataSetChanged()
        // true 表示平滑的滚动到位置0，false表示立即过渡到位置0
        viewPager.setCurrentItem(0, false)

    }

    private fun setStatusBar() {
        // 沉浸式状态栏
        val decorView = window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        // 将状态栏设置成透明色
        window.statusBarColor = Color.TRANSPARENT
    }

}