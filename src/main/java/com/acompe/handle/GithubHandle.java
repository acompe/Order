package com.acompe.handle;

import com.acompe.pojo.GitHub;
import com.acompe.pojo.User;
import com.acompe.service.GithubService;
import com.acompe.utils.JwtUtil;
import com.acompe.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class GithubHandle implements AuthenticationSuccessHandler {

    @Autowired
    private GithubService githubService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = defaultOAuth2User.getAttributes();
        GitHub gitHub = new GitHub();
        if (attributes.get("id")!=null){
            gitHub.setGitHubId(attributes.get("id").toString());
        }
        if (attributes.get("avatar_url")!=null){
            gitHub.setAvatarUrl(attributes.get("avatar_url").toString());
        }
        if (attributes.get("login")!=null){
            gitHub.setName(attributes.get("login").toString());
        }
        User user = githubService.login(gitHub);

       jwtUtil.generateToken(user);

       httpServletRequest.getRequestDispatcher("/index").forward(httpServletRequest,httpServletResponse);
    }
}
