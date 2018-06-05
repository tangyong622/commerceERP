package com.commerceerp.system.mapper;


import java.util.List;

/**
 * Created by Administrator on 2018/4/1.
 */
public interface Mapper {

    //查看总数
    int findListCount(Object o);

    //查看列表
    List<Object> findList(Object o);

    //新增
    Integer insert(Object o);

    //修改
    Integer update(Object o);

    //删除
    Integer delete(String id);

}
