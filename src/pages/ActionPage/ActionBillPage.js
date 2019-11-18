import React, { Component } from "react";
import FormActionBill from "../../components/Content/FormAction/FormActionBill";

class ActionBillPage extends Component {
  render() {
    var { history } = this.props;
    return (
      <div>
        <FormActionBill history={history}></FormActionBill>
      </div>
    );
  }
}

export default ActionBillPage;
