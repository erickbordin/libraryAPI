package io.github.cursodsousa.libraryapi.service;

import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import io.github.cursodsousa.libraryapi.repository.LivroRepository;
import io.github.cursodsousa.libraryapi.repository.specs.LivroSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static io.github.cursodsousa.libraryapi.repository.specs.LivroSpecs.*;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;


    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Livro livro) {
        repository.delete(livro);
    }

    public List<Livro> pesquisa(
            String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicacao) {

        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (isbn != null) {
            specs = specs.and(isbnEqual(isbn));
        }
        if (titulo != null) {
            specs = specs.and(tituloLike(titulo));
        }
        if (genero != null) {
            specs = specs.and(generoEqual(genero));
        }
        if (anoPublicacao != null) {
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }
        if (nomeAutor != null) {
            specs = specs.and(nomeAutorLike(nomeAutor));
        }
        return repository.findAll(specs);
    }
}
