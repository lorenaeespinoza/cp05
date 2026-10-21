package prova;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Chamado> lista = new ArrayList<>();

        lista.add(new Chamado("CH-101", "Financeiro", 3, 2.5,
                Arrays.asList("vpn", "acesso"), "RESOLVIDO"));

        lista.add(new Chamado("CH-102", "TI", 5, 6.0,
                Arrays.asList("servidor", "backup"), "ABERTO"));

        lista.add(new Chamado("CH-103", "RH", 2, 1.0,
                Arrays.asList("impressora"), "RESOLVIDO"));

        lista.add(new Chamado("CH-104", "Financeiro", 4, 3.5,
                Arrays.asList("ERP", "acesso", "erp"), "RESOLVIDO"));

        lista.add(new Chamado("CH-105", "Marketing", 1, 0.5,
                Arrays.asList("email"), "CANCELADO"));

        lista.add(new Chamado("CH-106", "TI", 5, 4.0,
                Arrays.asList("servidor", "rede"), "RESOLVIDO"));

        lista.add(new Chamado("CH-107", "RH", 3, 2.0,
                Arrays.asList("acesso", "vpn"), "ABERTO"));

        lista.add(new Chamado("CH-102", "TI", 2, 1.0,
                Arrays.asList("impressora"), "RESOLVIDO"));

        lista.add(new Chamado("CH-108", "Marketing", 4, 3.0,
                Arrays.asList("rede", "vpn"), "ABERTO"));

        lista.add(new Chamado("CH-109", "Financeiro", 2, 4.0,
                Arrays.asList("erp"), "ABERTO"));

        lista.add(new Chamado("CH-110", "TI", 3, 2.0,
                Arrays.asList("backup"), "RESOLVIDO"));

        lista.add(new Chamado("CH-103", "Marketing", 5, 9.0,
                Arrays.asList("email"), "ABERTO"));

        lista.add(new Chamado("CH-111", "RH", 4, 5.5,
                Arrays.asList("vpn", "Acesso"), "RESOLVIDO"));

        lista.add(new Chamado("CH-112", "TI", 1, 0.5,
                Arrays.asList("email", "rede"), "CANCELADO"));

        lista.add(new Chamado("CH-113", "RH", 2, 1.5,
                Arrays.asList("VPN"), "ABERTO"));

        CentralChamados central = new CentralChamados(
                Collections.unmodifiableList(lista)
        );
        System.out.println("== A ==");
        for (Chamado chamado : central.semDuplicados()) {
            System.out.println(
                    chamado.getCodigo() + ":" + chamado.getPrioridade()
            );
        }
        System.out.println("== B ==");
        Map<String, List<String>> abertos = central.abertosPorSetor();

        List<String> setores = new ArrayList<>(abertos.keySet());
        Collections.sort(setores);

        for (String setor : setores) {
            System.out.println(setor + ":" + abertos.get(setor));
        }
        System.out.println("== C ==");
        for (ResumoSetor resumo : central.resumoResolvidos()) {
            System.out.println(
                    resumo.setor() + ";"
                            + resumo.quantidade() + ";"
                            + String.format(Locale.US, "%.2f", resumo.totalHoras())
            );
        }
        System.out.println("== D ==");
        System.out.println(central.tagsMaisFrequentes(3));
    }
}

