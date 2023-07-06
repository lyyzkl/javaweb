package top.jwtzkl.backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jwtzkl.backend.entity.Emp;
import top.jwtzkl.backend.entity.PageBean;
import top.jwtzkl.backend.mapper.EmpMapper;
import top.jwtzkl.backend.service.EmpService;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    //分页查询
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDateTime begin, LocalDateTime end) {
        //设置分页查询参数
        PageHelper.startPage(page,pageSize);

        //查询
        List<Emp> empList = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageBean(p.getTotal(),p.getResult());

    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }
}
