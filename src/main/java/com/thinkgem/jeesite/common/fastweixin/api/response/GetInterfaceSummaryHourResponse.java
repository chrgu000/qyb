package com.thinkgem.jeesite.common.fastweixin.api.response;

import com.thinkgem.jeesite.common.fastweixin.api.entity.InterfaceSummaryHour;

import java.util.List;

/**
 * @author peiyu
 */
public class GetInterfaceSummaryHourResponse extends BaseResponse {

    private List<InterfaceSummaryHour> list;

    public List<InterfaceSummaryHour> getList() {
        return list;
    }

    public void setList(List<InterfaceSummaryHour> list) {
        this.list = list;
    }
}
