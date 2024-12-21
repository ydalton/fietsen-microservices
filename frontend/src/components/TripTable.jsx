import { useState, useEffect } from "react";
import { Link } from "react-router";
import Api from "../services/Api";
import { tokenState } from "../store";
import { useRecoilState } from "recoil";
import { handleError } from "../util";

export default function TripTable() {
  const [trips, setTrips] = useState([]);
  const [token, setToken] = useRecoilState(tokenState);
  const api = new Api("http://localhost:8080/api/trip");

  useEffect(() => {
    get();
  }, [])

  const get = async() => {
    await api.get().then((response) => setTrips(response.data));
  }

  const deleteTrip = async(id, token) => {
    await api.delete().catch((error) => handleError(error));
    await get();
  }

  if(trips.length < 1) {
    return (<p>No trips found.</p>)
  } else {
    return (
      <table>
        <thead>
          <tr>
            <th>Start location</th>
            <th>End location</th>
            <th>Start time</th>
            <th>End time</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {trips.map((trip) => {
            return (
              <tr key={trip.id}>
                <td>{trip.startLocation.latitude}, {trip.startLocation.longitude}</td>
                <td>{trip.endLocation.latitude}, {trip.endLocation.longitude}</td>
                <td>{trip.startTime}</td>
                <td>{trip.endTime}</td>
                <td>
                  <Link to={"/trips/" + trip.id}>
                    <button>
                      More info
                    </button>
                  </Link>
                  <button onClick={() => deleteTrip(trip.id, token)}>
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
