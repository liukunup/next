package com.liukunup.next.repository;

import com.liukunup.next.bean.entity.TestcasePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 测试用例执行记录
 * 数据库接口
 *
 * @author Liu Kun
 * @since 2020-07-25
 */
@Repository
public interface TestcaseRepository extends JpaRepository<TestcasePO, Long> {

    /**
     * 通过用例编号来查询用例
     *
     * @param id 用例编号
     * @return 用例
     */
    TestcasePO findTestcasePOById(Long id);

    /**
     * 通过用例编号来删除用例
     * @param id 用例编号
     */
    void deleteTestcasePOById(Long id);
}
