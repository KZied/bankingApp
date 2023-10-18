package com.zied.bankingApp.controllers;

import com.zied.bankingApp.dto.AddressDto;
import com.zied.bankingApp.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@AllArgsConstructor
public class AddressController {

    private AddressService addressService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody AddressDto addressDto){
        return ResponseEntity.ok(addressService.save(addressDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> findAll(){
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{address-id}")
    public ResponseEntity<AddressDto> findById(@PathVariable Integer addressId){
        return ResponseEntity.ok(addressService.findById(addressId));
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void> delete(@PathVariable Integer addressId){
        addressService.delete(addressId);
        return ResponseEntity.accepted().build();
    }
}
