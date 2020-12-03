package com.praveen.patient.controller;


import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuthException;
import com.praveen.patient.model.Patient;
import com.praveen.patient.model.UserRecordDTO;
import com.praveen.patient.service.AuthService;
import com.praveen.patient.service.PatientService;

@RestController
@RequestMapping("/api/v1_0")
public class PatientController {
	
    private PatientService patientService;
    private AuthService authService;    
    public PatientController(PatientService patientService, AuthService authService) {
    	this.patientService=patientService;
    	this.authService=authService;    	
    }

    @GetMapping("/getSinglePatient")
    public Patient getPatient(@RequestParam String name) throws InterruptedException, ExecutionException {
        return patientService.getSinglePatientDetails(name);
    }

    @GetMapping("/getSinglePatient/name")
    public List<Patient> getPatientWhereNameEquals(@RequestParam String name) throws InterruptedException, ExecutionException {
        return patientService.getPatientsWhereNameEquals(name);
    }

    @GetMapping("/getSinglePatient/age")
    public List<Patient> getPatient(@RequestParam int age) throws InterruptedException, ExecutionException {
        return patientService.getPatientWhereAgeEquals(age);
    }

    @GetMapping("/patients")
    public List<Patient> patients() throws ExecutionException, InterruptedException {
        return patientService.geAllPatients();
    }

    @PostMapping("/createPatient")
    public String createPatient(@RequestBody Patient patient ) throws InterruptedException, ExecutionException {
        return patientService.createPatient(patient);
    }

    @PutMapping("/updatePatient")
    public String updatePatient(@RequestBody Patient patient  ) throws InterruptedException, ExecutionException {
        return patientService.updatePatientDetails(patient);
    }

    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestParam String name){
        return patientService.deletePatient(name);
    }

    @PostMapping("/createUser")
    public UserRecordDTO createUser(@RequestBody UserRecordDTO userRecordDTO) throws FirebaseAuthException {
        return authService.createUser(userRecordDTO);
    }

}
