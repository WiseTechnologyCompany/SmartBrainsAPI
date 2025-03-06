package br.com.smartbrains.service;

import br.com.smartbrains.domain.abstracts.AbstractService;
import br.com.smartbrains.domain.messages.DeleteResponseDTO;
import br.com.smartbrains.model.dto.GeneroDTO;
import br.com.smartbrains.model.entity.Genero;
import br.com.smartbrains.repository.GeneroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GeneroService extends AbstractService<GeneroDTO, DeleteResponseDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    GeneroRepository generoRepository;

    @Override
    public List<GeneroDTO> findAll() {
        return generoRepository.findAll().stream().map(GeneroDTO::new).toList();
    }

    @Override
    public GeneroDTO findById(Integer pId) {
        var findGenero = generoRepository.getReferenceById(pId);
        return new GeneroDTO(findGenero);
    }

    @Override
    public GeneroDTO save(GeneroDTO pGeneroDTO) {
        var generoToSave = modelMapper.map(pGeneroDTO, Genero.class);
        return new GeneroDTO(generoRepository.save(generoToSave));
    }

    @Override
    public GeneroDTO update(Integer pId, GeneroDTO pGeneroDTO) {
        var findGenero = generoRepository.getReferenceById(pId);
        BeanUtils.copyProperties(pGeneroDTO, findGenero, "id");
        return new GeneroDTO(generoRepository.save(findGenero));
    }

    @Override
    public DeleteResponseDTO delete(Integer pId) {
        var findGenero = generoRepository.getReferenceById(pId);
        generoRepository.delete(findGenero);
        return DeleteResponseDTO.deleteResponseDTO;
    }
}