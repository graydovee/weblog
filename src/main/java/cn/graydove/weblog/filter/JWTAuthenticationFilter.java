package cn.graydove.weblog.filter;

import cn.graydove.weblog.pojo.JwtUser;
import cn.graydove.weblog.uitls.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 该拦截器用于获取用户登录的信息，只需创建一个token并调用authenticationManager.authenticate()
 * 让spring-security去进行验证就可以了，不用自己查数据库再对比密码了，这一步交给spring去操作。
 */
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info(username+"---"+password);
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象, 所以就是JwtUser
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();

        Map<String, String> map = new HashMap<>();

        map.put("id",String.valueOf(jwtUser.getUser().getUserId()));
        map.put("username",jwtUser.getUser().getUsername());

        String subJson = new ObjectMapper().writeValueAsString(map);
        log.info("jwtUserInfo:" + jwtUser.toString());

        String token = JwtTokenUtils.createToken(subJson,false);
        log.info("token:"+token);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        response.setHeader("Access-Control-Expose-Headers","token");
        response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, token");
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
//        Cookie cookie = new Cookie("token",token);
//        cookie.setMaxAge((int)JwtTokenUtils.EXPIRATION*1000);
//        response.addCookie(cookie);
    }

}
