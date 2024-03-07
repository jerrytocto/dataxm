package com.example.dataxm.repository;

import com.example.dataxm.dto.importdto.ImportHomeDTO;
import com.example.dataxm.dto.importdto.ImportHomeDTOTwo;
import com.example.dataxm.entity.ImportEntity;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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


    @Query("SELECT imp.id as id, imp.partida as departure, imp.description as description, SUM(imp.fobValue) as fobValue, " +
            "SUM(imp.netWeight) as netWeight, SUM(imp.securityValue) as securityValue, SUM(imp.fleteValue) as fleteValue, " +
            "p.sector as sector "+
            "FROM ImportEntity imp " +
            "INNER JOIN ProductsEntity p ON p.item = imp.partida "+
            "WHERE imp.description like CONCAT('%', :descrip, '%') AND YEAR(imp.date)= :year "+
            "GROUP BY imp.partida"
    )
    List<Tuple> findImportWhitProducts(@Param("descrip") String descrip, @Param("year") int year);


    @Query(value = "SELECT imp.id as id, imp.PART_NANDI as departure, imp.desc_comer as description, SUM(imp.FOB_DOLPOL) as fobValue, \n" +
            "            SUM(imp.PESO_NETO) as netWeight, SUM(imp.SEG_DOLAR) as securityValue, SUM(imp.FLE_DOLAR) as fleteValue\n" +
            "            FROM importa imp \n" +
            "            WHERE imp.part_nandi LIKE CONCAT('%', :parti, '%') AND YEAR(imp.FECH_INGSI)= :year\n" +
            "            GROUP BY imp.PART_NANDI; ",

        nativeQuery = true)
    List<Tuple> findImportWhitPartida(@Param("parti") String parti, @Param("year") int year);
}
