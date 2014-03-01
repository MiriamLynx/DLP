package ast;

public abstract class AbstractAst {

	public int linea;
	public int columna;
	
	public AbstractAst(int linea, int columna) {
		this.linea=linea;
		this.columna=columna;
	}
	
	public int getLinea(){
		return this.linea;
	}
	public int getColumna(){
		
		return this.columna;
	}
}
