package com.ly.system.dao.impl;

import com.ly.system.dao.ParamDao;
import com.ly.system.entity.Params;
import com.ly.util.Pagination;
import com.ly.util.ParamTypeEnum;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("paramDao")
public class ParamDaoImpl extends BaseDaoImpl<Params> implements ParamDao {

	@Override
	public Pagination<Map<String, Object>> queryList(Map<String, Object> params,int pageIndex, int pageSize, String paramType) {
		String querySql = " select * from " + ParamTypeEnum.getTableNameByCode(paramType) + " where 1=1 ";
		String countSql = " select count(1) from " + ParamTypeEnum.getTableNameByCode(paramType) + " where 1=1 ";
		if (!params.isEmpty()) {
			for(String key:params.keySet()){
				if (key.equals("name")) {
					querySql += " and name like:" + key + " ";
					countSql += " and name like:" + key + " ";
				}else{
					querySql += " and " + key + "=:" + key + " ";
					countSql += " and " + key + "=:" + key + " ";
				}
			}
		}
		querySql += " order by create_time asc";
		Pagination<Map<String, Object>> pagination = super.findSqlPagination(querySql, countSql, params, pageIndex, pageSize);
		return pagination;
	}

	@Override
	public void saveParam(Params param) {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		String sql = "insert into " + ParamTypeEnum.getTableNameByCode(param.getParamType()) + " (id,name,create_time) " +
				" values ('" + uuid + "','" + param.getName() + "',now()) ";
		super.executeSql(sql);
	}

	@Override
	public void updateParam(Params param) {
		String sql = "update " + ParamTypeEnum.getTableNameByCode(param.getParamType()) + " set name='" + param.getName() + "'," +
				"update_time=sysdate where id='" + param.getId() + "'";
		super.executeSqlUpdate(sql);
	}

	@Override
	public void deleteParam(String id, String paramType) {
		String sql = "delete from " + ParamTypeEnum.getTableNameByCode(paramType) + " where id='" + id + "'";
		super.executeSql(sql);
	}

	@Override
	public Params getByIdAndType(String id, String paramType) {
		String sql = "select * from " + ParamTypeEnum.getTableNameByCode(paramType) + " where id='" + id + "'";
		SQLQuery query = (SQLQuery) getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = query.list();
		if (list != null && list.size() > 0) {
			Params param = new Params();
			param.setCreateTime((Date) list.get(0).get("CREATE_TIME"));
			param.setId((String) list.get(0).get("ID"));
			param.setName((String) list.get(0).get("NAME"));
			param.setUpdateTime((Date) list.get(0).get("UPDATE_TIME"));
			return param;
		}
		return null;
	}

	@Override
	public List<Params> getByType(String paramType) {
		String sql = "select * from " + ParamTypeEnum.getTableNameByCode(paramType);
		SQLQuery query = (SQLQuery) getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = query.list();
		List<Params> params = new ArrayList<Params>();
		if (list != null) {
			for(Map<String, Object> map:list){
				Params param = new Params();
				param.setId((String) map.get("ID"));
				param.setName((String) map.get("NAME"));
				param.setCreateTime((Date) map.get("CREATE_TIME"));
				param.setUpdateTime((Date) map.get("UPDATE_TIME"));
				params.add(param);
			}
		}
		return params;
	}
}
