package ast;

import java.util.List;

public class Programa extends AbstractAst implements NodoAST {

	public List<Sentencia> sentencias;
	public List<DefVariable> definiciones;
	public List<DefVariable> variables;

	public Programa(int linea, int columna, List<Sentencia> sentencias, List<DefVariable> definiciones, List<DefVariable> variables) {
		super(linea,columna);
		this.sentencias = sentencias;
		this.variables = variables;
		this.definiciones = definiciones;
	}

	@Override
	public String toString() {
		return "Programa [sentencias=" + sentencias + ", definiciones="
				+ definiciones + ", variables=" + variables + "]";
	}

}
