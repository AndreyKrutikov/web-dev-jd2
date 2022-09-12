package by.krutikov.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(filterName = "AdminTokenFilter")
public class AdminTokenFilter implements Filter {
    static Logger log = Logger.getLogger(AdminTokenFilter.class);
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest castedRequest = (HttpServletRequest) request;
        String admin = castedRequest.getHeader("admin");
        if (StringUtils.isNotBlank(admin)) {
            log.info(String.format("Admin token found, %s", admin));
        } else {
            log.info("nothing found!");
        }
        chain.doFilter(request, response);
    }
}
