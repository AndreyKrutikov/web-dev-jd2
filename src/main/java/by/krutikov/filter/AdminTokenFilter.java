package by.krutikov.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter(filterName = "AdminTokenFilter")
public class AdminTokenFilter implements Filter {
    static final Logger logger = LogManager.getLogger(AdminTokenFilter.class);

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest castedRequest = (HttpServletRequest)request;
        String admin = castedRequest.getHeader("admin");
        if (StringUtils.isNotBlank(admin)){
            logger.info(String.format("Admin token found, %s", admin));
        }else{
            logger.info("nothing found!");
        }
        chain.doFilter(request, response);
    }
}
