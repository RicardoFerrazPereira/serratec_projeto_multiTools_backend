package br.com.backend.projetovoltz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.backend.projetovoltz.dto.FerramentaDto;
import br.com.backend.projetovoltz.dto.ProjetoDto;
import br.com.backend.projetovoltz.dto.UsuarioDto;
import br.com.backend.projetovoltz.entities.Ferramenta;
import br.com.backend.projetovoltz.entities.Projeto;
import br.com.backend.projetovoltz.entities.Usuario;
import br.com.backend.projetovoltz.exception.DatabaseException;
import br.com.backend.projetovoltz.exception.ProjetoException;
import br.com.backend.projetovoltz.repository.FerramentaRepository;
import br.com.backend.projetovoltz.repository.ProjetoRepository;
import br.com.backend.projetovoltz.repository.UsuarioRepository;

@Service
public class ProjetoService {

	@Autowired
	ProjetoRepository projetoRepository;

	@Autowired
	FerramentaRepository ferramentaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	public ProjetoDto paraDto(Projeto projeto, ProjetoDto projetoDto) {
		projetoDto.setIdProjeto(projeto.getIdProjeto());
		projetoDto.setDtInicio(projeto.getDtInicio());
		projetoDto.setEmail(projeto.getEmail());
		projetoDto.setNome(projeto.getNome());

		List<FerramentaDto> listaDto = new ArrayList<>();
		for (Ferramenta f : projeto.getFerramentas()) {
			FerramentaDto ferramDto = new FerramentaDto();
			ferramDto.setIdFerramenta(f.getIdFerramenta());
			ferramDto.setNome(f.getNome());
			ferramDto.setCustoMinUsuarioMes(f.getCustoMinUsuarioMes());
			ferramDto.setDescricao(f.getDescricao());
			ferramDto.setTags(f.getTags());
			ferramDto.setTipo(f.getTipo());

			listaDto.add(ferramDto);
		}
		projetoDto.setFerramentasDto(listaDto);

		List<UsuarioDto> listaUsuarioDto = new ArrayList<>();
		for (Usuario u : projeto.getUsuarios()) {
			UsuarioDto uDto = new UsuarioDto();

			uDto.setEmail(u.getEmail());
			uDto.setIdUsuario(u.getIdUsuario());
			uDto.setNome(u.getNome());

			listaUsuarioDto.add(uDto);
		}
		projetoDto.setListaUsuariosDto(listaUsuarioDto);
		return projetoDto;
	}

	public Projeto paraEntidade(ProjetoDto projetoDto, Projeto projeto) {
		projeto.setIdProjeto(projetoDto.getIdProjeto());
		projeto.setDtInicio(projetoDto.getDtInicio());
		projeto.setEmail(projetoDto.getEmail());
		projeto.setNome(projetoDto.getNome());

		return projeto;
	}
	
	public String salvar(ProjetoDto projetoDto) {

		Projeto projeto = new Projeto();
		Projeto novoProj = paraEntidade(projetoDto, projeto);

		Usuario usuario = usuarioRepository.findByEmail(projetoDto.getEmail());
		
		if(usuario == null) {
			return "Não foi encontrado o usuário com email informado";
		}

		List<Usuario> listaUsuario = new ArrayList<>();
		listaUsuario.add(usuario);
		novoProj.setUsuarios(listaUsuario);

		projetoRepository.save(novoProj);
		usuario.getProjetosUsuario().add(novoProj);
		usuarioRepository.save(usuario);

		return "O projeto foi criado com sucesso!";
	}	

	public String salvarVarios(List<ProjetoDto> listaProjetoDto) {
		List<Projeto> listaDeProjetos = new ArrayList<>();

		for (ProjetoDto projetoDto : listaProjetoDto) {
			Projeto projeto = new Projeto();
			paraEntidade(projetoDto, projeto);
			listaDeProjetos.add(projeto);
		}
		projetoRepository.saveAll(listaDeProjetos);
		return "Projetos criados com sucesso!";

	}

	public List<ProjetoDto> listarTodos() {
		List<Projeto> listaProjeto = projetoRepository.findAll();
		List<ProjetoDto> listaProjetoDto = new ArrayList<>();

		for (Projeto projeto : listaProjeto) {
			ProjetoDto projetoDto = new ProjetoDto();
			paraDto(projeto, projetoDto);
			listaProjetoDto.add(projetoDto);
		}
		return listaProjetoDto;
	}

	public ProjetoDto buscarPorId(Integer idProjeto) throws ProjetoException {
		Optional<Projeto> projeto = projetoRepository.findById(idProjeto);

		Projeto projetoNoBanco = new Projeto();
		ProjetoDto projetoDto = new ProjetoDto();

		if (projeto.isPresent()) {
			projetoNoBanco = projeto.get();
			projetoDto = paraDto(projetoNoBanco, projetoDto);
			return projetoDto;
		}
		throw new ProjetoException("Projeto não encontrado");
	}

	public String atualizar(Integer idProjeto, ProjetoDto projetoDto) throws ProjetoException {
		Optional<Projeto> projeto = projetoRepository.findById(idProjeto);
		Projeto projetoNoBanco = new Projeto();

		if (projeto.isPresent()) {
			projetoNoBanco = projeto.get();

			if (projetoDto.getNome() != null) {
				projetoNoBanco.setNome(projetoDto.getNome());
			}
			if (projetoDto.getDtInicio() != null) {
				projetoNoBanco.setDtInicio(projetoDto.getDtInicio());
			}
			if (projetoDto.getEmail() != null) {
				projetoNoBanco.setEmail(projetoDto.getEmail());
			}

			projetoRepository.save(projetoNoBanco);

			return "O Projeto com o id " + projetoNoBanco.getIdProjeto() + " foi atualizado";
		}
		throw new ProjetoException("O Projeto não foi atualizada!");

	}

	public String deletar(Integer idProjeto) throws ProjetoException {
		try {
			projetoRepository.deleteById(idProjeto);
			return "Projeto excluído com sucesso!";
		}
		catch(EmptyResultDataAccessException e) {
			throw new ProjetoException("Projeto não encontrado com id informado!");
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public ProjetoDto addFerramenta(Integer idProjeto, Integer idFerramenta) {
		Projeto proj = projetoRepository.findById(idProjeto).get();
		Ferramenta fer = ferramentaRepository.findById(idFerramenta).get();
		proj.getFerramentas().add(fer);
		Projeto projAtualizado = projetoRepository.save(proj);

		fer.getProjetos().add(projAtualizado);
		ferramentaRepository.save(fer);

		ProjetoDto projetoDto = new ProjetoDto();

		projetoDto = paraDto(projAtualizado, projetoDto);
		return projetoDto;
	}

	public ProjetoDto addUsuario(Integer idProjeto, Integer idUsuario) {
		Projeto proj = projetoRepository.findById(idProjeto).get();
		Usuario u = usuarioRepository.findById(idUsuario).get();
		proj.getUsuarios().add(u);
		Projeto projAtualizado = projetoRepository.save(proj);

		u.getProjetosUsuario().add(projAtualizado);
		usuarioRepository.save(u);

		ProjetoDto projetoDto = new ProjetoDto();

		projetoDto = paraDto(projAtualizado, projetoDto);
		return projetoDto;
	}
	
		
	public List<ProjetoDto> listarProjetosPorUsuario(String emailUsuario) {
		Usuario usuario = usuarioRepository.findByEmail(emailUsuario);
		if(usuario != null) {
			List<Usuario> listUsuario = new ArrayList<>();
			listUsuario.add(usuario);
			
			List<Projeto> listaProjeto = projetoRepository.findByInUsuarios(listUsuario);
			List<ProjetoDto> listaProjetoDto = new ArrayList<>();

			for (Projeto projeto : listaProjeto) {
				ProjetoDto projetoDto = new ProjetoDto();
				paraDto(projeto, projetoDto);
				listaProjetoDto.add(projetoDto);
			}
			return listaProjetoDto;
		}
		return null;
	}

}
