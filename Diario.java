import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

class Diario
{
	private Map<Aluno, Float> alunos;
	private Professor professor;
	private Disciplina disciplina;
	private Turma turma;
	private int cargaHoraria;

	// Construtores

	public Diario(Disciplina disciplina, Professor professor)
	{
		this(disciplina, professor, null);
	}

	public Diario(Disciplina disciplina, Professor professor, Turma turma)
	{
		this(disciplina, professor, turma, 0);
	}

	public Diario(Disciplina disciplina, Professor professor, Turma turma, int cargaHoraria) throws NullPointerException
	{
		if (disciplina == null)
			throw new NullPointerException("disciplina não pode ser null");

		if (professor == null)
			throw new NullPointerException("professor não pode ser null");

		if (turma == null)
			throw new NullPointerException("turma não pode ser null");

		this.disciplina = disciplina;
		this.professor = professor;
		this.turma = turma;
		this.cargaHoraria = cargaHoraria;
		alunos = new HashMap<Aluno, Float>();
	}

	// Getters

	public Professor getProfessor()
	{
		return professor;
	}

	public Disciplina getDisciplina()
	{
		return disciplina;
	}

	public Turma getTurma()
	{
		return turma;
	}

	public int getCargaHoraria()
	{
		return cargaHoraria;
	}

	public Map<Aluno, Float> getAlunos()
	{
		return alunos;
	}

	// Setters

	public void setProfessor(Professor professor)
	{
		this.professor = professor;
	}

	public void setDisciplina(Disciplina disciplina)
	{
		this.disciplina = disciplina;
	}

	public void setTurma(Turma turma)
	{
		this.turma = turma;
	}

	public void setCargaHoraria(int cargaHoraria)
	{
		this.cargaHoraria = cargaHoraria;
	}

	// Métodos

	public void inserirAluno(Aluno aluno)
	{
		inserirAluno(aluno, 0f);
	}

	public void inserirAluno(Aluno aluno, float nota)
	{
		alunos.put(aluno, nota);
	}

	public static List<Diario> listar(List<Professor> professores, List<Disciplina> disciplinas, List<Turma> turmas)
	{
		List<Diario> diarios = new ArrayList<Diario>();
		Diario novoDiario = null;
		Disciplina disciplinaDiario = null;
		Professor professorDiario = null;
		Turma turmaDiario = null;

		List<String> resultadoDiarios;
		int qtdeCamposDiarios = 4;
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");

		resultadoDiarios = bd.listar("SELECT DISC_ID_DISCIPLINA, PROF_NU_MATRICULA, TURM_ID_TURMA, PDTU_NU_CARGA_HORARIA FROM PROFESSOR_DISCIPLINA_TURMA;");

		for (int i = 0; i < resultadoDiarios.size(); i += qtdeCamposDiarios)
		{
			for (Disciplina disciplina : disciplinas)
				if (disciplina.getId() == Integer.parseInt(resultadoDiarios.get(i)))
				{	
					disciplinaDiario = disciplina;
					break;
				}

			for (Professor professor : professores)
				if (professor.getMatricula().equals(resultadoDiarios.get(i + 1)))
				{
					professorDiario = professor;
					break;
				}

			for (Turma turma : turmas)
				if (turma.getId() == Integer.parseInt(resultadoDiarios.get(i + 2)))
				{
					turmaDiario = turma;
					break;
				}

			novoDiario = new Diario(disciplinaDiario, professorDiario, turmaDiario, Integer.parseInt(resultadoDiarios.get(i + 3)));

			diarios.add(novoDiario);
		}

		return diarios;
	}

	public static List<Diario> listar(List<Professor> professores, List<Disciplina> disciplinas, List<Turma> turmas, List<Aluno> alunos)
	{
		List<Diario> diarios = new ArrayList<Diario>();
		Diario novoDiario = null;
		Disciplina disciplinaDiario = null;
		Professor professorDiario = null;
		Turma turmaDiario = null;

		List<String> resultadoDiarios;
		List<String> resultadoDiarioAlunos;
		int qtdeCamposDiarios = 4;
		int qtdeCamposDiarioAlunos = 5;
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");

		resultadoDiarios = bd.listar("SELECT DISC_ID_DISCIPLINA, PROF_NU_MATRICULA, TURM_ID_TURMA, PDTU_NU_CARGA_HORARIA FROM PROFESSOR_DISCIPLINA_TURMA;");
		resultadoDiarioAlunos = bd.listar("SELECT DISC_ID_DISCIPLINA, PROF_NU_MATRICULA, TURM_ID_TURMA, ALUN_ID_MATRICULA, APDT_VL_NOTA FROM ALUNO_PROFESSOR_DISCIPLINA_TURMA;");

		for (int i = 0; i < resultadoDiarios.size(); i += qtdeCamposDiarios)
		{
			for (Disciplina disciplina : disciplinas)
				if (disciplina.getId() == Integer.parseInt(resultadoDiarios.get(i)))
				{	
					disciplinaDiario = disciplina;
					break;
				}

			for (Professor professor : professores)
				if (professor.getMatricula().equals(resultadoDiarios.get(i + 1)))
				{
					professorDiario = professor;
					break;
				}

			for (Turma turma : turmas)
				if (turma.getId() == Integer.parseInt(resultadoDiarios.get(i + 2)))
				{
					turmaDiario = turma;
					break;
				}

			novoDiario = new Diario(disciplinaDiario, professorDiario, turmaDiario, Integer.parseInt(resultadoDiarios.get(i + 3)));

			for (int j = 0; j < resultadoDiarioAlunos.size(); j += qtdeCamposDiarioAlunos)
			{
				if (Integer.parseInt(resultadoDiarioAlunos.get(j)) == disciplinaDiario.getId() && resultadoDiarioAlunos.get(j + 1).equals(professorDiario.getMatricula()) && Integer.parseInt(resultadoDiarioAlunos.get(j + 2)) == turmaDiario.getId())
				{
					for (Aluno aluno : alunos)
					{
						if (resultadoDiarioAlunos.get(j + 3).equals(aluno.getMatricula()))
						{
							novoDiario.inserirAluno(aluno, Float.parseFloat(resultadoDiarioAlunos.get(j + 4)));
							aluno.inserirDiario(novoDiario);
							break;
						}
					}
				}
			}

			diarios.add(novoDiario);
		}

		return diarios;
	}

	public boolean salvar()
	{
		String sql = "INSERT INTO PROFESSOR_DISCIPLINA_TURMA (TURM_ID_TURMA, DISC_ID_DISCIPLINA, PROF_NU_MATRICULA, PDTU_NU_CARGA_HORARIA) VALUES (" + turma.getId() + ", " + disciplina.getId() + ", " + professor.getMatricula() + ", " + cargaHoraria + ");";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		return bd.salvar(sql);
	}

	public boolean atualizar()
	{
		String sql = "UPDATE PROFESSOR_DISCIPLINA_TURMA SET TURM_ID_TURMA = " + turma.getId() + ", DISC_ID_DISCIPLINA = " + disciplina.getId() + ", PROF_NU_MATRICULA = " + professor.getMatricula() + ", PDTU_NU_CARGA_HORARIA = " + cargaHoraria + " WHERE TURM_ID_TURMA = " + turma.getId() + " AND DISC_ID_DISCIPLINA = " + disciplina.getId() + " AND PROF_NU_MATRICULA = " + professor.getMatricula() + ";";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		return bd.salvar(sql);
	}

	public void salvarAlunos()
	{
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		String query;
		List<Aluno> relacoesSalvar = new ArrayList<Aluno>();
		List<String> relacoesDeletar = new ArrayList<String>();
		List<Aluno> relacoesAtualizar = new ArrayList<Aluno>();

		List<String> matriculasAlunos = bd.listar("SELECT ALUN_ID_MATRICULA FROM ALUNO_PROFESSOR_DISCIPLINA_TURMA WHERE TURM_ID_TURMA = " + turma.getId() + " AND DISC_ID_DISCIPLINA = " + disciplina.getId() + " AND PROF_NU_MATRICULA = " + professor.getMatricula() + ";");

		for (Aluno aluno : alunos.keySet())
		{
			boolean relacaoCadastrada = false;
			for (String matricula : matriculasAlunos)
				if (aluno.getMatricula().equals(matricula))
				{
					relacaoCadastrada = true;
					break;
				}

			if (relacaoCadastrada)
				relacoesAtualizar.add(aluno);
			else
				relacoesSalvar.add(aluno);
		}

		for (String matricula : matriculasAlunos)
		{
			boolean realacaoExiste = false;
			for (Aluno aluno : alunos.keySet())
				if (aluno.getMatricula().equals(matricula))
				{
					realacaoExiste = true;
					break;
				}

			if (!realacaoExiste)
				relacoesDeletar.add(matricula);
		}

		if (!relacoesSalvar.isEmpty())
		{
			query = "INSERT INTO ALUNO_PROFESSOR_DISCIPLINA_TURMA (ALUN_ID_MATRICULA, TURM_ID_TURMA, DISC_ID_DISCIPLINA, PROF_NU_MATRICULA, APDT_VL_NOTA) VALUES";
			for (Aluno aluno : relacoesSalvar)
				query += " ('" + aluno.getMatricula() + "', " + turma.getId() + ", " + disciplina.getId() + ", " + professor.getMatricula() + ", " + alunos.get(aluno) + "),";
			query = query.substring(0, query.length() - 1) + ";";
			bd.salvar(query);
		}

		if (!relacoesDeletar.isEmpty())
		{
			query = "";
			for (String deletar : relacoesDeletar)
				query += "DELETE FROM ALUNO_PROFESSOR_DISCIPLINA_TURMA WHERE ALUN_ID_MATRICULA = '" + deletar + "' AND TURM_ID_TURMA = " + turma.getId() + " AND DISC_ID_DISCIPLINA = " + disciplina.getId() + " AND PROF_NU_MATRICULA = " + professor.getMatricula() + "; ";
			bd.salvar(query);
		}

		if (!relacoesAtualizar.isEmpty())
		{
			query = "";
			for (Aluno aluno : relacoesAtualizar)
				query += "UPDATE ALUNO_PROFESSOR_DISCIPLINA_TURMA SET APDT_VL_NOTA = " + alunos.get(aluno) + " WHERE ALUN_ID_MATRICULA = '" + aluno.getMatricula() + "' AND TURM_ID_TURMA = " + turma.getId() + " AND DISC_ID_DISCIPLINA = " + disciplina.getId() + " AND PROF_NU_MATRICULA = " + professor.getMatricula() + "; ";
			bd.salvar(query);
		}
	}

	public boolean deletar()
	{
		String sqlRelacao = "DELETE FROM ALUNO_PROFESSOR_DISCIPLINA_TURMA WHERE TURM_ID_TURMA = " + turma.getId() + " AND DISC_ID_DISCIPLINA = " + disciplina.getId() + " AND PROF_NU_MATRICULA = " + professor.getMatricula() + "; ";
		String sqlDiario = "DELETE FROM PROFESSOR_DISCIPLINA_TURMA WHERE TURM_ID_TURMA = " + turma.getId() + " AND DISC_ID_DISCIPLINA = " + disciplina.getId() + " AND PROF_NU_MATRICULA = " + professor.getMatricula() + ";";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		bd.salvar(sqlRelacao);
		return bd.salvar(sqlDiario);
	}
}