package top.jwtzkl.backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.jwtzkl.backend.entity.Emp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmpMapper {

    /**
    //@Select("select count(*) from emp")
    //public Long count();

    /**
     * 分页查询,获取列表数据
     */
    //@Select("select * from emp limit #{start},#{pageSize}")
    //public List<Emp> page(Integer start, Integer pageSize);

    //分页查询
    List<Emp> list(String name, Short gender, LocalDateTime begin, LocalDateTime end);

    //@Select("select * from emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);


    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            " values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    @Select("select * from emp where  id = #{id}")
    Emp getById(Integer id);


    void update(Emp emp);

}
