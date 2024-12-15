import { useEffect, useState } from "react";
import BikeApi from "../services/BikeApi";

export default function BikeTable() {
  const [bikes, setBikes] = useState([]);

  useEffect(() => {
    get();
  }, []);

  const get = async() => {
    await BikeApi.get().then((response) => setBikes(response.data));
  }

  const deleteBike = async (id) => {
    await BikeApi.delete(id);
    await get();
  }

  if(bikes.length < 0) {
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
                    <button type="button" onClick={() => deleteBike(bike.id)}>
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
