import axios from "axios";

export default class Api {
  constructor(apiUrl) {
    this.apiUrl = apiUrl;
  }

  get() {
    return axios.get(this.apiUrl);
  }
  getById(id) {
    return axios.get(this.apiUrl + `/${id}`);
  }
  post(token) {
    return axios.get(this.apiUrl, {
      headers: {
        Authorization: `Bearer ${token}`,
      }
    });
  }
  delete(id, token) {
    return axios.delete(this.apiUrl + `/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      }
    })
  }
}
