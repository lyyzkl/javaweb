package top.jwtzkl.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.jwtzkl.backend.entity.Dept;
import top.jwtzkl.backend.entity.Result;
import top.jwtzkl.backend.service.DeptService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查询部门数据
    @GetMapping
    public Result list(){
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    //删除部门
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除部门：{}",id);

        deptService.delete(id);
        return Result.success();
    }

    //添加部门
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("添加部门:{}",dept);
        deptService.add(dept);
        return Result.success();
    }

}

