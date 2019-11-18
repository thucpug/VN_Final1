import React, { Component } from "react";
import Login from "../../components/User/Login";

class LoginPage extends Component {
  render() {
    var { history } = this.props;
    return (
      <div>
        <Login history={history}></Login>
      </div>
    );
  }
}

export default LoginPage;
