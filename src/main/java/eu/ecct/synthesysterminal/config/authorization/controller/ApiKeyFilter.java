package eu.ecct.synthesysterminal.config.authorization.controller;

import eu.ecct.synthesysterminal.config.authorization.entity.ApiKey;
import eu.ecct.synthesysterminal.config.authorization.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ApiKeyFilter extends GenericFilterBean {

    private final ApiKeyRepository apiKeyRepository;

    @Autowired
    public ApiKeyFilter(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getRequestURI();

        if (!path.startsWith("/api")) {
            chain.doFilter(request, response);
            return;
        }

        String keyString = req.getHeader("Key") == null ? "" : req.getHeader("Key");
        Optional<ApiKey> apiKeyOptional = apiKeyRepository.findByKey(keyString);
        if (apiKeyOptional.isPresent()) {
            chain.doFilter(request, response);
            return;
        }

        resp.reset();
        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);

    }

}
