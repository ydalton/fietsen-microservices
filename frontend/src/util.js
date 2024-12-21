export function handleError(error) {
  let message = "";

  switch(error.request.status) {
  case 401:
    message = "You are not logged in";
    break;
  default:
    message = "An unknown error occurred.";
    break;
  }

  alert(`Error: ${message}`);
}
