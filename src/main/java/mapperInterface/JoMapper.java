package mapperInterface;

import java.util.List;

import domain.JoDTO;

public interface JoMapper {

	List<JoDTO> selectList();

	JoDTO selectOne(JoDTO vo);

	int insert(JoDTO dto);

	int update(JoDTO dto);

	int delete(JoDTO dto);

}
