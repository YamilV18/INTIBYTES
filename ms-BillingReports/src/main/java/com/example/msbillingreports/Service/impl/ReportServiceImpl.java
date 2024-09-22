package com.example.msbillingreports.Service.impl;

import com.example.msbillingreports.Dto.UserDto;
import com.example.msbillingreports.Entity.Billing;
import com.example.msbillingreports.Entity.Report;
import com.example.msbillingreports.Feign.UserSubscriptionFeign;
import com.example.msbillingreports.Repository.ReportRepository;
import com.example.msbillingreports.Service.ReportService; // Asegúrate de importar la interfaz
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {  // Implementa la interfaz ReportService

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserSubscriptionFeign userSubscriptionFeign;

    @Override
    public List<Report> list() {
        return reportRepository.findAll();
    }

    @Override
    public Optional<Report> findById(Integer id) {
        Optional<Report> reportOpt = reportRepository.findById(id);
        if (reportOpt.isPresent()) {
            Report report = reportOpt.get();

            // Obtener cliente (sin Optional)
            ResponseEntity<UserDto> userResponse = userSubscriptionFeign.listUserById(report.getUserId());
            if (userResponse.getStatusCode().is2xxSuccessful()) {
                UserDto userdto = userResponse.getBody();  // Sin Optional
                if (userdto != null) {
                    report.setUser(userdto);
                    System.out.println("Cliente obtenido: " + userdto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }

            //report.setUser(user);
            return Optional.of(report);
        }
        return Optional.empty();
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
