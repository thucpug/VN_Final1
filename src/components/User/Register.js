import React, { Component } from "react";
import { connect } from "react-redux";
import ApiService from "../../utils/ApiService";
class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      retypePassword: "",
    };
  }
  onChange = e => {
    this.setState({
      [e.target.name]: e.target.value,
    });
    console.log(this.state);
  };
  onSubmit = e => {
    e.preventDefault();
    var { username, password, retypePassword } = this.state;
    if (retypePassword !== password) {
      alert("Enter password defferent!");
    } else {
      var userReg = {
        username: username,
        password: password,
      };
      ApiService.callApi("auth/register", "POST", userReg).then(res => {
        console.log(res.data);
        if (res.data.data === "Register Ok") {
          console.log(this.props.history);
          this.props.history.push("/");
        }
      });
    }
  };
  render() {
    var { username, password, retypePassword } = this.state;
    return (
      <div className="register-box">
        <div className="register-logo">
          <a href="../../index2.html">
            <b>BOOK-</b>Store
          </a>
        </div>
        <div className="register-box-body">
          <p className="login-box-msg">Register a new membership</p>
          <form>
            <div className="form-group has-feedback">
              <input
                type="text"
                name="username"
                value={username}
                onChange={this.onChange}
                className="form-control"
                placeholder="User name"
              />
              <span className="glyphicon glyphicon-envelope form-control-feedback" />
            </div>
            <div className="form-group has-feedback">
              <input
                type="password"
                name="password"
                value={password}
                onChange={this.onChange}
                className="form-control"
                placeholder="Password"
              />
              <span className="glyphicon glyphicon-lock form-control-feedback" />
            </div>
            <div className="form-group has-feedback">
              <input
                type="password"
                name="retypePassword"
                value={retypePassword}
                onChange={this.onChange}
                className="form-control"
                placeholder="Retype password"
              />
              <span className="glyphicon glyphicon-log-in form-control-feedback" />
            </div>
            <div className="row">
              <div className="col-xs-8">
                <div className="checkbox icheck">
                  <label>
                    <input type="checkbox" /> I agree to the{" "}
                    <a href="#">terms</a>
                  </label>
                </div>
              </div>
              {/* /.col */}
              <div className="col-xs-4">
                <button
                  onClick={this.onSubmit}
                  className="btn btn-primary btn-block btn-flat"
                >
                  Register
                </button>
              </div>
              {/* /.col */}
            </div>
          </form>
          <div className="social-auth-links text-center">
            <p>- OR -</p>
            <a
              href="#"
              className="btn btn-block btn-social btn-facebook btn-flat"
            >
              <i className="fa fa-facebook" /> Sign up using Facebook
            </a>
            <a
              href="#"
              className="btn btn-block btn-social btn-google btn-flat"
            >
              <i className="fa fa-google-plus" /> Sign up using Google+
            </a>
          </div>
          <a href="login.html" className="text-center">
            I already have a membership
          </a>
        </div>
        {/* /.form-box */}
      </div>
    );
  }
}
const mapStateToProps = state => {
  return {
    // : state.auth,
  };
};
const mapDispatchToProps = (dispatch, props) => {
  return {
    // onLogin: user => {
    //   dispatch(actLoginRequest(user));
    // },
    // onDeleteBook: id => {
    //   dispatch(actDeleteBookRequest(id));
    // },
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(Register);
