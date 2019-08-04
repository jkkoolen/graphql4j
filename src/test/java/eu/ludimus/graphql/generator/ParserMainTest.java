package eu.ludimus.graphql.generator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserMainTest {
    static {

    }
    @Autowired
    private ParserMain parserMain;

    @Test
    public void parseTest() throws IOException {
        assertNotNull(parserMain);
    }
}
