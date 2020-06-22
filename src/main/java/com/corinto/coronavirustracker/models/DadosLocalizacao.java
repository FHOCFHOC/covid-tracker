package com.corinto.coronavirustracker.models;

public class DadosLocalizacao {

	private String estado;
    private String pais;
    private int casosTotaisAtuais;
    private int diferencaOntemHoje;

    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
  
    
    public int getCasosTotaisAtuais() {
		return casosTotaisAtuais;
	}

	public void setCasosTotaisAtuais(int casosTotaisAtuais) {
		this.casosTotaisAtuais = casosTotaisAtuais;
	}

	@Override
    public String toString() {
        return "DadosLocalizacao{" +
                "estado='" + estado + '\'' +
                ", pais='" + pais + '\'' +
                ", casosTotaisAtuais=" + casosTotaisAtuais +
                '}';
    }

	public int getDiferencaOntemHoje() {
		return diferencaOntemHoje;
	}

	public void setDiferencaOntemHoje(int diferencaOntemHoje) {
		this.diferencaOntemHoje = diferencaOntemHoje;
	}
	
}
