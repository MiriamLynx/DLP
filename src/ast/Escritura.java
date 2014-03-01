package ast;

import java.util.List;

public class Escritura extends AbstractAst implements Sentencia {

	public List<Expresion> expresiones;

	public Escritura(int linea, int columna, List<Expresion> expresiones) {
		super(linea, columna);
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Escritura [expresiones=" + expresiones + "]";
	}

}
