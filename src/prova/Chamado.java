package prova;

import java.util.ArrayList;
import java.util.List;

public class Chamado {
    private String codigo;
    private String setor;
    private int prioridade;
    private double horas;
    private List<String> tags;
    private String status;

    public Chamado(String codigo, String setor, int prioridade, double horas,
                   List<String> tags, String status) {
        if (codigo == null || codigo.isBlank()){
            throw new IllegalArgumentException("Código inválido");
        }

        if (prioridade < 1 || prioridade > 5){
            throw new IllegalArgumentException("Prioridade inválida");
        }

        if (horas < 0){
            throw new IllegalArgumentException("Horas inválidas");
        }

        if (status == null){
            throw new IllegalArgumentException("Status inválidos");
        }

        status.trim().toUpperCase();

        if (!status.equals("ABERTO")
            && !status.equals("RESOLVIDO")
            && !status.equals("CANCELADO")){
            throw new IllegalArgumentException("Status inválidos");
        }

        this.codigo = codigo.trim().toUpperCase();
        this.setor = setor.trim();
        this.prioridade = prioridade;
        this.horas = horas;
        this.status = status;

        this.tags = new ArrayList<>();

        for (String tag : tags){
            this.tags.add(tag.trim().toLowerCase());
        }
    }


    public String getCodigo() {
        return codigo;
    }

    public String getSetor() {
        return setor;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public double getHoras() {
        return horas;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Chamado)) {
            return false;
        }

        Chamado outro = (Chamado) o;

        return codigo.equals(outro.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
