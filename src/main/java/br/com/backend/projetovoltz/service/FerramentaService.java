package br.com.backend.projetovoltz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.backend.projetovoltz.dto.FerramentaDto;
import br.com.backend.projetovoltz.entities.Ferramenta;
import br.com.backend.projetovoltz.entities.Projeto;
import br.com.backend.projetovoltz.exception.DatabaseException;
import br.com.backend.projetovoltz.exception.FerramentaException;
import br.com.backend.projetovoltz.repository.FerramentaRepository;
import br.com.backend.projetovoltz.repository.ProjetoRepository;

@Service
public class FerramentaService {

	@Autowired
	FerramentaRepository ferramentaRepository;

	@Autowired
	ProjetoRepository projetoRepository;

	public FerramentaDto paraDto(Ferramenta ferramenta, FerramentaDto ferramentaDto) {
		ferramentaDto.setIdFerramenta(ferramenta.getIdFerramenta());
		ferramentaDto.setNome(ferramenta.getNome());
		ferramentaDto.setTipo(ferramenta.getTipo());
		ferramentaDto.setDescricao(ferramenta.getDescricao());
		ferramentaDto.setCustoMinUsuarioMes(ferramenta.getCustoMinUsuarioMes());
		ferramentaDto.setTags(ferramenta.getTags());

		return ferramentaDto;
	}

	public Ferramenta paraEntidade(FerramentaDto ferramentaDto, Ferramenta ferramenta) {
		ferramenta.setNome(ferramentaDto.getNome());
		ferramenta.setTipo(ferramentaDto.getTipo());
		ferramenta.setDescricao(ferramentaDto.getDescricao());
		ferramenta.setCustoMinUsuarioMes(ferramentaDto.getCustoMinUsuarioMes());
		ferramenta.setTags(ferramentaDto.getTags());

		return ferramenta;
	}

	public String salvar(FerramentaDto ferramentaDto) {
		Ferramenta ferramenta = new Ferramenta();
		Ferramenta ferramentaSalvar = paraEntidade(ferramentaDto, ferramenta);
		ferramentaRepository.save(ferramentaSalvar);

		return "Ferramenta foi criada com o id: " + ferramenta.getIdFerramenta();
	}

	public String salvarVarios(List<FerramentaDto> listaFerramentaDto) {
		List<Ferramenta> listaDeFerramentas = new ArrayList<>();

		for (FerramentaDto ferramentaDto : listaFerramentaDto) {
			Ferramenta ferramenta = new Ferramenta();
			paraEntidade(ferramentaDto, ferramenta);
			listaDeFerramentas.add(ferramenta);
		}
		ferramentaRepository.saveAll(listaDeFerramentas);
		return "Ferramentas criadas com sucesso!";

	}

	public List<FerramentaDto> listarTodos() {
		List<Ferramenta> listaFerramenta = ferramentaRepository.findAll();
		List<FerramentaDto> listaFerramentaDto = new ArrayList<>();

		for (Ferramenta ferramenta : listaFerramenta) {
			FerramentaDto ferramentaDto = new FerramentaDto();
			paraDto(ferramenta, ferramentaDto);
			listaFerramentaDto.add(ferramentaDto);
		}
		return listaFerramentaDto;
	}

	public FerramentaDto buscarPorId(Integer idFerramenta) throws FerramentaException {
		Optional<Ferramenta> ferramenta = ferramentaRepository.findById(idFerramenta);

		Ferramenta ferramentaNoBanco = new Ferramenta();
		FerramentaDto ferramentaDto = new FerramentaDto();

		if (ferramenta.isPresent()) {
			ferramentaNoBanco = ferramenta.get();
			ferramentaDto = paraDto(ferramentaNoBanco, ferramentaDto);
			return ferramentaDto;
		}
		throw new FerramentaException("Ferramenta não encontrada. Id " + idFerramenta);

	}

	public String atualizar(Integer idFerramenta, FerramentaDto ferramentaDto) throws FerramentaException {
		Optional<Ferramenta> ferramenta = ferramentaRepository.findById(idFerramenta);
		Ferramenta ferramentaNoBanco = new Ferramenta();

		if (ferramenta.isPresent()) {
			ferramentaNoBanco = ferramenta.get();

			if (ferramentaDto.getNome() != null) {
				ferramentaNoBanco.setNome(ferramentaDto.getNome());
			}
			if (ferramentaDto.getTipo() != null) {
				ferramentaNoBanco.setTipo(ferramentaDto.getTipo());
			}
			if (ferramentaDto.getDescricao() != null) {
				ferramentaNoBanco.setDescricao(ferramentaDto.getDescricao());
			}
			if (ferramentaDto.getCustoMinUsuarioMes() != null) {
				ferramentaNoBanco.setCustoMinUsuarioMes(ferramentaDto.getCustoMinUsuarioMes());
			}
			if (ferramentaDto.getTags() != null) {
				ferramentaNoBanco.setTags(ferramentaDto.getTags());
			}
			ferramentaRepository.save(ferramentaNoBanco);

			return "A Ferramenta com o id " + ferramentaNoBanco.getIdFerramenta() + " foi atualizada";
		}
		throw new FerramentaException("Ferramenta não encontrada com o Id informado!");

	}

	public String deletar(Integer idFerramenta) throws FerramentaException {
		try {
			ferramentaRepository.deleteById(idFerramenta);
			return "Ferramenta excluída com sucesso!";

		} catch (EmptyResultDataAccessException e) {
			throw new FerramentaException("Ferramenta não encontrada com o Id informado!");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public FerramentaDto buscaPorIdProjeto(Integer idProjeto) throws FerramentaException {
		Projeto projeto = projetoRepository.findById(idProjeto).get();

		if (projeto == null) {
			throw new FerramentaException("Ferramenta não encontrada.");
		}

		Optional<Ferramenta> ferramenta = ferramentaRepository.findByProjetos(projeto);

		Ferramenta ferramentaNoBanco = new Ferramenta();
		FerramentaDto ferramentaDto = new FerramentaDto();

		if (ferramenta.isPresent()) {
			ferramentaNoBanco = ferramenta.get();
			ferramentaDto = paraDto(ferramentaNoBanco, ferramentaDto);
			return ferramentaDto;
		}
		throw new FerramentaException("Ferramenta não encontrada.");
	}

}
