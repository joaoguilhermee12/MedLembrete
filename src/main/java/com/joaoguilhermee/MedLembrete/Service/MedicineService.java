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

    public Medicine adicionarMedicamento(Long userId, Medicine medicine) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", userId));

        medicine.setUser(user);
        return medicineRepository.save(medicine);
    }

    public List<Medicine> listarMedicamentos(Long userId) {
        return medicineRepository.findByUserId(userId);
    }

    public Medicine atualizarMedicamento(Long userId, Long medicineId, Medicine dadosNovos) {
        Medicine medicine = medicineRepository.findByIdAndUserId(medicineId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento", medicineId));

        medicine.setNome(dadosNovos.getNome());
        medicine.setHorario(dadosNovos.getHorario());
        medicine.setDiaInicio(dadosNovos.getDiaInicio());
        medicine.setDiaFinal(dadosNovos.getDiaFinal());
        medicine.setDoses(dadosNovos.getDoses());
        medicine.setDosesPorDia(dadosNovos.getDosesPorDia());

        return medicineRepository.save(medicine);
    }

    public void excluirMedicamento(Long userId, Long medicineId) {
        Medicine medicine = medicineRepository.findByIdAndUserId(medicineId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Medicamento", medicineId));

        medicineRepository.delete(medicine);
    }

    public Medicine marcarComoTomado(Long userId, Long medicineId) {
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