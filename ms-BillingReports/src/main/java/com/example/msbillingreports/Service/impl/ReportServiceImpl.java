package com.example.msbillingreports.Service.impl;

import com.example.msbillingreports.Entity.Report;
import com.example.msbillingreports.Repository.ReportRepository;
import com.example.msbillingreports.Service.ReportService; // Asegúrate de importar la interfaz
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {  // Implementa la interfaz ReportService

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<Report> list() {
        return reportRepository.findAll();
    }

    @Override
    public Optional<Report> findById(Integer id) {
        return reportRepository.findById(id);
    }

    @Override
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Report update(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public void delete(Integer id) {
        reportRepository.deleteById(id);
    }
}
