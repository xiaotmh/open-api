package com.tianminghao.mapper;

import java.util.List;
import com.tianminghao.pojo.Application;
import org.apache.ibatis.annotations.Param;


/**
 * 应用数据接口
*  @author Athena
*/
public interface ApplicationMapper {


    /**
     * 删除一个应用
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新应用
     * @param application
     * @return
     */
    int insert(Application application);

    /**
     * 插入新应用自动拼接
     * @param application
     * @return
     */
    int insertSelective(Application application);

    /**
     * 通过主键查找应用
     * @param id
     * @return
     */
    Application selectByPrimaryKey(Integer id);

    /**
     * 更新应用
     * @return
     */
    int updateByPrimaryKeySelective(Application application);

    /**
     * 更新应用自动拼接
     * @return
     */
    int updateByPrimaryKey(Application application);


    /**
     * 查询所有应用
     * @return
     */
    List<Application> findAll();

    /**
     * 通过应用名查找
     * @return
     */
    List<Application> ferretByAppName(@Param("content") String content,@Param("state") String state);





}