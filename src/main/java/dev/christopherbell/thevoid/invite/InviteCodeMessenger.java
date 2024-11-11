package dev.christopherbell.thevoid.invite;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class InviteCodeMessenger {

  private final InviteCodeRepository inviteCodeRepository;

  /**
   *
   * @param inviteCodeEntity
   */
  public void saveInviteCode(InviteCodeEntity inviteCodeEntity) {
    this.inviteCodeRepository.save(inviteCodeEntity);
  }
}
