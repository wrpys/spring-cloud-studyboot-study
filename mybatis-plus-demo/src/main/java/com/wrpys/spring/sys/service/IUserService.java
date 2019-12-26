package com.wrpys.spring.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wrpys.spring.sys.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author WRP
 * @since 2019-12-12
 */
public interface IUserService extends IService<User> {

    IPage<User> selectPage(Page<User> page, User user);

}
