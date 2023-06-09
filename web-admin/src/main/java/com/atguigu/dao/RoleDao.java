package com.atguigu.dao;

import com.atguigu.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface RoleDao {
    List<Role> findAll();
    Integer insert(Role role);

    void delete(long id);

    Role getById(Long id);

    Integer update(Role role);

    List<Role> findPage(Map<String, Object> filters);
}
