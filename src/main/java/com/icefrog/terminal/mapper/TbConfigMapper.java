/*
 * Copyright 2019 icefrog.su@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.icefrog.terminal.mapper;

import com.icefrog.terminal.model.TbConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TbConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbConfig record);

    int insertSelective(TbConfig record);

    TbConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbConfig record);

    int updateByPrimaryKey(TbConfig record);
    
    TbConfig queryConfigByKey(@Param("key") String key);
}