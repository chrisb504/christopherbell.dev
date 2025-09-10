package dev.christopherbell.whatsforlunch.workflow;

import dev.christopherbell.libs.common.workflow.Workflow;
import dev.christopherbell.libs.common.workflow.exception.WorkflowException;
import dev.christopherbell.libs.common.workflow.exception.WorkflowStopExecutionException;
import dev.christopherbell.libs.common.workflow.model.WorkflowContext;
import org.springframework.stereotype.Component;

/**
 * Workflow implementation for determining what's for lunch.
 */
@Component
public class WhatsForLunchWorkflow implements Workflow {

  @Override
  public <T extends WorkflowContext> T execute(T ctx) throws WorkflowException {
    if (!(ctx instanceof WhatsForLunchWorkflowContext whatsForLunchWorkflowContext)) {
      throw new WorkflowStopExecutionException(
          "Invalid context type provided to WhatsForLunchWorkflow. Expected WhatsForLunchWorkflowContext."
      );
    }
    whatsForLunchWorkflowContext.getId();
    return null;
  }
}
