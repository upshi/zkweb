package com.yiheidaodi.zkweb.service.impl;

import com.yiheidaodi.zkweb.dto.ClientLocal;
import com.yiheidaodi.zkweb.service.api.IZKService;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * zkweb com.yiheidaodi.zkweb.service.impl
 * 描述：
 * 时间：2016-12-30 14:30.
 */

@Service
public class ZKServiceImpl implements IZKService {

    @Override
    public List<String> getChildren(String path) {
        CuratorFramework client = ClientLocal.clientLocal.get();
        List<String> children = null;
        try {
            children = client.getChildren().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return children;
    }
}
