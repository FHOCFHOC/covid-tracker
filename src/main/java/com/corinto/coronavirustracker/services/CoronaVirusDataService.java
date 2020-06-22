package com.corinto.coronavirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.corinto.coronavirustracker.models.DadosLocalizacao;

@Service
public class CoronaVirusDataService {

	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<DadosLocalizacao> dadosTotais = new ArrayList<>();
	
	public List<DadosLocalizacao> getDadosTotais() {
        return dadosTotais;
    }
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
		List<DadosLocalizacao> dadosNovos = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
        	DadosLocalizacao dado = new DadosLocalizacao();
            dado.setEstado(record.get("Province/State"));
            dado.setPais(record.get("Country/Region"));           
            int casosAtual =  Integer.parseInt(record.get(record.size() - 1));
            int casosAnterior =Integer.parseInt(record.get(record.size() - 2));
            dado.setCasosTotaisAtuais(casosAtual);
            dado.setDiferencaOntemHoje(casosAtual - casosAnterior);
            dadosNovos.add(dado);
        }
        this.dadosTotais = dadosNovos;
	}
	
}
