package com.fishinwater.postcenter.model;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.data.protocol.PostBean;
import com.fishinwater.base.model.BaseModel;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public class PostPageModel extends PostBean implements BaseModel<IBaseCallback> {

    @Override
    public void getData(String objId, IBaseCallback callback) {
        callback.failed("PostPageModel");
    }
}
