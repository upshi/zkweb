package com.yiheidaodi.zkweb.service.impl;

import com.yiheidaodi.zkweb.domain.ZKConnection;
import com.yiheidaodi.zkweb.service.api.IZKConnectionService;
import com.yiheidaodi.zkweb.util.SQLiteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * zkweb com.yiheidaodi.zkweb.service.impl
 * 描述：
 * 时间：2016-12-30 10:04.
 */

@Service
public class ZKConnectionServiceImpl implements IZKConnectionService {

    @Autowired
    private SQLiteUtil sqLiteUtil;

    @Override
    public int createConn(ZKConnection zkConn) {
        return sqLiteUtil.add(zkConn);
    }

    @Override
    public ZKConnection get(String uuid) {
        return sqLiteUtil.selectByUuid(uuid);
    }

    @Override
    public List<ZKConnection> getAll() {
        return sqLiteUtil.selectAll();
    }
}
