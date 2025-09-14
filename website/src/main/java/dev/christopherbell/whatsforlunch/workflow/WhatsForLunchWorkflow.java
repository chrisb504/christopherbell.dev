package dev.christopherbell.whatsforlunch.workflow;

import dev.christopherbell.libs.workflow.Workflow;
import dev.christopherbell.libs.workflow.exception.WorkflowException;
import dev.christopherbell.libs.workflow.exception.WorkflowStopExecutionException;
import dev.christopherbell.libs.workflow.model.WorkflowContext;
import dev.christopherbell.libs.workflow.model.WorkflowResult;
import dev.christopherbell.libs.workflow.model.WorkflowStatus;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Component;

/**
 * Workflow implementation for determining what's for lunch.
 */
@Component
public class WhatsForLunchWorkflow implements Workflow {

  @Override
  public WorkflowResult execute(WorkflowContext ctx) throws WorkflowException {
    if (!(ctx instanceof WhatsForLunchWorkflowContext whatsForLunchWorkflowContext)) {
      throw new WorkflowStopExecutionException(
          "Invalid context type provided to WhatsForLunchWorkflow. Expected WhatsForLunchWorkflowContext."
      );
    }

    var now = Instant.now();
    return WhatsForLunchWorkflowResult.builder()
        .createdAt(now)
        .updatedAt(now)
        .id(UUID.randomUUID())
        .status(WorkflowStatus.COMPLETED)
        .build();
  }
}
