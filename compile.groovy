import groovy.transform.CompileStatic

import static org.codehaus.groovy.control.customizers.builder.CompilerCustomizationBuilder.withConfig

withConfig(configuration) {
    ast(CompileStatic)
}