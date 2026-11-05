package jogador;

public class JogadorAutomatico implements Jogador
{
    // define as 8 linhas possiveis de vitoria (3 linhas, 3 colunas e 2 diagonais)
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

    private Marca     marca     = null;
    private Tabuleiro tabDoJogo = null;

    // guarda o tabuleiro e sorteia a marca do jogador
    public JogadorAutomatico (Tabuleiro tab) throws Exception
    {
        if (tab == null)
            throw new Exception ("Tabuleiro ausente");

        tabDoJogo = tab;

        if ((int)(Math.random()*2) == 0)
            marca = new Marca ('X');
        else
            marca = new Marca ('O');
    }

    // guarda o tabuleiro e a marca recebidos
    public JogadorAutomatico (Tabuleiro tab, Marca mrc) throws Exception
    {
        if (tab == null)
            throw new Exception ("Tabuleiro ausente");

        if (mrc == null)
            throw new Exception ("Marca ausente");

        tabDoJogo = tab;
        marca     = mrc;
    }

    // retorna a marca do jogador
    public Marca getMarca ()
    {
        return this.marca;
    }

    // monta a marca do adversario, oposta a do jogador
    private Marca getMarcaAdversario () throws Exception
    {
        return new Marca (this.marca.getSimbolo()=='X' ? 'O' : 'X');
    }

    // se a linha tem a quantidade pedida de marcas do alvo e de posicoes livres, joga na primeira livre
    private boolean jogarNaLinha (int[][] linha, Marca alvo, int qtdMarcas, int qtdLivres) throws Exception
    {
        int   marcas        = 0;
        int   livres        = 0;
        int[] primeiraLivre = null;

        for (int[] pos : linha)
        {
            if (!tabDoJogo.haMarcaNaPosicao(pos[0], pos[1]))
            {
                livres++;
                if (primeiraLivre == null)
                    primeiraLivre = pos;
            }
            else
                if (tabDoJogo.getMarcaNaPosicao(pos[0], pos[1]).equals(alvo))
                    marcas++;
        }

        if (marcas == qtdMarcas && livres == qtdLivres)
        {
            tabDoJogo.setMarcaNaPosicao(this.marca, primeiraLivre[0], primeiraLivre[1]);
            return true;
        }

        return false;
    }

    // percorre as 8 linhas tentando jogar conforme o criterio informado
    private boolean tentarEmTodasAsLinhas (Marca alvo, int qtdMarcas, int qtdLivres) throws Exception
    {
        for (int[][] linha : LINHAS)
            if (jogarNaLinha(linha, alvo, qtdMarcas, qtdLivres))
                return true;

        return false;
    }

    // joga na primeira posicao livre do tabuleiro
    private void jogarEmQualquerLugar () throws Exception
    {
        for (int lin = 0; lin < 3; lin++)
            for (int col = 0; col < 3; col++)
                if (!tabDoJogo.haMarcaNaPosicao(lin, col))
                {
                    tabDoJogo.setMarcaNaPosicao(this.marca, lin, col);
                    return;
                }
    }

    // tenta ganhar, depois atrapalhar o adversario, depois preparar jogada futura e por fim joga onde houver lugar
    public void facaSuaJogada ()
    {
        try
        {
            if (tentarEmTodasAsLinhas(this.marca, 2, 1))
                return;

            if (tentarEmTodasAsLinhas(getMarcaAdversario(), 2, 1))
                return;

            if (tentarEmTodasAsLinhas(this.marca, 1, 2))
                return;

            jogarEmQualquerLugar();
        }
        catch (Exception erro)
        {}
    }
}