package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.domain.abstracts.AbstractService;
import br.com.wisefinances.smartbrains.domain.messages.MessagesResponseDTO;
import br.com.wisefinances.smartbrains.model.dto.tipomovimentacao.TipoMovimentacaoDTO;
import br.com.wisefinances.smartbrains.model.entity.tipomovimentacao.TipoMovimentacao;
import br.com.wisefinances.smartbrains.repository.tipomovimentacao.TipoMovimentacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoMovimentacaoService extends AbstractService<TipoMovimentacaoDTO, MessagesResponseDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TipoMovimentacaoRepository tipoMovimentacaoRepository;

    @Override
    public List<TipoMovimentacaoDTO> findAll() {
        return tipoMovimentacaoRepository.findAll().stream().map(TipoMovimentacaoDTO::new).toList();
    }

    @Override
    public TipoMovimentacaoDTO findById(Integer pId) {
        var tipoMovimentacao = tipoMovimentacaoRepository.getReferenceById(pId);
        return new TipoMovimentacaoDTO(tipoMovimentacao);
    }

    @Override
    public MessagesResponseDTO save(TipoMovimentacaoDTO pTipoMovimentacaoDTO) {
        var tipoMovimentacao = modelMapper.map(pTipoMovimentacaoDTO, TipoMovimentacao.class);
        new TipoMovimentacaoDTO(tipoMovimentacaoRepository.save(tipoMovimentacao));
        return MessagesResponseDTO.createSucessResponseDTO;
    }

    @Override
    public TipoMovimentacaoDTO update(Integer pId, TipoMovimentacaoDTO pTipoMovimentacaoDTO) {
        var tipoMovimentacao = tipoMovimentacaoRepository.getReferenceById(pId);
        BeanUtils.copyProperties(pTipoMovimentacaoDTO, tipoMovimentacao, "id");
        return new TipoMovimentacaoDTO(tipoMovimentacaoRepository.save(tipoMovimentacao));
    }

    @Override
    public MessagesResponseDTO delete(Integer pId) {
        var tipoMovimentacao = tipoMovimentacaoRepository.getReferenceById(pId);
        tipoMovimentacaoRepository.delete(tipoMovimentacao);
        return MessagesResponseDTO.deleteSucessResponseDTO;
    }
}