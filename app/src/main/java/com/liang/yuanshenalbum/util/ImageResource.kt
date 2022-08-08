package com.liang.yuanshenalbum.util

import com.liang.yuanshenalbum.MyApplication
import com.liang.yuanshenalbum.R
import com.liang.yuanshenalbum.logic.dao.Role

object ImageResource {

    private const val BASE_URL = "http://10.69.32.169/yuan"

    // 现有角色及图片数量
    private val roleMap = mapOf(
        "group" to 5,
        "feixieer" to 31,
        "ganyu" to 22,
        "hutao" to 1,
        "keqing" to 1,
        "linghua" to 22,
        "mona" to 4,
        "nuoaier" to 1,
        "shaonu" to 1,
        "shenzi" to 8,
        "xinhai" to 2,
        "xiaogong" to 5,
        "ying" to 5,
    )


    fun getRoleList(): List<Role> {

        val resources = MyApplication.context.resources
        val roleList =
            listOf(
                Role(resources.getString(R.string.group), "group"),
                Role(resources.getString(R.string.feixieer), "feixieer"),
                Role(resources.getString(R.string.ganyu), "ganyu"),
                Role(resources.getString(R.string.hutao), "hutao"),
                Role(resources.getString(R.string.keqing), "keqing"),
                Role(resources.getString(R.string.linghua), "linghua"),
                Role(resources.getString(R.string.mona), "mona"),
                Role(resources.getString(R.string.nuoaier), "nuoaier"),
                Role(resources.getString(R.string.shaonu), "shaonu"),
                Role(resources.getString(R.string.shenzi), "shenzi"),
                Role(resources.getString(R.string.xinhai), "xinhai"),
                Role(resources.getString(R.string.xiaogong), "xiaogong"),
                Role(resources.getString(R.string.ying), "ying"),
            )

        return roleList
    }

    // 返回所有图片网络路径
    fun getAllImageUrl(): List<String> {
        val list = ArrayList<String>()
        for (i in 1..109) {
            list.add("$BASE_URL/all/img${i}.jpg")
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