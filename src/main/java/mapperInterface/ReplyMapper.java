package mapperInterface;

import java.util.List;

import domain.BoardDTO;
import domain.ReplyDTO;

public interface ReplyMapper {

	List<ReplyDTO> selectList(BoardDTO dto);

	ReplyDTO selectOne(ReplyDTO vo);

	int insert(ReplyDTO dto);

	int update(ReplyDTO dto);

	int delete(ReplyDTO dto);
	
}
