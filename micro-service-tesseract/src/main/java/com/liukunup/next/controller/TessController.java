package com.liukunup.next.controller;

import com.liukunup.next.bean.ApiResult;
import com.liukunup.next.bean.TessParam;
import com.liukunup.next.service.ITessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Tess Controller
 * @author Liu Kun
 * @date 2021-06-20 15:33:16
 **/
@RestController
public class TessController {

    @Autowired
    ITessService tessService;

    @RequestMapping(value = "/api/ocr/tesseract", method = RequestMethod.POST)
    public @ResponseBody
    ApiResult<Object> doOCR(@RequestBody TessParam param) {
        return tessService.process(param);
    }
}
