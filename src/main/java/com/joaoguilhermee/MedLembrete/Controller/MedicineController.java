package com.joaoguilhermee.MedLembrete.Controller;

import com.joaoguilhermee.MedLembrete.Model.Medicine;
import com.joaoguilhermee.MedLembrete.Service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{userId}/medicamentos")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping
    public ResponseEntity<Medicine> adicionar(@PathVariable Long userId,
                                              @RequestBody Medicine medicine) {
        return ResponseEntity.ok(medicineService.adicionarMedicamento(userId, medicine));
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> listar(@PathVariable Long userId) {
        return ResponseEntity.ok(medicineService.listarMedicamentos(userId));
    }

    @PutMapping("/{medicineId}")
    public ResponseEntity<Medicine> atualizar(@PathVariable Long userId,
                                              @PathVariable Long medicineId,
                                              @RequestBody Medicine medicine) {
        return ResponseEntity.ok(medicineService.atualizarMedicamento(userId, medicineId, medicine));
    }

    @DeleteMapping("/{medicineId}")
    public ResponseEntity<Void> excluir(@PathVariable Long userId,
                                        @PathVariable Long medicineId) {
        medicineService.excluirMedicamento(userId, medicineId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{medicineId}/tomado")
    public ResponseEntity<Medicine> marcarTomado(@PathVariable Long userId,
                                                 @PathVariable Long medicineId) {
        return ResponseEntity.ok(medicineService.marcarComoTomado(userId, medicineId));
    }
}