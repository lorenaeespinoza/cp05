package prova;

import java.util.*;

public class CentralChamados {
    private List<Chamado> chamados;

    public CentralChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
    public List<Chamado> semDuplicados (){
        List<Chamado> resultados = new ArrayList<>();
        Set<String> vistos = new HashSet<>();

        for (Chamado chamado : chamados){
            if (vistos.add(chamado.getCodigo())){
                resultados.add(chamado);
            }
        }

        Collections.sort(resultados,
                Comparator.comparingInt(Chamado::getPrioridade)
                        .reversed()
                        .thenComparing(chamado -> chamado.getCodigo()
                        )
        );
        return resultados;
    }

    public Map<String, List<String>> abertosPorSetor(){
        Map<String,List<String>> resultado = new HashMap<>();

        for (Chamado chamado : semDuplicados()){
            if (chamado.getStatus().equals("ABERTO")){
                String setor = chamado.getSetor();
                if (!resultado.containsKey(setor)){
                    resultado.put(setor, new ArrayList<>());
                }

                resultado.get(setor).add(chamado.getCodigo()
                );
            }
        }
        for (List<String> codigos : resultado.values()){
            Collections.sort(codigos);
        }
        return resultado;
    }


    public List<ResumoSetor> resumoResolvidos() {
        Map<String, ResumoSetor> resumo = new HashMap<>();

        for (Chamado chamado : semDuplicados()) {
            if (chamado.getStatus().equals("RESOLVIDO")) {
                String setor = chamado.getSetor();

                if (!resumo.containsKey(setor)) {
                    resumo.put(setor, new ResumoSetor(
                            setor, 1, chamado.getHoras()
                    ));
                } else {
                    ResumoSetor atual = resumo.get(setor);

                    resumo.put(setor, new ResumoSetor(
                            setor,
                            atual.quantidade() + 1,
                            atual.totalHoras() + chamado.getHoras()
                    ));
                }
            }
        }

        List<ResumoSetor> resultado = new ArrayList<>(resumo.values());

        Collections.sort(resultado,
                Comparator.comparingDouble(ResumoSetor::totalHoras)
                        .reversed()
                        .thenComparing(ResumoSetor::setor)
        );

        return resultado;
    }



    public List<String> tagsMaisFrequentes(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("N deve ser maior que zero");
        }

        Map<String, Integer> frequencia = new HashMap<>();

        for (Chamado chamado : semDuplicados()) {
            if (!chamado.getStatus().equals("CANCELADO")) {

                Set<String> tagsDoChamado = new HashSet<>(chamado.getTags());

                for (String tag : tagsDoChamado) {
                    if (!frequencia.containsKey(tag)) {
                        frequencia.put(tag, 0);
                    }

                    frequencia.put(tag, frequencia.get(tag) + 1);
                }
            }
        }

        List<String> tagsOrdenadas = new ArrayList<>(frequencia.keySet());

        Collections.sort(tagsOrdenadas,
                Comparator.comparingInt((String tag) -> frequencia.get(tag))
                        .reversed()
                        .thenComparing(tag -> tag)
        );

        return new ArrayList<>(
                tagsOrdenadas.subList(0, Math.min(n, tagsOrdenadas.size()))
        );
    }


}
