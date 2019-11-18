import axios from "axios";
import * as config from "./../config/config";
import setAuthrizationToken from "./setAuthToken";
class ApiService {
  onuploadFile(data) {
    return axios
      .post("http://localhost:8080/api/image/upload", data)
      .catch(err => {
        console.log(err);
      });
  }
  callApi(endpoint, method = "GET", body) {
    console.log(`${config.API_URL}/${endpoint}`);
    //setAuthrizationToken(null);
    return axios({
      method: method,
      url: `${config.API_URL}/${endpoint}`,
      data: body,
      header: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
    }).catch(err => {
      console.log(err);
    });
  }
}
export default new ApiService();
