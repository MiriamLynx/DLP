package ast;

public class LiteralEntero extends AbstractAst implements Expresion {

	public int valor;

	public LiteralEntero(int linea, int columna, int valor) {
		super(linea,columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralEntero [valor=" + valor + "]";
	}

}
