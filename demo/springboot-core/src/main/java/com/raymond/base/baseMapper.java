package com.raymond.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface baseMapper<T> extends Mapper<T>,MySqlMapper<T>{

}
