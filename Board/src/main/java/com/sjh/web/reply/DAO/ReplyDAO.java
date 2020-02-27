package com.sjh.web.reply.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import com.sjh.web.Pagination;
import com.sjh.web.reply.Reply;

@Repository
public class ReplyDAO implements IReplyDAO {
	private String user = "root"; // 사용자 이름
	private String password = "123456"; // PW
	private String db = "board"; // DB이름
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/" + db
			+ "?characterEncoding=euckr&useUnicode=true&mysqlEncoding=euckr";

	private DriverManagerDataSource dataSource;
	private JdbcTemplate template;

	public ReplyDAO() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		template = new JdbcTemplate();
		template.setDataSource(dataSource);
	}

	@Override
	public List<Reply> replyList(final String bId, final Pagination pagination) {
		List<Reply> replys = null;

		final String sql = "SELECT * FROM reply WHERE bId=? ORDER BY rId LIMIT ?, ?;";

		replys = template.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setObject(1, bId);
				pstmt.setObject(2, pagination.getStartList());
				pstmt.setObject(3, pagination.getListSize());
				return pstmt;
			}
		}, new RowMapper<Reply>() {
			@Override
			public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {

				Reply r = new Reply();
				r.setrId(Integer.parseInt(rs.getString("rId")));
				r.setbId(Integer.parseInt(bId));
				r.setrContent(rs.getString("rContent"));
				r.setrDate(rs.getTimestamp("rDate"));
				r.setmId(rs.getString("mId"));
				r.setrName(rs.getString("rName"));

				return r;
			}
		});

		return replys;
	}
	
	@Override
	public Reply replySerach(final String rId) {

		final String sql = "SELECT * FROM reply WHERE rId=?";

		Reply reply = template.queryForObject(sql, new Object[] { rId }, new RowMapper<Reply>() {
			public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
				Reply r = new Reply();
				r.setrId(Integer.parseInt(rs.getString("rId")));
				r.setbId(Integer.parseInt(rs.getString("bId")));
				r.setrName(rs.getString("rName"));
				r.setrContent(rs.getString("rContent"));
				r.setrDate(rs.getTimestamp("rDate"));
				return r;
			}
		});

		return reply;

	}

	@Override
	public int replyInsert(final Reply reply) {
		int result = 0;

		String sql = "INSERT INTO reply (bId, rContent, rDate, rName, mId) VALUES (?, ?, ?, ?, ?);";

		result = template.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, String.valueOf(reply.getbId()));
				pstmt.setString(2, reply.getrContent());
				pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
				pstmt.setString(4, reply.getrName());
				pstmt.setString(5, reply.getmId());
			}
		});

		return result;
	}

	@Override
	public int replyUpdate(final Reply reply) {
		int result = 0;

		String sql = "UPDATE reply SET rContent=?, rDate=? WHERE rId=?;";

		result = template.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, reply.getrContent());
				pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
				pstmt.setString(3, String.valueOf(reply.getrId()));
			}
		});

		return result;
	}

	@Override
	public int replyDelete(final String rId) {
		int result = 0;

		String sql = "DELETE FROM reply WHERE rId=?;";
		result = template.update(sql, rId);

		return result;
	}
	
	@Override
	public int replyCnt(final String bId) {
		
		int cnt = 0;
		
		String sql = "SELECT COUNT(*) FROM reply WHERE bId=?;";
		cnt = template.queryForObject(sql, new Object[] { bId }, Integer.class);
		
		return cnt;
		
	}
}
