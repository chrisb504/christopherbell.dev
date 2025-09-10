package dev.christopherbell.whatsforlunch.workflow;

import dev.christopherbell.libs.common.workflow.model.WorkflowType;

/**
 * Enum representing the different workflow types for the WhatsForLunch application.
 */
public enum WhatsForLunchWorkflowType implements WorkflowType {
  /**
   * Workflow type for the WhatsForLunch workflow.
   */
  WHATS_FOR_LUNCH_WORKFLOW;

  @Override
  public String getType() {
    return WHATS_FOR_LUNCH_WORKFLOW.name();
  }
}
