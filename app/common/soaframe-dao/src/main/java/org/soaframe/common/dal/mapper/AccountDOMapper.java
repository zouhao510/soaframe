// package org.soaframe.common.dal.mapper;
//
// import org.soaframe.common.dal.dataobject.AccountDO;
//
/// **
// * 由于需要对分页支持,请直接使用对应的DAO类 The Table TB_ACCOUNT. TB_ACCOUNT
// */
// public interface AccountDOMapper {
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
// Long insert(AccountDO entity);
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
// Long update(AccountDO entity);
//
// /**
// * desc:根据主键删除数据:TB_ACCOUNT.<br/>
// * descSql = DELETE FROM TB_ACCOUNT WHERE ID = #{id,jdbcType=BIGINT}
// *
// * @param id
// * id
// * @return Long
// */
// Long deleteByPrimary(Long id);
//
// /**
// * desc:根据主键获取数据:TB_ACCOUNT.<br/>
// * descSql = SELECT * FROM TB_ACCOUNT WHERE ID = #{id,jdbcType=BIGINT}
// *
// * @param id
// * id
// * @return AccountDO
// */
// AccountDO getByPrimary(Long id);
// }
