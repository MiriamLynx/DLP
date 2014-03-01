package ast;

public class Variable extends AbstractAst implements Expresion {

	public String nombre;

	public Variable(int linea, int columna, String nombre) {
		super(linea,columna);
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Variable [nombre=" + nombre + "]";
	}

}
