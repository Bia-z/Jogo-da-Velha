package jogador;

import java.io.*;

public class JogadorHumano implements Jogador
{
    private Marca          marca     = null;
    private Tabuleiro      tabDoJogo = null;
    private BufferedReader teclado   = new BufferedReader
                                        (new InputStreamReader
                                        (System.in));

    // guarda o tabuleiro e pede ao usuario que escolha a marca
    public JogadorHumano (Tabuleiro tab) throws Exception
    {
        if (tab == null)
            throw new Exception ("Tabuleiro ausente");

        tabDoJogo = tab;

        while (marca == null)
        {
            System.out.print ("Escolha sua marca (X ou O): ");
            String resposta = lerLinha().trim().toUpperCase();

            if (resposta.length() == 1)
            {
                try
                {
                    marca = new Marca (resposta.charAt(0));
                }
                catch (Exception erro)
                {
                    System.out.println ("Marca invalida");
                }
            }
            else
                System.out.println ("Marca invalida");
        }
    }

    // guarda o tabuleiro e a marca recebidos
    public JogadorHumano (Tabuleiro tab, Marca mrc) throws Exception
    {
        if (tab == null)
            throw new Exception ("Tabuleiro ausente");

        if (mrc == null)
            throw new Exception ("Marca ausente");

        tabDoJogo = tab;
        marca     = mrc;
    }

    // le uma linha do teclado, dando excecao se a entrada terminar
    private String lerLinha () throws Exception
    {
        String linha = teclado.readLine();

        if (linha == null)
            throw new Exception ("Entrada encerrada");

        return linha;
    }

    // le um numero de 1 a 3 do teclado e o converte para indice de 0 a 2, repetindo ate ser valido
    private int lerIndice (String pergunta) throws Exception
    {
        while (true)
        {
            System.out.print (pergunta);

            try
            {
                int valor = Integer.parseInt(lerLinha().trim());

                if (valor >= 1 && valor <= 3)
                    return valor - 1;
            }
            catch (NumberFormatException erro)
            {}

            System.out.println ("Valor invalido, digite de 1 a 3");
        }
    }

    // retorna a marca do jogador
    public Marca getMarca ()
    {
        return this.marca;
    }

    // mostra o tabuleiro e pede linha e coluna ate o usuario escolher uma posicao livre
    public void facaSuaJogada ()
    {
        try
        {
            System.out.println ();
            System.out.println (tabDoJogo);

            while (true)
            {
                int lin = lerIndice ("Linha (1 a 3): ");
                int col = lerIndice ("Coluna (1 a 3): ");

                if (tabDoJogo.haMarcaNaPosicao(lin, col))
                    System.out.println ("Posicao ocupada, escolha outra");
                else
                {
                    tabDoJogo.setMarcaNaPosicao(this.marca, lin, col);
                    return;
                }
            }
        }
        catch (Exception erro)
        {
            System.out.println ("Erro ao ler jogada");
        }
    }

    // mostra o tabuleiro final e a mensagem de vitoria
    public void vcGanhou ()
    {
        System.out.println ();
        System.out.println (tabDoJogo);
        System.out.println ("Voce ganhou!");
    }

    // mostra o tabuleiro final e a mensagem de derrota
    public void vcPerdeu ()
    {
        System.out.println ();
        System.out.println (tabDoJogo);
        System.out.println ("Voce perdeu!");
    }

    // mostra o tabuleiro final e a mensagem de empate
    public void deuVelha ()
    {
        System.out.println ();
        System.out.println (tabDoJogo);
        System.out.println ("Deu velha!");
    }

    // pergunta se deseja jogar de novo e retorna true para S e false para N
    public boolean querMaisUmaPartida ()
    {
        try
        {
            while (true)
            {
                System.out.print ("Deseja jogar novamente (S/N)? ");
                String resposta = lerLinha().trim().toUpperCase();

                if (resposta.equals("S"))
                    return true;

                if (resposta.equals("N"))
                    return false;

                System.out.println ("Resposta invalida");
            }
        }
        catch (Exception erro)
        {
            return false;
        }
    }
}