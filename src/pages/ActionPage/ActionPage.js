import React, { Component } from "react";
import FormAction from "../../components/Content/FormAction/FormAction";

class ActionPage extends Component {
  render() {
    var { history, match } = this.props;
    return (
      <div>
        <FormAction history={history} match={match}></FormAction>
      </div>
    );
  }
}
export default ActionPage;
