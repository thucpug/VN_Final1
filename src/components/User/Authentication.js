import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import ApiService from "../../utils/ApiService";
import getJwt from "../../helpers/jwt/jwt";
class Authentication extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isRegister: undefined,
    };
  }
  componentDidMount() {
    const jwt = getJwt();
    if (!jwt) {
      this.props.history.push("/login");
    } else {
      this.setState({
        isRegister: true,
      });
      this.props.history.push("/home");
    }
  }
  render() {
    if (this.state.isRegister === undefined) {
      return (
        <div>
          <h1>Loading...</h1>
        </div>
      );
    }

    return <div>{this.props.children}</div>;
  }
}

export default withRouter(Authentication);
