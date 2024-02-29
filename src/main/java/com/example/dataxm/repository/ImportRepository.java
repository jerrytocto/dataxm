package com.example.dataxm.repository;

import com.example.dataxm.dto.importdto.ImportHomeDTO;
import com.example.dataxm.dto.importdto.ImportHomeDTOTwo;
import com.example.dataxm.entity.ImportEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public interface ImportRepository extends JpaRepository<ImportEntity, String> {

    @Query("SELECT imp.partida as partida, imp.customsCode as customsCode, imp.fobValue as fobValue, " +
            "imp.netWeight as netWeight, imp.securityValue as securityValue, imp.fleteValue as fleteValue, " +
            "imp.importCompany as importCompany, imp.originCountry as originCountry " +
            "FROM ImportEntity imp " +
            "WHERE YEAR(imp.date) = :year")
    List<Tuple> findImportHomeDataByYear(Integer year);


    @Query("SELECT COUNT(DISTINCT imp.partida) as departure, COUNT(DISTINCT imp.customsCode) as customs, " +
            "COUNT(DISTINCT imp.importCompany) as companies, COUNT(DISTINCT imp.originCountry) as markets," +
            "SUM(imp.netWeight) as netWeight, SUM(imp.securityValue) as securityValue, SUM(imp.fleteValue) as fleteValue, " +
            "SUM(imp.fobValue) as valueFOB "+
            "FROM ImportEntity imp "+
            " WHERE YEAR(imp.date) = :year"
    )
    Tuple findImportHomeDataByYeartwo(Integer year);
}
