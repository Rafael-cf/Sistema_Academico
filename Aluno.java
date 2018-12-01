import java.util.List;
import java.util.ArrayList;

class Aluno
{
	private String matricula;
	private String nome;

	private List<Diario> diarios;
	private Turma turma;

	// Construtores

	public Aluno(String matricula, String nome, Turma turma)
	{
		this(matricula, nome, turma, new ArrayList<Diario>());
	}

	public Aluno(String matricula, String nome, Turma turma, List<Diario> diarios) throws NullPointerException
	{
		if (turma == null)
			throw new NullPointerException("turma não pode ser null");

		this.matricula = matricula;
		this.nome = nome;
		this.turma = turma;
		this.diarios = diarios;
	}

	// Getters

	public String getMatricula()
	{
		return matricula;
	}

	public String getNome()
	{
		return nome;
	}

	public Turma getTurma()
	{
		return turma;
	}

	// Setters

	public void setMatricula(String matricula)
	{
		this.matricula = matricula;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public void setTurma(Turma turma)
	{
		this.turma = turma;
	}

	// Métodos

	public void inserirDiario(Diario diario)
	{
		diarios.add(diario);
	}

	public static List<Aluno> listar(List<Turma> turmas)
	{
		List<Aluno> alunos = new ArrayList<Aluno>();
		List<String> resultado;
		int qtdeCampos = 3;
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");

		resultado = bd.listar("SELECT ALUN_ID_MATRICULA, ALUN_NM_ALUNO, TURM_ID_TURMA FROM ALUNO");

		for (int i = 0; i < resultado.size(); i += qtdeCampos)
		{
			for (Turma turma : turmas)
			{
				if (turma.getId() == Integer.parseInt(resultado.get(i + 2)))
				{
					alunos.add(new Aluno(resultado.get(i), resultado.get(i + 1), turma));
					break;
				}
			}
		}

		return alunos;
	}

	public boolean salvar()
	{
		String sql = "INSERT INTO ALUNO(ALUN_ID_MATRICULA, ALUN_NM_ALUNO, TURM_ID_TURMA) VALUES ('" + matricula + "', '" + nome + "', " + turma.getId() + ");";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		return bd.salvar(sql);
	}

	public boolean atualizar()
	{
		String sql = "UPDATE ALUNO SET ALUN_NM_ALUNO = '" + nome + "', TURM_ID_TURMA = " + turma.getId() + " WHERE ALUN_ID_MATRICULA = '" + matricula + "';";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		return bd.salvar(sql);
	}

	public boolean deletar()
	{
		String sql = "DELETE FROM ALUNO WHERE ALUN_ID_MATRICULA = '" + matricula + "';";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		return bd.salvar(sql);
	}
}