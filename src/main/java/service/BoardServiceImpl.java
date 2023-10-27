package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.BoardDTO;
import domain.MemberDTO;
import model.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	// ** 전역변수 정의
	@Autowired
	BoardDAO dao;

	// ** selectList
	@Override
	public List<BoardDTO> selectList() {
		return dao.selectList();
	}

	// ** selectOne
	@Override
	public BoardDTO selectOne(BoardDTO vo) {
		return dao.selectOne(vo);
	}

	@Override
	public int insert(BoardDTO dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(BoardDTO dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(BoardDTO dto) {
		return dao.delete(dto);
	}

	@Override
	public int updateReadCount(BoardDTO dto) {
		return dao.updateReadCount(dto);
	}

} // class
