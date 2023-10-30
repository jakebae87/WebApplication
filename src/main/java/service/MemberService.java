package service;

import java.util.List;

import domain.MemberDTO;

public interface MemberService {

	List<MemberDTO> selectList();

	List<MemberDTO> joList(int jno);
	
	MemberDTO selectOne(MemberDTO vo);

	int insert(MemberDTO dto);

	int update(MemberDTO dto);

	int delete(MemberDTO dto);
	
}