package com.jake.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
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
	public String join(HttpServletRequest request, MemberDTO dto, Model model) throws IOException {
		String realPath = request.getRealPath("/");

		// 개발중인지 배포중인지 확인
		if (realPath.contains(".eclipse.")) {
			realPath = "C:\\Users\\baedd\\git\\WebApplication\\src\\main\\webapp\\resources\\uploadImages\\"; // 데스크탑 개발
//			realPath = "C:\\MyWorkspace\\web\\src\\main\\webapp\\resources\\uploadImages\\";				//노트북 개발
		} else {
			realPath += "resources\\uploadImages\\";
		}

		// 해당 파일이 존재하지않으면, 파일을 생성해라.
		File file = new File(realPath);
		if (!file.exists())
			file.mkdir();

		// 기본이미지 설정
		String file1, file2 = "resources/uploadImages/basicman4.png";

		MultipartFile uploadfilef = dto.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) { // memberJoin 화면에서 설정한 이미지가 비어있지 않다면,
			file1 = realPath + uploadfilef.getOriginalFilename(); // 실제경로에 파일명(이름.확장자)을 뒤에 붙여라
			// MultipartFile의 transferTo 메서드는 매개변수로 File 타입을 받는다.
			// 이 File은 복사받을 위치를 매개변수로 설정하고, 이미지를 선택한 순간 uploadfilef 변수에는 해당 이미지의 모든 정보를 갖고
			// 있음으로 transferTo 함수가 실행되면서 복사완료된다.
			uploadfilef.transferTo(new File(file1));

			// 데이터베이스에 저장되는 파일의 경로
			// 이후에 프론트에서 화면을 출력할 때, Spring은 이미지를 src/main/webapps/ 아래에서부터 찾기 시작한다.
			// 그래서 아래와 같이 데이터베이스에 값을 입력한다.
			file2 = "resources/uploadImages/" + uploadfilef.getOriginalFilename();
			System.out.println(uploadfilef.getOriginalFilename());
		}

		// 최종적으로 dto에 데이터베이스에 저장될 값을 set으로 넣어준다.
		dto.setUploadfile(file2);

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
	public String update(HttpServletRequest request, MemberDTO dto, HttpSession session, RedirectAttributes rttr,
			Model model) throws IllegalStateException, IOException {

		String realPath = "C:\\Users\\baedd\\git\\WebApplication\\src\\main\\webapp\\resources\\uploadImages\\";
		String file1, file2 = "";

		// => newImage 선택한 경우
		MultipartFile uploadfilef = dto.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) { // 새로운 이미지파일 선택
			file1 = realPath + uploadfilef.getOriginalFilename(); // 실제 파일이 저장되는 경로
			uploadfilef.transferTo(new File(file1)); // 경로에 저장하는 방법
			file2 = "resources/uploadImages/" + uploadfilef.getOriginalFilename(); // 테이블에 저장되는 경로
		}

		dto.setUploadfile(file2);

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
