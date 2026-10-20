package prova;

public record ResumoSetor(String setor, long quantidade, double totalHoras) {
    public ResumoSetor {
        if (setor == null || setor.isBlank()){
            throw new IllegalArgumentException("Setor inválido");
        }
        if (quantidade < 1){
            throw new IllegalArgumentException("Quantidade inválida");
        }
        if (totalHoras < 0){
            throw new IllegalArgumentException("Total de horas inválidos");
        }
        setor = setor.trim();
    }
}
