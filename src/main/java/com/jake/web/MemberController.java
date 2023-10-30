package com.jake.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import service.MemberServiceImpl;

@Log4j
@AllArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

//	@Autowired
	MemberServiceImpl service;

	// Lombok의 log4j Test
	@GetMapping(value = "/log4jtest")
	public String log4jTest() {
		String name = "바나나";
		log.error("롬복 log_레벨 error: name=" + name);
		log.warn("롬복 log_레벨 warn: name=" + name);
		log.info("롬복 log_레벨 info: name=" + name);
		log.debug("롬복 log_레벨 debug: name=" + name);
		return "redirect:home";
	}

	@GetMapping(value = "/memberLogin")
	public void memberLogin() {
	}

	@PostMapping(value = "/login")
	public String login(MemberDTO dto, Model model, HttpServletRequest request) {

		String password = dto.getPassword(); // 입력한 비밀번호는 변수에 저장

		MemberDTO member = service.selectOne(dto); // 입력한 id를 바탕으로 객체생성

		if (member != null && password.equals(member.getPassword())) {
			request.setAttribute("message", "로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("loginID", member.getId());
			session.setAttribute("loginName", member.getName());
			session.setAttribute("loginPassword", member.getPassword());
			session.setAttribute("jno", member.getJno());
		} else {
			request.setAttribute("message", "로그인 실패");
			return "/member/memberLogin";
		}
		return "redirect:/home";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.invalidate();
		rttr.addFlashAttribute("message", "로그아웃 성공");
		return "redirect:/home";
	}

	@GetMapping(value = "/memberJoin")
	public void memberJoin() {
	}

	@PostMapping(value = "/join")
	public String join(MemberDTO dto, Model model) {
		if (service.insert(dto) > 0) {
			model.addAttribute("message", "회원가입 성공");
		} else {
			model.addAttribute("message", "회원가입 실패");
			return "member/memberJoin";
		}
		return "member/memberLogin";
	}

	@GetMapping(value = "/delete")
	public String delete(MemberDTO dto, Model model, HttpSession session, RedirectAttributes rttr) {

		String selfName = (String) session.getAttribute("loginName"); // 본인 삭제
		String name = service.selectOne(dto).getName();

		String uri = "redirect:/home";

		if (service.delete(dto) > 0) {
			if (dto.getId().equals(session.getAttribute("loginID"))) {
				session.invalidate();
				rttr.addFlashAttribute("message", selfName + "님 회원삭제 성공");
			} else {
				rttr.addFlashAttribute("message", name + "회원 삭제 성공");
				uri = "redirect:memberList";
			}
		} else {
			rttr.addFlashAttribute("message", "회원삭제 실패");
			return "redirect:memberList";
		}
		return uri;
	}

	@GetMapping(value = "/memberList")
	public void list(Model model) {
		model.addAttribute("banana", service.selectList());
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model, MemberDTO dto) {
		model.addAttribute("apple", service.selectOne(dto));
		return "member/memberDetail";
	}

	@GetMapping(value = "/memberUpdate")
	public void memberUpdate(Model model, MemberDTO dto) {
		model.addAttribute("apple", service.selectOne(dto));
	}

	@PostMapping(value = "/update")
	public String update(MemberDTO dto, HttpSession session, RedirectAttributes rttr, Model model) {
		String uri = "redirect:/home";
		// session에 보관하면 해킹의 위험이 있어서 보관못하게 되어있다.
		if (session.getAttribute("loginPassword").equals(dto.getPassword())) {
			if (service.update(dto) > 0) {
				rttr.addFlashAttribute("message", "수정 완료");
				uri = "redirect:detail?id=" + dto.getId();
			} else {
				model.addAttribute("message", "수정 실패");
				model.addAttribute("apple", dto);
				uri = "member/memberUpdate";
				return uri;
			}
		} else {
			model.addAttribute("message", "비밀번호 불일치");
			model.addAttribute("apple", dto);
			uri = "member/memberUpdate";
			return uri;
		}
		return uri;
	}
}
