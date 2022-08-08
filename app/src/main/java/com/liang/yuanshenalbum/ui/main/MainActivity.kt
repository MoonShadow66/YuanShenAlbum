package com.liang.yuanshenalbum.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.liang.yuanshenalbum.R
import com.liang.yuanshenalbum.logic.dao.Role
import com.liang.yuanshenalbum.util.ImageResource
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnRcyItemClickListener {

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private lateinit var adapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBar()
        initViewPager()
        initRcy()
    }

    private fun initRcy() {
        viewModel.roleList.addAll(ImageResource.getRoleList())
        val layoutManager = LinearLayoutManager(this)
        rcy_main.layoutManager = layoutManager
        val rcyAdapter = RcyAdapter(viewModel.roleList)
        rcyAdapter.setOnRcyItemClickListener(this)
        rcy_main.adapter = rcyAdapter

    }

    private fun initViewPager() {
        // 根据图片网络地址的数量动态创建 imageView
        viewModel.strList.addAll(ImageResource.getAllImageUrl())
        for (url in viewModel.strList) {
            val imageView = ImageView(this)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            viewModel.viewList.add(imageView)
        }
        adapter = MyAdapter(viewModel.strList, viewModel.viewList)
        viewPager.adapter = adapter
    }


    // 刷新数据
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

    override fun onClick(role: Role) {
        changeData(role.type)
        drawerLayout.closeDrawers()
    }

}