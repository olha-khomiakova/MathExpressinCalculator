package io.javaclasses;


import io.javaclasses.monkey.Monkey;
import io.javaclasses.runtime.IncorrectStatementException;
import io.javaclasses.runtime.RuntimeEnvironment;
import org.slf4j.Logger;
import spark.Request;
import spark.Response;
import spark.Route;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.slf4j.LoggerFactory.getLogger;
import static spark.Spark.*;

public class MySparkServer {
    private final Logger logger = getLogger(MySparkServer.class);
    public void start() {
        staticFiles.location("src/main/java/resources/public");
        port(6060);
        get("/spark", new Route() {
            @Override
            public Object handle(Request req, Response res) throws Exception {
                URL url = MySparkServer.class.getResource("/public/index.html");
                if (logger.isInfoEnabled()) {
                    logger.info(this.getClass()
                            .getSimpleName() + " get started.");
                }
                return new String(Files.readAllBytes(Paths.get(url.toURI())),
                        Charset.defaultCharset());
            }
        });
        post("/spark/cal",
                new Route() {
                    @Override
                    public Object handle(Request req, Response res) throws Exception {
                        String s = req.body();
                        if (logger.isInfoEnabled()) {
                            logger.info(this.getClass()
                                    .getSimpleName() +"  :"+s+s.toCharArray().length);
                        }
                        return calculate(s);
                    }
                });
    }

    private  String calculate(String input) throws
                                            IncorrectStatementException {
        //String code = input.collect(Collectors.joining(System.lineSeparator()));
        Monkey monkey = new Monkey();
        RuntimeEnvironment runtimeEnvironment = new RuntimeEnvironment();
        if (logger.isInfoEnabled()) {
            logger.info(this.getClass()
                    .getSimpleName() +"  :"+input+input.toCharArray().length);
        }
        monkey.interpret(input, runtimeEnvironment);
        return runtimeEnvironment.output().toString();
    }
}
