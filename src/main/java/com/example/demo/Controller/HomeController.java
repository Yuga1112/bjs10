package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	/*리턴타입 : 
	 * void : url 주소가 파일의 경로가 됨
	 * String : 파일의 경로를 직접 입력
	 * */
	@GetMapping("/")
	public String home() {
		//반환값 : home 폴더 아래 main.html
		return "/home/main";
	}
}
