import axios from "axios";
import qs from "qs";
import {UrlConfig} from "./utils/urlConfig";

let axiosInstance = {};

export function stringifyQuery(args) {
  return qs.stringify(args, {
    arrayFormat: "repeat",
    allowDots: true
  });
}

/**
 * Get relative url from the parent page, because the plugin is running in iframe.
 */
function getApiBaseUrl() {
  return window.parent.location.href
}

/**
 * Sending ajax request in iframe would require a crumb in the request's header, this csrf strategy is default open in Jenkins. So we have to get the crumb's header name and token, which is rendered in the iframe tag by the JAVA class PluginUI.
 */
const headers = {};
const crumbHeaderName = UrlConfig.getCrumbHeaderName();

if (crumbHeaderName) {
  headers[crumbHeaderName] = UrlConfig.getCrumbToken();
}

const AXIOS_DEFAULT_CONFIG = {
  baseURL: process.env.BASE_URL || getApiBaseUrl(),
  timeout: 20000,
  maxContentLength: 2000,
  headers: headers,
  withCredentials: true, // 允许携带cookie
  paramsSerializer: stringifyQuery
};

axiosInstance = axios.create(AXIOS_DEFAULT_CONFIG);


export default axiosInstance;

export const apiGetTodos = () => {
  return axiosInstance.post("/get-todos");
};

export const apiSetTodos = (todos) => {
  return axiosInstance.post("/set-todos", {data: todos});
};
