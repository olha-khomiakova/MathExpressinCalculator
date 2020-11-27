package io.javaclasses;

import io.javaclasses.monkey.Monkey;
import io.javaclasses.runtime.IncorrectStatementException;
import io.javaclasses.runtime.RuntimeEnvironment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Servlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Monkey monkey = new Monkey();

        //monkey.interpret();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            writer.println("<h2>Hello from HelloServlet</h2>");
        } finally {
            writer.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
        response.setStatus(200);
        Stream<String> lines = request.getReader().lines();
        try {
            response.getOutputStream().print(calculate(lines));
        } catch (IncorrectStatementException | IllegalStateException e) {
            response.getOutputStream().print("<span style=\"color: red;\">" +e.getMessage()+"</span>");
        }
    }
    private static String calculate(Stream<String> input) throws
            IncorrectStatementException {
        String code = input.collect(Collectors.joining(System.lineSeparator()));
        Monkey monkey = new Monkey();
        RuntimeEnvironment runtimeEnvironment = new RuntimeEnvironment();
        monkey.interpret( code, runtimeEnvironment);
        return runtimeEnvironment.output().toString();
    }
}
