package ast;

public class OperacionAritmetica extends AbstractAst implements Expresion {

	public Expresion izquierda;
	public Expresion derecha;
	public String operador;

	public OperacionAritmetica(int linea, int columna, Expresion izquierda,
			Expresion derecha, String operador) {
		super(linea, columna);
		this.izquierda = izquierda;
		this.derecha = derecha;
		this.operador = operador;
	}

	@Override
	public String toString() {
		return "OperacionAritmetica [izquierda=" + izquierda + ", derecha="
				+ derecha + ", operador=" + operador + "]";
	}

}
