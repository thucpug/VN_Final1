import React, { Component } from "react";
import "./App.css";
import Header from "./components/Header/Header";
import Menu from "./components/Menu/Menu";
import routes from "./routers/router";
import { Switch, Route, BrowserRouter as Router } from "react-router-dom";
import { connect } from "react-redux";
import HomePage from "./pages/HomePage/HomePage";
class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isAuthentication: undefined,
    };
  }
  componentDidMount() {
    var tokenSession = localStorage.getItem("accessToken");
    if (tokenSession) {
      this.setState({
        isAuthentication: tokenSession,
      });
    }
  }
  UNSAFE_componentWillReceiveProps(nextProps) {
    if (nextProps && nextProps.auth) {
      var { auth } = nextProps;
      console.log(auth);

      this.setState({
        isAuthentication: auth.isAuthentication,
      });
    }
  }
  render() {
    var { isAuthentication } = this.state;
    if (isAuthentication === undefined) {
      return (
        <Router>
          <div> {this.showContentsMenu(routes)}</div>
        </Router>
      );
    }
    return (
      <Router>
        <Header></Header>
        <Menu></Menu>
        <div className="content-wrapper"> {this.showContentsMenu(routes)}</div>
      </Router>
    );
  }
  showContentsMenu = routes => {
    var result = null;
    result = routes.map((route, index) => {
      return (
        <Route
          key={index}
          path={route.path}
          exact={route.exact}
          component={route.main}
        ></Route>
      );
    });
    return <Switch>{result}</Switch>;
  };
}
const mapStateToProps = state => {
  return {
    auth: state.auth,
  };
};
const mapDispatchToProps = (dispatch, props) => {
  return {
    // fetchAllBills: (page, limit) => {
    //   dispatch(actFetchBillsRequest(page, limit));
    // },
    // onRemoveBill: id => {
    //   dispatch(actRemoveBillRequest(id));
    // },
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(App);
