package com.sjh.web.board.DAO;

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
import com.sjh.web.board.Board;

@Repository
public class BoardDAO implements IBoardDAO {
	private String user = "root"; // 사용자 이름
	private String password = "123456"; // PW
	private String db = "board"; // DB이름
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/" + db +"?characterEncoding=euckr&useUnicode=true&mysqlEncoding=euckr";

	private DriverManagerDataSource dataSource;
	private JdbcTemplate template;
	
	public BoardDAO() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		template = new JdbcTemplate();
		template.setDataSource(dataSource);
	}
	
	@Override
	public List<Board> boardList(final Pagination pagination) {
		
		List<Board> boards = null;
		
		// 페이징을 위해 한 블록에 들어갈 게시글 수만큼 데이터를 읽어온다
		final String sql = "SELECT * FROM board ORDER BY bId DESC LIMIT ?, ?;"; 
		
		boards = template.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setObject(1, pagination.getStartList());
				pstmt.setObject(2, pagination.getListSize());
				return pstmt;
			}
		}, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Board b = new Board();
				b.setbId(Integer.parseInt(rs.getString("bId")));
				b.setmId(rs.getString("mId"));
				b.setbName(rs.getString("bName"));
				b.setbTitle(rs.getString("bTitle"));
				b.setbContent(rs.getString("bContent"));
				b.setbDate(rs.getTimestamp("bDate"));
				b.setbHit(Integer.parseInt(rs.getString("bHit")));
				
				return b;
			}
		});
		
		return boards;
	}
	
	public Board boardSelect(final String bId) {
		
		List<Board> list = null;
		
		final String sql = "SELECT * FROM board WHERE bId=?;";

		list = template.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bId);
				return pstmt;
			}
		}, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Board b = new Board();
				b.setbId(Integer.parseInt(rs.getString("bId")));
				b.setmId(rs.getString("mId"));
				b.setbName(rs.getString("bName"));
				b.setbTitle(rs.getString("bTitle"));
				b.setbContent(rs.getString("bContent"));
				b.setbDate(rs.getTimestamp("bDate"));
				b.setbHit(Integer.parseInt(rs.getString("bHit")));
				
				return b;
			}
		});
		
		if(list.isEmpty()) return null;
		
		return list.get(0);
	}
	
	@Override
	public int boardUpdate(final Board board) {
		
		int result = 0;
		
		String sql = "UPDATE board SET bTitle=?, bContent=?, bDate=? WHERE bId=?;";
		
		result = template.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, board.getbTitle());
				pstmt.setString(2, board.getbContent());
				pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
				pstmt.setString(4, String.valueOf(board.getbId()));
			}
		});
		
		return result;
	}
	
	@Override
	public int boardDelete(final String bId) {
		
		int result = 0;
		
		String sql = "DELETE FROM board WHERE bId=?;";
		
		result = template.update(sql, bId);
		
		return result;
		
	}
	
	@Override
	public int boardInsert(final Board board) {
		
		int result = 0;
		
		String sql = "INSERT INTO board (mId, bName, bTitle, bContent, bDate)"
				+ "VALUES (?, ?, ?, ?, ?);";
		
		
		result = template.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, board.getmId());
				pstmt.setString(2, board.getbName());
				pstmt.setString(3, board.getbTitle());
				pstmt.setString(4, board.getbContent());
				pstmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
			}
		});
				
		return result;	
	}
	
	@Override
	public int hitUp(final String bId) {
		
		int result = 0;
		
		String sql = "UPDATE board SET bHit=bHit+1 WHERE bId=?;";
		
		result = template.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, bId);
			}
		});
		
		return result;
	}
	
	@Override
	public int boardCnt() {
		
		int cnt = 0;
		
		String sql = "SELECT COUNT(*) FROM board;";
		cnt = template.queryForObject(sql, Integer.class);
		
		return cnt;
		
	}
}
