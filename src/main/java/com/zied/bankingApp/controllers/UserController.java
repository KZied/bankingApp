package com.zied.bankingApp.controllers;

import com.zied.bankingApp.dto.UserDto;
import com.zied.bankingApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));

    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> findById(@PathVariable("user-id") Integer id) {
        return ResponseEntity.ok(userService.findById(id));

    }

    @PatchMapping("validate/{user-id}")
    public ResponseEntity<Integer> validateAccount(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(userService.validateAccount(userId));
    }

    @PatchMapping("invalidate/{user-id}")
    public ResponseEntity<Integer> invalidateAccount(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(userService.invalidateAccount(userId));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete (@PathVariable("user-id") Integer userId){
        userService.delete(userId);
        return ResponseEntity.accepted().build();
    }

//    @GetMapping("/find")
//    public void search (
//            @RequestParam String firstName,
//            @RequestParam String lastName,
//            @RequestParam int age){
//
//    }
//
//    @GetMapping("/find/{uni_criteria}")
//    public void searchUnique(@PathVariable("uni_criteria") String criteria){
//    }
}
