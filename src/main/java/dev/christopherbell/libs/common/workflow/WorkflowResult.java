package dev.christopherbell.libs.common.workflow;

import dev.christopherbell.libs.common.workflow.model.WorkflowStatus;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class WorkflowResult {
  private UUID id;
  private WorkflowStatus status;
  private Instant createdAt;
  private Instant updatedAt;
}
