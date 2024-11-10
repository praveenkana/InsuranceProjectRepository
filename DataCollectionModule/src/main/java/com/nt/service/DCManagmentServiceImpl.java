package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bindings.AppRegistrationInputs;
import com.nt.bindings.ChildInputs;
import com.nt.bindings.DCSummaryReport;
import com.nt.bindings.EducationInputs;
import com.nt.bindings.IncomeInputs;
import com.nt.bindings.PlanSelectionInputs;
import com.nt.entity.AppRegistrationEntity;
import com.nt.entity.DC_CaseEntity;
import com.nt.entity.DC_ChildrenEntity;
import com.nt.entity.DC_EducationEntity;
import com.nt.entity.DC_IncomeEntity;
import com.nt.entity.PlanEntity;
import com.nt.repository.IAppRegistrationRepository;
import com.nt.repository.IDCCaseRepository;
import com.nt.repository.IDCIncomeRepository;
import com.nt.repository.IDC_ChildrenRepository;
import com.nt.repository.IDC_EducationRepository;
import com.nt.repository.IPlanRepository;

@Service
public class DCManagmentServiceImpl implements IDCManagmentService {

	@Autowired
	private IDCCaseRepository caserepo;
	@Autowired
	private IAppRegistrationRepository apprepo;
	@Autowired
	private IPlanRepository planrepo;
	@Autowired
	private IDCIncomeRepository incomerepo;
	@Autowired
	private IDC_EducationRepository edrepo;
	@Autowired
	private IDC_ChildrenRepository childrepo;

	@Override
	public Integer generateCaseNo(Integer appId) {

		Optional<AppRegistrationEntity> opt = apprepo.findById(appId);
		if (opt.isPresent()) {
			DC_CaseEntity entity = new DC_CaseEntity();
			entity.setAppId(appId);
			return caserepo.save(entity).getCaseNo();
		}

		return null;
	}

	@Override
	public List<String> showAllPlans() {

		List<PlanEntity> list = planrepo.findAll();
		/*
		 * ArrayList<String>al=new ArrayList(); list.forEach(l->{ String planName =
		 * l.getPlanName(); al.add(planName); }); return al;
		 */
		List<String> planList = list.stream().map(plan -> plan.getPlanName()).toList();
		return planList;
	}

	@Override
	public Integer savePlanSelection(PlanSelectionInputs inputs) {

		Optional<DC_CaseEntity> opt = caserepo.findById(inputs.getCaseNo());

		if (opt.isPresent()) {
			DC_CaseEntity caseEntity = opt.get();
			caseEntity.setPlanId(inputs.getPlanId());
			return caserepo.save(caseEntity).getCaseNo();
			// updated the case entity with plan id and return case no
		}

		return null;
	}

	@Override
	public Integer saveIncomeDetails(IncomeInputs inputs) {
		DC_IncomeEntity entity = new DC_IncomeEntity();
		BeanUtils.copyProperties(inputs, entity);
		incomerepo.save(entity);

		return entity.getCaseNo();
	}

	@Override
	public Integer saveEducationDetails(EducationInputs inputs) {
		DC_IncomeEntity entity = new DC_IncomeEntity();
		BeanUtils.copyProperties(inputs, entity);
		incomerepo.save(entity);
		return entity.getCaseNo();
	}

	@Override
	public Integer saveChildrenDetails(List<ChildInputs> inputs) {
		inputs.forEach(child -> {
			DC_ChildrenEntity entity = new DC_ChildrenEntity();
			BeanUtils.copyProperties(child, entity);
			childrepo.save(entity);
		});
		return inputs.get(0).getCaseNo();
	}

	@Override
	public DCSummaryReport showDataCollectionReport(Integer caseNo) {

		DC_IncomeEntity incomeEntity = incomerepo.findByCaseNo(caseNo);
		DC_EducationEntity educationEntity = edrepo.findByCaseNo(caseNo);
		List<DC_ChildrenEntity> childList = childrepo.findByCaseNo(caseNo);
		Optional<DC_CaseEntity> opt = caserepo.findById(caseNo);
		Integer planId = null;
		String planName = null;
		AppRegistrationEntity registrationEntity = null;
		Integer appId = null;
		if (opt.isPresent()) {
			DC_CaseEntity entity = opt.get();
			planId = entity.getPlanId();
			appId = entity.getAppId();
			Optional<PlanEntity> opt2 = planrepo.findById(planId);
			if (opt2.isPresent()) {
				planName = opt2.get().getPlanName();
			}
			Optional<AppRegistrationEntity> opt3 = apprepo.findById(appId);
			if (opt3.isPresent()) {
				registrationEntity = opt3.get();
			}
		}
		// convert entity class objects to binding class objects
		AppRegistrationInputs appInputs = new AppRegistrationInputs();
		BeanUtils.copyProperties(registrationEntity, appInputs);
		IncomeInputs income = new IncomeInputs();
		BeanUtils.copyProperties(incomeEntity, income);
		EducationInputs education = new EducationInputs();
		BeanUtils.copyProperties(educationEntity, education);
		List<ChildInputs> childlist = new ArrayList();
		childList.forEach(children -> {
			ChildInputs child = new ChildInputs();
			BeanUtils.copyProperties(children, child);
			childlist.add(child);

		});

		DCSummaryReport report = new DCSummaryReport();
		report.setChildDetails(childlist);
		report.setEdDetails(education);
		report.setIncomeDetails(income);
		report.setPlanName(planName);
		report.setAppinputs(appInputs);

		return report;
	}

}
