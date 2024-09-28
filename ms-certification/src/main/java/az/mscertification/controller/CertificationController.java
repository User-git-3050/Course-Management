package az.mscertification.controller;

import az.mscertification.dao.CertificationResponse;
import az.mscertification.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/certifications/")
public class CertificationController {
    private final CertificationService certificationService;

    @Autowired
    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @GetMapping
    public ResponseEntity<List<CertificationResponse>> getAllCertifications(){
        return certificationService.getAllCertifications();
    }

    @GetMapping("{certificationId}")
    public ResponseEntity<CertificationResponse> getCertificationById(@PathVariable Long certificationId){
        return certificationService.getCertificationById(certificationId);
    }

    @PostMapping("/generate")
    public ResponseEntity<List<CertificationResponse>> generateCertification(){
         return ResponseEntity.ok(certificationService.generateCertification());
    }

    @GetMapping("{certificationId}/download")
    public ResponseEntity<InputStreamResource> downloadCertification(@PathVariable Long certificationId){
        return certificationService.downloadCertificate(certificationId);
    }



}
