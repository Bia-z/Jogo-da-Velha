package jogador;

public class Tabuleiro implements Cloneable
{
    private Marca matriz [][] = new Marca [3][3];

    // cria um tabuleiro vazio
    public Tabuleiro ()
    {}

    // valida se linha e coluna estao entre 0 e 2
    private void validar (int lin, int col) throws Exception
    {
        if (lin < 0 || lin > 2)
            throw new Exception ("Linha invalida");

        if (col < 0 || col > 2)
            throw new Exception ("Coluna invalida");
    }

    // retorna true se ha marca na posicao informada
    public boolean haMarcaNaPosicao (int lin, int col) throws Exception
    {
        validar (lin, col);

        return this.matriz[lin][col] != null;
    }

    // retorna a marca da posicao informada
    public Marca getMarcaNaPosicao (int lin, int col) throws Exception
    {
        validar (lin, col);

        return this.matriz[lin][col];
    }

    // coloca a marca na posicao informada
    public void setMarcaNaPosicao (Marca mrc, int lin, int col) throws Exception
    {
        validar (lin, col);

        if (mrc == null)
            throw new Exception ("Marca ausente");

        this.matriz[lin][col] = mrc;
    }

    // monta o tabuleiro formatado para exibicao
    @Override
    public String toString ()
    {
        String ret = "    1   2   3\n";

        for (int lin = 0; lin < 3; lin++)
        {
            ret += (lin + 1) + "  ";

            for (int col = 0; col < 3; col++)
            {
                ret += " " + (this.matriz[lin][col] == null ? " " : this.matriz[lin][col].toString());

                if (col < 2)
                    ret += " |";
            }

            ret += "\n";

            if (lin < 2)
                ret += "   ---+---+---\n";
        }

        return ret;
    }

    // compara todas as posicoes com as do outro tabuleiro
    @Override
    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Tabuleiro outro = (Tabuleiro) obj;

        for (int lin = 0; lin < 3; lin++)
            for (int col = 0; col < 3; col++)
            {
                if (this.matriz[lin][col] == null)
                {
                    if (outro.matriz[lin][col] != null)
                        return false;
                }
                else
                    if (!this.matriz[lin][col].equals(outro.matriz[lin][col]))
                        return false;
            }

        return true;
    }

    // calcula o hashcode a partir de todas as posicoes
    @Override
    public int hashCode ()
    {
        int ret = 666;

        for (int lin = 0; lin < 3; lin++)
            for (int col = 0; col < 3; col++)
                ret = 7 * ret + (this.matriz[lin][col] == null ? 0 : this.matriz[lin][col].hashCode());

        if (ret < 0)
            ret = -ret;

        return ret;
    }

    // copia as marcas do tabuleiro modelo
    public Tabuleiro (Tabuleiro modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Modelo ausente");

        for (int lin = 0; lin < 3; lin++)
            for (int col = 0; col < 3; col++)
                this.matriz[lin][col] = modelo.matriz[lin][col];
    }

    // retorna uma copia deste tabuleiro usando o construtor de copia
    @Override
    public Object clone ()
    {
        Tabuleiro ret = null;

        try
        {
            ret = new Tabuleiro (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}