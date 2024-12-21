import { MapContainer, TileLayer, Marker, Popup, Polyline } from 'react-leaflet';

export default function TripMap({startPos, endPos}) {
  return (
    <MapContainer style={{ height: '400px', minWidth: '400px' }} center={startPos} zoom={5} scrollWheelZoom={true}>
      <TileLayer attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
      <Marker position={startPos}>
        <Popup>
          Starting position
        </Popup>
      </Marker>
      <Marker position={endPos}>
        <Popup>
          Ending position
        </Popup>
      </Marker>
      <Polyline positions={[startPos, endPos]} />
    </MapContainer>
  );
}
