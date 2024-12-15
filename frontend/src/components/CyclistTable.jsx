import { useState, useEffect } from "react";
import CyclistApi from "../services/CyclistApi";

export default function CyclistTable() {
  const [cyclists, setCyclists] = useState([]);

  useEffect(() => {
    get();
  }, [])

  const get = async() => {
    await CyclistApi.get().then((response) => setCyclists(response.data));
  }

  const deleteCyclist = async(id) => {
    await CyclistApi.delete(id);
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
                  <button onClick={() => deleteCyclist(cyclist.id)}>
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
