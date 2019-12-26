package com.newland.netty.service.impl;

import com.newland.netty.service.NettyCallBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 默认错误处理
 *
 * @author WRP
 * @since 2019/12/26
 */
@Service("errorNettyCallBackService")
public class ErrorNettyCallBackServiceImpl implements NettyCallBackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorNettyCallBackServiceImpl.class);

    /**
     * 执行方法
     *
     * @param params
     */
    @Override
    public void exec(Object params) {
        LOGGER.info("ErrorNettyCallBackServiceImpl.exec:{}", params);
    }
}
