package com.prachi.un_broke.controller;

import com.prachi.un_broke.dto.Category_DTO;
import com.prachi.un_broke.dto.Mode_DTO;
import com.prachi.un_broke.model.CustomUserDetails;
import com.prachi.un_broke.model.Mode;
import com.prachi.un_broke.service.ModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modes")
public class ModeServiceController {
    @Autowired
    ModeService modeService;

    @GetMapping("")
    public ResponseEntity<List<Mode>> getModes(@AuthenticationPrincipal CustomUserDetails userPrincipal){
        int userId = userPrincipal.getId();
        List<Mode> subcategories = modeService.getModes(userId);
        return ResponseEntity.ok(subcategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mode> getModeById(@PathVariable("id") int id, @AuthenticationPrincipal CustomUserDetails userPrincipal) {
        int userId = userPrincipal.getId();
        Mode subCat = modeService.getModeById(id, userId);
        return ResponseEntity.ok(subCat);
    }

    @PostMapping("")
    public ResponseEntity<Mode> createMode(@AuthenticationPrincipal CustomUserDetails userPrincipal, @RequestBody Mode_DTO mode) {
        int user_id = userPrincipal.getId();
        Mode mode1 = modeService.createMode(mode, user_id);
        return ResponseEntity.ok(mode1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mode> updateMode(@RequestBody Mode_DTO mode, @PathVariable("id") int id, @AuthenticationPrincipal CustomUserDetails userPrincipal) {
        int userId = userPrincipal.getId();
        Mode mode1 = modeService.updateMode(mode, id, userId);
        return ResponseEntity.ok(mode1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Mode>> deleteMode( @PathVariable("id") int id, @AuthenticationPrincipal CustomUserDetails userPrincipal){
        int userId = userPrincipal.getId();
        modeService.deleteMode(id, userId);
        return getModes(userPrincipal);
    }

}
