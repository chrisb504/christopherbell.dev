package dev.christopherbell.libs.common.workflow.operation;

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
public class OperationResult {
  private UUID id;
  private OperationStatus status;
  private Instant createdAt;
  private Instant updatedAt;
}
