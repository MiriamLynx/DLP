package ast;

public class Negacion extends AbstractAst implements Expresion {

	public Expresion expresion;

	public Negacion(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Negacion [expresion=" + expresion + "]";
	}

}
