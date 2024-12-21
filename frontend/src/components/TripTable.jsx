import { useState, useEffect } from "react";
import { Link } from "react-router";
import Api from "../services/Api";
import { tokenState } from "../store";
import { useRecoilState } from "recoil";
import { handleError } from "../util";
import TripMap from "./TripMap";

export default function TripTable() {
  const [trips, setTrips] = useState([]);
  const [token, setToken] = useRecoilState(tokenState);
  const [positions, getPositions] = useState([]);
  const api = new Api("http://localhost:8080/api/trip");

  useEffect(() => {
    get();
  }, [])

  const get = async() => {
    await api.get().then((response) => setTrips(response.data));
  }

  const deleteTrip = async(id, token) => {
    await api.delete(id, token).catch((error) => handleError(error));
    await get();
  }

  if(trips.length < 1) {
    return (<p>No trips found.</p>)
  } else {
    return (
      <div style={{display: 'flex'}}>
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
                    <button onClick={() => deleteTrip(trip.id, token)}>
                      Delete
                    </button>
                  </td>
                </tr>
              )
            })}
          </tbody>
        </table>
        <TripMap positions={trips.map((trip) => {
          let position = [
            {
              id: trip.id,
            },
            [
              trip.startLocation.latitude,
              trip.startLocation.longitude
            ],
            [
              trip.endLocation.latitude,
              trip.endLocation.longitude
            ]
          ];
          return position;
        })}>
        </TripMap>
      </div>
    );
  }
}
