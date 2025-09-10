package dev.christopherbell.libs.common.workflow;

import dev.christopherbell.libs.common.workflow.exception.WorkflowRetryableException;
import dev.christopherbell.libs.common.workflow.exception.WorkflowStopExecutionException;
import dev.christopherbell.libs.common.workflow.model.WorkflowContext;
import dev.christopherbell.libs.common.workflow.model.WorkflowResult;
import dev.christopherbell.libs.common.workflow.model.WorkflowStatus;
import dev.christopherbell.libs.common.workflow.operation.Operation;
import dev.christopherbell.libs.common.workflow.operation.OperationResult;
import dev.christopherbell.libs.common.workflow.operation.OperationStatus;
import dev.christopherbell.libs.common.workflow.operation.WorkflowEngine;
import java.time.Instant;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Represents the engine responsible for executing workflows.
 */
@Slf4j
@Service
public class WorkflowExecutor implements WorkflowEngine {

  /**
   * Executes the given operation with the provided context.
   *
   * @param operation the operation to be executed
   * @param context the context for the operation execution
   */
  public OperationResult executeOperation(Operation operation, WorkflowContext context) {
    OperationResult result;
    var operationName = operation.getOperationName();
    try {
      log.info("Starting execution of operation: {}", operationName);
      result = operation.execute(context);
      context.getOperationHistory().put(operationName, result.getStatus());
      log.info("Successfully completed execution of operation: {}", operationName);
    } catch (Exception e) {
      log.error("Error occurred during operation execution: {}. Operation: {}", e.getMessage(), operationName, e);
      context.getOperationHistory().put(operationName, OperationStatus.FAILED);
      var now = Instant.now();
      result = OperationResult.builder()
          .id(UUID.randomUUID())
          .createdAt(now)
          .updatedAt(now)
          .status(OperationStatus.FAILED)
          .build();
    }
    return result;
  }

  /**
   * Executes the given workflow with the provided context.
   *
   * @param workflow the workflow to be executed
   * @param context the context for the workflow execution
   */
  public WorkflowResult executeWorkflow(Workflow workflow, WorkflowContext context) {
    WorkflowResult result;
    var workflowName = workflow.getWorkflowName();
     try {
       log.info("Starting execution of workflow: {}", workflowName);
       result = workflow.execute(context);
       handleWorkflowSuccessState(context);
       log.info("Successfully completed execution of workflow: {}", workflowName);
     } catch (WorkflowRetryableException e) {
       log.warn(
           "Retryable error occurred during workflow execution: {}. Retrying workflow: {}",
           e.getMessage(),
           workflowName
       );
       var now = Instant.now();
       result = WorkflowResult.builder()
           .id(UUID.randomUUID())
           .createdAt(now)
           .updatedAt(now)
           .status(WorkflowStatus.RETRYABLE_FAILURE)
           .build();
       retryWorkflow(workflow, context);
     } catch (WorkflowStopExecutionException e) {
       log.error("Stopping workflow execution due to stop execution exception: {}", e.getMessage());
       handleWorkflowStopExecutionException(e, workflowName, context);
       var now = Instant.now();
       result = WorkflowResult.builder()
           .id(UUID.randomUUID())
           .createdAt(now)
           .updatedAt(now)
           .status(WorkflowStatus.STOPPED)
           .build();

     } catch (Exception e) {
       log.error("Stopping workflow: Unexpected error occurred during workflow execution: {}", e.getMessage(), e);
       var now = Instant.now();
       result = WorkflowResult.builder()
           .id(UUID.randomUUID())
           .createdAt(now)
           .updatedAt(now)
           .status(WorkflowStatus.FAILED)
           .build();
     } finally {
       saveContext(context);
     }
     return result;
  }

  public void retryWorkflow(Workflow workflow, WorkflowContext context) {
    // Implementation for retrying the workflow goes here
    log.info("Retrying workflow: {} with context: {}", workflow.getWorkflowName(), context);
  }

  public void stopWorkflowExecution(String workflowName) {
    // Implementation for stopping the workflow execution goes here
  }

  /**
   * Handles the successful completion of a workflow and updates the workflow context accordingly.
   *
   * @param context the current workflow context
   * @return the updated workflow context after handling the success state
   */
  public WorkflowContext handleWorkflowSuccessState(WorkflowContext context) {
    context.setStatus(WorkflowStatus.COMPLETED);
    return context;
  }

  /**
   * Handles the workflow stop execution exception and updates the workflow context accordingly.
   *
   * @param e the exception that caused the workflow to stop execution
   * @param workflowName the name of the workflow that encountered the exception
   * @param context the current workflow context
   * @return the updated workflow context after handling the exception
   */
  public WorkflowContext handleWorkflowStopExecutionException(
      Exception e,
      String workflowName,
      WorkflowContext context
  ) {
    // Implementation for handling workflow stop execution exception goes here
    log.error("Handling workflow stop execution exception for workflow: {} with context: {}", workflowName, context, e);
    context.setStatus(WorkflowStatus.STOPPED);

    return context;
  }

  public void monitorWorkflowStatus(String workflowName) {
        // Implementation for monitoring workflow status goes here
  }

  public void notifyWorkflowCompletion(String workflowName) {
        // Implementation for notifying workflow completion goes here
  }

  /**
   * Saves the current state of the workflow context.
   *
   * @param context the workflow context to be saved
   * @return the saved workflow context
   */
  public WorkflowContext saveContext(WorkflowContext context) {
        // Implementation for saving the workflow context goes here
        log.info("Saving workflow context: {}", context);
        return context;
  }

}
