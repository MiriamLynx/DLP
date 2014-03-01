package ast;

public class LiteralReal extends AbstractAst implements Expresion {

	public double valor;

	public LiteralReal(int linea, int columna, double valor) {
		super(linea, columna);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralReal [valor=" + valor + "]";
	}

}
