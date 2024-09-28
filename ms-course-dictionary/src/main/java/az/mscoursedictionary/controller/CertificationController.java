package az.mscoursedictionary.controller;

import az.mscoursedictionary.dao.CertificationResponse;
import az.mscoursedictionary.service.CertificationDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/certification/dictionary")
public class CertificationController {
    private final CertificationDictionaryService certificationDictionaryService;

    @Autowired
    public CertificationController(CertificationDictionaryService certificationDictionaryService) {
        this.certificationDictionaryService = certificationDictionaryService;
    }
    @GetMapping
    public ResponseEntity<List<CertificationResponse>> getAllCertifications(){
        return ResponseEntity.ok(certificationDictionaryService.getAllCertifications());
    }

    @GetMapping("{certificationId}")
    public ResponseEntity<CertificationResponse> getCertificationById(@PathVariable Long certificationId){
        return ResponseEntity.ok(certificationDictionaryService.getCertificationById(certificationId));
    }

    @PostMapping("/generate")
    public ResponseEntity<CertificationResponse> generateCertification(@RequestParam Long courseId, @RequestParam Long enrollmentId, @RequestBody byte[] pdfBytes){
        return ResponseEntity.ok(certificationDictionaryService.createCertificate(courseId,enrollmentId,pdfBytes));
    }


}
