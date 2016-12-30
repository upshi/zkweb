package com.yiheidaodi.zkweb.dto;

import org.apache.curator.framework.CuratorFramework;

public class ClientLocal {

    public static ThreadLocal<CuratorFramework> clientLocal = new ThreadLocal<CuratorFramework>();

}
