package com.fishinwater.situp.classes;

import com.fishinwater.situp.classes.base.Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fishinwater-1999
 * @version 2019-11-16
 */
public class SitUpPlan implements IObject{

    @Override
    public List<String[]> toList(Object object) {
        Plan plan = (Plan) object;
        List<String[]> list = new ArrayList<>();
//        id=2&event=ceshishuju&startTime=2019-11-14%2011-30&endTime=2019-11-14%2011-35&quality=30
        list.add(new String[]{"id" , plan.getId()+""});
        list.add(new String[]{"event" , plan.getName()});
        // 时/分 形式
        list.add(new String[]{"startTime" , plan.getFrom()[0] + "-" + plan.getFrom()[1]});
        list.add(new String[]{"endTime" , plan.getTo()[0] + "-" + plan.getTo()[1]});
        list.add(new String[]{"quality" , plan.getDegree()+""});
        return null;
    }

    @Override
    public String toJsonString(Object object) {
        return null;
    }

    @Override
    public String toGetString(String[] strings) {
        return null;
    }

}
