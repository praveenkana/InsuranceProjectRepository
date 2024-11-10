package com.nt.ms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate")
public class CititzenValidationController {

	@GetMapping("/citizen/{idNo}")
	public ResponseEntity<String> getStateByIdValue(@PathVariable Long idNo) {

		// dummy module to check whether the citizen is valid or not

		if (String.valueOf(idNo).length() != 12)
			return new ResponseEntity<String>("invalid id value", HttpStatus.BAD_REQUEST);

		// get state name

		String stateName = null;
		int stateCode = (int) (idNo % 100);
		if (stateCode == 01)
			stateName = "kerala";
		else if (stateCode == 02)
			stateName = "karnataka";
		else if (stateCode == 03)
			stateName = "tamilnadu";
		else if (stateCode == 04)
			stateName = "andhra pradesh";
		else if (stateCode == 05)
			stateName = "telengana";
		else if (stateCode == 06)
			stateName = "goa";
		else
			stateName = "invalid id";
		
		return new ResponseEntity<String>(stateName, HttpStatus.OK);

	}

}
