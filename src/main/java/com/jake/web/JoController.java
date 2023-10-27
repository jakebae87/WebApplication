package com.jake.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.JoDTO;
import lombok.AllArgsConstructor;
import service.JoService;
import service.MemberService;

@AllArgsConstructor
@RequestMapping("/jo")
@Controller
public class JoController {

	JoService service;
	MemberService mservice;

	@GetMapping(value = "/joList")
	public void joList(Model model) {
		model.addAttribute("list", service.selectList());
	}

	@GetMapping(value = "/joDetail")
	public void detail(Model model, JoDTO dto) {
		model.addAttribute("apple", service.selectOne(dto));
		model.addAttribute("banana", mservice.joList(dto.getJno()));
	}

	@GetMapping(value = "/joCreate")
	public void joCreate() {

	}

	@PostMapping(value = "/create")
	public String create(JoDTO dto, Model model, RedirectAttributes rttr) {
		String uri = "redirect:joList";

		if (service.insert(dto) > 0) {
			rttr.addFlashAttribute("message", "조 생성 성공");
		} else {
			rttr.addFlashAttribute("message", "조 생성 실패");
			uri = "jo/joCreate";
		}

		return uri;
	}

	@GetMapping(value = "/delete")
	public String delete(JoDTO dto, RedirectAttributes rttr) {
		String uri = "redirect:joList";

		if (service.delete(dto) > 0) {
			rttr.addFlashAttribute("message", "조 삭제 성공");
		} else {
			rttr.addFlashAttribute("message", "조 삭제 실패");
		}
		return uri;
	}

	@GetMapping(value = "/joUpdate")
	public void joUpdate(JoDTO dto, Model model) {
		model.addAttribute("apple", service.selectOne(dto));
	}

	@PostMapping(value = "/update")
	public String update(JoDTO dto, Model model) {
		model.addAttribute("apple",dto);
		String uri = "jo/joDetail";

		if (service.update(dto) > 0) {
			model.addAttribute("message", "조 수정 성공");
		} else {
			model.addAttribute("message", "조 수정 실패");
			uri = "jo/joUpdate";
		}

		return uri;
	}
}
