import { MapContainer, TileLayer, Marker, Popup, Polyline } from 'react-leaflet';

export default function TripMap({positions}) {
  return (
    <MapContainer style={{ height: '500px', width: '50%' }} center={[51,5]} zoom={4} scrollWheelZoom={true}>
      <TileLayer attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
        {positions.map((position) =>
          <div key={position[0].id}>
            <Marker position={position[1]}>
              <Popup>
                Starting position
              </Popup>
            </Marker>
            <Marker position={position[2]}>
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
