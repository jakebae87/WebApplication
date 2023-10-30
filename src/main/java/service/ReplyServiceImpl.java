package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.BoardDTO;
import domain.ReplyDTO;
import model.BoardDAO;
import model.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	ReplyDAO dao;

	@Override
	public List<ReplyDTO> selectList(BoardDTO dto) {
		return dao.selectList(dto);
	}

	@Override
	public ReplyDTO selectOne(ReplyDTO vo) {
		return dao.selectOne(vo);
	}

	@Override
	public int insert(ReplyDTO dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(ReplyDTO dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(ReplyDTO dto) {
		return dao.delete(dto);
	}

} // class
