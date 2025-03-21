package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.domain.abstracts.AbstractSpecificService;
import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.model.create.dto.CreateMovimentacaoDTO;
import br.com.wisefinances.smartbrains.model.create.entity.CreateMovimentacao;
import br.com.wisefinances.smartbrains.model.dto.MovimentacaoDTO;
import br.com.wisefinances.smartbrains.repository.MovimentacaoRepository;
import br.com.wisefinances.smartbrains.repository.create.CreateMovimentacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoService extends AbstractSpecificService<MovimentacaoDTO, CreateMovimentacaoDTO, MessagesResponseDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    CreateMovimentacaoRepository createMovimentacaoRepository;

    @Override
    public Page<MovimentacaoDTO> findAll(Pageable pPageable) {
        return movimentacaoRepository.findAll(pPageable).map(MovimentacaoDTO::new);
    }

    @Override
    public MovimentacaoDTO findById(Integer pId) {
        return new MovimentacaoDTO(movimentacaoRepository.getReferenceById(pId));
    }

    @Override
    public MessagesResponseDTO save(CreateMovimentacaoDTO pCreateMovimentacaoDTO) {
        var movimentacao = modelMapper.map(pCreateMovimentacaoDTO, CreateMovimentacao.class);
        createMovimentacaoRepository.save(movimentacao);
        return MessagesResponseDTO.createSucessResponseDTO;
    }

    @Override
    public CreateMovimentacaoDTO update(Integer pId, CreateMovimentacaoDTO pCreateMovimentacaoDTO) {
        var movimentacao = createMovimentacaoRepository.getReferenceById(pId);
        BeanUtils.copyProperties(pCreateMovimentacaoDTO, movimentacao, "id");
        return new CreateMovimentacaoDTO(createMovimentacaoRepository.save(movimentacao));
    }

    @Override
    public MessagesResponseDTO delete(Integer pId) {
        var movimentacao = movimentacaoRepository.getReferenceById(pId);
        movimentacaoRepository.delete(movimentacao);
        return MessagesResponseDTO.deleteSucessResponseDTO;
    }
}