package com.liukunup.next.service.impl;

import com.liukunup.next.bean.FieldCompareDO;
import com.liukunup.next.bean.HistoryDO;
import com.liukunup.next.bean.UserDO;
import com.liukunup.next.bean.entity.HistoryPO;
import com.liukunup.next.bean.entity.TestcasePO;
import com.liukunup.next.repository.HistoryRepository;
import com.liukunup.next.repository.TestcaseRepository;
import com.liukunup.next.service.IHistoryService;
import com.liukunup.next.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 执行记录
 * 管理服务
 *
 * @author Liu Kun
 * @date 2021-07-18 23:01:38
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service("historyServiceImpl")
public class HistoryServiceImpl implements IHistoryService {

    /**
     * 分页最大数量
     */
    private static final Integer MAX_PAGE_SIZE = 100;

    /**
     * 数据库接口
     */
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    TestcaseRepository testCaseRepository;

    /**
     * 用户访问服务
     */
    @Autowired
    IUserService userServiceImpl;

    @Override
    public boolean insertHistory(HistoryDO history) {
        try {
            // 判断输入是否为null
            if (null == history) {
                log.error("调用insertHistory函数时输入参数为null!");
                return false;
            }

            // 尝试获取用户
            UserDO user = null;
            String employeeId = history.getEmployeeId();
            if (!StringUtils.isEmpty(employeeId)) {
                UserDO tmpUser = new UserDO();
                tmpUser.setEmployeeId(employeeId);
                user = userServiceImpl.selectUser(tmpUser);
            }

            // 事先通过用例编号来查询用例
            TestcasePO tmpTestCase = testCaseRepository.findTestcasePOById(history.getTestcaseId());
            if (null == tmpTestCase) {
                log.error("调用insertHistory函数失败,输入参数中对应的用例编号{}不存在!无关联用例!", history.getTestcaseId());
                return false;
            }

            // 通过用例编号和任务编号确认执行记录是否存在
            List<HistoryPO> tmpHistoryList = historyRepository.findHistoryPOSByTestcaseAndTaskId(tmpTestCase, history.getTaskId());
            if (null != tmpHistoryList && tmpHistoryList.size() > 0) {

                return false;
            }

            // 获取任务编号
            String taskId = history.getTaskId();
            log.info("调用insertHistory函数时,当前的任务编号为{}", taskId);
            // 获取字段列表
            List<FieldCompareDO> fieldList = history.getFieldList();
            List<HistoryPO> historyList = new ArrayList<>();
            for (FieldCompareDO field : fieldList) {
                HistoryPO temp = new HistoryPO();
                temp.setTestcase(tmpTestCase);
                temp.setTaskId(taskId);
                // temp.setUser();
                temp.setField(field.getField());
                temp.setObject(field.getObject());
                temp.setReference(field.getReference());
                temp.setRemarks(field.getRemarks().toString());
                // 设置创建时间和更新时间一致
                Date date = new Date();
                temp.setGmtCreate(date);
                temp.setGmtModified(date);
                // 新增执行记录
                HistoryPO result = historyRepository.save(temp);
                // 打印当前插入的字段执行记录的信息
                log.info("当前持久化对象信息为{}", result);
                // 加入插入操作返回记录列表
                historyList.add(result);
            }

            // 判断每条新增执行记录是否均成功
            for (HistoryPO tmpHistory : historyList) {
                // 判断新增用户是否成功
                if ((tmpHistory.getId() != null) && (tmpHistory.getId() > 0)
                        && (tmpHistory.getGmtCreate() != null)
                        && (tmpHistory.getGmtModified() != null)) {
                    log.debug("调用insertHistory函数成功,已持久化的执行记录为{}", tmpHistory);
                } else {
                    log.warn("调用insertHistory函数,持久化执行记录不完全成功,请分析{}", historyList);
                    return false;
                }
            }

            // 完全成功
            log.debug("调用insertHistory函数成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.warn("调用insertHistory函数失败,输入的执行记录为{}", history);
        return false;
    }

    @Override
    public boolean deleteHistory(HistoryDO history) {
        try {
            // 判断输入是否为null
            if (null == history) {
                log.error("调用deleteHistory函数时输入参数为null!");
                return false;
            }

            // 事先通过用例编号来查询用例
            TestcasePO tmpTestCase = testCaseRepository.findTestcasePOById(history.getTestcaseId());
            if (null == tmpTestCase) {
                log.error("调用insertHistory函数失败,输入参数中对应的用例编号{}不存在!无关联用例!", history.getTestcaseId());
                return false;
            }

            // 通过用例编号和任务编号来删除执行记录
            historyRepository.deleteHistoryPOSByTestcaseAndTaskId(tmpTestCase, history.getTaskId());

            log.debug("调用insertHistory函数成功");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.warn("调用deleteHistory函数失败,输入的执行记录为{}", history);
        return false;
    }

    @Override
    public HistoryDO selectHistory(HistoryDO history) {
        try {
            // 判断输入是否为null
            if (null == history) {
                log.error("调用selectHistory函数时输入参数为null!");
                return null;
            }

            // 事先通过用例编号来查询用例
            TestcasePO tmpTestCase = testCaseRepository.findTestcasePOById(history.getTestcaseId());
            if (null == tmpTestCase) {
                log.error("调用insertHistory函数失败,输入参数中对应的用例编号{}不存在!无关联用例!", history.getTestcaseId());
                return null;
            }

            // 通过用例编号和任务编号来删除执行记录
            List<HistoryPO> tmpHistoryList = historyRepository.findHistoryPOSByTestcaseAndTaskId(tmpTestCase, history.getTaskId());
            if (null == tmpHistoryList || tmpHistoryList.isEmpty()) {

                return null;
            }

            HistoryDO tmpHistory = new HistoryDO();

            List<FieldCompareDO> fieldList = new ArrayList<>();

            for (HistoryPO tmp : tmpHistoryList) {
                FieldCompareDO field = new FieldCompareDO();
                field.setField(tmp.getField());
                field.setObject(tmp.getObject());
                field.setReference(tmp.getReference());
                field.setRemarks(tmp.getRemarks());
                fieldList.add(field);

                tmpHistory.setTestcaseId(tmp.getTestcase().getId());
                tmpHistory.setTaskId(tmp.getTaskId());
                tmpHistory.setEmployeeId(tmp.getUser().getEmployeeId());
            }

            tmpHistory.setFieldList(fieldList);

            log.debug("调用insertHistory函数成功");
            return tmpHistory;

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.warn("调用selectHistory函数失败,输入的执行记录为{}", history);
        return null;
    }

    @Override
    public Long countHistory(String taskId) {
        return null;
    }

    @Override
    public List<HistoryDO> listHistory(String taskId, Integer pageNo, Integer pageSize) {
        return null;
    }
}
