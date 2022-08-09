package com.liang.yuanshenalbum.ui.main

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.liang.yuanshenalbum.util.LogUtil

class MyAdapter(private val strList: List<String>, private val viewList: List<ImageView>) :
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
        Glide.with(container.context).load(strList[position]).into(viewList[position])
        LogUtil.d("MyAdapter",strList[position])
        container.addView(viewList[position])
        return viewList[position]
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