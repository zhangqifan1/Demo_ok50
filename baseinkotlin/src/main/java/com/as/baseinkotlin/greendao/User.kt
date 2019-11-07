package com.`as`.baseinkotlin.greendao

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id
import org.greenrobot.greendao.annotation.Generated

/**
 * -----------------------------
 * Created by zqf on 2019/10/23.
 * 用来存放用户基础数据
 * ---------------------------
 */
@Entity
class User {

    // 这个greenndao要求的一个 应该是用来设置表id  只能用long
    @Id(autoincrement = true)
    var id: Long? = null

    //用户名
    var name: String? = null

    //手机号
    var phoneNumber: String? = null

    //头像 可能是 url  或者 resid
    var icon: String? = null

    @Generated(hash = 677803454)
    constructor(id: Long?, name: String, phoneNumber: String, icon: String) {
        this.id = id
        this.name = name
        this.phoneNumber = phoneNumber
        this.icon = icon
    }

    @Generated(hash = 586692638)
    constructor() {
    }

}
