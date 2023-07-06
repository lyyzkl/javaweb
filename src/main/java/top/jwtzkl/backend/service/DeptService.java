package top.jwtzkl.backend.service;


import top.jwtzkl.backend.entity.Dept;

import java.util.List;

public interface DeptService {


    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);
}
