package top.jwtzkl.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.jwtzkl.backend.entity.Dept;
import top.jwtzkl.backend.entity.DeptLog;
import top.jwtzkl.backend.mapper.DeptMapper;
import top.jwtzkl.backend.mapper.EmpMapper;
import top.jwtzkl.backend.service.DeptLogService;
import top.jwtzkl.backend.service.DeptService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    //@Transactional(rollbackFor = Exception.class) //spring事务管理
    @Transactional
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.deleteById(id); //根据ID删除部门数据

            int i = 1/0;
            //if(true){throw new Exception("出错啦...");}

            //empMapper.deleteByDeptId(id); //根据部门ID删除该部门下的员工
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作,此次解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }
    }


    @Override
    public void add(Dept dept) {
        deptMapper.add(dept);
    }
}
