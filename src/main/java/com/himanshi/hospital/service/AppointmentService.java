package com.himanshi.hospital.service;

import com.himanshi.hospital.dto.AppointmentResponseDto;
import com.himanshi.hospital.entity.model.Appointment;
import com.himanshi.hospital.entity.model.Doctor;
import com.himanshi.hospital.entity.model.Patient;
import com.himanshi.hospital.repository.AppointmentRepository;
import com.himanshi.hospital.repository.DoctorRepository;
import com.himanshi.hospital.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Appointment setAppointments(Appointment appointment, Long doctorId , Long patientId){

        Doctor doctor=doctorRepository.findById(doctorId).orElseThrow(()->new EntityNotFoundException("Doctor not found with id: "+doctorId));
        Patient patient=patientRepository.findById(patientId).orElseThrow(()-> new EntityNotFoundException("Patient not found with id: "+patientId));

        if(appointment.getId()!=null) throw new IllegalArgumentException("Appointments should not have id");
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);
        return appointmentRepository.save(appointment);


    }
    @Transactional
    public Appointment reAssignAppointmentToNewDoctor(Long appointmentId, Long doctorId){
        Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor=doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);

        doctor.getAppointments().add(appointment);
        return appointment;
    }

    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId){
        if (!doctorRepository.existsById(doctorId)) {
            throw new EntityNotFoundException("Doctor not found with id: " + doctorId);
        }

        List<Appointment> appointmentsOfDoctor=appointmentRepository.findByDoctorId(doctorId);
        return appointmentsOfDoctor.stream()
                .map(appointment -> {
                    AppointmentResponseDto dto = new AppointmentResponseDto();
                    dto.setAppointmentId(appointment.getId());
                    dto.setAppointmentTime(appointment.getAppointmentTime());
                    dto.setReason(appointment.getReason());
                    dto.setPatientName(
                            appointment.getPatient().getName()
                    );
                    return dto;
                })
                .toList();
    }


    }


