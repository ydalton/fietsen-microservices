import { useState, useEffect } from "react";
import { Link } from "react-router";
import TripApi from "../services/TripApi";

export default function TripTable() {
  const [trips, setTrips] = useState([]);

  useEffect(() => {
    get();
  }, [])

  const get = async() => {
    await TripApi.get().then((response) => setTrips(response.data));
  }

  if(trips.length < 1) {
    return (<p>No trips found.</p>)
  } else {
    return (
      <table>
        <thead>
          <tr>
            <th>Start time</th>
            <th>End time</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {trips.map((trip) => {
            return (
              <tr key={trip.id}>
                <td>{trip.startTime}</td>
                <td>{trip.endTime}</td>
                <td>
                  <Link to={"/trips/" + trip.id}>
                    <button>
                      More info
                    </button>
                  </Link>
                  <button>
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
