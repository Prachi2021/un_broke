package com.prachi.un_broke.service;


import com.prachi.un_broke.LoggerClass;
import com.prachi.un_broke.dto.Category_DTO;
import com.prachi.un_broke.model.Category;
import com.prachi.un_broke.model.Mode;
import com.prachi.un_broke.model.User;
import com.prachi.un_broke.repository.ModeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Service
public class ModeService {
    @Autowired
    ModeRepo modeRepo;
    @Autowired
    UserService userService;

    public List<Mode> getModes(int user_id) {
        if(modeRepo!=null)
            return modeRepo.findByUserId(user_id);
        else {
            LoggerClass.provideError("------------------Mode List is null------------------");
            return null;
        }
    }
    public Mode getModeById(int id, int user_id) {
        return modeRepo.findById(id).orElse(null);
    }

    public Mode createMode(String mode, int user_id){
        User user = userService.getUserById(user_id);
        if(user != null) {
            Mode mode1 = new Mode(mode, user);
            return modeRepo.save(mode1);
        }
        else {
            LoggerClass.provideError("------------------Mode is null------------------");
            return null;
        }
    }

    public Mode updateMode(String mode, int id, int user_id){
        Mode mode1 = getModeById(id, user_id);
        if(mode1 != null) {
            mode1.setMode(mode);
            return modeRepo.save(mode1);
        }
        else return null;
    }

    public void deleteMode(int id, int userId){
        Mode mode = getModeById(id, userId);
        if(mode != null)
            modeRepo.delete(mode);
    }
}
