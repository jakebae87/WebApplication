package service;

import java.util.List;

import domain.MemberDTO;

public interface MemberService {

	// ** selectList
	List<MemberDTO> selectList();

	List<MemberDTO> joList(int jno);
	
	// ** selectOne
	MemberDTO selectOne(MemberDTO vo);

	int insert(MemberDTO dto);

	int update(MemberDTO dto);

	int delete(MemberDTO dto);
	
}