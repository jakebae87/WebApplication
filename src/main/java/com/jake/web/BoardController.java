package com.jake.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.BoardDTO;
import domain.ReplyDTO;
import lombok.AllArgsConstructor;
import service.BoardServiceImpl;
import service.ReplyServiceImpl;

@AllArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

	BoardServiceImpl service;
	ReplyServiceImpl rservice;

	// 글목록
	@GetMapping(value = "/boardList")
	public void boardList(Model model) {
		model.addAttribute("banana", service.selectList());
	}

	// 글상세정보
	@GetMapping(value = "/boardDetail")
	public void detail(Model model, BoardDTO dto, HttpSession session) {
		if (!service.selectOne(dto).getId().equals((String) session.getAttribute("loginID"))) {
			service.updateReadCount(dto);
		}
		model.addAttribute("apple", service.selectOne(dto));
		model.addAttribute("tomato", rservice.selectList(dto));
	}

	// 글작성
	@GetMapping(value = "/boardWrite")
	public void boardWrite() {
	}

	// 글작성
	@PostMapping(value = "/write")
	public String write(BoardDTO dto, Model model, RedirectAttributes rttr) {

		String uri = "redirect:boardList";

		if (service.insert(dto) > 0) {
			rttr.addFlashAttribute("message", "글 등록 성공");
		} else {
			model.addAttribute("message", "글 등록 실패");
			uri = "board/writeForm";
		}
		return uri;
	}

	// 글수정
	@GetMapping(value = "/boardUpdate")
	public void boardUpdate(BoardDTO dto, Model model) {
		model.addAttribute("apple", service.selectOne(dto));
	}

	// 글수정
	@PostMapping(value = "/update")
	public String update(BoardDTO dto, Model model, RedirectAttributes rttr) {
		model.addAttribute("apple", dto);
		String uri = "redirect:boardDetail?seq=" + dto.getSeq();

		if (service.update(dto) > 0) {
			rttr.addFlashAttribute("message", "글 수정 성공");
		} else {
			model.addAttribute("message", "글 수정 실패");
			uri = "board/boardUpdate";
		}
		return uri;
	}

	@GetMapping(value = "/delete")
	public String delete(BoardDTO dto, Model model, RedirectAttributes rttr) {
		String uri = "redirect:boardList";

		if (service.delete(dto) > 0) {
			rttr.addFlashAttribute("message", "글 삭제 성공");
		} else {
			rttr.addFlashAttribute("message", "글 삭제 실패");
		}
		return uri;
	}

	// -------------------------------------------------------------------------
	// 댓글 기능 구현
	// -------------------------------------------------------------------------

	@GetMapping(value = "/replyWrite")
	public void replyWrite(ReplyDTO dto, Model model) {
		model.addAttribute("number", dto);
	}

	@PostMapping(value = "/replyWrite")
	public String write(ReplyDTO dto, Model model, RedirectAttributes rttr) {

		String uri = "redirect:boardDetail?seq=" + dto.getPost();

		if (rservice.insert(dto) > 0) {
			rttr.addFlashAttribute("message", "댓글 등록 성공");
		} else {
			model.addAttribute("message", "댓글 등록 실패");
			uri = "board/replyWrite";
		}
		return uri;
	}

	@GetMapping(value = "/replyUpdate")
	public void replyUpdate(ReplyDTO dto, Model model) {
		model.addAttribute("banana", rservice.selectOne(dto));
	}

	@PostMapping(value = "/replyUpdate")
	public String update(ReplyDTO dto, Model model, RedirectAttributes rttr) {

		String uri = "redirect:boardDetail?seq=" + dto.getPost();
		if (rservice.update(dto) > 0) {
			rttr.addFlashAttribute("message", "댓글 수정 성공");
		} else {
			model.addAttribute("message", "댓글 수정 실패");
			uri = "board/replyWrite";
		}
		return uri;
	}

	@GetMapping(value = "/replyDelete")
	public String delete(ReplyDTO dto, Model model, RedirectAttributes rttr) {
		String uri = "redirect:boardDetail?seq=" + dto.getPost();

		if (rservice.delete(dto) > 0) {
			rttr.addFlashAttribute("message", "댓글 삭제 성공");
		} else {
			rttr.addFlashAttribute("message", "댓글 삭제 실패");
		}
		return uri;
	}

}
