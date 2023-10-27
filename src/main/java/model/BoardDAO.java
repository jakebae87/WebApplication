package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import domain.BoardDTO;

@Component
public class BoardDAO {
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;

	public List<BoardDTO> selectList() {
		sql = "select * from board order by seq desc";
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				do {
					BoardDTO dto = new BoardDTO();
					dto.setSeq(rs.getInt(1));
					dto.setId(rs.getString(2));
					dto.setTitle(rs.getString(3));
					// dto.setContent(rs.getString(4)); 글 목록에서 게시글 내용을 보여줄 필요는 없다.
					dto.setRegdate(rs.getString(5));
					dto.setCnt(rs.getInt(6));
					list.add(dto);
				} while (rs.next());
			} else {
				list = null;
			}
		} catch (Exception e) {
			System.out.println("** selectList Exception => " + e.toString());
			list = null;
		}
		return list;
	} // selectList

	public int updateReadCount(BoardDTO dto) {
		sql = "update board set cnt = cnt + 1 where seq=?";

		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getSeq());
			return pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("** read counting Exception => " + e.toString());
			return 0;
		}
	}

	// ** selectOne
	public BoardDTO selectOne(BoardDTO dto) {
		sql = "select * from board where seq=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getSeq());
			rs = pst.executeQuery();
			if (rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				dto.setCnt(rs.getInt(6));
				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectOne Exception => " + e.toString());
			return null;
		}
	} // selectOne

	// ** insert
	public int insert(BoardDTO dto) {
		sql = "insert into board (seq,id,title,content) values("
				+ "(select * from (select IFNULL(max(seq),0)+1 from board) as temp)"
				+ ",?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());

			return pst.executeUpdate(); // 처리갯수

		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			return 0;
		}
	} // insert

	// ** update
	public int update(BoardDTO dto) {
		sql = "update board set title = ?, content = ? where seq = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getTitle());
			pst.setString(2, dto.getContent());
			pst.setInt(3, dto.getSeq());

			return pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** update Exception => " + e.toString());
			return 0;
		}
	} // update

	// ** delete
	public int delete(BoardDTO dto) {
		sql = "delete from board where seq = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getSeq());

			return pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** delete Exception => " + e.toString());
			return 0;
		}
	} // delete

} // class
