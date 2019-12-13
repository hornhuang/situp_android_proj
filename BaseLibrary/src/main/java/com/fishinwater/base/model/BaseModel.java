package com.fishinwater.base.model;

import com.fishinwater.base.callback.IBaseCallback;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public interface BaseModel<V> {

    void getData(String objId, V callback);

}
