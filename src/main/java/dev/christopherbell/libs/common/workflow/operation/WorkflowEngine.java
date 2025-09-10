package dev.christopherbell.libs.common.workflow.operation;

import dev.christopherbell.libs.common.workflow.Workflow;
import dev.christopherbell.libs.common.workflow.model.WorkflowContext;
import dev.christopherbell.libs.common.workflow.model.WorkflowResult;

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
   * Executes the given workflow within the provided workflow context.
   *
   * @param workflow the workflow to be executed
   * @param context the workflow context to be used for execution
   * @return the result of the workflow execution
   */
  WorkflowResult executeWorkflow(Workflow workflow, WorkflowContext context);
}
