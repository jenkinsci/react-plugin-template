# React Boilerplate for Jenkins Plugin

## Overview
This is a boilerplate project for building Jenkins plugin with React.

This boilerplate is part of the project [Working Hours UI Improvement](https://summerofcode.withgoogle.com/projects/#6112735123734528) during
[Google Summer of Code 2019](https://summerofcode.withgoogle.com/), which uses this pattern to develop Jenkins plugin with React.

## Features

| Feature                                | Summary|
|----------------------------------------|---------------|
|React Integrated | React is intergreted, your can take full control of the UI|
|Using Iframe|Using iframe could create a new javascript env, we can get rid of some side effects of some polyfills which is added gloablly.(such as Prototype.js)|
|Maven Lifecycle|npm commands are integrated into Maven lifecycle with help of [Frontend Maven Plugin](https://github.com/eirslett/frontend-maven-plugin/)|
|Webpack |Webpack helps us reduce the size of the bundle, also avoids pollution on the global namespace.|
|Express as devserver|You can run your react app in a standalone page so you can develop in webpack hot reload mode, also with webpack proxy, the standalone app is still accessible to the jenkins dev server|
|Axios as http client| Axios hugely simplify the way to make requests.


## Getting Started

Clone the repo:
```sh
git clone https://github.com/ShenJack/ReactBoilerplateforJenkinsPlugin.git
cd ReactBoilerplateforJenkinsPlugin
```
Install the Maven dependencies and node modules.
```sh
mvn install -DskipTests
```
Run standalone React app
```sh
npm run start
```
Run plugin
```sh
mvn hpi:run -Dskip.npm -f pom.xml
```
