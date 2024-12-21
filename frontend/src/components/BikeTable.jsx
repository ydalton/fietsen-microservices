import { useEffect, useState } from "react";
import Api from "../services/Api";
import { useRecoilState } from "recoil";
import { tokenState } from "../store";
import { handleError } from "../util";

export default function BikeTable() {
  const [bikes, setBikes] = useState([]);
  const [token, setToken] = useRecoilState(tokenState);
  const api = new Api("http://localhost:8080/api/bike")

  useEffect(() => {
    get();
  }, []);

  const get = async() => {
    await api.get()
      .then((response) => setBikes(response.data));
  }

  const deleteBike = async (id, token) => {
    await api.delete(id, token).catch((error) => handleError(error));
    await get();
  }

  if(bikes.length < 1) {
    return <p>No bikes found.</p>
  } else {
    return (
      <>
        <table>
          <thead>
            <tr>
              <th>Manufacturer</th>
              <th>Model</th>
              <th>Year</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {bikes.map((bike) => {
              return (
                <tr key={bike.id}>
                  <td>{ bike.manufacturer }</td>
                  <td>{ bike.model }</td>
                  <td>{ bike.year }</td>
                  <td>
                    <button type="button" onClick={() => deleteBike(bike.id, token)}>
                      Delete
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </>
    );

  }
}
