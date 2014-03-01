package ast;

import java.util.List;

import tipos.Tipo;

public class DefFuncion extends AbstractAst implements Definicion {

	public Tipo tipo;
	public String nombre;
	public List<DefVariable> parametros;
	public List<DefVariable> definiciones;
	public List<Sentencia> sentencias;

	public DefFuncion(int linea, int columna, Tipo tipo, String nombre,
			List<DefVariable> parametros, List<DefVariable> definiciones,
			List<Sentencia> sentencias) {
		super(linea, columna);
		this.tipo = tipo;
		this.nombre = nombre;
		this.parametros = parametros;
		this.definiciones = definiciones;
		this.sentencias = sentencias;
	}

	@Override
	public String toString() {
		return "DefFuncion [tipo=" + tipo + ", nombre=" + nombre
				+ ", parametros=" + parametros + ", definiciones="
				+ definiciones + ", sentencias=" + sentencias + "]";
	}

}
