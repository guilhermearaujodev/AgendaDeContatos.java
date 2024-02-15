import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    //SUBALGORITMOS

    //variáveis e objetos públicos
    public static Scanner entrada = new Scanner(System.in);
    public static String agenda[][] = new String[10][3];

    //Esse procedimento limpa a matriz
    public static void limparMatriz(String mm[][]) {
        //insere vazio em todas as células da matriz
        for(int l = 0; l < 10; l++){
            for(int c = 0; c < 3; c++){
                mm[l][c] = "";
            }
        }
    }
    //Esse procedimento cadastra um novo contato
    public static void novo(String mm[][], int l) {
        //solicita ao usuário a edição do campo da linha passada por parâmetro
        System.out.println("------------- PREENCHA O NOVO CONTATO: ");
        System.out.println("Nome........: ");
        mm[1][0] = entrada.next();
        System.out.println("Celular.....: ");
        mm[1][1] = entrada.next();
        System.out.println("E-mail......: ");
        mm[1][2] = entrada.next();
    }

    //Esse procedimento edita um contato
    public static void editarContato(String mm[][], int l) {
        //permite ao usuário editar o contato encontrado
        //não permite que o nome seja editado por ser a chave da pesquisa
        System.out.println("------------- EDITE O CONTATO: ");
        System.out.println("Nome........: " + mm[1][0] + "");
        System.out.println("Celular.....: ");
        mm[1][1] = entrada.next();
        System.out.println("E-mail......: ");
        mm[1][2] = entrada.next();
    }

    //Essa função retorna a próxima linha em branco da matriz
    public static int linhaProximoContato(String[][] mm) {
       for(int l = 0; l < 10; l++)
           if (mm[1][0].equals("")) {
               //caso tenha encontrado, retorne o número da linha
               //próxima linha vazia
               return l;
           }
       // - 1 representa a matriz estar cheia
       return -1;
    }

    //Esse procedimento exibe um contato
    public static void exibirContato(String mm[][], int linha) {
        //exibe o registro da linha passada por parâmetro
        System.out.println("Nome........: " + mm[linha][0]);
        System.out.println("Celular.....: " + mm[linha][1]);
        System.out.println("E-mail......: " + mm[linha][2]);
    }

    //Esse procedimento lista todos os contatos cadastrados
    public static void listarAgenda(String mm[][]) {
        System.out.println("------------- CONTATOS DA AGENDA: ");
        for(int l = 0; l < 10; l++){
            //enquanto tiver registro, exibe-o
            if (mm[l][0] != "") {
                exibirContato(mm,l);
                System.out.println("---------------------------------------");
            }
        }
        System.out.println("----------- FIM DA AGENDA: ");
    }

    //Essa função pesquisa o contato e retorna a linha
    public static int pesquisarContato(String mm[][], String n) {
        //caso encontre o contato, retorna a linha onde ele está
        for (int l = 0; l < 10; l++)
            if (mm[l][0].equals(n)) {
                return l;
            }
        //se não encontrou o contato retorna -1
        return -1;
    }
    //Esse procedimento exclui a linha passada por parâmetros
    public static void excluiLinha(String mm[][], int l) {
        //exclui o contato a partir da linha passada por parâmetro
        mm[1][0] = "";
        mm[1][1] = "";
        mm[1][2] = "";
        System.out.println("Contato Excluído");
    }

    //Esse procedimento pesquisa e exclui um contato
    public static void apagarContato(String mm[][], String n) {
        //inicia a variável que achou como false
        boolean achou = false;
        //alimenta a linha com o valor da pesquisa (-1 não encontrou)
        int linha = pesquisarContato(mm, n);
        String opcao;
        if (linha != -1) {
            //exibe o contato a partir da linha
            exibirContato(mm, linha);
            //confirma com o usuário se ele quer excluir ou não o contato
            System.out.println("Confirma a exclusão do contato [S]im ou [N]ão?");
            opcao = entrada.next();
            if (opcao.equals("s") || opcao.equals("S")) {
                //se a escolha for sim, exclui o contato
                excluiLinha(mm, linha);
            } else {
                //cancela a exclusão
                System.out.println("Exclusão cancelada!");
            }
        } else {
            //contato não encontrado
            System.out.println("Contato não encontrado!");
        }
    }
    //PROGRAMA PRINCIPAL
    public static void exibeMenu()
    {
        System.out.println("********* M E N U ********");
        System.out.println("1 - Adicionar novo contato");
        System.out.println("2 - Editar contato");
        System.out.println("3 - Pesquisar contato");
        System.out.println("4 - Lista de contatos");
        System.out.println("5 - Apagar um contato");
        System.out.println("6 - Sair");
    }

    public static void main(String[] args)
    {
        int opcao, linha;
        String nome;

        // ao iniciar a aplicação, sempre é bom limparmos a matriz para que a "sujeira"
        // do Buffer não influencie nos resultados
        limparMatriz(agenda);

        do
        {
            // Exibição do menu
            exibeMenu();

            // Colhe a opção escolhida pelo usuário
            System.out.print("Escolha uma opção:");
            opcao = entrada.nextInt();
            System.out.println();

            // Seleciona a opção escolhida
            switch(opcao)
            {
                // Caso a escolha seja "adicionar novo contato"
                case 1:
                    novo(agenda, linhaProximoContato(agenda));
                    break;

                // Caso a escolha seja "Editar contato"
                case 2:
                    System.out.println("------------ EDITANDO (PESQUISE O CONTATO): ");
                    System.out.print("Digite o nome.........:");
                    nome = entrada.next();
                    linha = pesquisarContato(agenda, nome);
                    if (linha == -1)
                    {
                        // informa se não encontrou o contato
                        System.out.print("Contato não Cadastrado! ");
                    }
                    else
                    {
                        // se encontrou, exibe o contato e o edite
                        exibirContato(agenda, linha);
                        editarContato(agenda, linha);
                    }
                    break;

                // Efetua a pesquisa do contato
                case 3:
                    // pede o nome
                    System.out.println("------------ PESQUISE O CONTATO: ");
                    System.out.print("Digite o nome.........:");
                    nome = entrada.next();
                    // Retorna a linha da pesquisa
                    linha = pesquisarContato(agenda, nome);
                    if (linha == -1)
                    {
                        // Se o contato não existe
                        System.out.print("Contato não Cadastrado! ");
                    }
                    else
                    {
                        // Se o contato existe, exibí-lo
                        exibirContato(agenda, linha);
                    }
                    break;

                // Lista todos os Contatos da agenda
                case 4:
                    listarAgenda(agenda);
                    break;

                // Exclui um registro digitado pelo usuário
                case 5:

                    System.out.println("------------ EXCLUINDO (PESQUISE O CONTATO): ");
                    System.out.print("Digite o nome.........:");
                    nome = entrada.next();
                    apagarContato(agenda, nome);
                    break;

                case 6:
                    System.out.print("OBRIGADO POR UTILIZAR A NOSSA AGENDA :)");

            }
            System.out.println();
        }while(opcao != 6);
    }
}