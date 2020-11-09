import Config from "./config";
import axios from "axios";

export const vueInstance = {
  instance: null,
  store: null
};

let runningRequest = 0;

function addRequestCount() {
  runningRequest++;
  if (runningRequest > 0 && vueInstance.store != null) {
    vueInstance.store.state.loading = true;
  }
}



const axiosInstance = axios.create({
  baseURL: Config.backEndUrl,
  timeout: 30000,
  withCredentials: true
});


const dataHandle = response => {
  return response;
};

const innerGet = function() {
  addRequestCount();
  return axiosInstance.get.apply(this, arguments).then(dataHandle);
};

const innerPost = function() {
  addRequestCount();
  return axiosInstance.post.apply(this, arguments).then(dataHandle);
};

const innerDelete = function() {
  addRequestCount();
  return axiosInstance.delete.apply(this, arguments).then(dataHandle);
};

const innerPut = function() {
  addRequestCount();
  return axiosInstance.put.apply(this, arguments).then(dataHandle);
};

export const pureGet = function(url) {
  return innerGet(url);
};

export const pureDelete = function(url) {
  return innerDelete(url);
};

export const get = function(url, params) {
  return innerGet(url, {
    params: params
  });
};

export const post = function(url, data) {
  return innerPost(url, data, {
    headers: {
      "Content-Type": "application/json",
     "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods":"PUT,POST,GET,DELETE,OPTIONS",
      'Access-Control-Allow-Headers': 'Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild'
    }
  });
};

export const del = function(url, params) {
  return innerDelete(url, {
    params: params
  });
};

export const put = function(url, data) {
  return innerPut(url, data, {
    headers: {
      "Content-Type": "application/json"
    }
  });
};
