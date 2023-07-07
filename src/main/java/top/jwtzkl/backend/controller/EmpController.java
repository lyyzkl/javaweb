package top.jwtzkl.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import top.jwtzkl.backend.entity.Emp;
import top.jwtzkl.backend.entity.PageBean;
import top.jwtzkl.backend.entity.Result;
import top.jwtzkl.backend.service.EmpService;
import top.jwtzkl.backend.utils.XmlParserUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-mm-dd")LocalDateTime begin,
                       @DateTimeFormat(pattern = "yyyy-mm-dd")LocalDateTime end) {
        log.info("分页查询, 参数: {},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        //调用service分页查询
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }


    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作, ids:{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工, emp: {}",emp);
        empService.save(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询员工信息, id: {}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息 : {}", emp);
        empService.update(emp);
        return Result.success();
    }

    @RequestMapping("/listmap")
    public Result listmap(){
        //加载并且解析xml文件
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> list = XmlParserUtils.parse(file, Emp.class);
        list.stream().forEach(emp->{
            //处理 gender 1: 男, 2: 女
//            String gender = emp.getGender();
//            if("1".equals(gender)){
//                emp.setGender("男");
//            }else if("2".equals(gender)){
//                emp.setGender("女");
//            }

            //处理job - 1: 讲师, 2: 班主任 , 3: 就业指导
//            String job = emp.getJob();
//            if("1".equals(job)){
//                emp.setJob("讲师");
//            }else if("2".equals(job)){
//                emp.setJob("班主任");
//            }else if("3".equals(job)){
//                emp.setJob("就业指导");
//            }
        });
        //3. 响应数据
        return Result.success(list);
    }
}
