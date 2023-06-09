package com.atguigu.service;

import com.atguigu.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface RoleService {
    List<Role> findAll();
    Integer insert(Role role);

    void delete(long id);

    Role getById(Long id);

    Integer update(Role role);

    PageInfo<Role> findPage(Map<String, Object> filters);
//    PageInfo<Role> findPage(Map<String, Object> filters){}
}
