package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.JoDTO;
import model.JoDAO;

@Service
public class JoServiceImpl implements JoService {
	@Autowired
	JoDAO dao;

	@Override
	public List<JoDTO> selectList() {
		return dao.selectList();
	}

	// ** selectOne
	@Override
	public JoDTO selectOne(JoDTO vo) {
		return dao.selectOne(vo);
	}

	@Override
	public int insert(JoDTO dto) {
		return dao.insert(dto);
	}

	@Override
	public int update(JoDTO dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(JoDTO dto) {
		return dao.delete(dto);
	}

} // class
