package Main;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

public class Main {

    public static void main(String[] args) {
        String nomeDaClasse = "Produto";
        List<Atributo> listaDeAtributos = new ArrayList<>();

        List<String> arquivo = new ArrayList<>();
        arquivo = new ManipulaArquivo().abrirArquivo("src/Main/" + nomeDaClasse + ".txt");
        for (int i = 0; i < arquivo.size(); i++) {
            String[] s = arquivo.get(i).split(";");
            Atributo atributo = new Atributo();
            atributo.setTipo(s[0]);
            atributo.setNome(s[1]);
            listaDeAtributos.add(atributo);
        }

        int c = 0;
        for (int i = 0; i < listaDeAtributos.size(); i++) {
            if ((listaDeAtributos.get(i).getNome().substring(listaDeAtributos.get(i).getNome().length() - 1, listaDeAtributos.get(i).getNome().length())).equals("K")) {
                c++;
            }
        }
        String[] foreignKey = new String[c];
        if (c > 0) {
            foreignKey[0] = "Tipo";
            foreignKey[1] = "Marca";
        }

        //new GerarClasseGUI(nomeDaClasse, listaDeAtributos, foreignKey);
        //new GerarClasseGUISemImagem(nomeDaClasse, listaDeAtributos, foreignKey);
        new GerarGUIListagem(nomeDaClasse, listaDeAtributos);
        //new GerarDAOGenerico();
        //new GerarDAOEspecifico(nomeDaClasse, listaDeAtributos);
    }
}
