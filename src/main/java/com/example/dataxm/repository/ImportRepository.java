package com.example.dataxm.repository;

import com.example.dataxm.entity.ImportEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImportRepository extends JpaRepository<ImportEntity, String> {

    @Query("SELECT imp.partida as partida, imp.customsCode as customsCode, imp.fobValue as fobValue, " +
            "imp.netWeight as netWeight, imp.securityValue as securityValue, imp.fleteValue as fleteValue, " +
            "imp.importCompany as importCompany, imp.originCountry as originCountry " +
            "FROM ImportEntity imp " +
            "WHERE YEAR(imp.date) = :year"
    )
    List<Tuple> findImportHomeDataByYear(Integer year);


    @Query("SELECT COUNT(DISTINCT imp.partida) as departure, COUNT(DISTINCT imp.customsCode) as customs, " +
            "COUNT(DISTINCT imp.importCompany) as companies, COUNT(DISTINCT imp.originCountry) as markets," +
            "SUM(imp.netWeight) as netWeight, SUM(imp.securityValue) as securityValue, SUM(imp.fleteValue) as fleteValue, " +
            "SUM(imp.fobValue) as valueFOB "+
            "FROM ImportEntity imp "+
            " WHERE YEAR(imp.date) = :year"
    )
    Tuple findImportHomeDataByYeartwo(Integer year);


    @Query( value = "SELECT imp.PART_NANDI as departure, imp.DESC_COMER as description, SUM(imp.FOB_DOLPOL) as fobValue, \n" +
            "            SUM(imp.PESO_NETO) as netWeight, SUM(imp.SEG_DOLAR) as securityValue, SUM(imp.FLE_DOLAR) as fleteValue, \n" +
            "            p.sector as sector \n" +
            "            FROM importa imp \n" +
            "            INNER JOIN productos p ON p.partida_nandi = imp.PART_NANDI \n" +
            "            WHERE imp.DESC_COMER like CONCAT('%', :descrip, '%') AND YEAR(imp.FECH_INGSI)= :year\n" +
            "            GROUP BY imp.PART_NANDI, imp.DESC_COMER, p.sector \n" +
            "            ORDER BY SUM(imp.FOB_DOLPOL) DESC",
            nativeQuery = true
    )
    List<Tuple> findImportProductsFirstLevel(@Param("descrip") String descrip, @Param("year") int year);


    @Query(value = "SELECT  imp.PART_NANDI as departure, imp.desc_comer as description, SUM(imp.FOB_DOLPOL) as fobValue, \n" +
            "                        SUM(imp.PESO_NETO) as netWeight, SUM(imp.SEG_DOLAR) as securityValue, SUM(imp.FLE_DOLAR) as fleteValue\n" +
            "                        FROM importa imp \n" +
            "                        WHERE imp.part_nandi LIKE CONCAT('%', :parti, '%') AND YEAR(imp.FECH_INGSI)= :year\n" +
            "                        GROUP BY imp.PART_NANDI , imp.desc_comer\n" +
            "                        ORDER BY SUM(imp.FOB_DOLPOL) DESC",

            nativeQuery = true)
    List<Tuple> findImportWhitPartida(@Param("parti") String parti, @Param("year") int year);


    @Query(value = "SELECT p.pais as pais,SUM(imp.FOB_DOLPOL) as fobValue, \n" +
            "                        imp.pais_orige as codCountry, imp.DESC_COMER as productName, \n" +
            "                        SUM(imp.PESO_NETO) as netWeight, SUM(imp.SEG_DOLAR) as securityValue, SUM(imp.FLE_DOLAR) as fleteValue\n" +
            "                        FROM importa imp \n" +
            "                        INNER JOIN paises p on p.idpaises = imp.PAIS_ORIGE\n" +
            "                        WHERE YEAR(imp.FECH_INGSI) = :year AND imp.PAIS_ORIGE= :country\n" +
            "                        GROUP BY imp.DESC_COMER, p.pais, imp.pais_orige, imp.DESC_COMER\n" +
            "                        ORDER BY SUM(imp.FOB_DOLPOL) DESC",
        nativeQuery = true
    )
    Optional<List<Tuple>> findImportWithCountry(@Param("country") String country, @Param("year") int year);

    @Query(value = "SELECT p.pais as pais, YEAR(imp.FECH_INGSI) as years,SUM(imp.FOB_DOLPOL) as fobValue, \n" +
            "                        imp.pais_orige as codCountry, imp.DESC_COMER as productName, \n" +
            "                        SUM(imp.PESO_NETO) as netWeight, SUM(imp.SEG_DOLAR) as securityValue, SUM(imp.FLE_DOLAR) as fleteValue\n" +
            "                        FROM importa imp \n" +
            "                        INNER JOIN paises p on p.idpaises = imp.PAIS_ORIGE\n" +
            "                        WHERE YEAR(imp.FECH_INGSI)= :year \n" +
            "                        GROUP BY imp.DESC_COMER, p.pais, YEAR(imp.FECH_INGSI), imp.pais_orige \n" +
            "                        ORDER BY SUM(imp.FOB_DOLPOL) DESC " ,
            nativeQuery = true
    )
    List<Tuple> findImportWithCountryOnlyYear(@Param("year") int year);


    @Query("SELECT imp.description as description, YEAR(imp.date) as year, COUNT(DISTINCT imp.partida) as departure, " +
            "COUNT(DISTINCT imp.importCompany) as companies, COUNT(DISTINCT imp.originCountry) as markets," +
            "SUM(imp.netWeight) as netWeight, SUM(imp.securityValue) as securityValue, SUM(imp.fleteValue) as fleteValue, " +
            "SUM(imp.fobValue) as valueFOB "+
            "FROM ImportEntity imp "+
            " WHERE imp.description = :productName AND YEAR(imp.date) = :year "+
            " GROUP BY imp.description, YEAR(imp.date)"

    )
    Optional<Tuple> findByProductsIdAndYear(String productName, int year);


    @Query(value = "SELECT imp.description as description, YEAR(imp.date) as year, COUNT(DISTINCT imp.partida) as departure, " +
            "COUNT(DISTINCT imp.importCompany) as companies, COUNT(DISTINCT imp.originCountry) as markets, COUNT(DISTINCT imp.agentAdua) as agentAdua, " +
            "SUM(imp.netWeight) as netWeight, SUM(imp.securityValue) as securityValue, SUM(imp.fleteValue) as fleteValue, " +
            "SUM(imp.fobValue) as valueFOB "+
            "FROM ImportEntity imp "+
            " WHERE imp.partida = :departure AND YEAR(imp.date) = :year "+
            " GROUP BY imp.partida, YEAR(imp.date), imp.description"
    )
    Optional<Tuple> secondLevelDeparture(String departure, int year);


    @Query("SELECT COUNT(DISTINCT imp.partida) as departure, COUNT(DISTINCT imp.customsCode) as customs, " +
            "COUNT(DISTINCT imp.importCompany) as companies," +
            "SUM(imp.netWeight) as netWeight, SUM(imp.securityValue) as securityValue, SUM(imp.fleteValue) as fleteValue, " +
            "SUM(imp.fobValue) as valueFOB "+
            "FROM ImportEntity imp "+
            " WHERE YEAR(imp.date) = :year AND imp.originCountry = :countryName"
    )
    Optional<Tuple> secondLevelCountry(int year, String countryName);
}
