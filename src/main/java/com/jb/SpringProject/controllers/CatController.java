package com.jb.SpringProject.controllers;

import com.jb.SpringProject.Exceptions.CatException;
import com.jb.SpringProject.Security.JWTSecurity;
import com.jb.SpringProject.beans.Cat;
import com.jb.SpringProject.beans.Users.UserDetails;
import com.jb.SpringProject.services.CatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@RequestMapping("littleKitty")
@RequiredArgsConstructor
public class CatController {

    private final CatServiceImpl catServiceImpl;
    private final JWTSecurity myUtil;

    @GetMapping("login/{userEmail}")
    public ResponseEntity<?> getLoginToken(@PathVariable String userEmail) {
        UserDetails admin = new UserDetails(userEmail, "12345", "Admin");
        return new ResponseEntity<>(myUtil.generateToken(admin), HttpStatus.ACCEPTED);
    }

    @PostMapping("cat/add")
    public ResponseEntity<?> addCat(@RequestBody Cat cat) throws CatException {
        catServiceImpl.addCat(cat);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("cat/update")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateCat(@RequestBody Cat cat) throws CatException {
        try {
            catServiceImpl.updateCat(cat);
        } catch (CatException e) {
            throw new CatException(e.getMessage());
        }
    }

    @DeleteMapping("cat/delete/{catId}")
    public ResponseEntity<?> deleteCat(@PathVariable int catId) throws CatException {

        try {
            catServiceImpl.deleteCatById(catId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (CatException e) {
            throw new CatException(e.getMessage());
        }

    }

    @GetMapping("cat/all")
    public ResponseEntity<?> getAllCats() {
        return new ResponseEntity<>(catServiceImpl.getAllCats(), HttpStatus.OK);
    }


    @GetMapping("cat/{catId}")
    public ResponseEntity<?> getById(@PathVariable int catId) throws CatException {
        return new ResponseEntity<>(catServiceImpl.getOneCat(catId), HttpStatus.OK);
    }


    @GetMapping("cat/All_by_name_or_weight")
    public ResponseEntity<?> getAllCatsByNameAndWeight(String name, float weight) throws CatException {
        try {
            return new ResponseEntity<>(catServiceImpl.getAllCatsByNameAndWeight(name, weight), HttpStatus.OK);
        } catch (CatException e) {
            throw new CatException(e.getMessage());
        }
    }

    @GetMapping("cat/hello")
    private ResponseEntity<?> sayHello(@RequestParam(defaultValue = "no name") String name) {
        String myString = "Hello " + name + " !";
        return new ResponseEntity<>(myString, HttpStatus.OK);
    }

    @GetMapping("cat/avgw")
    private ResponseEntity<?> getAvgWeight() {
        return new ResponseEntity<>(catServiceImpl.getAvgWeight(), HttpStatus.OK);
    }


}
