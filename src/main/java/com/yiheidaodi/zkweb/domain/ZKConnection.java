package com.yiheidaodi.zkweb.domain;

/**
 * zkweb com.yiheidaodi.zkweb.domain
 * 描述：
 * 时间：2016-12-24 15:27.
 */

public class ZKConnection {

    private String uuid;
    private String name;
    private String ip;
    private String port;

    public ZKConnection() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ZKConnection{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
