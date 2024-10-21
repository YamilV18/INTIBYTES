package com.example.msbillingreports.Controller;

import com.example.msbillingreports.Dto.ErrorResponseDto;
import com.example.msbillingreports.Dto.SubscriptionDto;
import com.example.msbillingreports.Dto.UserDto;
import com.example.msbillingreports.Entity.Billing;
import com.example.msbillingreports.Entity.Report;
import com.example.msbillingreports.Feign.UserSubscriptionFeign;
import com.example.msbillingreports.Service.ReportService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @Autowired
    private UserSubscriptionFeign userSubscriptionFeign;

    @GetMapping
    public ResponseEntity<List<Report>> getAll() {
        return ResponseEntity.ok(reportService.list());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Report>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(reportService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Report report) {
        try {
            // Llamada al servicio Feign para obtener el usuario
            UserDto userDto = userSubscriptionFeign.listUserById(report.getUserId()).getBody();

            if (userDto == null || userDto.getId() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDto("Error: Usuario no encontrado."));
            }

            // Si el usuario es válido, se guarda el reporte
            Report newReport = reportService.save(report);
            return ResponseEntity.ok(newReport);

        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto("Error: No se encontró el usuario con el ID proporcionado."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDto("Error al procesar la solicitud, revise la existencia del usuario."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Report> update(@PathVariable Integer id,
                                         @RequestBody Report report) {
            report.setId(id);
        return ResponseEntity.ok(reportService.save(report));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Report>> delete(@PathVariable Integer id) {
        reportService.delete(id);
        return ResponseEntity.ok(reportService.list());
    }


}
}