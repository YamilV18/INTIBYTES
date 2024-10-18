package com.example.msbillingreports.Repository;

import com.example.msbillingreports.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {

}
