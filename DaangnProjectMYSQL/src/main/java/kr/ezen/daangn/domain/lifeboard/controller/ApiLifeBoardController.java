package kr.ezen.daangn.domain.lifeboard.controller;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ezen.daangn.domain.lifeboard.dao.DaangnLifeBoardDAO2;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApiLifeBoardController {

    private final DaangnLifeBoardDAO2 daangnLifeBoardDAO2;

    @GetMapping("/lifeBoards")
    public ResponseEntity<?> getLifeBoardList(){
        return ResponseEntity.status(401).body(null);
    }

    @GetMapping("/lifeBoards/{idx}")
    public void getLifeBoardByIdx(@PathVariable("idx") int idx){
        try {
            var data = daangnLifeBoardDAO2.selectByIdx(idx);
            System.out.println(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/lifeBoards")
    public void saveLifeBoard() {
        
    }
    
    @PutMapping("/lifeBoards/{idx}")
    public void updateLifeBoard(@PathVariable("idx") int idx) {
        
    }
    
}
