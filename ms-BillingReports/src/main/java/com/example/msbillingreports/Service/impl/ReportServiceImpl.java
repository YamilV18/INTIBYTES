package com.example.msbillingreports.Service.impl;

import com.example.msbillingreports.Dto.UserDto;
import com.example.msbillingreports.Entity.Report;
import com.example.msbillingreports.Feign.UserSubscriptionFeign;
import com.example.msbillingreports.Repository.ReportRepository;
import com.example.msbillingreports.Service.ReportService; // Asegúrate de importar la interfaz
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {  // Implementa la interfaz ReportService

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserSubscriptionFeign userSubFeign;

    @Override
    public List<Report> list() {
        List<Report> reports = reportRepository.findAll();

        // Para cada orden en la lista, obtenemos los detalles del cliente y productos
        return reports.stream().map(report -> {
            // Obtener cliente
            ResponseEntity<UserDto> userResponse = userSubFeign.listUserById(report.getUserId());
            if (userResponse.getStatusCode().is2xxSuccessful()) {
                UserDto userDto = userResponse.getBody();  // Sin Optional
                if (userDto != null) {
                    report.setUser(userDto);
                    System.out.println("Cliente obtenido: " + userDto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }

            return report;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Report> findById(Integer id) {
        Optional<Report> reportOpt = reportRepository.findById(id);
        if (reportOpt.isPresent()) {
            Report report = reportOpt.get();

            // Obtener cliente (sin Optional)
            ResponseEntity<UserDto> userResponse = userSubFeign.listUserById(report.getUserId());
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
        // Guardar el objeto Report en el repositorio
        Report savedReport = reportRepository.save(report);

        // Obtener cliente (sin Optional) si hay un UserId
        if (savedReport.getUserId() != null) {
            ResponseEntity<UserDto> UserResponse = userSubFeign.listUserById(savedReport.getUserId());
            if (UserResponse.getStatusCode().is2xxSuccessful()) {
                UserDto userDto = UserResponse.getBody();  // Sin Optional
                if (userDto != null) {
                    savedReport.setUser(userDto);
                    System.out.println("Cliente obtenido: " + userDto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }
        }

        return savedReport;
    }

    @Override
    public Report update(Report report) {
        // Guardar el objeto Report en el repositorio
        Report savedReport = reportRepository.save(report);

        // Obtener cliente (sin Optional) si hay un UserId
        if (savedReport.getUserId() != null) {
            ResponseEntity<UserDto> UserResponse = userSubFeign.listUserById(savedReport.getUserId());
            if (UserResponse.getStatusCode().is2xxSuccessful()) {
                UserDto userDto = UserResponse.getBody();  // Sin Optional
                if (userDto != null) {
                    savedReport.setUser(userDto);
                    System.out.println("Cliente obtenido: " + userDto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }
        }

        return savedReport;
    }

    @Override
    public void delete(Integer id) {
        reportRepository.deleteById(id);
    }
}
