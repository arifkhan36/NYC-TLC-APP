/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.language.cpp.internal;

import org.gradle.api.Action;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.FileCollection;
import org.gradle.api.file.FileTree;
import org.gradle.api.internal.file.FileOperations;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.util.PatternSet;
import org.gradle.internal.Cast;
import org.gradle.language.cpp.CppBinary;
import org.gradle.language.cpp.CppComponent;
import org.gradle.language.internal.DefaultBinaryCollection;
import org.gradle.language.nativeplatform.internal.ComponentWithNames;
import org.gradle.language.nativeplatform.internal.DefaultNativeComponent;
import org.gradle.language.nativeplatform.internal.Names;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.concurrent.Callable;

public abstract class DefaultCppComponent extends DefaultNativeComponent implements CppComponent, ComponentWithNames {
    private final FileCollection cppSource;
    private final String name;
    private final FileOperations fileOperations;
    private final ConfigurableFileCollection privateHeaders;
    private final FileCollection privateHeadersWithConvention;
    private final Property<String> baseName;
    private final Names names;
    private final Configuration implementation;
    private final DefaultBinaryCollection<CppBinary> binaries;

    @Inject
    public DefaultCppComponent(String name, FileOperations fileOperations, ObjectFactory objectFactory, ConfigurationContainer configurations) {
        super(fileOperations);
        this.name = name;
        this.fileOperations = fileOperations;
        cppSource = createSourceView("src/" + name + "/cpp", Arrays.asList("cpp", "c++", "cc"));
        privateHeaders = fileOperations.files();
        privateHeadersWithConvention = createDirView(privateHeaders, "src/" + name + "/headers");
        baseName = objectFactory.property(String.class);

        names = Names.of(name);
        implementation = configurations.create(names.withSuffix("implementation"));
        implementation.setCanBeConsumed(false);
        implementation.setCanBeResolved(false);

        binaries = Cast.uncheckedCast(objectFactory.newInstance(DefaultBinaryCollection.class, CppBinary.class));
    }

    @Override
    public Names getNames() {
        return names;
    }

    @Override
    public String getName() {
        return name;
    }

    protected FileCollection createDirView(final ConfigurableFileCollection dirs, final String conventionLocation) {
        return fileOperations.files(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                if (dirs.getFrom().isEmpty()) {
                    return fileOperations.files(conventionLocation);
                }
                return dirs;
            }
        });
    }

    @Override
    public Property<String> getBaseName() {
        return baseName;
    }

    @Override
    public FileCollection getCppSource() {
        return cppSource;
    }

    @Override
    public ConfigurableFileCollection getPrivateHeaders() {
        return privateHeaders;
    }

    @Override
    public void privateHeaders(Action<? super ConfigurableFileCollection> action) {
        action.execute(privateHeaders);
    }

    @Override
    public FileCollection getPrivateHeaderDirs() {
        return privateHeadersWithConvention;
    }

    @Override
    public Configuration getImplementationDependencies() {
        return implementation;
    }

    @Override
    public FileTree getHeaderFiles() {
        return getAllHeaderDirs().getAsFileTree().matching(new PatternSet().include("**/*.h"));
    }

    public FileCollection getAllHeaderDirs() {
        return privateHeadersWithConvention;
    }

    @Override
    public DefaultBinaryCollection<CppBinary> getBinaries() {
        return binaries;
    }
}
