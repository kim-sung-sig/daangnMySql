package kr.ezen.daangn.domain.lifeboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1")
public class ApiLifeBoardController {

    @GetMapping("/lifeBoards")
    public ResponseEntity<?> getLifeBoardList(){
        return ResponseEntity.status(401).body(null);
    }

    @GetMapping("/lifeBoards/{idx}")
    public void getLifeBoardByIdx(@PathVariable("idx") int idx){

    }

    @PostMapping("/lifeBoards")
    public void saveLifeBoard() {
        
    }
    
    @PutMapping("/lifeBoards/{idx}")
    public void updateLifeBoard(@PathVariable("idx") int idx) {
        
    }
    
}
