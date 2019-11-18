import React, { Component } from "react";

class BillHighItem extends Component {
  render() {
    var { bookitem, bool } = this.props;
    return <div>{this.showitem(bool, bookitem)}</div>;
  }
  showitem = (bool, bookitem) => {
    var result = null;
    if (bool) {
      result = <li>{bookitem.bookName}</li>;
    } else {
      result = <li>{bookitem.counts}</li>;
    }
    return result;
  };
}

export default BillHighItem;
