package br.ufal.ia.bo;

import java.awt.image.ReplicateScaleFilter;

public class Interpreter {

	public static String translate(String prem) throws NotFoundException{
		String status = "";
		
		char[] c = prem.replace(System.lineSeparator(), "").
				replaceAll(" ", "").toCharArray();
		switch (c[1]){
			case (char)'\u2192': 
				if (c[0] == c[3]) status = ""+c[2];
			break;
			default:
				throw new NotFoundException(String.format("Premissa não reconhecida:\n%s", 
						prem));
		}
		
		return status;
	}
}
