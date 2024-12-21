import axios from "axios";

const apiUrl = "http://localhost:8080/api/trip"

export default class TripApi {
  static get() {
    return axios.get(apiUrl);
  }
  static getById(id) {
    return axios.get(apiUrl + `/${id}`);
  }
}
