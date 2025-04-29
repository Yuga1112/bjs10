package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;


@Controller
@RequestMapping("/board")  //중간경로
public class BoardController {

	//컨트롤러에서 사용할 서비스를 필드로 선언
	@Autowired
	BoardService service;
	
  
    
    //게시물 목록 화면을 반환하는 함수
//    @GetMapping("/list")
//    public void list(Model model) {
//    	//리턴값 : board 폴더 아래 list.html 파일
//    	//서비스로 게시물 리스트 조회
//    	List<BoardDTO> list = service.getList();
//    	
//    	//화면에 리스트 전달
//    	model.addAttribute("list", list);
//    }
    
    //게시물 목록 화면을 반환하는 함수2
    //페이지 번호를 파라미터로 수집
    //URL : /board/list?page=1
    //URL : /board/list (페이지번호는 기본값0)
    //파라미터가 없으면 첫번째 페이지 표시
    @GetMapping("/list")
    public void list(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {
    	
    	//게시물 목록조회
    	Page<BoardDTO> result = service.getList(page);
    	
    	// 화면에 게시물 데어터를 전달
    	model.addAttribute("list", result);
    	
    	System.out.println("전체 페이지 수" + result.getTotalPages());
    	System.out.println("현재 페이지 번호" + (result.getNumber() + 1));
    }
    
    //게시물 등록화면을 반환하는 함수
    @GetMapping("/register")
    public void register() {
    	//반환값 : board 폴더 아래 register.html 파일
    }
    
   // 게시물을 등록하는 함수
    //폼화면에서 전달한 데이터를 파라미터로 수집
    //RedirectAttributes : 리다이렉트시 화면에 데이터를 전달하는 개개체
    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
    
    	//전달받은 게시물을 등록하고 새운 게시물 번호 받기
    	int newNo = service.register(dto);
    	
    	//등록이 끝났으면 목록 화면으로 이동
    	redirectAttributes.addFlashAttribute("NewNo", newNo);
    	
    	//리디이렉트 : 새로운 주소를 다시 호출하는 것
    	return "redirect:/board/list";
    }
    
    //상세화면을 반환하는 메소드
    //게시물 번호를 파라미터로 수집
    //url 예시 : /board/read?no=1
    @GetMapping("/read")
    public void read(@RequestParam("no") int no, @RequestParam(name = "page") int page, Model model) {
    	//반환값 : board 폴더 아래 read.html
    	
    	
    	//서비스를 사용해서 특정 게시물을 조회
    	BoardDTO dto = service.read(no);
    	model.addAttribute("dto", dto);
    	//화면에 게시물을 전달
    	//수집한 파라미터를 그대로 전달
    	model.addAttribute("page", page);
    	
    	
    	}
    //수정화면을 반환하는 함수
    // 파라미터로 게시물 번호 수집
    // 예시 : URL : /board/modify?no=1
    @GetMapping("/modify")
    public void modify(Model model, @RequestParam(name = "no") int no) {
    	
    	//결과값 : board 폴더아래 modify.html
    	//화면에 특정 게시물 데이터 전달
    	
    	//게시물 조회
    	BoardDTO dto = service.read(no);
    	
    	//화면에 특정게시물 데이터 전달
    	model.addAttribute("dto", dto);
    }
    
    //수정처리
    @PostMapping("/modify")
    public String modifyPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
    	
    	//서비스로 수정 처리
    	service.modify(dto);
    	
    	// 상세화면으로 이동할때 게시물번호를 파라미터로 추가
    	// ?no=1
    	redirectAttributes.addAttribute("no", dto.getNo());
    	// 예상주소 : /board/read?no=1
    	
    	// 수정처리 끝났으면 상세페이지로
    	return "redirect:/board/read";
    }
    
    //삭제처리
    //파라미터로 게시물 번호 수집
    //url : /board/remove?no=1
    @PostMapping("/remove")
    public String remove(@RequestParam("no") int no) {
    	service.remove(no);
    	
    	//삭제가 끝나면 목록화면으로
    	return "redirect:/board/list";
    }
 }
