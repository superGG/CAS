package com.earl.cas.vo;

import com.earl.cas.util.GsonUtil;

/**
 * Created by Administrator on 2016/5/1.
 */
public abstract  class JsonEntry {

    public String toJson(){
        return GsonUtil.toJson(this);
    }

}
