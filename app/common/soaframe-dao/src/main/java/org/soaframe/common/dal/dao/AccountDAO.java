// package org.soaframe.common.dal.dao;
//
// import org.soaframe.common.dal.dataobject.AccountDO;
// import org.soaframe.common.dal.mapper.AccountDOMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Repository;
//
/// **
// * The Table TB_ACCOUNT. TB_ACCOUNT
// */
// @Repository
// public class AccountDAO {
//
// @Autowired
// private AccountDOMapper accountDOMapper;
//
// /**
// * desc:插入表:TB_ACCOUNT.<br/>
// * descSql = SELECT LAST_INSERT_ID() INSERT INTO TB_ACCOUNT( ID ,TYPE ,EMAIL
// * ,MONEY ,REMARK ,STATUS ,ACCOUNT ,CREATE_BY ,PASSWORD ,USER_NAME
// * ,GMT_CREATE ,TELEPHONE ,CONTACT_USER ,GMT_MODIFIED ,CONTACT_TELEPHONE
// * )VALUES( #{id,jdbcType=BIGINT} , #{type,jdbcType=VARCHAR} ,
// * #{email,jdbcType=VARCHAR} , #{money,jdbcType=DOUBLE} ,
// * #{remark,jdbcType=VARCHAR} , #{status,jdbcType=TINYINT} ,
// * #{account,jdbcType=VARCHAR} , #{createBy,jdbcType=VARCHAR} ,
// * #{password,jdbcType=VARCHAR} , #{userName,jdbcType=VARCHAR} , now() ,
// * #{telephone,jdbcType=VARCHAR} , #{contactUser,jdbcType=VARCHAR} , now() ,
// * #{contactTelephone,jdbcType=VARCHAR} )
// *
// * @param entity
// * entity
// * @return Long
// */
// public Long insert(AccountDO entity) {
// return accountDOMapper.insert(entity);
// }
//
// /**
// * desc:更新表:TB_ACCOUNT.<br/>
// * descSql = UPDATE TB_ACCOUNT SET TYPE = #{type,jdbcType=VARCHAR} ,EMAIL =
// * #{email,jdbcType=VARCHAR} ,MONEY = #{money,jdbcType=DOUBLE} ,REMARK =
// * #{remark,jdbcType=VARCHAR} ,STATUS = #{status,jdbcType=TINYINT} ,ACCOUNT
// * = #{account,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR}
// * ,PASSWORD = #{password,jdbcType=VARCHAR} ,USER_NAME =
// * #{userName,jdbcType=VARCHAR} ,TELEPHONE = #{telephone,jdbcType=VARCHAR}
// * ,CONTACT_USER = #{contactUser,jdbcType=VARCHAR} ,GMT_MODIFIED = now()
// * ,CONTACT_TELEPHONE = #{contactTelephone,jdbcType=VARCHAR} WHERE ID =
// * #{id,jdbcType=BIGINT}
// *
// * @param entity
// * entity
// * @return Long
// */
// public Long update(AccountDO entity) {
// return accountDOMapper.update(entity);
// }
//
// /**
// * desc:根据主键删除数据:TB_ACCOUNT.<br/>
// * descSql = DELETE FROM TB_ACCOUNT WHERE ID = #{id,jdbcType=BIGINT}
// *
// * @param id
// * id
// * @return Long
// */
// public Long deleteByPrimary(Long id) {
// return accountDOMapper.deleteByPrimary(id);
// }
//
// /**
// * desc:根据主键获取数据:TB_ACCOUNT.<br/>
// * descSql = SELECT * FROM TB_ACCOUNT WHERE ID = #{id,jdbcType=BIGINT}
// *
// * @param id
// * id
// * @return AccountDO
// */
// public AccountDO getByPrimary(Long id) {
// return accountDOMapper.getByPrimary(id);
// }
// }
