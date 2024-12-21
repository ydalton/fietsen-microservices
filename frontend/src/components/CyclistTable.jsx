import { useState, useEffect } from "react";
import Api from "../services/Api";
import { useRecoilState } from "recoil";
import { tokenState } from "../store";
import { handleError } from "../util";

export default function CyclistTable() {
  const [cyclists, setCyclists] = useState([]);
  const [token, setToken] = useRecoilState(tokenState);
  const api = new Api("http://localhost:8080/api/cyclist");

  useEffect(() => {
    get();
  }, [])

  const get = async() => {
    await api.get().then((response) => setCyclists(response.data));
  }

  const deleteCyclist = async(id, token) => {
    await api.delete(id, token).catch((error) => handleError(error));
    await get();
  }

  if(cyclists.length < 1) {
    return (<p>No cyclists found.</p>)
  } else {
    return (
      <table>
        <thead>
          <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {cyclists.map((cyclist) => {
            return (
              <tr key={cyclist.id}>
                <td>{cyclist.firstName}</td>
                <td>{cyclist.lastName}</td>
                <td>{cyclist.age}</td>
                <td>{cyclist.gender}</td>
                <td>
                  <button onClick={() => deleteCyclist(cyclist.id, token)}>
                    Delete
                  </button>
                </td>
              </tr>
            )
          })}
        </tbody>
      </table>
    );
  }
}
