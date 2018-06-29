package Main;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

public class GerarClasseGUISemImagem {

    String nomeDaClasse;
    Atributo atributo = new Atributo();
    List<Atributo> listaDeAtributos = new ArrayList<>();
    List<String> codigoGerado = new ArrayList<>();
    List<String> foreignKey = new ArrayList<>();
    StringTools sT = new StringTools();

    public GerarClasseGUISemImagem(String nomeDaClasse, List<Atributo> listaDeAtributos, String[] foreignKey) {
        String idMa = sT.primeiraLetraMaiuscula(listaDeAtributos.get(0).getNome());
        String idMe = sT.primeiraLetraMinuscula(listaDeAtributos.get(0).getNome());
        String classeMe = sT.primeiraLetraMinuscula(nomeDaClasse);
        String classeMa = sT.primeiraLetraMaiuscula(nomeDaClasse);
        codigoGerado.add("package CodigoGerado;");
        codigoGerado.add("import DAOs.DAO" + classeMa + ";\n"
                + "import Entidades.*;\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.GridLayout;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.FocusEvent;\n"
                + "import java.awt.event.FocusListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import java.io.File;\n"
                + "import javax.swing.ImageIcon;\n"
                + "import javax.swing.JButton;\n"
                + "import javax.swing.JFrame;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JTextField;\n"
                + "import javax.swing.JToolBar;\n"
                + "import javax.swing.WindowConstants;\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.FlowLayout;\n"
                + "import java.awt.GridLayout;\n"
                + "import java.awt.ScrollPane;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.FocusEvent;\n"
                + "import java.awt.event.FocusListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import java.text.SimpleDateFormat;\n"
                + "import java.text.ParseException;\n"
                + "import java.util.List;\n"
                + "import java.util.Date;\n"
                + "import java.util.logging.Level;\n"
                + "import java.util.logging.Logger;\n"
                + "import javax.swing.JButton;\n"
                + "import javax.swing.ImageIcon;\n"
                + "import javax.swing.JFrame;\n"
                + "import javax.swing.JComboBox;\n"
                + "import javax.swing.JCheckBox;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JTextArea;\n"
                + "import java.io.File;\n"
                + "import javax.swing.JFileChooser;\n"
                + "import java.awt.Image;\n"
                + "import javax.swing.JTextField;\n"
                + "import tools.*;\n"
                + "import DAOs.*;\n"
                + "public class GUI" + classeMa + " extends JFrame {\n"
                + "ImageIcon iconeCreate = new ImageIcon(getClass().getResource(\"/icones/create.png\"));\n"
                + "ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource(\"/icones/retrieve.png\"));\n"
                + "ImageIcon iconeUpdate = new ImageIcon(getClass().getResource(\"/icones/update.png\"));\n"
                + "ImageIcon iconeDelete = new ImageIcon(getClass().getResource(\"/icones/delete.png\"));\n"
                + "ImageIcon iconeSave = new ImageIcon(getClass().getResource(\"/icones/save.png\"));\n"
                + "ImageIcon iconeCancel = new ImageIcon(getClass().getResource(\"/icones/cancel.png\"));\n"
                + "ImageIcon iconeListar = new ImageIcon(getClass().getResource(\"/icones/list.png\"));\n"
                + "JButton btnCreate = new JButton(iconeCreate);\n"
                + "JButton btnRetrieve = new JButton(iconeRetrieve);\n"
                + "JButton btnUpdate = new JButton(iconeUpdate);\n"
                + "JButton btnDelete = new JButton(iconeDelete);\n"
                + "JButton btnSave = new JButton(iconeSave);\n"
                + "JButton btnCancel = new JButton(iconeCancel);\n"
                + "JButton btnList = new JButton(iconeListar);");
        int verificacao = 0;
        for (int i = 1; i < listaDeAtributos.size(); i++) {
            if (listaDeAtributos.get(i).getTipo().equals("Date")) {
                verificacao++;
            }
        }
        if (verificacao > 0) {
            codigoGerado.add("private SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");");
            for (int i = 0; i < listaDeAtributos.size(); i++) {
                if (listaDeAtributos.get(i).getTipo().equals("Date")) {
                    codigoGerado.add("private Date data" + i + ";");
                }
            }
        }
        codigoGerado.add("private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));");
        int tamanho = listaDeAtributos.size() - 1;
        System.out.println(tamanho);
        codigoGerado.add("private JPanel pnCentro = new JPanel(new GridLayout(" + (listaDeAtributos.size() - 1) + ", 2));");
        codigoGerado.add("private JPanel pnSul = new JPanel(new GridLayout(1, 1));");
        for (int i = 0; i < listaDeAtributos.size(); i++) {
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("private JPanel pn" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ""
                        + " = new JPanel(new GridLayout(1, 2));");
                codigoGerado.add("private JLabel lb" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()))
                        + " = new JLabel(\"" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + "\");");
                codigoGerado.add("private JTextField tf" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()))
                        + " = new JTextField();");
                codigoGerado.add("private JButton bt" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()))
                        + " = new JButton(\"Procurar\");");
            } else if (listaDeAtributos.get(i).getTipo().equals("boolean")) {
                codigoGerado.add("private JLabel lb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ""
                        + " = new JLabel(\"" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + "\");");
                codigoGerado.add("private JCheckBox cb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())
                        + " = new JCheckBox(\"\");");
            } else if (listaDeAtributos.get(i).getTipo().equals("Date")) {
                codigoGerado.add("private JLabel lb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + " = new JLabel(\"" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + "\");\n"
                        + "private JTextField tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + " = new JTextField(10);\n"
                        + "private JButton btEscolha" + i + " = new JButton(\"Escolha\");\n"
                        + "private JPanel pn" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + " = new JPanel(new GridLayout(1, 2));"
                );
            } else {
                codigoGerado.add("private JLabel lb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ""
                        + " = new JLabel(\"" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + "\");");
                codigoGerado.add("private JTextField tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())
                        + " = new JTextField(10);");
            }
        }
        codigoGerado.add("ScrollPane scroll = new ScrollPane();");
        codigoGerado.add("JTextArea jTextArea = new JTextArea();");
        codigoGerado.add("JPanel aviso = new JPanel();\n"
                + "JLabel labelAviso = new JLabel(\"\");\n"
                + "String qualAcao = \"\";//variavel para facilitar insert e update\n"
                + "DAO" + classeMa + " dao" + classeMa + " = new DAO" + classeMa + "();\n"
                + classeMa + " " + classeMe + ";");
        codigoGerado.add("private CaixaDeFerramentas ferramentas = new CaixaDeFerramentas();");
        codigoGerado.add("public GUI" + classeMa + "() {");
        codigoGerado.add("setSize(900, 400);\n"
                + "setDefaultCloseOperation(DISPOSE_ON_CLOSE);\n"
                + "setTitle(\"CRUD - " + classeMa + "\");\n"
                + "Container cp = getContentPane();"
                + "cp = getContentPane();\n"
                + "btnCreate.setToolTipText(\"Inserir novo registro\");\n"
                + "btnRetrieve.setToolTipText(\"Pesquisar por chave\");\n"
                + "btnUpdate.setToolTipText(\"Alterar\");\n"
                + "btnDelete.setToolTipText(\"Excluir\");\n"
                + "btnList.setToolTipText(\"Listar todos\");\n"
                + "btnSave.setToolTipText(\"Salvar\");\n"
                + "btnCancel.setToolTipText(\"Cancelar\");"
                + "cp.setLayout(new BorderLayout());\n"
                + "cp.add(pnNorte, BorderLayout.NORTH);\n"
                + "cp.add(pnCentro, BorderLayout.CENTER);\n"
                + "cp.add(pnSul, BorderLayout.SOUTH);"
                + "pnNorte.add(lb" + idMa + ");\n"
                + "pnNorte.add(tf" + idMa + ");\n"
                + "pnNorte.add(btnRetrieve);\n"
                + "pnNorte.add(btnCreate);\n"
                + "pnNorte.add(btnUpdate);\n"
                + "pnNorte.add(btnDelete);\n"
                + "pnNorte.add(btnSave);\n"
                + "pnNorte.add(btnList);"
        );
        codigoGerado.add("btnCancel.setVisible(false);\n"
                + "btnDelete.setVisible(false);\n"
                + "btnCreate.setVisible(false);\n"
                + "btnSave.setVisible(false);\n"
                + "btnUpdate.setVisible(false);");
        for (int i = 1;
                i < listaDeAtributos.size();
                i++) {
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("pnCentro.add(lb" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ");");
                codigoGerado.add("pnCentro.add(pn" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ");");
                codigoGerado.add("pn" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ".add(tf"
                        + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ");");
                codigoGerado.add("pn" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ".add(bt"
                        + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ");");
            } else if (listaDeAtributos.get(i).getTipo().equals("boolean")) {
                codigoGerado.add("pnCentro.add(lb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ");");
                codigoGerado.add("pnCentro.add(cb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ");");
            } else if (listaDeAtributos.get(i).getTipo().equals("Date")) {
                codigoGerado.add("pnCentro.add(lb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ");\n"
                        + "pnCentro.add(pn" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ");\n"
                        + "pn" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".add(btEscolha" + i + ");\n"
                        + "pn" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".add(tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ");");
            } else {
                codigoGerado.add("pnCentro.add(lb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ");");
                codigoGerado.add("pnCentro.add(tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ");");
            }
        }
        codigoGerado.add("pnSul.setBackground(Color.red);\n"
                + "scroll.add(jTextArea);\n"
                + "pnSul.add(scroll);");
        for (int i = 1; i < listaDeAtributos.size(); i++) {
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("tf" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ".setEditable(false);");
                codigoGerado.add("bt" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ".setEnabled(false);");
            } else if (listaDeAtributos.get(i).getTipo().equals("boolean")) {
                codigoGerado.add("cb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setEnabled(false);");
            } else if (listaDeAtributos.get(i).getTipo().equals("Date")) {
                codigoGerado.add("btEscolha" + i + ".setEnabled(false);");
                codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setEditable(false);");
            } else {
                codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setEditable(false);");
            }
        }
        codigoGerado.add("btnRetrieve.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e) {\n"
                + "try {\n"
                + "tf" + idMa + ".setBackground(Color.white);\n"
                + "jTextArea.setText(\"\");");
        if (listaDeAtributos.get(0).getTipo().equals("int")) {
            codigoGerado.add(classeMe + " = new " + classeMa + "();");
            codigoGerado.add("int identificador = Integer.valueOf(tf" + idMa + ".getText());");
            codigoGerado.add(classeMe + ".set" + idMa + "(identificador);");
            codigoGerado.add(classeMe + " = dao" + classeMa + ".obter(" + classeMe + ".get" + idMa + "());");
        } else if (listaDeAtributos.get(
                0).getTipo().equals("long")) {
            codigoGerado.add(classeMe + " = new " + classeMa + "();");
            codigoGerado.add("Long identificador = Long.valueOf(tf" + idMa + ".getText());");
            codigoGerado.add(classeMe + ".set" + idMa + "(identificador);");
            codigoGerado.add(classeMe + " = dao" + classeMa + ".obter(" + classeMe + ".get" + idMa + "());");
        } else if (listaDeAtributos.get(
                0).getTipo().equals("double")) {
            codigoGerado.add(classeMe + " = new " + classeMa + "();");
            codigoGerado.add("Double identificador = Double.valueOf(tf" + idMa + ".getText());");
            codigoGerado.add(classeMe + ".set" + idMa + "(identificador);");
            codigoGerado.add(classeMe + " = dao" + classeMa + ".obter(" + classeMe + ".get" + idMa + "());");
        } else {
            codigoGerado.add(classeMe + " = new " + classeMa + "();");
            codigoGerado.add("String identificador = tf" + idMa + ".getText();");
            codigoGerado.add(classeMe + ".set" + idMa + "(identificador);");
            codigoGerado.add(classeMe + " = dao" + classeMa + ".obter(" + classeMe + ".get" + idMa + "());");
        }
        codigoGerado.add("if (" + classeMe + " == null) {");
        codigoGerado.add("pnNorte.setBackground(Color.red);");
        for (int i = 1; i < listaDeAtributos.size(); i++) {
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("tf" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ".setText(\"\");");
            } else if (listaDeAtributos.get(i).getTipo().equals("boolean")) {
                codigoGerado.add("cb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setSelected(false);");
            } else {
                codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setText(\"\");");
            }
        }
        codigoGerado.add("btnCreate.setVisible(true);");
        codigoGerado.add("} else {\n"
                + "pnNorte.setBackground(Color.green);");
        int d = 1;
        for (int i = 1; i < listaDeAtributos.size(); i++) {
            String nome = sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome());
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("String dao" + d + " = String.valueOf(" + classeMe + ".get" + sT.retiraUltimoCaractere(nome) + "());");
                codigoGerado.add("String [] aux" + d + " = dao" + d + ".split(\"-\");");
                codigoGerado.add("tf" + sT.retiraUltimoCaractere(nome) + ".setText(aux" + d + "[0]);");
                d++;
            } else if (listaDeAtributos.get(i).getTipo().equals("String")) {
                codigoGerado.add("tf" + nome + ".setText(" + classeMe + ".get" + nome + "());");
            } else if (listaDeAtributos.get(i).getTipo().equals("Date")) {
                codigoGerado.add("tf" + nome + ".setText(sdf.format(" + classeMe + ".get" + nome + "()));");
            } else if (listaDeAtributos.get(i).getTipo().equals("boolean")) {
                codigoGerado.add("cb" + nome + ".setSelected(Boolean.valueOf(" + classeMe + ".get" + nome + "()));");
            } else {
                codigoGerado.add("tf" + nome + ".setText(String.valueOf(" + classeMe + ".get" + nome + "()));");
            }
        }
        codigoGerado.add("btnUpdate.setVisible(true);\n"
                + "btnDelete.setVisible(true);\n"
                + "btnCreate.setVisible(false);\n"
                + "}");
        for (int i = 0; i < listaDeAtributos.size(); i++) {
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("bt" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ".setEnabled(false);");
            } else if (listaDeAtributos.get(i).getTipo().equals("boolean")) {
                codigoGerado.add("cb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setEnabled(false);");
            } else {
                codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setEditable(false);");
            }
        }
        codigoGerado.add("tf" + idMa + ".selectAll();\n"
                + "} catch (Exception erro) {\n"
                + "pnNorte.setBackground(Color.yellow);\n"
                + "tf" + idMa + ".requestFocus();\n"
                + "tf" + idMa + ".setBackground(Color.red);\n"
                + "jTextArea.setText(\"Erro... \\n\");\n"
                + "jTextArea.append(erro.getMessage());\n"
                + "}\n"
                + "}\n"
                + "});");
        codigoGerado.add("btnCreate.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e) {\n"
                + "tf" + idMa + ".setEditable(false);");
        codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(1).getNome()) + ".requestFocus();");
        codigoGerado.add("btnCreate.setVisible(false);\n"
                + "btnSave.setVisible(true);\n"
                + "qualAcao = \"incluir\";");
        codigoGerado.add(classeMe + " = new " + classeMa + "();");
        for (int i = 1; i < listaDeAtributos.size(); i++) {
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("bt" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ".setEnabled(true);");
            } else if (listaDeAtributos.get(i).getTipo().equals("Date")) {
                codigoGerado.add("btEscolha" + i + ".setEnabled(true);");
            } else if (listaDeAtributos.get(i).getTipo().equals("boolean")) {
                codigoGerado.add("cb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setEnabled(true);");
            } else {
                codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setEditable(true);");
            }
        }
        codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(0).getNome()) + ".setEditable(false);");
        codigoGerado.add("}\n"
                + "});\n"
                + "btnSave.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e) {");
        codigoGerado.add("try {\n"
                + "jTextArea.setText(\"\");");
        codigoGerado.add(classeMe + " = new " + classeMa + "();");
        int n = 0;
        for (int i = 0; i < listaDeAtributos.size(); i++) {
            String x = sT.primeiraLetraMaiuscula(sT.retiraUltimoCaractere(listaDeAtributos.get(i).getNome()));
            String z = sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome());
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("String [] aux" + n + " = tf" + x + ".getText().split(\"-\");");
                if (listaDeAtributos.get(i).getTipo().equals("String")) {
                    codigoGerado.add("DAO" + foreignKey[n] + " dao" + foreignKey[n] + " = new DAO" + foreignKey[n] + "();");
                    codigoGerado.add(foreignKey[n] + " d" + n + " = dao" + foreignKey[n] + ".obter(aux" + n + "[0]);");
                    codigoGerado.add(classeMe + ".set" + x + "(d" + n + ");");
                    n++;
                } else if (listaDeAtributos.get(i).getTipo().equals("int")) {
                    codigoGerado.add("DAO" + foreignKey[n] + " dao" + foreignKey[n] + " = new DAO" + foreignKey[n] + "();");
                    codigoGerado.add(foreignKey[n] + " d" + n + " = dao" + foreignKey[n] + ".obter(Integer.valueOf(aux" + n + "[0]));");
                    codigoGerado.add(classeMe + ".set" + x + "(d" + n + ");");
                    n++;
                } else if (listaDeAtributos.get(i).getTipo().equals("double")) {
                    codigoGerado.add("DAO" + foreignKey[n] + " dao" + foreignKey[n] + " = new DAO" + foreignKey[n] + "();");
                    codigoGerado.add(foreignKey[n] + " d" + n + " = dao" + foreignKey[n] + ".obter(Long.valueOf(aux" + n + "[0]));");
                    codigoGerado.add(classeMe + ".set" + x + "(d" + n + ");");
                    n++;
                } else {
                    codigoGerado.add("DAO" + foreignKey[n] + " dao" + foreignKey[n] + " = new DAO" + foreignKey[n] + "();");
                    codigoGerado.add(foreignKey[n] + " d" + n + " = dao" + foreignKey[n] + ".obter(Long.valueOf(aux" + n + "[0]));");
                    codigoGerado.add(classeMe + ".set" + x + "(d" + n + ");");
                    n++;
                }
            } else if (listaDeAtributos.get(i).getTipo().equals("String")) {
                codigoGerado.add(classeMe + ".set" + z + "(tf" + z + ".getText());");
            } else if (listaDeAtributos.get(i).getTipo().equals("int")) {
                codigoGerado.add(classeMe + ".set" + z + "(Integer.valueOf(tf" + z + ".getText()));");
            } else if (listaDeAtributos.get(i).getTipo().equals("double")) {
                codigoGerado.add(classeMe + ".set" + z + "(Double.valueOf(tf" + z + ".getText()));");
            } else if (listaDeAtributos.get(i).getTipo().equals("boolean")) {
                codigoGerado.add(classeMe + ".set" + z + "(cb" + z + ".isSelected());");
            } else if (listaDeAtributos.get(i).getTipo().equals("long")) {
                codigoGerado.add(classeMe + ".set" + z + "(Long.valueOf(tf" + z + ".getText()));");
            } else if (listaDeAtributos.get(i).getTipo().equals("float")) {
                codigoGerado.add(classeMe + ".set" + z + "(Float.valueOf(tf" + z + ".getText()));");
            } else if (listaDeAtributos.get(i).getTipo().equals("byte")) {
                codigoGerado.add(classeMe + ".set" + z + "(Byte.valueOf(tf" + z + ".getText()));");
            } else if (listaDeAtributos.get(i).getTipo().equals("short")) {
                codigoGerado.add(classeMe + ".set" + z + "(Short.valueOf(tf" + z + ".getText()));");
            } else if (listaDeAtributos.get(i).getTipo().equals("char")) {
                codigoGerado.add(classeMe + ".set" + z + "(tf" + z + ".getText().charAt(0));");
            } else {
                if (verificacao > 0) {
                    codigoGerado.add("sdf.setLenient(false);");
                    verificacao = -1000;
                }
                codigoGerado.add("data" + i + " = sdf.parse(tf" + z + ".getText());");
                codigoGerado.add("try {\n"
                        + classeMe + ".set" + z + "(sdf.parse(tf" + z + ".getText()));"
                        + "} catch (ParseException ex) {");
                codigoGerado.add("Logger.getLogger(GUI" + classeMa + ".class\n"
                        + ".getName()).log(Level.SEVERE, null, ex);\n"
                        + "}");
            }
        }

        codigoGerado.add("if (qualAcao.equals(\"incluir\")) {");
        codigoGerado.add("dao" + classeMa + ".inserir(" + classeMe + ");");
        codigoGerado.add("} else {");
        codigoGerado.add("dao" + classeMa + ".atualizar(" + classeMe + ");");
        codigoGerado.add("}");
        codigoGerado.add("tf" + idMa + ".setEditable(true);");
        codigoGerado.add("tf" + idMa + ".requestFocus();");
        for (int i = 1; i < listaDeAtributos.size(); i++) {
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("tf" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ".setText(\"\");");
            } else if (listaDeAtributos.get(i).getTipo().equals("boolean")) {
                codigoGerado.add("cb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setSelected(false);");
            } else {
                codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setText(\"\");");
            }
        }
        codigoGerado.add("btnSave.setVisible(false)\n;" + "pnNorte.setBackground(Color.green);");
        for (int i = 1; i < listaDeAtributos.size(); i++) {
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("bt" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ".setEnabled(false);");
            } else if (listaDeAtributos.get(i).getTipo().equals("Date")) {
                codigoGerado.add("btEscolha" + i + ".setEnabled(false);");
            } else if (listaDeAtributos.get(i).getTipo().equals("boolean")) {
                codigoGerado.add("cb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setEnabled(false);");
            } else {
                codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setEditable(false);");
            }
        }
        codigoGerado.add("} catch (Exception erro){\n"
                + "jTextArea.append(\"Erro............\");\n"
                + "tf" + idMa + ".setEditable(true);\n"
                + "pnNorte.setBackground(Color.red); \n"
                + "}");
        codigoGerado.add("}\n"
                + "});\n"
                + "btnUpdate.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e) {\n"
                + "btnUpdate.setVisible(false);\n"
                + "btnDelete.setVisible(false);");
        codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(1).getNome()) + ".requestFocus();");
        codigoGerado.add("btnSave.setVisible(true);\n"
                + "qualAcao = \"editar\";");
        for (int i = 1; i < listaDeAtributos.size(); i++) {
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("bt" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ".setEnabled(true);");
            } else if (listaDeAtributos.get(i).getTipo().equals("Date")) {
                codigoGerado.add("btEscolha" + i + ".setEnabled(true);");
            } else if (listaDeAtributos.get(i).getTipo().equals("boolean")) {
                codigoGerado.add("cb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setEnabled(true);");
            } else {
                codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setEditable(true);");
            }
        }
        codigoGerado.add("}\n"
                + "});\n"
                + "btnDelete.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e) {\n"
                + "if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,\n"
                + "\"Confirma a exclusão do registro <ID = \" + " + classeMe + ".get" + idMa + "() + \">?\", \"Confirm\",\n"
                + "JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {");
        codigoGerado.add(
                "dao" + classeMa + ".remover(" + classeMe + ");");
        codigoGerado.add(
                "tf" + idMa + ".requestFocus();");
        for (int i = 1; i < listaDeAtributos.size(); i++) {
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("tf" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + ".setText(\"\");");
            } else if (listaDeAtributos.get(i).getTipo().equals("boolean")) {
                codigoGerado.add("cb" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setSelected(false);");
            } else {
                codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setText(\"\");");
            }
        }
        codigoGerado.add(
                "tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(0).getNome()) + ".setEditable(true);");
        codigoGerado.add(
                "btnUpdate.setVisible(false);\n"
                + "btnDelete.setVisible(false);\n"
                + "}\n"
                + "}\n"
                + "});\n"
                + "btnList.addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + " public void actionPerformed(ActionEvent e) {");
        codigoGerado.add(
                "GUIListagem" + classeMa + " guiListagem = new GUIListagem" + classeMa + "(dao" + classeMa + ".list());");
        codigoGerado.add(
                "}\n" + "});");
        codigoGerado.add(
                "setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);\n"
                + "addWindowListener(new WindowAdapter() {\n"
                + "@Override\n"
                + "public void windowClosing(WindowEvent e) {\n"
                + "System.exit(0);\n"
                + "}\n"
                + "});");
        for (int i = 0;
                i < listaDeAtributos.size();
                i++) {
            if (listaDeAtributos.get(i).getTipo().equals("Date")) {
                codigoGerado.add("btEscolha" + i + ".addActionListener(new ActionListener() {\n"
                        + "@Override\n"
                        + "public void actionPerformed(ActionEvent e) {\n"
                        + "try {\n"
                        + "jTextArea.setText(\"\");\n"
                        + "DateChooser dc" + i + " = new DateChooser((JFrame) null, \"Escolha uma data\", 683, 0);\n"
                        + "data" + i + " = dc" + i + ".select();\n"
                        + "tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + ".setText(sdf.format(data" + i + "));\n"
                        + "} catch (Exception ex) {\n"
                        + "jTextArea.setText(\"Escolha uma data\\n\");\n"
                        + "}"
                        + "}\n"
                        + "});");
            }
        }

        int c = 0;
        for (int i = 0; i < listaDeAtributos.size(); i++) {
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                codigoGerado.add("DAO" + foreignKey[c] + " dao" + foreignKey[c] + ""
                        + " = new DAO" + foreignKey[c] + "();");
                codigoGerado.add("bt" + sT.primeiraLetraMaiuscula(sT.retiraUltimoCaractere(listaDeAtributos.get(i).getNome())) + ".addActionListener(new ActionListener() {\n"
                        + "@Override\n"
                        + "public void actionPerformed(ActionEvent e) {\n"
                        + "List<String> listaAuxiliar = dao" + foreignKey[c] + ".listInOrderNomeStrings(\"id\");\n"
                        + "if (listaAuxiliar.size() > 0) {\n"
                        + "String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();\n"
                        + "if (!selectedItem.equals(\"\")) {\n"
                        + "String[] aux = selectedItem.split(\"-\");");
                codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(sT.retiraUltimoCaractere(listaDeAtributos.get(i).getNome())) + ".setText(aux[0]);");
                codigoGerado.add("} else {\n"
                        + "jTextArea.setText(\"Nenhum dado adicionado!\");\n"
                        + "}\n"
                        + "}\n"
                        + "}\n"
                        + "});");
                c++;
            }
        }
        codigoGerado.add("tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(0).getNome()) + ".addActionListener(new ActionListener() {\n"
                + "@Override\n"
                + "public void actionPerformed(ActionEvent e) {\n"
                + "List<String> listaAuxiliar = dao" + classeMa + ".listInOrderNomeStrings(\"id\");\n"
                + "if (listaAuxiliar.size() > 0) {\n"
                + "String selectedItem = new JanelaPesquisar(listaAuxiliar).getValorRetornado();\n"
                + "if (!selectedItem.equals(\"\")) {\n"
                + "String[] aux = selectedItem.split(\"-\");");
        codigoGerado.add(
                "tf" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(0).getNome()) + ".setText(aux[0]);");
        codigoGerado.add(
                "btnRetrieve.doClick();\n");
        codigoGerado.add(
                "} else {\n"
                + "tf" + idMa + ".requestFocus();"
                + "tf" + idMa + ".selectAll();"
                + "}\n"
                + "}\n"
                + "}\n"
                + "});");
        codigoGerado.add(
                "CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();\n"
                + "setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));\n"
                + "setVisible(true);");
        codigoGerado.add(
                "}\n"
                + "public static void main(String[] args) {\n"
                + "GUI" + classeMa + " gui" + classeMa + " = new GUI" + classeMa + "();\n"
                + "}\n"
                + "}");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

        manipulaArquivo.salvarArquivo(
                "src/CodigoGerado/GUI" + nomeDaClasse + ".java", codigoGerado);
    }
}
