import React, { Component } from "react";
import { getJwt } from "./../../helpers/jwt/jwt";
import { withRouter } from "react-router-dom";
import axios from "axios";

class AuthenticatedComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      user: undefined,
    };
  }

  componentDidMount() {
    const jwt = getJwt();
    if (!jwt) {
      this.props.history.push("/Login");
    }
  }

  render() {
    if (this.state.user === undefined) {
      return (
        <div>
          <h1>Loading...</h1>
        </div>
      );
    }

    return <div>{this.props.children}</div>;
  }
}

export default withRouter(AuthenticatedComponent);
