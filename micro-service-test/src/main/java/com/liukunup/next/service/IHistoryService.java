package com.liukunup.next.service;

import com.liukunup.next.bean.HistoryDO;

import java.util.List;

/**
 * 执行记录(测试用例对应)
 * 管理服务接口
 *
 * @author Liu Kun
 * @date 2020-07-25
 */
public interface IHistoryService {

    /**
     * 新增一条执行记录
     *
     * @param history 执行记录
     * @return 新增操作执行结果
     */
    boolean insertHistory(HistoryDO history);

    /**
     * 删除一条执行记录
     * 需要指定任务编号和用例编号
     *
     * @param history 执行记录
     * @return 删除操作执行结果
     */
    boolean deleteHistory(HistoryDO history);

    /**
     * 查询一条执行记录
     * 需要指定任务编号和用例编号
     *
     * @param history 执行记录
     * @return 查询操作执行结果
     */
    HistoryDO selectHistory(HistoryDO history);

    /**
     * 统计当前执行记录总数量
     * 需要指定任务编号
     *
     * @param taskId 任务编号
     * @return 对应任务编号的总记录数
     */
    Long countHistory(String taskId);

    /**
     * 分页列举执行记录
     *
     * @param taskId   任务编号
     * @param pageNo   页码
     * @param pageSize 每页数量
     * @return 执行记录列表
     */
    List<HistoryDO> listHistory(String taskId, Integer pageNo, Integer pageSize);
}
