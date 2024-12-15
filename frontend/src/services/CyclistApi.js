import axios from "axios";

const apiUrl = "http://localhost:8080/api/cyclist"

export default class CyclistApi {
  static get() {
    return axios.get(apiUrl);
  }
  static delete(id) {
    return axios.delete(apiUrl + `/${id}`)
  }
}
