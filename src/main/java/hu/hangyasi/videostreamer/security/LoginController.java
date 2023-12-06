package hu.hangyasi.videostreamer.security;

import java.time.Instant;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

  private final JwtEncoder encoder;

  public LoginController(JwtEncoder encoder) {
    this.encoder = encoder;
  }

  @GetMapping
  public String auth(Authentication authentication) {
    Instant now = Instant.now();
    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("self")
        .issuedAt(now)
        .expiresAt(Instant.now().plusSeconds(86400L))
        .subject(authentication.getName())
        .build();
    return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

}
