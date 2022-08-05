package com.liang.yuanshenalbum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.liang.yuanshenalbum.ui.home.VerticalPageTransformer
import com.liang.yuanshenalbum.ui.home.VerticalViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = listOf<String>(
            "http://10.69.32.169/img/ganyu/ganyu1.jpeg",
            "http://10.69.32.169/img/ganyu/ganyu3.jpeg",
            "http://10.69.32.169/img/ganyu/ganyu4.jpeg",
            "http://10.69.32.169/img/ganyu/ganyu5.jpeg",
            "http://10.69.32.169/img/ganyu/ganyu6.jpeg",
            "http://10.69.32.169/img/ganyu/ganyu7.jpeg"
        )

        val vptf = VerticalPageTransformer()
        val adapter = VerticalViewPagerAdapter(supportFragmentManager,list)
        vp_home.adapter = adapter
        vp_home.setPageTransformer(false, vptf)

    }
}