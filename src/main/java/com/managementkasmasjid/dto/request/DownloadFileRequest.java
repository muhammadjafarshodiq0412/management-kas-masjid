package com.managementkasmasjid.dto.request;

import com.managementkasmasjid.entity.GlobalParam;

import java.util.Date;

public class DownloadFileRequest {
    private String fromDate;
    private String untilDate;
    private GlobalParam category;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(String untilDate) {
        this.untilDate = untilDate;
    }

    public GlobalParam getCategory() {
        return category;
    }

    public void setCategory(GlobalParam category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "DownloadFileRequest{" +
                "fromDate='" + fromDate + '\'' +
                ", untilDate='" + untilDate + '\'' +
                ", category=" + category +
                '}';
    }
}
