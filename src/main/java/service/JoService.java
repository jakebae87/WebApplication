package service;

import java.util.List;

import domain.JoDTO;

public interface JoService {

	List<JoDTO> selectList();

	JoDTO selectOne(JoDTO vo);

	int insert(JoDTO dto);

	int update(JoDTO dto);

	int delete(JoDTO dto);

}