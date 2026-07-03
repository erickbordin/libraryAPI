package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    /**
     * Commit -> confirma as alteracoes
     * Rollback -> desfazer as alteracoes
     */
    @Test
    void transacaoSimples() {
        transacaoService.executar();
    }

    @Test
    void transacaoEstadoMenaged() {
        transacaoService.atualizacaoSemAtualizar();
    }

}
