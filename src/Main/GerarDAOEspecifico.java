package Main;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import tools.StringTools;

public class GerarDAOEspecifico {

    String nomeDaClasse;
    Atributo atributo = new Atributo();
    List<Atributo> listaDeAtributos = new ArrayList<>();
    List<String> codigoGerado = new ArrayList<>();
    StringTools sT = new StringTools();

    public GerarDAOEspecifico(String nomeDaClasse, List<Atributo> listaDeAtributos) {
        String classeMa = sT.primeiraLetraMaiuscula(nomeDaClasse);
        String classeMe = sT.primeiraLetraMinuscula(nomeDaClasse);
        String item1 = sT.primeiraLetraMaiuscula(listaDeAtributos.get(0).getNome());
        String item1Min = listaDeAtributos.get(0).getNome();
        String item2 = sT.primeiraLetraMaiuscula(listaDeAtributos.get(1).getNome());
        String item2Min = listaDeAtributos.get(1).getNome();
        codigoGerado.add("package DAOs;\n"
                + "\n"
                + "\n"
                + "import Entidades." + classeMa + ";\n"
                + "import DAOs.DAOGenerico;\n"
                + "import static DAOs.DAOGenerico.em;\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "\n"
                + "public class DAO" + classeMa + " extends DAOGenerico<" + classeMa + "> {\n"
                + "\n"
                + "    public DAO" + classeMa + "() {\n"
                + "        super(" + classeMa + ".class);\n"
                + "    }\n"
                + "\n"
                + "    public int autoId" + classeMa + "() {\n"
                + "        Integer a = (Integer) em.createQuery(\"SELECT MAX(e.id" + classeMa + ") FROM " + classeMa + " e \").getSingleResult();\n"
                + "        if (a != null) {\n"
                + "            return a + 1;\n"
                + "        } else {\n"
                + "            return 1;\n"
                + "        }\n"
                + "    }\n"
                + "\n"
                + "    public List<" + classeMa + "> listByNome(String nome) {\n"
                + "        return em.createQuery(\"SELECT e FROM " + classeMa + " e WHERE e."+ item2Min +" LIKE :nome\").setParameter(\"nome\", \"%\" + nome + \"%\").getResultList();\n"
                + "    }\n"
                + "\n"
                + "    public List<" + classeMa + "> listById(int id) {\n"
                + "        return em.createQuery(\"SELECT e FROM " + classeMa + " e WHERE e."+ item1Min +" = :id\").setParameter(\"id\", id).getResultList();\n"
                + "    }\n"
                + "\n"
                + "    public List<" + classeMa + "> listInOrderNome() {\n"
                + "        return em.createQuery(\"SELECT e FROM " + classeMa + " e ORDER BY e."+ item2Min +"\").getResultList();\n"
                + "    }\n"
                + "\n"
                + "    public List<" + classeMa + "> listInOrderId() {\n"
                + "        return em.createQuery(\"SELECT e FROM " + classeMa + " e ORDER BY e."+ item1Min +"\").getResultList();\n"
                + "    }\n"
                + "\n"
                + "    public List<String> listInOrderNomeStrings(String qualOrdem) {\n"
                + "        List<" + classeMa + "> lf;\n"
                + "        if (qualOrdem.equals(\"id\")) {\n"
                + "            lf = listInOrderId();\n"
                + "        } else {\n"
                + "            lf = listInOrderNome();\n"
                + "        }\n"
                + "\n"
                + "        List<String> ls = new ArrayList<>();\n"
                + "        for (int i = 0; i < lf.size(); i++) {\n"
                + "            ls.add(lf.get(i).get" + item1 + "() + \"-\" + lf.get(i).get" + item2 + "());\n"
                + "        }\n"
                + "        return ls;\n"
                + "    }\n"
                + "public static void main(String[] args) {\n"
                + "        DAO" + classeMa + " dao" + classeMa + " = new DAO" + classeMa + "();\n"
                + "        List<" + classeMa + "> lista" + classeMa + " = dao" + classeMa + ".list();\n"
                + "        for (" + classeMa + " " + classeMe + " : lista" + classeMa + ") {\n"
                + "            System.out.println(" + classeMe + ".get" + item1 + "() + \"-\" + " + classeMe + ".get" + item2 + "());\n"
                + "        }\n"
                + "    }"
                + "}");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

        manipulaArquivo.salvarArquivo(
                "src/CodigoGerado/DAO" + nomeDaClasse + ".java", codigoGerado);
    }
}