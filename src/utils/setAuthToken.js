import axios from "axios";
class setAuthToken {
  setAuthrizationToken(token) {
    if (token) {
      axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    } else {
      delete axios.defaults.headers.common["Authorization"];
    }
  }
  setHeaderPostJson() {
    axios.defaults.headers.post["Content-Type"] = "application/json";
  }
}

export default new setAuthToken();
