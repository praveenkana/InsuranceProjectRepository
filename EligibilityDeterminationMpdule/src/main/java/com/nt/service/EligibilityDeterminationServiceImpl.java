package com.nt.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bindings.EligibilityDetailsOutput;
import com.nt.entity.AppRegistrationEntity;
import com.nt.entity.DC_CaseEntity;
import com.nt.entity.DC_ChildrenEntity;
import com.nt.entity.DC_EducationEntity;
import com.nt.entity.DC_IncomeEntity;
import com.nt.entity.EligibilityDetailsEntity;
import com.nt.entity.PlanEntity;
import com.nt.repository.IAppRegistrationRepository;
import com.nt.repository.IDCCaseRepository;
import com.nt.repository.IDCIncomeRepository;
import com.nt.repository.IDC_ChildrenRepository;
import com.nt.repository.IDC_EducationRepository;
import com.nt.repository.IEligibilityDeterminationRepository;
import com.nt.repository.IPlanRepository;

@Service
public class EligibilityDeterminationServiceImpl implements IEligibilityDeterminationService {

	@Autowired
	private IDCCaseRepository caserepo;
	@Autowired
	private IPlanRepository planrepo;
	@Autowired
	private IDCIncomeRepository incomerepo;
	@Autowired
	private IDC_ChildrenRepository childrepo;
	@Autowired 
	private IAppRegistrationRepository apprepo;
	@Autowired
	private IDC_EducationRepository edrepo;
	@Autowired
	private IEligibilityDeterminationRepository elgirepo;

	Integer appId = null;
	Integer planId = null;
	String planName = null;
	Integer age=null;
	@Override
	public EligibilityDetailsOutput determineEligibility(Integer caseNo) {

		Optional<DC_CaseEntity> opt = caserepo.findById(caseNo);
		if (opt.isPresent()) {
			DC_CaseEntity caseEntity = opt.get();
			appId = caseEntity.getAppId();
			planId = caseEntity.getPlanId();
		}
		//get plan name
		Optional<PlanEntity> opt1 = planrepo.findById(planId);
		PlanEntity planEntity = opt1.get();
		planName = planEntity.getPlanName();
		
		//get age
		Optional<AppRegistrationEntity> opt3 = apprepo.findById(appId);
		if(opt3.isPresent()) {
			AppRegistrationEntity entity = opt3.get();
			LocalDate dob = entity.getDob();
			age=Period.between(LocalDate.now(), dob).getYears();
		}
		
		EligibilityDetailsOutput elgiout=appyPlanConditions(caseNo, planName,age);
		EligibilityDetailsEntity entity=new EligibilityDetailsEntity();
		BeanUtils.copyProperties( elgiout,entity);
		elgirepo.save(entity);
		return elgiout;
	}

	// use a private method to determine eligibility criteria

	private EligibilityDetailsOutput appyPlanConditions(Integer caseNo, String planName,Integer age) {
		EligibilityDetailsOutput elgioutput = new EligibilityDetailsOutput();
		elgioutput.setPlanName(planName);

		DC_IncomeEntity incomeEntity = incomerepo.findByCaseNo(caseNo);
		Double empIncome = incomeEntity.getEmpIncome();
		Double propertyIncome = incomeEntity.getPropertyIncome();

		if (planName.equalsIgnoreCase("HELPING_HANDS")) {
			if (empIncome < 1000.0) {
				elgioutput.setPlanStatus("approved");
				elgioutput.setBenefitAmount(4000.0);
			} else {
				elgioutput.setPlanStatus("denied");
				elgioutput.setDenialReason("highIncome");
			}
		} else if (planName.equalsIgnoreCase("SUPPORT CHILD EDUCATION")) {
			boolean kidsCount = false;
			boolean kidsAgeCondition = true;

			List<DC_ChildrenEntity> childlist = childrepo.findByCaseNo(caseNo);

			if (!childlist.isEmpty()) {
				kidsCount = true;

				/*
				 * childlist.forEach(child->{ int
				 * kidAge=Period.between(LocalDate.now(),child.getChildDob()).getYears(); });
				 */
				for (DC_ChildrenEntity child : childlist) {

					int kidsAge = Period.between(LocalDate.now(), child.getChildDob()).getYears();
					if (kidsAge > 15) {
						kidsAgeCondition = false;
						break;
					}
				}
			}
			if (empIncome <= 1000.0 && kidsCount && kidsAgeCondition) {
				elgioutput.setPlanStatus("approved");
				elgioutput.setBenefitAmount(4000.0);
			} else {
				elgioutput.setPlanStatus("denied");
				elgioutput.setDenialReason("not satisfying the eligibility conditions");
			}

		} else if (planName.equalsIgnoreCase("WIPE OUT POVERTY")) {
			if (empIncome < 500.0 && propertyIncome == 0.0) {
				elgioutput.setPlanStatus("approved");
				elgioutput.setBenefitAmount(3000.0);
			} else {
				elgioutput.setPlanStatus("denied");
				elgioutput.setDenialReason("high income");
			}
		}else if(planName.equalsIgnoreCase("MEDCARE")) {
			if(age>65) {
				elgioutput.setPlanStatus("approved");
				elgioutput.setBenefitAmount(3000.0);
			}else {
				elgioutput.setPlanStatus("denied");
				elgioutput.setDenialReason("not satisfying the eligibility conditions");
			}
			
			
		}else if(planName.equalsIgnoreCase("SUPPORT UNEMPLOYED")) {
			
			DC_EducationEntity edentity = edrepo.findByCaseNo(caseNo);
			int passoutYear=edentity.getPassOutYear();
			if(empIncome==0 && passoutYear<LocalDate.now().getYear()) {
				elgioutput.setPlanStatus("approved");
				elgioutput.setBenefitAmount(2000.0);
			}else {
				elgioutput.setPlanStatus("denied");
				elgioutput.setDenialReason("not satisfying the eligibility conditions");
				
			}
		}
		// if plan approved then validate the plan for two years

		if (elgioutput.getPlanStatus().equalsIgnoreCase("approved")) {
			elgioutput.setPlanStartDate(LocalDate.now());
			elgioutput.setPlanEndDate(LocalDate.now().plusYears(2));
		}

		return elgioutput;

	}

}
