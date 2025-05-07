package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

//Controller : 사용자 요청을 처리하는 컴포넌트

@Controller
//중간경로 @RequestMapping("/member") 
public class MemberController {

	@Autowired
	MemberService service; 
	
	//url :/member/list?page=1
	//목록화면을 반환하는 메소드
	@GetMapping("/member/list")
	public void list(Model model,@RequestParam(name = "page", defaultValue = "0") int page) {
		//반환값 : member 폴더아래 list.html
		
		Page<MemberDTO> list = service.getList(page);
		
		model.addAttribute("list", list);
		
	}
	
	//회원가입 화면을 빈환 메소드
	//반환값 : member 폴더 아래 register.html
	@GetMapping("/register")
	public String register() {
		return "/member/register";
		// 반환값 : member 폴더 아래 register.html
	}
	
	//새로운 회원 추가 메소드
	//새로운 회원 정보를 파라미터로 수집
	
	//회원가입 성공시 회원 목록화면
	//회원 목록화면은 관리자만 접근
	// 일반회원은 x
	@PostMapping("/register")
	public String registerPost(MemberDTO dto, RedirectAttributes attributes) {
		boolean result = service.register(dto);	
		
		//등록 성공 시 메인화면으로 이동
		// 그렇지 않으면 실패메세지
		if(result) {
			
			return "redirect:";
		} else {
			
			attributes.addFlashAttribute("msg", "아이디 중복, 등록실패");
			return "redirect:/register";
			
		}
		
	}
	//회원 상세 화면을 반환하는 메소드
	//파라미터로 회원 아이디 수집
	//url : /member/read?id=user1
	@GetMapping("/member/read")
	public void read(Model model, @RequestParam(name = "id") String id, @RequestParam(name = "page", defaultValue = "0") int page) {
		
		//아이디로 특정 회원으 정보를 조회
		MemberDTO dto = service.read(id);
		
		//화면에 회원 데이터를 전달
		model.addAttribute("dto", dto);
		
		//화면에 페이지 번호 전달
		model.addAttribute("page", page);
	}
}
