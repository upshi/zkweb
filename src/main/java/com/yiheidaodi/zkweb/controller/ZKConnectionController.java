package com.yiheidaodi.zkweb.controller;

import com.yiheidaodi.zkweb.domain.ZKConnection;
import com.yiheidaodi.zkweb.service.api.IZKConnectionService;
import com.yiheidaodi.zkweb.util.ClientFactory;
import com.yiheidaodi.zkweb.util.RespUtil;
import com.yiheidaodi.zkweb.util.StringUtil;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * zkweb com.yiheidaodi.zkweb.controller
 * 描述：
 * 时间：2016-12-30 10:02.
 */

@Controller
@RequestMapping("/zkConn")
public class ZKConnectionController {

    @Autowired
    private IZKConnectionService zkConnectionService;

    @ResponseBody
    @RequestMapping(value = "/createConn")
    public Map<String, Object> createConn(ZKConnection zkConn) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        zkConn.setUuid(StringUtil.uuid());
        int count = zkConnectionService.createConn(zkConn);
        if(count > 0) {
            dataMap.put("zkConn", zkConn);
            return RespUtil.success(dataMap);
        } else {
            return RespUtil.error("创建连接失败");
        }

    }

    @ResponseBody
    @RequestMapping(value = "/connect")
    public Map<String, Object> connect(String uuid, HttpSession session) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        ZKConnection zkConn = zkConnectionService.get(uuid);
        if(zkConn == null) {
            return RespUtil.error("连接不存在");
        } else {
            CuratorFramework client = ClientFactory.createSimple(zkConn.connectionString());
            client.start();
            session.setAttribute("zkConn", zkConn);
            session.setAttribute("client", client);
            dataMap.put("zkConn", zkConn);
            return RespUtil.success(dataMap);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public Map<String, Object> list() {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<ZKConnection> zkConns = zkConnectionService.getAll();
        dataMap.put("zkConns", zkConns);
        return RespUtil.success(dataMap);
    }

}
