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
					consultar();
					break;
				}
			}

		}while(opcao == 0);
		

	}

	private static void cadastrar()
	{
		String[] selecao = { "Turma", "Aluno", "Disciplina", "Professor", "Diário", "Aluno no diário" };
		
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

				case "Aluno no diário":
				{
					Diario diario = null;
					Aluno aluno = null;
					float nota;

					int opcaoInserir;

					List<Disciplina> disciplinas = Disciplina.listar();
					List<Professor> professores = Professor.listar(disciplinas);
					List<Turma> turmas = Turma.listar();
					List<Aluno> alunos = Aluno.listar(turmas);
					List<Diario> diarios = Diario.listar(professores, disciplinas, turmas, alunos);

					String[] nomesDiarios = new String[diarios.size()];
					List<String> nomesAlunos = new ArrayList<String>();

					for (int i = 0; i < diarios.size(); i++)
						nomesDiarios[i] = diarios.get(i).getTurma().getAno() + " " + diarios.get(i).getTurma().getSemestre() + " - " + diarios.get(i).getDisciplina().getNome();

					JComboBox comboDiarios = new JComboBox<String>(nomesDiarios);
					opcaoInserir = JOptionPane.showOptionDialog(null, comboDiarios, "Escolha um diario", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
					if (opcaoInserir == 1)
						break;

					diario = diarios.get(comboDiarios.getSelectedIndex());

					for (Aluno a : alunos)
						nomesAlunos.add(a.getNome());

					JComboBox comboAlunos = new JComboBox<String>(nomesAlunos.toArray(new String[0]));
					opcaoInserir = JOptionPane.showOptionDialog(null, comboAlunos, "Escolha um aluno", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
					if (opcaoInserir == 1)
						break;
					aluno = alunos.get(comboAlunos.getSelectedIndex());

					nota = Float.parseFloat(JOptionPane.showInputDialog(null, "Insira a nota do aluno:", "Inserir aluno em diario", JOptionPane.QUESTION_MESSAGE));

					diario.getAlunos().put(aluno, nota);

					diario.salvarAlunos();
				}
			}

		}while(opcao == 0);
	}

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

	private static void consultar()
	{

		String[] selecao = { "Turma", "Aluno", "Disciplina", "Professor", "Diário", "Aluno com maior nota", "Aluno com menor nota", "Média de notas" };
		
		String[] opcoes = { "Selecionar", "Sair" };

		JComboBox<String> combo = new JComboBox<String>(selecao);
		int opcao;

		do
		{
			opcao = JOptionPane.showOptionDialog(null, combo, "Quais informações deseja ver?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

			if (opcao == 1) 
			break;	
			
			switch(combo.getSelectedItem().toString())
			{
				case "Turma":
					JOptionPane.showMessageDialog(null, listarTurmas(), "Turmas", JOptionPane.PLAIN_MESSAGE, null);
					break;
				
				case "Aluno":
					JOptionPane.showMessageDialog(null, listarAlunos(), "Alunos", JOptionPane.PLAIN_MESSAGE, null);
					break;
				
				case "Disciplina":
					JOptionPane.showMessageDialog(null, listarDisciplinas(), "Disciplinas", JOptionPane.PLAIN_MESSAGE, null);
					break;

				case "Professor":
					JOptionPane.showMessageDialog(null, listarProfessores(), "Professores", JOptionPane.PLAIN_MESSAGE, null);
					break;

				case "Diário":
					JOptionPane.showMessageDialog(null, listarDiarios(), "Diários", JOptionPane.PLAIN_MESSAGE, null);
					break;

				case "Aluno com maior nota":
					JOptionPane.showMessageDialog(null, exibirAlunosComMaiorNotaPorDiario(), "Alunos com maior nota", JOptionPane.PLAIN_MESSAGE, null);
					break;

				case "Aluno com menor nota":
					JOptionPane.showMessageDialog(null, exibirAlunosComMenorNotaPorDiario(), "Alunos com menor nota", JOptionPane.PLAIN_MESSAGE, null);
					break;

				case "Média de notas":
					JOptionPane.showMessageDialog(null, exibirMediaNotasPorDiario(), "Alunos com menor nota", JOptionPane.PLAIN_MESSAGE, null);
					break;
			}

		}while(opcao == 0);
	}

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
	
	private static String listarDisciplinas()
	{
		List<Disciplina> disciplinas = Disciplina.listar();
		String mensagem = "";

		for (Disciplina disciplina : disciplinas)
			mensagem += disciplina.getId() + " - " + disciplina.getNome() + "\n";

		return mensagem;
	}

	private static String listarProfessores()
	{
		List<Disciplina> disciplinas = Disciplina.listar();
		List<Professor> professores = Professor.listar(disciplinas);

		String mensagem = "";

		for (Professor professor : professores)
		{
			mensagem += professor.getMatricula() + " - " + professor.getNome() + ":\n";
			for (int i = 0; i < professor.getQtdeDisciplinas(); i++)
				mensagem += "   " + professor.getDisciplina(i).getNome() + "\n";
			
			mensagem += "\n";
		}

		return mensagem;
	}

	private static String listarTurmas()
	{
		List<Turma> turmas = Turma.listar();
		String mensagem = "";

		for (Turma turma : turmas)
			mensagem += turma.getId() + " - " + turma.getAno() + " " + turma.getSemestre() + "\n";

		return mensagem;
	}

	private static String listarAlunos()
	{
		List<Turma> turmas = Turma.listar();
		List<Aluno> alunos = Aluno.listar(turmas);
		String mensagem = "";

		for (Aluno aluno : alunos)
			mensagem += aluno.getTurma().getAno() + " " + aluno.getTurma().getSemestre() + " " + aluno.getMatricula() + " - " + aluno.getNome() + "\n";

		return mensagem;
	}

	private static String listarDiarios()
	{
		List<Disciplina> disciplinas = Disciplina.listar();
		List<Professor> professores = Professor.listar(disciplinas);
		List<Turma> turmas = Turma.listar();
		List<Aluno> alunos = Aluno.listar(turmas);
		List<Diario> diarios = Diario.listar(professores, disciplinas, turmas, alunos);

		String mensagem = "";
		
		for (Diario diario : diarios)
		{
			mensagem += diario.getDisciplina().getNome() + " (" + diario.getCargaHoraria() + " hrs)\n   Turma: " + 
				diario.getTurma().getAno() + " " + diario.getTurma().getSemestre() + "\n   Professor: " +
				diario.getProfessor().getNome() + "\nAlunos:\n";

			for (Aluno aluno : diario.getAlunos().keySet())
				mensagem += "   " + aluno.getNome() + " - " + diario.getAlunos().get(aluno) + "\n";
			mensagem += "\n";
		}

		return mensagem;
	}

	private static String exibirAlunosComMaiorNotaPorDiario()
	{
		List<Disciplina> disciplinas = Disciplina.listar();
		List<Professor> professores = Professor.listar(disciplinas);
		List<Turma> turmas = Turma.listar();
		List<Aluno> alunos = Aluno.listar(turmas);
		List<Diario> diarios = Diario.listar(professores, disciplinas, turmas, alunos);

		String mensagem = "Aluno com maior nota em cada diário:\n\n";

		for (Diario diario : diarios)
		{
			mensagem += diario.getTurma().getAno() + " " + diario.getTurma().getSemestre() + " - " + diario.getDisciplina().getNome() + ":\n";

			List<Aluno> alunosMaiorNota = new ArrayList<Aluno>();
			float maiorNota = 0;
			for (Aluno aluno : diario.getAlunos().keySet())
			{
				if (alunosMaiorNota.isEmpty() || diario.getAlunos().get(aluno) > maiorNota)
				{
					alunosMaiorNota.clear();
					alunosMaiorNota.add(aluno);
					maiorNota = diario.getAlunos().get(aluno);
				}
				else if (diario.getAlunos().get(aluno) == maiorNota)
					alunosMaiorNota.add(aluno);
			}

			mensagem += "Maior nota: " + maiorNota + "\n";
			mensagem += "Aluno";

			if (alunosMaiorNota.size() > 1)
				mensagem += "s";

			mensagem += " com a maior nota:\n";

			for (Aluno aluno : alunosMaiorNota)
				mensagem += aluno.getNome() + "\n";

			mensagem += "\n";
		}

		return mensagem;
	}

	private static String exibirAlunosComMenorNotaPorDiario()
	{
		List<Disciplina> disciplinas = Disciplina.listar();
		List<Professor> professores = Professor.listar(disciplinas);
		List<Turma> turmas = Turma.listar();
		List<Aluno> alunos = Aluno.listar(turmas);
		List<Diario> diarios = Diario.listar(professores, disciplinas, turmas, alunos);

		String mensagem = "Aluno com menor nota em cada diário:\n\n";

		for (Diario diario : diarios)
		{
			mensagem += diario.getTurma().getAno() + " " + diario.getTurma().getSemestre() + " - " + diario.getDisciplina().getNome() + ":\n";

			List<Aluno> alunosMenorNota = new ArrayList<Aluno>();
			float menorNota = 0;
			for (Aluno aluno : diario.getAlunos().keySet())
			{

				if (alunosMenorNota.isEmpty() || diario.getAlunos().get(aluno) < menorNota)
				{
					alunosMenorNota.clear();
					alunosMenorNota.add(aluno);
					menorNota = diario.getAlunos().get(aluno);
				}
				else if (diario.getAlunos().get(aluno) == menorNota)
					alunosMenorNota.add(aluno);
			}

			mensagem += "Menor nota: " + menorNota + "\n";
			mensagem += "Aluno";

			if (alunosMenorNota.size() > 1)
				mensagem += "s";

			mensagem += " com a menor nota:\n";

			for (Aluno aluno : alunosMenorNota)
				mensagem += aluno.getNome() + "\n";

			mensagem += "\n";
		}

		return mensagem;
	}

	private static String exibirMediaNotasPorDiario()
	{
		List<Disciplina> disciplinas = Disciplina.listar();
		List<Professor> professores = Professor.listar(disciplinas);
		List<Turma> turmas = Turma.listar();
		List<Aluno> alunos = Aluno.listar(turmas);
		List<Diario> diarios = Diario.listar(professores, disciplinas, turmas, alunos);

		String mensagem = "Nota média em cada diário:\n\n";

		for (Diario diario : diarios)
		{
			mensagem += diario.getTurma().getAno() + " " + diario.getTurma().getSemestre() + " - " + diario.getDisciplina().getNome() + ":\n";

			float media = 0;

			for (Aluno aluno : diario.getAlunos().keySet())
			{
				media += diario.getAlunos().get(aluno);
			}

			media /= diario.getAlunos().size();

			mensagem += "Média: " + media + "\n\n";
		}

		return mensagem;
	}
}