package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.jws.Oneway;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/role")
public class RoleController {
    private static final String  SUCCESS_PAGE="common/successPage";
    @Autowired
    private RoleService roleService;
//    @RequestMapping
//    public String index(Map map){
//        //调取RoleService中获取所有角色的方法
//        List<Role> list = roleService.findAll();
//        //将所有角色放到request域中
//        map.put("list",list);
//        //渲染数据的页面
//        return "role/index";
//    }
    //分页及带条件查询的方法
    @RequestMapping
    public String index(ModelMap model,HttpServletRequest request){
        Map<String, Object> filters = getFilters(request);
        PageInfo<Role> page = roleService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return "role/index";
    }
    @RequestMapping("/create")
    public String toAddPage(){
        return "role/create";
    }
    @RequestMapping("save")
    public String save(Role role, HttpServletRequest request){
        roleService.insert(role);
//        return "redirect:/role";
        return SUCCESS_PAGE;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        roleService.delete(id);
        return "redirect:/role";
    }
    @GetMapping("/edit/{id}")
    public String edit(ModelMap model,@PathVariable Long id){
        Role role = roleService.getById(id);
        model.addAttribute("role",role);
        return "role/edit";
    }
    @PostMapping("/update")
    public String update(Role role){
        roleService.update(role);
        return SUCCESS_PAGE;
    }
    /**
     * 封装页面提交的分页参数及搜索条件
     * @param request
     * @return
     */
    private Map<String, Object> getFilters(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> filters = new TreeMap();
        while(paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            if (values != null && values.length != 0) {
                if (values.length > 1) {
                    filters.put(paramName, values);
                } else {
                    filters.put(paramName, values[0]);
                }
            }
        }
        if(!filters.containsKey("pageNum")) {
            filters.put("pageNum", 1);
        }
        if(!filters.containsKey("pageSize")) {
            filters.put("pageSize", 5 );
        }

        return filters;
    }
}

