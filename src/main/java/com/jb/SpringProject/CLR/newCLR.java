package com.jb.SpringProject.CLR;

import com.jb.SpringProject.beans.Cat;
import com.jb.SpringProject.services.CatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
//@Component
@Order(3)
public class newCLR implements CommandLineRunner {
    @Autowired
    private CatServiceImpl catService;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("newCLR");

        List<Cat> cats = catService.getAllCats();
        System.out.println(cats);




    }
}
