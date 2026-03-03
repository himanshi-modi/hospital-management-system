package com.himanshi.hospital;

import com.himanshi.hospital.repository.PatientRepository;
import com.himanshi.hospital.service.AppointmentService;
import com.himanshi.hospital.service.InsuranceService;
import com.himanshi.hospital.entity.model.Appointment;
import com.himanshi.hospital.entity.model.Insurance;
import com.himanshi.hospital.entity.model.Patient;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    @Transactional
    public void TestInsurance(){
        Patient patient1 = Patient.builder().name("Priya").build();
        Patient patient2 = Patient.builder().name("Rahul").build();
        patientRepository.save(patient1);
        patientRepository.save(patient2);

        Insurance insurance= Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2012,12,3))
                .build();

        Patient updatedPatient = insuranceService.assignInsuranceToPatient(insurance, patient1.getId());
        System.out.println(updatedPatient);

        Patient newPatient = insuranceService.disAssociateInsuranceFromPatient(patient2.getId());
        System.out.println(newPatient);
    }

    @Test

    public void TestAppointment(){
        Appointment appointment=Appointment.builder().appointmentTime(LocalDateTime.of(2023,12,2,14,0)).reason("Cancer").build();
        var newAppointment=appointmentService.setAppointments(appointment,1L,2L);
        System.out.println(newAppointment);
        var reAssignedAppointment=appointmentService.reAssignAppointmentToNewDoctor(appointment.getId(),3L);
        System.out.println(reAssignedAppointment);
    }

}
