package com.liang.yuanshenalbum.util

import com.liang.yuanshenalbum.MyApplication
import com.liang.yuanshenalbum.R
import com.liang.yuanshenalbum.logic.model.Role

object ImageResource {

    private const val BASE_URL = "http://10.69.32.169/yuan"

    // 现有角色及图片数量
    private val roleMap = mapOf(
        "group" to 15,
        "other" to 6,
        "babala" to 1,
        "feixieer" to 31,
        "ganyu" to 31,
        "hutao" to 21,
        "keqing" to 6,
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
        "ying" to 12,
        "youla" to 8,
        "yunjin" to 1,
    )


    fun getRoleList(): List<Role> {

        val resources = MyApplication.context.resources

        return listOf(
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
    }

    // 返回所有图片网络路径
    fun getAllImageUrl(): List<String> {
        val list = ArrayList<String>()
        for (i in 1..109) {
            val random = (1..109).random()
            list.add("$BASE_URL/all/img${random}.jpg")
        }
        return list
    }

    // 根据分类得到分类图片
    fun getImageUrlByName(name: String): List<String> {
        // 根据分类名称得到分类数量
        val length = roleMap[name] ?: 0

        val list = ArrayList<String>()
        for (i in 1..length) {
            list.add("$BASE_URL/${name}/${name}${i}.jpg")
        }
        return list
    }


}