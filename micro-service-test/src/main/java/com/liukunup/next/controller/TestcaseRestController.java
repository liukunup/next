package com.liukunup.next.controller;

import com.liukunup.next.bean.ApiResult;
import com.liukunup.next.bean.TestcaseDO;
import com.liukunup.next.constant.RetCode;
import com.liukunup.next.service.ITestcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 测试用例
 * 管理服务接口
 *
 * @author Liu Kun
 * @since 2021-07-18 22:50:15
 */
@RestController
@RequestMapping("/api/testcase")
public class TestcaseRestController {

    @Autowired
    ITestcaseService testcaseServiceImpl;

    /**
     * 新增一条用例
     * 尽可能详细描述
     *
     * @param testcase 用例信息
     * @return 操作执行结果 成功或失败
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> add(@RequestBody TestcaseDO testcase) {
        return new ApiResult<>(RetCode.FAILED,null);
    }

    /**
     * 删除一条用例
     * 通过用例编号来删除
     * 通常建议先查询再删除
     *
     * @param testcase 用例信息 仅需要填写用例编号
     * @return 操作执行结果 成功或失败
     */
    @RequestMapping(value="/del", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> del(@RequestBody TestcaseDO testcase) {
        return new ApiResult<>(RetCode.FAILED, null);
    }

    /**
     * 更新一条用例
     * 通过用例编号来更新
     * 通常建议先查询再更新
     *
     * @param testcase 用例信息
     * @return 操作执行结果 成功或失败
     */
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> update(@RequestBody TestcaseDO testcase) {
        return new ApiResult<>(RetCode.FAILED, null);
    }

    /**
     * 查找一条用例
     * 支持模糊查找
     *
     * @param testcase 用例信息
     * @return 查找结果
     */
    @RequestMapping(value="/get", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> get(@RequestBody TestcaseDO testcase) {
        return new ApiResult<>(RetCode.FAILED, null);
    }

    /**
     * 统计用例数量
     * 通过标签列表来统计某一批用例的数量
     *
     * @param labels 标签列表
     * @return 符合条件的用例数量
     */
    @RequestMapping(value="/count", method = RequestMethod.GET)
    public @ResponseBody
    ApiResult<Object> count(@RequestParam(value = "tags") Integer labels) {
        return new ApiResult<>(RetCode.FAILED, null);
    }

    /**
     * 列举用例信息
     * 通过标签列表结合页码和页大小来列举
     * 通常和count接口结合使用
     *
     * @param labels 标签列表
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return 符合条件的用例
     */
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public @ResponseBody
    ApiResult<Object> list(@RequestParam(value = "tags") Integer labels,
                   @RequestParam(value = "no") Integer pageNo,
                   @RequestParam(value = "size") Integer pageSize) {
        return new ApiResult<>(RetCode.FAILED, null);
    }
}
