import { Link, Outlet } from "react-router";

export default function Layout() {
  return (
    <>
      <nav>
        <ul>
          <li>
            <Link to="/">Cyclist System</Link>
          </li>
          <li>
            <Link to="/bikes">Bikes</Link>
          </li>
          <li>
            <Link to="/cyclists">Cyclists</Link>
          </li>
        </ul>
      </nav>
      <main>
        <Outlet />
      </main>
    </>
  );
}
