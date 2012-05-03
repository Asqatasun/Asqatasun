/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2012  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.mock;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 *
 * @author jkowalczyk
 */
public class MockBindingResult implements BindingResult{

    private boolean hasErrors = false;
    
    @Override
    public Object getTarget() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, Object> getModel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getRawFieldValue(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PropertyEditor findEditor(String string, Class type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PropertyEditorRegistry getPropertyEditorRegistry() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addError(ObjectError oe) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] resolveMessageCodes(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void recordSuppressedField(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] getSuppressedFields() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getObjectName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setNestedPath(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getNestedPath() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void pushNestedPath(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void popNestedPath() throws IllegalStateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reject(String string) {
        hasErrors = true;
    }

    @Override
    public void reject(String string, String string1) {
        hasErrors = true;
    }

    @Override
    public void reject(String string, Object[] os, String string1) {
        hasErrors = true;
    }

    @Override
    public void rejectValue(String string, String string1) {
        hasErrors = true;
    }

    @Override
    public void rejectValue(String string, String string1, String string2) {
        hasErrors = true;
    }

    @Override
    public void rejectValue(String string, String string1, Object[] os, String string2) {
        hasErrors = true;
    }

    @Override
    public void addAllErrors(Errors errors) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasErrors() {
        return hasErrors;
    }

    @Override
    public int getErrorCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ObjectError> getAllErrors() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasGlobalErrors() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getGlobalErrorCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ObjectError> getGlobalErrors() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ObjectError getGlobalError() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasFieldErrors() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFieldErrorCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<FieldError> getFieldErrors() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FieldError getFieldError() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasFieldErrors(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getFieldErrorCount(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<FieldError> getFieldErrors(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FieldError getFieldError(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getFieldValue(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Class getFieldType(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
