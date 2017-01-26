/*
 * Copyright 2014 mattitahvonenitmill.
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
package org.vaadin.viritin.fields;

import org.vaadin.viritin.MSize;

import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.Resource;
import com.vaadin.ui.TextArea;

/**
 *
 * @author mattitahvonenitmill
 */
public class MTextArea extends TextArea {

    private static final long serialVersionUID = 2513695658302975814L;

    public MTextArea() {
        super();
        configureMaddonStuff();
    }

    private void configureMaddonStuff() {
        setNullRepresentation("");
        setWidth("100%");
    }

    public MTextArea(String caption) {
        super(caption);
        configureMaddonStuff();
    }

    public MTextArea(Property dataSource) {
        super(dataSource);
        configureMaddonStuff();
    }

    public MTextArea(String caption, Property dataSource) {
        super(caption, dataSource);
        configureMaddonStuff();
    }

    public MTextArea(String caption, String value) {
        super(caption, value);
    }

    public MTextArea withRows(int rows) {
        setRows(rows);
        return this;
    }

    public MTextArea withFullWidth() {
        setWidth("100%");
        return this;
    }

    public MTextArea withValue(String value) {
        setValue(value);
        return this;
    }

    public MTextArea withSize(MSize mSize) {
        setWidth(mSize.getWidth(), mSize.getWidthUnit());
        setHeight(mSize.getHeight(), mSize.getHeightUnit());
        return this;
    }

    public MTextArea withValidator(Validator validator) {
        setImmediate(true);
        addValidator(validator);
        return this;
    }

    public MTextArea withWidth(String width) {
        setWidth(width);
        return this;
    }

    public MTextArea withWordwrap(boolean wordwrap) {
        setWordwrap(wordwrap);
        return this;
    }

    public MTextArea withNullRepresentation(String nullRepresentation) {
        setNullRepresentation(nullRepresentation);
        return this;
    }

    public MTextArea withTextChangeListener(FieldEvents.TextChangeListener listener) {
        addTextChangeListener(listener);
        return this;
    }

    public MTextArea withValueChangeListener(ValueChangeListener listener) {
        addValueChangeListener(listener);
        return this;
    }

    public MTextArea withBlurListener(FieldEvents.BlurListener listener) {
        addBlurListener(listener);
        return this;
    }

    public MTextArea withStyleName(String... styleNames) {
        for (String styleName : styleNames) {
            addStyleName(styleName);
        }
        return this;
    }

    public MTextArea withIcon(Resource icon) {
        setIcon(icon);
        return this;
    }

    public MTextArea withRequired(boolean required) {
        setRequired(required);
        return this;
    }

    public MTextArea withRequiredError(String requiredError) {
        setRequiredError(requiredError);
        return this;
    }

    public MTextArea withId(String id) {
        setId(id);
        return this;
    }

    public MTextArea withVisible(boolean visible) {
        setVisible(visible);
        return this;
    }
}
