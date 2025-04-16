package com.edu.asistenteCupos.Utils;

import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PromptTemplateProvider {
  private ClasspathResourceLoader loader;

  public PromptTemplateProvider(ClasspathResourceLoader loader) {
    this.loader = loader;
  }

  public Resource systemResource() {
    String systemPathName = "prompts/system-template.md";
    return loader.comoRecurso(systemPathName);
  }

  public Resource userResource() {
    String userPathName = "prompts/user-template.md";
    return loader.comoRecurso(userPathName);
  }
}