package com.liukunup.next.repository;

import com.liukunup.next.bean.entity.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户
 * 数据库接口
 *
 * @author Liu Kun
 * @since 2020-08-05
 */
@Repository
public interface UserRepository extends JpaRepository<UserPO, Long> {

    /**
     * 通过工号查询用户
     * @param employeeId 工号
     * @return 用户
     */
    UserPO findUserPOByEmployeeId(String employeeId);

    /**
     * 通过工号删除用户
     * @param employeeId 工号
     */
    void deleteUserPOByEmployeeId(String employeeId);
}
