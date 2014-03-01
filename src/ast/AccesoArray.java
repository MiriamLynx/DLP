package ast;

public class AccesoArray extends AbstractAst implements Expresion {

	public Expresion index;
	public Expresion array;

	public AccesoArray(int linea, int columna, Expresion index, Expresion array) {
		super(linea, columna);
		this.index = index;
		this.array = array;
	}

}
