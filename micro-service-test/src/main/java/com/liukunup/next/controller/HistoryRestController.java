package com.liukunup.next.controller;

import com.liukunup.next.bean.ApiResult;
import com.liukunup.next.bean.HistoryDO;
import com.liukunup.next.constant.RetCode;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 测试用例 执行历史记录
 * 管理服务接口
 *
 * @author Liu Kun
 * @since 2021-07-18 22:59:54
 */
@RestController
@RequestMapping("/api/history")
public class HistoryRestController {

    /**
     * 新增一条记录
     * 尽可能详细描述
     *
     * @param history 记录信息
     * @return 操作执行结果 成功或失败
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> add(@RequestBody HistoryDO history) {
        return new ApiResult<>(RetCode.FAILED, null);
    }

    /**
     * 删除一条记录
     * 通过用例编号和任务编号来删除
     *
     * @param history 记录信息 仅需要填写用例编号和任务编号
     * @return 操作执行结果 成功或失败
     */
    @RequestMapping(value="/del", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> del(@RequestBody HistoryDO history) {
        return new ApiResult<>(RetCode.FAILED, null);
    }

    /**
     * 统计记录数量
     * 通过任务编号来统计某一轮的数量
     *
     * @param taskId 任务编号
     * @return 符合条件的记录数量
     */
    @RequestMapping(value="/count", method = RequestMethod.GET)
    public @ResponseBody
    ApiResult<Object> count(@RequestParam(value = "tid") Integer taskId) {
        return new ApiResult<>(RetCode.FAILED, null);
    }

    /**
     * 列举记录信息
     * 通过任务编号结合页码和页大小来列举
     * 通常和count接口结合使用
     *
     * @param taskId 任务编号
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return 符合条件的记录
     */
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public @ResponseBody
    ApiResult<Object> list(@RequestParam(value = "tid") Integer taskId,
                   @RequestParam(value = "no") Integer pageNo,
                   @RequestParam(value = "size") Integer pageSize) {
        return new ApiResult<>(RetCode.FAILED, null);
    }
}
