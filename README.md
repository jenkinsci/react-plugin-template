# Demo Working Hours Plugin Implemented with React

This is the development repo for [Jenkins Working Hours Plugin](https://github.com/jenkinsci/working-hours-plugin), the new features will be updated and tested in this repo.

## Run Standalone Web App
The frontend app is built with Webpack and React, running a standalone web app outside the Jenkins plugin is simple as below:

After the repo is cloned:
```bash
cd ./working-hours-plugin/react 
# This is where our React sub-project located.

yarn install or npm install
# Install the dependencies

yarn run start or npm run start
# Start the dev server
```
Then the web app is running at [http://localhost:3000](http://localhost:3000), any changes in the `app` directory could be sync to the web page at once.

## Send/Fetch Data From the Plugin Service

In order to interact with the plugin service so that we could persistent out data, we need to run the plugin:

```bash
#make sure that you are under the root dir.

mvn package
mvn install
mvn hpi:run 
```

When the jenkins is running, our requests could be proxied to the jenkins service.

