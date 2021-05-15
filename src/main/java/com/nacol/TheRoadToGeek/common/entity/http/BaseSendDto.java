package com.nacol.TheRoadToGeek.common.entity.http;

import java.io.Serializable;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/15
 * @Description Http 传送基础参数
 */
public class BaseSendDto implements Serializable {

    private static final long serialVersionUID = -976806130063122788L;

    private boolean logRecord;

    public boolean isLogRecord(){
        return this.logRecord;
    }

    public void setLogRecord(boolean logRecord) {
        this.logRecord = logRecord;
    }

}
