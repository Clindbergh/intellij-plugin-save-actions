package com.dubreuia.processors;

import com.dubreuia.model.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;

import java.util.ArrayList;
import java.util.List;

import static com.dubreuia.model.Action.activate;
import static com.dubreuia.model.Action.activateOnShortcut;

public enum ProcessorFactory {

    INSTANCE;

    public List<Processor> getSaveActionsProcessors(Project project, PsiFile psiFile, Storage storage) {
        List<Processor> processors = new ArrayList<Processor>();
        if (storage.isEnabled(activate) || storage.isEnabled(activateOnShortcut)) {
            processors.add(new OptimizeImportsProcessor(project, psiFile, storage));
            processors.add(new ReformatCodeProcessor(project, psiFile, storage));
            processors.add(new RearrangeCodeProcessor(project, psiFile, storage));
            processors.add(new CompileProcessor(project, psiFile, storage));
        }
        return processors;
    }

}
