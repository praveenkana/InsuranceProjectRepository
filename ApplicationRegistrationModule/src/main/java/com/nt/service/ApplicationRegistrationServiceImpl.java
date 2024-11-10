package com.nt.service;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.nt.bindings.AppRegistrationInputs;
import com.nt.entity.AppRegistrationEntity;
import com.nt.repsitory.IAppRegistrationRepository;


@Service
public class ApplicationRegistrationServiceImpl implements IApplicationRegistrationService {
	
	@Autowired
	private IAppRegistrationRepository appRegRepo;
	@Autowired
	private RestTemplate template;
	@Autowired
	private WebClient client;
	
	@Value("${ar.state}")
	private String targetState;
	@Override
	public Integer applicationRegistrationService(AppRegistrationInputs appInputs) throws IllegalIdValueException {
		
		
		
		//external rest web url
		String extUrl="http://localhost:9090/validate/citizen/{idNo}";
		
		/*HttpHeaders headers=new HttpHeaders();
		headers.set("accept", "application/json");
		HttpEntity httpentity=new HttpEntity(headers);
		ResponseEntity<String> response = template.exchange(extUrl, HttpMethod.GET, httpentity, String.class,appInputs.getIdNo());
		String stateName = response.getBody();
		*/
		String stateName = client.get().uri(extUrl,appInputs.getIdNo()).retrieve().bodyToMono(String.class).block();
		
		//register citizen if he is from kerala
		
		if(stateName.equalsIgnoreCase(targetState)) {
			
			AppRegistrationEntity entity= new AppRegistrationEntity();
			BeanUtils.copyProperties(appInputs, entity);
			entity.setStateName(stateName);
			//save the object
			Integer appId = appRegRepo.save(entity).getAppId();
			return appId;
			
		}
		
		throw new IllegalIdValueException("invalid id");
	}

}
