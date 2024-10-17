package org.serratec.bookshop.repository;

import java.util.List;

import org.serratec.bookshop.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LivroRepository extends JpaRepository<Livro, Long> {
	List<Livro> findByNomeIgnoreCase(String none);
	List<Livro> findByAutorIgnoreCase(String autor);
	List<Livro> findByEditoraIgnoreCase(String editora);
}
