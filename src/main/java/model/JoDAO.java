package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import domain.JoDTO;
import domain.MemberDTO;

@Component
public class JoDAO {
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;

	// ** selectList
	public List<JoDTO> selectList() {
		sql = "select j.jno, j.jname, j.id, m.name, j.project, j.slogan "
				+ "from jo j left join member m "
				+ "on j.id = m.id ";
		List<JoDTO> list = new ArrayList<JoDTO>();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				// => selectList 결과 존재
				// => 결과를 list 에 담기
				do {
					JoDTO dto = new JoDTO();
					dto.setJno(rs.getInt(1));
					dto.setJname(rs.getString(2));
					dto.setId(rs.getString(3));
					dto.setName(rs.getString(4));
					dto.setProject(rs.getString(5));
					dto.setSlogan(rs.getString(6));
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

	// ** selectOne
	public JoDTO selectOne(JoDTO dto) {
		sql = "select j.jno, j.jname, j.id, m.name, j.project, j.slogan "
				+ "from jo j left join member m "
				+ "on j.id = m.id where j.jno = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getJno());
			rs = pst.executeQuery();
			if (rs.next()) {
				dto.setJno(rs.getInt(1));
				dto.setJname(rs.getString(2));
				dto.setId(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setProject(rs.getString(5));
				dto.setSlogan(rs.getString(6));
				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectOne Exception => " + e.toString());
			return null;
		}
	} // selectOne

	public int insert(JoDTO dto) {
		sql = "insert into jo values(?,?,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getJno());
			pst.setString(2, dto.getJname());
			pst.setString(3, dto.getId());
			pst.setString(4, dto.getProject());
			pst.setString(5, dto.getSlogan());

			return pst.executeUpdate(); // 처리갯수

		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			return 0;
		}
	} // insert

	public int update(JoDTO dto) {
		sql = "update jo set jname = ?, id = ?, project = ?, slogan = ? where jno = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getJname());
			pst.setString(2, dto.getId());
			pst.setString(3, dto.getProject());
			pst.setString(4, dto.getSlogan());
			pst.setInt(5, dto.getJno());

			return pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** update Exception => " + e.toString());
			return 0;
		}
	} // update

	public int delete(JoDTO dto) {
		sql = "delete from jo where jno = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getJno());

			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** delete Exception => " + e.toString());
			return 0;
		}
	} // delete

} // class
