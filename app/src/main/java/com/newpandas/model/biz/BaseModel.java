package com.newpandas.model.biz;

import com.newpandas.net.HttpFactory;
import com.newpandas.net.IHttp;

/**
 * Created by yan on 2017/7/28.
 */

public interface BaseModel {
    public static IHttp iHttp = HttpFactory.create();
}
