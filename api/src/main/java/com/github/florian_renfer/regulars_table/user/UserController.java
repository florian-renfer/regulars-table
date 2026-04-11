package com.github.florian_renfer.regulars_table.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

  @GetMapping("/users/me")
  public String getCurrentUser(@AuthenticationPrincipal DefaultOidcUser principal) {
    return principal.getGivenName();
  }
}
