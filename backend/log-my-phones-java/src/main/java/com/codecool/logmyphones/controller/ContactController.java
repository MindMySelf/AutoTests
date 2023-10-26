package com.codecool.logmyphones.controller;

import com.codecool.logmyphones.model.DTO.ContactDTO;
import com.codecool.logmyphones.model.DTO.NewContactDTO;
import com.codecool.logmyphones.service.contactservice.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<Set<ContactDTO>> getAllContactsByCompany(@RequestHeader("Authorization") String token) {
        return contactService.getAllContacts(token);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById( @PathVariable Long id) {
        return contactService.getContactById(id);
    }

    @PostMapping
    public ResponseEntity<NewContactDTO> addNewContact(@RequestBody NewContactDTO contact) {
        return contactService.addNewContact(contact);
    }

    // TODO ne hasznaljunk patchet (PUT)
    @PatchMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContactById(@PathVariable Long id, @RequestBody ContactDTO contactUpdate) {
        return contactService.updateContact(id, contactUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContactById(@PathVariable Long id) {
       contactService.deleteContact(id);
    }
}
