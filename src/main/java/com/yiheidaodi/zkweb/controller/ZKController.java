package com.yiheidaodi.zkweb.controller;

import com.yiheidaodi.zkweb.service.api.IZKService;
import com.yiheidaodi.zkweb.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * zkweb com.yiheidaodi.zkweb.controller
 * 描述：
 * 时间：2016-12-30 14:31.
 */

@Controller
@RequestMapping("/zk")
public class ZKController {

    @Autowired
    private IZKService zkService;

    @ResponseBody
    @RequestMapping(value = "/getChildren")
    public Map<String, Object> getChildren(String path) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<String> children = zkService.getChildren(path);
        dataMap.put("children", children);
        return RespUtil.success(dataMap);
    }

}
