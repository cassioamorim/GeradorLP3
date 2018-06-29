
package Main;


class Atributo {
   private String tipo;
   private String nome;

    public Atributo() {
    }

    public Atributo(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Atributo{" + "tipo=" + tipo + ", nome=" + nome + '}';
    }
}
