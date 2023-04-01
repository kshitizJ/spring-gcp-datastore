package com.springdatastore.tutorial;

import com.google.cloud.datastore.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<List<Address>> getAllAddresss(@PathVariable Long id) {
        return ResponseEntity.ok(addressRepository.findAddressesByUserId(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Address> createAddress(@PathVariable Long id, @RequestBody Address address) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
        Address newAddress = new Address();
        newAddress.setCity(address.getCity());
        newAddress.setCountry(address.getCountry());
        newAddress.setUser(user);
        List<Address> addresses = new ArrayList<>(user.getAddress());
        Address savedAddress = addressRepository.save(newAddress);
        addresses.add(savedAddress);
        user.setAddress(addresses);
        userRepository.save(user);
        return ResponseEntity.ok(savedAddress);
    }

    @PutMapping("/{addressKey}")
    public ResponseEntity<Address> updateAddress(@PathVariable Key addressKey, @RequestBody Address address) {
        return ResponseEntity.ok(addressRepository.findById(addressKey.getId()).orElseThrow(() -> new ResourceNotFoundException("Address not exist with id: " + addressKey.getId())));
    }

    @DeleteMapping("/{addressKey}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Key addressKey) {
        addressRepository.deleteById(addressKey.getId());
        return ResponseEntity.ok().build();
    }
}
