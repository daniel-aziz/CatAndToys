package com.jb.SpringProject.Repositories;

import com.jb.SpringProject.beans.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
    List<Cat> findAllByNameAndWeight(String name, float weight);

    List<Cat> findAllByName(String name);

    List<Cat> findAllByWeight(float weight);

    List<Cat> findAllByNameOrWeight(String name, float weight);

    List<Cat> findByOrderByWeightDesc();

    List<Cat> findByOrderByWeightAsc();

    List<Cat> findByNameStartingWith(String prefix);

    boolean existsByNameAndWeight(String name, float weight);

}
