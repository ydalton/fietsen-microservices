import { Link, Outlet } from "react-router";
import { GoogleLogin, googleLogout } from "@react-oauth/google";
import { tokenState, userState } from "../store";
import { useRecoilState } from "recoil";

export default function Layout() {
  const [token, setToken] = useRecoilState(tokenState);
  const [user, setUser] = useRecoilState(userState);

  const jwtDecode = (token) => {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split('')
        .map((c) => `%${`00${c.charCodeAt(0).toString(16)}`.slice(-2)}`)
        .join('')
    );

    return JSON.parse(jsonPayload);
  };

  function logout() {
    googleLogout();
    setUser(null);
    setToken(token);
  }

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
            onSuccess={response => {
              setToken(response.credential);
              setUser(jwtDecode(response.credential));
            }}
            onError={() => {
              console.log('Login Failed');
            }}
            /> : <div style={{ 'display': 'flex' }}>
            <p id="login">{user.name}</p>
            <button className="link" onClick={logout}>
              Log out
            </button>
          </div>
        }
        </div>
      </nav>
      <main>
        <Outlet />
      </main>
    </>
  );
}
