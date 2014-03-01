package ast;

import java.util.List;

public class LlamadaFuncion extends AbstractAst implements Expresion {

	public String nombre;
	public List<Expresion> expresiones;

	public LlamadaFuncion(int linea, int columna, String nombre,
			List<Expresion> expresiones) {
		super(linea, columna);
		this.nombre = nombre;
		this.expresiones = expresiones;
	}

	@Override
	public String toString() {
		return "LlamadaFuncion [nombre=" + nombre + ", expresiones="
				+ expresiones + "]";
	}

}
