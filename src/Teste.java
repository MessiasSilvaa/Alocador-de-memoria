import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class Teste {

    private static Memoria memoria;
    private static Scanner s;

    public static void main(String[] args) {

        s = new Scanner(System.in);
        memoria = new Memoria();

        memoria.addBloco(new Bloco(500));
        memoria.addBloco(new Bloco(450));
        memoria.addBloco(new Bloco(400));
        memoria.addBloco(new Bloco(390));
        memoria.addBloco(new Bloco(100));
        memoria.addBloco(new Bloco(350));
        memoria.addBloco(new Bloco(410));
        memoria.addBloco(new Bloco(100));
        memoria.addBloco(new Bloco(50));
        memoria.addBloco(new Bloco(25));

        int opcao = 0;

        do {
            mostraMemoria();

            System.out.println("Escolha 1 das 3 estratégias de gerenciamento de processo:"
                    + "\n1 - First Fit (O Primeiro encaixe)"
                    + "\n2 - Next Fit (O Próximo encaixe)"
                    + "\n3 - Best Fit (O Melhor encaixe)"
                    + "\n4 - Worst Fit (O Pior encaixe)"
                    + "\n5 - Adicionar Processo"
                    + "\n6 - Deletar Bloco"
                    + "\n0 - Sair");

            opcao = s.nextInt();

            verificaOpcao(opcao);

        } while (opcao != 0);

    }

    private static void verificaOpcao(int opcao) {
        int tamanho = 0;
        switch (opcao) {
            case 1:
                System.out.println("[FIRST FIT] Insira o tamanho do processo:");
                tamanho = s.nextInt();
                Gerenciador.firstFit(tamanho, memoria);
                break;
            case 2:
                System.out.println("[NEXT FIT] Insira o tamanho do processo:");
                tamanho = s.nextInt();
                Gerenciador.nextFit(tamanho, memoria);
                break;
            case 3:
                System.out.println("[BEST FIT] Insira o tamanho do processo:");
                tamanho = s.nextInt();
                Gerenciador.bestFit(tamanho, memoria);
                break;
            case 4:
                System.out.println("[WORST FIT] Insira o tamanho do processo:");
                tamanho = s.nextInt();
                Gerenciador.worstFit(tamanho, memoria);
                break;
            case 5:
                System.out.println("Insira o tamanho do novo processo:");
                tamanho = s.nextInt();
                memoria.addBloco(new Bloco(tamanho, true)); // Adiciona o processo com tamanho fornecido e marcado como alocado
                break;
            case 6:
                System.out.println("Insira o índice do bloco a ser deletado:");
                int indice = s.nextInt();
                Bloco blocoDeletado = memoria.getBloco(indice); // Obter o bloco a ser deletado
                blocoDeletado.setAlocado(false); // Marcar o bloco como não alocado
                blocoDeletado.setEspacoEmUso(0); // Definir o espaço em uso como 0
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Escolha inválida!");
        }
    }

    private static void mostraMemoria() {
        List<Bloco> blocos = memoria.getBlocos();
        DecimalFormat df = new DecimalFormat("#00");

        int espacoTotal = 0;
        int espacoAlocado = 0;
        int blocosAlocados = 0;

        for (Bloco bloco : blocos) {
            espacoTotal += bloco.getEspacoTotal();
            if (bloco.isAlocado()) {
                espacoAlocado += bloco.getEspacoEmUso();
                blocosAlocados++;
            }
        }

        int espacoLivre = espacoTotal - espacoAlocado;
        double porcentagemAlocada = (double) espacoAlocado / espacoTotal * 100;
        double porcentagemLivre = (double) espacoLivre / espacoTotal * 100;

        System.out.println("Visão Geral da Memória:");
        System.out.println("Espaço total: " + espacoTotal);
        System.out.println("Espaço alocado: " + espacoAlocado + " (" + df.format(porcentagemAlocada) + "%)");
        System.out.println("Espaço livre: " + espacoLivre + " (" + df.format(porcentagemLivre) + "%)");
        System.out.println("Blocos alocados: " + blocosAlocados + "/" + blocos.size());

        System.out.println("\nLocalização dos Processos na Memória:");

        for (Bloco bloco : blocos) {
            if (bloco.isAlocado()) {
                System.out.println("Processo de tamanho " + bloco.getEspacoEmUso() + " alocado no bloco " + bloco.getId());
            }
        }
    }
}
