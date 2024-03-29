package com.example.dataxm.repository;

import com.example.dataxm.entity.ExportEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ExportRepository extends JpaRepository<ExportEntity, String> {
    @Query("SELECT ex.description as description, YEAR(ex.shippingDate) as year, COUNT(DISTINCT ex.ubigeo) as departure, " +
            "COUNT(DISTINCT ex.company) as companies, COUNT(DISTINCT ex.country) as markets," +
            "SUM(ex.netWeight) as netWeight, SUM(ex.fobValue) as valueFOB "+
            "FROM ExportEntity ex "+
            " WHERE ex.description = :productName AND YEAR(ex.shippingDate) = :year "+
            " GROUP BY ex.description, YEAR(ex.shippingDate)")
    Optional<Tuple> findByProductsIdAndYear(String productName, int year);
}
