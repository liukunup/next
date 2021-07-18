package com.liukunup.next.repository;

import com.liukunup.next.bean.entity.HistoryPO;
import com.liukunup.next.bean.entity.TestcasePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 测试用例执行历史记录
 * 数据库接口
 *
 * @author Liu Kun
 * @since 2020-08-05
 */
@Repository
public interface HistoryRepository extends JpaRepository<HistoryPO, Long> {

    /**
     * 通过用例编号和任务编号查询执行记录
     *
     * @param testcase 用例编号
     * @param taskId 任务编号
     * @return 执行记录
     */
    List<HistoryPO> findHistoryPOSByTestcaseAndTaskId(TestcasePO testcase, String taskId);

    /**
     * 通过用例编号和任务编号删除执行记录
     *
     * @param testcase 用例编号
     * @param taskId 任务编号
     */
    void deleteHistoryPOSByTestcaseAndTaskId(TestcasePO testcase, String taskId);


    int countHistoryPOSByTaskId(String taskId);
}
