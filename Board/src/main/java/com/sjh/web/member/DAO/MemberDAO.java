package com.sjh.web.member.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sjh.web.member.Member;

@Repository
public class MemberDAO implements IMemberDAO {
	private JdbcTemplate template;
	
	public MemberDAO(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int memberInsert(final Member member) {
		int result = 0;
		
		String sql = "INSERT INTO member values (?, ?, ?, ?, ?)";
		
		result = template.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, member.getmId());
				pstmt.setString(2, member.getmPw());
				pstmt.setString(3, member.getmName());
				pstmt.setString(4, member.getmEmail());
				pstmt.setString(5, member.getmAddress());
			}
		});
		
		return result;
	}
	
	@Override
	public Member memberSelect(final Member member) {
		
		List<Member> members = null;
		
		final String sql = "SELECT * FROM member WHERE mId=? AND mPw=?";
		
		members = template.query(new PreparedStatementCreator() {			
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getmId());
				pstmt.setString(2, member.getmPw());
				return pstmt;
			}
		}, new RowMapper<Member>() {
			
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member mem = new Member();
				mem.setmId(rs.getString("mId"));
				mem.setmPw(rs.getString("mPw"));
				mem.setmName(rs.getString("mName"));
				mem.setmEmail(rs.getString("mEmail"));
				mem.setmAddress(rs.getString("mAddress"));
				return mem;
			}
		});
		
		if(members.isEmpty()) return null;
		
		return members.get(0);
		
	}
	
	@Override
	public Member memberUpdate(final Member member) {
		
		int result = 0;
		Member mem = null;
		
		String sql = "UPDATE member SET mPw=?, mName=?, mEmail=?, mAddress=? WHERE mId=?";
		
		result = template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, member.getmPw());
				pstmt.setString(2, member.getmName());
				pstmt.setString(3, member.getmEmail());
				pstmt.setString(4, member.getmAddress());
				pstmt.setString(5, member.getmId());
			}
		});
		
		if(result != 0) mem = memberSelect(member);
		
		return mem;
	}
	
	@Override
	public int memberDelete(final Member member) {
		
		int result = 0;
		
		String sql = "DELETE FROM member WHERE mId=? AND mPw=?";
		
		result = template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, member.getmId());
				pstmt.setString(2, member.getmPw());;
			}
		});
		
		return result;
		
	}
}
