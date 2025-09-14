package dev.christopherbell.libs.workflow;

import dev.christopherbell.libs.workflow.model.WorkflowContext;
import dev.christopherbell.libs.workflow.model.WorkflowResult;
import dev.christopherbell.libs.workflow.operation.Operation;
import dev.christopherbell.libs.workflow.operation.OperationResult;
import dev.christopherbell.libs.workflow.retry.RetryPolicy;

/**
 * Interface representing a workflow engine capable of executing operations and workflows.
 */
public interface WorkflowEngine {

  /**
   * Executes the given operation within the provided workflow context.
   *
   * @param operation the operation to be executed
   * @param context the workflow context to be used for execution
   * @return the result of the operation execution
   */
  OperationResult executeOperation(Operation operation, WorkflowContext context);

  /**
   * Executes the given workflow with retry logic based on the provided retry policy.
   *
   * @param retryPolicy the retry policy to be applied for workflow execution
   * @param workflow the workflow to be executed
   * @param context the workflow context to be used for execution
   * @return the result of the workflow execution with retry information
   */
  WorkflowResult executeWorkflowWithRetry(RetryPolicy retryPolicy, Workflow workflow, WorkflowContext context);

  /**
   * Executes the given workflow within the provided workflow context.
   *
   * @param workflow the workflow to be executed
   * @param context the workflow context to be used for execution
   * @return the result of the workflow execution
   */
  WorkflowResult executeWorkflow(Workflow workflow, WorkflowContext context);
}
