package org.example.servlet;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
    public static final List<ServletMapping> servletMappings = new ArrayList<>();

    static {
        servletMappings.add(new ServletMapping("helloWorld", "/hello-world", "org.example.servlet.impl.HelloWorldServlet"));
    }
}
