package ast;

import java.util.List;

public class Lectura extends AbstractAst implements Sentencia {

	public List<Expresion> expresiones;

	public Lectura(int linea, int columna, List<Expresion> expresiones) {
		super(linea,columna);
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "Lectura [expresiones=" + expresiones + "]";
	}

}
