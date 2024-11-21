package com.nt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.bindings.PlanDataInputs;
import com.nt.entity.PlanCategory;
import com.nt.entity.PlanEntity;
import com.nt.repository.IPlanCategoryepository;
import com.nt.repository.IPlanRepository;

@Service
public class PlanMgmtServiceImpl implements IplanMgmtService {
	@Autowired
	private IPlanRepository planrepo;
	@Autowired
	private IPlanCategoryepository categoryrepo;

	@Override
	public String registerPlan(PlanDataInputs plan) {
		
	PlanEntity entity=new PlanEntity();
	BeanUtils.copyProperties(plan, entity);
	PlanEntity planEntity = planrepo.save(entity);
	if(planEntity.getPlanId()!=null) {
		return "plan saved with id value: "+planEntity.getPlanId();
	}else
		
		return "problem in saving plan";
	}

	@Override
	public List<PlanDataInputs> showAllPlans() {

		List<PlanEntity> entitylist = planrepo.findAll();
		List<PlanDataInputs> planDataList=new ArrayList<PlanDataInputs>();
		entitylist.forEach(entity->{
			PlanDataInputs plandata=new PlanDataInputs();
			BeanUtils.copyProperties(entity, plandata);
			planDataList.add(plandata);
		});
		
		
		return planDataList;
	}

	@Override
	public PlanDataInputs showPlansById(Integer planId) {
		
		Optional<PlanEntity> opt = planrepo.findById(planId);
		PlanDataInputs planData=new PlanDataInputs();
		if(opt.isPresent()) {
			PlanEntity planEntity = opt.get();
			
			BeanUtils.copyProperties(planEntity, planData);
		}
		return planData;
	}

	@Override
	public String deletePlan(Integer planId) {
		Optional<PlanEntity> opt = planrepo.findById(planId);
		if(opt.isPresent()) {
			planrepo.deleteById(planId);
			return planId+" successfully deleted";
		}
		return planId+" not deleted";
	}

	@Override
	public Map<Integer, String> getPlanCategories() {

		List<PlanCategory> list = categoryrepo.findAll();
		Map<Integer,String> categoryMap=new HashMap<Integer,String>();
		list.forEach(category->{
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		return categoryMap;
	}

	

	@Override
	public String changePlanStatus(Integer planId, String planStatus) {
		Optional<PlanEntity> opt = planrepo.findById(planId);
		if(opt.isPresent()) {
			PlanEntity planEntity = opt.get();
			planEntity.setActiveSw(planStatus);
			planrepo.save(planEntity);
			return "plan status changed successfully";
		}else {
			return "plan status not uodated";
		}
		
	}

	@Override
	public String updatePlan(PlanDataInputs plan) {

		Optional<PlanEntity> opt = planrepo.findById(plan.getPlanId());
		if(opt.isPresent()) {
			//update the object
			PlanEntity entity = opt.get();
			BeanUtils.copyProperties(plan,entity);
			planrepo.save(entity);
			return "plan with plan id "+plan.getPlanId()+" updated successfully";
		}
		
		return "plan with plan id "+plan.getPlanId()+" not updated";
	}

}




