package com.jb.SpringProject.services;

import com.jb.SpringProject.Exceptions.CatException;
import com.jb.SpringProject.Repositories.CatRepository;
import com.jb.SpringProject.beans.Cat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatService {

    void addCat(Cat cat);
    void updateCat(Cat cat) throws CatException;
    void deleteCatById(int catId) throws CatException;
    List<Cat> getAllCats();
    Cat getOneCat(int catId) throws CatException;
    List<Cat> getAllCatsByNameAndWeight(String name, float weight) throws CatException;
    float getAvgWeight();
}
