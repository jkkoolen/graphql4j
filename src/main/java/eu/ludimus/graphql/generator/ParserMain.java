package eu.ludimus.graphql.generator;

import graphql.language.Document;
import graphql.parser.Parser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;

@Slf4j
@SpringBootApplication(scanBasePackages = "eu.ludimus.graphql")
public class ParserMain implements CommandLineRunner {

    private final GeneratorVisitor visitor;

    public ParserMain(GeneratorVisitor visitor) {
        this.visitor = visitor;
    }

    public static void main(String[] args) {
        SpringApplication.run(ParserMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length != 2) {
            log.error("ParserMain <schemapath> <packageName>");
            return;
        }
        Parser parser = new Parser();
        Document document = parser.parseDocument(new FileReader(args[0]));
        document.getDefinitions().forEach(definition -> visitor.visit(definition));
        visitor.generate(args[1]);
        System.exit(0);
    }
}
