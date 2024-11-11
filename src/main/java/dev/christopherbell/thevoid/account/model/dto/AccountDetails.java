package dev.christopherbell.thevoid.account.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.christopherbell.thevoid.account.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AccountDetails {

  @JsonProperty("id")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Long id;

  @JsonProperty("creationDate")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String creationDate; // The date that the user joined the platform

  @JsonProperty("firstName")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String firstName;

  @JsonProperty("lastName")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String lastName;

  @JsonProperty("lastLoginDate")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String lastLoginDate;

  @JsonProperty("address")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Address address; // Of course this shouldn't be a String. We do need to understand where the user is posting from

  @JsonProperty("phoneNumber")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String phoneNumber;
}
