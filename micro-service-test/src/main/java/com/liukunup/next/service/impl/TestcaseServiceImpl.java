package com.liukunup.next.service.impl;

import com.liukunup.next.bean.TestcaseDO;
import com.liukunup.next.bean.entity.LabelPO;
import com.liukunup.next.bean.entity.TestcasePO;
import com.liukunup.next.repository.TestcaseRepository;
import com.liukunup.next.service.ITestcaseService;
import com.liukunup.next.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试用例执行记录
 * 管理服务
 *
 * @author Liu Kun
 * @date 2021-07-18 22:54:08
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service("testcaseServiceImpl")
public class TestcaseServiceImpl implements ITestcaseService {

    /**
     * 分页最大数量
     */
    private static final Integer MAX_PAGE_SIZE = 100;

    /**
     * 数据库访问对象
     */
    @Autowired
    TestcaseRepository testcaseRepository;

    /**
     * 用户管理服务实例
     */
    @Autowired
    IUserService userServiceImpl;

    @Override
    public boolean insertTestcase(TestcaseDO testcase) {

        // 输入参数不能为null
        if (null == testcase) {
            log.error("输入参数为null");
            return false;
        }

        // 输入 输入参数 不能为null或空串
        String param = testcase.getParam();
        if (null == param || param.isEmpty()) {
            log.error("输入 输入参数 为null或空串");
            return false;
        }

        // 设置创建时间和更新时间一致
        Date date = new Date();

        // 构建 测试用例 持久化对象
        TestcasePO tmpTestcase = new TestcasePO();
        tmpTestcase.setParam(testcase.getParam());
        tmpTestcase.setExcept(testcase.getExcept());
        tmpTestcase.setDescription(testcase.getDescription());
        tmpTestcase.setGmtCreate(date);
        tmpTestcase.setGmtModified(date);
        // tmpTestcase.setUser();

        List<LabelPO> labelList = new ArrayList<>();
        // 获取标签信息
        String labels = testcase.getLabels();
        String[] labelArray = labels.split(",");
        for (String label : labelArray) {
            // 构建 标签 持久化对象
            LabelPO tmpLabel = new LabelPO();
            tmpLabel.setContent(label);
            tmpLabel.setGmtCreate(date);
            tmpLabel.setGmtModified(date);
            // tmpLabel.setUser();
            labelList.add(tmpLabel);
        }
        tmpTestcase.setLabelList(labelList);

        // 插入数据库
        testcaseRepository.save(tmpTestcase);

        return true;
    }

    @Override
    public boolean deleteTestcase(TestcaseDO testcase) {
        try {
            // 判断输入是否为null
            if (null == testcase) {
                log.error("调用deleteHistory函数时输入参数为null!");
                return false;
            }

            testcaseRepository.deleteTestcasePOById(testcase.getId());

            log.debug("调用insertHistory函数成功");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.warn("调用deleteHistory函数失败,输入的执行记录为{}", testcase);
        return false;
    }

    @Override
    public boolean updateTestcase(TestcaseDO testcase) {
        try {
            // 判断输入是否为null
            if (null == testcase) {
                log.error("调用deleteHistory函数时输入参数为null!");
                return false;
            }

            TestcasePO tmpTestcase = testcaseRepository.findTestcasePOById(testcase.getId());

            tmpTestcase.setExcept(testcase.getExcept());

            log.debug("调用insertHistory函数成功");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.warn("调用deleteHistory函数失败,输入的执行记录为{}", testcase);
        return false;
    }

    @Override
    public TestcaseDO selectTestcase(TestcaseDO testcase) {
        try {
            // 判断输入是否为null
            if (null == testcase) {
                log.error("调用deleteHistory函数时输入参数为null!");
                return null;
            }

            TestcasePO tmpTestcase = testcaseRepository.findTestcasePOById(testcase.getId());

            log.debug("调用insertHistory函数成功");
            return po2dto(tmpTestcase);

        } catch (Exception e) {
            e.printStackTrace();
        }

        log.warn("调用deleteHistory函数失败,输入的执行记录为{}", testcase);
        return null;
    }

    @Override
    public Long countTestcase() {
        // 统计已有测试用例执行记录总数量
        return testcaseRepository.count();
    }

    @Override
    public List<TestcaseDO> listTestcase(Integer pageNo, Integer pageSize) {

        // 检查传入的页码参数
        if (null == pageNo || pageNo <= 0) {
            return null;
        }

        // 检查传入的分页参数
        if (null == pageSize || pageSize > MAX_PAGE_SIZE) {
            return null;
        }

        // 分页查询
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<TestcasePO> testcasePage = testcaseRepository.findAll(pageable);

        // 取出用户信息
        List<TestcaseDO> result = new ArrayList<>();
        for(TestcasePO user : testcasePage) {
            result.add(po2dto(user));
        }

        return result;
    }

    /**
     * PO转DTO
     *
     * @param po 持久化对象
     * @return 数据传输对象
     */
    private TestcaseDO po2dto(TestcasePO po) {

        // 输入PO不能为null
        if (null == po) {
            return null;
        }

        // PO转DTO
        TestcaseDO dto = new TestcaseDO();
        dto.setParam(po.getParam());
        dto.setExcept(po.getExcept());
        dto.setDescription(po.getDescription());

        return dto;
    }
}
