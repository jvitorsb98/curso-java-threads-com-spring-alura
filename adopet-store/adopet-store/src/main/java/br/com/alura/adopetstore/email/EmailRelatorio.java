package br.com.alura.adopetstore.email;

import br.com.alura.adopetstore.dto.PedidoDTO;
import br.com.alura.adopetstore.dto.RelatorioEstoque;
import br.com.alura.adopetstore.dto.RelatorioFaturamento;
import br.com.alura.adopetstore.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailRelatorio {
    @Autowired
    private EnviadorEmail enviador;

    @Async
    public void enviar(RelatorioEstoque relatorioEstoque, RelatorioFaturamento relatorioFaturamento){
        enviador.enviarEmail(
                "Relatorios gerados",
                "admin@email.com",
                """
                        Olá, seus relátorios foram gerados e seguem abaixo:
                        %s
                        
                        %s
                        """.formatted(relatorioEstoque,relatorioFaturamento)
        );
    }
}
