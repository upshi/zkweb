package com.yiheidaodi.zkweb.service.api;

import com.yiheidaodi.zkweb.domain.ZKConnection;

import java.util.List;

/**
 * zkweb com.yiheidaodi.zkweb.service.api
 * 描述：
 * 时间：2016-12-30 10:04.
 */

public interface IZKConnectionService {
    int createConn(ZKConnection zkConn);

    ZKConnection get(String uuid);

    List<ZKConnection> getAll();
}
