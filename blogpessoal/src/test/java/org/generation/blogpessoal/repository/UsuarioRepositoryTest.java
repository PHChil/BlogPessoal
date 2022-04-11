package org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogpessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.save(new Usuario(0L, "DJ Cleiton Rasta","cleitinho@pedra.com","cabecadegelo","https://i.imgur.com/JR7kUFU.jpg\r\n"));
		usuarioRepository.save(new Usuario(0L, "DJ Laurinha Lero","larinha@lero.com","laura123","https://i.imgur.com/JR7kUFU.jpg\r\n"));
		usuarioRepository.save(new Usuario(0L, "MC Naninha","naninha@imperio.com","trabalholindo","https://i.imgur.com/JR7kUFU.jpg\r\n"));
	}
	
	@Test
	@DisplayName("Retorna apenas um usuário")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("naninha@imperio.com");
		assertTrue(usuario.get().getUsuario().equals("naninha@imperio.com"));
		
	}
	
	@Test
	@DisplayName("Retorna dois usuários")
	public void deveRetornarDoisUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("DJ");
		assertEquals(2, listaDeUsuarios.size());
		
		assertTrue(listaDeUsuarios.get(0).getNome().equals("DJ Cleiton Rasta"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("DJ Laurinha Lero"));

		
	}
}
