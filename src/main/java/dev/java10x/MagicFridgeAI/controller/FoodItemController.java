package dev.java10x.MagicFridgeAI.controller;

import dev.java10x.MagicFridgeAI.DTO.FoodItemDTO;
import dev.java10x.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }

    //POST
    @PostMapping ("/criar")
    public ResponseEntity<FoodItemDTO> criar(@RequestBody FoodItemDTO foodItemDTO){
        FoodItemDTO salvo = service.salvar(foodItemDTO);
        return ResponseEntity.ok(salvo);
    }

    //GET
    @GetMapping("/listar")
    public ResponseEntity<List<FoodItemDTO>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<FoodItemDTO> buscarPorId(@PathVariable Long id){
        FoodItemDTO item = service.buscarPorId(id);
        if (item == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(item);
        }
    }

    //UPDATE
    @PutMapping("/alterar/{id}")
    public ResponseEntity<FoodItemDTO> alterar(@PathVariable Long id, @RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO itemAlterado = service.alterar(id, foodItemDTO);
        if (itemAlterado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemAlterado);
    }

    //DELETE
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id) != null){
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
