package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("d2f4c9ad-7ab1-44a1-8a28-f17dab4536e2");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento((LocalDate.of(1960, 1, 30)));

            repository.save(autorEncontrado);
        }


    }

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("d2f4c9ad-7ab1-44a1-8a28-f17dab4536e2");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("cf73d88d-a16d-4ca5-8b34-1db3fff543ed");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivroTest() {
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1970, 8, 5));

        Livro livro = new Livro();
        livro.setIsbn("23232-84844");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Livro teste aula 64");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Livro livro2 = new Livro();
        livro2.setIsbn("23232-84844");
        livro2.setPreco(BigDecimal.valueOf(205));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("Livro2 teste aula 64");
        livro2.setDataPublicacao(LocalDate.of(200, 1, 2));

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);
        livro.setAutor(autor);
        livro2.setAutor(autor);
        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());


    }

    @Test
    void listarLivrosAutor() {
        var id = UUID.fromString("841fb3be-63dc-45fc-b3aa-7c5ed634526f");
        var autor = repository.findById(id).get();

        // buscar os livros do autor
        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }

}
