import React, { Component } from "react";
import Register from "../../components/User/Register";

class RegisterPage extends Component {
  render() {
    var { history } = this.props;
    return (
      <div>
        <Register history={history}></Register>
      </div>
    );
  }
}

export default RegisterPage;
