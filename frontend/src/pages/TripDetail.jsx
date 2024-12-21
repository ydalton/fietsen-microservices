import { useParams } from "react-router";
import { useState, useEffect } from "react";
import Api from "../services/Api";
import TripMap from "../components/TripMap";

export default function TripDetail() {
  const id = useParams().id;
  const api = new Api("http://localhost:8080/api/trip");

  const [trip, setTrip] = useState(null);
  const [startPos, setStartPos] = useState([0, 0]);
  const [endPos, setEndPos] = useState([0, 0]);

  useEffect(() => {
    get();
  }, [])

  const get = async () => {
    await api.getById(id).then((response) => {
      let tmp = response.data;
      setTrip(tmp);
      setStartPos([tmp.startLocation.latitude, tmp.startLocation.longitude]);
      setEndPos([tmp.endLocation.latitude, tmp.endLocation.longitude])
    });
  };

  if(trip == null) {
    return <p>Loading info...</p>;
  } else {
    return (
      <div style={{ display: 'flex' }}>
        <div>
          <p>Start time: {trip.startTime}</p>
          <p>End time: {trip.endTime}</p>
        </div>
        <TripMap startPos={startPos} endPos={endPos} />
      </div>
    );
  }
}
