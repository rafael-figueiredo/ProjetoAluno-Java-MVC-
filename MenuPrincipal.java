package br.com.mvc.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.mvc.dao.LeitorDAO;
import br.com.mvc.dao.LeitorDAO2;
import br.com.mvc.model.Leitor;
import br.com.mvc.model.Leitor2;
import br.com.mvc.util.ConnectionFactory;

public class MenuPrincipal extends JFrame {

	protected static final KeyEvent evt = null;
	private Connection conn; // Conecta com o banco de dados
	private PreparedStatement ps; // Permite executar querys
	private ResultSet rs;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelDadosPessoais;
	private JPanel panelCurso;
	private JPanel panelNotasFaltas;
	private JPanel panelBoletim;
	private JMenuBar menuBar;
	private JMenu mnAluno;
	private JMenu mnNotasFaltas;
	private JMenu mnAjuda;
	private JMenuItem miSalvar;
	private JMenuItem miAlterar;
	private JMenuItem miConsultar;
	private JMenuItem miExcluir;
	private JMenuItem miSair;
	private JSeparator separator;
	private JMenuItem miSalvar2;
	private JMenuItem miAlterar2;
	private JMenuItem miExcluir2;
	private JMenuItem miConsultar2;
	private JMenuItem miSobre;
	private JLabel lblTodosDados;
	private TextArea txtaDados;
	private JLabel lblRGM;
	private JFormattedTextField ftxtRGM;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblDataDeNascimento;
	private JFormattedTextField ftxtData;
	private JLabel lblCpf;
	private JFormattedTextField ftxtCPF;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblEnd;
	private JTextField txtEndereco;
	private JLabel lblMunicipio;
	private JTextField txtMunicipio;
	private JLabel lblUf;
	private JComboBox cmbUF;
	private JLabel lblCelular;
	private JFormattedTextField ftxtCelular;
	private JLabel lblCurso;
	private JLabel lblCampus;
	private JComboBox cmbCurso;
	private JComboBox cmbCampus;
	private JLabel lblPeriodo;
	private JRadioButton radMatutino;
	private JRadioButton radVespertino;
	private JRadioButton radNoturno;
	private final ButtonGroup btnGrupo = new ButtonGroup();
	private JButton btnSalvar;
	private JButton btnAlterar;
	private JButton btnConsultar;
	private JButton btnExcluir;
	private JButton btnNovo;
	private Leitor leitor;
	private LeitorDAO dao;
	private Leitor2 leitor2;
	private LeitorDAO2 dao2;
	private JButton btnListar;
	private JLabel lblDadosPessoais;
	private JLabel lblDadosCurso;
	private JLabel lblNotasFaltas;
	private JLabel lblRgm;
	private JFormattedTextField ftxtRGM2;
	private JTextField txtNome2;
	private JTextField txtCurso;
	private JLabel lblDisciplina;
	private JComboBox cmbDisciplina;
	private JLabel lblSemestre;
	private JComboBox cmbSemestre;
	private JLabel lblNotas;
	private JComboBox cmbNotas;
	private JLabel lblFaltas;
	private JTextField txtFaltas;
	private JButton btnNovo2;
	private JButton btnSalvar2;
	private JButton btnAlterar2;
	private JButton btnConsultar2;
	private JButton btnExcluir2;
	private JButton btnListar2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setResizable(false);
		setFont(new Font("Segoe UI", Font.BOLD, 14));
		setTitle("CADASTRO DE ALUNOS & NOTAS & FALTAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 550, 370);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnAluno = new JMenu("Aluno");
		menuBar.add(mnAluno);

		miSalvar = new JMenuItem("Salvar");
		miSalvar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (ftxtRGM.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Campo RGM Inválido!!");
					} else if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Nome em branco!!");
					} else if (ftxtData.getText().trim().length() <= 9) {
						JOptionPane.showMessageDialog(null, "Campo Data Nascimento Inválido!!");
					} else if (ftxtCPF.getText().trim().length() <= 13) {
						JOptionPane.showMessageDialog(null, "Campo CPF Inválido!!");
					} else if (txtEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Email em branco!!");
					} else if (txtEndereco.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Endereço em branco!!");
					} else if (txtMunicipio.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Municipio em branco!!");
					} else if (cmbUF.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo UF em branco!!");
					} else if (ftxtCelular.getText().trim().length() <= 13) {
						JOptionPane.showMessageDialog(null, "Campo Celular Inválido!!");
					} else if (cmbCurso.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Campo Curso não selecionado!!");
					} else if (cmbCampus.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Campo Campus não selecionado!!");
					} else if (btnGrupo.isSelected(null)) {
						JOptionPane.showMessageDialog(null, "Campo Período não selecionado!!");
					} else {
						// criei o objeto leitor para pegar os dados da tela
						leitor = new Leitor();
						leitor.setRgm(Integer.parseInt(ftxtRGM.getText()));
						leitor.setNome(txtNome.getText());
						leitor.setData(ftxtData.getText());
						leitor.setCpf(ftxtCPF.getText());
						leitor.setEmail(txtEmail.getText());
						leitor.setEndereco(txtEndereco.getText());
						leitor.setMunicipio(txtMunicipio.getText());
						leitor.setUf((String) cmbUF.getSelectedItem());
						leitor.setCelular(ftxtCelular.getText());
						leitor.setCurso((String) cmbCurso.getSelectedItem());
						leitor.setCampus((String) cmbCampus.getSelectedItem());
						if (radMatutino.isSelected()) {
							leitor.setPeriodo("Matutino");
						} else if (radVespertino.isSelected()) {
							leitor.setPeriodo("Vespertino");
						} else {
							leitor.setPeriodo("Noturno");
						}
						// Abrir a conexão
						dao = new LeitorDAO();
						// Salvar
						dao.salvar(leitor);
						JOptionPane.showMessageDialog(null, "Salvo com Sucesso!!!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Salvar!");
				}
			}
		});
		miSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnAluno.add(miSalvar);

		miAlterar = new JMenuItem("Alterar");
		miAlterar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (ftxtRGM.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Campo RGM Inválido!!");
					} else if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Nome em branco!!");
					} else if (ftxtData.getText().trim().length() <= 9) {
						JOptionPane.showMessageDialog(null, "Campo Data Nascimento Inválido!!");
					} else if (ftxtCPF.getText().trim().length() <= 13) {
						JOptionPane.showMessageDialog(null, "Campo CPF Inválido!!");
					} else if (txtEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Email em branco!!");
					} else if (txtEndereco.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Endereço em branco!!");
					} else if (txtMunicipio.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Municipio em branco!!");
					} else if (cmbUF.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo UF em branco!!");
					} else if (ftxtCelular.getText().trim().length() <= 13) {
						JOptionPane.showMessageDialog(null, "Campo Celular Inválido!!");
					} else if (cmbCurso.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Campo Curso não selecionado!!");
					} else if (cmbCampus.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Campo Campus não selecionado!!");
					} else if (btnGrupo.isSelected(null)) {
						JOptionPane.showMessageDialog(null, "Campo Período não selecionado!!");
					} else {
					// criei o objeto leitor para pegar os dados da tela
					// leitor = new Leitor();
					leitor.setRgm(Integer.parseInt(ftxtRGM.getText()));
					leitor.setNome(txtNome.getText());
					leitor.setData(ftxtData.getText());
					leitor.setCpf(ftxtCPF.getText());
					leitor.setEmail(txtEmail.getText());
					leitor.setEndereco(txtEndereco.getText());
					leitor.setMunicipio(txtMunicipio.getText());
					leitor.setUf((String) cmbUF.getSelectedItem());
					leitor.setCelular(ftxtCelular.getText());
					leitor.setCurso((String) cmbCurso.getSelectedItem());
					leitor.setCampus((String) cmbCampus.getSelectedItem());
					if (radMatutino.isSelected()) {
						leitor.setPeriodo("Matutino");
					} else if (radVespertino.isSelected()) {
						leitor.setPeriodo("Vespertino");
					} else {
						leitor.setPeriodo("Noturno");
					}
					// Abrir a conexão
					dao = new LeitorDAO();
					// Alterar
					dao.alterar(leitor);
					JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Alterar");
				}
			}
		});
		mnAluno.add(miAlterar);

		miConsultar = new JMenuItem("Consultar");
		miConsultar.setEnabled(false);
		mnAluno.add(miConsultar);

		miExcluir = new JMenuItem("Excluir");
		miExcluir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (ftxtRGM.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Campo RGM Inválido!!");
					}else if(txtNome.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Informe um RGM Cadastrado para Excluir");
					}
					else {
						// Abrir a conexão
						dao = new LeitorDAO();
						dao2 = new LeitorDAO2();
						// Excluir
						int rgm = Integer.parseInt(ftxtRGM.getText());
						dao.excluir(rgm);
						dao2.excluir2(rgm);
						JOptionPane.showMessageDialog(null, "Excluído com Sucesso!!!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Excluir!!");
				}
			}
		});
		mnAluno.add(miExcluir);

		separator = new JSeparator();
		mnAluno.add(separator);

		miSair = new JMenuItem("Sair");
		miSair.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
			
		});
		miSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_MASK));
		mnAluno.add(miSair);

		mnNotasFaltas = new JMenu("Notas e Faltas");
		menuBar.add(mnNotasFaltas);

		miSalvar2 = new JMenuItem("Salvar");
		miSalvar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (ftxtRGM2.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Valido para Salvar!");
					} else if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Cadastrado para Salvar!");
					} else if (cmbCurso.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Selecione um Curso para Salvar!");
					} else if (cmbDisciplina.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Selecione uma Disciplina para Salvar!");
					} else if (cmbSemestre.getSelectedItem().equals("Escolha")) {
						JOptionPane.showMessageDialog(null, "Selecione um Semestre para Salvar!");
					} else if (cmbNotas.getSelectedItem().equals("Informe")) {
						JOptionPane.showMessageDialog(null, "Informe uma Nota para Salvar!");
					} else if (txtFaltas.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Falta em branco!!");
					} else {
						// criei o objeto leitor para pegar os dados da tela
						Leitor leitor = new Leitor();
						Leitor2 leitor2 = new Leitor2();
						leitor.setRgm(Integer.parseInt(ftxtRGM2.getText()));
						leitor.setNome(txtNome.getText());
						leitor.setCurso((String) cmbCurso.getSelectedItem());
						leitor2.setDisciplina((String) cmbDisciplina.getSelectedItem());
						leitor2.setSemestre((String) cmbSemestre.getSelectedItem());
						double nota = (Double.parseDouble((String) cmbNotas.getSelectedItem()));
						leitor2.setNotas(nota);
						leitor2.setFaltas(Integer.parseInt(txtFaltas.getText()));
						leitor2.setLeitor(leitor);

						// Abrir a conexão
						dao2 = new LeitorDAO2();
						// Salvar
						dao2.salvar2(leitor2);
						JOptionPane.showMessageDialog(null, "Salvo com Sucesso!!!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Salvar");
				}
			}
		});
		mnNotasFaltas.add(miSalvar2);

		miAlterar2 = new JMenuItem("Alterar");
		miAlterar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					conn = ConnectionFactory.getConnection();
					ps = conn.prepareStatement("SELECT * FROM notasfaltas");
					rs = ps.executeQuery();
					
					if (ftxtRGM2.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Valido para Alterar!");
					} else if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Cadastrado para Alterar!");
					} else if (cmbCurso.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Selecione um Curso para salvar!");
					} else if (cmbDisciplina.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Selecione uma Disciplina para salvar!");
					} else if (cmbSemestre.getSelectedItem().equals("Escolha")) {
						JOptionPane.showMessageDialog(null, "Selecione um Semestre para salvar!");
					} else if (cmbNotas.getSelectedItem().equals("Informe")) {
						JOptionPane.showMessageDialog(null, "Informe uma Nota para salvar!");
					} else if (txtFaltas.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Falta em branco!!");
					} else {
						char s = 's';
						while (s == 's') {
							while (rs.next()) {
								int rgm = rs.getInt("RGM");
								int rgm2 = Integer.parseInt(ftxtRGM2.getText());
								if (rgm2 == rgm) {
									Leitor leitor = new Leitor();
									Leitor2 leitor2 = new Leitor2();
									leitor2.setDisciplina((String) cmbDisciplina.getSelectedItem());
									leitor2.setSemestre((String) cmbSemestre.getSelectedItem());
									leitor2.setNotas((Double.parseDouble((String) cmbNotas.getSelectedItem())));
									leitor2.setFaltas(Integer.parseInt(txtFaltas.getText()));
									leitor.setRgm(Integer.parseInt(ftxtRGM2.getText()));

									ps = conn.prepareStatement("UPDATE notasfaltas SET DISCIPLINA=?, SEMESTRE=?, NOTA=?, FALTA=? "
													+ "WHERE RGM=?");
									ps.setString(1, leitor2.getDisciplina());
									ps.setString(2, leitor2.getSemestre());
									ps.setDouble(3, leitor2.getNotas());
									ps.setInt(4, leitor2.getFaltas());
									ps.setInt(5, leitor.getRgm());
									ps.executeUpdate();
									JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!!");
								}
							}
							s = 'n';
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Alterar!");
				}
			}
		});
		miAlterar2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnNotasFaltas.add(miAlterar2);

		miExcluir2 = new JMenuItem("Excluir");
		miExcluir2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (ftxtRGM2.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Valido para Excluir!");
					} else if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Cadastrado para Excluir!");
					} else {
						// Abrir a conexão
						dao = new LeitorDAO();
						dao2 = new LeitorDAO2();
						// Excluir
						int rgm = Integer.parseInt(ftxtRGM2.getText());
						dao.excluir(rgm);
						dao2.excluir2(rgm);
						JOptionPane.showMessageDialog(null, "Excluído com Sucesso!!!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Excluir!");
				}
			}
		});
		mnNotasFaltas.add(miExcluir2);

		miConsultar2 = new JMenuItem("Consultar");
		miConsultar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (ftxtRGM2.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Valido para Consultar!");
					} else {
						try {
							dao = new LeitorDAO();
							int rgm = Integer.parseInt(ftxtRGM2.getText());
							leitor = dao.consultar(rgm);

							txtNome2.setText(leitor.getNome());
							txtCurso.setText(leitor.getCurso());
							String rgm2 = (String.valueOf(leitor.getRgm()));
							ftxtRGM.setText(rgm2);
							ftxtRGM.setEditable(false);
							txtNome.setText(leitor.getNome());
							ftxtData.setText(leitor.getData());
							ftxtCPF.setText(leitor.getCpf());
							txtEmail.setText(leitor.getEmail());
							txtEndereco.setText(leitor.getEndereco());
							txtMunicipio.setText(leitor.getMunicipio());
							cmbUF.setSelectedItem(leitor.getUf());
							ftxtCelular.setText(leitor.getCelular());
							cmbCurso.setSelectedItem(leitor.getCurso());
							cmbCampus.setSelectedItem(leitor.getCampus());
							if (leitor.getPeriodo().equals("Matutino")) {
								radMatutino.setSelected(true);
								radVespertino.setSelected(false);
								radNoturno.setSelected(false);
							} else if (leitor.getPeriodo().equals("Vespertino")) {
								radVespertino.setSelected(true);
								radMatutino.setSelected(false);
								radNoturno.setSelected(false);
							} else {
								radNoturno.setSelected(true);
								radMatutino.setSelected(false);
								radVespertino.setSelected(false);
							}
						} catch (NullPointerException e1) {
							JOptionPane.showMessageDialog(null, "Informe um RGM Cadastrado para Consultar!");
						}
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Consultar!");
				}
			}
		});
		mnNotasFaltas.add(miConsultar2);

		mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		miSobre = new JMenuItem("Sobre");
		miSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Este software foi desenvolvido no intuito de ajudar\nos alunos a fazerem o cadastro de seus dados e \ngravarem suas disciplinas, notas e faltas com o\nobjetivo de consultarem seu boletim.");
			}
		});
		mnAjuda.add(miSobre);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 21, 510, 277);
		contentPane.add(tabbedPane);

		panelDadosPessoais = new JPanel();
		panelDadosPessoais.setBackground(new Color(30, 144, 255));
		tabbedPane.addTab("Dados Pessoais", null, panelDadosPessoais, null);
		panelDadosPessoais.setLayout(null);

		lblRGM = new JLabel("RGM:");
		lblRGM.setForeground(new Color(255, 255, 0));
		lblRGM.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblRGM.setBounds(10, 52, 46, 16);
		panelDadosPessoais.add(lblRGM);

		ftxtRGM = new JFormattedTextField();
		ftxtRGM.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		formatarCampo();
		ftxtRGM.setBounds(55, 52, 126, 20);
		panelDadosPessoais.add(ftxtRGM);

		lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(255, 255, 0));
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNome.setBounds(213, 52, 46, 14);
		panelDadosPessoais.add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNome.setColumns(10);
		txtNome.setBounds(269, 52, 226, 20);
		panelDadosPessoais.add(txtNome);

		lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setForeground(new Color(255, 255, 0));
		lblDataDeNascimento.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDataDeNascimento.setBounds(10, 83, 149, 14);
		panelDadosPessoais.add(lblDataDeNascimento);

		ftxtData = new JFormattedTextField();
		ftxtData.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		formatarCampo2();
		ftxtData.setBounds(160, 82, 99, 20);
		panelDadosPessoais.add(ftxtData);

		lblCpf = new JLabel("CPF:");
		lblCpf.setForeground(new Color(255, 255, 0));
		lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCpf.setBounds(340, 83, 29, 14);
		panelDadosPessoais.add(lblCpf);

		ftxtCPF = new JFormattedTextField();
		ftxtCPF.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		formatarCampo3();
		ftxtCPF.setBounds(376, 83, 119, 20);
		panelDadosPessoais.add(ftxtCPF);

		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 0));
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEmail.setBounds(10, 116, 46, 14);
		panelDadosPessoais.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(85, 113, 410, 20);
		panelDadosPessoais.add(txtEmail);

		lblEnd = new JLabel("Endere\u00E7o:");
		lblEnd.setForeground(new Color(255, 255, 0));
		lblEnd.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEnd.setBounds(10, 147, 82, 14);
		panelDadosPessoais.add(lblEnd);

		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(85, 144, 410, 20);
		panelDadosPessoais.add(txtEndereco);

		lblMunicipio = new JLabel("Munic\u00EDpio:");
		lblMunicipio.setForeground(new Color(255, 255, 0));
		lblMunicipio.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblMunicipio.setBounds(10, 175, 82, 20);
		panelDadosPessoais.add(lblMunicipio);

		txtMunicipio = new JTextField();
		txtMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtMunicipio.setColumns(10);
		txtMunicipio.setBounds(85, 175, 110, 20);
		panelDadosPessoais.add(txtMunicipio);

		lblUf = new JLabel("UF:");
		lblUf.setForeground(new Color(255, 255, 0));
		lblUf.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblUf.setBounds(213, 175, 21, 14);
		panelDadosPessoais.add(lblUf);

		cmbUF = new JComboBox();
		cmbUF.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cmbUF.setModel(new DefaultComboBoxModel(new String[] { "", "SP" }));
		cmbUF.setBounds(244, 174, 46, 22);
		panelDadosPessoais.add(cmbUF);

		lblCelular = new JLabel("Celular:");
		lblCelular.setForeground(new Color(255, 255, 0));
		lblCelular.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCelular.setBounds(320, 175, 58, 20);
		panelDadosPessoais.add(lblCelular);

		ftxtCelular = new JFormattedTextField();
		ftxtCelular.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		formatarCampo4();
		ftxtCelular.setBounds(376, 175, 119, 20);
		panelDadosPessoais.add(ftxtCelular);

		lblDadosPessoais = new JLabel("DADOS PESSOAIS");
		lblDadosPessoais.setForeground(new Color(255, 255, 0));
		lblDadosPessoais.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDadosPessoais.setBounds(185, 15, 126, 14);
		panelDadosPessoais.add(lblDadosPessoais);

		panelCurso = new JPanel();
		panelCurso.setBackground(new Color(30, 144, 255));
		tabbedPane.addTab("Curso", null, panelCurso, null);
		panelCurso.setLayout(null);

		lblCurso = new JLabel("Curso:");
		lblCurso.setForeground(new Color(255, 255, 0));
		lblCurso.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCurso.setBounds(10, 46, 55, 20);
		panelCurso.add(lblCurso);

		lblCampus = new JLabel("Campus:");
		lblCampus.setForeground(new Color(255, 255, 0));
		lblCampus.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCampus.setBounds(10, 89, 65, 20);
		panelCurso.add(lblCampus);

		cmbCurso = new JComboBox();
		cmbCurso.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma op\u00E7\u00E3o", "An\u00E1lise e Desenvolvimento de Sistemas", "Banco de Dados", "Ci\u00EAncia da Computa\u00E7\u00E3o", "Tecnologia da Informa\u00E7\u00E3o", "Redes"}));
		cmbCurso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cmbCurso.setBounds(97, 44, 340, 25);
		panelCurso.add(cmbCurso);

		cmbCampus = new JComboBox();
		cmbCampus.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma op\u00E7\u00E3o", "An\u00E1lia Franco", "Pinheiros", "S\u00E3o Miguel", "Tatuap\u00E9"}));
		cmbCampus.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cmbCampus.setBounds(97, 89, 340, 25);
		panelCurso.add(cmbCampus);

		lblPeriodo = new JLabel("Per\u00EDodo:");
		lblPeriodo.setForeground(new Color(255, 255, 0));
		lblPeriodo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPeriodo.setBounds(10, 130, 65, 14);
		panelCurso.add(lblPeriodo);

		radMatutino = new JRadioButton("Matutino");
		radMatutino.setForeground(new Color(255, 255, 0));
		radMatutino.setBackground(new Color(30, 144, 255));
		btnGrupo.add(radMatutino);
		radMatutino.setFont(new Font("Segoe UI", Font.BOLD, 14));
		radMatutino.setBounds(115, 125, 89, 23);
		panelCurso.add(radMatutino);

		radVespertino = new JRadioButton("Vespertino");
		radVespertino.setForeground(new Color(255, 255, 0));
		radVespertino.setBackground(new Color(30, 144, 255));
		btnGrupo.add(radVespertino);
		radVespertino.setFont(new Font("Segoe UI", Font.BOLD, 14));
		radVespertino.setBounds(220, 125, 104, 23);
		panelCurso.add(radVespertino);

		radNoturno = new JRadioButton("Noturno");
		radNoturno.setForeground(new Color(255, 255, 0));
		radNoturno.setBackground(new Color(30, 144, 255));
		btnGrupo.add(radNoturno);
		radNoturno.setFont(new Font("Segoe UI", Font.BOLD, 14));
		radNoturno.setBounds(340, 125, 89, 23);
		panelCurso.add(radNoturno);

		btnNovo = new JButton("");
		btnNovo.setToolTipText("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ftxtRGM.setText(null);
				txtNome.setText(null);
				ftxtData.setText(null);
				ftxtCPF.setText(null);
				txtEmail.setText(null);
				txtEndereco.setText(null);
				txtMunicipio.setText(null);
				cmbUF.setSelectedIndex(0);
				ftxtCelular.setText(null);
				cmbCurso.setSelectedIndex(0);
				cmbCampus.setSelectedIndex(0);
				btnGrupo.clearSelection();
				ftxtRGM2.setText(null);
				txtNome2.setText(null);
				txtCurso.setText(null);
				cmbDisciplina.setSelectedIndex(0);
				cmbSemestre.setSelectedIndex(0);
				cmbNotas.setSelectedIndex(0);
				txtFaltas.setText(null);
				ftxtRGM.setEditable(true);
			}
		});
		ImageIcon icon1 = new ImageIcon(MenuPrincipal.class.getResource("/imgs/icon1.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(50, 45, 1));
		btnNovo.setIcon(icon1);
		btnNovo.setBounds(10, 172, 65, 51);
		panelCurso.add(btnNovo);

		btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (ftxtRGM.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Campo RGM Inválido!!");
					} else if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Nome em branco!!");
					} else if (ftxtData.getText().trim().length() <= 9) {
						JOptionPane.showMessageDialog(null, "Campo Data Nascimento Inválido!!");
					} else if (ftxtCPF.getText().trim().length() <= 13) {
						JOptionPane.showMessageDialog(null, "Campo CPF Inválido!!");
					} else if (txtEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Email em branco!!");
					} else if (txtEndereco.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Endereço em branco!!");
					} else if (txtMunicipio.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Municipio em branco!!");
					} else if (cmbUF.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo UF em branco!!");
					} else if (ftxtCelular.getText().trim().length() <= 13) {
						JOptionPane.showMessageDialog(null, "Campo Celular Inválido!!");
					} else if (cmbCurso.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Campo Curso não selecionado!!");
					} else if (cmbCampus.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Campo Campus não selecionado!!");
					} else if (btnGrupo.isSelected(null)) {
						JOptionPane.showMessageDialog(null, "Campo Período não selecionado!!");
					} else {
						// criei o objeto leitor para pegar os dados da tela
						leitor = new Leitor();
						leitor.setRgm(Integer.parseInt(ftxtRGM.getText()));
						leitor.setNome(txtNome.getText());
						leitor.setData(ftxtData.getText());
						leitor.setCpf(ftxtCPF.getText());
						leitor.setEmail(txtEmail.getText());
						leitor.setEndereco(txtEndereco.getText());
						leitor.setMunicipio(txtMunicipio.getText());
						leitor.setUf((String) cmbUF.getSelectedItem());
						leitor.setCelular(ftxtCelular.getText());
						leitor.setCurso((String) cmbCurso.getSelectedItem());
						leitor.setCampus((String) cmbCampus.getSelectedItem());
						if (radMatutino.isSelected()) {
							leitor.setPeriodo("Matutino");
						} else if (radVespertino.isSelected()) {
							leitor.setPeriodo("Vespertino");
						} else {
							leitor.setPeriodo("Noturno");
						}
						// Abrir a conexão
						dao = new LeitorDAO();
						// Salvar
						dao.salvar(leitor);
						JOptionPane.showMessageDialog(null, "Salvo com Sucesso!!!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Salvar!");
				}
			}
		});
		btnSalvar.setToolTipText("Salvar");
		ImageIcon icon2 = new ImageIcon(MenuPrincipal.class.getResource("/imgs/icon2.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(50, 45, 1));
		btnSalvar.setIcon(icon2);
		btnSalvar.setBounds(95, 172, 65, 51);
		panelCurso.add(btnSalvar);

		btnAlterar = new JButton("");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (ftxtRGM.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Campo RGM Inválido!!");
					} else if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Nome em branco!!");
					} else if (ftxtData.getText().trim().length() <= 9) {
						JOptionPane.showMessageDialog(null, "Campo Data Nascimento Inválido!!");
					} else if (ftxtCPF.getText().trim().length() <= 13) {
						JOptionPane.showMessageDialog(null, "Campo CPF Inválido!!");
					} else if (txtEmail.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Email em branco!!");
					} else if (txtEndereco.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Endereço em branco!!");
					} else if (txtMunicipio.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Municipio em branco!!");
					} else if (cmbUF.getSelectedItem().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo UF em branco!!");
					} else if (ftxtCelular.getText().trim().length() <= 13) {
						JOptionPane.showMessageDialog(null, "Campo Celular Inválido!!");
					} else if (cmbCurso.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Campo Curso não selecionado!!");
					} else if (cmbCampus.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Campo Campus não selecionado!!");
					} else if (btnGrupo.isSelected(null)) {
						JOptionPane.showMessageDialog(null, "Campo Período não selecionado!!");
					} else {
					// criei o objeto leitor para pegar os dados da tela
					// leitor = new Leitor();
					leitor.setRgm(Integer.parseInt(ftxtRGM.getText()));
					leitor.setNome(txtNome.getText());
					leitor.setData(ftxtData.getText());
					leitor.setCpf(ftxtCPF.getText());
					leitor.setEmail(txtEmail.getText());
					leitor.setEndereco(txtEndereco.getText());
					leitor.setMunicipio(txtMunicipio.getText());
					leitor.setUf((String) cmbUF.getSelectedItem());
					leitor.setCelular(ftxtCelular.getText());
					leitor.setCurso((String) cmbCurso.getSelectedItem());
					leitor.setCampus((String) cmbCampus.getSelectedItem());
					if (radMatutino.isSelected()) {
						leitor.setPeriodo("Matutino");
					} else if (radVespertino.isSelected()) {
						leitor.setPeriodo("Vespertino");
					} else {
						leitor.setPeriodo("Noturno");
					}
					// Abrir a conexão
					dao = new LeitorDAO();
					// Alterar
					dao.alterar(leitor);
					JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Alterar");
				}
			}
		});
		btnAlterar.setToolTipText("Alterar");
		ImageIcon icon3 = new ImageIcon(MenuPrincipal.class.getResource("/imgs/icon3.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(50, 45, 1));
		btnAlterar.setIcon(icon3);
		btnAlterar.setBounds(180, 172, 65, 51);
		panelCurso.add(btnAlterar);

		btnConsultar = new JButton("");
		btnConsultar.setEnabled(false);
		btnConsultar.setToolTipText("Buscar");
		ImageIcon icon4 = new ImageIcon(MenuPrincipal.class.getResource("/imgs/icon4.png"));
		icon4.setImage(icon4.getImage().getScaledInstance(50, 45, 1));
		btnConsultar.setIcon(icon4);
		btnConsultar.setBounds(350, 172, 65, 51);
		panelCurso.add(btnConsultar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (ftxtRGM.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Campo RGM Inválido!!");
					}else if(txtNome.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Informe um RGM Cadastrado para Excluir");
					}
					else {
						// Abrir a conexão
						dao = new LeitorDAO();
						dao2 = new LeitorDAO2();
						// Excluir
						int rgm = Integer.parseInt(ftxtRGM.getText());
						dao.excluir(rgm);
						dao2.excluir2(rgm);
						JOptionPane.showMessageDialog(null, "Excluído com Sucesso!!!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Excluir!!");
				}
			}
		});
		btnExcluir.setToolTipText("Excluir");
		ImageIcon icon5 = new ImageIcon(MenuPrincipal.class.getResource("/imgs/icon5.png"));
		icon5.setImage(icon5.getImage().getScaledInstance(50, 45, 1));
		btnExcluir.setIcon(icon5);
		btnExcluir.setBounds(265, 172, 65, 51);
		panelCurso.add(btnExcluir);

		btnListar = new JButton("");
		btnListar.setEnabled(false);
		ImageIcon icon6 = new ImageIcon(MenuPrincipal.class.getResource("/imgs/icon6.png"));
		icon6.setImage(icon6.getImage().getScaledInstance(50, 45, 1));
		btnListar.setIcon(icon6);
		btnListar.setBounds(430, 172, 65, 51);
		panelCurso.add(btnListar);

		lblDadosCurso = new JLabel("DADOS DO CURSO");
		lblDadosCurso.setForeground(new Color(255, 255, 0));
		lblDadosCurso.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDadosCurso.setBounds(185, 15, 131, 14);
		panelCurso.add(lblDadosCurso);

		panelNotasFaltas = new JPanel();
		panelNotasFaltas.setBackground(new Color(30, 144, 255));
		tabbedPane.addTab("Notas e Faltas", null, panelNotasFaltas, null);
		panelNotasFaltas.setLayout(null);

		lblNotasFaltas = new JLabel("NOTAS E FALTAS");
		lblNotasFaltas.setForeground(new Color(255, 255, 0));
		lblNotasFaltas.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNotasFaltas.setBounds(185, 15, 120, 14);
		panelNotasFaltas.add(lblNotasFaltas);

		lblRgm = new JLabel("RGM:");
		lblRgm.setForeground(Color.YELLOW);
		lblRgm.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblRgm.setBounds(15, 53, 36, 14);
		panelNotasFaltas.add(lblRgm);

		ftxtRGM2 = new JFormattedTextField();
		formatarCampo5();
		ftxtRGM2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		ftxtRGM2.setBounds(61, 50, 75, 20);
		panelNotasFaltas.add(ftxtRGM2);

		txtNome2 = new JTextField();
		txtNome2.setEditable(false);
		txtNome2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(255, 0, 0), new Color(0, 0, 0)));
		txtNome2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtNome2.setColumns(10);
		txtNome2.setBounds(146, 50, 345, 20);
		panelNotasFaltas.add(txtNome2);

		txtCurso = new JTextField();
		txtCurso.setEditable(false);
		txtCurso.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(255, 0, 0), new Color(0, 0, 0)));
		txtCurso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtCurso.setColumns(10);
		txtCurso.setBounds(15, 80, 476, 20);
		panelNotasFaltas.add(txtCurso);

		lblDisciplina = new JLabel("Disciplina:");
		lblDisciplina.setForeground(Color.YELLOW);
		lblDisciplina.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDisciplina.setBounds(15, 114, 68, 14);
		panelNotasFaltas.add(lblDisciplina);

		cmbDisciplina = new JComboBox();
		cmbDisciplina.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma op\u00E7\u00E3o", "An\u00E1lise e Projeto de Sistemas I", "An\u00E1lise e Projeto de Sistemas II", "Aplica\u00E7\u00E3o para Internet", "Banco de Dados", "Big Data", "Computa\u00E7\u00E3o em Nuvem", "Engenharia de Requisitos", "Engenharia de Software", "Fundamentos de Sistemas de Informa\u00E7\u00E3o", "Fundamentos e Estruturas de Dados", "Gest\u00E3o de Tecnologia da Informa\u00E7\u00E3o", "Modelagem de Neg\u00F3cios", "Organiza\u00E7\u00E3o e Arquitetura de Computadores", "Programa\u00E7\u00E3o Orientada Objeto", "Programa\u00E7\u00E3o para Computadores", "Programa\u00E7\u00E3o para Dispositivos M\u00F3veis", "Programa\u00E7\u00E3o Web", "Qualidade de Software", "Redes de Computadores", "Sistemas Cliente/Servidor", "Sistemas Operacionais", "T\u00E9cnicas de Desenvolvimento de Algoritmos", "T\u00E9cnicas de Programa\u00E7\u00E3o", "T\u00F3picos Especiais em Desenvolvimento de Sistemas"}));
		cmbDisciplina.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cmbDisciplina.setBounds(93, 110, 398, 25);
		panelNotasFaltas.add(cmbDisciplina);

		lblSemestre = new JLabel("Semestre:");
		lblSemestre.setForeground(Color.YELLOW);
		lblSemestre.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblSemestre.setBounds(15, 150, 68, 14);
		panelNotasFaltas.add(lblSemestre);

		cmbSemestre = new JComboBox();
		cmbSemestre.setModel(new DefaultComboBoxModel(new String[] { "Escolha", "2020-1", "2020-2", "2021-3", "2021-4",
				"2021-1", "2021-2", "2022-3", "2022-4", "2022-1", "2022-2" }));
		cmbSemestre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cmbSemestre.setBounds(93, 148, 75, 22);
		panelNotasFaltas.add(cmbSemestre);

		lblNotas = new JLabel("Notas:");
		lblNotas.setForeground(Color.YELLOW);
		lblNotas.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNotas.setBounds(220, 150, 43, 14);
		panelNotasFaltas.add(lblNotas);

		cmbNotas = new JComboBox();
		cmbNotas.setModel(new DefaultComboBoxModel(new String[] { "Informe", "0.5", "1.0", "1.5", "2.0", "2.5", "3.0",
				"3.5", "4.0", "4.5", "5.0", "5.5", "6.0", "6.5", "7.0", "7.5", "8.0", "8.5", "9.0", "9.5", "10.0" }));
		cmbNotas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cmbNotas.setBounds(270, 146, 75, 22);
		panelNotasFaltas.add(cmbNotas);

		lblFaltas = new JLabel("Faltas:");
		lblFaltas.setForeground(Color.YELLOW);
		lblFaltas.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblFaltas.setBounds(378, 150, 43, 14);
		panelNotasFaltas.add(lblFaltas);

		txtFaltas = new JTextField();
		txtFaltas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtFaltas.setBounds(424, 149, 67, 20);
		panelNotasFaltas.add(txtFaltas);
		txtFaltas.setColumns(10);

		btnNovo2 = new JButton("");
		btnNovo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ftxtRGM.setText(null);
				txtNome.setText(null);
				ftxtData.setText(null);
				ftxtCPF.setText(null);
				txtEmail.setText(null);
				txtEndereco.setText(null);
				txtMunicipio.setText(null);
				cmbUF.setSelectedIndex(0);
				ftxtCelular.setText(null);
				cmbCurso.setSelectedIndex(0);
				cmbCampus.setSelectedIndex(0);
				btnGrupo.clearSelection();
				ftxtRGM2.setText(null);
				txtNome2.setText(null);
				txtCurso.setText(null);
				cmbDisciplina.setSelectedIndex(0);
				cmbSemestre.setSelectedIndex(0);
				cmbNotas.setSelectedIndex(0);
				txtFaltas.setText(null);
				ftxtRGM.setEditable(true);
			}
		});
		btnNovo2.setToolTipText("Novo");
		ImageIcon icon7 = new ImageIcon(MenuPrincipal.class.getResource("/imgs/icon1.png"));
		icon7.setImage(icon7.getImage().getScaledInstance(50, 45, 1));
		btnNovo2.setIcon(icon7);
		btnNovo2.setBounds(15, 187, 65, 51);
		panelNotasFaltas.add(btnNovo2);

		btnSalvar2 = new JButton("");
		btnSalvar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (ftxtRGM2.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Valido para Salvar!");
					} else if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Cadastrado para Salvar!");
					} else if (cmbCurso.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Selecione um Curso para Salvar!");
					} else if (cmbDisciplina.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Selecione uma Disciplina para Salvar!");
					} else if (cmbSemestre.getSelectedItem().equals("Escolha")) {
						JOptionPane.showMessageDialog(null, "Selecione um Semestre para Salvar!");
					} else if (cmbNotas.getSelectedItem().equals("Informe")) {
						JOptionPane.showMessageDialog(null, "Informe uma Nota para Salvar!");
					} else if (txtFaltas.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Falta em branco!!");
					} else {
						// criei o objeto leitor para pegar os dados da tela
						Leitor leitor = new Leitor();
						Leitor2 leitor2 = new Leitor2();
						leitor.setRgm(Integer.parseInt(ftxtRGM2.getText()));
						leitor.setNome(txtNome.getText());
						leitor.setCurso((String) cmbCurso.getSelectedItem());
						leitor2.setDisciplina((String) cmbDisciplina.getSelectedItem());
						leitor2.setSemestre((String) cmbSemestre.getSelectedItem());
						double nota = (Double.parseDouble((String) cmbNotas.getSelectedItem()));
						leitor2.setNotas(nota);
						leitor2.setFaltas(Integer.parseInt(txtFaltas.getText()));
						leitor2.setLeitor(leitor);

						// Abrir a conexão
						dao2 = new LeitorDAO2();
						// Salvar
						dao2.salvar2(leitor2);
						JOptionPane.showMessageDialog(null, "Salvo com Sucesso!!!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Salvar");
				}
			}
		});
		btnSalvar2.setToolTipText("Salvar");
		ImageIcon icon8 = new ImageIcon(MenuPrincipal.class.getResource("/imgs/icon2.png"));
		icon8.setImage(icon8.getImage().getScaledInstance(50, 45, 1));
		btnSalvar2.setIcon(icon8);
		btnSalvar2.setBounds(98, 187, 65, 51);
		panelNotasFaltas.add(btnSalvar2);

		btnAlterar2 = new JButton("");
		btnAlterar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					conn = ConnectionFactory.getConnection();
					ps = conn.prepareStatement("SELECT * FROM notasfaltas");
					rs = ps.executeQuery();
					
					if (ftxtRGM2.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Valido para Alterar!");
					} else if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Cadastrado para Alterar!");
					} else if (cmbCurso.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Selecione um Curso para salvar!");
					} else if (cmbDisciplina.getSelectedItem().equals("Selecione uma opção")) {
						JOptionPane.showMessageDialog(null, "Selecione uma Disciplina para salvar!");
					} else if (cmbSemestre.getSelectedItem().equals("Escolha")) {
						JOptionPane.showMessageDialog(null, "Selecione um Semestre para salvar!");
					} else if (cmbNotas.getSelectedItem().equals("Informe")) {
						JOptionPane.showMessageDialog(null, "Informe uma Nota para salvar!");
					} else if (txtFaltas.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Campo Falta em branco!!");
					} else {
						char s = 's';
						while (s == 's') {
							while (rs.next()) {
								int rgm = rs.getInt("RGM");
								int rgm2 = Integer.parseInt(ftxtRGM2.getText());
								if (rgm2 == rgm) {
									Leitor leitor = new Leitor();
									Leitor2 leitor2 = new Leitor2();
									leitor2.setDisciplina((String) cmbDisciplina.getSelectedItem());
									leitor2.setSemestre((String) cmbSemestre.getSelectedItem());
									leitor2.setNotas((Double.parseDouble((String) cmbNotas.getSelectedItem())));
									leitor2.setFaltas(Integer.parseInt(txtFaltas.getText()));
									leitor.setRgm(Integer.parseInt(ftxtRGM2.getText()));

									ps = conn.prepareStatement("UPDATE notasfaltas SET DISCIPLINA=?, SEMESTRE=?, NOTA=?, FALTA=? "
													+ "WHERE RGM=?");
									ps.setString(1, leitor2.getDisciplina());
									ps.setString(2, leitor2.getSemestre());
									ps.setDouble(3, leitor2.getNotas());
									ps.setInt(4, leitor2.getFaltas());
									ps.setInt(5, leitor.getRgm());
									ps.executeUpdate();
									JOptionPane.showMessageDialog(null, "Alterado com Sucesso!!!");
								}
							}
							s = 'n';
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Alterar!");
				}
			}
		});
		btnAlterar2.setToolTipText("Alterar");
		ImageIcon icon9 = new ImageIcon(MenuPrincipal.class.getResource("/imgs/icon3.png"));
		icon9.setImage(icon9.getImage().getScaledInstance(50, 45, 1));
		btnAlterar2.setIcon(icon9);
		btnAlterar2.setBounds(180, 187, 65, 51);
		panelNotasFaltas.add(btnAlterar2);

		btnConsultar2 = new JButton("");
		btnConsultar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (ftxtRGM2.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Valido para Consultar!");
					} else {
						try {
							dao = new LeitorDAO();
							int rgm = Integer.parseInt(ftxtRGM2.getText());
							leitor = dao.consultar(rgm);

							txtNome2.setText(leitor.getNome());
							txtCurso.setText(leitor.getCurso());
							String rgm2 = (String.valueOf(leitor.getRgm()));
							ftxtRGM.setText(rgm2);
							ftxtRGM.setEditable(false);
							txtNome.setText(leitor.getNome());
							ftxtData.setText(leitor.getData());
							ftxtCPF.setText(leitor.getCpf());
							txtEmail.setText(leitor.getEmail());
							txtEndereco.setText(leitor.getEndereco());
							txtMunicipio.setText(leitor.getMunicipio());
							cmbUF.setSelectedItem(leitor.getUf());
							ftxtCelular.setText(leitor.getCelular());
							cmbCurso.setSelectedItem(leitor.getCurso());
							cmbCampus.setSelectedItem(leitor.getCampus());
							if (leitor.getPeriodo().equals("Matutino")) {
								radMatutino.setSelected(true);
								radVespertino.setSelected(false);
								radNoturno.setSelected(false);
							} else if (leitor.getPeriodo().equals("Vespertino")) {
								radVespertino.setSelected(true);
								radMatutino.setSelected(false);
								radNoturno.setSelected(false);
							} else {
								radNoturno.setSelected(true);
								radMatutino.setSelected(false);
								radVespertino.setSelected(false);
							}
						} catch (NullPointerException e1) {
							JOptionPane.showMessageDialog(null, "Informe um RGM Cadastrado para Consultar!");
						}
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Consultar!");
				}
			}
		});
		btnConsultar2.setToolTipText("Consultar");
		ImageIcon icon10 = new ImageIcon(MenuPrincipal.class.getResource("/imgs/icon4.png"));
		icon10.setImage(icon10.getImage().getScaledInstance(50, 45, 1));
		btnConsultar2.setIcon(icon10);
		btnConsultar2.setBounds(345, 187, 65, 51);
		panelNotasFaltas.add(btnConsultar2);

		btnExcluir2 = new JButton("");
		btnExcluir2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (ftxtRGM2.getText().trim().length() <= 7) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Valido para Excluir!");
					} else if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Informe um RGM Cadastrado para Excluir!");
					} else {
						// Abrir a conexão
						dao = new LeitorDAO();
						dao2 = new LeitorDAO2();
						// Excluir
						int rgm = Integer.parseInt(ftxtRGM2.getText());
						dao.excluir(rgm);
						dao2.excluir2(rgm);
						JOptionPane.showMessageDialog(null, "Excluído com Sucesso!!!");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao Excluir!");
				}
			}
		});
		btnExcluir2.setToolTipText("Excluir");
		ImageIcon icon11 = new ImageIcon(MenuPrincipal.class.getResource("/imgs/icon5.png"));
		icon11.setImage(icon11.getImage().getScaledInstance(50, 45, 1));
		btnExcluir2.setIcon(icon11);
		btnExcluir2.setBounds(265, 187, 65, 51);
		panelNotasFaltas.add(btnExcluir2);

		btnListar2 = new JButton("");
		btnListar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ftxtRGM2.getText().trim().length() <= 7) {
					JOptionPane.showMessageDialog(null, "Informe um RGM Valido para Listar os Dados!");
				} else {
					try {
						txtaDados.setText(null);

						conn = ConnectionFactory.getConnection();
						ps = conn.prepareStatement("SELECT * FROM notasfaltas");
						rs = ps.executeQuery();

						try {
							char s = 's';
							while (s == 's') {
								while (rs.next()) {
									String disciplina = rs.getString("DISCIPLINA");
									String semestre = rs.getString("SEMESTRE");
									double notas = rs.getDouble("NOTA");
									int faltas = rs.getInt("FALTA");
									int rgm = rs.getInt("RGM");

									int rgm2 = Integer.parseInt(ftxtRGM2.getText());
									if (rgm2 == rgm) {
										
										conn = ConnectionFactory.getConnection();
										ps = conn.prepareStatement("SELECT * FROM dadospessoais");
										rs = ps.executeQuery();
											while (rs.next()) {
												String nome = rs.getString("NOME");
												String curso = rs.getString("CURSO");
												txtaDados.append("RGM: " + rgm);
												txtaDados.append("     Nome: " + nome + "\n");
												txtaDados.append("\nCurso: " + curso);
												txtaDados.append("\n");
												txtaDados.append("==================================================================");
												txtaDados.append("\nDisciplina: " + disciplina);
												txtaDados.append("\nSemestre: " + semestre);
												txtaDados.append("\tNota: " + notas);
												txtaDados.append("\tFaltas: " + faltas + "\n");
												txtaDados.append("=================================================================="+ "\n");
												txtaDados.append("\n");
											}
								}
								}
								s = 'n';
							}
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Informe um RGM Valido para Listar os Dados");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Erro ao listar os dados");
					}
				}
			}
		});
		btnListar2.setToolTipText("Listar");
		ImageIcon icon12 = new ImageIcon(MenuPrincipal.class.getResource("/imgs/icon6.png"));
		icon12.setImage(icon12.getImage().getScaledInstance(50, 45, 1));
		btnListar2.setIcon(icon12);
		btnListar2.setBounds(426, 187, 65, 51);
		panelNotasFaltas.add(btnListar2);

		panelBoletim = new JPanel();
		panelBoletim.setBackground(new Color(30, 144, 255));
		tabbedPane.addTab("Boletim", null, panelBoletim, null);
		panelBoletim.setLayout(null);

		lblTodosDados = new JLabel("BOLETIM");
		lblTodosDados.setForeground(new Color(255, 255, 0));
		lblTodosDados.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTodosDados.setBounds(220, 15, 59, 14);
		panelBoletim.add(lblTodosDados);

		txtaDados = new TextArea();
		txtaDados.setFocusable(false);
		txtaDados.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtaDados.setForeground(new Color(0, 0, 0));
		txtaDados.setBackground(new Color(135, 206, 235));
		txtaDados.setEditable(false);
		txtaDados.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtaDados.setBounds(10, 46, 485, 195);
		panelBoletim.add(txtaDados);

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				ftxtRGM.requestFocus();
			}
		});
	}

	private void formatarCampo() {
		try {
			MaskFormatter mask = new MaskFormatter("########");
			mask.install(ftxtRGM);
		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro ao formatar campo de texto", "ERROR", JOptionPane.ERROR);
		}
	}

	private void formatarCampo2() {
		try {
			MaskFormatter mask2 = new MaskFormatter("##/##/####");
			mask2.install(ftxtData);
		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro ao formatar campo de texto", "ERROR", JOptionPane.ERROR);
		}
	}

	private void formatarCampo3() {
		try {
			MaskFormatter mask3 = new MaskFormatter("###.###.###-##");
			mask3.install(ftxtCPF);
		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro ao formatar campo de texto", "ERROR", JOptionPane.ERROR);
		}
	}

	private void formatarCampo4() {
		try {
			MaskFormatter mask4 = new MaskFormatter("(##)#####-####");
			mask4.install(ftxtCelular);
		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro ao formatar campo de texto", "ERROR", JOptionPane.ERROR);
		}
	}

	private void formatarCampo5() {
		try {
			MaskFormatter mask = new MaskFormatter("########");
			mask.install(ftxtRGM2);
		} catch (ParseException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro ao formatar campo de texto", "ERROR", JOptionPane.ERROR);
		}
	}
}
