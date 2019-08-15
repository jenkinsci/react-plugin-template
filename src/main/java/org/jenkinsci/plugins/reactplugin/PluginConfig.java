/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 GoDaddy Operating Company, LLC.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jenkinsci.plugins.reactplugin;

import hudson.Extension;
import jenkins.model.GlobalConfiguration;
import org.jenkinsci.plugins.reactplugin.model.Todo;

import javax.annotation.CheckForNull;
import java.util.Collections;
import java.util.List;

/**
 * Provides configuration options for this plugin.
 *
 * @author jxpearce@godaddy.com
 */
@Extension(optional = true)
public class PluginConfig extends GlobalConfiguration {

    /**
     * The list of valid times.
     */
    private List<Todo> todos;


    /**
     * Gets the list of excluded dates.
     *
     * @return the list of excluded dates.
     */
    public List<Todo> getTodos() {
        return this.todos == null
                ? Collections.<Todo>emptyList()
                : this.todos;
    }

    /**
     * Sets the list of excluded dates.
     *
     * @param value the list of excluded dates.
     */
    public void setTodos(
            @CheckForNull List<Todo> value) {
        this.todos = value;
        save();
    }
}
