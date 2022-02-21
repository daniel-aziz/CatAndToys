package com.jb.SpringProject.Repositories;

import com.jb.SpringProject.beans.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepository extends JpaRepository<Toy, Integer> {
}
