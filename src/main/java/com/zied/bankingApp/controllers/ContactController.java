package com.zied.bankingApp.controllers;

import com.zied.bankingApp.dto.ContactDto;
import com.zied.bankingApp.services.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@AllArgsConstructor
@Tag(name = "contact")
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(ContactDto contactDto){
        return ResponseEntity.ok(contactService.save(contactDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactDto>> findAll(){
        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("/{contact-id}")
    public ResponseEntity<ContactDto> findById(@PathVariable("contact-id") Integer contactId){
        return ResponseEntity.ok(contactService.findById(contactId));
    }

    @GetMapping("/users/user-id")
    public ResponseEntity<List<ContactDto>> findByUserId(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(contactService.findAllByUserId(userId));
    }

    @DeleteMapping("/{contact-id}")
    public ResponseEntity<Void> delete(@PathVariable("contact-id") Integer contactId){
        contactService.delete(contactId);
        return ResponseEntity.accepted().build();
    }

}
