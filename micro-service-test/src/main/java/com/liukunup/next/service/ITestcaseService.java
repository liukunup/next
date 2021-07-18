package com.liukunup.next.service;

import com.liukunup.next.bean.TestcaseDO;

import java.util.List;

/**
 * 测试用例
 * 管理服务接口
 *
 * @author Liu Kun
 * @date 2020-07-25
 */
public interface ITestcaseService {

    /**
     * 新增一条测试用例
     *
     * @param testcase 测试用例
     * @return 新增操作执行结果
     */
    boolean insertTestcase(TestcaseDO testcase);

    /**
     * 删除一条测试用例
     *
     * @param testcase 测试用例
     * @return 删除操作执行结果
     */
    boolean deleteTestcase(TestcaseDO testcase);

    /**
     * 修改一条测试用例
     *
     * @param testcase 测试用例
     * @return 修改操作执行结果
     */
    boolean updateTestcase(TestcaseDO testcase);

    /**
     * 查询一条测试用例
     *
     * @param testcase 测试用例
     * @return 删除操作执行结果
     */
    TestcaseDO selectTestcase(TestcaseDO testcase);

    /**
     * 统计当前测试用例执行记录总数量
     *
     * @return 总测试用例数
     */
    Long countTestcase();

    /**
     * 分页列举测试用例执行记录
     *
     * @param pageNo   页码
     * @param pageSize 每页数量
     * @return 测试用例执行记录列表
     */
    List<TestcaseDO> listTestcase(Integer pageNo, Integer pageSize);
}
