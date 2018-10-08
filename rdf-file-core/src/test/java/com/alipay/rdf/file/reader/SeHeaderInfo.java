package com.alipay.rdf.file.reader;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/10/8 10:27
 * description：上交所头信息
 **/
public class SeHeaderInfo extends SeHeaderBaseInfo {

    /**
     * serial id
     */
    private static final long serialVersionUID = 5446374578340927428L;


    private long bodyLength;

    private Integer totNumTradeReports;

    private Long mdReportID;

    private String senderCompID;

    private Date mdTime;

    private String mdUpdateType;


    private String mdSesStatus;


    public long getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(long bodyLength) {
        this.bodyLength = bodyLength;
    }

    public int getTotNumTradeReports() {
        return totNumTradeReports;
    }

    public void setTotNumTradeReports(int totNumTradeReports) {
        this.totNumTradeReports = totNumTradeReports;
    }

    public long getMdReportID() {
        return mdReportID;
    }

    public void setMdReportID(long mdReportID) {
        this.mdReportID = mdReportID;
    }

    public String getSenderCompID() {
        return senderCompID;
    }

    public void setSenderCompID(String senderCompID) {
        this.senderCompID = senderCompID;
    }

    public Date getMdTime() {
        return mdTime;
    }

    public void setMdTime(Date mdTime) {
        this.mdTime = mdTime;
    }

    public String getMdUpdateType() {
        return mdUpdateType;
    }

    public void setMdUpdateType(String mdUpdateType) {
        this.mdUpdateType = mdUpdateType;
    }

    public String getMdSesStatus() {
        return mdSesStatus;
    }

    public void setMdSesStatus(String mdSesStatus) {
        this.mdSesStatus = mdSesStatus;
    }
}
