package com.alianrest.alianrest.repo;

import com.alianrest.alianrest.entity.Alian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlianRepo extends JpaRepository<Alian, Integer> {
    List<Alian> findByTech(String tech);

    List<Alian> findByAidIsGreaterThan(int i);

    @Query("from Alian a where a.tech=?1 order by a.tech")
    List<Alian> findByTechSorted(String Tech);
}
