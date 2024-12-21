import axios from "axios";

export default class Api {
  constructor(apiUrl) {
    this.apiUrl = apiUrl;
  }

  getConfig(token) {
    let config = {};
    if(token !== undefined) {
      config = {
        headers: {
          Authorization: `Bearer ${token}`,
        }
      };
    }
    return config;
  }

  get() {
    return axios.get(this.apiUrl);
  }
  getById(id) {
    return axios.get(this.apiUrl + `/${id}`);
  }
  post(token) {
    return axios.get(this.apiUrl, this.getConfig(token));
  }
  delete(id, token) {
    return axios.delete(this.apiUrl + `/${id}`, this.getConfig(token))
  }
}
