package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.DTO.FoodItemDTO;
import dev.java10x.MagicFridgeAI.mapper.FoodItemMapper;
import dev.java10x.MagicFridgeAI.model.FoodItemModel;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodItemService {

    private FoodItemRepository repository;
    private FoodItemMapper mapper;

    public FoodItemService(FoodItemRepository repository, FoodItemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FoodItemDTO salvar(FoodItemDTO foodItemDTO){
        FoodItemModel model = mapper.map(foodItemDTO);
        FoodItemModel salvo = repository.save(model);
        return mapper.map(salvo);
    }

    public List<FoodItemDTO> listar(){
        return repository.findAll().stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public FoodItemDTO listarPorId(Long id){
        return repository.findById(id)
                .map(mapper::map)
                .orElse(null);
    }

    public FoodItemDTO alterar(Long id, FoodItemDTO foodItemDTO){
        if (!repository.existsById(id)){
            return null;
        }
        foodItemDTO.setId(id);
        FoodItemModel model = mapper.map(foodItemDTO);
        FoodItemModel salvo = repository.save(model);
        return mapper.map(salvo);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }


}
