package com.liang.yuanshenalbum.util

object ImageResource {

    private const val BASE_URL = "http://10.69.32.169/yuan"

    // 现有角色及图片数量
    val roleMap = mapOf(
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