package ast;

import java.util.List;

public class DefStruct extends AbstractAst implements Definicion {

	public String nombre;
	public List<DefVariable> definiciones;

	public DefStruct(int linea, int columna, String nombre,
			List<DefVariable> definiciones) {
		super(linea, columna);
		this.nombre = nombre;
		this.definiciones = definiciones;
	}

	@Override
	public String toString() {
		return "DefStruct [nombre=" + nombre + ", definiciones=" + definiciones
				+ "]";
	}

}
