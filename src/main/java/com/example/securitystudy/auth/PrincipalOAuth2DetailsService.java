package com.example.securitystudy.auth;

import com.example.securitystudy.auth.model.PrincipalDetails;
import com.example.securitystudy.user.UserProvider;
import com.example.securitystudy.user.UserService;
import com.example.securitystudy.user.model.User;
import com.example.securitystudy.util.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PrincipalOAuth2DetailsService extends DefaultOAuth2UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserProvider userProvider;
    private final UserService userService;

    @Autowired
    public PrincipalOAuth2DetailsService(PasswordEncoder passwordEncoder, UserProvider userProvider, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userProvider = userProvider;
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String username = oAuth2User.getAttributes().get("name").toString();
        String nickname = oAuth2User.getAttributes().get("name").toString();
        String email = oAuth2User.getAttributes().get("email").toString();
        String password = passwordEncoder.encode(email);
        String role = "ROLE_USER";
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String provider_id = oAuth2User.getAttributes().get("sub").toString();

        User user = null;

        try {
            if ( userProvider.checkEmail(email) == 0) {
                log.info("구글 로그인이 최초입니다. 회원가입을 진행합니다.");
                user = new User(username, nickname, email, password, role, provider, provider_id);
                userService.createUser(user);
            }else {
                log.info("구글 로그인 기록이 있습니다.");
                user = userProvider.retrieveByEmail(email);
            }
        } catch (BaseException e) {
            throw new RuntimeException(e);
        }

        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}
