package cn.graydove.weblog.filter;

import cn.graydove.weblog.uitls.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        // 如果请求头中则查Cookie
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
//            Cookie[] cookies = request.getCookies();
//            if(cookies!=null){
//                for(Cookie cookie:cookies){
//                    if("token".equals(cookie.getName())){
//                        tokenHeader = cookie.getValue();
//                    }
//                }
//            }
            //没有Authorization信息则直接放行了
            if (tokenHeader == null || tokenHeader.equals("")){
                chain.doFilter(request, response);
                return;
            }
        }
        log.info(tokenHeader);

        // 如果请求头中有token，则进行解析，并且设置认证信息
        Map<String, String> map;
        UsernamePasswordAuthenticationToken authentication;
        try {
            map = getUserInfo(tokenHeader);
            authentication = getAuthentication(map.get("username"));
        }catch (Exception e){
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String userId = map.get("id");
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request) {
            @Override
            public String[] getParameterValues(String name) {
                if (name.equals("userId")) {
                    return new String[] { userId };
                }
                return super.getParameterValues(name);
            }
            @Override
            public Enumeration<String> getParameterNames() {
                Set<String> paramNames = new LinkedHashSet<>();
                paramNames.add("userId");
                Enumeration<String> names =  super.getParameterNames();
                while(names.hasMoreElements()) {
                    paramNames.add(names.nextElement());
                }
                return Collections.enumeration(paramNames);
            }
        };

        super.doFilterInternal(requestWrapper, response, chain);
    }

    private HashMap getUserInfo(String tokenHeader){
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        HashMap map = null;
        try {
            map = new ObjectMapper().readValue(username, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String username) {
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        }
        return null;
    }

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }
}
