package name.chenyuelin.service.activity;

import java.util.ArrayList;
import java.util.Collection;

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.stereotype.Service;

@Service
public class ActivitySampleService {
	public Collection<String> getCandidate(ExecutionEntity execution) {
		Collection<String> result = new ArrayList<String>();
		result.add("011");
		return result;
	}
	
	public void doServiceTask(ExecutionEntity execution) {
		System.out.println(execution.getActivityId());
	}
	
	public boolean selectFlow(boolean flag){
		return flag;
	}
}
