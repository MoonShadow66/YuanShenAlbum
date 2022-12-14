package com.liang.yuanshenalbum.ui.main

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.liang.yuanshenalbum.R

class MyAdapter(private val strList: List<String>) :
    PagerAdapter() {

    /**
     * 获取要滑动的控件的数量
     */
    override fun getCount() = strList.size

    /**
     * 判断显示的是否是同个控件，一般将两个参数相比较返回即可
     */
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    /**
     * 当要显示的页面可以进行缓存的时候，会调用这个方法进行显示页面的初始化，
     * 我们将要显示的控件(viewLists.get(position)从集合中获取View)加入到ViewGroup中，然后作为返回值返回即可
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(container.context).load(strList[position]).error(R.drawable.error).into(imageView)
        container.addView(imageView)
        return imageView
    }

    /**
     * PagerAdapter只缓存三个页面，如果滑动的页面超出了缓存的范围，
     * 就会调用这个方法，将页面销毁
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

}