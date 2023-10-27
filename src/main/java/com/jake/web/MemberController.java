package com.jake.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import service.MemberService;

//** Spring 의 redirect ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//** RedirectAttributes
//=> Redirect 할 때 파라메터를 쉽게 전달할 수 있도록 지원하며,
// addAttribute, addFlashAttribute, getFlashAttribute 등의 메서드가 제공됨.
//=> addAttribute
// - url에 퀴리스트링으로 파라메터가 붙어 전달됨. 
//  - 그렇기 때문에 전달된 페이지에서 파라메터가 노출됨.

//=> addFlashAttribute
// - Redirect 동작이 수행되기 전에 Session에 값이 저장되고 전달 후 소멸된다.
//  - Session을 선언해서 집어넣고 사용 후 지워주는 수고를 덜어주고,
//    -> url에 퀴리스트링으로 붙지 않기때문에 깨끗하고 f5(새로고침)에 영향을 주지않음.  
//    -> 주의사항 
//       받는쪽 매핑메서드의 매개변수로 parameter를 전달받는 VO가 정의되어 있으면
//         이 VO 생성과 관련된 500 발생 하므로 주의한다.
//      ( Test : JoController 의 jupdate 성공시 redirect:jdetail )
//      단, VO로 받지 않는 경우에는 url에 붙여 전달하면서 addFlashAttribute 사용가능함        

//=> getFlashAttribute
//    - insert 성공 후 redirect:jlist 에서 Test (JoController, 결과는 null)
//    - 컨트롤러에서 addFlashAttribute 가 session에 보관한 값을 꺼내는것은 좀더 확인이 필요함 

//** redirect 로 한글 parameter 전달시 한글깨짐
//=> 한글깨짐이 발생하는경우 사용함.
//=> url 파라메터 로 전달되는 한글값 을 위한 encoding
//    - String message = URLEncoder.encode("~~ member 가 없네용 ~~", "UTF-8");
//      mv.setViewName("redirect:mlist?message="+message);  
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//** Model & ModelAndView **

//=> Model(interface)
//-> controller처리 후 데이터(Model) 을 담아서 반환 
//-> 구현클래스 : ConcurrentModel, ExtendedModelMap 등.
//-> 아래 mapping 메서드 처럼, ModelAndView 보다 심플한 코드작성 가능하므로 많이사용됨. 
//mv.setViewName("~~~~~") 하지않고 viewName 을 return 

//=> ModelAndView (class)
//-> controller처리 후 데이터(Model) 와 viewName 을 담아서 반환
//-> Object -> ModelAndView
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//** @RequestMapping
//=> DefaultAnnotationHandlerMapping에서 컨트롤러를 선택할 때 대표적으로 사용하는 애노테이션. 
//=> DefaultAnnotationHandlerMapping은 클래스와 메서드에 붙은 @RequestMapping 애노테이션 정보를 결합해 최종 매핑정보를 생성한다.
//=> 기본적인 결합 방법은 클래스 레벨의 @RequestMapping을 기준으로 삼고, 
//  메서드 레벨의 @RequestMapping으로 세분화하는 방식으로 사용된다.

//** @RequestMapping 특징
//=> url당 하나의 컨트롤러에 매핑되던 다른 핸들러 매핑과 달리 메서드 단위까지 세분화하여 적용할 수 있으며,
//url 뿐 아니라 파라미터, 헤더 등 더욱 넓은 범위를 적용할 수 있다. 
//=> 요청과 매핑메서드 1:1 mapping 
//=> value="/mlist" 
// : 이때 호출되는 메서드명과 동일하면 value 생략가능 그러나 value 생략시 404 (확인필요함)
// : 해당 메서드 내에서 mv.setViewName("...."); 을 생략하면
//   요청명을 viewName 으로 인식 즉, mv.setViewName("mlist") 으로 처리함.

//** @RequestMapping 속성
//=> value : URL 패턴 ( 와일드카드 * 사용 가능 )
//  @RequestMapping(value="/post")
//  @RequestMapping(value="/post.*")
//  @RequestMapping(value="/post/**/comment")
//  @RequestMapping(value={"/post", "/P"}) : 다중매핑 가능

//=> method 
// @RequestMapping(value="/post", method=RequestMethod.GET)
// -> url이 /post인 요청 중 GET 메서드인 경우 호출됨
// @RequestMapping(value="/post", method=RequestMethod.POST)
// -> url이 /post인 요청 중 POST 메서드인 경우 호출됨
//    GET, POST, PUT, DELETE, OPTIONS, TRACE 총 7개의 HTTP 메서드가 정의되어 있음.
//    ( 이들은 아래 @GetMapping ... 등으로도 좀더 간편하게 사용가능 )  

//=> params : 요청 파라미터와 값으로도 구분 가능함.
// @RequestMapping(value="/post", params="useYn=Y")
// -> /post?useYn=Y 일 경우 호출됨
// @RequestMapping(value="/post", params="useYn!=Y")
// ->  not equal도 가능
// @RequestMapping(value="/post", parmas="useYn")
// > 값에 상관없이 파라미터에 useYn이 있을 경우 호출됨
// @RequestMapping(value="/post", params="!useYn")
// > 파라미터에 useYn이 없어야 호출됨
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
@Log4j
@AllArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

//	@Autowired
	MemberService service;

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
