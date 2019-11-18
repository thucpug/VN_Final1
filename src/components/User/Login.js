import React, { Component } from "react";
import { connect } from "react-redux";
import { actLoginRequest } from "../../redux/actions";
import { Link } from "react-router-dom";
class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      role: "",
    };
  }
  onChange = e => {
    this.setState({
      [e.target.name]: e.target.value,
    });
  };
  onLogin = e => {
    e.preventDefault();
    var user = {
      username: this.state.username,
      password: this.state.password,
    };
    this.props.onLogin(JSON.stringify(user));
    this.props.history.push("/home");
  };
  render() {
    var { username, password, role } = this.state;
    return (
      <div>
        <div className="login-box">
          <div className="login-logo">
            <a href="../../index2.html">
              <b>BOOK</b>STORE
            </a>
          </div>
          {/* /.login-logo */}
          <div className="login-box-body">
            <p className="login-box-msg">Sign in to start your session</p>
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
              <div className="row">
                <div className="col-xs-8">
                  <div className="checkbox icheck">
                    <label>
                      <input type="checkbox" /> Remember Me
                    </label>
                  </div>
                </div>
                {/* /.col */}
                <div className="col-xs-4">
                  <button
                    onClick={this.onLogin}
                    className="btn btn-primary btn-block btn-flat"
                  >
                    Sign In
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
                <i className="fa fa-facebook" /> Sign in using Facebook
              </a>
              <a
                href="#"
                className="btn btn-block btn-social btn-google btn-flat"
              >
                <i className="fa fa-google-plus" /> Sign in using Google+
              </a>
            </div>
            {/* /.social-auth-links */}
            <a href="#">I forgot my password</a>
            <br />
            <Link to="/register" className="text-center">
              Register a new membership
            </Link>
          </div>
          {/* /.login-box-body */}
        </div>
      </div>
    );
  }
}
const mapStateToProps = state => {
  return {
    auth: state.auth,
  };
};
const mapDispatchToProps = (dispatch, props) => {
  return {
    onLogin: user => {
      dispatch(actLoginRequest(user));
    },
    // onDeleteBook: id => {
    //   dispatch(actDeleteBookRequest(id));
    // },
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(Login);
