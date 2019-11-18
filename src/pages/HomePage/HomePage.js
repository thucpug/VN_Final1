import React, { Component } from "react";
import logo from "./../../assets/image/photo3.jpg";
class HomePage extends Component {
  render() {
    return (
      <div >
        <img src={logo} alt="" style={{ width: "100%", height: "800px" }} />
      </div>
    );
  }
}
export default HomePage;
