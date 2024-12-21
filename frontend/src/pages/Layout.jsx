import { Link, Outlet } from "react-router";
import { GoogleLogin } from "@react-oauth/google";

export default function Layout({user}) {
  return (
    <>
      <nav>
        <div id="top">
        <ul>
          <li id="title">
            <Link to="/">CycleTracker</Link>
          </li>
          <li>
            <Link to="/bikes">Bikes</Link>
          </li>
          <li>
            <Link to="/cyclists">Cyclists</Link>
          </li>
          <li>
            <Link to="/trips">Trips</Link>
          </li>
        </ul>
        {
          !user ?
          <GoogleLogin
            onSuccess={credentialResponse => {
              console.log(credentialResponse);
            }}
            onError={() => {
              console.log('Login Failed');
            }}
          /> : <p>user.name</p>
        }
        </div>
      </nav>
      <main>
        <Outlet />
      </main>
    </>
  );
}
