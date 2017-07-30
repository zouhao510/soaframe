package org.soaframe.common.dal.resultmap;

import java.io.Serializable;

/**
 * The table PagedStatistic
 */
public class PagedStatistic implements Serializable {

private static final long serialVersionUID = -1L;

    /**
     * account 账户名.
     */
    private String account;
    /**
     * remarks 备注.
     */
    private String remarks;
    /**
     * day 某天.
     */
    private int day;
    /**
     * hour 某小时.
     */
    private int hour;
    /**
     * sign 短信签名.
     */
    private String sign;
    /**
     * successNum 发送成功数量.
     */
    private Long successNum;
    /**
     * failNum 发送失败数量.
     */
    private Long failNum;
    /**
     * quireNum 总发送数量.
     */
    private Long quireNum;

    /**
     * Set account 账户名.
     */
    public void setAccount(String account){
        this.account = account;
    }

    /**
     * Get account 账户名.
     *
     * @return the string
     */
    public String getAccount(){
        return account;
    }

    /**
     * Set remarks 备注.
     */
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    /**
     * Get remarks 备注.
     *
     * @return the string
     */
    public String getRemarks(){
        return remarks;
    }

    /**
     * Set day 某天.
     */
    public void setDay(int day){
        this.day = day;
    }

    /**
     * Get day 某天.
     *
     * @return the string
     */
    public int getDay(){
        return day;
    }

    /**
     * Set hour 某小时.
     */
    public void setHour(int hour){
        this.hour = hour;
    }

    /**
     * Get hour 某小时.
     *
     * @return the string
     */
    public int getHour(){
        return hour;
    }

    /**
     * Set sign 短信签名.
     */
    public void setSign(String sign){
        this.sign = sign;
    }

    /**
     * Get sign 短信签名.
     *
     * @return the string
     */
    public String getSign(){
        return sign;
    }

    /**
     * Set successNum 发送成功数量.
     */
    public void setSuccessNum(Long successNum){
        this.successNum = successNum;
    }

    /**
     * Get successNum 发送成功数量.
     *
     * @return the string
     */
    public Long getSuccessNum(){
        return successNum;
    }

    /**
     * Set failNum 发送失败数量.
     */
    public void setFailNum(Long failNum){
        this.failNum = failNum;
    }

    /**
     * Get failNum 发送失败数量.
     *
     * @return the string
     */
    public Long getFailNum(){
        return failNum;
    }

    /**
     * Set quireNum 总发送数量.
     */
    public void setQuireNum(Long quireNum){
        this.quireNum = quireNum;
    }

    /**
     * Get quireNum 总发送数量.
     *
     * @return the string
     */
    public Long getQuireNum(){
        return quireNum;
    }
}
