import axios from "axios";

const apiUrl = "http://localhost:8080/api/bike"

export default class BikeApi {
  static get() {
    return axios.get(apiUrl);
  }
  static delete(id) {
    return axios.delete(apiUrl + `/${id}`)
  }
}
