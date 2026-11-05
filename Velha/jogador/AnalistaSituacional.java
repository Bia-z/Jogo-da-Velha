package jogador;

public class AnalistaSituacional
{
    // define as 8 linhas possiveis de vitoria
    private static final int[][][] LINHAS =
    {
        {{0,0},{0,1},{0,2}},
        {{1,0},{1,1},{1,2}},
        {{2,0},{2,1},{2,2}},
        {{0,0},{1,0},{2,0}},
        {{0,1},{1,1},{2,1}},
        {{0,2},{1,2},{2,2}},
        {{0,0},{1,1},{2,2}},
        {{0,2},{1,1},{2,0}}
    };

    private Tabuleiro tabDoJogo;

    // valida e guarda o tabuleiro
    public AnalistaSituacional (Tabuleiro tab) throws Exception
    {
        if (tab == null)
            throw new Exception ("Tabuleiro ausente");

        this.tabDoJogo = tab;
    }

    // procura uma linha com tres marcas iguais e retorna a marca, ou null se nao houver vencedor
    public Marca getMarcaDoVencedor ()
    {
        try
        {
            for (int[][] linha : LINHAS)
            {
                Marca a = tabDoJogo.getMarcaNaPosicao(linha[0][0], linha[0][1]);
                Marca b = tabDoJogo.getMarcaNaPosicao(linha[1][0], linha[1][1]);
                Marca c = tabDoJogo.getMarcaNaPosicao(linha[2][0], linha[2][1]);

                if (a != null && a.equals(b) && b.equals(c))
                    return a;
            }
        }
        catch (Exception erro)
        {}

        return null;
    }

    // retorna true se nenhuma posicao do tabuleiro esta livre
    private boolean tabuleiroCheio ()
    {
        try
        {
            for (int lin = 0; lin < 3; lin++)
                for (int col = 0; col < 3; col++)
                    if (!tabDoJogo.haMarcaNaPosicao(lin, col))
                        return false;
        }
        catch (Exception erro)
        {}

        return true;
    }

    // retorna true se ha vencedor ou se o tabuleiro esta cheio
    public boolean fimDeJogo ()
    {
        if (getMarcaDoVencedor() != null)
            return true;

        if (tabuleiroCheio())
            return true;

        return false;
    }

    // retorna o tabuleiro analisado em forma de texto
    @Override
    public String toString ()
    {
        return "Analista do tabuleiro:\n" + this.tabDoJogo;
    }

    // compara o tabuleiro analisado com o do outro analista
    @Override
    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        AnalistaSituacional outro = (AnalistaSituacional) obj;

        return this.tabDoJogo.equals(outro.tabDoJogo);
    }

    // calcula o hashcode a partir do tabuleiro
    @Override
    public int hashCode ()
    {
        int ret = 666;

        ret = 7 * ret + this.tabDoJogo.hashCode();

        if (ret < 0)
            ret = -ret;

        return ret;
    }
}