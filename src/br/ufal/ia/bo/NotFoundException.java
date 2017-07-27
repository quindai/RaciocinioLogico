package br.ufal.ia.bo;

public
class NotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3025241092519399670L;

	public NotFoundException(String message) {
		//gambiarra- corrigir depois
		super(message);
	}
	public NotFoundException(String message, int i) {
		super(message);
	}
	
}
