package ast;

public class Comparacion extends AbstractAst implements Expresion {
	
	public Expresion izquierda;
	public Expresion derecha;
	public String operador;
	
	public Comparacion(int linea, int columna, Expresion izquierda, String operador, Expresion derecha){
		super(linea, columna);
		this.izquierda = izquierda;
		this.derecha = derecha;
		this.operador = operador;
	}

	@Override
	public String toString() {
		return "Comparacion [izquierda=" + izquierda + ", derecha=" + derecha
				+ ", operador=" + operador + "]";
	}

}
