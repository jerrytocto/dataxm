package com.example.dataxm.repository;

import com.example.dataxm.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImportCountryRepository extends JpaRepository<Country, String> {

/*
    @Query("findByNombrePaisLike(:country)")
    Optional<List<Country>> findByCountryNameLike(@Param("country") String country);
*/
}
