package controlDeErrores;

import java.util.List;

import tipos.TipoError;

public class ME {

	private List<TipoError> errores;

	private static ME instance;

	private ME() {

	}

	public static ME getInstance() {
		if (instance == null) {
			instance = new ME();
		}
		return instance;
	}

	public void añadirError(TipoError e) {
		errores.add(e);
	}

	public void mostrarErrores() {
		for (TipoError e : errores) {
			System.out.println(e.toString());
		}
	}

	public boolean hayErrores() {
		return errores.size() != 0;
	}

	public List<TipoError> getErrores() {
		return errores;
	}

	public void setErrores(List<TipoError> errores) {
		this.errores = errores;
	}

}
