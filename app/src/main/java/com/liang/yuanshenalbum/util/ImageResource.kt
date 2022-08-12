package com.liang.yuanshenalbum.util

import com.liang.yuanshenalbum.MyApplication
import com.liang.yuanshenalbum.R
import com.liang.yuanshenalbum.logic.model.Role

object ImageResource {

    const val BASE_URL = "http://10.69.32.169/yuan"

    // 现有角色及图片数量
    val roleMap = mapOf(
        "all" to 195,
        "group" to 15,
        "other" to 6,
        "babala" to 1,
        "feixieer" to 31,
        "ganyu" to 31,
        "hutao" to 21,
        "keqing" to 7,
        "linghua" to 24,
        "mona" to 8,
        "ningguang" to 1,
        "nuoaier" to 2,
        "shaonu" to 3,
        "shenhe" to 3,
        "shenzi" to 9,
        "xiangling" to 1,
        "xiaogong" to 5,
        "xinhai" to 3,
        "ying" to 11,
        "youla" to 8,
        "yunjin" to 1,
    )

    // 获取所有分类信息
    fun getRoleList(): List<Role> {
        val resources = MyApplication.context.resources
        val list = listOf(
            Role(resources.getString(R.string.all), "all"),
            Role(resources.getString(R.string.group), "group"),
            Role(resources.getString(R.string.other), "other"),
            Role(resources.getString(R.string.babala), "babala"),
            Role(resources.getString(R.string.feixieer), "feixieer"),
            Role(resources.getString(R.string.ganyu), "ganyu"),
            Role(resources.getString(R.string.hutao), "hutao"),
            Role(resources.getString(R.string.keqing), "keqing"),
            Role(resources.getString(R.string.linghua), "linghua"),
            Role(resources.getString(R.string.mona), "mona"),
            Role(resources.getString(R.string.ningguang), "ningguang"),
            Role(resources.getString(R.string.nuoaier), "nuoaier"),
            Role(resources.getString(R.string.shaonu), "shaonu"),
            Role(resources.getString(R.string.shenhe), "shenhe"),
            Role(resources.getString(R.string.shenzi), "shenzi"),
            Role(resources.getString(R.string.xiangling), "xiangling"),
            Role(resources.getString(R.string.xiaogong), "xiaogong"),
            Role(resources.getString(R.string.xinhai), "xinhai"),
            Role(resources.getString(R.string.ying), "ying"),
            Role(resources.getString(R.string.youla), "youla"),
            Role(resources.getString(R.string.yunjin), "yunjin"),
        )

        // 默认选中第一个（全部）
        list[0].isChecked = true

        return list
    }

    // 根据分类得到分类图片
    fun getImageUrlByName(name: String, isRandom: Boolean = true): List<String> {
        // 根据分类名称得到分类数量
        val length = roleMap[name] ?: 0

        val list = ArrayList<String>()
        // 获取 0 ~ length-1 的不重复随机索引
        val index = random(length)

        for (i in 1..length) {
            if (isRandom) {
                val number = index[i - 1] // 因为index是从0开始取，所以index[i-1]
                list.add("$BASE_URL/${name}/${name}${number}.jpg")
//                LogUtil.d("ImageResource", "$BASE_URL/${name}/${name}${number}.jpg")
            } else {
                list.add("$BASE_URL/${name}/${name}${i}.jpg")
            }
        }

        return list
    }

    // 返回一个Int类型的数组，其元素是1 ~ count的不重复的随机数
    private fun random(count: Int): Array<Int> {
        // 创建一个Int类型的数组，元素为1 ~ count
        val tempArr = Array<Int>(count) { i -> i + 1 }
        tempArr.sortBy { 0.5 - Math.random() }
        return tempArr
    }


}