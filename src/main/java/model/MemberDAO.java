package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import domain.MemberDTO;

//** DAO(Data Access Object)
//=> SQL 구문 처리
//=> CRUD 구현 
//   Create(Insert), Read(selectList, selectOne), Update, Detete

//=> 첫번째 예제 Ex01_~~~ 와 DAO 와 다른점
// 	- 요청 처리 결과를 제공
//	- 즉, 메서드의 역할별로 처리결과를 return 해야함
//	- 그러므로 특히 select 결과를 전달하기위해 결과를 담는 작업이 필요함  

@Component
public class MemberDAO {
	// ** 전역변수 정의
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;

	// ** Jo_List 추가
	public List<MemberDTO> joList(int jno) {
		sql = "select * from member where jno=?";
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, jno);
			rs = pst.executeQuery();
			if (rs.next()) {
				do {
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setAge(rs.getInt(4));
					dto.setJno(rs.getInt(5));
					dto.setInfo(rs.getString(6));
					dto.setPoint(rs.getDouble(7));
					dto.setBirthday(rs.getString(8));
					dto.setRid(rs.getString(9));
					list.add(dto);
				} while (rs.next());
			} else {
				System.out.println("** Member joList: 출력자료가 1도 없습니다. **");
				list = null;
			} // else
		} catch (Exception e) {
			System.out.println("** Member joList Exception => " + e.toString());
			list = null;
		} // try
		return list;
	} // joList

	// ** selectList
	public List<MemberDTO> selectList() {
		sql = "select * from member";
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				// => selectList 결과 존재
				// => 결과를 list 에 담기
				do {
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setAge(rs.getInt(4));
					dto.setJno(rs.getInt(5));
					dto.setInfo(rs.getString(6));
					dto.setPoint(rs.getDouble(7));
					dto.setBirthday(rs.getString(8));
					dto.setRid(rs.getString(9));
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
	public MemberDTO selectOne(MemberDTO dto) {
		sql = "select * from member where id=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			rs = pst.executeQuery();
			if (rs.next()) {
				// => Data 존재: rs 을 vo에 담아서 return
				dto.setPassword(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAge(rs.getInt(4));
				dto.setJno(rs.getInt(5));
				dto.setInfo(rs.getString(6));
				dto.setPoint(rs.getDouble(7));
				dto.setBirthday(rs.getString(8));
				dto.setRid(rs.getString(9));
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
	// => 입력 컬럼: name, age, jno, info
	public int insert(MemberDTO dto) {
		sql = "insert into member values(?,?,?,?,?,?,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPassword());
			pst.setString(3, dto.getName());
			pst.setInt(4, dto.getAge());
			pst.setInt(5, dto.getJno());
			pst.setString(6, dto.getInfo());
			pst.setDouble(7, dto.getPoint());
			pst.setString(8, dto.getBirthday());
			pst.setString(9, dto.getRid());

			// int count = pst.executeUpdate();
			// return count;
			// => 비교
			return pst.executeUpdate(); // 처리갯수

		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			return 0;
		}
	} // insert

	// ** update
	// => info, point, birthday 수정
	public int update(MemberDTO dto) {
		sql = "update member set name = ?, age = ?, jno = ?, info = ?, point = ?, birthday = ?, rid = ? where id = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());
			pst.setDouble(5, dto.getPoint());
			pst.setString(6, dto.getBirthday());
			pst.setString(7, dto.getRid());
			pst.setString(8, dto.getId());

			return pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** update Exception => " + e.toString());
			return 0;
		}
	} // update

	// ** delete
	// => sno 로 삭제
	public int delete(MemberDTO dto) {
		sql = "delete from member where id = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());

			return pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** delete Exception => " + e.toString());
			return 0;
		}
	} // delete

	public void transactionTest() {
		sql = "insert into student (sno, name, age, jno, info) values (25, 'nameTest', 28, 7, 'infoTest')";

		// 1) Transaction 적용전
		try {
			cn.setAutoCommit(false); // mysql에서 start transaction과 같음
			pst = cn.prepareStatement(sql);

			pst.executeUpdate(); // 1번째는 Table에 입력완료 -> setAutoCommit(false)가 위에 있으면 Buffer 에 입력됨
			pst.executeUpdate(); // 2번째는 p.key 중복 오류
			cn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("** Transaction1 Exception => " + e.toString());

			// => rollback
			try {
				cn.rollback();
				System.out.println("** Rollback 성공 **");
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("** Rollback Exception => " + e2.toString());
			}
		}
	}

} // class
