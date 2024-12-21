import { MapContainer, TileLayer, Marker, Popup, Polyline } from 'react-leaflet';

import markerIconPng from "leaflet/dist/images/marker-icon.png"
import {Icon} from 'leaflet'

export default function TripMap({positions}) {
  let icon = new Icon({ iconUrl: markerIconPng, iconSize: [25, 41], iconAnchor: [12, 41] });

  return (
    <MapContainer style={{ height: '500px', width: '50%' }} center={[51,5]} zoom={4} scrollWheelZoom={true}>
      <TileLayer attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
        {positions.map((position) =>
          <div key={position[0].id}>
            <Marker position={position[1]} icon={icon}>
              <Popup>
                Starting position
              </Popup>
            </Marker>
            <Marker position={position[2]} icon={icon}>
              <Popup>
                End position
              </Popup>
            </Marker>
            <Polyline positions={[position[1], position[2]]} />
          </div>
        )
        }
    </MapContainer>
  );
}
