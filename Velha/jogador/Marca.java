package jogador;

public class Marca
{
    private char simbolo;

    // valida o simbolo (X ou O) e o guarda
    public Marca (char smb) throws Exception
    {
        if (smb != 'X' && smb != 'O')
            throw new Exception ("Simbolo invalido");

        this.simbolo = smb;
    }

    // retorna o simbolo
    public char getSimbolo ()
    {
        return this.simbolo;
    }

    // retorna o simbolo como texto
    @Override
    public String toString ()
    {
        return ""+simbolo;
    }

    // compara o simbolo com o do outro objeto
    @Override
    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Marca outra = (Marca) obj;

        return this.simbolo == outra.simbolo;
    }

    // calcula o hashcode a partir do simbolo
    @Override
    public int hashCode ()
    {
        int ret = 666;

        ret = 7 * ret + Character.valueOf(this.simbolo).hashCode();

        if (ret < 0)
            ret = -ret;

        return ret;
    }
}