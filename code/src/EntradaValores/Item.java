package EntradaValores;
/* @author Paulo Henrique */

public class Item {
    
    private int id;
    private float valor;
    private float peso;

    public Item(int id, float valor, float peso) {
        this.id = id;
        this.valor = valor;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

}