import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.util.List;
import java.util.ArrayList;

class Principal
{
	public static void main(String[] argumentos) throws Exception
	{
		String[] selecao = { "Cadastrar", "Excluir", "Alterar", "Consultar" };
		
		String[] opcoes = { "Selecionar", "Sair" };

		JComboBox<String> combo = new JComboBox<String>(selecao);
		int opcao;
		do
		{
			opcao = JOptionPane.showOptionDialog(null, combo, "Qual operação deseja realizar?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

			if (opcao == 1) 
			    break;	
			
			switch(combo.getSelectedItem().toString())
			{
				case "Cadastrar":
				{
					cadastrar();
					break;
				}
				
				case "Excluir":
				{
					excluir();
					break;
				}
				case "Alterar":
				{
					alterar();
					break;
				}
				case "Consultar":
				{
					//consultar();
					break;
				}
			}

		}while(opcao == 0);
		

	}

	// private static void cadastrar()
	// {
	// 	String[] selecao = { "Turma", "Aluno", "Disciplina", "Professor", "Diário" };
		
	// 	String[] opcoes = { "Selecionar", "Sair" };

	// 	JComboBox<String> combo = new JComboBox<String>(selecao);
	// 	int opcao;

	// 	do
	// 	{
	// 		opcao = JOptionPane.showOptionDialog(null, combo, "Qual cadastro deseja realizar?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

	// 		if (opcao == 1) 
	// 		    break;	
			
	// 		switch(combo.getSelectedItem().toString())
	// 		{
	// 			case "Turma":
	// 			{
	// 				try
	// 				{
	// 					int ano = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ano letivo: ", "Turma", JOptionPane.QUESTION_MESSAGE));
	// 					char semestre = JOptionPane.showInputDialog(null, "Informe o semestre letivo: ", "Turma", JOptionPane.QUESTION_MESSAGE).charAt(0);
	// 					Turma turma = new Turma(ano, semestre);
	// 					turma.salvar();
	// 				}
	// 				catch(Exception e)
	// 				{
 //                        System.out.println(e);
	// 				}
					
	// 				break;
	// 			}
	// 			case "Aluno":
	// 			{

	// 				try
	// 				{
 //                        Turma turma;
 //                        List<Turma> turmas = Turma.listar();
 //                        String[] nomesTurmas = new String[turmas.size()];
 //                        for (int i = 0; i < turmas.size(); i++)
 //                            nomesTurmas[i] = turmas.get(i).getAno() + " " + turmas.get(i).getSemestre();
 //                        JComboBox comboTurmas = new JComboBox<String>(nomesTurmas);

 //                        int opcaoAluno;

	// 					String matricula = JOptionPane.showInputDialog(null, "Informe a matricula do aluno: ", "Aluno", JOptionPane.QUESTION_MESSAGE);
	// 					String nome  = JOptionPane.showInputDialog(null, "Informe o nome do aluno: ", "Aluno", JOptionPane.QUESTION_MESSAGE);
                        
 //                        opcaoAluno = JOptionPane.showOptionDialog(null, comboTurmas, "Escolha uma turma:", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
 //                        if (opcaoAluno == 1)
 //                            break;
 //                        turma = turmas.get(comboTurmas.getSelectedIndex());

	// 					Aluno aluno = new Aluno(matricula, nome, turma);
	// 					aluno.salvar();

	// 				}catch(Exception e)
	// 				{
 //                        System.out.println(e);
	// 				}
	// 				break;
	// 			}
	// 			case "Disciplina":
	// 			{
	// 				try
	// 				{
	// 					String nome  = JOptionPane.showInputDialog(null, "Informe o nome da disciplina: ", "Disciplina", JOptionPane.QUESTION_MESSAGE);
	// 					Disciplina disciplina = new Disciplina(nome);
	// 					disciplina.salvar();
	// 				}catch(Exception e)
	// 				{
	// 					System.out.println(e);
	// 				}
	// 				break;
	// 			}
	// 			case "Professor":
	// 			{
	// 				try
	// 				{
	// 					String matricula = JOptionPane.showInputDialog(null, "Informe a matricula do professor: ", "Professor", JOptionPane.QUESTION_MESSAGE);
	// 					String nome  = JOptionPane.showInputDialog(null, "Informe o nome do professor: ", "Professor", JOptionPane.QUESTION_MESSAGE);
	// 					Professor professor = new Professor(matricula, nome);
	// 					professor.salvar();

	// 				}
	// 				catch(Exception e)
	// 				{
	// 					System.out.println(e);
	// 				}
	// 				break;
	// 			}
	// 			case "Diário":
	// 			{
	// 				try
	// 				{
 //                        int opcaoDiario, cargaHoraria;
 //                        Disciplina disciplina;
 //                        Turma turma;
 //                        Professor professor;
 //                        Diario diario;

 //                        List<Disciplina> disciplinas = Disciplina.listar();
 //                        List<Turma> turmas = Turma.listar();
 //                        List<Professor> professores = Professor.listar(disciplinas);

 //                        int disciplinaSelecionada, turmaSelecionada, professorSelecionado;
 //                        JComboBox<String> comboDisciplinas, comboTurma, comboProfessor;

 //                        String[] nomesDisciplinas = new String[disciplinas.size()];
 //                        String[] nomesTurmas = new String[turmas.size()];
 //                        String[] nomesProfessores = new String[professores.size()];

 //                        for (int i = 0; i < disciplinas.size(); i++)
 //                            nomesDisciplinas[i] = disciplinas.get(i).getNome();

 //                        comboDisciplinas = new JComboBox<String>(nomesDisciplinas);
 //                        opcaoDiario = JOptionPane.showOptionDialog(null, comboDisciplinas, "Escolha uma disciplina:", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
 //                        if (opcaoDiario == 1)
 //                            break;
 //                        disciplinaSelecionada = comboDisciplinas.getSelectedIndex();
                        
                        
 //                        for (int i = 0; i < turmas.size(); i++)
 //                            nomesTurmas[i] = turmas.get(i).getAno() + " " + turmas.get(i).getSemestre();

 //                        comboTurma = new JComboBox<String>(nomesTurmas);
 //                        opcaoDiario = JOptionPane.showOptionDialog(null, comboTurma, "Escolha uma turma", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
 //                        if (opcaoDiario == 1)
 //                            break;
 //                        turmaSelecionada = comboTurma.getSelectedIndex();

 //                        for (int i = 0; i < professores.size(); i++)
 //                            nomesProfessores[i] = professores.get(i).getNome();
 //                        comboProfessor = new JComboBox<String>(nomesProfessores);
 //                        opcaoDiario = JOptionPane.showOptionDialog(null, comboProfessor, "Escolha um professor", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
 //                        if (opcaoDiario == 1)
 //                            break;
 //                        professorSelecionado = comboProfessor.getSelectedIndex();

 //                        cargaHoraria = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual é a carga horaria?", "Diario", JOptionPane.QUESTION_MESSAGE));

 //                        disciplina = disciplinas.get(disciplinaSelecionada);
 //                        turma = turmas.get(turmaSelecionada);
 //                        professor = professores.get(professorSelecionado);

 //                        diario = new Diario(disciplina, professor, turma, cargaHoraria);

 //                        diario.salvar();
                        
	// 				}catch(Exception e)
	// 				{
	// 					System.out.println(e);
	// 				}
	// 				break;
	// 			}
	// 		}

	// 	}while(opcao == 0);
	// }

	private static void excluir()
	{
        String[] selecao = { "Turma", "Aluno", "Disciplina", "Professor", "Diário" };
		
		String[] opcoes = { "Selecionar", "Sair" };

		JComboBox<String> combo = new JComboBox<String>(selecao);
        int opcao, opcao2;
        
        do
        {
            opcao = JOptionPane.showOptionDialog(null, combo, "O que deseja excluir?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

			if (opcao == 1) 
			    break;	
			
            switch(combo.getSelectedItem().toString())
            {
                case "Turma":
                {
                    try
                    {
                        Turma turma;
                        List<Turma> turmas = Turma.listar();
                        String[] nomesTurmas = new String[turmas.size()];

                        for (int i = 0; i < turmas.size(); i++)
                            nomesTurmas[i] = turmas.get(i).getAno() + " " + turmas.get(i).getSemestre();

                        JComboBox comboTurmas = new JComboBox<String>(nomesTurmas);

                        opcao2 = JOptionPane.showOptionDialog(null, comboTurmas, "Escolha uma turma", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
                        if (opcao2 == 1)
                            break;

                        turma = turmas.get(comboTurmas.getSelectedIndex());
                        turma.deletar();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                }

                case "Aluno":
                {
                    try
                    {
                        Aluno aluno;
                        List<Turma> turmas = Turma.listar();
                        List<Aluno> alunos = Aluno.listar(turmas);
                        String[] nomesAlunos = new String[alunos.size()];

                        for (int i = 0; i < alunos.size(); i++)
                            nomesAlunos[i] = alunos.get(i).getNome();

                        JComboBox comboAlunos = new JComboBox<String>(nomesAlunos);

                        opcao2 = JOptionPane.showOptionDialog(null, comboAlunos, "Escolha um aluno", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
                        if (opcao2 == 1)
                            break;

                        aluno = alunos.get(comboAlunos.getSelectedIndex());
                        aluno.deletar();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                }

                case "Disciplina":
                {
                    try
                    {
                        Disciplina disciplina;
                        List<Disciplina> disciplinas = Disciplina.listar();
                        String[] nomesDisciplinas = new String[disciplinas.size()];

                        for (int i = 0; i < disciplinas.size(); i++)
                            nomesDisciplinas[i] = disciplinas.get(i).getNome();

                        JComboBox comboDisciplinas = new JComboBox<String>(nomesDisciplinas);

                        opcao2 = JOptionPane.showOptionDialog(null, comboDisciplinas, "Escolha uma disciplina", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
                        if (opcao2 == 1)
                            break;

                        disciplina = disciplinas.get(comboDisciplinas.getSelectedIndex());
                        disciplina.deletar();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                }

                case "Professor":
                {
                    try
                    {
                        Professor professor;
                        List<Disciplina> disciplinas = Disciplina.listar();
                        List<Professor> professores = Professor.listar(disciplinas);
                        String[] nomesProfessores = new String[professores.size()];

                        for (int i = 0; i < professores.size(); i++)
                            nomesProfessores[i] = professores.get(i).getNome();

                        JComboBox comboProfessores = new JComboBox<String>(nomesProfessores);

                        opcao2 = JOptionPane.showOptionDialog(null, comboProfessores, "Escolha um professor", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
                        if (opcao2 == 1)
                            break;

                        professor = professores.get(comboProfessores.getSelectedIndex());
                        professor.deletar();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                }

                case "Diário":
                {
                    try
                    {
                        Diario diario;

                        List<Disciplina> disciplinas = Disciplina.listar();
                        List<Turma> turmas = Turma.listar();
                        List<Professor> professores = Professor.listar(disciplinas);

                        List<Diario> diarios = Diario.listar(professores, disciplinas, turmas);
                        String[] nomesDiarios = new String[diarios.size()];

                        for (int i = 0; i < diarios.size(); i++)
                            nomesDiarios[i] = diarios.get(i).getTurma().getAno() + " " + diarios.get(i).getTurma().getSemestre() + " - " + diarios.get(i).getDisciplina().getNome();

                        JComboBox comboDiarios = new JComboBox<String>(nomesDiarios);

                        opcao2 = JOptionPane.showOptionDialog(null, comboDiarios, "Escolha um diario", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
                        if (opcao2 == 1)
                            break;

                        diario = diarios.get(comboDiarios.getSelectedIndex());
                        diario.deletar();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                }
            }
        } while (opcao == 0);
	}

	// private static void alterar()
	// {
	// 	String[] selecao = { "Cadastrar", "Excluir", "Alterar", "Consultar" };
		
	// 	String[] opcoes = { "Selecionar", "Sair" };

	// 	JComboBox<String> combo = new JComboBox<String>(selecao);
	// 	int opcao;
	// 	do
	// 	{
	// 		opcao = JOptionPane.showOptionDialog(null, combo, "Qual operação deseja realizar?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

	// 		if (opcao == 1) 
	// 		    break;	
			
	// 		switch(combo.getSelectedItem().toString())
	// 		{
	// 			case "Cadastrar":
	// 			{
	// 				cadastrar();
	// 				break;
	// 			}
				
	// 			case "Excluir":
	// 			{
	// 				excluir();
	// 				break;
	// 			}
	// 			case "Alterar":
	// 			{
	// 				alterar();
	// 				break;
	// 			}
	// 			case "Consultar":
	// 			{
	// 				//consultar();
	// 				break;
	// 			}
	// 		}

	// 	}while(opcao == 0);
		

	//}

	private static void cadastrar()
	{
		String[] selecao = { "Turma", "Aluno", "Disciplina", "Professor", "Diário" };
		
		String[] opcoes = { "Selecionar", "Sair" };

		JComboBox<String> combo = new JComboBox<String>(selecao);
		int opcao;

		do
		{
			opcao = JOptionPane.showOptionDialog(null, combo, "Qual cadastro deseja realizar?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

			if (opcao == 1) 
			    break;	
			
			switch(combo.getSelectedItem().toString())
			{
				case "Turma":
				{
					try
					{
						int ano = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ano letivo: ", "Turma", JOptionPane.QUESTION_MESSAGE));
						char semestre = JOptionPane.showInputDialog(null, "Informe o semestre letivo: ", "Turma", JOptionPane.QUESTION_MESSAGE).charAt(0);
						Turma turma = new Turma(ano, semestre);
						turma.salvar();
					}
					catch(Exception e)
					{
                        System.out.println(e);
					}
					
					break;
				}
				case "Aluno":
				{

					try
					{
                        Turma turma;
                        List<Turma> turmas = Turma.listar();
                        String[] nomesTurmas = new String[turmas.size()];
                        for (int i = 0; i < turmas.size(); i++)
                            nomesTurmas[i] = turmas.get(i).getAno() + " " + turmas.get(i).getSemestre();
                        JComboBox comboTurmas = new JComboBox<String>(nomesTurmas);

                        int opcaoAluno;

						String matricula = JOptionPane.showInputDialog(null, "Informe a matricula do aluno: ", "Aluno", JOptionPane.QUESTION_MESSAGE);
						String nome  = JOptionPane.showInputDialog(null, "Informe o nome do aluno: ", "Aluno", JOptionPane.QUESTION_MESSAGE);
                        
                        opcaoAluno = JOptionPane.showOptionDialog(null, comboTurmas, "Escolha uma turma:", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
                        if (opcaoAluno == 1)
                            break;
                        turma = turmas.get(comboTurmas.getSelectedIndex());

						Aluno aluno = new Aluno(matricula, nome, turma);
						aluno.salvar();

					}catch(Exception e)
					{
                        System.out.println(e);
					}
					break;
				}
				case "Disciplina":
				{
					try
					{
						String nome  = JOptionPane.showInputDialog(null, "Informe o nome da disciplina: ", "Disciplina", JOptionPane.QUESTION_MESSAGE);
						Disciplina disciplina = new Disciplina(nome);
						disciplina.salvar();
					}catch(Exception e)
					{
						System.out.println(e);
					}
					break;
				}
				case "Professor":
				{
					try
					{
						String matricula = JOptionPane.showInputDialog(null, "Informe a matricula do professor: ", "Professor", JOptionPane.QUESTION_MESSAGE);
						String nome  = JOptionPane.showInputDialog(null, "Informe o nome do professor: ", "Professor", JOptionPane.QUESTION_MESSAGE);
						Professor professor = new Professor(matricula, nome);
						professor.salvar();

					}
					catch(Exception e)
					{
						System.out.println(e);
					}
					break;
				}
				case "Diário":
				{
					try
					{
                        int opcaoDiario, cargaHoraria;
                        Disciplina disciplina;
                        Turma turma;
                        Professor professor;
                        Diario diario;

                        List<Disciplina> disciplinas = Disciplina.listar();
                        List<Turma> turmas = Turma.listar();
                        List<Professor> professores = Professor.listar(disciplinas);

                        int disciplinaSelecionada, turmaSelecionada, professorSelecionado;
                        JComboBox<String> comboDisciplinas, comboTurma, comboProfessor;

                        String[] nomesDisciplinas = new String[disciplinas.size()];
                        String[] nomesTurmas = new String[turmas.size()];
                        String[] nomesProfessores = new String[professores.size()];

                        for (int i = 0; i < disciplinas.size(); i++)
                            nomesDisciplinas[i] = disciplinas.get(i).getNome();

                        comboDisciplinas = new JComboBox<String>(nomesDisciplinas);
                        opcaoDiario = JOptionPane.showOptionDialog(null, comboDisciplinas, "Escolha uma disciplina:", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
                        if (opcaoDiario == 1)
                            break;
                        disciplinaSelecionada = comboDisciplinas.getSelectedIndex();
                        
                        
                        for (int i = 0; i < turmas.size(); i++)
                            nomesTurmas[i] = turmas.get(i).getAno() + " " + turmas.get(i).getSemestre();

                        comboTurma = new JComboBox<String>(nomesTurmas);
                        opcaoDiario = JOptionPane.showOptionDialog(null, comboTurma, "Escolha uma turma", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
                        if (opcaoDiario == 1)
                            break;
                        turmaSelecionada = comboTurma.getSelectedIndex();

                        for (int i = 0; i < professores.size(); i++)
                            nomesProfessores[i] = professores.get(i).getNome();
                        comboProfessor = new JComboBox<String>(nomesProfessores);
                        opcaoDiario = JOptionPane.showOptionDialog(null, comboProfessor, "Escolha um professor", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
                        if (opcaoDiario == 1)
                            break;
                        professorSelecionado = comboProfessor.getSelectedIndex();

                        cargaHoraria = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual é a carga horaria?", "Diario", JOptionPane.QUESTION_MESSAGE));

                        disciplina = disciplinas.get(disciplinaSelecionada);
                        turma = turmas.get(turmaSelecionada);
                        professor = professores.get(professorSelecionado);

                        diario = new Diario(disciplina, professor, turma, cargaHoraria);

                        diario.salvar();
                        
					}catch(Exception e)
					{
						System.out.println(e);
					}
					break;
				}
			}

		}while(opcao == 0);
	}

	private static void alterar()
	{
        String[] selecao = { "Turma", "Aluno", "Disciplina", "Professor", "Diário" };
		
		String[] opcoes = { "Selecionar", "Voltar" };

		JComboBox<String> combo = new JComboBox<String>(selecao);
        int opcao, opcao2;
        
        do
        {
            opcao = JOptionPane.showOptionDialog(null, combo, "O que deseja alterar?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

			if (opcao == 1) 
			    break;	
			
            switch(combo.getSelectedItem().toString())
            {
                case "Turma":
                {
                    try
                    {


                    	Turma turma = selecionarTurma();

                        String[] selecaoAtributos = { "Semestre", "Ano"};

						JComboBox<String> comboAtributos = new JComboBox<String>(selecaoAtributos);
				        //int opcao;
						opcao = 1;
				        opcao = JOptionPane.showOptionDialog(null, comboAtributos, "Qual componente deseja alterar?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
				        if (opcao == 1)
	                        break;

				         switch(comboAtributos.getSelectedItem().toString())
							{
								case "Semestre":
								{
									turma.setSemestre(JOptionPane.showInputDialog(null, "Informe Codigo do semestre da turma:", "Turma", JOptionPane.QUESTION_MESSAGE).charAt(0));
									break;	
								}

								case "Ano":
								{
									int i = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ano da turma: ", "Turma", JOptionPane.QUESTION_MESSAGE));
									turma.setAno(i);
									break;	
								}
								default:
								{
									JOptionPane.showMessageDialog(null, "a");
								}

							}

                       	
                        turma.atualizar();

                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                }

                case "Aluno":
                {
                    try
                    {
                       
                        Aluno aluno = selecionarAluno();
                        String[] selecaoAtributos = { "Nome", "Turma"};
		
						JComboBox<String> comboAtributos = new JComboBox<String>(selecaoAtributos);
				        
						opcao = 1;
				        opcao = JOptionPane.showOptionDialog(null, comboAtributos, "Qual componente deseja alterar?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
				        if (opcao == 1)
	                        break;

				         switch(comboAtributos.getSelectedItem().toString())
							{
								case "Nome":
								{
									aluno.setNome(JOptionPane.showInputDialog(null, "Informe o nome do aluno:", "Aluno", JOptionPane.QUESTION_MESSAGE));
									//aluno.turma = aluno.getTurma();
									break;	
								}

								case "Turma":
								{
									aluno.setTurma(selecionarTurma()); 
									break;	
								}
								default:
								{
									JOptionPane.showMessageDialog(null,"a");
								}

							}

                       	
                        aluno.atualizar();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                }

                case "Disciplina":
                {
                    try
                    {
                        Disciplina disciplina = selecionarDisciplina();
                        String[] selecaoAtributos = { "Nome"};
		
						JComboBox<String> comboAtributos = new JComboBox<String>(selecaoAtributos);
				        
						opcao = 1;
				        opcao = JOptionPane.showOptionDialog(null, comboAtributos, "Qual componente deseja alterar?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
				        if (opcao == 1)
	                        break;

				         switch(comboAtributos.getSelectedItem().toString())
							{
								case "Nome":
								{
									disciplina.setNome(JOptionPane.showInputDialog(null, "Informe o nome da Disciplina:", "Disciplina", JOptionPane.QUESTION_MESSAGE));
									
									break;	
								}
								default:
								{
									JOptionPane.showMessageDialog(null,"a");
								}

							}

                       	
                        disciplina.atualizar();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                }

                case "Professor":
                {
                    try
                    {
                        Professor professor = selecionarProfessor();
                        String[] selecaoAtributos = { "Nome"};
		
						JComboBox<String> comboAtributos = new JComboBox<String>(selecaoAtributos);
				        
						opcao = 1;
				        opcao = JOptionPane.showOptionDialog(null, comboAtributos, "Qual componente deseja alterar?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
				        if (opcao == 1)
	                        break;

				         switch(comboAtributos.getSelectedItem().toString())
							{
								case "Nome":
								{
									professor.setNome(JOptionPane.showInputDialog(null, "Informe o nome da Professor:", "Professor", JOptionPane.QUESTION_MESSAGE));
									
									break;	
								}
								default:
								{
									JOptionPane.showMessageDialog(null,"a");
								}

							}

                       	
                        professor.atualizar();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                }

                case "Diário":
                {
                    try
                    {
                        Diario diario = selecionarDiario();
                        String[] selecaoAtributos = { "Turma", "Professor", "Disciplina", "Carga Horaria"};
		
						JComboBox<String> comboAtributos = new JComboBox<String>(selecaoAtributos);
				        
						opcao = 1;
				        opcao = JOptionPane.showOptionDialog(null, comboAtributos, "Qual componente deseja alterar?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
				        if (opcao == 1)
	                        break;

				         switch(comboAtributos.getSelectedItem().toString())
							{
								case "Turma":
								{
									diario.setTurma(selecionarTurma()); 
									break;	
								}
								case "Professor":
								{
									diario.setProfessor(selecionarProfessor()); 
									break;	
								}
								case "Disciplina":
								{
									diario.setDisciplina(selecionarDisciplina()); 
									break;	
								}
								case "Carga Horaria":
								{
									int i = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a carga horaria do diário: ", "Diario", JOptionPane.QUESTION_MESSAGE));
									diario.setCargaHoraria(i);
									break;	
								}
								default:
								{
									JOptionPane.showMessageDialog(null,"a");
								}

							}

                       	
                        diario.atualizar();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;
                }
            }
        } while (opcao == 0);
	}

    /*
	private static void consultar()
	{

		String[] selecao = { "Turma", "Aluno", "Disciplina", "Professor", "Diário" };
		
		String[] opcoes = { "Selecionar", "Sair" };

		JComboBox<String> combo = new JComboBox<String>(selecao);
		int opcao;

		do
		{
			opcao = JOptionPane.showOptionDialog(null, combo, "Qual cadastro deseja realizar?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

			if (opcao == 1) 
			break;	
			
			switch(combo.getSelectedItem().toString())
			{
				case "Turma":
				{
					try
					{
						String listaTurmas = "";
						List<Turma> turmas = new ArrayList<Turma>();
						turmas = Turma.listar();
						
						//System.out.println(""+alunos.size());

						for (int i = 0; i < turmas.size(); i++ ) 
						{
							listaTurmas += "\n";
							listaTurmas += "ID: " + turmas.get(i).getId() + "\n";
							listaTurmas += "Semestre: " + turmas.get(i).getSemestre() + "\n";
							listaTurmas += "Ano: " + turmas.get(i).getAno()+ "\n";
							
						}

						JOptionPane.showMessageDialog(null, listaTurmas, "Turmas", JOptionPane.INFORMATION_MESSAGE, null);
					}
					catch(Exception e)
					{

					}
					
					break;
				}
				case "Aluno":
				{

					try
					{

						String stringAluno = "";
						List<Aluno> alunos = new ArrayList<Aluno>();
						alunos = Aluno.listar();
						
						//System.out.println(""+alunos.size());

						for (int i = 0; i < alunos.size(); i++ ) 
						{
							stringAluno += "\n";
							stringAluno += "matricula: " + alunos.get(i).getMatricula() + "\n";
							stringAluno += "Nome: " + alunos.get(i).getNome() + "\n";
							stringAluno += "Turma: " + alunos.get(i).getTurma().getAno();
							stringAluno += alunos.get(i).getTurma().getSemestre()+ "\n";
						}

						JOptionPane.showMessageDialog(null, stringAluno, "Alunos", JOptionPane.INFORMATION_MESSAGE, null);



					}
					catch(Exception e)
					{

					}
					break;
				}
				case "Disciplina":
				{
					try
					{
						String listaDisciplinas = "";
						List<Disciplina> disciplinas = new ArrayList<Disciplina>();
						disciplinas = Disciplina.listar();

						
						//System.out.println(""+alunos.size());

						for (int i = 0; i < disciplinas.size(); i++ ) 
						{
							listaDisciplinas += "\n";
							listaDisciplinas += "ID: " + disciplinas.get(i).getId() + "\n";
							listaDisciplinas += "Nome: " + disciplinas.get(i).getNome() + "\n";
							
						}

						JOptionPane.showMessageDialog(null, listaDisciplinas, "Disciplinas", JOptionPane.INFORMATION_MESSAGE, null);

					}catch(Exception e)
					{
						System.out.println(e);
					}
					break;
				}
				case "Professor":
				{
					try
					{
						String listaProfessores = "";
						List<Professor> professores = new ArrayList<Professor>();
						professores = Professor.listar();

						
						//System.out.println(""+alunos.size());

						for (int i = 0; i < professores.size(); i++ ) 
						{
							listaProfessores += "\n";
							listaProfessores += "Matricula: " + professores.get(i).getMatricula() + "\n";
							listaProfessores += "nome: " + professores.get(i).getNome() + "\n";
							
						}

						JOptionPane.showMessageDialog(null, listaProfessores, "Professores", JOptionPane.INFORMATION_MESSAGE, null);

					}
					catch(Exception e)
					{
						
					}
					break;
				}
				case "Diário":
				{
					try
					{

					}catch(Exception e)
					{
						
					}
					break;
				}
			}

		}while(opcao == 0);
    }
    */


    public static Turma selecionarTurma()
    {
    	Turma turma = null;	
    	
        List<Turma> turmas = Turma.listar();

        String[] nomeTurmas = new String[turmas.size()];
		
		String[] opcoes = { "Selecionar", "Voltar" };

        for (int i = 0; i < turmas.size(); i++)
            nomeTurmas[i] = turmas.get(i).getAno() + " " + turmas.get(i).getSemestre();

        JComboBox comboTurma = new JComboBox<String>(nomeTurmas);

        int opcao = 1;
        opcao = JOptionPane.showOptionDialog(null, comboTurma, "Escolha uma turma", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
        if (opcao == 1)
            return(null);	

        turma = turmas.get(comboTurma.getSelectedIndex());

        return (turma);
    }

    public static Aluno selecionarAluno()
    {
    	Aluno aluno = null;	

    	List<Turma> turmas = Turma.listar();

        List<Aluno> alunos = Aluno.listar(turmas);

        String[] nomeAlunos = new String[alunos.size()];
		
		String[] opcoes = { "Selecionar", "Voltar" };

        for (int i = 0; i < alunos.size(); i++)
            nomeAlunos[i] = alunos.get(i).getNome();

        JComboBox comboAluno = new JComboBox<String>(nomeAlunos);

        int opcao = 1;
        opcao = JOptionPane.showOptionDialog(null, comboAluno, "Escolha um aluno", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
        if (opcao == 1)
           return(null);
        
        aluno = alunos.get(comboAluno.getSelectedIndex());

        return(aluno);	
    }

    public static Disciplina selecionarDisciplina()
    {
    	Disciplina disciplina = null;	

        List<Disciplina> disciplinas = Disciplina.listar();

        String[] nomeDisciplinas = new String[disciplinas.size()];

		
		String[] opcoes = { "Selecionar", "Voltar" };

        for (int i = 0; i < disciplinas.size(); i++)
            nomeDisciplinas[i] = disciplinas.get(i).getNome();

        JComboBox comboDisciplinas = new JComboBox<String>(nomeDisciplinas);

        int opcao = 1;
        opcao = JOptionPane.showOptionDialog(null, comboDisciplinas, "Escolha uma disciplina", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
        if (opcao == 1)
           return(null);
        
        disciplina = disciplinas.get(comboDisciplinas.getSelectedIndex());

        return(disciplina);	
    }

    public static Professor selecionarProfessor()
    {
    	Professor professor = null;	

        List<Disciplina> disciplinas = Disciplina.listar();

        List<Professor> professores = Professor.listar(disciplinas);

        String[] nomeProfessores = new String[professores.size()];

		
		String[] opcoes = { "Selecionar", "Voltar" };

        for (int i = 0; i < professores.size(); i++)
            nomeProfessores[i] = professores.get(i).getNome();

        JComboBox comboProfessores = new JComboBox<String>(nomeProfessores);

        int opcao = 1;
        opcao = JOptionPane.showOptionDialog(null, comboProfessores, "Escolha uma Professor", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
        if (opcao == 1)
           return(null);
        
        professor = professores.get(comboProfessores.getSelectedIndex());

        return(professor);	
    }

    public static Diario selecionarDiario()
    {
    	Diario diario = null;	

    	List<Turma> turmas = Turma.listar();

    	//List<Aluno> alunos = Aluno.listar(turmas);

        List<Disciplina> disciplinas = Disciplina.listar();

        List<Professor> professores = Professor.listar(disciplinas);

        List<Diario> diarios = Diario.listar(professores, disciplinas, turmas);

        String[] nomeDiarios = new String[diarios.size()];

		
		String[] opcoes = { "Selecionar", "Voltar" };

        for (int i = 0; i < diarios.size(); i++)
            nomeDiarios[i] = diarios.get(i).getTurma().getAno() +" "+ diarios.get(i).getTurma().getSemestre() +" - "+ diarios.get(i).getDisciplina().getNome();

        JComboBox comboDiarios = new JComboBox<String>(nomeDiarios);

        int opcao = 1;
        opcao = JOptionPane.showOptionDialog(null, comboDiarios, "Escolha uma Professor", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
        if (opcao == 1)
           return(null);
        
        diario = diarios.get(comboDiarios.getSelectedIndex());

        return(diario);	
    }
}