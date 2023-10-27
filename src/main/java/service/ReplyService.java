package service;

import java.util.List;

import domain.BoardDTO;
import domain.ReplyDTO;

public interface ReplyService {

	// ** selectList
	List<ReplyDTO> selectList(BoardDTO dto);

	// ** selectOne
	ReplyDTO selectOne(ReplyDTO vo);

	int insert(ReplyDTO dto, int seq);

	int update(ReplyDTO dto, int seq);

	int delete(ReplyDTO dto);

}