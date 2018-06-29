package Main;

import java.util.ArrayList;
import java.util.List;
import tools.StringTools;
import tools.ManipulaArquivo;

public class GerarGUIListagem {

    String nomeDaClasse;
    Atributo atributo = new Atributo();
    List<Atributo> listaDeAtributos = new ArrayList<>();
    List<String> codigoGerado = new ArrayList<>();
    StringTools sT = new StringTools();

    public GerarGUIListagem(String nomeDaClasse, List<Atributo> listaDeAtributos) {
        String classeMe = sT.primeiraLetraMinuscula(nomeDaClasse);
        String classeMa = sT.primeiraLetraMaiuscula(nomeDaClasse);
        codigoGerado.add("package GUIs;\n"
                + "import Entidades." + classeMa + ";\n"
                + "import tools.*;"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.Dimension;\n"
                + "import java.util.List;\n"
                + "import javax.swing.JDialog;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JScrollPane;\n"
                + "import javax.swing.JTable;\n"
                + "import javax.swing.JToolBar;\n"
                + "import javax.swing.table.DefaultTableModel;\n"
                + "import java.text.SimpleDateFormat;\n"
                + "import java.text.DecimalFormat;\n"
                + "public class GUIListagem" + classeMa + " extends JDialog {\n"
                + "JPanel painelTa = new JPanel();\n"
                + "JScrollPane scroll = new JScrollPane();\n"
                + "SimpleDateFormat sdf = new SimpleDateFormat(\"dd/MM/yyyy\");\n"
                + "DecimalFormat decimalFormat = new DecimalFormat(\"###,###,##0.00\");\n"
                + "public GUIListagem" + classeMa + "(List<" + classeMa + "> texto) {\n"
                + "setTitle(\"Listagem de " + classeMa + "\");\n"
                + "setSize(600, 300);//tamanho da janela\n"
                + "setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memÃ³ria a classe\n"
                + "setLayout(new BorderLayout());//informa qual gerenciador de layout serÃ¡ usado\n"
                + "setBackground(Color.CYAN);//cor do fundo da janela\n"
                + "setModal(true);\n"
                + "Container cp = getContentPane();//container principal, para adicionar nele os outros componentes\n"
                + "JToolBar toolBar = new JToolBar();\n");
        String colunas = "String[] colunas = new String[]{";
        for (int i = 0; i < listaDeAtributos.size(); i++) {
            if (i == listaDeAtributos.size()) {
                if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                    colunas += "\"" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + "\"";
                } else {
                    colunas += "\"" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + "\"";
                }
            } else {
                if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                    colunas += "\"" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + "\", ";
                } else {
                    colunas += "\"" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + "\", ";
                }
            }
        }
        codigoGerado.add(colunas + "};");
        codigoGerado.add("String[][] dados = new String[0][3];\n"
                + "DefaultTableModel model = new DefaultTableModel(dados, colunas);\n"
                + "JTable tabela = new JTable(model);\n"
                + "scroll.setViewportView(tabela);\n"
                + "for (int i = 0; i < texto.size(); i++) {");
        String linha = "String[] linha = new String[]{";
        for (int i = 0; i < listaDeAtributos.size(); i++) {
            if (listaDeAtributos.get(i).getTipo().equals("Date")) {
                linha += "sdf.format(texto.get(i).get" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + "()), ";
            } else {
                if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                    linha += "String.valueOf(texto.get(i).get" + sT.retiraUltimoCaractere(sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome())) + "()), ";
                } else {
                    linha += "String.valueOf(texto.get(i).get" + sT.primeiraLetraMaiuscula(listaDeAtributos.get(i).getNome()) + "()), ";
                }
            }
        }
        codigoGerado.add(linha + "};");
        codigoGerado.add("model.addRow(linha);\n"
                + "}\n"
                + "painelTa.add(scroll);\n"
                + "cp.add(toolBar, BorderLayout.NORTH);\n"
                + "cp.add(scroll, BorderLayout.CENTER);\n"
                + "CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();\n"
                + "setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));\n"
                + "setVisible(true);\n"
                + "}\n"
                + "}");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

        manipulaArquivo.salvarArquivo(
                "src/CodigoGerado/GUIListagem" + nomeDaClasse + ".java", codigoGerado);
    }
}
