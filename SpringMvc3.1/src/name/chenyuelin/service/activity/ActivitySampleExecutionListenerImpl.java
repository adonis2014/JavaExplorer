package name.chenyuelin.service.activity;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.stereotype.Service;

@Service
public class ActivitySampleExecutionListenerImpl implements ExecutionListener {

	private static final long serialVersionUID = -927737368645484597L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		ExecutionEntity executionEntity=(ExecutionEntity)execution;
		System.out.println(executionEntity.getRevision());
		System.out.println(execution.getEventName());

	}

}
