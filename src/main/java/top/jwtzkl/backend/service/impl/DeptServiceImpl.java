package top.jwtzkl.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.jwtzkl.backend.entity.Dept;
import top.jwtzkl.backend.mapper.DeptMapper;
import top.jwtzkl.backend.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        deptMapper.add(dept);
    }
}
