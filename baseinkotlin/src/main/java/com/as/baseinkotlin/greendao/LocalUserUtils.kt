package com.`as`.baseinkotlin.greendao


import com.`as`.baseinkotlin.application.BaseApplication
import com.`as`.greendao.gen.UserDao

/**
 * 操作 User 数据库的 工具类
 */

object LocalUserUtils {

    /**
     * 只会有一个用户数据 id为 1l
     * @return
     */
    val user: User
        get() = BaseApplication.getDaoInstant().userDao.queryBuilder().where(UserDao.Properties.Id.eq(1L)).uniqueOrThrow()

    /**
     * 添加数据，如果有重复则覆盖
     */
    fun insertUserInfo(userInfo: User) {
        BaseApplication.getDaoInstant().userDao.insertOrReplace(userInfo)
    }

    /**
     * 删除数据
     */
    fun deleteAllUserInfo() {
        BaseApplication.getDaoInstant().userDao.deleteAll()
    }

    /**
     * 更新数据
     */
    fun updateUserInfo(userInfo: User) {
        BaseApplication.getDaoInstant().userDao.update(userInfo)
    }

    //    /**
    //     * 查询条件为Type=TYPE_LOVE的数据
    //     *
    //     * @return
    //     */
    //    public static User queryMusic(int uid) {
    //        User User = BaseApplication.getDaoInstant().getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(uid)).uniqueOrThrow();
    //        return User;
    //    }
    //
    //    /**
    //     * 查询全部数据
    //     */
    //    public static User queryAll() {
    //        List<User> userInfos = BaseApplication.getDaoInstant().getUserDao().loadAll();
    //        if (userInfos.size() == 0) {
    //            return null;
    //        }
    //        return userInfos.get(0);
    //    }
    //
    //    /**
    //     * 查询条件为Type=TYPE_LOVE的数据
    //     *
    //     * @return
    //     */
    //    public static User queryIdMusic(Long id) {
    //        User User = BaseApplication.getDaoInstant().getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(id)).uniqueOrThrow();
    //        return User;
    //    }
    //
    //    /**
    //     * 查询全部数据
    //     */
    //    public static int querySortId() {
    //        return BaseApplication.getDaoInstant().getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(1L)).uniqueOrThrow().getSortid();
    //    }
}
