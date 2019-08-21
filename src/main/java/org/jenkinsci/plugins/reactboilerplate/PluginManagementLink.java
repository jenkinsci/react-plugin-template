package org.jenkinsci.plugins.reactboilerplate;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import hudson.Extension;
import hudson.model.ManagementLink;
import org.kohsuke.stapler.StaplerProxy;

import javax.annotation.CheckForNull;

@Extension
public class PluginManagementLink extends ManagementLink implements StaplerProxy {

    @Inject
    PluginUI webapp;

    @CheckForNull
    @Override
    public String getIconFileName() {
        return "/plugin/react-boilerplate/images/icon.png";
    }

    @CheckForNull
    @Override
    public String getDisplayName() {
        return "react-boilerplate";
    }

    @CheckForNull
    @Override
    public String getUrlName() {
        return "react-boilerplate";
    }

    @Override
    public Object getTarget() {
        return webapp;
    }

    @Extension
    public static class ModuleImpl implements Module {

        @Override
        public void configure(Binder binder) {
            binder.bind(PluginUI.class).toInstance(new PluginUI());
        }
    }
}

