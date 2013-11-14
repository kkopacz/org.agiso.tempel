/* org.agiso.tempel.core.model.beans.TemplateParamBean (15-09-2012)
 * 
 * TemplateParamBean.java
 * 
 * Copyright 2012 agiso.org
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
package org.agiso.tempel.core.model.beans;

import org.agiso.tempel.api.ITemplateParamConverter;
import org.agiso.tempel.api.ITemplateParamValidator;
import org.agiso.tempel.api.model.TemplateParam;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 
 * 
 * @author <a href="mailto:kkopacz@agiso.org">Karol Kopacz</a>
 */
@XStreamAlias("param")
public class TemplateParamBean implements TemplateParam {
	@XStreamAsAttribute
	private String key;
	@XStreamAsAttribute
	private String name;
	@XStreamAsAttribute
	private String type;

	private TemplateParamConverterBean converter;

//	@XStreamAsAttribute
//	@XStreamAlias("converter")
	@XStreamOmitField	// XStreamTempelFileProcessor.TemplateParamConverter
	private Class<? extends ITemplateParamConverter<?>> converterClass;

	private TemplateParamValidatorBean validator;

//	@XStreamAsAttribute
//	@XStreamAlias("validator")
	@XStreamOmitField	// XStreamTempelFileProcessor.TemplateParamConverter
	private Class<? extends ITemplateParamValidator<?>> validatorClass;

	private Boolean fixed;
	private String value;

//	--------------------------------------------------------------------------
	@Override
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@SuppressWarnings("unchecked")
	public <T extends TemplateParamBean> T withKey(String key) {
		this.key = key;
		return (T)this;
	}

	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@SuppressWarnings("unchecked")
	public <T extends TemplateParamBean> T withName(String name) {
		this.name = name;
		return (T)this;
	}

	@Override
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@SuppressWarnings("unchecked")
	public <T extends TemplateParamBean> T withType(String type) {
		this.type = type;
		return (T)this;
	}

	@Override
	public TemplateParamConverterBean getConverter() {
		return converter;
	}
	public void setConverter(TemplateParamConverterBean converter) {
		this.converter = converter;
	}
	@SuppressWarnings("unchecked")
	public <T extends TemplateParamBean> T withConverter(TemplateParamConverterBean converter) {
		this.converter = converter;
		return (T)this;
	}

	@Override
	public Class<? extends ITemplateParamConverter<?>> getConverterClass() {
		if(converter != null) {
			return converter.getConverterClass();
		}
		return converterClass;
	}
	@Override
	public void setConverterClass(Class<? extends ITemplateParamConverter<?>> converterClass) {
		this.converterClass = converterClass;
	}
	@SuppressWarnings("unchecked")
	public <T extends TemplateParamBean> T withConverterClass(Class<? extends ITemplateParamConverter<?>> converterClass) {
		this.converterClass = converterClass;
		return (T)this;
	}

	@Override
	public TemplateParamValidatorBean getValidator() {
		return validator;
	}
	public void setValidator(TemplateParamValidatorBean validator) {
		this.validator = validator;
	}
	@SuppressWarnings("unchecked")
	public <T extends TemplateParamBean> T withValidator(TemplateParamValidatorBean validator) {
		this.validator = validator;
		return (T)this;
	}

	@Override
	public Class<? extends ITemplateParamValidator<?>> getValidatorClass() {
		if(validator != null) {
			return validator.getValidatorClass();
		}
		return validatorClass;
	}
	@Override
	public void setValidatorClass(Class<? extends ITemplateParamValidator<?>> validatorClass) {
		this.validatorClass = validatorClass;
	}
	@SuppressWarnings("unchecked")
	public <T extends TemplateParamBean> T withValidatorClass(Class<? extends ITemplateParamValidator<?>> validatorClass) {
		this.validatorClass = validatorClass;
		return (T)this;
	}

	@Override
	public Boolean getFixed() {
		return fixed;
	}
	@Override
	public void setFixed(Boolean fixed) {
		this.fixed = fixed;
	}
	@SuppressWarnings("unchecked")
	public <T extends TemplateParamBean> T withFixed(Boolean fixed) {
		this.fixed = fixed;
		return (T)this;
	}

	@Override
	public String getValue() {
		return value;
	}
	@Override
	public void setValue(String value) {
		this.value = value;
	}
	@SuppressWarnings("unchecked")
	public <T extends TemplateParamBean> T withValue(String value) {
		this.value = value;
		return (T)this;
	}

//	--------------------------------------------------------------------------
	@Override
	public TemplateParamBean clone() {
		return fillClone(new TemplateParamBean());
	}
	protected TemplateParamBean fillClone(TemplateParamBean clone) {
		clone.key = key;
		clone.name = name;
		clone.type = type;

		if(converter != null) {
			clone.converter = converter.clone();
		}
		clone.converterClass = converterClass;

		if(validator != null) {
			clone.validator = validator.clone();
		}
		clone.validatorClass = validatorClass;

		clone.fixed = fixed;
		clone.value = value;

		return clone;
	}
}