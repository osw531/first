package com.itbank.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.itbank.model.domain.Admin;

@Repository
public class JdbcAdminDAO implements AdminDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin loginCheck(Admin admin) {
		Admin obj = null;
		String sql = "select * from admin where id=? and pass=?";
		try {
			obj = jdbcTemplate.queryForObject(sql, new RowMapper<Admin>() {
				@Override
				public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
					Admin admin = new Admin();
					admin.setAdmin_id(rs.getInt("admin_id"));
					admin.setId(rs.getString("id"));;
					admin.setPass(rs.getString("pass"));
					admin.setName(rs.getString("name"));
					return admin;
				}
			},admin.getId(),admin.getPass());
		} catch (DataAccessException e) {
			e.printStackTrace();	
		}
		return obj;
	}

	@Override
	public Admin select(int admin_id) {
		return null;
	}

	@Override
	public int insert(Admin admin) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Admin admin) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int admin_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
