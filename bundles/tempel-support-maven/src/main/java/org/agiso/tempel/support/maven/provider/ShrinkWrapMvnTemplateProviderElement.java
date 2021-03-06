/* org.agiso.tempel.support.maven.provider.ShrinkWrapMvnTemplateProviderElement (19-01-2013)
 * 
 * ShrinkWrapMvnTemplateProviderElement.java
 * 
 * Copyright 2013 agiso.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.agiso.tempel.support.maven.provider;

import static org.agiso.core.lang.util.AnsiUtils.*;
import static org.agiso.core.lang.util.AnsiUtils.AnsiElement.*;
import static org.agiso.tempel.ITempel.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.agiso.core.i18n.annotation.I18n;
import org.agiso.core.i18n.util.I18nUtils.I18nId;
import org.agiso.core.lang.util.StringUtils;
import org.agiso.core.logging.I18nLogger;
import org.agiso.core.logging.util.LogUtils;
import org.agiso.tempel.api.model.Template;
import org.agiso.tempel.support.maven.resolver.ShrinkWrapMvnTempelDependencyResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Karol Kopacz
 * @since 1.0
 */
@Component
public class ShrinkWrapMvnTemplateProviderElement extends AbstractMvnTemplateProviderElement {
	private static final I18nLogger<Logs> supportLogger = LogUtils.getLogger(LOGGER_SUPPORT);
	private static enum Logs implements I18nId {
		@I18n(def = "Resolving Maven artifact for template {0}")
		LOG_01,
	}

//	--------------------------------------------------------------------------
	@Autowired
	private ShrinkWrapMvnTempelDependencyResolver mvnDependencyResolver;

//	--------------------------------------------------------------------------
	@Override
	protected void doInitialize() throws IOException {
	}

	@Override
	protected void doConfigure(Map<String, Object> properties) throws IOException {
		mvnDependencyResolver.doConfigure(properties);

		setActive(true);
	}

//	--------------------------------------------------------------------------
	@Override
	protected List<File> resolve(String fqtn) throws Exception {
		if(supportLogger.isDebugEnabled()) supportLogger.debug(Logs.LOG_01,
				ansiString(GREEN, fqtn)
		);

		StringTokenizer tokenizer = new StringTokenizer(fqtn, ":", false);
		String groupId = tokenizer.nextToken();
		String templateId = tokenizer.nextToken();
		String version = tokenizer.nextToken();

		return mvnDependencyResolver.resolve(groupId, templateId, version);
	}

	@Override
	protected String getTemplatePath(Template<?> template) {
		if(StringUtils.isEmpty(template.getGroupId())) {
			throw new RuntimeException("Szablon SWRAP bez groupId");
		}

		String path = mvnDependencyResolver.getLocalRepositoryPath();
		path = path + '/' + template.getGroupId().replace('.', '/');
		path = path + '/' + template.getTemplateId();
		path = path + '/' + template.getVersion();
		path = path + '/' + template.getTemplateId() + '-' +  template.getVersion() + ".jar";
		return path;
	}
}
