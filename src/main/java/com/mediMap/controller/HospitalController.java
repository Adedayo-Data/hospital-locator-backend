package com.mediMap.controller;

import com.mediMap.dto.HospitalDto;
import com.mediMap.model.Hospital;
import com.mediMap.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hospital")
@AllArgsConstructor
public class HospitalController {

    HospitalRepository hospitalRepo;

    // GET all hospitals
    @GetMapping
    public ResponseEntity<List<Hospital>> getAllHospitals(){
        return ResponseEntity.ok(hospitalRepo.findAll());
    }

    // GET hospitals by Id
    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable Long id){

        Optional<Hospital> dbHospital = hospitalRepo.findById(id);

        if(dbHospital.isEmpty()){
            return null;
        }
        Hospital hospital = dbHospital.get();
        return ResponseEntity.ok(hospital);
    }

    // CREATE hospital
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Hospital> createHospital(@RequestBody HospitalDto hospitalDto){
        Hospital hospital = new Hospital();

        hospital.setHospitalName(hospitalDto.getHospitalName());
        hospital.setDescription(hospitalDto.getDescription());
        hospital.setEmail(hospitalDto.getEmail());
        hospital.setLocation(hospitalDto.getLocation());
        hospital.setImage(hospitalDto.getImage());
        hospital.setSpecialties(hospitalDto.getSpecialties());
        hospital.setEmergency(hospitalDto.getEmergency());
        hospital.setOpeningHours(hospitalDto.getOpeningHours());
        hospital.setWebsite(hospitalDto.getWebsite());
        hospital.setLat(hospitalDto.getLat());
        hospital.setLng(hospitalDto.getLng());
        hospital.setRatings(1.0);

        // save the hospital
        hospital = hospitalRepo.save(hospital);

        return ResponseEntity.ok(hospital);
    }

    // UPDATE hospital info
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Hospital> updateHospital(@PathVariable Long id,
                                                   @RequestBody HospitalDto hospitalDto){
        // get the hospital
        Optional<Hospital> hospital = hospitalRepo.findById(id);

//        if(optionalHospital.isEmpty()){
//            return null;
//        }

        hospital.ifPresent(realHospital -> realHospital.setHospitalName(hospitalDto.getHospitalName()));

        hospital.ifPresent(realHospital -> realHospital.setEmail(hospitalDto.getEmail()));

        hospital.ifPresent(realHospital -> realHospital.setLocation(hospitalDto.getLocation()));

        hospital.ifPresent(realHospital -> realHospital.setDescription(hospitalDto.getDescription()));

        hospital.ifPresent(realHospital -> realHospital.setImage(hospitalDto.getImage()));

        hospital.ifPresent(realHospital -> realHospital.setSpecialties(hospitalDto.getSpecialties()));

        hospital.ifPresent(realHospital -> realHospital.setEmergency(hospitalDto.getEmergency()));

        hospital.ifPresent(realHospital -> realHospital.setOpeningHours(hospitalDto.getOpeningHours()));

        hospital.ifPresent(realHospital -> realHospital.setWebsite(hospitalDto.getWebsite()));

        hospital.ifPresent(realHospital -> realHospital.setLat(hospitalDto.getLat()));

        hospital.ifPresent(realHospital -> realHospital.setLng(hospitalDto.getLng()));

        Hospital savedHostpital = hospital.map(value -> hospitalRepo.save(value)).orElseThrow(() -> new RuntimeException("An error occured while updating hospital"));

        return ResponseEntity.ok(savedHostpital);

    }

    // DELETE a hospital
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteHospital(@PathVariable Long id){
        hospitalRepo.deleteById(id);
        return ResponseEntity.ok("Hospital deleted successfully!");
    }
}
