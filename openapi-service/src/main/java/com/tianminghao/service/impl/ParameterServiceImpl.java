package com.tianminghao.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianminghao.mapper.ParameterMapper;
import com.tianminghao.mapper.ParameterMapper;
import com.tianminghao.pojo.Parameter;
import com.tianminghao.pojo.Parameter;
import com.tianminghao.service.ParameterService;
import com.tianminghao.service.ParameterService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Athena
 * @date: 2020/9/25 20:03
 * @description: 客户服务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Log4j
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    ParameterMapper parameterMapper;

    /**
     * 增加
     *
     * @param parameter
     * @return
     */
    @Override
    public int save(Parameter parameter) throws Exception {
        int result = parameterMapper.insertSelective(parameter);
        return result;
    }

    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Parameter> findAll() throws Exception {
        List<Parameter> parameters = parameterMapper.findAll();
        return parameters;
    }

    /**
     * 分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<Parameter> findPage(Integer pageNum, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Parameter> Parameters = parameterMapper.findAll();
        PageInfo<Parameter> pageInfo = new PageInfo<>(Parameters);
        return pageInfo;
    }

    /**
     * 搜索并分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<Parameter> searchPage(Integer pageNum, Integer pageSize, String content, String state) throws Exception {
        //先用用户名搜索，然后用公司名搜索
        PageHelper.startPage(pageNum, pageSize);
        List<Parameter> Parameters = parameterMapper.ferretByName(content,state);
        PageInfo<Parameter> pageInfo =null;
        if (Parameters.size() == 0) {
            PageHelper.startPage(pageNum, pageSize);
            Parameters = parameterMapper.ferretByName(content,state);
            //如果用户名和公司名都没找到，那么检测字符串长度，如果>1就分割后再查一次
            if (Parameters.size() == 0) {
                if(content.length()>1){
                    log.fatal("进入删减搜索");
                    for (int i = 0; i < content.length() ; i++) {//jack1 01234     5
                        String newContent=content.substring(0,content.length()-i-1);

                        PageHelper.startPage(pageNum, pageSize);
                        Parameters = parameterMapper.ferretByName(newContent,state);
                        if (Parameters.size() != 0) {
                            log.fatal("删减搜索成功==>"+newContent);
                            break;
                        }
                    }

                    if (Parameters.size() == 0) {
                        for (int i = 0; i < content.length(); i++) {
                            String newContent=content.substring(0,content.length()-i-1);
                            PageHelper.startPage(pageNum, pageSize);
                            Parameters = parameterMapper.ferretByName(newContent,state);
                            if (Parameters.size() != 0) {
                                log.fatal("删减搜索成功==>"+newContent);
                                break;
                            }
                        }
                    }

                    //如果这个时候还是空，则需要遍历内容搜索公司名

                    if (Parameters.size() == 0) {
                        log.fatal("单字搜索失败");
                    }


                    pageInfo = new PageInfo<>(Parameters);
                    return pageInfo;
                }else {
                    pageInfo = new PageInfo<>(Parameters);
                    return pageInfo;
                }
            }
        }else{
            pageInfo = new PageInfo<>(Parameters);
            return pageInfo;
        }
        return pageInfo;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public int drop(Integer id) throws Exception {
        int delete = parameterMapper.deleteByPrimaryKey(id);
        return delete;
    }

    /**
     * 批量删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int dropBatch(Integer id) throws Exception {
        return 0;
    }

    /**
     * 更新信息
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @Override
    public int alter(Parameter parameter) throws Exception {
        int update = parameterMapper.updateByPrimaryKeySelective(parameter);
        return update;
    }

    /**
     * 添加信息
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @Override
    public int add(Parameter parameter) throws Exception {
        int insert = parameterMapper.insert(parameter);
        return insert;
    }


}