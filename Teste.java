import java.util.List;

class Teste
{
    public static void main(String[] args)
    {
        Turma turma = Turma.obter(4);
        turma.deletar();
    }
}