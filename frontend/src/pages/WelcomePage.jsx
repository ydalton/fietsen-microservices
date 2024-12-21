import { useRecoilState } from "recoil"
import { userState } from "../store"

export default function WelcomePage() {
  const [user, setUser] = useRecoilState(userState);

  return (
    <>
      <h1>Welcome to CycleTracker
        {user ? ', ' + (user?.given_name) : ''}!
      </h1>
      {
        !user
        ? <p>Please log in to do anything useful.</p>
        : <p>Click on the links above to view the dashboard.</p>
      }
    </>
  )
}
