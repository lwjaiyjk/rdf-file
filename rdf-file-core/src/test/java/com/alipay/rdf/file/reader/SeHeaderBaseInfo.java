package com.alipay.rdf.file.reader;

import java.io.Serializable;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/10/8 10:40
 * description：
 **/
public class SeHeaderBaseInfo implements Serializable{

    /**
     * serial id
     */
    private static final long serialVersionUID = 3895134115649252991L;

    private String beginString;

    private String version;

    public String getBeginString() {
        return beginString;
    }

    public void setBeginString(String beginString) {
        this.beginString = beginString;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
