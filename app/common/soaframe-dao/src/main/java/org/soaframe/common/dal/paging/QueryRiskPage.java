package org.soaframe.common.dal.paging;

import org.soaframe.common.dal.paging.BasePage;
import org.soaframe.common.dal.resultmap.PagedStatistic;

/**
 * The table TB_STATISTIC_NUM TB_STATISTIC_NUM
 */
public class QueryRiskPage extends BasePage<PagedStatistic>{

    /**
     * account 客户编码.
     */
    private String account;

    /**
     * Set account 客户编码.
     */
    public void setAccount(String account){
        this.account = account;
    }

    /**
     * Get account 客户编码.
     *
     * @return the string
     */
    public String getAccount(){
        return account;
    }
}
