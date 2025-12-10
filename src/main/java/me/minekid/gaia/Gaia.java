package me.minekid.gaia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Consumer;

import me.minekid.gaia.ast.Program;
import me.minekid.gaia.runtime.GaiaNull;
import me.minekid.gaia.bytecode.Bytecode;
import me.minekid.gaia.runtime.GaiaValue;
import me.minekid.gaia.vm.GaiaInterpreter;
import me.minekid.gaia.parser.GaiaParserFacade;
import me.minekid.gaia.bytecode.BytecodeEmitter;

public final class Gaia {
    private Gaia() {
    }

    public static Program parse(String source) {
        Objects.requireNonNull(source, "source");
        GaiaParserFacade parser = new GaiaParserFacade();

        return parser.parse(source);
    }

    public static Bytecode compile(String source) {
        Program program = parse(source);

        if (program == null) {
            return null;
        }

        BytecodeEmitter emitter = new BytecodeEmitter();

        return emitter.compile(program);
    }

    public static GaiaInterpreter newInterpreter(Bytecode bytecode) {
        Objects.requireNonNull(bytecode, "bytecode");

        return new GaiaInterpreter(bytecode);
    }

    public static GaiaValue run(String source) {
        Bytecode bytecode = compile(source);

        if (bytecode == null) {
            return GaiaNull.INSTANCE;
        }

        GaiaInterpreter interpreter = newInterpreter(bytecode);

        return interpreter.execute();
    }

    public static GaiaValue run(String source, Consumer<GaiaInterpreter> configure) {
        Objects.requireNonNull(configure, "configure");
        Bytecode bytecode = compile(source);

        if (bytecode == null) {
            return GaiaNull.INSTANCE;
        }

        GaiaInterpreter interpreter = newInterpreter(bytecode);
        configure.accept(interpreter);

        return interpreter.execute();
    }

    public static GaiaValue runFile(Path path) throws IOException {
        Objects.requireNonNull(path, "path");
        String source = Files.readString(path);

        return run(source);
    }

    public static GaiaValue runFile(Path path, Consumer<GaiaInterpreter> configure) throws IOException {
        Objects.requireNonNull(path, "path");
        Objects.requireNonNull(configure, "configure");
        String source = Files.readString(path);

        return run(source, configure);
    }

    public static Bytecode compileFile(Path path) throws IOException {
        Objects.requireNonNull(path, "path");
        String source = Files.readString(path);

        return compile(source);
    }
}
