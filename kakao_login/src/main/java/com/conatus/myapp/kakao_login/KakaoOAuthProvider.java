
package com.conatus.myapp.kakao_login;

// ClientAuthenticationMethod
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
// ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistration;
// AuthorizationGrantType
import org.springframework.security.oauth2.core.AuthorizationGrantType;

public enum KakaoOAuthProvider {


    KAKAO {
        @Override
        public ClientRegistration.Builder getBuilder(String registerationId) {
            ClientRegistration.Builder build = getBuilder(registerationId, ClientAuthenticationMethod.CLIENT_SECRET_POST, DEFAULT_REDIRECT_URL);

            build.scope("account_email");
            build.authorizationUri("https://kauth.kakao.com/oauth/authorize");
            build.tokenUri("https://kauth.kakao.com/oauth/token");
            build.userInfoUri("https://kapi.kakao.com/v2/user/me");
            build.userNameAttributeName("id");
            build.clientName("Kakao");

            return build;
        }


    };

    private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/login/oauth2/code/{registrationId}";

    protected final ClientRegistration.Builder getBuilder(String registrationId,
                                                          ClientAuthenticationMethod method, String redirectUrl) {

        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUri(redirectUrl);
        return builder;
    }


    public abstract ClientRegistration.Builder getBuilder(String registerationId);
}
