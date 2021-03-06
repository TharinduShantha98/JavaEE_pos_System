package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CROSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);
        HttpServletResponse response  = (HttpServletResponse) servletResponse;
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","DELETE");
        response.addHeader("Access-Control-Allow-Methods","PUT");
        response.addHeader("Access-Control-Allow-Headers","X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");



    }

    @Override
    public void destroy() {

    }
}
