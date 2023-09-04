package com.alinesno.infra.ops.pipeline.groovy;

import java.util.Map;

import com.alinesno.infra.ops.pipeline.ScriptEngine;
import org.codehaus.groovy.control.CompilerConfiguration;

public class GroovyScriptEngine implements ScriptEngine {

    @Override
    public <T> T invoke(String scriptName, Map<String, Object> scriptParams) {
        return null;
    }

    @Override
    public void setCompilerConfiguration(CompilerConfiguration cc) {

    }
}