package com.example.dataxm.repository;

import com.example.dataxm.entity.Company;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImportCompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByRucContaining(String ruc);

    Company findByBusinessNameContaining(String businessName);


    @Query(value = "SELECT imp.dnombre as company, SUM(imp.FOB_DOLPOL) as fobValue, \n" +
            "            SUM(imp.PESO_NETO) as netWeight, SUM(imp.SEG_DOLAR) as securityValue, SUM(imp.FLE_DOLAR) as fleteValue\n" +
            "            FROM importa imp \n" +
            "            WHERE imp.DNOMBRE like CONCAT('%', :compan, '%') AND YEAR(imp.FECH_INGSI)= :year\n" +
            "            GROUP BY imp.dnombre ",
        nativeQuery = true
    )
    //Query para cuando se envía como parámetro la empresa y el año
    List<Tuple> findImportWhitCompany(@Param("compan") String compan, @Param("year") int year);


    @Query(value = "SELECT imp.dnombre as company, SUM(imp.FOB_DOLPOL) as fobValue, \n" +
            "                        SUM(imp.PESO_NETO) as netWeight, SUM(imp.SEG_DOLAR) as securityValue, SUM(imp.FLE_DOLAR) as fleteValue\n" +
            "                        FROM importa imp \n" +
            "                        WHERE YEAR(imp.FECH_INGSI)= :year\n" +
            "                        GROUP BY imp.dnombre \n" +
            "                        ORDER BY SUM(imp.FOB_DOLPOL) DESC",
            nativeQuery = true
    )
    List<Tuple> findImportsByCompanyOnlyYear(@Param("year") int year);
}
