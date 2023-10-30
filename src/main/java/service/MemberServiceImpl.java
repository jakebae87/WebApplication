package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import domain.MemberDTO;
import mapperInterface.MemberMapper;
import model.MemberDAO;

@Service
public class MemberServiceImpl implements MemberMapper {
	// ** 전역변수 정의
	@Autowired
	MemberMapper dao;

	// ** selectList
	@Override
	public List<MemberDTO> selectList() {
		return dao.selectList();
	}

	// ** selectOne
	@Override
	public MemberDTO selectOne(MemberDTO vo) {
		return dao.selectOne(vo);
	}

	@Override
	public int insert(MemberDTO dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(MemberDTO dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(MemberDTO dto) {
		return dao.delete(dto);
	}

	@Override
	public List<MemberDTO> joList(int jno) {
		return dao.joList(jno);
	}

} // class
