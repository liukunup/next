package com.liukunup.next.service;

import com.liukunup.next.bean.UserDO;

import java.util.List;

/**
 * 用户
 * 管理服务接口
 *
 * @author Liu Kun
 * @since 2020-08-05
 */
public interface IUserService {

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 新增操作结果
     */
    boolean insertUser(UserDO user);

    /**
     * 删除用户
     * 通过工号删除
     *
     * @param user 用户信息
     * @return 删除操作结果
     */
    boolean deleteUser(UserDO user);

    /**
     * 更新用户
     * 通过工号更新
     *
     * @param user 用户信息
     * @return 更新操作结果
     */
    boolean updateUser(UserDO user);

    /**
     * 查询用户
     * 通过工号查询
     *
     * @param user 用户信息
     * @return 查询结果
     */
    UserDO selectUser(UserDO user);

    /**
     * 分页列举用户
     *
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return 用户列表
     */
    List<UserDO> listUser(Integer pageNo, Integer pageSize);
}
