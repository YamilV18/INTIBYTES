package com.example.msbillingreports.Controller;

import com.example.msbillingreports.Entity.Report;
import com.example.msbillingreports.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<List<Report>> getAll() {
        return ResponseEntity.ok(reportService.list());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Report>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(reportService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Report> create(@RequestBody Report report) {
        return ResponseEntity.ok(reportService.save(report));
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
