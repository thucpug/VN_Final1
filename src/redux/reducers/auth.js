import * as Types from "./../constants/index";
import jwtDecode from "jsonwebtoken";
import setAuthToken from "../../utils/setAuthToken";
var initialState = {
  isAuthentication: undefined,
  user: {
    token: "",
    role: "",
    userName: "",
  },
};
const auth = (state = initialState, action) => {
  var index = -1;
  switch (action.type) {
    case Types.LOGIN:
      setAuthToken.setAuthrizationToken(action.token.accessToken);
      state.user.token = action.token.accessToken;
      localStorage.setItem("accessToken", action.token.accessToken);
      var temp = jwtDecode.decode(action.token.accessToken);
      state.user.userName = temp.sub;
      state.isAuthentication = true;
      console.log(state);
      console.log(temp);
      return { ...state };
    default:
      return { ...state };
  }
};
export default auth;
