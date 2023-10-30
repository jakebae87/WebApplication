package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import domain.BoardDTO;
import domain.ReplyDTO;

@Component
public class ReplyDAO {
	private static Connection cn = DBConnection.getConnection();
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;

	public List<ReplyDTO> selectList(BoardDTO dto) {
		sql = "select * from reply where post = ?";
		List<ReplyDTO> list = new ArrayList<ReplyDTO>();
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getSeq());
			rs = pst.executeQuery();
			if (rs.next()) {
				do {
					ReplyDTO rdto = new ReplyDTO();
					rdto.setSeq(rs.getInt(1));
					rdto.setPost(rs.getInt(2));
					rdto.setId(rs.getString(3));
					rdto.setContent(rs.getString(4));
					rdto.setRegdate(rs.getString(5));
					list.add(rdto);
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

	public int insert(ReplyDTO dto) {
		sql = "insert into reply (post,id,content) values (?,?,?); ";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getPost());
			pst.setString(2, dto.getId());
			pst.setString(3, dto.getContent());

			return pst.executeUpdate(); // 처리갯수

		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			return 0;
		}
	} // insert

	public int update(ReplyDTO dto) {
		sql = "update reply set content = ? where post = ? and seq = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getContent());
			pst.setInt(2, dto.getPost());
			pst.setInt(3, dto.getSeq());
			return pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** update Exception => " + e.toString());
			return 0;
		}
	} // update

	public ReplyDTO selectOne(ReplyDTO dto) {
		sql = "select * from reply where post = ? and seq = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getPost());
			pst.setInt(2, dto.getSeq());
			rs = pst.executeQuery();
			if (rs.next()) {
				dto.setSeq(rs.getInt(1));
				dto.setPost(rs.getInt(2));
				dto.setId(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectOne Exception => " + e.toString());
			return null;
		}
	}

	public int delete(ReplyDTO dto) {
		sql = "delete from reply where post = ? and seq = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getPost());
			pst.setInt(2, dto.getSeq());

			return pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** delete Exception => " + e.toString());
			return 0;
		}
	} // delete
} // class
