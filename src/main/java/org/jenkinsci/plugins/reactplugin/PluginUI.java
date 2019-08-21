package org.jenkinsci.plugins.reactplugin;

import hudson.ExtensionList;
import hudson.security.csrf.CrumbIssuer;
import hudson.util.HttpResponses;
import jenkins.model.Jenkins;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jenkinsci.plugins.reactplugin.model.Todo;
import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.json.JsonHttpResponse;
import org.kohsuke.stapler.StaplerProxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PluginUI implements StaplerProxy {
    private PluginConfig config;

    public PluginUI() {
    }

    @Override
    public Object getTarget() {
        return this;
    }

    /**
     * Handle dynamic routes, and do process on set/get excluded dates and time
     * ranges.
     *
     * @param request Request object passed by Stapler
     * @return A JSON Response that handle back to client.
     */
    public HttpResponse doDynamic(StaplerRequest request) {
        if (config == null) {
            config = ExtensionList.lookup(PluginConfig.class).get(0);
        }

        String restOfPath = request.getRestOfPath();

        String[] pathTokens = restOfPath.split("/");
        List<String> params = new ArrayList<>();

        for (String pathToken : pathTokens) {
            if (pathToken.length() > 0) {
                params.add(pathToken);
            }
        }

        switch (params.get(0)) {
        case "get-todos":
            return getTodos();
        case "set-todos":
            return setTodos(request);
        }

        throw new JsonHttpResponse(new JSONObject());
    }

    /**
     * Stapler's handler for getting the list of todos.
     *
     * @return {@link HttpResponse} Response with data.
     */
    private HttpResponse getTodos() {
        return HttpResponses.okJSON(JSONArray.fromObject(config.getTodos()));
    }

    private HttpResponse setTodos(StaplerRequest request) {
        List<Todo> newTodos = new ArrayList();

        JSONArray todosJson = (JSONArray) getRequestBody(request).get("data");

        for (int i = 0; i < todosJson.size(); i++) {
            newTodos.add(new Todo((JSONObject) todosJson.get(i)));
        }

        config.setTodos(newTodos);

        return getTodos();
    }

    /**
     * Get body from a post request.
     *
     * @param request Request object passed by Stapler
     * @return A JSONObject that contains the request body.
     */
    private JSONObject getRequestBody(StaplerRequest request) {
        JSONObject body;
        try {
            body = JSONObject.fromObject(IOUtils.toString(request.getReader()));
        } catch (IOException e) {
            // TODO: 28/5/2019 Implement a detailed Error Response
            throw new JsonHttpResponse(new JSONObject());
        }
        return body;
    }

    /**
     * Get the crumb token value
     *
     * @return the crumb token value or empty String if no {@link CrumbIssuer}
     */
    public String getCrumbToken() {
        CrumbIssuer crumbIssuer = Jenkins.getInstance().getCrumbIssuer();
        return crumbIssuer == null ? StringUtils.EMPTY : crumbIssuer.getCrumb();
    }

    /**
     * Get the crumb request field
     *
     * @return the crumb request field or empty String if no {@link CrumbIssuer}
     */
    public String getCrumbRequestField() {
        CrumbIssuer crumbIssuer = Jenkins.getInstance().getCrumbIssuer();
        return crumbIssuer == null ? StringUtils.EMPTY : crumbIssuer.getCrumbRequestField();
    }

}
