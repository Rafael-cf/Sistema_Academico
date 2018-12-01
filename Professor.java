import java.util.List;
import java.util.ArrayList;

class Professor
{
	private String nome;
	private String matricula;

	private List<Disciplina> disciplinas;

	// Construtoress

	public Professor(String matricula, String nome)
	{
		this(matricula, nome, new ArrayList<Disciplina>());
	}

	public Professor(String matricula, String nome, List<Disciplina> disciplinas)
	{
		this.matricula = matricula;
		this.nome = nome;
		this.disciplinas = disciplinas;
	}

	// Getters

	public String getNome()
	{
		return nome;
	}

	public String getMatricula()
	{
		return matricula;
	}

	public Disciplina getDisciplina(int posicao)
	{
		return disciplinas.get(posicao);
	}

	public int getQtdeDisciplinas()
	{
		return disciplinas.size();
	}

	// Setters

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public void setMatricula(String matricula)
	{
		this.matricula = matricula;
	}

	// Métodos

	public void inserirDisciplina(Disciplina disciplina)
	{
		disciplinas.add(disciplina);
	}

	public boolean removerDisciplina(Disciplina disciplina)
	{
		return disciplinas.remove(disciplina);
	}

	public static List<Professor> listar(List<Disciplina> disciplinas)
	{
		List<Professor> professores = new ArrayList<Professor>();
		Professor novoProfessor;
		List<String> resultadoProfessores;
		List<String> resultadoProfessorDisciplinas;
		int qtdeCampos = 2;
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");		

		resultadoProfessores = bd.listar("SELECT PROF_NU_MATRICULA, PROF_NM_PROFESSOR FROM PROFESSOR;");
		resultadoProfessorDisciplinas = bd.listar("SELECT PROF_NU_MATRICULA, DISC_ID_DISCIPLINA FROM DISCIPLINA_PROFESSOR;");

		for (int i = 0; i < resultadoProfessores.size(); i += qtdeCampos)
		{
			novoProfessor = new Professor(resultadoProfessores.get(i), resultadoProfessores.get(i + 1));
			
			for (int j = 0; j < resultadoProfessorDisciplinas.size(); j += 2)
			{
				if (resultadoProfessorDisciplinas.get(j).equals(novoProfessor.getMatricula()))
				{
					for (Disciplina disciplina : disciplinas)
					{
						if (disciplina.getId() == Integer.parseInt(resultadoProfessorDisciplinas.get(j + 1)))
						{
							novoProfessor.inserirDisciplina(disciplina);
							break;
						}
					}
				}
			}

			professores.add(novoProfessor);
		}

		return professores;
	}

	public boolean salvar()
	{
		String sql = "INSERT INTO PROFESSOR (PROF_NU_MATRICULA, PROF_NM_PROFESSOR) VALUES (" + matricula + ", '" + nome + "');";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		return bd.salvar(sql);
	}

	public boolean atualizar()
	{
		String sql = "UPDATE PROFESSOR SET PROF_NM_PROFESSOR = '" + nome + "' WHERE PROF_NU_MATRICULA = " + matricula + ";";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		return bd.salvar(sql);
	}

	public void salvarDisciplinas()
	{
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		String query;
		List<Integer> relacoesSalvar = new ArrayList<Integer>();
		List<Integer> relacoesDeletar = new ArrayList<Integer>();

		List<String> resultado = bd.listar("SELECT DISC_ID_DISCIPLINA FROM DISCIPLINA_PROFESSOR WHERE PROF_NU_MATRICULA = " + matricula + ";");
		List<Integer> idDisciplinas = new ArrayList<Integer>();

		for (String id : resultado)
			idDisciplinas.add(Integer.parseInt(id));

		for (Disciplina disciplina : disciplinas)
		{
			boolean relacaoCadastrada = false;
			for (int idDisciplina : idDisciplinas)
				if (disciplina.getId() == idDisciplina)
				{
					relacaoCadastrada = true;
					break;
				}

			if (!relacaoCadastrada)
				relacoesSalvar.add(disciplina.getId());
		}

		for (int idDisciplina : idDisciplinas)
		{
			boolean relacaoExiste = false;
			for (Disciplina disciplina : disciplinas)
				if (disciplina.getId() == idDisciplina)
				{
					relacaoExiste = true;
					break;
				}

			if (!relacaoExiste)
				relacoesDeletar.add(idDisciplina);
		}

		if (!relacoesSalvar.isEmpty())
		{
			query = "INSERT INTO DISCIPLINA_PROFESSOR (DISC_ID_DISCIPLINA, PROF_NU_MATRICULA) VALUES";
			for (int salvar : relacoesSalvar)
				query += " (" + salvar + ", " + matricula + "),";
			query = query.substring(0, query.length() - 1) + ";";
			bd.salvar(query);
		}
		
		if (!relacoesDeletar.isEmpty())
		{
			query = "";
			for (int deletar : relacoesDeletar)
				query += "DELETE FROM DISCIPLINA_PROFESSOR WHERE DISC_ID_DISCIPLINA = " + deletar + " AND PROF_NU_MATRICULA = " + matricula + "; ";
			bd.salvar(query);
		}
	}

	public boolean deletar()
	{
		String sqlDisciplina = "DELETE FROM DISCIPLINA_PROFESSOR WHERE PROF_NU_MATRICULA = " + matricula + ";";
		String sqlDiario = "DELETE FROM PROFESSOR_DISCIPLINA_TURMA WHERE PROF_NU_MATRICULA = " + matricula + ";";
		String sqlProfessor = "DELETE FROM PROFESSOR WHERE PROF_NU_MATRICULA = " + matricula + ";";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		bd.salvar(sqlDisciplina);
		bd.salvar(sqlDiario);
		return bd.salvar(sqlProfessor);
	}
}