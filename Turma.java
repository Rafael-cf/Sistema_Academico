import java.util.List;
import java.util.ArrayList;

class Turma
{
	private int id;
	private int ano;
	private char semestre;

	// Construtores

	public Turma(int ano, char semestre)
	{
		this(-1, ano, semestre);
	}

	public Turma(int id, int ano, char semestre)
	{
		this.id = id;
		this.ano = ano;
		this.semestre = semestre;
	}

	// Getters

	public int getId()
	{
		return id;
	}

	public int getAno()
	{
		return ano;
	}

	public char getSemestre()
	{
		return semestre;
	}

	public void setSemestre(char semestre)
	{
		this.semestre = semestre;
	}

	public void setAno(int ano)
	{
		this.ano = ano;
	}

	// Métodos
	
	public static List<Turma> listar()
	{
		List<Turma> turmas = new ArrayList<Turma>();
		List<String> resultado;
		int qtdeCampos = 3;
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");

		resultado = bd.listar("SELECT TURM_ID_TURMA, TURM_NU_ANO, TURM_CD_SEMESTRE FROM TURMA;");

		for (int i = 0; i < resultado.size(); i += qtdeCampos)
			turmas.add(new Turma(Integer.parseInt(resultado.get(i)), Integer.parseInt(resultado.get(i + 1)), resultado.get(i + 2).charAt(0)));

		return turmas;
	}

	public static Turma obter(int id)
	{
		List<String> resultado;
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");

		resultado = bd.listar("SELECT TURM_NU_ANO, TURM_CD_SEMESTRE FROM TURMA WHERE TURM_ID_TURMA = " + id + ";");

		return new Turma(id, Integer.parseInt(resultado.get(0)), resultado.get(1).charAt(0));
	}

	public boolean salvar()
	{
		String sql = "INSERT INTO TURMA (TURM_CD_SEMESTRE, TURM_NU_ANO) VALUES ('" + semestre + "', " + ano + ");";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		boolean salvou = bd.salvar(sql);

		if (salvou && id == -1)
		{
			int novoId = bd.getUltimoId();
			id = novoId;
		}

		return salvou;
	}

	public boolean atualizar()
	{
		String sql = "UPDATE TURMA SET TURM_CD_SEMESTRE = '" + semestre + "', TURM_NU_ANO = " + ano + " WHERE TURM_ID_TURMA = " + id + ";";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		return bd.salvar(sql);
	}

	public boolean deletar()
	{
		String sqlAlunos = "DELETE FROM ALUNO WHERE TURM_ID_TURMA = " + id + ";";
		String sqlTurma = "DELETE FROM TURMA WHERE TURM_ID_TURMA = " + id + ";";

		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		bd.salvar(sqlAlunos);
		return bd.salvar(sqlTurma);
	}
}