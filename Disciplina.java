import java.util.List;
import java.util.ArrayList;

class Disciplina
{
	private int id;
	private String nome;

	// Construtores

	public Disciplina(String nome)
	{
		this(-1, nome);
	}

	public Disciplina(int id, String nome)
	{
		this.id = id;
		this.nome = nome;
	}

	// Getters

	public int getId()
	{
		return id;
	}

	public String getNome()
	{
		return nome;
	}

	// Setters

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	// Métodos

	public static List<Disciplina> listar()
	{
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		List<String> resultado;
		int qtdeCampos = 2;
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");

		resultado = bd.listar("SELECT DISC_ID_DISCIPLINA, DISC_NM_DISCIPLINA FROM DISCIPLINA;");

		for (int i = 0; i < resultado.size(); i += qtdeCampos)
			disciplinas.add(new Disciplina(Integer.parseInt(resultado.get(i)), resultado.get(i+1)));

		return disciplinas;
	}

	public boolean salvar()
	{
		String sql = "INSERT INTO DISCIPLINA (DISC_NM_DISCIPLINA) VALUE ('" + nome + "');";
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
		String sql = "UPDATE DISCIPLINA SET DISC_NM_DISCIPLINA = '" + nome + "' WHERE DISC_ID_DISCIPLINA = " + id + ";";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");
		return bd.salvar(sql);
	}

	public boolean deletar()
	{
		String sqlRelacao = "DELETE FROM DISCIPLINA_PROFESSOR WHERE DISC_ID_DISCIPLINA = " + id + ";";
		String sqlDisciplina = "DELETE FROM DISCIPLINA WHERE DISC_ID_DISCIPLINA = " + id + ";";
		InteracaoBD bd = new InteracaoBD("SISTEMA_ACADEMICO", "root", "root");

		bd.salvar(sqlRelacao);
		return bd.salvar(sqlDisciplina);
	}
}