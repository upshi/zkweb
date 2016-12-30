package com.yiheidaodi.zkweb.service.api;

import java.util.List;

/**
 * zkweb com.yiheidaodi.zkweb.service.api
 * 描述：
 * 时间：2016-12-30 14:30.
 */

public interface IZKService {
    List<String> getChildren(String path);
}
