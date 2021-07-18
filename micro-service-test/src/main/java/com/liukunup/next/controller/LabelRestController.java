package com.liukunup.next.controller;

import com.liukunup.next.bean.ApiResult;
import com.liukunup.next.bean.ContentDTO;
import com.liukunup.next.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签
 * 管理服务接口
 *
 * @author Liu Kun
 * @since 2021-07-18 23:02:24
 */
@RestController
@RequestMapping("/api/label")
public class LabelRestController {

    @Autowired
    ILabelService labelServiceImpl;

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> addLabel(@RequestBody ContentDTO label) {
        return ApiResult.check(labelServiceImpl.insertLabel(label), label);
    }

    @RequestMapping(value="/del", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> delLabel(@RequestBody ContentDTO label) {
        return ApiResult.check(labelServiceImpl.deleteLabel(label), label);
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> updateLabel(@RequestBody ContentDTO label) {
        return ApiResult.check(labelServiceImpl.updateLabel(label), label);
    }

    @RequestMapping(value="/find", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> selectLabel(@RequestBody ContentDTO label) {
        ContentDTO content = labelServiceImpl.selectLabel(label);
        return ApiResult.check((null != content), content);
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public @ResponseBody
    ApiResult<Object> listLabel(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
        List<ContentDTO> contentList = labelServiceImpl.listLabel(pageNo, pageSize);
        return ApiResult.check((null != contentList), contentList);
    }
}
