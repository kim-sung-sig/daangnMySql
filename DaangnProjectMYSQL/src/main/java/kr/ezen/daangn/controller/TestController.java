package kr.ezen.daangn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import kr.ezen.daangn.service.DaangnLifeBoardService;
import kr.ezen.daangn.vo.DaangnLifeBoardVO;
import kr.ezen.daangn.vo.ScrollVO;

@RestController
public class TestController {
	
	@Autowired
	private DaangnLifeBoardService lifeBoardService;
	
	@GetMapping("/asdfasdf")
	public List<DaangnLifeBoardVO> getList(@ModelAttribute ScrollVO cv){
		return lifeBoardService.selectPagedLifeBoards(cv);
	}
}
