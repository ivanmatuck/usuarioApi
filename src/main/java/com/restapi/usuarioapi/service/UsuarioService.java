package com.restapi.usuarioapi.service;

import com.restapi.usuarioapi.dto.MessageResponseDTO;
import com.restapi.usuarioapi.dto.UsuarioDTO;
import com.restapi.usuarioapi.entity.ContatosUsuario;
import com.restapi.usuarioapi.entity.Usuario;
import com.restapi.usuarioapi.repository.ContatosUsuarioRepository;
import com.restapi.usuarioapi.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContatosUsuarioRepository contatosUsuarioRepository;

    @Transactional
    public MessageResponseDTO createUsuario(UsuarioDTO usuarioDTO) throws Exception {
        if (this.usuarioRepository.findUsuarioByCpf(usuarioDTO.getCpf()).isPresent()) {
            return new MessageResponseDTO("Já existe um usuário com este CPF");
        } else {
            List<ContatosUsuario> contatosUsuariosList = new ArrayList<>();
            Usuario usuario = new Usuario(usuarioDTO.getName(), usuarioDTO.getCpf(), contatosUsuariosList);
            this.usuarioRepository.save(usuario);
            try {
                usuarioDTO.getContatosUsuarios().forEach(allContatosUsuario -> {
                    ContatosUsuario contatosUsuario = new ContatosUsuario(usuario.getId(), allContatosUsuario.getEmail(),
                            allContatosUsuario.getTelefone(), allContatosUsuario.getIsContatoPrincipal());
                    this.contatosUsuarioRepository.save(contatosUsuario);
                    contatosUsuariosList.add(contatosUsuario);
                });
                usuario.setContatosUsuarios(contatosUsuariosList);
                this.usuarioRepository.save(usuario);
                return new MessageResponseDTO("Usuário criado com sucesso.");
            } catch (Exception e) {
                throw new Exception("Erro ao salvar usuário", e.getCause());
            }
        }
    }

    @Transactional
    public List<UsuarioDTO> listAll() throws Exception {
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
        try {
            List<Usuario> allUsuarios = this.usuarioRepository.findAll();
            allUsuarios.forEach(usuario -> {
                List<ContatosUsuario> contatosUsuario =
                        this.contatosUsuarioRepository.findContatosUsuarioByIdUsuario(usuario.getId());
                UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getName(), usuario.getCpf(), contatosUsuario);
                usuarioDTOList.add(usuarioDTO);
            }); return usuarioDTOList;
        } catch (Exception e) {
            throw new Exception("Erro ao buscar usuários.", e.getCause());
        }
    }


    @Transactional
    public List<UsuarioDTO> findByCPF(Long cpf) throws Exception {
        verifyIfExists(cpf);
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
        try {
            Optional<Usuario> usuario = this.usuarioRepository.findUsuarioByCpf(cpf);
            List<ContatosUsuario> contatosUsuario = this.contatosUsuarioRepository.findContatosUsuarioByIdUsuario(usuario.get().getId());
            usuarioDTOList.add(new UsuarioDTO(usuario.get().getName(), usuario.get().getCpf(), contatosUsuario));
            return usuarioDTOList;
        } catch (Exception e) {
            throw new Exception("Erro ao buscar usuários com o CPF: " + cpf, e.getCause());
        }
    }

    @Transactional
    public MessageResponseDTO updateByCpf(Long cpf, UsuarioDTO usuarioDTO) throws Exception {
        verifyIfExists(cpf);
        List<ContatosUsuario> contatosUsuariosNovos = new ArrayList<>();
        try {
            Optional<Usuario> usuarioOptional = this.usuarioRepository.findUsuarioByCpf(usuarioDTO.getCpf());
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                List<ContatosUsuario> contatosUsuariosList = this.contatosUsuarioRepository.findContatosUsuarioByIdUsuario(usuario.getId());
                this.contatosUsuarioRepository.deleteAll(contatosUsuariosList);
                usuarioDTO.getContatosUsuarios().forEach(allContatosUsuario -> {
                    ContatosUsuario contatosUsuario = new ContatosUsuario(usuario.getId(), allContatosUsuario.getEmail(),
                            allContatosUsuario.getTelefone(), allContatosUsuario.getIsContatoPrincipal());
                    this.contatosUsuarioRepository.save(contatosUsuario);
                    contatosUsuariosNovos.add(contatosUsuario);
                });
                usuario.setContatosUsuarios(contatosUsuariosNovos);
                this.usuarioRepository.save(usuario);
            } return new MessageResponseDTO("Contatos atualizados com sucesso.");
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar usuário", e.getCause());
        }
    }


    @Transactional
    public MessageResponseDTO deleteByCpf(Long cpf) throws Exception {
        verifyIfExists(cpf);
        try {
            Optional<Usuario> usuario = this.usuarioRepository.findUsuarioByCpf(cpf);
            if (usuario.isPresent()) {
                List<ContatosUsuario> contatosUsuario = this.contatosUsuarioRepository.findContatosUsuarioByIdUsuario(usuario.get().getId());
                contatosUsuario.forEach(contatos -> {
                    this.contatosUsuarioRepository.deleteById(contatos.getId());
                });
                this.usuarioRepository.deleteById(usuario.get().getId());
            } return new MessageResponseDTO("Usuario apagado com sucesso.");
        } catch (Exception e) {
            throw new Exception("Erro ao deletar usuários com o CPF: " + cpf, e.getCause());
        }
    }

    @Transactional
    void verifyIfExists(Long cpf) throws Exception {
        try {
            this.usuarioRepository.findUsuarioByCpf(cpf);
        } catch (Exception e) {
            throw new Exception("Usuário não encontrado para este CPF: " + cpf, e.getCause());
        }
    }
}



