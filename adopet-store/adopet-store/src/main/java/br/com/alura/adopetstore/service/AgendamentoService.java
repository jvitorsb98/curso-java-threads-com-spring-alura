package br.com.alura.adopetstore.service;


import br.com.alura.adopetstore.dto.RelatorioEstoque;
import br.com.alura.adopetstore.dto.RelatorioFaturamento;
import br.com.alura.adopetstore.email.EmailRelatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AgendamentoService {

    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    private RelatorioService relatorioService;

    @Autowired
    private EmailRelatorio emailRelatorio;

    @Scheduled(cron = "0 52 17 * * *", zone = TIME_ZONE)
    public void envioEmailsAgendado(){
        var relatorioZerado = relatorioService.infoEstoque();
        var faturamentoObtido = relatorioService.faturamentoObtido();

        CompletableFuture.allOf(relatorioZerado,faturamentoObtido).join();

        try {
            emailRelatorio.enviar(relatorioZerado.get(),faturamentoObtido.get());

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }


}
