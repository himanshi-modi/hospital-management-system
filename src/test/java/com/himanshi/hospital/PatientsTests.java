package com.himanshi.hospital;

import com.himanshi.hospital.service.PatientService;
import com.himanshi.hospital.entity.model.Patient;
import com.himanshi.hospital.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PatientsTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository(){
        List<Patient> patientList=patientRepository.findAllPatientWithAppointment();
        System.out.println(patientList);
    }
    @Test
    public void TransactionalMethod(){
//        Patient patient=patientService.getPatient(1L);
//        System.out.println(patient);

//        Patient patient=patientRepository.findByName("Priya Mehta");
//        List<Patient> patientList=patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);
//        List<Object[]> bloodGroupList=patientRepository.countEachBloodGroupType();
//        for (Object[] objects:bloodGroupList){
//            System.out.println(objects[0]+" "+objects[1]);
//        }
//        List<BloodGroupCountEntity> bloodGroupTypeList=patientRepository.countEachBloodGroupType();
//
//        for (BloodGroupCountEntity bloodGroupCountEntity: bloodGroupTypeList){
//            System.out.println(bloodGroupCountEntity);
//        }
//        int rowsAffected=patientRepository.updateNameWithId("Nehal",2L);
//        System.out.println(rowsAffected);

        Page<Patient> patientList=patientRepository.getAllPatients(PageRequest.of(0,2, Sort.by("patient_name")));
        for (Patient patient:patientList){
            System.out.println(patient);
        }
    }
}
