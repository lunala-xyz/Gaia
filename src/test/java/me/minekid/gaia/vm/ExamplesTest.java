package me.minekid.gaia.vm;

import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

import me.minekid.gaia.Gaia;
import me.minekid.gaia.ast.Program;
import me.minekid.gaia.runtime.GaiaNull;
import me.minekid.gaia.runtime.GaiaValue;
import me.minekid.gaia.bytecode.Bytecode;
import me.minekid.gaia.bytecode.BytecodeEmitter;
import me.minekid.gaia.parser.GaiaParserFacade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class ExamplesTest {
    boolean executeFile(String path) {
        try {
            Path file = Path.of(path);
            GaiaParserFacade parser = new GaiaParserFacade();
            String source = Files.readString(file);
            Program program = parser.parse(source);

            if (parser.hasErrors()) {
                parser.getErrors().forEach(System.err::println);
                return false;
            }

            BytecodeEmitter emitter = new BytecodeEmitter();
            Bytecode bytecode = emitter.compile(program);

            GaiaInterpreter interpreter = Gaia.newInterpreter(bytecode);

            interpreter.registerNativeFunction(new GaiaNativeFunction("print", 1) {
                @Override
                public GaiaValue call(List<GaiaValue> arguments) {
                    GaiaValue arg = arguments.get(0);

                    interpreter.getLogger().info(arg.toGaiaString());

                    return GaiaNull.INSTANCE;
                }
            });

            Files.writeString(Path.of("./examples/" + file.getFileName().toString().replace(".gaia", ".grb")), bytecode.toString());

            interpreter.execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Test
    @DisplayName("Execute Fibonacci Sequence")
    void executeFibSequence() {
        assert executeFile("./examples/fib.gaia");
    }

    @Test
    @DisplayName("Execute FizzBuzz")
    void executeFizzBuzz() {
        assert executeFile("./examples/fizzbuzz.gaia");
    }

    @Test
    @DisplayName("Execute Bottles")
    void executeBottles() {
        assert executeFile("./examples/bottles.gaia");
    }

    @Test
    @DisplayName("Execute Test")
    void executeTest() {
        assert executeFile("./examples/test.gaia");
    }
}
