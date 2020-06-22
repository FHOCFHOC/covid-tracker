package com.corinto.coronavirustracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.corinto.coronavirustracker.models.DadosLocalizacao;
import com.corinto.coronavirustracker.services.CoronaVirusDataService;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
       List<DadosLocalizacao> dadosTotais = coronaVirusDataService.getDadosTotais();
       int totalCasos = dadosTotais.stream().mapToInt(stat -> stat.getCasosTotaisAtuais()).sum();
       int totalCasosNovos = dadosTotais.stream().mapToInt(stat -> stat.getDiferencaOntemHoje()).sum();
       model.addAttribute("dadosLocalizacao", dadosTotais);
       model.addAttribute("totalCasos", totalCasos);
       model.addAttribute("totalCasosNovos", totalCasosNovos);

        return "home";
    }
}
