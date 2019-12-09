package com.fishinwater.postmodule.model;

import com.fishinwater.base.callback.IBaseCallback;
import com.fishinwater.base.model.BaseModel;
import com.fishinwater.postmodule.Post;

/**
 * @author fishinwater-1999
 * @version 2019-12-09
 */
public class PostPageModel extends BaseModel<IBaseCallback> {

    @Override
    public void getData(String objId, IBaseCallback callback) {
        callback.failed("PostPageModel");
    }
}
