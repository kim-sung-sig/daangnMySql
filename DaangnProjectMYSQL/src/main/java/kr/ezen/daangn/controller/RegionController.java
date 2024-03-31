package kr.ezen.daangn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.ezen.daangn.service.RegionService;

@RestController
public class RegionController {
	
	@Autowired
	private RegionService regionService;
	
	// 지역을 넘겨주는 컨트롤러
	@GetMapping("/region")
	public List<String> getRegion(@RequestParam(value = "region", required = false) String region,
								  @RequestParam(value = "gu", required = false) String gu,
								  @RequestParam(value = "dong", required = false) String dong){
		List<String> regionList = regionService.regionList(region, gu, dong);
		return regionList;
	}
}
