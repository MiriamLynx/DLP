package ast;

public class Asignacion extends AbstractAst implements Sentencia {

	private Expresion izquierda;
	private Expresion derecha;

	public Asignacion(int linea, int columna, Expresion izquierda,
			Expresion derecha) {
		super(linea,columna);
		this.izquierda = izquierda;
		this.derecha = derecha;
	}


	@Override
	public String toString() {
		return "Asignacion [izquierda=" + izquierda + ", derecha=" + derecha
				+ "]";
	}

}
