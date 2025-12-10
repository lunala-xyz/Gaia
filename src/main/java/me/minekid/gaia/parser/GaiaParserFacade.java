package me.minekid.gaia.parser;

import java.util.List;
import java.util.ArrayList;

import me.minekid.gaia.ast.Program;
import me.minekid.gaia.antlr.GaiaLexer;
import me.minekid.gaia.antlr.GaiaParser;

import org.antlr.v4.runtime.*;

public class GaiaParserFacade {
    private final List<ParseError> errors = new ArrayList<>();

    /**
     * Parse Gaia source code and return a Program AST.
     *
     * @param source the source code to parse
     * @return the parsed Program, or null if parsing failed
     */
    public Program parse(String source) {
        return parse(source, "<unknown>");
    }

    /**
     * Parse Gaia source code and return a Program AST.
     *
     * @param source   the source code to parse
     * @param fileName the name of the source file (for error reporting)
     * @return the parsed Program, or null if parsing failed
     */
    public Program parse(String source, String fileName) {
        errors.clear();

        GaiaLexer lexer = new GaiaLexer(CharStreams.fromString(source));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new ErrorCollector(fileName));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GaiaParser parser = new GaiaParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ErrorCollector(fileName));

        GaiaParser.ProgContext tree = parser.prog();

        if (!errors.isEmpty()) {
            return null;
        }

        AstBuilder builder = new AstBuilder(source);
        return builder.visitProg(tree);
    }

    public List<ParseError> getErrors() {
        return new ArrayList<>(errors);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    private class ErrorCollector extends BaseErrorListener {
        private final String fileName;

        public ErrorCollector(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            errors.add(new ParseError(fileName, line, charPositionInLine, msg));
        }
    }
}
