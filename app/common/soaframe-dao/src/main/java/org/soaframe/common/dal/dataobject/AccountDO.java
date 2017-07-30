package org.soaframe.common.dal.dataobject;

import java.util.Date;

/**
 * The table TB_ACCOUNT
 */
public class AccountDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * type TYPE.
     */
    private String type;
    /**
     * email EMAIL.
     */
    private String email;
    /**
     * money MONEY.
     */
    private Double money;
    /**
     * remark REMARK.
     */
    private String remark;
    /**
     * status STATUS.
     */
    private Integer status;
    /**
     * account ACCOUNT.
     */
    private String account;
    /**
     * createBy CREATE_BY.
     */
    private String createBy;
    /**
     * password PASSWORD.
     */
    private String password;
    /**
     * userName USER_NAME.
     */
    private String userName;
    /**
     * gmtCreate GMT_CREATE.
     */
    private Date gmtCreate;
    /**
     * telephone TELEPHONE.
     */
    private String telephone;
    /**
     * contactUser CONTACT_USER.
     */
    private String contactUser;
    /**
     * gmtModified GMT_MODIFIED.
     */
    private Date gmtModified;
    /**
     * contactTelephone CONTACT_TELEPHONE.
     */
    private String contactTelephone;

    /**
     * Set id ID.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id ID.
     *
     * @return the string
     */
    public Long getId(){
        return id;
    }

    /**
     * Set type TYPE.
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * Get type TYPE.
     *
     * @return the string
     */
    public String getType(){
        return type;
    }

    /**
     * Set email EMAIL.
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Get email EMAIL.
     *
     * @return the string
     */
    public String getEmail(){
        return email;
    }

    /**
     * Set money MONEY.
     */
    public void setMoney(Double money){
        this.money = money;
    }

    /**
     * Get money MONEY.
     *
     * @return the string
     */
    public Double getMoney(){
        return money;
    }

    /**
     * Set remark REMARK.
     */
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
     * Get remark REMARK.
     *
     * @return the string
     */
    public String getRemark(){
        return remark;
    }

    /**
     * Set status STATUS.
     */
    public void setStatus(Integer status){
        this.status = status;
    }

    /**
     * Get status STATUS.
     *
     * @return the string
     */
    public Integer getStatus(){
        return status;
    }

    /**
     * Set account ACCOUNT.
     */
    public void setAccount(String account){
        this.account = account;
    }

    /**
     * Get account ACCOUNT.
     *
     * @return the string
     */
    public String getAccount(){
        return account;
    }

    /**
     * Set createBy CREATE_BY.
     */
    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    /**
     * Get createBy CREATE_BY.
     *
     * @return the string
     */
    public String getCreateBy(){
        return createBy;
    }

    /**
     * Set password PASSWORD.
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Get password PASSWORD.
     *
     * @return the string
     */
    public String getPassword(){
        return password;
    }

    /**
     * Set userName USER_NAME.
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
     * Get userName USER_NAME.
     *
     * @return the string
     */
    public String getUserName(){
        return userName;
    }

    /**
     * Set gmtCreate GMT_CREATE.
     */
    public void setGmtCreate(Date gmtCreate){
        this.gmtCreate = gmtCreate;
    }

    /**
     * Get gmtCreate GMT_CREATE.
     *
     * @return the string
     */
    public Date getGmtCreate(){
        return gmtCreate;
    }

    /**
     * Set telephone TELEPHONE.
     */
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    /**
     * Get telephone TELEPHONE.
     *
     * @return the string
     */
    public String getTelephone(){
        return telephone;
    }

    /**
     * Set contactUser CONTACT_USER.
     */
    public void setContactUser(String contactUser){
        this.contactUser = contactUser;
    }

    /**
     * Get contactUser CONTACT_USER.
     *
     * @return the string
     */
    public String getContactUser(){
        return contactUser;
    }

    /**
     * Set gmtModified GMT_MODIFIED.
     */
    public void setGmtModified(Date gmtModified){
        this.gmtModified = gmtModified;
    }

    /**
     * Get gmtModified GMT_MODIFIED.
     *
     * @return the string
     */
    public Date getGmtModified(){
        return gmtModified;
    }

    /**
     * Set contactTelephone CONTACT_TELEPHONE.
     */
    public void setContactTelephone(String contactTelephone){
        this.contactTelephone = contactTelephone;
    }

    /**
     * Get contactTelephone CONTACT_TELEPHONE.
     *
     * @return the string
     */
    public String getContactTelephone(){
        return contactTelephone;
    }
}
