package ast;

import java.util.List;

public class LlamadaProcedimiento extends AbstractAst implements Sentencia {

	public String nombre;
	public List<Expresion> expresiones;

	public LlamadaProcedimiento(int linea, int columna, String nombre,
			List<Expresion> expresiones) {
		super(linea, columna);
		this.nombre = nombre;
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "LlamadaProcedimiento [nombre=" + nombre + ", expresiones="
				+ expresiones + "]";
	}

}
