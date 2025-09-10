package dev.christopherbell.libs.common.workflow;

import dev.christopherbell.libs.common.workflow.exception.WorkflowException;
import dev.christopherbell.libs.common.workflow.model.WorkflowContext;
import dev.christopherbell.libs.common.workflow.model.WorkflowResult;

/**
 * Represents a workflow that can be executed with a given context.
 */
public interface Workflow {

  /**
   * Executes the workflow with the provided context.
   *
   * @param ctx the context to be used for execution
   * @return the updated context after execution
   * @throws WorkflowException if an error occurs during workflow execution
   */
  WorkflowResult execute(WorkflowContext ctx) throws WorkflowException;

  /**
   * Returns the name of the workflow.
   *
   * @return the workflow name
   */
  default String getWorkflowName() {
    return this.getClass().getSimpleName();
  }
}
