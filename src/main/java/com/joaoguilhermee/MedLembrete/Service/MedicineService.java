package com.joaoguilhermee.MedLembrete.Service;

import com.joaoguilhermee.MedLembrete.Exception.ResourceNotFoundException;
import com.joaoguilhermee.MedLembrete.Model.Medicine;
import com.joaoguilhermee.MedLembrete.Model.User;
import com.joaoguilhermee.MedLembrete.Repository.MedicineRepository;
import com.joaoguilhermee.MedLembrete.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private UserRepository userRepository;

    public Medicine addMedicine(Long userId, Medicine medicine) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));

        medicine.setUser(user);
        return medicineRepository.save(medicine);
    }

    public List<Medicine> listMedicines(Long userId) {
        return medicineRepository.findByUserId(userId);
    }

    public Medicine updateMedicine(Long userId, Long medicineId, Medicine newData) {
        Medicine medicine = medicineRepository.findByIdAndUserId(medicineId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento", medicineId));

        medicine.setNome(newData.getNome());
        medicine.setHorario(newData.getHorario());
        medicine.setDiaInicio(newData.getDiaInicio());
        medicine.setDiaFinal(newData.getDiaFinal());
        medicine.setDoses(newData.getDoses());
        medicine.setDosesPorDia(newData.getDosesPorDia());

        return medicineRepository.save(medicine);
    }

    public void deleteMedicine(Long userId, Long medicineId) {
        Medicine medicine = medicineRepository.findByIdAndUserId(medicineId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento", medicineId));

        medicineRepository.delete(medicine);
    }

    public Medicine markDoseTaken(Long userId, Long medicineId) {
        Medicine medicine = medicineRepository.findByIdAndUserId(medicineId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento", medicineId));

        if (medicine.getDosesTomadas() < medicine.getDosesPorDia()) {
            medicine.setDosesTomadas(medicine.getDosesTomadas() + 1);
        } else {
            throw new RuntimeException("Todas as doses do dia já foram tomadas!");
        }

        return medicineRepository.save(medicine);
    }
}