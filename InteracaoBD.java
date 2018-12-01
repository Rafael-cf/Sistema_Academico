import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

class InteracaoBD
{
	private Connection conexao;
	private Properties propriedades;

	public InteracaoBD(String database, String usuario, String senha)
	{
		propriedades = new Properties();
		propriedades.put("user", usuario);
		propriedades.put("password", senha);

		try
		{
			Class classe = Class.forName("com.mysql.cj.jdbc.Driver");
			classe.newInstance();
			conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1/" + database + "?useSSL=false", propriedades);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public List<String> listar(String query)
	{
		List<String> resultado = new ArrayList<String>();

		try
		{
			Statement consulta = conexao.createStatement();
			ResultSet conjuntoDados = consulta.executeQuery(query);

			while (conjuntoDados.next())
				for (int i = 1; i <= conjuntoDados.getMetaData().getColumnCount(); ++i)
					resultado.add(conjuntoDados.getString(i));
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

		return resultado;
	}

	public boolean salvar(String query)
	{
		try
		{
			Statement consulta = conexao.createStatement();
			consulta.execute(query);
			return true;
		}
		catch (Exception e)
		{
			System.out.println(e);
			return false;
		}

	}

	public int getUltimoId()
	{
		try
		{
			Statement consulta = conexao.createStatement();
			ResultSet resultado = consulta.executeQuery("SELECT LAST_INSERT_ID()");
			resultado.next();
			return resultado.getInt(1);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return -1;
		}
	}
}