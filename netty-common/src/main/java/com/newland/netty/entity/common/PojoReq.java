package com.newland.netty.entity.common;

import java.io.Serializable;

/**
 * 请求bean
 *
 * @author WRP
 * @since 2019/12/25
 */
public class PojoReq implements Serializable {

    private static final long serialVersionUID = -2074951901339683353L;

    /**
     * 请求序列
     */
    private Long reqId;
    /**
     * service名
     */
    private String serviceName;
    /**
     * 执行方法
     */
    private String methodName;
    /**
     * 执行参数
     */
    private Object params;

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "PojoReq{" +
                "reqId=" + reqId +
                ", serviceName='" + serviceName + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
