package com.example.msbillingreports.Service;

import com.example.msbillingreports.Entity.Report;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    List<Report> list();
    Optional<Report> findById(Integer id);
    Report save(Report report);
    Report update(Report report);
    void delete(Integer id);

}
