package ast;

public class OperacionLogica extends AbstractAst implements Expresion {

	public Expresion izquierda;
	public Expresion derecha;
	public String operador;

	public OperacionLogica(int linea, int columna, Expresion izquierda,
			String operador, Expresion derecha) {
		super(linea, columna);
		this.izquierda = izquierda;
		this.derecha = derecha;
		this.operador = operador;
	}

}
