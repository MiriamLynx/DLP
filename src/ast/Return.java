package ast;

public class Return extends AbstractAst implements Sentencia {

	public Expresion expresion;

	public Return(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Return [expresion=" + expresion + "]";
	}

}
