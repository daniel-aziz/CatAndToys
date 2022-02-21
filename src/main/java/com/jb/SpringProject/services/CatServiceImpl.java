package com.jb.SpringProject.services;

import com.jb.SpringProject.Exceptions.CatException;
import com.jb.SpringProject.Repositories.CatRepository;
import com.jb.SpringProject.beans.Cat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;

    @Override
    public void addCat(Cat cat) {
        catRepository.save(cat);
    }

    @Override
    public void updateCat(Cat cat) throws CatException {
        if (catRepository.existsById(cat.getCatId())) {
            catRepository.save(cat);
        } else {
            throw new CatException("Cat not Exists in the System");
        }
    }

    @Override
    public void deleteCatById(int catId) throws CatException {
        if (catRepository.existsById(catId)) {
            catRepository.deleteById(catId);
        } else {
            throw new CatException("Cat not Exists in the System");
        }

    }

    @Override
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    @Override
    public Cat getOneCat(int catId) throws CatException {
        if (catRepository.existsById(catId)) {
            return catRepository.getOne(catId);
        } else {
            throw new CatException("Cat not Exists in the System");
        }
    }

    @Override
    public List<Cat> getAllCatsByNameAndWeight(String name, float weight) throws CatException {
        if (!(weight <= 0)) {
            return catRepository.findAllByNameAndWeight(name, weight);
        } else {
            throw new CatException("Cannot find by weight below 0");
        }
    }

    @Override
    public float getAvgWeight() {
        List<Cat> cats = catRepository.findAll();
        float total = 0;
        for (Cat item : cats) {
            total = total + item.getWeight();
        }
        return (float)(total / cats.size());
    }


}
