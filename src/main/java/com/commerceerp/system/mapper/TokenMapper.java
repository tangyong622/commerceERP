package com.commerceerp.system.mapper;

import org.apache.ibatis.annotations.Param;


/**
 * Created by tangyong on 2018/3/10.
 */
public interface TokenMapper extends Mapper{

    Integer saveToken(@Param("code") String code,@Param("id") String id,@Param("type") String type);

}
