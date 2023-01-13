package ca.mcgill.ecse321.museumsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.museumsystem.dto.EntryPassDto;
import ca.mcgill.ecse321.museumsystem.model.EntryPass;
import ca.mcgill.ecse321.museumsystem.service.EntryPassService;
import ca.mcgill.ecse321.museumsystem.dto.VisitorDto;


/**
 * 
 * @author Anthony Boustany
 * 
 * EntryPass Controller class.
 *
 */

@RestController
public class EntryPassController {

    @Autowired
    EntryPassService entryPassService;

    @GetMapping("/entrypass/{id}")
    public ResponseEntity<EntryPass> getArtworkById(@PathVariable int passId){
        EntryPass entryPass = entryPassService.getEntryPassById(passId);
        return new ResponseEntity<EntryPass>(entryPass, HttpStatus.OK);
    }

    @PostMapping("/entrypass")
    public ResponseEntity<EntryPass> createEntryPass(@RequestBody EntryPass entryPass){
        entryPass = entryPassService.createEntryPass(entryPass);
        return new ResponseEntity<EntryPass>(entryPass, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/entrypass/{id}")
    public ResponseEntity<String> deleteArtworkById(@PathVariable int passId){
        EntryPass entryPass = entryPassService.getEntryPassById(passId);
        entryPassService.deleteEntryPass(entryPass);
        return new ResponseEntity<String>("Entry Pass Deleted Successfully", HttpStatus.OK);
    }

}