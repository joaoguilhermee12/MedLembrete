package com.joaoguilhermee.MedLembrete.Controller;

import com.joaoguilhermee.MedLembrete.Model.Medicine;
import com.joaoguilhermee.MedLembrete.Service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    // POST / ADICIONAR MEDICAMENTO
    @PostMapping
    public ResponseEntity<Medicine> addMedicine(@PathVariable Long userId,
                                                @RequestBody @Valid Medicine medicine) {
        return ResponseEntity.ok(medicineService.addMedicine(userId, medicine));
    }
    // GET / LISTAR MEDICAMENTOS DO USUÁRIO
    @GetMapping
    public ResponseEntity<List<Medicine>> listMedicines(@PathVariable Long userId) {
        return ResponseEntity.ok(medicineService.listMedicines(userId));
    }
    // PUT / ATUALIZAR MEDICAMENTO
    @PutMapping("/{medicineId}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable Long userId,
                                                   @PathVariable Long medicineId,
                                                   @RequestBody @Valid Medicine medicine) {
        return ResponseEntity.ok(medicineService.updateMedicine(userId, medicineId, medicine));
    }
    // DELETE / EXCLUIR MEDICAMENTO
    @DeleteMapping("/{medicineId}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long userId,
                                               @PathVariable Long medicineId) {
        medicineService.deleteMedicine(userId, medicineId);
        return ResponseEntity.noContent().build();
    }
    // PATCH / DOSE TOMADA
    @PatchMapping("/{medicineId}/taken")
    public ResponseEntity<Medicine> markDoseTaken(@PathVariable Long userId,
                                                  @PathVariable Long medicineId) {
        return ResponseEntity.ok(medicineService.markDoseTaken(userId, medicineId));
    }
}