package kr.ezen.daangn.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RegionService {
	
	public List<String> regionList(String region , String gu , String dong ) {
		List<String> list = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonNode = objectMapper.readTree(getClass().getResourceAsStream("/static/data/data.json"));
			if(region == null) { // 리턴() => 서울시, 강원도,.. 
				Iterator<String> regionNames = jsonNode.fieldNames();
				while (regionNames.hasNext()) {
					list.add(regionNames.next());
				}
			} else if (gu == null){ // 리턴 (서울시)=>강서구, 양천구 ...
				JsonNode regionNode = jsonNode.path(region);
				Iterator<String> guNames = regionNode.fieldNames();
				while (guNames.hasNext()) {
					list.add(guNames.next());
				}
			} else { // 리턴 ( 서울시, 강서구) => 화곡1동, 신월동, 목3동  or (서울시, 강서구, 화곡1동) => 화곡1동, 신월동, 목3동
				JsonNode guNode = jsonNode.path(region).path(gu);
				if (guNode.isArray()) {
					for (JsonNode dongNode : guNode) {
						list.add(dongNode.asText());
					}
				}
			}
			list.sort(Comparator.naturalOrder());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
