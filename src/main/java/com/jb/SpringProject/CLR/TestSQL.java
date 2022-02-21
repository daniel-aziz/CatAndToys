package com.jb.SpringProject.CLR;

import com.jb.SpringProject.Repositories.CatRepository;
import com.jb.SpringProject.Repositories.ToyRepository;
import com.jb.SpringProject.beans.Cat;
import com.jb.SpringProject.beans.Toy;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(1)
@AllArgsConstructor
public class TestSQL implements CommandLineRunner {
    private CatRepository catRepository;
    private ToyRepository toyRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("TestSQL");
        Toy toy1 = Toy.builder().name("chooie").build();
        Toy toy2 = Toy.builder().name("tennis ball").build();
        Toy toy3 = Toy.builder().name("big hat").build();
        Toy toy4 = Toy.builder().name("flairs").build();


        Cat cat = Cat.builder().name("mitzi").weight(0.5F).toy(toy1).toy(toy2).build();
        Cat cat2 = Cat.builder().name("bumper").weight(0.8F).toy(toy3).toy(toy4).build();
        Cat cat3 = Cat.builder().name("yoram").weight(0.8F).build();
        catRepository.save(cat);
        catRepository.save(cat2);
        catRepository.deleteById(1);
        cat2.setName("bumperos");
        catRepository.save(cat2);
        catRepository.save(cat3);

//        System.out.println(catRepository.getOne(2));
//        System.out.println(catRepository.findAll());



    }
}
