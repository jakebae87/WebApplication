package service;

import java.util.List;

import domain.BoardDTO;
import domain.MemberDTO;
import domain.ReplyDTO;

public interface BoardService {

	List<BoardDTO> selectList();

	BoardDTO selectOne(BoardDTO vo);

	int insert(BoardDTO dto);

	int update(BoardDTO dto);

	int delete(BoardDTO dto);

	int updateReadCount(BoardDTO dto);

}